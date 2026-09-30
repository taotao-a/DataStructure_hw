//package com.tao;

/*
 * This class can create a new node
 */
public class ItemInfoNode {
	private ItemInfoNode prev;
	private ItemInfo info;
	private ItemInfoNode next;

	//constructor	
	public ItemInfoNode() {
		
	}
	
	//accessor and mutator methods for each variable
	public void setInfo(ItemInfo info) {
		this.info = info;
	}
	
	public ItemInfo getInfo() {
		return info;
	}
	
	public void setNext(ItemInfoNode node) {
		this.next=node;
	}
	
	public void setPrev(ItemInfoNode node) {
		this.prev=node;
	}
	
	public ItemInfoNode getNext() {
		return next;
	}
	
	public ItemInfoNode getPrev() {
		return prev;
	}

}
