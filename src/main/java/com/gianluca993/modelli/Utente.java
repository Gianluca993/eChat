package com.gianluca993.modelli;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;

import dao.UtenteDao;

public class Utente {

	private ObjectId id;
	private String nickname, 
	email, 
	password;
	private LocalDateTime dataIscrizione;
	private LocalDateTime ultimoAccesso;
	private Map<ObjectId, Utente> amici;
	private boolean emailConfirmed;
	private List<Post> posts;
	private File profilePic;
	private String status;
	private Map<ObjectId, Utente> followers;

	@JsonbCreator
	public Utente(@JsonbProperty("nickname") String nickname, 
			@JsonbProperty("email") String email, 
			@JsonbProperty("password") String password) {
		setNickname(nickname);
		setEmail(email);
		setPassword(password);
		amici = new HashMap<>();
		posts = new ArrayList<>();
		followers = new HashMap<>();
		this.dataIscrizione = LocalDateTime.now();
	}
	public Utente(Document d) {
		setId(d.getObjectId("_id"));
		setNickname(d.getString("nickname"));
		setPassword(d.getString("password"));
		setAmici(d);
		setFollowers(d);
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		if(nickname != null && !nickname.trim().isEmpty())
			this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email != null && !email.trim().isEmpty())
			this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != null && password.trim().length() >= 8)
			this.password = password;
	}
	public LocalDateTime getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(LocalDateTime dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public void setDataIscrizione(Document d) {
		MongoClient mc = new MongoClient();
		setDataIscrizione(LocalDateTime.parse(d.getDate("dataIscrizione").toString()));
	}
	public LocalDateTime getUltimoAccesso() {
		return ultimoAccesso;
	}
	public void setUltimoAccesso(LocalDateTime ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}
	public Map<ObjectId, Utente> getAmici() {
		return amici;
	}
	public List<ObjectId> getDocumentAmici() {
		Document doc = new Document();
		List<ObjectId> oid = new ArrayList<>();
		int i = 0;
		for(ObjectId key : amici.keySet()) {
			oid.add(amici.get(key).getId());			
		}
		doc.append("utenti", oid);
		return oid;
	}
	public void setAmici(Map<ObjectId, Utente> amici) {
		this.amici = amici;
	}
	public void setAmici(Document d) {
		Map<ObjectId, Utente> fr = new HashMap<>();
		List<ObjectId> u = (List<ObjectId>) d.get("utenti");
		MongoClient mc = new MongoClient();
		for(ObjectId id : u) {
			fr.put(id, new Utente(UtenteDao.getUtente(mc, id)));
		}
		setAmici(fr);
	}
	public void addAmico(Utente u) {
		if(u != null) {
			amici.put(u.getId(), u);
		}
	}
	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}
	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public void addPost(Post post) {
		if(post != null) {
			posts.add(0, post);
		}
	}
	public File getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<ObjectId, Utente> getFollowers() {
		return followers;
	}
	public void setFollowers(Map<ObjectId, Utente> followers) {
		this.followers = followers;
	}
	private void setFollowers(Document d) {
		Map<ObjectId, Utente> fr = new HashMap<>();
		List<ObjectId> u = (List<ObjectId>) d.get("utenti");
		MongoClient mc = new MongoClient();
		for(ObjectId id : u) {
			fr.put(id, new Utente(UtenteDao.getUtente(mc, id)));
		}
		setFollowers(fr);
	}
	public void addFollower(Utente f) {
		if(f != null) {
			followers.put(f.getId(), f);
		}
	}
	public Document utenteToDocument() {
		Document d = new Document();
		d.append("nickname", getNickname())
		.append("email", getEmail())
		.append("password", getPassword())
		.append("dataIscrizione", getDataIscrizione())
		.append("emailConfirmed", isEmailConfirmed());
		if(ultimoAccesso != null)
			d.append("ultimoAccesso", getUltimoAccesso());		
		if(profilePic != null)
			d.append("profilePic", getProfilePic());
		if(status != null)
			d.append("status", getStatus());
		if(amici.size() != 0)
			d.append("amici", getDocumentAmici());
		if(followers.size() != 0)
			d.append("followers", getFollowers());
		if(posts.size() != 0)
			d.append("posts", getPosts());
		return d;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Utente))
			return false;
		Utente other = (Utente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}
	
}
