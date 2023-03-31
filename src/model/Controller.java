package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
//
public class Controller {

	private Project[] projects;
	private int counter=0;

	public Controller() {
		projects = new Project[10];
	}
	
	//Incomplete
	public String RegisterProject(String name, String clientName, Calendar initialDate, Calendar finalDate, double budget) {
		String result="Ya no se puede registrar más proyectos";
		if(counter<10){
			projects[counter++]=new Project(name, clientName, initialDate, finalDate, budget);
			result="El proyecto se guardo correctamente ";
		}
		return result;
	}

	//Incomplete
	// Date class also has their own before() and after() method
	public String searchProjectsAfterDate(Calendar after) {

		String msg = "";
		int j=1;
		for (int i = 0; i < counter; i++) {
			if(after.compareTo( projects[i].getInitialDate()  ) < 0){
				msg+="\n"+(j++)+" "+projects[i].getName();
			}
		}
		msg=(msg.equalsIgnoreCase(""))?"No exist project":msg;
		return msg;

	}
	
	//Incomplete
	// Date class also has their own before() and after() method
	public String searchProjectsBeforeDate(Calendar before) {

		String msg = "";
		int j=1;
		for (int i = 0; i < counter; i++) {
			if(before.compareTo( projects[i].getFinalDate()) > 0){
				msg+="\n"+(j++)+" "+projects[i].getName();
			}
		}
		msg=(msg.equalsIgnoreCase(""))?"No exist project":msg;
		return msg;

	}
}
