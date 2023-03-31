package ui;

import java.util.Scanner;
import model.Controller;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Main {

	private Scanner reader;
	private Controller controller;
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public Main() {
		reader = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {
		int option=0;
		Main exe = new Main();
		do{

		
		exe.menu();
		option=exe.validateOption();

		switch(option){
			case 1->exe.RegisterProject();
			case 2->exe.searchProjectsBeforeDate();
			case 3->exe.searchProjectsAfterDate();
		}
		
		}while(option!=0);
	}

	// Incomplete
	public void menu() {

		System.out.println(
				"1.Crear Pryecto\n2.Buscar Proyecto que finalizan antes de una fecha\n3.ConsultarProyectos que inicia despues de una fecha");
	}

	// Incomplete
	/**
	 * 
	 */
	public void RegisterProject() {

		String nameProject = "", nameClient = "", typeProject = "", date = "";
		Calendar endDate = Calendar.getInstance(), startDate = Calendar.getInstance();
		double budget = 0;
		int tipo = 0;

		System.out.println("\3Registro del proyecto\3\n");
		System.out.print("Ingrese el nombre del proyecto: ");
		nameProject = reader.next();
		System.out.print("Ingrese el nombre del cliente: ");
		nameClient = reader.next();
		System.out.println("Ingrese tipo del proyecto: 1. Desarrollo, 2.Mantenimiento, 3.Despliegue");
		do {
			System.out.print("Type: ");
			tipo = validateOption();
		} while (tipo != 1 && tipo != 2 && tipo != 3);

		switch (tipo) {
			case 1 -> typeProject = "Desarrollo";
			case 2 -> typeProject = "Mantenimiento";
			case 3 -> typeProject = "Despliegue";
		}

		do {
			System.out.println("Ingrese la fecha de inicio: ");
			startDate=assingDate();
			System.out.println("Ingrese la fecha final: ");
			endDate=assingDate();

			if(startDate.compareTo(endDate) > 0){
				System.out.println("La fecha final no puede ir antes que la inicial");
			}
		} while (startDate.compareTo(endDate) > 0);
		System.out.print("\nIngrese cuanto el presupuesto del proyecto: ");
		budget = validateDouble();

		System.out.println(controller.RegisterProject(nameProject, nameClient, startDate, endDate, budget));

	}

	public double validateDouble() {
		double option = 0;
		do {
			if (reader.hasNextDouble()) {
				option = reader.nextDouble();
			} else {
				reader.next();// limpiar el scanner
				option = Integer.MAX_VALUE;
				System.out.print("Invalid number, type a number: ");
			}
		} while (option == Integer.MAX_VALUE);
		return option;
	}

	public int validateOption() {
		int option = 0;
		do {
			if (reader.hasNextInt()) {
				option = reader.nextInt();
			} else {
				reader.next();
				System.out.print("Invalid number! Type number: ");
				option = Integer.MAX_VALUE;
			}
		} while (option == Integer.MAX_VALUE);
		return option;
	}

	// Incomplete
	public void searchProjectsAfterDate() {
		Calendar after = Calendar.getInstance();
		System.out.println("Buscar projectos que inicia depues de una fecha");
		after=assingDate();
		System.out.println( controller.searchProjectsAfterDate(after) );
	}

	// Incomplete
	public void searchProjectsBeforeDate() {
		System.out.println("Buscar proyecto antes de la finalizacion de la fecha ");
		Calendar after = Calendar.getInstance();
		after=assingDate();
		System.out.println( controller.searchProjectsAfterDate(after) );
	}

	public Calendar assingDate(){
		Calendar date1=Calendar.getInstance();
		boolean follow;
		do {
			follow = false;
			String date = "";
			int year = 0;
			int month = 0;
			int day = 0;
			System.out.println("Ingrese la fecha : ");
			System.out.print("Ingrese año: ");
			year = validateOption();
			System.out.print("Ingrese el mes: ");
			month = validateOption();
			System.out.print("Ingrese el dia: ");
			day = validateOption();
			date = day + "/" + month + "/" + year;
			try {
				date1.setTime(format.parse(date));
				follow = true;
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("Correctamente");
			}
		} while (!follow);
		
		return date1;
	}

}
