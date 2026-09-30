//package com.tao;
/**
 * This class is to perform simulation of 
 * a building's elevator system that processes passenger requests.
 * 
 * @author Tao Yumiao R32314004
 */

/**
 * This class contains a main method which prompts the user, on separate lines,
 * for each of the 4 parameters required for the simulate method of the Simulator class.
 */

import java.util.Scanner;

public class Analyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Elevator simulator!");
       
        //Prompt the user to input four parameters and verify the legality.
        System.out.print("Please enter the probability of arrival for Requests:");
        double requestProbability = scanner.nextDouble();
        scanner.nextLine();
        while (requestProbability < 0.0 || requestProbability > 1.0) {
            System.out.println("Invalid input. Please enter a value between 0.0 and 1.0. Enter again:");
            requestProbability = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("Please enter the number of floors: ");
        int numberOfFloors = scanner.nextInt();
        scanner.nextLine();
        while (numberOfFloors <= 1) {
            System.out.println("Invalid input. The number of floors must be greater than 1. Enter again:");
            numberOfFloors = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Please enter the number of elevators: ");
        int numberOfElevators = scanner.nextInt();
        scanner.nextLine();
        while (numberOfElevators <= 0) {
            System.out.println("Invalid input. The number of elevators must be greater than 0. Enter again:");
            numberOfElevators = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Please enter the length of the simulation (in time units): ");
        int simulationLength = scanner.nextInt();
        scanner.nextLine();
        while (simulationLength <= 0) {
            System.out.println("Invalid input. The simulation length must be greater than 0. Enter again:");
            simulationLength = scanner.nextInt();
            scanner.nextLine();
        }

        //Pass four parameters to method
        Simulator start = new Simulator();
        start.simulate(requestProbability, numberOfFloors, numberOfElevators, simulationLength);
        
        int totalWaitTime=start.getTotalWaitTime();
        int totalRequests=start.getTotalRequests();
        
        //calculate averageWaitTime
        double averageWaitTime=0;
        if (totalRequests > 0) {
            averageWaitTime = (double) totalWaitTime / totalRequests;
        } else {
            System.out.println("No requests were processed.");
        }
        
        //print the results
        System.out.println("Total Wait Time:"+totalWaitTime);
        System.out.println("Total Requests:"+totalRequests);
        System.out.printf("Average Wait Time: %.2f units.\n", averageWaitTime);
    }
}
