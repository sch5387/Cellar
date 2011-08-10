package project2;
/*
 * MagicWarp.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single magic effect
 *
 *@Author - Steven Horowitz
 */
public class MagicWarp extends Magic{

	Room warpTarget;
	public MagicWarp(String name, String prot, int val,String className) {
		super(name, prot, val,className);
		// TODO Auto-generated constructor stub
	}

	public void setTargetRoom(Room target){
		warpTarget = target;
	}
	
	public int getVal(){
		return value;
	}
	
	public String EffectPlayer(Player P) {
		if(!P.immuneToMagic(protectionName)){
			P.setCurrentRoom(warpTarget);
			return "...You warp to Room "+value;
		}
		return "... you are protected by your "+protectionName+"!";
	}

}
