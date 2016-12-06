package Presentation;
 import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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


public class Chat extends JFrame implements ActionListener  { 
	    String message;
	    JPanel PChat1;
	    JPanel PChat;
	    
	    JTextField saisie;
	    JTextField MessageEnvoyer;
	    JButton Envoyer = new JButton("Envoyer");
        
        

	    public Chat() throws Exception { 
	    	Envoyer.addActionListener(this);

	    	PChat= new JPanel(new FlowLayout());
	    	PChat.setOpaque(true);
	    	PChat.setLayout(null);
	    	PChat.setBackground(Color.LIGHT_GRAY);
	    	saisie= new JTextField(); 
	    	saisie.setColumns(120);
	    	saisie.setSize(475,80);
	    	saisie.setLocation(3,320);
	    	MessageEnvoyer= new JTextField(); 
	    	MessageEnvoyer.setColumns(120);
	    	MessageEnvoyer.setSize(400,300);
	    	MessageEnvoyer.setLocation(3,3);
	    	Envoyer.setSize(150,25);
	    	Envoyer.setLocation(165,415);
	    	PChat.add(MessageEnvoyer);
	    	PChat.add(saisie);
	    	PChat.add(Envoyer);
	        this.setSize(500,500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setContentPane(PChat);
	        //this.pack();
	        this.setVisible(true);
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
		
			if (e.getActionCommand().equals("Envoyer")){
				MessageEnvoyer.setText(saisie.getText());
				saisie.setText("");
			}

				}
		
	    }
		
	


