package dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;

public class MongoConnection {

	public static MongoClient connetti() {
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		return mongoClient;
	}
	
}
