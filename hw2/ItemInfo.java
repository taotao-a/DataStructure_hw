/*
 * This class contains various information about a specific item 
 * that can or has been sold in a given department store. 
 */
//package com.tao;

public class ItemInfo {
	//basic information about an item
	private String name;
	private double price;
	private String rfidTagNumber;
	private String originalLocation;
	private String currentLocation;
	
	//constructor
	public ItemInfo() {
		
	}
	
/*
 * This is a parameterized constructor	
 * 
 * @param name,price,rfidTagNumber,originalLocation,currentLocation
 * basic information of an item
 * 
 */
	public ItemInfo(String name, double price, String rfidTagNumber, String originalLocation, String currentLocation) {
        setName(name);
        setRfidTag(rfidTagNumber);
        setPrice(price);
        setOriginalLocation(originalLocation);
        this.currentLocation = originalLocation;//At first, currentLocation is equal to originalLocation.
	}

//	accessor and mutator methods for each variable	
	public String getName() {
		return name;
	}

/*
 * This method set name of the item	
 * 
 * @param name
 * The name user entered
 * 
 * @throws IllegalArgumentException
 * when the length of the name field is larger than 20 characters
 */
	public void setName(String name) {
	        if (name.length() > 20) { 
	            throw new IllegalArgumentException("The product name must not exceed 20 characters.");
	        }
	        this.name = name;
	}

	public double getPrice() {
		return price;
	}
	
	/*
	 * This method set price of the item	
	 * 
	 * @param price
	 * The price user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the price is negative
	 */
	public void setPrice(double price) {
		if (price < 0) {
            throw new IllegalArgumentException("The product price must be a positive number.");
        }
        this.price = price;
	}

	public String getRfidTagNumber() {
		return rfidTagNumber;
	}
	
	/*
	 * This method set rfidTagNumber of the item	
	 * 
	 * @param rfidTagNumber
	 * The rfidTagNumber user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the input does not comply with the regulations
	 */
	public void setRfidTag(String rfidTagNumber) {
        if (rfidTagNumber.length() != 9) {
            throw new IllegalArgumentException("The length of setRfidTag is to be fixed at 9.");
        }

        for (int i = 0; i < rfidTagNumber.length(); i++) {
            char c = rfidTagNumber.charAt(i);
            
            boolean isDigit = (c >= '0' && c <= '9'); 
            boolean isUppercaseHex = (c >= 'A' && c <= 'F'); 
            boolean isLowercaseHex = (c >= 'a' && c <= 'f'); 
            
            if (!isDigit && !isUppercaseHex && !isLowercaseHex) {
                throw new IllegalArgumentException("The setRfidTag should be represented in hexadecimal format");
            }
        }

        this.rfidTagNumber = rfidTagNumber;
    }
	
	public String getOriginalLocation() {
		return originalLocation;
	}
	
	/*
	 * This method set originalLocation of the item	
	 * 
	 * @param originalLocation
	 * The originalLocation user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the input does not comply with the regulations
	 */
	public void setOriginalLocation(String originalLocation) {
		if (originalLocation.length() != 6) {
	        throw new IllegalArgumentException("The length of original location is to be fixed at 6.");
	    }

	    char firstChar = originalLocation.charAt(0);
	    if (firstChar != 's') {
	        throw new IllegalArgumentException("The first character of original location should be 's'");
	    }

	    for (int i = 1; i < originalLocation.length(); i++) {
	        char c = originalLocation.charAt(i);
	        if (c < '0' || c > '9') {
	            throw new IllegalArgumentException("The last five of original location characters should be numbers.");
	        }
	    }
	    
	    this.originalLocation=originalLocation;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}
	
	/*
	 * This method set currentLocation of the item	
	 * 
	 * @param currentLocation
	 * The currentLocation user entered
	 * 
	 * @throws IllegalArgumentException
	 * when the input does not comply with the regulations
	 */
	public void setCurrentLocation(String currentLocation) {
		if (currentLocation.equalsIgnoreCase("out")) {
	        this.currentLocation = currentLocation.toLowerCase();
	        return;
	    }
		
	    if (currentLocation.length() == 4 && currentLocation.charAt(0) == 'c') {
	        for (int i = 1; i < currentLocation.length(); i++) {
	            char c = currentLocation.charAt(i);
	            if (c < '0' || c > '9') {
	                throw new IllegalArgumentException("The format of currentLocation is fault");
	            }
	        }
	        this.currentLocation = currentLocation.toLowerCase();
	        return;
	    }

	    if (currentLocation.length() == 6 && currentLocation.charAt(0) == 's') {
	        for (int i = 1; i < currentLocation.length(); i++) {
	            char c = currentLocation.charAt(i);
	            if (c < '0' || c > '9') {
	                throw new IllegalArgumentException("The format of currentLocation is fault");
	            }
	        }
	        this.currentLocation = currentLocation.toLowerCase();
	        return;
	    }

	    throw new IllegalArgumentException("You need to input the shelf location, shopping cart position, or 'out'.");
	}
	
    @Override
    public String toString() {
        return String.format("%-20s%-12s%-10s%-10s%-8.2f", 
            name, rfidTagNumber, originalLocation, currentLocation, price);
    }
	
	

}
