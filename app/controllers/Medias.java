package controllers;

import models.Media;

public class Medias extends CRUD {

	
	public static void create(String url, String type, String origin){
		
		new Media(url,type,origin).save();
		
		//Application.index();
		
	}
}
