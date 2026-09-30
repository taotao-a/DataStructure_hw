//package com.tao;

/*
 * This class can create a new list which cotains many nodes.
 * Meanwhile,there are many basic operations about the list.
 */
public class ItemList {
	private ItemInfoNode head;
	private ItemInfoNode tail;
	
	public ItemList() {
		head=null;
		tail=null;
	}
	
	/*
	 * This method create a new node and insert nodes into the linked list in order.
	 * 
	 * @param name,rfidTag,price,initPosition
	 * basic information about an item to creat a new node
	 */
	// Time complexity: O(N)
	public void insertInfo(String name, String rfidTag, double price, String initPosition) {
		ItemInfo newItem = new ItemInfo(name,price,rfidTag,initPosition,initPosition);
		ItemInfoNode newNode = new ItemInfoNode();
		newNode.setInfo(newItem);
	    
		//when the linked list is empty
		if (head == null) {
	        head = newNode;
	        tail = newNode;
	        return;
	    }
		
		//Find the insertion position based on the RFID tag.
		ItemInfoNode current = head; //pointer
		while (current != null) {//loop
		    ItemInfo currentInfo = current.getInfo();
		    String currentRfidTag = currentInfo.getRfidTagNumber();
		    
		    if (currentRfidTag.equalsIgnoreCase(rfidTag)) {
		    	if (current == head) {//insert to head
	                newNode.setNext(head);
	                head.setPrev(newNode);
	                head = newNode;
	            } else {//insert to middle
	                newNode.setNext(current);
	                newNode.setPrev(current.getPrev());
	                current.getPrev().setNext(newNode);
	                current.setPrev(newNode);
	            }
	            return;  
		    }
		    
		    current=current.getNext();
		}
		
		tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
	}
	
	/*
	 * This method removes all nodes in the list that have current location listed as "out"
	 */
	// Time complexity: O(N)
	public void removeAllPurchased() {
        ItemInfoNode current = head;
        while (current != null) {//loop
            String currentLocation = current.getInfo().getCurrentLocation();

            if (currentLocation.equalsIgnoreCase("out")) {
                removeNode(current);
                System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
                System.out.println("------------------------------------------------------------");
                System.out.println(current.getInfo());
            }

            current = current.getNext();
        }
	}
	
	/*
	 * This is a helper method. This method moves nodes.
	 * 
	 * @param node
	 * The node to be moved.
	 */
	private void removeNode(ItemInfoNode node) {
        if (node == head) {
            head = head.getNext();
        } 
        else if (node == tail) {
            tail = tail.getPrev();
        } 
        else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
    }
	
	/*
	 * This method moves an item in the store
	 * 
	 * @param rfidTag
	 * the rfidTag to search for the item
	 * @param source
	 * original location
	 * @param dest
	 * target location
	 * 
	 * @throws IllegalArgumentException
	 * when source or dest is invalid
	 */
	// Time complexity: O(N)
	public boolean moveItem(String rfidTag, String source, String dest) {
	    if (source.equalsIgnoreCase("out")) {
	        throw new IllegalArgumentException("Source cannot be 'out'.");
	    }

	    boolean isDestOut = dest.equalsIgnoreCase("out");
	    boolean isDestShelf = dest.toLowerCase().startsWith("s") && dest.length() == 6;
	    boolean isDestCart = dest.toLowerCase().startsWith("c") && dest.length() == 4;

	    if (!isDestOut && !isDestShelf && !isDestCart) {
	        throw new IllegalArgumentException("You need to input the shelf location, shopping cart position, or 'out'.");
	    }

	    ItemInfoNode current = head;
	    boolean found = false;
	    while (current != null) {//loop
	        ItemInfo currentItem = current.getInfo();  
	        String currentRfidTag = currentItem.getRfidTagNumber();  
	        String currentLocation = currentItem.getCurrentLocation();  

	        boolean isSameRfidTag = currentRfidTag.equalsIgnoreCase(rfidTag);
	        boolean isSameSource = currentLocation.equalsIgnoreCase(source);

	        if (isSameRfidTag && isSameSource) {
	            currentItem.setCurrentLocation(dest);
	            found = true;  
	            break; 
	        }

	        current = current.getNext();  
	    }

	    return found;
	}

	/*
	 * Prints a neatly formatted list of all items currently in the list
	 */
	// Time complexity: O(N)
	public void printAll() {
		System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("------------------------------------------------------------");

        ItemInfoNode current = head;
        while (current != null) {
            System.out.println(current.getInfo());
            current = current.getNext();
        }
	}
	
	/*
	 * Prints a neatly formatted list of all items in a specified current location.
	 * 
	 * @param location
	 * current location of the item
	 */
	// Time complexity: O(N)
	public void printByLocation(String location) {
		System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("------------------------------------------------------------");

        ItemInfoNode current = head;
        while (current != null) {
            String currentLocation = current.getInfo().getCurrentLocation();
            
            if (currentLocation.equalsIgnoreCase(location)) {
                System.out.println(current.getInfo());
            }
            current = current.getNext();
        }
	}
	/*
	 * This method takes every item from the wrong shelf to its original location.
	 */
	// Time complexity: O(N)
	public void cleanStore() {
	    ItemInfoNode current = head;

	    while (current != null) {
	        ItemInfo info = current.getInfo();
	        String originalLocation = info.getOriginalLocation();
	        String currentLocation = info.getCurrentLocation();

	        boolean ifInOriginalLocation = currentLocation.equalsIgnoreCase(originalLocation);
	        boolean ifInCart = currentLocation.startsWith("c");
	        boolean ifCheckedOut = currentLocation.equalsIgnoreCase("out");

	        if (!ifInOriginalLocation) {
	            if (!ifInCart) {
	                if (!ifCheckedOut) {
	                	System.out.println(info);
	                    info.setCurrentLocation(originalLocation);
	                }
	            }
	        }

	        current = current.getNext();
	    }
	}
	
/*
 * Goes through a given cart and checks out each item
 * 
 * @param cartNumber
 * the cartNuber of an item
 */
	// Time complexity: O(N)
	public double checkOut(String cartNumber) {
		double totalCost = 0.0;

        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            String currentLocation = info.getCurrentLocation();

            if (currentLocation.equalsIgnoreCase(cartNumber)) {
                info.setCurrentLocation("out");
                System.out.println(info);
                totalCost += info.getPrice();
            }

            current = current.getNext();
        }

        return totalCost;
    }
	
	public void printByRfid(String rfidTag) {
		System.out.printf("%-20s%-12s%-10s%-10s%-8s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("------------------------------------------------------------");

        ItemInfoNode current = head;
        while (current != null) {
            String currentRfidTag = current.getInfo().getRfidTagNumber();
            
            if (currentRfidTag.equalsIgnoreCase(rfidTag)) {
                System.out.println(current.getInfo());
            }
            current = current.getNext();
        }
	}
}

