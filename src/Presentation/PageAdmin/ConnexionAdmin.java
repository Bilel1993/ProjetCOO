package Presentation.PageAdmin;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domaine.Personne;
import Presentation.Accueil;
import Presentation.PageUtilisateur.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;


@SuppressWarnings({ "serial", "unused" })
public class ConnexionAdmin extends JFrame implements ActionListener  { 
    //Jpanel Gauche avec les boutons
	JPanel PGauche ;   
	//Jpanel Droit, qui affiche selon le bouton qu'on appuie
	JPanel PDroite;
    //Boutons d'actions
    JButton Creation = new JButton("Créer un compte");
    JButton Modifier = new JButton("Modifier un utilisateur");
    JButton Suppression = new JButton("Supprimer un compte");
    JButton Deconnexion = new JButton ("Deconnexion");
    JLabel MessageBienvenue = new JLabel();
    
		    public ConnexionAdmin(Accueil idAccueil,Personne Admin) throws Exception { 
		    	
				// On supprime completement la fenetre de connexion
				idAccueil.dispose();
				
		    	//JPanel Gauche
		    	PGauche= new JPanel(new FlowLayout());
		    	PGauche.setOpaque(true);
		    	PGauche.setLayout(null);
		    	PGauche.setBackground(Color.LIGHT_GRAY);
		    	PGauche.setBorder(BorderFactory.createMatteBorder(0, 0, 0,2, Color.BLACK));
		    	
		    	//Message de bienvenue
		    	MessageBienvenue= new JLabel("Bienvenue " + Admin.getNomComptePers() + " ! Que voulez vous faire ?", JLabel.CENTER);
		    	MessageBienvenue.setSize(300,50);
		    	MessageBienvenue.setLocation(100,10);

		    	//Bouton Creation
		    	Creation.setSize(150,25);
		    	Creation.setLocation(60,110);
		    	Creation.addActionListener(this);
		    
		    	
		    	//Bouton Modifier
		    	Modifier.setSize(170,25);
		    	Modifier.setLocation(60,170);
		    	Modifier.addActionListener(this);
		    	
		    	//Bouton Suppression
		    	Suppression.setSize(160,25);
		    	Suppression.setLocation(260,170);
		    	Suppression.addActionListener(this);
		    	
	
				// Bouton ANNULER
				Deconnexion.setSize(120,50);
				Deconnexion.setBorderPainted(false);
				Deconnexion.setLocation(320, 350);
				Deconnexion.setForeground(Color.red);
				Deconnexion.addActionListener(this);

		    	//Ajout Bouton au panel 
				PGauche.add(MessageBienvenue);
		    	PGauche.add(Creation);
		    	PGauche.add(Modifier);
		    	PGauche.add(Suppression);
		    	PGauche.add(Deconnexion);
		    	
				
		    	//Jpanel Droite
		    	PDroite= new JPanel();
		    	PDroite.setOpaque(true);
		    	PDroite.setLayout(null);
		    	PDroite.setBackground(Color.LIGHT_GRAY);	

		    	
		    	//JFrame
		        this.setSize(1000,500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.getContentPane().setLayout(new GridLayout(1,2));
				this.getContentPane().add(PGauche);
				this.getContentPane().add(PDroite);
		        this.setVisible(true);
		        this.setLocationRelativeTo(null);
		    }; 
		    
		   
			public void actionPerformed(ActionEvent e) {
				//Si on clique sur Creer Compte , la Partie de droite devient Inscription
				if (e.getActionCommand().equals("Créer un compte"))
					try {						
						PDroite.removeAll();
						PDroite.add(new InscriptionAdmin());
						PDroite.validate();
						PDroite.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				
				//Si on clique sur Modifier un utilisateur
				if (e.getActionCommand().equals("Modifier un utilisateur"))
					try {
					PDroite.removeAll();
					PDroite.add(new ModifierCompteAdmin());
					PDroite.validate();
					PDroite.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				
				//Si on clique sur Supprimer un compte
				if (e.getActionCommand().equals("Supprimer un compte"))
					try {
					PDroite.removeAll();
					PDroite.add(new SuppressionAdmin());
					PDroite.validate();
					PDroite.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				//Si on veut se deconnecter
				if (e.getActionCommand().equals("Deconnexion")) {
					// On supprime la fenetre actuel
					this.dispose();
					try {
						// On recree la fenetre d acceuil
						Accueil fenetre = new Accueil();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				
			}
			
}

			
		



