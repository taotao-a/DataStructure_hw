//package com.tao;

/**
 * This class represents an email box and it will contain all of the folders and the remaining logic. 
 * 
 * @author Tao Yumiao
 */

import java.io.*;
import java.util.*;

public class Mailbox implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Folder inbox;  
    private Folder trash;  
    private ArrayList<Folder> folders;  // save new folder
    public static Mailbox mailbox;  
    
    /**
     * constructor
     */
    public Mailbox() {
        this.inbox = new Folder("Inbox");
        this.trash = new Folder("Trash");
        this.folders = new ArrayList<>();
    }

    /**
     * initialize the mailbox, load previous file if exists
     */
    public static void initializeMailbox() {
        File file = new File("mailbox.obj");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                mailbox = (Mailbox) ois.readObject();
                System.out.println("Mailbox loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to load mailbox. Starting with an empty mailbox.");
                mailbox = new Mailbox();
            }
        } else {
            mailbox = new Mailbox();
            System.out.println("Previous save not found, starting with an empty mailbox.");
        }
    }

    /**
     * save file 
     */
    public static void saveMailbox() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mailbox.obj"))) {
            oos.writeObject(mailbox);
        } catch (IOException e) {
            System.out.println("Failed to save mailbox.");
        }
    }
    
    /**
     * print all foder name
     */
    public void printFolders() {
        System.out.println("\nMailbox:");
        System.out.println("--------");
        System.out.println("Inbox");
        System.out.println("Trash");
        for (Folder folder : folders) {
            System.out.println(folder.getName());
        }
    }

    /**
     * add folder to mailbox
     * @param folder
     * folder to add
     */
    public void addFolder(Folder folder) {
        for (Folder f : folders) {
            if (f.getName().equals(folder.getName())) {
                System.out.println("Folder with that name already exists.");
                return;
            }
        }
        folders.add(folder);
    }

    /**
     * delete folder from mailbox
     * @param folder
     * folder to delete
     */
    public void deleteFolder(String name) {
        if (name.equals("Inbox") || name.equals("Trash")) {
            System.out.println("Cannot delete Inbox or Trash.");
            return;
        }
        Folder folderToDelete = null;
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                folderToDelete = folder;
                break;
            }
        }

        if (folderToDelete != null) {
            folders.remove(folderToDelete);
            System.out.println(name + " deleted.");
        } else {
            System.out.println("Folder not found.");
        }
    }

    /**
     * create a new email and add it to inbox
     */
    public void composeEmail() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipient (To): ");
        String to = scanner.nextLine();
        System.out.print("Enter carbon copy recipients (CC):");
        String cc = scanner.nextLine();
        System.out.print("Enter blind carbon copy recipients (BCC):");
        String bcc = scanner.nextLine();
        System.out.print("Enter subject line:");
        String subject = scanner.nextLine();
        System.out.print("Enter Body: ");
        String body = scanner.nextLine();

        Email newEmail = new Email(to, cc, bcc, subject, body);
        inbox.addEmail(newEmail);
        System.out.println("Email successfully added to Inbox.");
    }

    /**
     * delete email-move to trash
     * @param email
     */
    public void deleteEmail(Email email) {
        inbox.removeEmail(email);
        trash.addEmail(email);
        System.out.println(email.getSubject()+" has successfully been moved to the trash.");
    }

    /**
     * delete all emails in trash
     */
    public void clearTrash() {
        int numberOfEmails = trash.getEmails().size();  
        trash.clearEmails();  

        System.out.println(numberOfEmails + " item(s) successfully deleted.");
    }

    /**
     * move email of specific index
     * @param email
     * email to move
     * @param target
     * index of email to move
     */
    public void moveEmail(Email email, Folder target) {
        if (target == null) {
            System.out.println("Target folder not found. Moving to Inbox.");
            target = inbox;  
        }

        target.addEmail(email);  
        inbox.removeEmail(email); 
        System.out.println("Email moved to " + target.getName());
    }

    /**
     * get a folder according to its name
     * @param name
     * name of the folder to get
     * @return
     * folder to get
     */
    public Folder getFolder(String name) {
        if (name.equals("Inbox")) {
            return inbox;
        } else if (name.equals("Trash")) {
            return trash;
        }

        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return folder;
            }
        }
        return null;
    }

    //main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mailbox.initializeMailbox(); //initialize mailbox, load previous data
        mailbox.printFolders();

     // loop main menu
        while (true) {
            System.out.println("\nMailbox Menu:");
            System.out.println("A - Add folder");
            System.out.println("R - Remove folder");
            System.out.println("C - Compose email");
            System.out.println("F - View Folder");
            System.out.println("I - View Inbox");
            System.out.println("T - View Trash");
            System.out.println("E - Empty Trash");
            System.out.println("Q - Quit");

            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    System.out.print("Enter folder name: ");
                    String folderName = scanner.nextLine();
                    Folder newFolder = new Folder(folderName);
                    Mailbox.mailbox.addFolder(newFolder);
                    mailbox.printFolders();
                    break;

                case "R":
                    System.out.print("Enter folder name to remove: ");
                    folderName = scanner.nextLine();
                    Mailbox.mailbox.deleteFolder(folderName);
                    mailbox.printFolders();
                    break;

                case "C":
                	Mailbox.mailbox.composeEmail();
                    mailbox.printFolders();
                    break;

                case "F":
                    System.out.print("Enter folder name: ");
                    folderName = scanner.nextLine();
                    Folder folder = mailbox.getFolder(folderName);
                    if (folder != null) {
                        openFolderSubMenu(folder);
                    } else {
                        System.out.println("Folder not found.");
                    }
                    break;
                    
                case "I":
                    openFolderSubMenu(Mailbox.mailbox.inbox);  
                    break;

                case "T":
                    openFolderSubMenu(Mailbox.mailbox.trash);  
                    break;
                    
                case "E":
                    mailbox.clearTrash();
                    mailbox.printFolders();
                    break;

                case "Q":
                	Mailbox.saveMailbox();
                    System.out.println("Program successfully exited and mailbox saved.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * open sub menu of folder
     * @param folder
     * the folder to check
     */
    public static void openFolderSubMenu(Folder folder) {
        Scanner scanner = new Scanner(System.in);
        
        printFolderContents(folder);

        //loop sub menu
        while (true) {
             System.out.println("M – Move email");
             System.out.println("D – Delete email");
             System.out.println("V – View email contents");
             System.out.println("SA – Sort by subject line in ascending order");
             System.out.println("SD – Sort by subject line in descending order");
             System.out.println("DA – Sort by date in ascending order");
             System.out.println("DD – Sort by date in descending order");
             System.out.println("R – Return to mailbox");

             System.out.print("Enter a user option: ");
             String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "M":
                    moveEmailInFolder(folder);
                    break;

                case "D":
                    deleteEmailInFolder(folder);
                    break;

                case "V":
                    viewEmailInFolder(folder);
                    break;

                case "SA":
                    folder.sortBySubjectAscending();
                    printFolderContents(folder);
                    break;

                case "SD":
                    folder.sortBySubjectDescending();
                    printFolderContents(folder);
                    break;

                case "DA":
                    folder.sortByTimestampAscending();
                    printFolderContents(folder);
                    break;

                case "DD":
                    folder.sortByTimestampDescending();
                    printFolderContents(folder);
                    break;

                case "R":
                	mailbox.printFolders();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * move email in folder
     * @param folder
     * The folder where the email want to move is located
     */
    public static void moveEmailInFolder(Folder folder) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email index to move: ");
        int emailIndex = scanner.nextInt()-1;
        scanner.nextLine();  

        if (emailIndex >= 0 && emailIndex < folder.getEmails().size()) {
            Email email = folder.getEmails().get(emailIndex);

            System.out.println("Folders:");
            System.out.println("Inbox");
            System.out.println("Trash");
            for (Folder f : Mailbox.mailbox.folders) {
                System.out.println(f.getName());  
            }

            System.out.print("Select a folder to move \"" + email.getSubject() + "\" to: ");
            String targetFolderName = scanner.nextLine(); 

            Folder targetFolder = null;

            // match the name of folder
            if (targetFolderName.equalsIgnoreCase("Inbox")) {
                targetFolder = Mailbox.mailbox.inbox;
            } else if (targetFolderName.equalsIgnoreCase("Trash")) {
                targetFolder = Mailbox.mailbox.trash;
            } else {
                for (Folder f : Mailbox.mailbox.folders) {
                    if (f.getName().equalsIgnoreCase(targetFolderName)) {
                        targetFolder = f;
                        break;
                    }
                }
            }

            if (targetFolder != null) {
                folder.removeEmail(emailIndex);  // remove from previous folder
                targetFolder.addEmail(email);  // add to target folder
                System.out.println("\"" + email.getSubject() + "\" successfully moved to " + targetFolder.getName() + ".");
                
             // Print the latest contents of the original folder
                System.out.println("\n" + folder.getName());
                if (folder.getEmails().isEmpty()) {
                    System.out.println(folder.getName()+" is empty.");
                } else {
                    for (int i = 0; i < folder.getEmails().size(); i++) {
                        Email e = folder.getEmails().get(i);
                        System.out.println((i + 1) + "   |  " + e.getTimestamp() + " | " + e.getSubject());
                        System.out.println("\n");
                    }
                }
            } else {
                System.out.println("Invalid folder name. Operation canceled.");
            }
        } else {
            System.out.println("Invalid email index.");
        }
    }

    /**
     * remove email from folder
     * @param folder
     * The folder where the email want to remove is located
     */
    public static void deleteEmailInFolder(Folder folder) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email index: ");
        int emailIndex = scanner.nextInt()-1;
        scanner.nextLine();  // Consume newline

        if (emailIndex >= 0 && emailIndex < folder.getEmails().size()) {
        	Email email = folder.getEmails().get(emailIndex);

            // remove from previous folder
            folder.removeEmail(emailIndex);

            // move to trash
            Mailbox.mailbox.trash.addEmail(email);

            System.out.println("\"" + email.getSubject() + "\" has successfully been moved to the trash.");

            
            // Print the latest contents of the original folder
            System.out.println("\n" + folder.getName());
            if (folder.getEmails().isEmpty()) {
                System.out.println(folder.getName()+"is empty.");
            } else {
                for (int i = 0; i < folder.getEmails().size(); i++) {
                    Email e = folder.getEmails().get(i);
                    System.out.println((i + 1) + "   |  " + e.getTimestamp() + " | " + e.getSubject());
                }
            }
        } else {
            System.out.println("Invalid email index.");
        }
    }

    /**
     * check the content of the folder
     * @param folder
     * the folder to check
     */
    public static void viewEmailInFolder(Folder folder) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email index: ");
        int emailIndex = scanner.nextInt()-1;
        scanner.nextLine();  // Consume newline

        if (emailIndex >= 0 && emailIndex < folder.getEmails().size()) {
            Email email = folder.getEmails().get(emailIndex);
            email.displayEmail();
        } else {
            System.out.println("Invalid email index.");
        }
    }
    
    /**
     * A common way to print folder contents
     * @param folder
     * the folder to print
     */
    public static void printFolderContents(Folder folder) {
        System.out.println("\n" + folder.getName());
        System.out.println("Index |        Time       | Subject");
        System.out.println("-----------------------------------");

        // print all emials
        if (folder.getEmails().isEmpty()) {
            System.out.println("No emails in this folder.");
        } else {
            for (int i = 0; i < folder.getEmails().size(); i++) {
                Email email = folder.getEmails().get(i);
                System.out.println((i + 1) + "   |  " + email.getTimestamp() + " | " + email.getSubject());
            }
        }
    }

}
