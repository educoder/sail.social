package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	 List<Profile> firstProfile = Profile.findAll();
         List<Task> allTasks = Task.findAll();
         render(firstProfile, allTasks);
    }

}