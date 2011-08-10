package project2;
/*
 * Item.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single Item in the game.
 *
 *@Author - Steven Horowitz
 */
public class Item {
	String Protection;
	
	public Item(String name){
		Protection = name;
	}
	
	public String getProtection(){
		return Protection;
	}
}
