package Presentation.PageUtilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Domaine.Personne;
import Domaine.Utilisateur;
import Persistance.UnitOfWork;

import java.awt.*;

@SuppressWarnings("serial")
public class ModifierCompteUtilisateur extends JPanel implements ActionListener  {
	Personne Precherche;
	JLabel description;
	JLabel nomLabel;
	JTextField nom;

	JLabel motdepasseLabel;
	JPasswordField motdepasse;

	JLabel prenomLabel;
	JTextField prenom;

	JLabel message;
	Personne Utilisateur;
	
	//Jbutton
	JButton BoutonModifier= new JButton("Modifier");

	public ModifierCompteUtilisateur(Personne U) throws Exception {
		Utilisateur = U;
		//Description
		description = new JLabel("Modifier votre compte :");
		description.setSize(250,50);
		description.setLocation(200,10);

		//Nom
		nomLabel = new JLabel("Nom :",JLabel.CENTER);
		nomLabel.setSize(100,25);
		nomLabel.setLocation(30,50);
		nom = new JTextField(); 
		nom.setColumns(20);
		nom.setSize(150,25);
		nom.setLocation(200,50);	
		nom.setText(U.getNomPers());
	
		//Prenom
		prenomLabel = new JLabel("Prenom :",JLabel.CENTER);
		prenomLabel.setSize(100,25);
		prenomLabel.setLocation(30,90);
		prenom = new JTextField(); 
		prenom.setColumns(20);
		prenom.setSize(150,25);
		prenom.setLocation(200,90);
		prenom.setText(U.getPrenomPers());

		//MotDePasse
		motdepasseLabel = new JLabel("Mot de passe :",JLabel.CENTER);
		motdepasseLabel.setSize(100,25);
		motdepasseLabel.setLocation(30,130);
		motdepasse = new JPasswordField(); 
		motdepasse.setColumns(20);
		motdepasse.setSize(150,25);
		motdepasse.setLocation(200,130);
		motdepasse.setText(U.getPasswordPers());


		//Bouton Modifier
		BoutonModifier.setSize(150,25);
		BoutonModifier.setLocation(200,160);
		BoutonModifier.addActionListener(this);
		BoutonModifier.setVisible(true);


		//Ajout d un message  Non visible pour le moment
		message = new JLabel("",JLabel.CENTER);
		message.setForeground(Color.blue);
		message.setSize(350,25);
		message.setLocation(75,200);
		message.setVisible(false);

		//JPanel ajouts
		this.add(nomLabel);
		this.add(nom);
		this.add(prenomLabel);
		this.add(prenom);
		this.add(motdepasseLabel);
		this.add(motdepasse);
		this.add(BoutonModifier);
		this.add(message);
		

		//JPanel
		setSize(500,500);
		setLayout(null);
		setVisible(true);
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);	



	}; 


	public void actionPerformed(ActionEvent e) {
		//Si on clique sur Modifier
		if (e.getActionCommand().equals("Modifier")){
			//Si on a fait aucune modification
				if( (Utilisateur.getNomPers().equals(nom.getText())) && (Utilisateur.getPrenomPers().equals(prenom.getText())) &&
						(Utilisateur.getPasswordPers().equals(new String(motdepasse.getPassword()))) ){
					message.setText("Aucune Modification!");
					message.setForeground(Color.red);
					message.setVisible(true);
				}else{
						Personne PersonneModifie = new Utilisateur(Utilisateur.getNomComptePers(),nom.getText(),prenom.getText(),motdepasse.getPassword());
						UnitOfWork.getInstance().action(PersonneModifie);
						UnitOfWork.getInstance().commit();
						message.setText("Modifié avec succès!");
						message.setForeground(Color.blue);
						message.setVisible(true);
						
				}
		}

	}//Action performed

}//Inscription



