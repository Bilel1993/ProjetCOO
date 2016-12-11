package Presentation.PageUtilisateur;
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
import Persistance.PersonneMapper;
import Presentation.Accueil;
import Presentation.PageAdmin.ModifierCompteAdmin;
import Presentation.PageUtilisateur.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;


@SuppressWarnings({ "serial", "unused" })
public class ConnexionUtilisateur extends JFrame implements ActionListener  { 
    //Jpanel Gauche avec les boutons
	JPanel PGauche ;   
	//Jpanel Droit, qui affiche selon le bouton qu'on appuie
	JPanel PDroite;
    
	//JLabel avec le Nom Compte Pers 
	JLabel NomComptePers;

	//Boutons d'actions
	JButton Chat = new JButton("Passer en Mode Chat");
    JButton Deconnexion = new JButton ("Deconnexion");
    JButton Modifier = new JButton("Modifier son Compte");
    JButton CreationGroupe = new JButton ("Creer un groupe");
    JLabel MessageBienvenue = new JLabel();
    Personne Utilisateur;
    
		    public ConnexionUtilisateur(Personne utilisateur) throws Exception { 
		    	Utilisateur = utilisateur;
		    	//JPanel Gauche
		    	PGauche= new JPanel(new FlowLayout());
		    	PGauche.setOpaque(true);
		    	PGauche.setLayout(null);
		    	PGauche.setBackground(Color.LIGHT_GRAY);
		    	PGauche.setBorder(BorderFactory.createMatteBorder(0, 0, 0,2, Color.BLACK));
		    	
		    	//Bouton Modifier
		    	Modifier.setSize(170,25);
		    	Modifier.setLocation(60,170);
		    	Modifier.addActionListener(this);
		    	
		    	//Message de bienvenue
		    	MessageBienvenue= new JLabel("Bienvenue " + utilisateur.getPrenomPers() + " ! Que voulez vous faire ?", JLabel.CENTER);
		    	MessageBienvenue.setSize(300,50);
		    	MessageBienvenue.setLocation(100,10);
		 
		    	//NomComptePers
		    	NomComptePers= new JLabel(utilisateur.getNomComptePers(),JLabel.CENTER);
		    	NomComptePers.setText(utilisateur.getNomComptePers());
		    	NomComptePers.setVisible(false);
		    	
		    	//Bouton Chat
		    	Chat.setSize(170,25);
		    	Chat.setLocation(260,110);
		    	Chat.addActionListener(this);
		    		
		    	//Bouton Creer un groupe 
		    	CreationGroupe.setSize(170,25);
		    	CreationGroupe.setLocation(60,110);
		    	CreationGroupe.addActionListener(this);
				
		    	// Bouton ANNULER
				Deconnexion.setSize(120,50);
				Deconnexion.setBorderPainted(false);
				Deconnexion.setLocation(320, 350);
				Deconnexion.setForeground(Color.red);
				Deconnexion.addActionListener(this);

		    	//Ajout Bouton au panel 
				PGauche.add(MessageBienvenue);
		    	PGauche.add(Chat);
		    	PGauche.add(CreationGroupe);
		    	PGauche.add(Deconnexion);
		    	PGauche.add(Modifier);
		    	
				
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
				PersonneMapper PM = new PersonneMapper();
				//Si on clique sur Passer en mode Chat
				if (e.getActionCommand().equals("Passer en Mode Chat"))
					try {
					PDroite.removeAll();
					PDroite.add(new ChatUtilisateur());
					PDroite.validate();
					PDroite.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				//Si on clique sur "Modifier son Compte"
				if (e.getActionCommand().equals("Modifier son Compte"))
					try {
					PDroite.removeAll();
					PDroite.add(new ModifierCompteUtilisateur(Utilisateur));
					PDroite.validate();
					PDroite.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				//Si on clique sur creer un groupe
				if (e.getActionCommand().equals("Creer un groupe"))
					try {
					PDroite.removeAll();
					Personne p = PM.FindByComptePers(NomComptePers.getText());
					PDroite.add(new CreerGroupeUtilisateur(p));
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

			
		



