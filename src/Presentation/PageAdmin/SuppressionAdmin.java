package Presentation.PageAdmin;
import Persistance.PersonneMapper;
import Domaine.Personne;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class SuppressionAdmin extends JPanel implements ActionListener  { 	
	Personne Precherche;
	//Label et TextField
	JLabel Description;
	JLabel rechercherLabel;
	JTextField rechercher;
	JLabel Message;

	//Jbutton
	JButton Boutonrechercher = new JButton("Rechercher");
	JButton Boutonsupprimer = new JButton("Supprimer");

	public SuppressionAdmin() throws Exception {
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
		rechercherLabel.setSize(120,100);
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
		Boutonsupprimer.setLocation(200,300);
		Boutonsupprimer.addActionListener(this);
		Boutonsupprimer.setVisible(false);


		//Ajout d'un message 
		Message = new JLabel();


		//Ajout au Panel 
		this.add(Description);
		this.add(rechercherLabel);
		this.add(rechercher);
		this.add(Boutonrechercher);
		this.add(Boutonsupprimer);
		this.add(Message);


	}; 

	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		//Si on  Appuie sur RECHERCHER:
		if (e.getActionCommand().equals("Rechercher"))
			try {
				//on verifie que le pseudo n existe pas dans la BDD
				if (PM.IsExistNomComptePers(rechercher.getText()) > 0){
					//On sauvegarde la personne dans une variable
					Precherche = PM.FindByComptePers(rechercher.getText());
					//on confirme la suppression du compte
					Message.setText("Voulez-vous vraiment supprimer ce compte ' " + Precherche.getNomComptePers() + " ' ? ");
					Message.setForeground(Color.BLUE);
					Message.setSize(300,25);
					Message.setLocation(160,340);
					Message.setVisible(true);
					Boutonsupprimer.setVisible(true);
				}else{
					//Sinon, il y a une erreur
					Message.setText("Erreur, ce compte n'existe pas");
					Message.setForeground(Color.RED);
					Message.setSize(250,25);
					Message.setLocation(175,240);
					Message.setVisible(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}


		if(e.getActionCommand().equals("Supprimer"))
			try{
				//On verifie que le champ de recherche n'a pas été modifié
				if (rechercher.getText().equals(Precherche.getNomComptePers())){
					if (PM.IsExistNomComptePers(Precherche.getNomComptePers()) > 0 ){
						PM.delete(Precherche);
						//on confirme la suppression du compte
						Message.setText("Compte " + Precherche.getNomComptePers() + " supprimé ! :) ");
						Message.setForeground(Color.green);
						Message.setSize(300,25);
						Message.setLocation(190,340);
						Message.setVisible(true);
					}else{
						Message.setText("Erreur, le compte n'existe plus !");
						Message.setForeground(Color.red);
						Message.setSize(300,25);
						Message.setLocation(190,340);
						Message.setVisible(true);
					}
				}else{
					Message.setText("Vous avez changer la recherche, veuillez le rechercher à nouveau !");
					Message.setForeground(Color.red);
					Message.setSize(400,25);
					Message.setLocation(50,340);
					Message.setVisible(true);
				}
			}catch (SQLException e2){
				e2.printStackTrace();
			}

	}//Action performed

}//Suppression