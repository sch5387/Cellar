package project2;
/*
 * CellarGUI.java
 * 
 * File:
 * $Id$
 *      
 * Revision:
 * $Log$
 */

/**
 *This class represents the GUI interface for the Cellar game.
 *
 *@Author - Steven Horowitz
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.*;

public class CellarGUI extends JFrame implements ActionListener{
	Cellar model;
	JList roomItemsList;
	JList playerItemsList = new JList();
	JLabel health = new JLabel();
	JLabel exp= new JLabel();
	JLabel spacer = new JLabel("");
	JLabel currentRoom= new JLabel();
	JLabel magicLabel =  new JLabel("Magic in the Room");
	JLabel magic = new JLabel();
	JList hallList;
	JLabel playerItemLabel = new JLabel();;
	JTextArea output;
	
	public CellarGUI (Cellar model){
		this.model = model;
		setTitle("Cellar");
		setSize(new Dimension (1000,600));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		health.setPreferredSize(new Dimension(200,10));
		exp.setPreferredSize(new Dimension(200,10));
		currentRoom.setPreferredSize(new Dimension(200,10));
		spacer.setPreferredSize(new Dimension(200,115));
		magicLabel.setPreferredSize(new Dimension(200,10));
		magicLabel.setHorizontalAlignment(JLabel.CENTER);
		playerItemLabel.setText("Player's Items (Max 1)");
		setLabels();
		
		JScrollPane playerItemsPane = new JScrollPane();
		playerItemsList.setListData(model.getPlayer().getItemList().toArray());
		playerItemsPane.setViewportView(playerItemsList);
		playerItemsPane.setPreferredSize(new Dimension(200,125));
		JButton drop = new JButton("Drop Item");
		drop.addActionListener(this);
		drop.setPreferredSize(new Dimension(200,25));
		
		JScrollPane roomItemsPane = new JScrollPane();
		roomItemsList = new JList(getRoomItems());
		roomItemsPane.setViewportView(roomItemsList);
		roomItemsPane.setPreferredSize(new Dimension(200,125));
		JButton pickUp = new JButton("Pick Up");
		pickUp.addActionListener(this);
		pickUp.setPreferredSize(new Dimension(200,25));
		
		JScrollPane hallsPane = new JScrollPane();
		hallList = new JList(getHalls());
		hallsPane.setViewportView(hallList);
		hallsPane.setPreferredSize(new Dimension(200,200));
		JButton enterHall = new JButton("Enter Hallway");
		enterHall.setPreferredSize(new Dimension(200,25));
		enterHall.addActionListener(this);
		
		JScrollPane outputPane = new JScrollPane();
		output = new JTextArea();
		outputPane.setPreferredSize(new Dimension(550,550));
		output.setEditable(false);
		output.setBorder(BorderFactory.createLineBorder(Color.black));
		outputPane.setViewportView(output);
		
		JLabel roomItemLabel = new JLabel("Items in the Room");
		JLabel hallsLabel = new JLabel("Hallways");
		JPanel rightPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		rightPanel.add(playerItemLabel);
		rightPanel.add(playerItemsPane);
		rightPanel.add(drop);
		rightPanel.add(roomItemLabel);
		rightPanel.add(roomItemsPane);
		rightPanel.add(pickUp);
		rightPanel.add(magicLabel);
		rightPanel.add(magic);
		rightPanel.add(spacer);
		rightPanel.add(health);
		rightPanel.add(exp);
		rightPanel.add(currentRoom);
		rightPanel.setPreferredSize(new Dimension(200,600));
		rightPanel.setBackground(Color.WHITE);
		leftPanel.add(hallsLabel);
		leftPanel.add(hallsPane);
		leftPanel.add(enterHall);
		leftPanel.setPreferredSize(new Dimension(200,600));
		leftPanel.setBackground(Color.WHITE);
		add(outputPane,BorderLayout.CENTER);
		add(rightPanel,BorderLayout.WEST);
		add(leftPanel,BorderLayout.EAST);
	}
	
	public void outputText(String text){
		output.setText(output.getText()+text+"\n");
	}
	
	public void setLabels(){
		int currentRoomID = model.getPlayer().getCurrentRoomID();
		magic.setText(model.getRoom(currentRoomID).getMagicName());
		health.setText("Health = "+model.getPlayer().getHealth());
		magic.setVerticalTextPosition(JLabel.TOP);
		exp.setText("Experience = "+model.getPlayer().getExp());
		currentRoom.setText("Current Room ID = "+model.getPlayer().getCurrentRoomID());
		playerItemLabel.setText("Player's Items (Max "+model.getPlayer().getExp()+")");
	}
	
	public Object[] getHalls(){
		int currentRoomID = model.getPlayer().getCurrentRoomID();
		ArrayList<String> halls = model.getRoom(currentRoomID).getHallList(); 
		return halls.toArray();
	}
	
	public Object[] getRoomItems(){
		int currentRoomID = model.getPlayer().getCurrentRoomID();
		ArrayList<String> itemList = model.getRoom(currentRoomID).getItemList();
		return itemList.toArray();
	}
	
	public void update(){
		setLabels();
		hallList.setListData(getHalls());
		roomItemsList.setListData(getRoomItems());
		Object[] items = (model.getPlayer().getItemList().toArray());
		playerItemsList.setListData(items);
		if(model.playerDied())
			endGame("You Lose!");
	}
	
	public void endGame(String message){
		JOptionPane.showMessageDialog(null, message);
		setVisible(false);
		dispose();
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Enter Hallway")){
			if(hallList.getSelectedValue()!=null){
				outputText(model.movePlayer(hallList.getSelectedValue().toString()));
				update();
			}
		}
		if(arg0.getActionCommand().equals("Drop Item")){
			if(playerItemsList.getSelectedValue()!=null)
				model.dropItem(playerItemsList.getSelectedValue().toString());
				update();
		}
		if(arg0.getActionCommand().equals("Pick Up")){
			if(roomItemsList.getSelectedValue()!=null)
				if(model.pickItem(roomItemsList.getSelectedValue().toString()))
					endGame("You Win!");
				update();
		}
	}
}
