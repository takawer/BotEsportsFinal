package controller;

import com.pengrad.telegrambot.model.Update;
import model.*;
import view.*;

public class ControllerProcuraTime implements ControllerProcura{
	
	private Model model;
	private View view;
	
	public ControllerProcuraTime(Model model, View view){
		this.model = model; 
		this.view = view; 
	}
	
	public void search(Update update){
		view.sendTypingMessage(update);
		model.searchTime(update);
	}


}

