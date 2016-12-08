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
	public class Accueil extends JFrame implements ActionListener  { 
		    JLabel pseudoLabel;
		    JLabel motdepasseLabel;
		    JPanel PAccueil; 
		    JTextField pseudo;
		    JPasswordField motdepasse;
		    
		    //Bouton Se connecter
	        JButton SeConnecter = new JButton("Se Connecter");
	        //Les fenetres s'ouvrant selon le choix de connexion
	        ConnexionAdmin  connexionadmin;
	        ConnexionUtilisateur  connnexionutilsateur;


		    public Accueil() throws Exception {
		    	PAccueil= new JPanel(new FlowLayout());
		    	PAccueil.setOpaque(true);
		    	PAccueil.setLayout(null);
		    	PAccueil.setBackground(Color.LIGHT_GRAY);	
		    	pseudoLabel = new JLabel("Pseudo ",JLabel.CENTER);
		    	pseudoLabel.setSize(100,100);
		    	pseudoLabel.setLocation(35,10);
		    	pseudo = new JTextField(); 
		    	pseudo.setColumns(20);
		    	pseudo.setSize(150,25);
		    	pseudo.setLocation(200,47);
		    	motdepasseLabel = new JLabel("Mot de passe ",JLabel.CENTER);
		    	motdepasseLabel.setSize(170,100);
		    	motdepasseLabel.setLocation(22,50);
		    	motdepasse = new JPasswordField(); 
		    	motdepasse.setColumns(20);
		    	motdepasse.setSize(150,25);
		    	motdepasse.setLocation(200,87);
		    	SeConnecter.setSize(150,25);
		    	SeConnecter.setLocation(200,150);
		    	SeConnecter.addActionListener(this);
		    	PAccueil.add(pseudoLabel);
		    	PAccueil.add(pseudo);
		    	PAccueil.add(motdepasseLabel);
		    	PAccueil.add(motdepasse);
		    	PAccueil.add(SeConnecter);
		        this.setSize(500,500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.setContentPane(PAccueil);
		        //this.pack();
		        this.setVisible(true);
		    }; 
		  


			public void actionPerformed(ActionEvent e) {
				PersonneMapper PM = new PersonneMapper();
				if (e.getActionCommand().equals("Se Connecter"))
					try {
						//on verifie si la personne existe dans la BDD
						if (PM.IsConnected(pseudo.getText(),motdepasse.getPassword()) > 0)
							if(PM.IsAdmin(pseudo.getText(),motdepasse.getPassword()) == 1)
								 connexionadmin = new ConnexionAdmin();
							else connnexionutilsateur = new ConnexionUtilisateur();
						
						else{
							System.out.println("erreur");
						
							}
						
					}catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			
		
			}

			
		}




