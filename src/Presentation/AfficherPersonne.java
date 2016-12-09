package Presentation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Domaine.*;
import Persistance.*;

	@SuppressWarnings("serial")
	public class AfficherPersonne extends JFrame implements ActionListener  { 	
		//Label et TextField
		JLabel Modifer;
		JLabel nomLabel;
	    JTextField nom;
	    JLabel motdepasseLabel;
	    JPasswordField motdepasse;
	    JLabel prenomLabel;
	    JTextField prenom;
	    JLabel message;
	    
	    //Jbutton
	    JButton BoutonModifier= new JButton("Modifier");
	    
	    public AfficherPersonne(Personne p) throws Exception {
	    	//Option du Panel
	    	new JPanel(new FlowLayout());
	    	this.setLayout(null);
	    	this.setBackground(Color.LIGHT_GRAY);	
	        this.setSize(500,500);
	        this.setVisible(true);
	        
	        //Description
	        Modifer = new JLabel("Modifier un utilisateur :");
	        Modifer.setSize(250,50);
	        Modifer.setLocation(175,10);
	        
	    	//Nom
	    	nomLabel = new JLabel("Nom :",JLabel.CENTER);
	    	nomLabel.setSize(100,100);
	    	nomLabel.setLocation(30,50);
	    	nom = new JTextField(); 
	    	nom.setColumns(20);
	    	nom.setSize(150,25);
	    	nom.setLocation(200,87);
	    	nom.setText(p.getNomPers());
	    	
	    	//Prenom
	    	prenomLabel = new JLabel("Prenom ",JLabel.CENTER);
	    	prenomLabel.setSize(100,100);
	    	prenomLabel.setLocation(30,80);
	    	prenom = new JTextField(); 
	    	prenom.setColumns(20);
	    	prenom.setSize(150,25);
	    	prenom.setLocation(200,127);
	    	prenom.setText(p.getPrenomPers());
	    	
	    	//MotDePasse
	    	motdepasseLabel = new JLabel("Mot de passe :",JLabel.CENTER);
	    	motdepasseLabel.setSize(100,100);
	    	motdepasseLabel.setLocation(30,130);
	    	motdepasse = new JPasswordField(); 
	    	motdepasse.setColumns(20);
	    	motdepasse.setSize(150,25);
	    	motdepasse.setLocation(200,167);
	    	motdepasse.setText(p.getPasswordPers());
	    	
	    	//Pseudo
	    	/*PseudoLabel = new JLabel("Pseudonyme : ",JLabel.CENTER);
	    	PseudoLabel.setSize(100,100);
	    	PseudoLabel.setLocation(30,170);
	    	pseudo = new JTextField(); 
	    	pseudo.setColumns(20);
	    	pseudo.setSize(150,25);
	    	pseudo.setLocation(200,207);
	    	*/
	    	
	    	//Bouton Modifier
	    	BoutonModifier.setSize(150,25);
	    	BoutonModifier.setLocation(200,300);
	    	BoutonModifier.addActionListener(this);
	    	
	    	
	    	//Ajout d un message  Non visible pour le moment
	    	message = new JLabel("l'utilisateur a été modifie",JLabel.CENTER);
	    	message.setForeground(Color.RED);
	    	message.setSize(250,25);
	    	message.setLocation(150,250);
	    	message.setVisible(false);
	    	
	    	
	    	//Ajout au Panel 
	    	this.add(Modifer);
	    	this.add(nomLabel);
	    	this.add(nom);
	    	this.add(prenomLabel);
	    	this.add(prenom);
	    	this.add(motdepasseLabel);
	    	this.add(motdepasse);
	    	//this.add(PseudoLabel);
	    	//this.add(pseudo);
	    	this.add(BoutonModifier);
	    	this.add(message);
	    	
	    	//une fois que PAffichage est fermé on fait le commit 
	    	this.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            	p.setNomPers(nom.getText());
	            	p.setPrenomPers(prenom.getText());
	            	p.setPasswordPers(motdepasse.getPassword());
	    			UnitOfWork.getInstance().action(p);
	            	UnitOfWork.getInstance().commit();
	            }
	          });
	    	
	    }; 
			  

		public void actionPerformed(ActionEvent e) {
			//Si on clique sur Modifier
			if (e.getActionCommand().equals("Modifier")){
				message.setVisible(true);						

			}
			
	}//Action performed

	}//Inscription


