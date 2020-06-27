package com.gianluca993.modelli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Room {

	private String id;
	private String nome, 
				password;
	private File img;
	private boolean hasPass;
	private List<Utente> partecipanti;
	private List<Messaggio> messaggi;
	
	public Room(String nome) {
		this.nome = nome;
		hasPass = false;
		partecipanti = new ArrayList<>();
		messaggi = new ArrayList<>();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	
	
}
