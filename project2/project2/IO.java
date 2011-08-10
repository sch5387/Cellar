package project2;
/*
 * IO.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class builds 2 dimensional string arrays for creating objects.
 *
 *@Author - Steven Horowitz
 */
import java.io.*;
import java.util.*;
public class IO {
	
	FileReader in;
	ArrayList<String> file;
	int numMagics;
	int roomWithAmulet;
	int numRooms;
	int hallStartPointer;
	
	public IO(String filename) throws IOException {
		in = new FileReader(filename);
		file = new ArrayList<String>();
		readFile(in);
	}
	
	public void readFile(FileReader source) throws IOException{
		BufferedReader buffReader = new BufferedReader(source);
		String line = buffReader.readLine();
		while(line!=null){
			file.add(line);
			line = buffReader.readLine();
		}
	}
	
	public String[][] getMagicInstances(){
		numMagics = Integer.parseInt(file.get(0));
		String[][] magics = new String[numMagics][4];
		for(int i=1;i<numMagics+1;i++){
			String line = file.get(i);
			String[] splitline = line.split(" ");
			if(splitline.length!=4){
				String[]newline = new String[4];
				newline[0]=splitline[0];
				newline[1]=splitline[1];
				newline[2]=null;
				newline[3]=splitline[2];
				splitline = newline;
			}
			magics[i-1]=splitline;
		}
		return magics;
	}
	
	public String[][] getRoomInstances(){
		int roomPointer = numMagics+1;
		String numRooms = file.get(roomPointer);
		roomPointer++;
		String[] splitNumRooms = numRooms.split(" ");
		this.numRooms = Integer.parseInt(splitNumRooms[0]);
		String[][] returnable = new String[this.numRooms][];
		roomWithAmulet = Integer.parseInt(splitNumRooms[1]);
		
		for(int i = 0; i<this.numRooms;i++){
			String name = file.get(roomPointer);
			roomPointer++;
			String[] splitRoom = file.get(roomPointer).split(" ");
			String[] splitRoomWithName = new String[splitRoom.length+1];
			splitRoomWithName[0] = name;
			roomPointer++;
			for(int x = 1; x <splitRoom.length+1;x++)
				splitRoomWithName[x]=splitRoom[x-1];
			returnable[i]=splitRoomWithName;
		}
		hallStartPointer = roomPointer;
		return returnable;
	}
	
	public String[][] getHallwayInstances(){
		int numHalls = Integer.parseInt(file.get(hallStartPointer));
		hallStartPointer++;
		String[][] returnable = new String[numHalls][];
		for(int i = 0; i< numHalls;i++){
			String[] line = file.get(hallStartPointer).split(" ");
			hallStartPointer++;
			returnable[i]=line;
		}
		return returnable;
	}
	
	public int getAmuletRoom(){
		return this.roomWithAmulet;
	}
}
