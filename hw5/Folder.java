//package com.tao;

/**
 * This class represents an email folder and will handle all of the logic for adding and removing emails.
 * 
 * @author Tao Yumiao
 */


import java.io.Serializable;
import java.util.*;

public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String name;  // folder name
    private List<Email> emails;  // a list of emails 
    private String currentSortingMethod; 

    // constructor
    public Folder(String name) {
        this.name = name;
        this.emails = new ArrayList<>();
        this.currentSortingMethod = "Timestamp (Descending)";  // default sorting method
    }

    /**
     * mutator and accessor methods for each instance variable
     */
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}

	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}



	/**
	 * add the email to current folder according to current sorting method
	 * @param email
	 * the email to add
	 */
    public void addEmail(Email email) {
        if (email == null) {
            System.out.println("Cannot add a null email.");
            return;
        }

        // add according to current sorting method
        if (currentSortingMethod.equals("Timestamp (Descending)")) {
            insertEmailByTimestampDescending(email);
        } else if (currentSortingMethod.equals("Timestamp (Ascending)")) {
            insertEmailByTimestampAscending(email);
        } else if (currentSortingMethod.equals("Subject (Ascending)")) {
            insertEmailBySubjectAscending(email);
        } else if (currentSortingMethod.equals("Subject (Descending)")) {
            insertEmailBySubjectDescending(email);
        } else {
            System.out.println("Unknown sorting method. Defaulting to Timestamp (Descending).");
            insertEmailByTimestampDescending(email);  // default
        }
    }

    /**
     * Insert in descending order of timestamp
     * @param email
     * the email to add
     */
    private void insertEmailByTimestampDescending(Email email) {
        int index = 0;
        while (index < emails.size() && emails.get(index).getTimestamp().compareTo(email.getTimestamp()) > 0) {
            index++;// loop to find position
        }
        emails.add(index, email);
    }

    /**
     * Insert in ascending order of timestamp
     * @param email
     * the email to add
     */
    private void insertEmailByTimestampAscending(Email email) {
        int index = 0;
        while (index < emails.size() && emails.get(index).getTimestamp().compareTo(email.getTimestamp()) < 0) {
            index++;
        }
        emails.add(index, email);
    }

    /**
     * Insert in descending order of subject
     * @param email
     * the email to add
     */
    private void insertEmailBySubjectAscending(Email email) {
        int index = 0;
        while (index < emails.size() && emails.get(index).getSubject().compareToIgnoreCase(email.getSubject()) < 0) {
            index++;
        }
        emails.add(index, email);
    }

    /**
     * Insert in ascending order of timestamp
     * @param email
     * the email to add
     */
    private void insertEmailBySubjectDescending(Email email) {
        int index = 0;
        while (index < emails.size() && emails.get(index).getSubject().compareToIgnoreCase(email.getSubject()) > 0) {
            index++;
        }
        emails.add(index, email);
    }

    /**
     * remove specified email
     * @param email
     * the email to remove
     */
    public void removeEmail(Email email) {
        emails.remove(email);
    }

    /**
     * remove email according to index
     * @param index
     * the index of email to remove
     */
    public void removeEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            emails.remove(index);
        } else {
            System.out.println("Invalid email index.");
        }
    }

    /**
     * remove all emails in this folder
     */
    public void clearEmails() {
        emails.clear();
    }

    /**
     * display all emails in this folder
     */
    public void displayEmails() {
        if (emails.isEmpty()) {
            System.out.println("No emails in this folde.");
            return;
        }

        for (int i = 0; i < emails.size(); i++) {
            System.out.println(emails.get(i).toString());
        }
    }

    /**
     * sort by subject ascending
     */
    public void sortBySubjectAscending() {
        emails.sort(Comparator.comparing(Email::getSubject));
        currentSortingMethod = "Subject (Ascending)";
        System.out.println("Emails sorted by subject ascending.");
    }

    /**
     * sort by subject descending
     */
    public void sortBySubjectDescending() {
        emails.sort((e1, e2) -> e2.getSubject().compareTo(e1.getSubject()));
        currentSortingMethod = "Subject (Descending)";
        System.out.println("Emails sorted by subject descending.");
    }

    /**
     * sort by timestamp ascending
     */
    public void sortByTimestampAscending() {
        emails.sort(Comparator.comparing(Email::getTimestamp));
        currentSortingMethod = "Timestamp (Ascending)";
        System.out.println("Emails sorted by timestamp ascending.");
    }

    /**
     * sort by timestamp descending
     */
    public void sortByTimestampDescending() {
        emails.sort((e1, e2) -> e2.getTimestamp().compareTo(e1.getTimestamp()));
        currentSortingMethod = "Timestamp (Descending)";
        System.out.println("Emails sorted by timestamp descending.");
    }
}
