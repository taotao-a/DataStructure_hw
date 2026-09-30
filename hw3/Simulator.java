//package com.tao;
/**
 * This class is used to actually carry out the simulation.
 * 
 * @author Tao Yumiao R32314004
 */

public class Simulator {
	RequestQueue queue = new RequestQueue();
    Elevator[] elevators;
    BooleanSource requestSource;
    
    int totalWaitTime = 0;  
    int totalRequests = 0;  
    
    /**
     * Empty constructor
     */
    public Simulator() {
    	
    }
	
    /**
     * This method simulates the opearation elevators and count totalWaitTime and totalRequests.
     * 
     * @param requestProbability
     * The probability of requests that will appear.
     * 
     * @param numberOfFloors
     * The total number of floors of a building.
     * 
     * @param numberOfElevators
     * The total number of elevators of a building.
     * 
     * @param simulationLength
     * The length of time.
     */
    public void simulate(double requestProbability, int numberOfFloors, int numberOfElevators, int simulationLength) {
        // Initialize the request queue and the elevator array
    	elevators = new Elevator[numberOfElevators];
    	requestSource = new BooleanSource(requestProbability);
        // initialize each elevator
        for (int i = 0; i < numberOfElevators; i++) {
            elevators[i] = new Elevator();
        }

        // simulate each time unit
        for (int time = 0; time < simulationLength; time++) {
            // determine whether a new request is generated
            if (requestSource.requestArrived()) {
                Request newRequest = new Request(numberOfFloors);
                newRequest.setTimeEntered(time);  
                queue.enqueue(newRequest);        
            }

            // assign idle elevators to handle requests
            for (Elevator elevator : elevators) {
                // if an elevator is idle and there are pending requests, assign a request
                if (elevator.isIdle() && !queue.isEmpty()) {
                    Request nextRequest = queue.dequeue();
                    elevator.setCurrentRequest(nextRequest);
                    elevator.setElevatorState(Elevator.TO_SOURCE);
                }

                //  if the elevator is heading to the source floor
                if (elevator.getElevatorState() == Elevator.TO_SOURCE) {
                    elevator.moveOneFloor(); 
                    if (elevator.hasReachedSource()) {
                        // when the elevator reaches the source floor, 
                    	//calculate the wait time and start heading to the destination floor
                        totalWaitTime += (time - elevator.getCurrentRequest().getTimeEntered());
                        totalRequests++;
                        elevator.setElevatorState(Elevator.TO_DESTINATION);
                    }
                }

                //  if the elevator is heading to the destination floor
                if (elevator.getElevatorState() == Elevator.TO_DESTINATION) {
                    elevator.moveOneFloor();  // 电梯移动一层
                    if (elevator.hasReachedDestination()) {
                        // when the elevator reaches the destination floor, complete the request and reset to idle
                        elevator.reset();  
                    }
                }
            }
        }

        
        
    }
    
    /**
     * Accessor for totalWaitTime
     * 
     * @return
     * total wait time
     */
    public int getTotalWaitTime() {
    	return totalWaitTime;
    }
    
    /**
     * Accessor for totalRequests
     * 
     * @return
     * total requests
     */
    public int getTotalRequests() {
    	return totalRequests;
    }
}
