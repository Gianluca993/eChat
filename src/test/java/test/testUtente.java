package test;

import com.gianluca993.modelli.Utente;

import dao.MongoConnection;
import dao.UtenteDao;
import com.mongodb.MongoClient;

public class testUtente {

	public static void main(String[] args) {
		
		Utente u = new Utente("abra", "cadabra@", "alakazamm");
		Utente b = new Utente("sano", "donato@", "shabadabam");
		Utente c = new Utente("dioa", "canaro@", "sdoganato");
		MongoClient mc = MongoConnection.connetti();
		UtenteDao.upUtente(mc, u);
		UtenteDao.upUtente(mc, b);
		UtenteDao.upUtente(mc, c);
		u.addAmico(b);
		u.addAmico(c);
		UtenteDao.editUtente(mc, u.getId(), u.utenteToDocument());
		System.out.println(u.utenteToDocument().toString());

		
	}
}
