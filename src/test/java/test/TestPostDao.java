package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.bson.Document;
import org.junit.Test;

import com.gianluca993.modelli.Post;
import com.gianluca993.modelli.Room;
import com.gianluca993.modelli.Utente;
import com.mongodb.MongoClient;

import dao.MongoConnection;
import dao.PostDao;
import dao.RoomDao;
import dao.UtenteDao;

public class TestPostDao {

	@Test
	public void test_getTitle() throws SQLException {
        // given
        String title = "John";
        String body = "Lorem Ipsum dolorem sit amet";
        Utente u = new Utente("John", "John@", "blablablabla");
        MongoClient mc = MongoConnection.connetti();
        UtenteDao.upUtente(mc, u);
        Post post = new Post(u, body, body);
        System.out.println(post.getTitle());
        
        // then
        
        
        PostDao.upPost(mc, post);
        Document postDb = PostDao.getPostById(mc, post.getId());
        Post post2 = new Post(postDb);
        String result = post2.getTitle();
        System.out.println(result);
        
		mc.close();

        
        // expect
        assertEquals(title, result, "The title must be the same");
    }

}
