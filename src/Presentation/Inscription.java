package Presentation;
import Domaine.*;
import Persistance.PersonneMapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Inscription extends JPanel implements ActionListener  { 	
	//Label et TextField
	JLabel Description;
	JLabel nomLabel;
    JTextField nom;
    JLabel motdepasseLabel;
    JPasswordField motdepasse;
    JLabel PseudoLabel;
    JTextField pseudo;
    JLabel prenomLabel;
    JTextField prenom;
    
    //Jbutton
    JButton Boutoninscription = new JButton("inscription");
    
    public Inscription() throws Exception {
    	//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);
        
        //Description
        Description = new JLabel("Creer un nouvel utilisateur :");
        Description.setSize(250,50);
        Description.setLocation(175,10);
        
    	//Nom
    	nomLabel = new JLabel("Nom :",JLabel.CENTER);
    	nomLabel.setSize(100,100);
    	nomLabel.setLocation(30,50);
    	nom = new JTextField(); 
    	nom.setColumns(20);
    	nom.setSize(150,25);
    	nom.setLocation(200,87);
    	
    	//Prenom
    	prenomLabel = new JLabel("Prenom ",JLabel.CENTER);
    	prenomLabel.setSize(100,100);
    	prenomLabel.setLocation(30,80);
    	prenom = new JTextField(); 
    	prenom.setColumns(20);
    	prenom.setSize(150,25);
    	prenom.setLocation(200,127);
    	
    	//MotDePasse
    	motdepasseLabel = new JLabel("Mot de passe :",JLabel.CENTER);
    	motdepasseLabel.setSize(100,100);
    	motdepasseLabel.setLocation(30,130);
    	motdepasse = new JPasswordField(); 
    	motdepasse.setColumns(20);
    	motdepasse.setSize(150,25);
    	motdepasse.setLocation(200,167);
    	
    	//Pseudo
    	PseudoLabel = new JLabel("Pseudonyme : ",JLabel.CENTER);
    	PseudoLabel.setSize(100,100);
    	PseudoLabel.setLocation(30,170);
    	pseudo = new JTextField(); 
    	pseudo.setColumns(20);
    	pseudo.setSize(150,25);
    	pseudo.setLocation(200,207);
    	
    	//Bouton this
    	Boutoninscription.setSize(150,25);
    	Boutoninscription.setLocation(200,300);
    	Boutoninscription.addActionListener(this);
    	
    	//Ajout au Panel 
    	this.add(Description);
    	this.add(nomLabel);
    	this.add(nom);
    	this.add(prenomLabel);
    	this.add(prenom);
    	this.add(motdepasseLabel);
    	this.add(motdepasse);
    	this.add(PseudoLabel);
    	this.add(pseudo);
    	this.add(Boutoninscription);
    	
    }; 
		  


	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		//Si on clique sur Inscription
		if (e.getActionCommand().equals("inscription"))
			try {
				//on verifie que le pseudo n existe pas dans la BDD
				if (PM.IsExistNomComptePers(pseudo.getText()) > 0)
					//si il existe : erreur
					System.out.println("Erreur, le Nom de compte est deja pris !");
				else{
					//Sinon, on cree la personne, et on l insert dans le mapper
					Personne p=new Utilisateur(pseudo.getText(),nom.getText(),
							prenom.getText(),new String(motdepasse.getPassword()));
					PM.insert(p);
					}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
}//Action performed

}//Inscription


