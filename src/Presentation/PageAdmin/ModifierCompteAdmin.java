package Presentation.PageAdmin;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Domaine.Personne;
import Domaine.Utilisateur;
import Persistance.PersonneMapper;
import Persistance.UnitOfWork;

import java.awt.*;

@SuppressWarnings("serial")
public class ModifierCompteAdmin extends JPanel implements ActionListener  { 
	Personne Precherche;
	//AfficherPersonneAdmin afficher;
	JPanel HautChercher= new JPanel();
	JPanel BasAfficher= new JPanel();

	//Label et TextField HAUT

	JLabel ChercherPers;

	JLabel PseudoLabel;
	JTextField pseudo;

	JLabel Erreur;

	//Label et TextField BAS
	JLabel nomLabel;
	JTextField nom;

	JLabel motdepasseLabel;
	JPasswordField motdepasse;

	JLabel prenomLabel;
	JTextField prenom;

	JLabel message;

	JLabel PseudoLabelBas;
	JTextField pseudoBas;


	//Jbutton
	JButton BoutonChercher= new JButton("Chercher");
	JButton BoutonModifier= new JButton("Modifier");

	public ModifierCompteAdmin() throws Exception {

		//EN HAUT  
		//Description
		ChercherPers = new JLabel("Modifier un utilisateur :");
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

		//Boutton Chercher
		BoutonChercher.setSize(150,25);
		BoutonChercher.setLocation(200,150);
		BoutonChercher.addActionListener(this);

		//Ajout d une erreur  Non visible pour le moment
		Erreur = new JLabel("Erreur, Pseudonyme invalide",JLabel.CENTER);
		Erreur.setForeground(Color.RED);
		Erreur.setSize(250,25);
		Erreur.setLocation(150,115);
		Erreur.setVisible(false);


		//Ajout au Panel 
		HautChercher.add(ChercherPers);
		HautChercher.add(PseudoLabel);
		HautChercher.add(pseudo);
		HautChercher.add(BoutonChercher);
		HautChercher.add(Erreur);
		HautChercher.setLayout(null);
		HautChercher.setOpaque(true);
		HautChercher.setBackground(Color.LIGHT_GRAY);	
		HautChercher.setSize(500,120);



		//EN BAS

		//Nom
		nomLabel = new JLabel("Nom :",JLabel.CENTER);
		nomLabel.setSize(100,25);
		nomLabel.setLocation(30,0);
		nom = new JTextField(); 
		nom.setColumns(20);
		nom.setSize(150,25);
		nom.setLocation(200,0);

		//Prenom
		prenomLabel = new JLabel("Prenom :",JLabel.CENTER);
		prenomLabel.setSize(100,25);
		prenomLabel.setLocation(30,30);
		prenom = new JTextField(); 
		prenom.setColumns(20);
		prenom.setSize(150,25);
		prenom.setLocation(200,30);

		//MotDePasse
		motdepasseLabel = new JLabel("Mot de passe :",JLabel.CENTER);
		motdepasseLabel.setSize(100,25);
		motdepasseLabel.setLocation(30,60);
		motdepasse = new JPasswordField(); 
		motdepasse.setColumns(20);
		motdepasse.setSize(150,25);
		motdepasse.setLocation(200,60);

		//Pseudo
		PseudoLabelBas = new JLabel("Pseudonyme : ",JLabel.CENTER);
		PseudoLabelBas.setSize(100,25);
		PseudoLabelBas.setLocation(30,90);
		pseudoBas = new JTextField(); 
		pseudoBas.setColumns(20);
		pseudoBas.setSize(150,25);
		pseudoBas.setLocation(200,90);

		//Bouton Modifier
		BoutonModifier.setSize(150,25);
		BoutonModifier.setLocation(200,125);
		BoutonModifier.addActionListener(this);
		BoutonModifier.setVisible(true);


		//Ajout d un message  Non visible pour le moment
		message = new JLabel("",JLabel.CENTER);
		message.setForeground(Color.blue);
		message.setSize(350,25);
		message.setLocation(75,150);
		message.setVisible(false);

		//Panel BasAfficher
		BasAfficher.add(nomLabel);
		BasAfficher.add(nom);
		BasAfficher.add(prenomLabel);
		BasAfficher.add(prenom);
		BasAfficher.add(motdepasseLabel);
		BasAfficher.add(motdepasse);
		BasAfficher.add(PseudoLabelBas);
		BasAfficher.add(pseudoBas);
		BasAfficher.add(BoutonModifier);
		BasAfficher.add(message);
		BasAfficher.setVisible(false);
		BasAfficher.setLayout(null);
		BasAfficher.setOpaque(true);
		BasAfficher.setBackground(Color.LIGHT_GRAY);	
		BasAfficher.setSize(500,350);

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
				if (PM.IsExistNomComptePers(pseudo.getText()) > 0){
					Precherche = PM.FindByComptePers(pseudo.getText());
					BasAfficher.setVisible(true);
					nom.setText(Precherche.getNomPers());
					prenom.setText(Precherche.getPrenomPers());
					motdepasse.setText(Precherche.getPasswordPers());
					pseudoBas.setText(Precherche.getNomComptePers());
				}else{
					//si il n'existe  pas: erreur
					Erreur.setVisible(true);
					BasAfficher.setVisible(false);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//Si on clique sur Modifier
		if (e.getActionCommand().equals("Modifier")){
			try {
				if (pseudo.getText().equals(Precherche.getNomComptePers())){
					if (PM.IsExistNomComptePers(Precherche.getNomComptePers()) > 0 ){
						Personne PersonneModifie = new Utilisateur(pseudoBas.getText(),nom.getText(),prenom.getText(),motdepasse.getPassword());
						UnitOfWork.getInstance().action(PersonneModifie);
						UnitOfWork.getInstance().commit();
						message.setText("l'utilisateur a été modifié !");
						message.setForeground(Color.blue);
						message.setVisible(true);
					}else{
						message.setText("Erreur, le compte n'existe plus !");
						message.setForeground(Color.red);
						message.setVisible(true);
					}
				}else{
					message.setText("Vous avez changer la recherche, veuillez le rechercher à nouveau !");
					message.setForeground(Color.red);
					message.setVisible(true);
				}	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}//Action performed

}//Inscription



