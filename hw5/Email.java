//package com.tao;

/**
 * This class will contain detailed information about each email.
 * 
 * @author Tao Yumiao
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Email implements Serializable {//to be able to save Email objects to a file
    private static final long serialVersionUID = 1L;

    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp; // time mark

    /**
     * constructor
     */
    public Email(String to, String cc, String bcc, String subject, String body) {
        if (to == null || to.isEmpty()) {//no initialization or the initialization content is empty
            System.out.println("Error: 'To' field cannot be empty.");
            this.to = "(Unknown)";
        } else {
            this.to = to;
        }

        if (cc == null) {
            this.cc = ""; // if not provided, set to null
        } else {
            this.cc = cc;
        }

        if (bcc == null) {
            this.bcc = ""; 
        } else {
            this.bcc = bcc;
        }

        if (subject == null || subject.isEmpty()) {
            this.subject = "(No Subject)"; 
        } else {
            this.subject = subject;
        }

        if (body == null) {
            this.body = "(No Content)"; 
        } else {
            this.body = body;
        }

        this.timestamp = new GregorianCalendar(); // current time
    }

    
    /**
     * mutator and accessor methods for each instance variable
     */
    public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp.getTime());  // formatted time
    }

	public void setTimestamp(GregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}



	/**
	 * display the content of an email 
	 */
    public void displayEmail() {
        System.out.println("To: " + to);
        if (!cc.isEmpty()) {
            System.out.println("CC: " + cc);
        }
        if (!bcc.isEmpty()) {
            System.out.println("BCC: " + bcc);
        }
        System.out.println("Subject: " + subject);
        System.out.println("\n" + body);
    }

}
