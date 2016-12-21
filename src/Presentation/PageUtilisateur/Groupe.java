package Presentation.PageUtilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Domaine.Personne;
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class Groupe  extends JPanel implements ActionListener { 
	//Jbutton
	JButton EnvoyerDemandeGroupe = new JButton ("Ajouter un ami a un groupe");
	JButton AccepterDemandeGroupe = new JButton ("Demande groupe");
	JButton EnvoyerMessage = new JButton ("Envoyez un message");
	JButton QuitterGroupe = new JButton ("Quitter un groupe");


	
	//la personne connectée
	Personne Utilisateur;
	 //JLabel avec le Nom Compte Pers 
		JLabel NomCompteUtil;
	 
	public Groupe(Personne Utilisateur) throws Exception {

		//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);

		//Boutton Envoyer une demande de groupe a un ami 
        EnvoyerDemandeGroupe.setSize(200,25);
        EnvoyerDemandeGroupe.setLocation(60,110);
        EnvoyerDemandeGroupe.addActionListener(this);
		
		//Boutton Accepter une demande de groupe 
        AccepterDemandeGroupe.setSize(170,25);
        AccepterDemandeGroupe.setLocation(260,110);
        AccepterDemandeGroupe.addActionListener(this);
        
    	//Boutton Envoyer un message a un groupe 
        EnvoyerMessage.setSize(170,25);
        EnvoyerMessage.setLocation(60,170);
        EnvoyerMessage.addActionListener(this);
		
      //Boutton Envoyer un message a un groupe 
        QuitterGroupe.setSize(170,25);
        QuitterGroupe.setLocation(260,170);
        QuitterGroupe.addActionListener(this);
		
		//garder une trace du  nom de l'utilisateur 
		NomCompteUtil= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
		NomCompteUtil.setVisible(false);
		
		
		
		this.add(EnvoyerDemandeGroupe);
		this.add(AccepterDemandeGroupe);
		this.add(EnvoyerMessage);
		this.add(QuitterGroupe);

	}; 


	
    public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();

		
		if (e.getActionCommand().equals("Ajouter un ami a un groupe"))
			try {
			this.removeAll();
			Personne p = PM.FindByComptePers(NomCompteUtil.getText());
			this.add(new AjouterAmisGroupe(p));
			this.validate();
			this.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		if (e.getActionCommand().equals("Demande groupe"))
			try {
			this.removeAll();
			Personne p = PM.FindByComptePers(NomCompteUtil.getText());
			this.add(new AccepterDemandeGroupe(p));
			this.validate();
			this.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		if (e.getActionCommand().equals("Envoyez un message"))
			try {
			this.removeAll();
			Personne p = PM.FindByComptePers(NomCompteUtil.getText());
			this.add(new EnvoyerMessageGroupe(p));
			this.validate();
			this.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		
		if (e.getActionCommand().equals("Quitter un groupe"))
			try {
			this.removeAll();
			Personne p = PM.FindByComptePers(NomCompteUtil.getText());
			this.add(new QuitterGroupe(p));
			this.validate();
			this.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
				
}//Action performed

//Inscription
	}


