/*
 * This class will contain a main method that provides a menu with many options to interact with the program
 * and update the store inventory information
 * @author Tao Yumiao R32314004
 */
//package com.tao;

import java.util.Scanner;

public class DepartmentStore {
	private static ItemList itemList = new ItemList();
	
	public static void main(String[] args) {
		//print menu
		System.out.println("Welcome!");
		System.out.println("\n");
		System.out.println("C - Clean store ");
		System.out.println("I - Insert an item into the list ");
		System.out.println("L - List by location ");
		System.out.println("M - Move an item in the store ");
		System.out.println("O - Checkout  ");
		System.out.println("P - Print all items in store");
		System.out.println("R - Print by RFID tag number");
		System.out.println("U - Update inventory system");
		System.out.println("Q - Exit the program.");
		
		Scanner scanner = new Scanner(System.in);
		String option;
		
		//loop to let user enter constantly, stop when user enters "Q"
		do{
			System.out.print("Please select an option:");
			option=scanner.nextLine();
			option=option.toUpperCase();
			
			switch(option) {
				case "C" :
					System.out.println("The following item(s) have been moved back to their original locations:");
					System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
	                System.out.println("------------------------------------------------------------");
					itemList.cleanStore();
					break;
				case "I" :
					insertItem(scanner);
					break;
				case "L" :
					listByLocation(scanner);
					break;
				case "M" :
					moveItem(scanner);
					break;
				case "O" :
					checkout(scanner);
					break;
				case "P" :
					itemList.printAll();
					break;
				case "R" :
					printItemsByRfid(scanner);
				    break;
				case "U" :
					itemList.removeAllPurchased();
					break;
				case "Q" :
					System.out.println("Goodbye!");
					return;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
					
			}
		}while(option !="Q");
		
		 scanner.close();
	}
	
/*
 * This method inserts an item into the list.
 * 
 * @param scanner
 * a scanner for reading input
 * We do not create the scanner repeatedly to prevent mutual interference.

 */
	 private static void insertItem(Scanner scanner) {
		 //ask for information about a new item
	        System.out.print("Enter the name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter the RFID: ");
	        String rfid = scanner.nextLine();

	        System.out.print("Enter the original location: ");
	        String originalLocation = scanner.nextLine();

	        System.out.print("Enter the price: ");
	        double price = scanner.nextDouble();
	        scanner.nextLine();  

	        try {//test whether the input is valid
	            itemList.insertInfo(name, rfid, price, originalLocation);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	 /*
	  * This method list items by location
	  * 
	  * @param scanner
	  * a scanner for reading input
	  */
	 private static void listByLocation(Scanner scanner) {
	        System.out.print("Enter the location: ");
	        String location = scanner.nextLine();
	        itemList.printByLocation(location);
	    }
	 
	 /*
	  * This method moves item to target location
	  * 
	  * @param scanner
	  * a scanner for reading input
	  */
	 private static void moveItem(Scanner scanner) {
	        System.out.print("Enter the RFID: ");
	        String rfid = scanner.nextLine();

	        System.out.print("Enter the source location: ");
	        String source = scanner.nextLine();

	        System.out.print("Enter the destination location: ");
	        String destination = scanner.nextLine();

	        try {
	            if (itemList.moveItem(rfid, source, destination)) {
	                System.out.println("Item moved successfully.");
	            } else {
	                System.out.println("Item not found at the given source location.");
	            }
	        } catch (IllegalArgumentException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	 
	 /*
	  * This method check out items
	  * 
	  * @param scanner
	  * a scanner for reading input
	  */
	 private static void checkout(Scanner scanner) {
	        System.out.print("Enter the cart number: ");
	        String cartNumber = scanner.nextLine();
            System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
            System.out.println("------------------------------------------------------------");
	        try {
	            double totalCost = itemList.checkOut(cartNumber);
	            System.out.printf("The total cost for all merchandise in cart %s was $%.2f\n", cartNumber, totalCost);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	 
	 private static void printItemsByRfid(Scanner scanner) {
		    System.out.print("Enter the RFID tag number: ");
		    String rfid = scanner.nextLine();
		    itemList.printByRfid(rfid);

		}


}
