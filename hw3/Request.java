//package com.tao;
/**
 * This class contains basic information of a request.
 * 
 * @author Tao Yumiao R32314004
 */

public class Request {
	private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    /**
     * A constructor to initialize a request by using Math.random().
     * 
     * @param numberOfFloors
     * Help initialize sourceFloor and destinationFloor.
     */
    public Request(int numberOfFloors) {
        this.sourceFloor = (int) (Math.random() * numberOfFloors) + 1;
        this.destinationFloor = (int) (Math.random() * numberOfFloors) + 1;
    }

    /**
     * Accessor and mutator methods for each variable.
     */
	public int getSourceFloor() {
		return sourceFloor;
	}

	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	public int getTimeEntered() {
		return timeEntered;
	}

	public void setTimeEntered(int timeEntered) {
		this.timeEntered = timeEntered;
	}

}
