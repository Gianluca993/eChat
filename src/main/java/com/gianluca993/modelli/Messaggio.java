package com.gianluca993.modelli;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Messaggio {

	private String id;
	private Utente mittente;
	private String testo;
	private LocalDateTime dataInvio;
	
	public Messaggio(Utente mittente, String testo) {
		setMittente(mittente);
		setTesto(testo);
		dataInvio = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Utente getMittente() {
		return mittente;
	}

	public void setMittente(Utente mittente) {
		this.mittente = mittente;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(LocalDateTime dataInvio) {
		this.dataInvio = dataInvio;
	}
	
}
