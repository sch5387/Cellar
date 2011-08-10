package project2;
/*
 * Magic
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents a single magic effect in the game.
 *
 *@Author - Steven Horowitz
 */
public abstract class Magic {
	String magicName;
	String protectionName;
	int value;
	String className;
	
	public Magic(String name,String prot, int val,String className){
		value = val;
		protectionName = prot;
		magicName = name;
		this.className=className;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getName(){
		return magicName;
	}
	
	public String getProtection(){
		return protectionName;
	}
	
	public String getClassName(){
		return className;
	}
	
	public abstract String EffectPlayer(Player P);
}
