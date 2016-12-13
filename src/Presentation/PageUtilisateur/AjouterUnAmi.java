package Presentation.PageUtilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Domaine.Personne;
import Persistance.DemandeAmiMapper;
import Persistance.PersonneMapper;

import java.awt.*;

@SuppressWarnings("serial")
public class AjouterUnAmi extends JPanel implements ActionListener  { 
	Personne Precherche;
	Personne Putilisateur;
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
	JLabel nom;

	JLabel prenomLabel;
	JLabel prenom;
	
	//message d'erreur
	JLabel message;
	JLabel messageAmis;

	JLabel PseudoLabelBas;
	JTextField pseudoBas;


	//Jbutton
	JButton BoutonChercher= new JButton("Chercher");
	JButton BoutonAjouter= new JButton("Ajouter");
	
	//la personne connectée
	Personne Utilisateur;
    //JLabel avec le Nom Compte Pers 
	JLabel NomComptePers;

	public AjouterUnAmi(Personne Utilisateur) throws Exception {

		//EN HAUT  
		//Description
		ChercherPers = new JLabel("Ajouter un ami :");
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
		nom = new JLabel(); 
		nom.setSize(150,25);
		nom.setLocation(200,0);

		//Prenom
		prenomLabel = new JLabel("Prenom :",JLabel.CENTER);
		prenomLabel.setSize(100,25);
		prenomLabel.setLocation(30,30);
		prenom = new JLabel(); 
		prenom.setSize(150,25);
		prenom.setLocation(200,30);

		//Bouton Modifier
		BoutonAjouter.setSize(150,25);
		BoutonAjouter.setLocation(200,100);
		BoutonAjouter.addActionListener(this);
		BoutonAjouter.setVisible(true);


		//Ajout d un message  Non visible pour le moment
		message = new JLabel("Vous avez déjà envoyez une demande a cette personne",JLabel.CENTER);
		message.setForeground(Color.red);
		message.setSize(350,25);
		message.setLocation(100,140);
		message.setVisible(false);
		
		//Ajout d un message  Non visible pour le moment
		messageAmis = new JLabel("Vous êtes déjà ami avec cette personne ",JLabel.CENTER);
		messageAmis.setForeground(Color.red);
		messageAmis.setSize(350,25);
		messageAmis.setLocation(100,140);
		messageAmis.setVisible(false);

		//Panel BasAfficher
		BasAfficher.add(nomLabel);
		BasAfficher.add(nom);
		BasAfficher.add(prenomLabel);
		BasAfficher.add(prenom);
		BasAfficher.add(BoutonAjouter);
		BasAfficher.add(message);
		BasAfficher.add(messageAmis);
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
		
		
		//garder une trace du  nom de l'utilisateur 
		NomComptePers = new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
		NomComptePers.setVisible(false);


	}; 


	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		DemandeAmiMapper DM = new DemandeAmiMapper();
			
		
		//Si on clique sur Chercher
		if (e.getActionCommand().equals("Chercher"))
			try {
				//on verifie que le pseudo  existe  dans la BDD
				if (PM.IsExistNomComptePers(pseudo.getText()) > 0){	
					Precherche = PM.FindByComptePers(pseudo.getText());
					BasAfficher.setVisible(true);
					nom.setText(Precherche.getNomPers());
					prenom.setText(Precherche.getPrenomPers());
				}else{
					//si il n'existe  pas: erreur
					Erreur.setVisible(true);
					BasAfficher.setVisible(false);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//Si on clique sur ajouter
		if (e.getActionCommand().equals("Ajouter"))
			try {
				Precherche = PM.FindByComptePers(pseudo.getText());
				Putilisateur= PM.FindByComptePers(NomComptePers.getText());

	//on verifie que l'utilsateur n'a pas déjà envoyer une demande a la même personne et que cette personne n'a pas encore répondu  
				if (DM.IsAmis(Putilisateur,Precherche) == 0){
					message.setVisible(true);
				}else if (DM.IsAmis(Precherche,Putilisateur) == 0) {
					
					message.setText("vous avez une demande d'ami de cette personne");
					message.setVisible(true);
				}else if ((DM.IsAmis(Putilisateur,Precherche) == 1) || (DM.IsAmis(Precherche,Putilisateur) == 1)) {
					 messageAmis.setVisible(true);
					 	
				}else {
					DM.insert(Putilisateur,Precherche);
					 }
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}//Action performed

//Inscription




