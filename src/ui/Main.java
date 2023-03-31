package ui;

import java.util.Scanner;
import model.Controller;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	private Scanner reader;
	private Controller controller;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	public Main() {
		reader = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {
		double option = 0;
		Main exe = new Main();
		do {
			System.out.println("\nWelcome\n");
			exe.menu();
			do {
				System.out.print("Type: ");
				option = exe.validateDouble();
			} while (option != 1 && option != 2 && option != 3 && option!=0);
			switch ((int) option) {
				case 1:
					exe.RegisterProject();
					break;
				case 2:
					exe.searchProjectsBeforeDate();
					break;
				case 3:
					exe.searchProjectsAfterDate();
					break;
				case 0:
					System.out.println("Exit");
					break;
			}

		} while (option != 0);
	}

	// Incomplete
	public void menu() {

		System.out.println(
				"1. Create Project\n2. Search Projects ending before a date\n3. Search Projects starting after a date\n0.Exit");
	}

	/**
	 * Registers a new project by requesting input from the user for the project name, client name,
	 * project type, start and end dates, and budget. Validates the input and sends
	 * it to the controller for registration.
	 */
	public void RegisterProject() {

		String nameProject = "", nameClient = "", typeProject = "", date = "";
		Calendar endDate = Calendar.getInstance(), startDate = Calendar.getInstance();
		double budget = 0;
		double tipo = 0;

		System.out.println("\3Project Registration\3\n");
		System.out.print("Enter Project Name: ");
		nameProject = reader.next();
		System.out.print("Enter Client Name: ");
		nameClient = reader.next();
		System.out.println("Enter project type: 1. Development, 2. Maintenance, 3. Deployment");
		do {
			System.out.print("Type: ");
			tipo = validateDouble();
		} while (tipo != 1 && tipo != 2 && tipo != 3);
		switch ((int) tipo) {
			case 1 -> typeProject = "Development";
			case 2 -> typeProject = "Maintenance";
			case 3 -> typeProject = "Deployment";
		}

		do {
			System.out.println("\nEnter start date\n ");
			startDate = assingDate();
			System.out.println("\nEnter end date\n ");
			endDate = assingDate();

			if (startDate.compareTo(endDate) > 0) {
				System.out.println("End date cannot be before start date");
			}
		} while (startDate.compareTo(endDate) > 0);
		System.out.print("\nEnter project budget: ");
		budget = validateDouble();

		System.out
				.println(controller.RegisterProject(nameProject, nameClient, startDate, endDate, budget, typeProject));
	}

	/**
	 * This method validates user input to ensure it is a valid double value. It prompts the user to enter
	 * a number and checks if it can be parsed as a double. If the input is not a valid double, it clears
	 * the scanner and prompts the user to enter a valid number until it receives one.
	 * 
	 * @return Finally, it returns the valid double value entered by the user.
	 */
	public double validateDouble() {
		double option = 0;
		do {
			if (reader.hasNextDouble()) {
				option = reader.nextDouble();
			} else {
				reader.next();// clear the scanner
				option = Integer.MAX_VALUE;
				System.out.print("Invalid number, please enter a number: ");
			}
		} while (option == Integer.MAX_VALUE);
		return option;
	}

	/**
	 * This method allows the user to search for projects starting after a given
	 * date. It prompts the user to input a date and calls the controller to retrieve and
	 * return a list of projects that start after that date.
	 */
	public void searchProjectsAfterDate() {
		Calendar after = Calendar.getInstance();
		System.out.println("Search for projects starting after a date");
		after = assingDate();
		System.out.println(controller.searchProjectsAfterDate(after));
	}

	/**
	 * Searches for projects starting before a given date and prints the results.
	 * This information is used by GreenSQA to plan their deadlines and workload.
	 */
	public void searchProjectsBeforeDate() {
		System.out.println("Search for projects starting before a date");
		Calendar after = Calendar.getInstance();
		after = assingDate();
		System.out.println(controller.searchProjectsBeforeDate(after));
	}

	/**
	 * Prompts the user to enter a date in the format "dd/MM/yyyy",
	 * and validates the input to ensure that it is a valid date. Returns
	 * a Calendar object representing the date entered by the user.
	 * 
	 * @return A Calendar object representing the date entered by the user.
	 */
	public Calendar assingDate() {
		Calendar dateCal = Calendar.getInstance();
		boolean follow;
		System.out.println("Date format: dd-MM-yyyy. Example: 22-02-2023");

		do {
			follow = false;
			String date = "";
			System.out.print("Enter date: ");
			date = reader.next();
			try {
				dateCal.setTime(format.parse(date));
				follow = true;
			} catch (ParseException e) {
				//e.printStackTrace();
				System.out.println("Please enter date correctly");
			}
		} while (!follow);

		return dateCal;
	}

}
