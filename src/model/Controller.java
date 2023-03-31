package model;

import java.util.Calendar;

public class Controller {

	private Project[] projects;
	private int counter = 0;

	public Controller() {
		projects = new Project[10];
	}

	/**
	Registers a new project in the system with the given details.
	@param name the name of the project
	@param clientName the name of the client for the project
	@param initialDate the initial date for the project
	@param finalDate the final date for the project
	@param budget the budget for the project
	@param typeProject the type of project
	@return a message indicating whether the project was successfully registered or not
	*/
	public String RegisterProject(String name, String clientName, Calendar initialDate, Calendar finalDate,
			double budget, String typeProject) {
		String result = "Ya no se puede registrar más proyectos";
		if (counter < 10) {
			projects[counter++] = new Project(name, clientName, initialDate, finalDate, budget, typeProject);
			result = "El proyecto se guardo correctamente ";
		}
		return result;
	}

	/** Searches for projects starting after a specified date.
	@param after the date to search after
	@return a String with the names of the projects starting after the specified date, 
	or "No exist project" if there are no such projects
	*/
	public String searchProjectsAfterDate(Calendar after) {

		String msg = "Projects: ";

		int j=1;
		for (int i = 0; i < counter; i++) {
			if(after.compareTo( projects[i].getInitialDate()  ) < 0){
				msg+="\n"+(j++)+"."+projects[i].getName();
			
		}
	}
		msg=(msg.equalsIgnoreCase("Projects: "))?"No exist project":msg;

		return msg;

	}

	/**
	 * 
	 * Searches for projects that end before a given date and returns a message with
	 * the names of the matching projects.
	 * If no matching projects are found, returns a message indicating that no
	 * projects were found.
	 * 
	 * @param before The date before which to search for projects.
	 * @return A message with the names of the matching projects or a message
	 *         indicating that no projects were found.
	 */

	public String searchProjectsBeforeDate(Calendar before) {

		String msg = "Projects: ";
		int j = 1;
		for (int i = 0; i < counter; i++) {
			if (before.compareTo(projects[i].getFinalDate()) > 0) {
				msg += "\n" + (j++) + "." + projects[i].getName();
			}
		}
		msg = (msg.equalsIgnoreCase("Projects: ")) ? "No exist project" : msg;
		return msg;
	}

}
