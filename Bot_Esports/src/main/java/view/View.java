package view;

import java.util.List;
import controller.*;
import model.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer {

	TelegramBot bot = TelegramBotAdapter.build("569087317:AAGLrAjzsGMOOJV7U8fYhZk-nYisMBMG7dA\n");

	GetUpdatesResponse updatesResponse;

	SendResponse sendResponse;

	BaseResponse baseResponse;

	int queuesIndex = 0;

	ControllerProcura controllerProcura;

	boolean searchBehaviour = false;

	private Model model;

	public View(Model model) {
		this.model = model;
	}

	public void setControllerProcura(ControllerProcura controllerProcura) {
		this.controllerProcura = controllerProcura;
	}

	public void receiveUsersMessages() {

		while (true) {

			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			
			List<Update> updates = updatesResponse.updates();

			try {
				for (Update update : updates) {

					queuesIndex = update.updateId() + 1;

					if (this.searchBehaviour == true) {
						this.callController(update);

					} else if (update.message().text().equalsIgnoreCase("Jogadores")
							|| update.message().text().equalsIgnoreCase("Jogador")) {

						setControllerProcura(new ControllerProcuraJogador(model, this));
						sendResponse = bot
								.execute(new SendMessage(update.message().chat().id(), "Digite o jogo do player?"));
						this.searchBehaviour = true;

					} else if (update.message().text().equalsIgnoreCase("Times")
							|| update.message().text().equalsIgnoreCase("Time")) {

						setControllerProcura(new ControllerProcuraTime(model, this));
						sendResponse = bot
								.execute(new SendMessage(update.message().chat().id(), "Digite o jogo do time?"));
						this.searchBehaviour = true;

					} else {
						sendResponse = bot
								.execute(new SendMessage(update.message().chat().id(), "Digite 'Jogador' ou 'Time' !"));
					}

				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		}
	}

	public void callController(Update update) {
		this.controllerProcura.search(update);
	}

	public void update(long chatId, String playerData) {
		sendResponse = bot.execute(new SendMessage(chatId, playerData));
		this.searchBehaviour = false;
	}

	public void sendTypingMessage(Update update) {
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}

}
