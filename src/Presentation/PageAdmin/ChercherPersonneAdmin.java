package Presentation.PageAdmin;


import Persistance.PersonneMapper;
import Persistance.UnitOfWork;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Domaine.Personne;
import java.awt.*;

@SuppressWarnings("serial")
public class ChercherPersonneAdmin extends JPanel implements ActionListener  { 	
	AfficherPersonneAdmin afficher;
	JPanel HautChercher= new JPanel();
	JPanel BasAfficher= new JPanel();
	//Label et TextField
	JLabel ChercherPers;
	JLabel PseudoLabel;
	JTextField pseudo;
	JLabel Erreur;
	JLabel Modifer;
	JLabel nomLabel;
	JTextField nom;
	JLabel motdepasseLabel;
	JPasswordField motdepasse;
	JLabel prenomLabel;
	JTextField prenom;
	JLabel message;


	//Jbutton
	JButton BoutonChercher= new JButton("Chercher");
	JButton BoutonModifier= new JButton("Modifier");

	public ChercherPersonneAdmin() throws Exception {


		//Description
		ChercherPers = new JLabel("Chercher un utilisateur :");
		ChercherPers.setSize(250,50);
		ChercherPers.setLocation(200,10);

		//Pseudo
		PseudoLabel = new JLabel("Pseudonyme : ",JLabel.CENTER);
		PseudoLabel.setSize(100,100);
		PseudoLabel.setLocation(75,40);
		pseudo = new JTextField(); 
		pseudo.setColumns(20);
		pseudo.setSize(150,25);
		pseudo.setLocation(200,80);

		//Bouton this
		BoutonChercher.setSize(150,25);
		BoutonChercher.setLocation(200,150);
		BoutonChercher.addActionListener(this);

		//Ajout d une erreur  Non visible pour le moment
		Erreur = new JLabel("Erreur, Pseudonyme invalide",JLabel.CENTER);
		Erreur.setForeground(Color.RED);
		Erreur.setSize(250,25);
		Erreur.setLocation(150,115);
		Erreur.setVisible(false);


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
		HautChercher.add(ChercherPers);
		HautChercher.add(PseudoLabel);
		HautChercher.add(pseudo);
		HautChercher.add(BoutonChercher);
		HautChercher.add(Erreur);

		//Ajout au Panel 
		BasAfficher.add(Modifer);
		BasAfficher.add(nomLabel);
		BasAfficher.add(nom);
		BasAfficher.add(prenomLabel);
		BasAfficher.add(prenom);
		BasAfficher.add(motdepasseLabel);
		BasAfficher.add(motdepasse);
		BasAfficher.add(PseudoLabel);
		BasAfficher.add(pseudo);
		BasAfficher.add(BoutonModifier);
		BasAfficher.add(message);

		//JPanel
		setSize(500,500);
		setLayout(new GridLayout(2,1));
		add(HautChercher);
		add(BasAfficher);
		setVisible(true);
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);	



	}; 


	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		//Si on clique sur Chercher
		if (e.getActionCommand().equals("Chercher"))
			try {
				//on verifie que le pseudo  existe  dans la BDD
				if (PM.IsExistNomComptePers(pseudo.getText()) > 0)
					afficher = new AfficherPersonneAdmin(PM.FindByComptePers(pseudo.getText()));

				else
					//si il n'existe  pas: erreur
					Erreur.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//Si on clique sur Modifier
		if (e.getActionCommand().equals("Modifier")){
			Personne p;
			try {
				p = PM.FindByComptePers(pseudo.getText());
				UnitOfWork.getInstance().action(p);
				UnitOfWork.getInstance().commit();
				message.setVisible(true);	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}//Action performed

}//Inscription



