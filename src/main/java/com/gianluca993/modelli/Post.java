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

public class Post implements Comparable<Post>{

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
		data = LocalDateTime.now();
	}
	
	@JsonbCreator
	public Post(@JsonbProperty("op") ObjectId op, 
			@JsonbProperty("title") String title, 
			@JsonbProperty("body") String body, 
			@JsonbProperty("img") File img,
			@JsonbProperty("data") LocalDateTime data){
		setOp(op);
		setTitle(title);
		setBody(body);
		setImg(img);
		setData(data);
	}
	
	public Post(Utente op, String title, String body) {
		setOp(op);
		setTitle(title);
		setBody(body);
		data = LocalDateTime.now();
	}
	
	public Post(Document d) {
		setId(d.getObjectId("_id"));
		setOp(d.getObjectId("op"));
		setTitle(d.getString("title"));
		setBody(d.getString("body"));
		setOp(op);
		setTitle(title);
		setBody(body);
		data = LocalDateTime.now();
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
		setOp(new Utente(d));
		
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

	public Document postToDocument() {
		Document d = new Document()
				.append("op", getOp().getId())
				.append("title", getTitle())
				.append("body", getBody());
		return d;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Post))
			return false;
		Post other = (Post) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int compareTo(Post o) {
		return this.getData().compareTo(o.getData());
	}
	
	
}
