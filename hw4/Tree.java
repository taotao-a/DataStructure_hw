//package com.tao;

import java.util.*;

/**
 * This class  will have a reference to the root of the tree and other useful methods.
 * 
 * @author Tao Yumiao R32314004
 */

public class Tree {
    private TreeNode root;

    /**
     * constructor to create an empty tree
     */
    public Tree() {
        this.root = null;
    }
    
    /**
     * get the root of the tree
     * @return
     * the root of the tree
     */
    public TreeNode getRoot() {
    	return root;
    }

    /**
     * add nodes to the tree
     * @param label 
     * Used for unique identification.
     * @param prompt 
     * Options provided for users to answer or select.
     * @param message 
     * The question or answer content stored in the node. 
     * @param parentLabel 
     * label of the father of the node
     * @return 
     * if add successfully,return true;otherwise,return false
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        // if the tree is empty, create root node
        if (root == null) {
            root = new TreeNode(label, prompt, message);
            return true;
        }

        TreeNode parentNode = getNodeReference(root, parentLabel);
        if (parentNode == null) {
            return false;
        }

        //Add child nodes to the parent node according to order
        if (parentNode.getLeft() == null) {
            parentNode.setLeft(new TreeNode(label, prompt, message));
        } else if (parentNode.getMiddle() == null) {
            parentNode.setMiddle(new TreeNode(label, prompt, message));
        } else if (parentNode.getRight() == null) {
            parentNode.setRight(new TreeNode(label, prompt, message));
        } else {
            return false; //the parent's child nodes are already full.
        }
        return true;
    }

    /**
     * Search for a node by its label
     * @param current 
     * current node (recursion starting point)
     * @param label 
     * target label
     * @return 
     * return the found node reference; if not found, return null
     */
    private TreeNode getNodeReference(TreeNode current, String label) {
        if (current == null) {
            return null;
        }
        if (current.getLabel().equals(label)) {
            return current;
        }

        // Recursively search the left, middle, and right child nodes.
        TreeNode foundNode = getNodeReference(current.getLeft(), label);
        if (foundNode != null) {
            return foundNode;
        }
        foundNode = getNodeReference(current.getMiddle(), label);
        if (foundNode != null) {
            return foundNode;
        }
        return getNodeReference(current.getRight(), label);
    }

    /**
     *Traverses the tree in preorder, and prints the contents of the tree to the screen.
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        System.out.println("Traversing the tree in preorder:");
        preOrderTraversal(root); // 从根节点开始递归遍历
    }

    /**
     * recursive implementation of a preorder traversal
     * @param node 
     * current node
     */
    private void preOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // print message 
        System.out.println("Label: " + node.getLabel());
        System.out.println("Prompt: " + node.getPrompt());
        System.out.println("Message: " + node.getMessage());

        // recursive traversal
        preOrderTraversal(node.getLeft());
        preOrderTraversal(node.getMiddle());
        preOrderTraversal(node.getRight());
    }
    
    /**
     * This method will be used to start the question and answer session.
     */
    public void beginSession() {
        if (root == null) {
            System.out.println("The tree is empty. Please load a tree first.");
            return;
        }

        System.out.println("Help Session Starting...");
        
        TreeNode current = root;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n" + current.getMessage());

            if (current.isLeaf()) {
                System.out.println("Thank you for using this automated help service!");
                break;
            }

            if (current.getLeft() != null) {
                System.out.println("1 " + current.getLeft().getPrompt());
            }
            if (current.getMiddle() != null) {
                System.out.println("2 " + current.getMiddle().getPrompt());
            }
            if (current.getRight() != null) {
                System.out.println("3 " + current.getRight().getPrompt());
            }
            System.out.println("0 Exit session");

            System.out.print("Choice> ");
            String choice = scanner.nextLine().trim();

            // Update the current node based on the user's selection.
            if (choice.equals("0")) {
                System.out.println("Exiting the session.");
                break;
            } else if (choice.equals("1") && current.getLeft() != null) {
                current = current.getLeft();
            } else if (choice.equals("2") && current.getMiddle() != null) {
                current = current.getMiddle();
            } else if (choice.equals("3") && current.getRight() != null) {
                current = current.getRight();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}

