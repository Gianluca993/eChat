package com.gianluca993.modelli;

import java.io.File;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;

import dao.MongoConnection;
import dao.UtenteDao;

public class Post {

	private ObjectId id;
	private Utente op;
	private String title;
	private String body;
	private File img;
	private LocalDateTime data;
	
	@JsonbCreator
	public Post(@JsonbProperty("op") ObjectId op, 
			@JsonbProperty("title") String title, 
			@JsonbProperty("body") String body, 
			@JsonbProperty("img") File img) {
		setOp(op);
		setTitle(title);
		setBody(body);
		setImg(img);
	}
	
	public Post(Document d) {
		setOp(d.getObjectId("op"));
		setTitle(d.getString("title"));
		setBody(d.getString("body"));
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getId() {
		return id;
	}

	public void setOp(ObjectId id) {
		MongoClient mc = MongoConnection.connetti();
		Document d = UtenteDao.getUtente(mc, id);
		this.op = new Utente(d);
		
	}
	public void setOp(Utente op) {
		this.op = op;
	}

	public Utente getOp() {
		return op;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
