package Presentation;


import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;

import Domaine.Personne;
import Domaine.Utilisateur;
import Persistance.PersonneMapper;



@SuppressWarnings({ "serial", "unused" })
	public class Connexion extends JFrame implements ActionListener  { 
		    JLabel pseudo1;
		    JLabel motdepasse1;
		    JPanel PConnexion; 
		    JTextField pseudo;
		    JPasswordField motdepasse;
	        JButton connexion = new JButton("connexion");
	        ConnectionAdmin  connectionadmin;
	        ConnectionUtilisateur  connnectionutilsateur;


		    public Connexion() throws Exception {
		    	connexion.addActionListener(this);
		    	PConnexion= new JPanel(new FlowLayout());
		    	PConnexion.setOpaque(true);
		    	PConnexion.setLayout(null);
		    	PConnexion.setBackground(Color.LIGHT_GRAY);	
		    	pseudo1 = new JLabel("Pseudo ",JLabel.CENTER);
		    	pseudo1.setSize(100,100);
		    	pseudo1.setLocation(35,10);
		    	pseudo = new JTextField(); 
		    	pseudo.setColumns(20);
		    	pseudo.setSize(150,25);
		    	pseudo.setLocation(200,47);
		    	motdepasse1 = new JLabel("Mot de passe ",JLabel.CENTER);
		    	motdepasse1.setSize(170,100);
		    	motdepasse1.setLocation(22,50);
		    	motdepasse = new JPasswordField(); 
		    	motdepasse.setColumns(20);
		    	motdepasse.setSize(150,25);
		    	motdepasse.setLocation(200,87);
		    	connexion.setSize(150,25);
		    	connexion.setLocation(200,150);
		    	PConnexion.add(pseudo1);
		    	PConnexion.add(pseudo);
		    	PConnexion.add(motdepasse1);
		    	PConnexion.add(motdepasse);
		    	PConnexion.add(connexion);
		        this.setSize(500,500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.setContentPane(PConnexion);
		        //this.pack();
		        this.setVisible(true);
		    }; 
		  


			public void actionPerformed(ActionEvent e) {
				PersonneMapper PM = new PersonneMapper();
				if (e.getActionCommand().equals("connexion"))
					try {
						//on verifie si la personne existe dans la BDD
						if (PM.IsConnected(pseudo.getText(),motdepasse.getPassword()) > 0)
							if(PM.IsAdmin(pseudo.getText(),motdepasse.getPassword()) == 1)
								 connectionadmin = new ConnectionAdmin();
							else connnectionutilsateur = new ConnectionUtilisateur();
						
						else{
							System.out.println("erreur");
						
							}
						
					}catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			
		
			}

			
		}




