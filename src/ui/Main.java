package ui;

import java.util.Scanner;

import javax.swing.CellEditor;

import model.Controller;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//
public class Main {

	private Scanner reader;
	private Controller controller;
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	/*
	 * En Green al aceptar un proyecto de un cliente se debe registrar el mismo
	 * almacenando
	 * la siguiente información: nombre del proyecto, nombre del cliente, tipo de
	 * proyecto
	 * (puede ser Desarrollo, Mantenimiento, Despliegue), fecha planeada para el
	 * inicio del
	 * proyecto y fecha planeada para la finalización del proyecto, el valor
	 * correspondiente al presupuesto del proyecto.
	 */
	public Main() {

		reader = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {

		Main exe = new Main();
		exe.menu();
		exe.RegisterProject();

	}

	// Incomplete
	public void menu() {

		System.out.println("1.Crear Pryecto\n2.Buscar Proyecto que finalizan antes de una fecha\n3.ConsultarProyectos que inicia despues de una fecha");
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

		do{
		for (int i = 0; i < 2; i++) {
			boolean follow;

			do {
				follow = false;

				int year = 0;
				int month = 0;
				int day = 0;
				String fecha = (i == 0) ? "de inicio" : "Final";
				System.out.println("Ingrese la fecha " + fecha + ": ");
				System.out.print("Ingrese año: ");
				year = validateOption();
				System.out.print("Ingrese el mes: ");
				month = validateOption();
				System.out.print("Ingrese el dia: ");
				day = validateOption();
				date = day + "/" + month + "/" + year;

				if (i == 0) {
					try {
						startDate.setTime(format.parse(date));
						follow = true;
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("Correctamente");
					}
				}
				{
					try {
						endDate.setTime(format.parse(date));
						follow = true;
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("Correctamente");
					}
				}
			} while (!follow);

		}
		}while( startDate.compareTo(endDate)>0);		

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

	}

	// Incomplete
	public void searchProjectsBeforeDate() {

	}
}
