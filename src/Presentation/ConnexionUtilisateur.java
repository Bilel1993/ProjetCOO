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



@SuppressWarnings({ "serial", "unused" })
public class ConnexionUtilisateur extends JFrame implements ActionListener  { 
		Chat chat;
	    JPanel PUtilisateur;  
	    JButton Chat = new JButton("Passer en Mode Chat");
        

	    public ConnexionUtilisateur() throws Exception { 
	    	//Panel
	    	PUtilisateur=new JPanel(new FlowLayout());
	    	PUtilisateur.setOpaque(true);
	    	PUtilisateur.setLayout(null);
	    	PUtilisateur.setBackground(Color.LIGHT_GRAY);	
	    	
	    	//Bouton Chat
	    	Chat.setSize(170,25);
	    	Chat.setLocation(150,170);
	    	Chat.addActionListener(this);
	    	
	    	//Ajouts Boutons
	    	PUtilisateur.add(Chat);
	    	
	    	//JFrame
	        this.setSize(500,500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setContentPane(PUtilisateur);
	        this.setVisible(true);
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
			//Si on clique sur Chat
			if (e.getActionCommand().equals("Passer en Mode Chat"))
				try {
					chat= new Chat();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

		}		
}
		

