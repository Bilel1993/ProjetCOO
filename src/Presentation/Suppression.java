package Presentation;
import Persistance.PersonneMapper;
import Domaine.Personne;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Suppression extends JPanel implements ActionListener  { 	
	//Label et TextField
	JLabel Description;
	JLabel rechercherLabel;
    JTextField rechercher;
    JLabel Erreur;
    JLabel Confirmation;
    
    //Jbutton
    JButton Boutonrechercher = new JButton("Rechercher");
    JButton Boutonsupprimer = new JButton("Supprimer");
    
    public Suppression() throws Exception {
    	//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);
        
        //Description
        Description = new JLabel("Supprimer un utilisateur :");
        Description.setSize(250,50);
        Description.setLocation(175,10);
        
    	//Rechercher un utilisateur
    	rechercherLabel = new JLabel("Rechercher pseudo :",JLabel.CENTER);
    	rechercherLabel.setSize(100,100);
    	rechercherLabel.setLocation(30,50);
    	rechercher = new JTextField(); 
    	rechercher.setColumns(20);
    	rechercher.setSize(150,25);
    	rechercher.setLocation(200,87);
    	
    	
    	//Bouton Rechercher
    	Boutonrechercher.setSize(150,25);
    	Boutonrechercher.setLocation(200,200);
    	Boutonrechercher.addActionListener(this);
    	
    	//Bouton Suppression
    	Boutonsupprimer.setSize(150,25);
    	Boutonsupprimer.setLocation(200,400);
    	Boutonsupprimer.addActionListener(this);
    	
    	//Ajout au Panel 
    	this.add(Description);
    	this.add(rechercherLabel);
    	this.add(rechercher);
    	this.add(Boutonrechercher);
    	this.add(Boutonsupprimer);
    	
    	//Ajout d'une erreur  Non visible pour le moment
    	Erreur = new JLabel("Erreur, ce compte n'existe pas",JLabel.CENTER);
    	Erreur.setForeground(Color.RED);
    	Erreur.setSize(250,25);
    	Erreur.setLocation(75,200);
    	Erreur.setVisible(false);
    	
    	//Ajout d'un message de confirmation de suppression
    	Confirmation = new JLabel("Voulez-vous vraiment supprimer ce compte?",JLabel.CENTER);
    	Confirmation.setForeground(Color.BLUE);
    	Confirmation.setSize(250,25);
    	Confirmation.setLocation(75,400);
    	Confirmation.setVisible(false);
    	
    }; 

	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		//Si on  Appuie sur RECHERCHER:
		if (e.getActionCommand().equals("Rechercher"))
			try {
				//on verifie que le pseudo n existe pas dans la BDD
				if (PM.IsExistNomComptePers(rechercher.getText()) > 0){
					//on confirme la suppression du compte
					Confirmation.setText("Voulez-vous vraiment supprimer ce compte?");
					Confirmation.setVisible(true);
				}else{
					//Sinon, il y a une erreur
					Erreur.setText("Erreur, ce compte n'existe pas");
					Erreur.setVisible(true);
					}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		
		if(e.getActionCommand().equals("Supprimer"))
			try{
				if (PM.IsExistNomComptePers(rechercher.getText()) > 0){
					Personne p =PM.FindByComptePers(rechercher.getText());
					PM.delete(p);
					System.out.println("Compte supprimé :)");
				}
			}catch (SQLException e2){
				e2.printStackTrace();
			}
			
}//Action performed

}//Suppression