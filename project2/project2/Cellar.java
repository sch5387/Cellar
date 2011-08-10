package project2;
/*
 * Cellar
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a cellar model. 
 *
 *@Author - Steven Horowitz
 */
import java.util.*;
import java.io.*;

public class Cellar {
	final static String CONFIG_FILE = "Config";
	Player P;
	ArrayList<Magic> magics;
	ArrayList<Room> rooms;
	static boolean running = false;
	String[][] magicsArray;
	String[][] roomsArray;
	String[][] hallsArray;
	
	public void setup() throws IOException, ClassNotFoundException{
		IO fileReader = new IO(CONFIG_FILE);
		rooms = new ArrayList<Room>();
		magics = new ArrayList<Magic>();
		magicsArray = fileReader.getMagicInstances();
		roomsArray = fileReader.getRoomInstances();
		hallsArray = fileReader.getHallwayInstances();
		running = true;

		createMagic();
		createRooms();
		setWarps();
		createHalls();
		getRoom(fileReader.getAmuletRoom()).setAmulet();
	}
	
	public void setWarps(){
		for(int i = 0; i<magics.size();i++){
			if(magics.get(i).getClassName().equals("warp")){
				((MagicWarp)magics.get(i)).setTargetRoom(getRoom(magics.get(i).getValue()));
			}
		}
	}
	
	public void setPlayer(Player P){
		this.P=P;
	}
	
	public Player getPlayer(){
		return P;
	}
	
	public void createRooms(){
		for(int i = 0; i< roomsArray.length;i++){
			Room newRoom = new Room(i);
			newRoom.setMagic(getMagic(roomsArray[i][0]));
			for(int x = 1; x< roomsArray[i].length;x++)
				newRoom.AddItem(new Item(roomsArray[i][x]));
			rooms.add(newRoom);
		}
	}
	
	public void createHalls(){
		for(int i = 0; i< hallsArray.length;i++){
			String name = hallsArray[i][0];
			Room room1 = getRoom(Integer.parseInt(hallsArray[i][1]));
			Room room2 = getRoom(Integer.parseInt(hallsArray[i][2]));
			Hallway hall = new Hallway(room1,room2,name);
			room1.addHall(hall);
			room2.addHall(hall);
		}
	}
	
	public Room getRoom(int ID){
		for(int i = 0; i<rooms.size();i++){
			if(rooms.get(i).ID==ID)
				return rooms.get(i);
		}
		return null;
	}
	
	public Magic getMagic(String magicName){
		for(int i = 0; i<magics.size();i++)
			if(magics.get(i).magicName.equals(magicName))
				return magics.get(i);
		return new MagicNone("None","None",0,"none");
	}
	
	public void createMagic(){
		for(int i = 0; i<magicsArray.length;i++){
			String[] magicParams = magicsArray[i];
			String name = magicParams[0];
			String className = magicParams[1];
			String value = magicParams[2];
			String protection = magicParams[3];

			if(className.equals("none")){
				magics.add(new MagicNone(name,protection,Integer.parseInt(value),className));
			}
			if(className.equals("vanish")){
				magics.add(new MagicVanish(name,protection,className));
			}			
			if(className.equals("warp")){
				magics.add(new MagicWarp(name,protection,Integer.parseInt(value),className));
			}			
			if(className.equals("weaken")){
				magics.add(new MagicWeaken(name,protection,Integer.parseInt(value),className));
			}
		}
	}
	
	public String movePlayer(String hallName){
		Room room = getRoom(P.getCurrentRoomID());
		Hallway hall = room.getHall(hallName);
		Room nextRoom = (hall.getOtherVertex(P.getCurrentRoomID()));
		P.setCurrentRoom(hall.getOtherVertex(P.getCurrentRoomID()));
		String returnable = "Entered Room "+nextRoom.getID()+"\n";
		returnable = returnable+nextRoom.EffectPlayer(P);
		return returnable;
	}
	
	public void dropItem(String item){
		Item picked;
		Room currentRoom = getRoom(P.getCurrentRoomID());
		picked = P.getItem(item);
		currentRoom.AddItem(picked);
	}
	
	public boolean pickItem(String item){
		if(P.getItemList().size()<P.getExp()){
			Item picked;
			Room currentRoom = getRoom(P.getCurrentRoomID());
			picked = currentRoom.getItem(item);
			P.addItem(picked);
			if(picked.getProtection().equals("Amulet"))
				return true;
		}
		return false;
	}
	
	public boolean playerDied(){
		return P.getHealth()<1;
	}
	
	public static void main (String[]args){
		Cellar myCellar = new Cellar();
		try {
			myCellar.setup();
			myCellar.setPlayer(new Player(100,1,new ArrayList<Item>(),myCellar.getRoom(0)));
			CellarGUI GUI = new CellarGUI(myCellar);
			GUI.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
