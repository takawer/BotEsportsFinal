package model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

import model.Jogador.JogadorAttr;
import view.Observer;
import view.Subject;

public class Model implements Subject {

	private List<Observer> observers = new LinkedList<Observer>();

	private static Model uniqueInstance;

	private Model() {
	}

	public static Model getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers(long chatId, String playersData) {
		for (Observer observer : observers) {
			observer.update(chatId, playersData);
		}
	}

	/*
	 * Método que procura por jogadores brasileiros
	 * 
	 * @param Recebe object Update
	 * 
	 * @return retorna para View de jogadores brasileiros desiarilizada
	 */

	public void searchJogador(Update update) {
		String game = update.message().text();

		URL url = null;
		try {
			url = new URL("https://esports.glenndehaan.com/api/players//" + game);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(url);
		Utils jog = new Utils();

		Jogador jogador = jog.deserializePlayers(jog.GetResponse(url));

		if (Banco.selectValues(JogadorAttr.class).toString().equals("[]")) {
			this.notifyObservers(update.message().chat().id(), "Verificando na API" + "\n");
			for (JogadorAttr jogadores : jogador.getPlayers()) {
				try {
					if (jogadores.getBirth_place().equals("Brazil")) {
						Banco.insert(jogadores);

						this.notifyObservers(update.message().chat().id(),
								"Local de nascimento: " + jogadores.getBirth_place() + "\nNickname: "
										+ jogadores.getIn_game_name() + "\nNome real:" + jogadores.getReal_life_name()
										+ "\n");
					}
				} catch (NullPointerException e) {
					System.out.println("Algum attr é nulo");
				}
			}
		} else {
			List<JogadorAttr> listJogador = Banco.db.query(JogadorAttr.class);
			this.notifyObservers(update.message().chat().id(), "Verificando banco de dados" + "\n");

			for (JogadorAttr jogadores : listJogador) {
				this.notifyObservers(update.message().chat().id(),
						"Local de nascimento: " + jogadores.getBirth_place() + "\nNickname: "
								+ jogadores.getIn_game_name() + "\nNome real: " + jogadores.getReal_life_name() + "\n");
			}
		}
	}

	/*
	 * Método que procura por times brasileiros
	 * 
	 * @param Recebe object Update
	 * 
	 * @return retorna para View lista de times desiarilizados
	 */

	public void searchTime(Update update) {
		String game = update.message().text();

		URL url = null;
		try {
			url = new URL("https://esports.glenndehaan.com/api/teams/" + game);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Utils util = new Utils();

		Team team = util.deserializeTeams(util.GetResponse(url));

		if (Banco.selectValues(Team.class).toString().equals("[]")) {
			this.notifyObservers(update.message().chat().id(), "Verificando na API" + "\n");
			for (Team time : team.getTeams()) {
				try {
					if (time.getCountry().equals("Brazil")) {
						Banco.insert(time);
						this.notifyObservers(update.message().chat().id(),
								"Pais: " + time.getCountry() + "\nNome do time: " + time.getFull_name()
										+ "\nNome do time curto: " + time.getShort_name());
					}
				} catch (NullPointerException e) {
					System.out.println("Algum attr é nulo");
				}
			}
		} else {
			List<Team> listTeam = Banco.db.query(Team.class);
			this.notifyObservers(update.message().chat().id(), "Verificando banco de dados" + "\n");

			for (Team time : listTeam) {
				this.notifyObservers(update.message().chat().id(), "Pais: " + time.getCountry() + "\nNome do time: "
						+ time.getFull_name() + "\nNome do time curto: " + time.getShort_name());
			}
		}
	}
}