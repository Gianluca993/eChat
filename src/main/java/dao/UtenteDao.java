package dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.gianluca993.modelli.Utente;
import com.mongodb.client.FindIterable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UtenteDao {

	public static void upUtente(MongoClient mc, Utente u) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		mcoll.insertOne(u.utenteToDocument());
		u.setId(getIdUtente(mc, u.getEmail()));
	}
	private static ObjectId getIdUtente(MongoClient mc, String email) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("email", email);
		FindIterable<Document> fI = mcoll.find(query);
		return fI.first().getObjectId("_id");
	}
	public static Document getUtente(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}	
	public static Document getUtenteByMail(MongoClient mc, String email) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("email", email);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}
	public static Document getUtenteByName(MongoClient mc, String name) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("nickname", name);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}
	public static Document editUtente(MongoClient mc, ObjectId id, Document newUtente) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndReplace(Filters.eq("_id", id), newUtente);
		return newUtente;
	}
	public static void deleteUtente(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("utenti");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndDelete(Filters.eq("_id", id));
	}
}
