//package com.tao;

/**
 * This class contains basic operations about the elevators
 * 
 * @author Tao Yumiao R32314004
 */
public class Elevator {
	//Three constants to represent the three states of an elevator.
    public static final int IDLE = 0;             
    public static final int TO_SOURCE = 1;        
    public static final int TO_DESTINATION = 2;   

    private int currentFloor;          
    private int elevatorState;         
    private Request currentRequest;    

    // 构造方法，初始化电梯在1楼，状态为空闲
    /**
     * A constructor to initialize the elevator on the 1st floor, with status set to idle.
     */
    public Elevator() {
        this.currentFloor = 1;  // 电梯默认在1楼
        this.elevatorState = IDLE;  // 默认状态为空闲
        this.currentRequest = null;  // 初始没有任何请求
    }

    /**
     * Accessor and mutator methods for each variable.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public int getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(int state) {
        this.elevatorState = state;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(Request request) {
        this.currentRequest = request;
    }

    /**
     * This method checks if the elevator is idle.
     * @return
     * 'true' when elevator is empty; else return 'false'
     */
    public boolean isIdle() {
        return elevatorState == IDLE;
    }

    /**
     * The elevator moves one floor, depending on the direction of the target floor
     */
    public void moveOneFloor() {
        if (elevatorState == TO_SOURCE) {
            if (currentFloor < currentRequest.getSourceFloor()) {
                currentFloor++;
            } else if (currentFloor > currentRequest.getSourceFloor()) {
                currentFloor--;
            }
        } else if (elevatorState == TO_DESTINATION) {
            if (currentFloor < currentRequest.getDestinationFloor()) {
                currentFloor++;
            } else if (currentFloor > currentRequest.getDestinationFloor()) {
                currentFloor--;
            }
        }
    }

    /**
     * This method checks if the elevator has reached the source floor of the request.
     *
     * @return
     * 'true' when elevator elevator has reached the source floor; else return 'false'
     */
    public boolean hasReachedSource() {
        return currentFloor == currentRequest.getSourceFloor();
    }

    /**
     * This method checks if the elevator has reached the destination floor of the request
     *
     * @return
     * 'true' when elevator elevator has reached the destination floor; else return 'false'
     */
    public boolean hasReachedDestination() {
        return currentFloor == currentRequest.getDestinationFloor();
    }

    /**
     * This method resets the elevator status to idle.
     */
    public void reset() {
        this.elevatorState = IDLE;
        this.currentRequest = null;
    }
}
