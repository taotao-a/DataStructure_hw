//package com.tao;

import java.util.ArrayList;

/**
 * This class is derived as a subclass of ArrayList and is used for managing a request queue.
 * 
 * @author Tao Yumiao R32314004
 */

public class RequestQueue extends ArrayList<Request> {

    /**
     * empty constructor
     */
    public RequestQueue() {
    	
    }

    /**
     * This method adds the request to the end of the queue.
     * 
     * @param request 
     * The request object to be added to the queue.
     */
    public void enqueue(Request request) {
        this.add(request);  
    }

    /**
     * Remove and return the first request from the queue.
     * @return 
     * Return the first request in the queue, or return null if the queue is empty.
     */
    public Request dequeue() {
        if (!this.isEmpty()) {
            return this.remove(0);  
        } else {
            return null;  
        }
    }

    /**
     * Return the number of requests in the queue.
     * @return 
     * The number of requests in the queue.
     */
    @Override
    public int size() {
        return super.size();  
    }

    /**
     * Check if the queue is empty.
     * @return 
     * Return `true` if the queue is empty, otherwise return `false`.
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();  
    }
}


