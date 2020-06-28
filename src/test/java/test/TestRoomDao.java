package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.bson.Document;
import org.junit.Test;

import com.gianluca993.modelli.Room;
import com.mongodb.MongoClient;

import dao.MongoConnection;
import dao.RoomDao;


public class TestRoomDao {

	@Test
	public void test_getName() throws SQLException {
        // given
        String nome = "John";
        String password = "Doe";
        Room room = new Room(nome, password);
        System.out.println(room.getNome());
        
        // then
        MongoClient mc = MongoConnection.connetti();
        
        RoomDao.upRoom(mc, room);
        Document roomDb = RoomDao.getRoom(mc, room.getId());
        Room room2 = new Room(roomDb.getString("nome"), roomDb.getString("password"));
        room2.setId(roomDb.getObjectId("_id"));
        String result = room2.getNome();
        System.out.println(result);
        
		mc.close();

        
        // expect
        assertEquals(nome, result, "The name must be the same");
    }

}
