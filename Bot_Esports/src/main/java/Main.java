import java.io.IOException;

import model.*;
import view.*;

public class Main {
	
	private static Model model;
	
	public static void main(String[] args) throws IOException,NullPointerException {
		
		model = Model.getInstance();
		View view = new View(model);
		model.registerObserver(view); //connection Model -> View
		view.receiveUsersMessages();

		}
	
	
		
	}

	
	



