package project2;
/*
 * MagicWeaken.java
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
public class MagicWeaken extends Magic{

	public MagicWeaken(String name, String prot, int val,String className) {
		super(name, prot, val,className);
	}

	@Override
	public String EffectPlayer(Player P) {
		if(!P.immuneToMagic(protectionName)){
			P.setHealth(P.getHealth()-value);
			return "... you lose "+value+" life!";
		}
		return "... you are protected by your "+protectionName+"!";
	}

}
