package project2;
/*
 * MagicVanish
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
public class MagicVanish extends Magic{

	public MagicVanish(String name, String prot, int val,String className) {
		super(name, prot, val,className);
		// TODO Auto-generated constructor stub
	}
	
	public MagicVanish(String name, String prot,String className){
		super(name,prot,0,className);
	}

	@Override
	public String EffectPlayer(Player P) {
		if(!P.immuneToMagic(protectionName)){
			P.removeAllItems();
			return "... All your protections vanish!";
		}
		return "... you are protected by your "+protectionName+"!";
	}

}
