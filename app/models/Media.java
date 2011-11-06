package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Media extends Model {
	
	public String url;
	public String type;
	public String origin;

	
	public Media(String url, String type, String origin) {
		this.url = url;
		this.type = type;
		this.origin = origin;
	}

}
