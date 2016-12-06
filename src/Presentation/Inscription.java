package Presentation;
import Domaine.Personne;
import Domaine.Utilisateur;
import Persistance.PersonneMapper;
	
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

public class Inscription extends JFrame implements ActionListener  { 
	JLabel nom1;
    JLabel motdepasse1;
    JLabel pseudo1;
    JLabel prenom1;
    JPanel PInscription; 
    JTextField nom;
    JTextField prenom;
    JPasswordField motdepasse;
    JTextField pseudo;
    JButton inscription = new JButton("inscription");
    

    public Inscription() throws Exception {
    	inscription.addActionListener(this);
    	JPanel PInscription= new JPanel(new FlowLayout());
    	PInscription.setOpaque(true);
    	PInscription.setLayout(null);

    	PInscription.setBackground(Color.LIGHT_GRAY);	
    	nom1 = new JLabel("Nom ",JLabel.CENTER);
    	nom1.setSize(100,100);
    	nom1.setLocation(30,10);
    	nom = new JTextField(); 
    	nom.setColumns(20);
    	nom.setSize(150,25);
    	nom.setLocation(200,47);
    	prenom1 = new JLabel("Prenom ",JLabel.CENTER);
    	prenom1.setSize(100,100);
    	prenom1.setLocation(30,50);
    	prenom = new JTextField(); 
    	prenom.setColumns(20);
    	prenom.setSize(150,25);
    	prenom.setLocation(200,87);
    	motdepasse1 = new JLabel("Mot de passe ",JLabel.CENTER);
    	motdepasse1.setSize(100,100);
    	motdepasse1.setLocation(30,90);
    	motdepasse = new JPasswordField(); 
    	motdepasse.setColumns(20);
    	motdepasse.setSize(150,25);
    	motdepasse.setLocation(200,127);
    	pseudo1 = new JLabel("Pseudo ",JLabel.CENTER);
    	pseudo1.setSize(100,100);
    	pseudo1.setLocation(30,130);
    	pseudo = new JTextField(); 
    	pseudo.setColumns(20);
    	pseudo.setSize(150,25);
    	pseudo.setLocation(200,167);
    	inscription.setSize(150,25);
    	inscription.setLocation(200,300);
    	PInscription.add(nom1);
    	PInscription.add(nom);
    	PInscription.add(prenom1);
    	PInscription.add(prenom);
    	PInscription.add(motdepasse1);
    	PInscription.add(motdepasse);
    	PInscription.add(pseudo1);
    	PInscription.add(pseudo);
    	PInscription.add(inscription);
        this.setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setContentPane(PInscription);
        //this.pack();
        this.setVisible(true);
    }; 
		  


	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		//Si on clique sur Inscription
		if (e.getActionCommand().equals("inscription"))
			try {
				//on verifie que le pseudo n existe pas dans la BDD
				if (PM.IsExistNomComptePers(pseudo.getText()) > 0)
					//si il existe : erreur
					System.out.println("Erreur, le Nom de ocmpte est déjà pris !");
				else{
					//Sinon, on cree la personne, et on l insert dans le mapper
					Personne p=new Utilisateur(pseudo.getText(),nom.getText(),
							prenom.getText(),motdepasse.getText());
					PM.insert(p);
					}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				
			System.out.println(nom.getText());
			System.out.println(prenom.getText());
			System.out.println(motdepasse.getText());
			System.out.println(pseudo.getText());

    }

		
		}


