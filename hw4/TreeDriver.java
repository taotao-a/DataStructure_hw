//package com.tao;

/**
 * This class contains the main() method and it should display a menu
 * 
 * @author Tao Yumiao R32314004
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeDriver {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);

        while (true) { // loop to print the menu
            printMenu();
            char choice = scanner.next().toUpperCase().charAt(0);

            switch (choice) {
                case 'L': 
                    loadTree(tree, scanner);
                    break;
                case 'H':
                    startHelpSession(tree);
                    break;
                case 'T':
                    traverseTree(tree);
                    break;
                case 'Q': 
                    System.out.println("Thank you for using our automated help services!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    /**
     * This method prints the main menu.
     */
    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("L - Load a Tree");
        System.out.println("H - Start Help Session");
        System.out.println("T - Traverse the Tree in Preorder");
        System.out.println("Q - Quit");
        System.out.print("Choice> ");
    }

    /**
     * Load input file and build a tree
     * @param tree
     * the tree to be built
     * @param scanner
     * read input
     */
    private static void loadTree(Tree tree, Scanner scanner) {
        System.out.print("Enter the file name> ");
        String fileName = scanner.next();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            //create root node
            String rootLabel = fileScanner.nextLine().trim();
            String rootPrompt = fileScanner.nextLine().trim();
            String rootMessage = fileScanner.nextLine().trim();
            tree.addNode(rootLabel, rootPrompt, rootMessage, null);

            //add other nodes
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim(); //read the message of parentNode
                if (line.matches("^.+\\s+\\d+$")) { // match the format of "label + blank + number"
                    String[] parts = line.split("\\s+");
                    String parentLabel = parts[0]; //label of parentNode
                    int numChildren = Integer.parseInt(parts[1]); //the number of child

                    // read chilNode according to the number of child 
                    for (int i = 0; i < numChildren; i++) {
                        if (!fileScanner.hasNextLine()) break;
                        String label = fileScanner.nextLine().trim();
                        String prompt = fileScanner.nextLine().trim();
                        String message = fileScanner.nextLine().trim();

                        boolean success = tree.addNode(label, prompt, message, parentLabel);
                        if (!success) {
                            System.out.println("Error adding node: " + label);
                        }
                    }
                }
            }

            System.out.println("Tree loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file path.");
        } catch (Exception e) {
            System.out.println("Error loading tree: " + e.getMessage());
        }
    }

    private static void startHelpSession(Tree tree) {
        if (tree == null || tree.getRoot() == null) {
            System.out.println("The tree is not initialized. Please load a tree first.");
            return;
        }

        tree.beginSession();
    }

    private static void traverseTree(Tree tree) {
        if (tree == null) {
            System.out.println("The tree object is null.");
            return;
        }

        tree.preOrder();
    }


}
