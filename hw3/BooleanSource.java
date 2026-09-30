//package com.tao;

/**
 * This class decides whether to create a new Request object.
 * 
 * @author Tao Yumiao R32314004
 */
public class BooleanSource {
	private double probability;  

	/**
	 * Parameterized constructor to initialize the probability of generating a request.
	 * @param probability
	 * The probability user entered.
	 */
    public BooleanSource(double probability) {//Test for legality.
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0.");
        }
        this.probability = probability;
    }

    
    /**
     * Generate a random number and compare it with the `probability` 
     * to determine whether to generate a request in the current time unit.
     * @return
     * 'true' when Math.random() < probability;else return false
     */
    public boolean requestArrived() {
        return Math.random() < probability;
    }
}
