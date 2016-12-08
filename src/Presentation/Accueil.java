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
	        JButton SeConnecter = new JButton("Se Connecter");


		    public Accueil() throws Exception {
		    	//Jpanel
		    	PAccueil= new JPanel(new FlowLayout());
		    	PAccueil.setOpaque(true);
		    	PAccueil.setLayout(null);
		    	PAccueil.setBackground(Color.LIGHT_GRAY);	
		    	
		    	//Pseudo
		    	pseudoLabel = new JLabel("Pseudo ",JLabel.CENTER);
		    	pseudoLabel.setSize(50,50);
		    	pseudoLabel.setLocation(10,10);
		    	pseudo = new JTextField(); 
		    	pseudo.setColumns(20);
		    	pseudo.setSize(150,25);
		    	pseudo.setLocation(100,25);
		    	
		    	//Mot de passe
		    	motdepasseLabel = new JLabel("Mot de passe ",JLabel.CENTER);
		    	motdepasseLabel.setSize(150,50);
		    	motdepasseLabel.setLocation(-25,50);
		    	motdepasse = new JPasswordField(); 
		    	motdepasse.setColumns(20);
		    	motdepasse.setSize(150,25);
		    	motdepasse.setLocation(100,65);
		    	
		    	//Bouton se Connecter
		    	SeConnecter.setSize(125,25);
		    	SeConnecter.setLocation(100,100);
		    	SeConnecter.addActionListener(this);

		    	//Ajout d une erreur  Non visible pour le moment
		    	Erreur = new JLabel("Erreur, ID inconnu !",JLabel.CENTER);
		    	Erreur.setForeground(Color.RED);
		    	Erreur.setSize(150,25);
		    	Erreur.setLocation(75,140);
		    	Erreur.setVisible(false);
		    	
		    	//Ajout dans le Panel
		    	PAccueil.add(pseudoLabel);
		    	PAccueil.add(pseudo);
		    	PAccueil.add(motdepasseLabel);
		    	PAccueil.add(motdepasse);
		    	PAccueil.add(SeConnecter);
		    	PAccueil.add(Erreur);
		    	
		    	
		    	//Parametre JFrame
		        this.setSize(300,200);
		        this.setTitle("Connexion");
		        this.setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.setContentPane(PAccueil);
		        this.setVisible(true);
		    }; 
		  

		    //Actions !
			public void actionPerformed(ActionEvent e) {
				PersonneMapper PM = new PersonneMapper();
				//Si on clique sur Se connecter
				if (e.getActionCommand().equals("Se Connecter"))
					try {
						//on verifie l'existence d'une personne en BDD
						if (PM.Exists(pseudo.getText(),motdepasse.getPassword()) > 0)
							//On verifie si elle est admin ou non
							if(PM.IsAdmin(pseudo.getText(),motdepasse.getPassword()) == 1)
								  connexionA = new ConnexionAdmin();
							else
								 connexionU = new ConnexionUtilisateur();	
						else{
							Erreur.setVisible(true);							}				
					}catch (Exception e1) {
						e1.printStackTrace();
					}
			}//fin actionPerformed

			
}//fin  Accueil




