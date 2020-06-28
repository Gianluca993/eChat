package dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.gianluca993.modelli.Room;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class RoomDao {

	public static void upRoom(MongoClient mc, Room r) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		mcoll.insertOne(r.roomToDocument());
		r.setId(getIdRoom(mc, r.getNome()));
	}
	private static ObjectId getIdRoom(MongoClient mc, String nome) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		BasicDBObject query = new BasicDBObject();
	    query.put("nome", nome);
		FindIterable<Document> fI = mcoll.find(query);
		return fI.first().getObjectId("_id");
	}
	public static Document getRoom(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}
	public static Document getRoomByName(MongoClient mc, String name) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		BasicDBObject query = new BasicDBObject();
	    query.put("nickname", name);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}
	public static Document editRoom(MongoClient mc, ObjectId id, Document newRoom) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndReplace(Filters.eq("_id", id), newRoom);
		return newRoom;
	}
	public static void deleteRoom(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("rooms");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndDelete(Filters.eq("_id", id));
	}
	
}
