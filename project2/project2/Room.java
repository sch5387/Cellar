package project2;
/*
 * Room.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single room in the Cellar.
 *
 *@Author - Steven Horowitz
 */
import java.util.*;
public class Room {
	ArrayList<Hallway> Hallways;
	Magic magic;
	ArrayList<Item> Items;
	int ID;
	
	public Room(int ID){
		this.ID = ID;
		Hallways = new ArrayList<Hallway>();
		Items = new ArrayList<Item>();
	}
	
	public void setAmulet(){
		Items.add(new Item("Amulet"));
	}
	
	public int getID(){
		return ID;
	}
	
	public void addHall(Hallway hall){
		Hallways.add(hall);
	}
	
	public Hallway getHall(String hallName){
		Hallway hall = null;
		for(int i = 0; i< Hallways.size();i++)
			if(Hallways.get(i).getName().equals(hallName))
				hall = Hallways.get(i);
		return hall;
	}
	
	public ArrayList<String> getHallList(){
		ArrayList<String> returnable = new ArrayList<String>();
		for(int i = 0; i<Hallways.size();i++)
			returnable.add(Hallways.get(i).getName());
		return returnable;
	}
	
	public void setMagic(Magic magic){
		this.magic = magic;
	}
	
	public String getMagicName(){
		return magic.getName();
	}
	
	public String EffectPlayer(Player P){
		return magic.EffectPlayer(P);
	}
	
	public void AddItem(Item item){
		Items.add(item);
	}
	
	public ArrayList<String> getItemList(){
		ArrayList<String> returnable = new ArrayList<String>();
		for(int i = 0; i<Items.size();i++)
			returnable.add(Items.get(i).getProtection());
		
		return returnable;
	}
	
	public Item getItem(String ItemName){
		for(int i = 0; i< Items.size();i++)
			if(Items.get(i).getProtection().equals(ItemName)){
				Item item = Items.get(i);
				Items.remove(i);
				return item;
			}
		return null;
	}
}
