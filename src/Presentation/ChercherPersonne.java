package Presentation;


import Persistance.PersonneMapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

	@SuppressWarnings("serial")
	public class ChercherPersonne extends JPanel implements ActionListener  { 	
		AfficherPersonne afficher;
		//Label et TextField
		JLabel ChercherPers;
	    JLabel PseudoLabel;
	    JTextField pseudo;
	    JLabel Erreur;

	    
	    //Jbutton
	    JButton BoutonChercher= new JButton("Chercher");
	    
	    public ChercherPersonne() throws Exception {
	    	//Option du Panel
	    	new JPanel(new FlowLayout());
	    	this.setLayout(null);
	    	this.setOpaque(true);
	    	this.setBackground(Color.LIGHT_GRAY);	
	        this.setSize(500,500);
	        this.setVisible(true);
	        
	        //Description
	        ChercherPers = new JLabel("Chercher un utilisateur :");
	        ChercherPers.setSize(250,50);
	        ChercherPers.setLocation(175,10);
	        
	    	//Pseudo
	    	PseudoLabel = new JLabel("Pseudonyme : ",JLabel.CENTER);
	    	PseudoLabel.setSize(100,100);
	    	PseudoLabel.setLocation(30,170);
	    	pseudo = new JTextField(); 
	    	pseudo.setColumns(20);
	    	pseudo.setSize(150,25);
	    	pseudo.setLocation(200,207);
	    	
	    	//Bouton this
	    	BoutonChercher.setSize(150,25);
	    	BoutonChercher.setLocation(200,300);
	    	BoutonChercher.addActionListener(this);
	    	
	    	//Ajout d une erreur  Non visible pour le moment
	    	Erreur = new JLabel("Erreur,Pseudonyme invalide",JLabel.CENTER);
	    	Erreur.setForeground(Color.RED);
	    	Erreur.setSize(250,25);
	    	Erreur.setLocation(150,350);
	    	Erreur.setVisible(false);
	    	
	    	//Ajout au Panel 
	    	this.add(ChercherPers);
	    	this.add(PseudoLabel);
	    	this.add(pseudo);
	    	this.add(BoutonChercher);
	    	this.add(Erreur);
	    	
	    }; 
			  


		public void actionPerformed(ActionEvent e) {
			PersonneMapper PM = new PersonneMapper();
			//Si on clique sur Chercher
			if (e.getActionCommand().equals("Chercher"))
				try {
					//on verifie que le pseudo  existe  dans la BDD
					if (PM.IsExistNomComptePers(pseudo.getText()) > 0)
						afficher = new AfficherPersonne(PM.FindByComptePers(pseudo.getText()));
				
					else
						//si il n'existe  pas: erreur
						Erreur.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}//Action performed

	}//Inscription



