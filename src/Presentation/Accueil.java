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
			//Les fenêtres selon la connexion de la personne
	 		ConnexionAdmin connexionA;
	 		ConnexionUtilisateur connexionU;
	 		
	 		//Les labels et Champs
		    JLabel pseudoLabel;
		    JLabel motdepasseLabel;
		    JPanel PAccueil; 
		    JTextField pseudo;
		    JLabel Erreur;
		    JPasswordField motdepasse;
		    
		    //Bouton Se connecter
	        JButton SeConnecterA = new JButton("Mode Administrateur");
	        JButton SeConnecterU = new JButton("Mode Utilisateur");


		    public Accueil() throws Exception {
		    	//Jpanel
		    	PAccueil= new JPanel(new FlowLayout());
		    	PAccueil.setOpaque(true);
		    	PAccueil.setLayout(null);
		    	PAccueil.setBackground(Color.LIGHT_GRAY);	
		    	
		    	//Pseudo
		    	pseudoLabel = new JLabel("Pseudo ",JLabel.CENTER);
		    	pseudoLabel.setSize(50,50);
		    	pseudoLabel.setLocation(60,10);
		    	pseudo = new JTextField(); 
		    	pseudo.setColumns(20);
		    	pseudo.setSize(150,25);
		    	pseudo.setLocation(150,25);
		    	
		    	//Mot de passe
		    	motdepasseLabel = new JLabel("Mot de passe ",JLabel.CENTER);
		    	motdepasseLabel.setSize(150,50);
		    	motdepasseLabel.setLocation(25,50);
		    	motdepasse = new JPasswordField(); 
		    	motdepasse.setColumns(20);
		    	motdepasse.setSize(150,25);
		    	motdepasse.setLocation(150,65);
		    	
		    	//Bouton se ConnecterA
		    	SeConnecterA.setSize(180,25);
		    	SeConnecterA.setLocation(10,120);
		    	SeConnecterA.addActionListener(this);
		    	
		    	//Bouton SeConnecterU
		    	SeConnecterU.setSize(180,25);
		    	SeConnecterU.setLocation(210,120);
		    	SeConnecterU.addActionListener(this);

		    	//Ajout d une erreur  Non visible pour le moment
		    	Erreur = new JLabel("Erreur, Nom ou Mot de passe Incorrecte",JLabel.CENTER);
		    	Erreur.setForeground(Color.RED);
		    	Erreur.setSize(250,25);
		    	Erreur.setLocation(75,150);
		    	Erreur.setVisible(false);
		    	
		    	
		    	
		    	//Ajout dans le Panel
		    	PAccueil.add(pseudoLabel);
		    	PAccueil.add(pseudo);
		    	PAccueil.add(motdepasseLabel);
		    	PAccueil.add(motdepasse);
		    	PAccueil.add(SeConnecterA);
		    	PAccueil.add(SeConnecterU);
		    	PAccueil.add(Erreur);
		    	
		    	
		    	//Parametre JFrame
		        this.setSize(450,200);
		        this.setTitle("Connexion");
		        this.setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.setContentPane(PAccueil);
		        this.setVisible(true);
		    }; 
		  

		    //Actions !
			public void actionPerformed(ActionEvent e) {
				PersonneMapper PM = new PersonneMapper();
				//Si on clique sur Se connecter en tant qu'adminstrateur
				if (e.getActionCommand().equals("Mode Administrateur"))
					try {
						//on verifie l'existence d'une personne en BDD
						if (PM.Exists(pseudo.getText(),motdepasse.getPassword()) > 0){
							//On verifie si elle est admin ou non
							if(PM.IsAdmin(pseudo.getText(),motdepasse.getPassword()) == 1)
								connexionA = new ConnexionAdmin();
							else{
								Erreur.setText("Erreur, vous n'êtes pas Administrateur");
								Erreur.setVisible(true);						
							}	
						}else{
							Erreur.setVisible(true);
						}
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				
				//Si on clique sur Se connecter en tant qu'Utilisateur
				if (e.getActionCommand().equals("Mode Utilisateur"))
					try {
						//on verifie l'existence d'une personne en BDD
						if (PM.Exists(pseudo.getText(),motdepasse.getPassword()) > 0)
								 connexionU = new ConnexionUtilisateur();	
						else{
							Erreur.setVisible(true);						
						}				
					}catch (Exception e1) {
						e1.printStackTrace();
					}
			}//fin actionPerformed

			
}//fin  Accueil




