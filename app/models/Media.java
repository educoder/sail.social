package models;


import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Media extends Model {
	
	public String url;
	public String mediaTyp;
	public String origin;

	
	public Media(String url, String type, String origin) {
		this.url = url;
		this.mediaTyp = type;
		this.origin = origin;
	}
	
	public static List<Media> findMediaByImage(){
		String mediaTyp = "IMAGE";
		return find("byMediaTyp", mediaTyp).fetch();
	}
	
	public static List<Media> findMediaByVideo(){
		String mediaTyp = "video";
		return find("byMediaType", mediaTyp).fetch();
	}

}
