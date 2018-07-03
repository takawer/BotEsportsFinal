package model;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Banco {
	static ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), 
			"./db/banco");
   
	
	public static void insert(Object object) {
		db.store(object);
	}
	
	
	public static Object selectValues(Class<?> classe) {
		
		ObjectSet<?> result = db.query(classe);
		
		return(result);
		
	}
	

}
