package com.gianluca993.modelli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Room {

	private ObjectId id;
	private String nome, 
				password;
	private File img;
	private boolean hasPass = false;
	private List<Utente> partecipanti;
	private List<Messaggio> messaggi;
	
	@JsonbCreator
	public Room(@JsonbProperty("nome") String nome) {
		setNome(nome);
		partecipanti = new ArrayList<>();
		messaggi = new ArrayList<>();
	}
	
	@JsonbCreator
	public Room(@JsonbProperty("nome") String nome,
				@JsonbProperty("password") String password) {
		setNome(nome);
		setPassword(password);
		setHasPass(true);
		partecipanti = new ArrayList<>();
		messaggi = new ArrayList<>();
	}
	public Room(Document d) {
		setId(d.getObjectId("_id"));
		setNome(d.getString("nome"));
		if(d.getString("password") != null) {
			setPassword(d.getString("password"));
			setHasPass(true);
		}
		partecipanti = new ArrayList<>();
		messaggi = new ArrayList<>();
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public boolean isHasPass() {
		return hasPass;
	}
	public void setHasPass(boolean hasPass) {
		this.hasPass = hasPass;
	}
	public List<Utente> getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(List<Utente> partecipanti) {
		this.partecipanti = partecipanti;
	}
	public void addPartecipante(Utente partecipante) {
		if(partecipante != null)
			partecipanti.add(partecipante);
	}
	public List<Messaggio> getMessaggi() {
		return messaggi;
	}
	public void setMessaggi(List<Messaggio> messaggi) {
		this.messaggi = messaggi;
	}
	
	public Document roomToDocument() {
		Document d = new Document()
				.append("nome", getNome());
		if(this.password != null)
				d.append("password", getPassword());
		return d;
	}
	
}
