package project2;
/*
 * MagicNone.java
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
public class MagicNone extends Magic{

	public MagicNone(String name, String prot, int val,String className) {
		super(name, prot, val,className);
	}

	@Override
	public String EffectPlayer(Player P) {
		return "...and nothing happened!";
	}

}
