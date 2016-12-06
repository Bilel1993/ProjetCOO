package Presentation;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;


public class ConnectionUtilisateur extends JFrame implements ActionListener  { 
		Connexion  con;
		Chat chat;
	    JPanel PUtilisateur;  
	    JButton Chat = new JButton("Passer en Mode Chat");
        

	    public ConnectionUtilisateur() throws Exception { 
	    	Chat.addActionListener(this);
	    	PUtilisateur=new JPanel(new FlowLayout());
	    	PUtilisateur.setOpaque(true);
	    	PUtilisateur.setLayout(null);

	    	PUtilisateur.setBackground(Color.LIGHT_GRAY);	
	    	Chat.setSize(170,25);
	    	Chat.setLocation(150,170);
	    	PUtilisateur.add(Chat);
	        this.setSize(500,500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setContentPane(PUtilisateur);
	        //this.pack();
	        this.setVisible(true);
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
		
			if (e.getActionCommand().equals("Passer en Mode Chat"))
				try {
					chat= new Chat();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		}
		
		public static void main(String[] args) throws Exception {
			ConnectionUtilisateur connectUtil = new ConnectionUtilisateur();
		}
	    }
		

