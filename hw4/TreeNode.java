//package com.tao;

/**
 * This class stores the data in each node.
 * 
 * @author Tao Yumiao R32314004
 */

public class TreeNode {
    private String label;   
    private String message; 
    private String prompt;  
    private TreeNode left;  
    private TreeNode middle; 
    private TreeNode right;  

    /**
     * constructor to store the contents of a document into each node
     * @param label 
     * Used for unique identification.
     * @param prompt
     * Options provided for users to answer or select.
     * @param message
     * The question or answer content stored in the node. 
     */
    public TreeNode(String label, String prompt, String message) {
        this.label = label;
        this.prompt = prompt;
        this.message = message;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    /**
     *  determine if it is a leaf node
     * @return 
     * If there are no child nodes, return true.
     */
    public boolean isLeaf() {
        return left == null && middle == null && right == null;
    }

    // mutator and accessor methods for each instance variable
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getMiddle() {
        return middle;
    }

    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
