package com.gianluca993.modelli;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import org.bson.Document;

public class Utente {

	private String id;
	private String nickname, 
	email, 
	password;
	private LocalDateTime dataIscrizione;
	private LocalDateTime ultimoAccesso;
	private Map<String, Utente> amici;
	private boolean emailConfirmed;
	private List<Post> posts;
	private File profilePic;
	private String status;
	private Map<String, Utente> followers;

	@JsonbCreator
	public Utente(@JsonbProperty("nickname") String nickname, 
			@JsonbProperty("email") String email, 
			@JsonbProperty("password") String password) {
		setNickname(nickname);
		setEmail(email);
		setPassword(password);
		amici = new HashMap<>();
		this.dataIscrizione = LocalDateTime.now();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public LocalDateTime getUltimoAccesso() {
		return ultimoAccesso;
	}
	public void setUltimoAccesso(LocalDateTime ultimoAccesso) {
		this.ultimoAccesso = ultimoAccesso;
	}
	public Map<String, Utente> getAmici() {
		return amici;
	}
	public Document getDocumentAmici() {
		Document d = new Document();
		for(String key : amici.keySet()) {
			d.append(key, amici.get(key).utenteToDocument());
		}
		return d;
	}
	public void setAmici(Map<String, Utente> amici) {
		this.amici = amici;
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
	public Map<String, Utente> getFollowers() {
		return followers;
	}
	public void setFollowers(Map<String, Utente> followers) {
		this.followers = followers;
	}
	public void addFollower(Utente f) {
		if(f != null) {
			followers.put(f.getId(), f);
		}
	}
	public Document utenteToDocument() {
		Document d = new Document();
		d.append("id", getId())
		.append("nickname", getNickname())
		.append("email", getEmail())
		.append("password", getPassword())
		.append("amici", getDocumentAmici())
		.append("dataIscrizione", getDataIscrizione())
		.append("ultimoAccesso", getUltimoAccesso())
		.append("emailConfirmed", isEmailConfirmed())
		.append("posts", getPosts())
		.append("profilePic", getProfilePic())
		.append("status", getStatus())
		.append("followers", getFollowers());
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
