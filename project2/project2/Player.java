package project2;
/*
 * Player.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single player in the cellar.
 *
 *@Author - Steven Horowitz
 */
import java.util.*;
public class Player {
	
	private int health;
	private ArrayList<Item> items;
	private int exp;
	private Room currentRoom;
	
	
	public Player(int health,int exp, ArrayList<Item> items,Room currentRoom){
		this.health = health;
		this.exp = exp;
		this.items = items;
		this.currentRoom = currentRoom;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getExp(){
		return exp;
	}
	
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	public void setExp(int newExp){
		exp = newExp;
	}
	
	public void addItem(Item newItem){
		items.add(newItem);
	}
	
	public void removeAllItems(){
		items.clear();
	}
	
	public int getCurrentRoomID(){
		return currentRoom.getID();
	}
	
	public Room getAdjacentRoom(String hallName){
		Hallway hall = currentRoom.getHall(hallName);
		return hall.getOtherVertex(currentRoom.getID());
	}
	
	public ArrayList<String> getItemList(){
		ArrayList<String> returnable = new ArrayList<String>();
		for(int i = 0; i<items.size();i++)
			returnable.add(items.get(i).getProtection());
		
		return returnable;
	}
	
	public Item getItem(String itemName){
		Item returnable = null;
		for(int i = 0; i< items.size();i++)
			if(items.get(i).getProtection().equals(itemName))
				returnable = items.get(i);
		return returnable;
	}
	
	public void putItem(Item item){
		items.add(item);
	}
	
	public boolean hasAmulet(){
		for(int i = 0; i< items.size();i++)
			if(items.get(i).getProtection().equals("Amulet"))
				return true;
		return false;
	}
	
	public void setCurrentRoom(Room room){
		currentRoom = room;
	}
	
	public boolean immuneToMagic(String magic){
		boolean returnable = false;
		for(int i = 0; i< items.size();i++)
			if(items.get(i).getProtection().equals(magic)){
				returnable = true;
				items.remove(i);
				setExp(getExp()+1);
				i++;
			}
		return returnable;
	}
}
