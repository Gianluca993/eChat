package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.gianluca993.modelli.Post;
import com.gianluca993.modelli.Room;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class PostDao {

	public static void upPost(MongoClient mc, Post p) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		mcoll.insertOne(p.postToDocument());
		p.setId(getIdPost(mc, p.getOp().getId()));
	}
	private static ObjectId getIdPost(MongoClient mc, ObjectId opId) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
	    query.put("op", opId);
		FindIterable<Document> fI = mcoll.find(query);
		return fI.first().getObjectId("_id");
	}
	public static Document getPostById(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
		FindIterable<Document> fI = mcoll.find(query);		
		return fI.first();
	}
	public static List<Post> getPostsByOpId(MongoClient mc, ObjectId opId) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
	    query.put("op", opId);
		FindIterable<Document> fI = mcoll.find(query);
		List<Post> list = new ArrayList<>();
		for(Document d : fI) {
			list.add(new Post(d));
		}
		Collections.sort(list);
		return list;
	}
	public static Document editPost(MongoClient mc, ObjectId id, Document newPost) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndReplace(Filters.eq("_id", id), newPost);
		return newPost;
	}
	public static void deletePost(MongoClient mc, ObjectId id) {
		MongoDatabase md = mc.getDatabase("eChat");
		MongoCollection<Document> mcoll = md.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", id);
	    mcoll.findOneAndDelete(Filters.eq("_id", id));
	}
	
}
