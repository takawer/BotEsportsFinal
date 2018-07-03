package controller;

import com.pengrad.telegrambot.model.Update;
import model.*;
import view.*;

public class ControllerProcuraJogador implements ControllerProcura{
	
	
	private Model model;
	private View view;
	
	public ControllerProcuraJogador(Model model, View view){
		this.model = model; 
		this.view = view; 
	}
	
	public void search(Update update){
		view.sendTypingMessage(update);
		model.searchJogador(update);
	}

}