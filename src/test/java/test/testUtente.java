package test;

import com.gianluca993.modelli.Utente;

import dao.MongoConnection;
import dao.UtenteDao;
import com.mongodb.MongoClient;

public class testUtente {

	public static void main(String[] args) {
		
		Utente u = new Utente("abra", "cadabra@", "alakazamm");
		u.addAmico(new Utente("sano", "donato@", "shabadabam"));
		u.addAmico(new Utente("dioa", "canaro@", "sdoganato"));
		System.out.println(u.utenteToDocument().toString());

		System.out.println(UtenteDao.getUtente(MongoConnection.connetti(), "5ef7c7f55cb5d42d7cbc4fc0"));
	}
}
