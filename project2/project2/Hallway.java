package project2;
/*
 * Hallway
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single Hallway in the Cellar
 *
 *@Author - Steven Horowitz
 */
public class Hallway {
	Room vertex1;
	Room vertex2;
	String name;
	
	public Hallway(Room vertex1, Room vertex2, String name){
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.name = name;
	}
	
	public Room getOtherVertex(int currentID){
		if(vertex1.getID()!=currentID)
			return vertex1;
		else
			return vertex2;
	}
	
	public String getName(){
		return name;
	}
}
