
package Presentation.PageUtilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Domaine.Personne;
import Persistance.AmisMapper;
import Persistance.DemandeAmiMapper;
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class AccepterDemande  extends JPanel implements ActionListener, ListSelectionListener   { 
	Personne Precherche;

	//Jbutton
	JButton BoutonAccepter= new JButton("Accepter");
	JButton BoutonRefuser= new JButton("Refuser");
	
	//la personne connectée
	Personne Utilisateur;
    //JLabel avec le Nom Compte Pers 
	JLabel NomComptePersReceive;
	JLabel NomComptePersSend;
	
	//Jliste
	 JList<String> ListeDemande = new JList<String>();
	 DefaultListModel<String> ModelDemande = new DefaultListModel<String>(); 
	 
	//Erreur
	 JLabel Erreur;
	 
	public AccepterDemande(Personne Utilisateur) throws Exception {

		//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);

		//Boutton Accepter
		BoutonAccepter.setSize(100,25);
		BoutonAccepter.setLocation(140,350);
		BoutonAccepter.addActionListener(this);
		
		//Boutton Accepter
		BoutonRefuser.setSize(100,25);
		BoutonRefuser.setLocation(270,350);
		BoutonRefuser.addActionListener(this);
	
		
		
		//garder une trace du  nom de l'utilisateur 
		NomComptePersReceive= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
		NomComptePersReceive.setVisible(false);
		
		//Jlabel qui va contenir le nom du compte de la personne selectionnée
		NomComptePersSend= new JLabel("",JLabel.CENTER);
		NomComptePersSend.setSize(200,200);
		NomComptePersSend.setLocation(150,230);
		
    	// remplir notre JListe avec la liste des fils de la personne passer en parametre 
		DemandeAmiMapper DA = new DemandeAmiMapper();
    	ArrayList <Personne> Send = new ArrayList<Personne>();
    	Send=DA.FindPersSend(Utilisateur);
    	Iterator<Personne> it =Send.iterator() ;
		 while (it.hasNext()) {
			 Personne p = it.next() ; 
			 ModelDemande.addElement(p.getNomComptePers());
		 }
		 
		//Ajout d une erreur  Non visible pour le moment
		Erreur = new JLabel("Veuillez selectionner une personne ",JLabel.CENTER);
		Erreur.setForeground(Color.RED);
		Erreur.setSize(250,25);
		Erreur.setLocation(150,400);
		Erreur.setVisible(false);
		
		//ajouter le model a la liste 
		ListeDemande.setModel(ModelDemande);
		ListeDemande.getSelectionModel().addListSelectionListener(this);
		ListeDemande.setSize(150,150);
		ListeDemande.setLocation(180,100);

		this.add(BoutonAccepter);
		this.add(BoutonRefuser);
		this.add(ListeDemande);
		this.add(Erreur);
	
	}; 

    public void valueChanged(ListSelectionEvent e) {
        int debutIndex = ListeDemande.getSelectionModel().getMinSelectionIndex();
        int finIndex = ListeDemande.getSelectionModel().getMaxSelectionIndex();
        if (ListeDemande.getSelectionModel().isSelectionEmpty()) {
            NomComptePersSend.setText("vide");
        } else {
        	for (int i = debutIndex; i <= finIndex; i++) {
        	NomComptePersSend.setText(ListeDemande.getModel().getElementAt(i) );
        	}
        }
    }
	public void actionPerformed(ActionEvent e) {
		AmisMapper AM = new AmisMapper();
		DemandeAmiMapper DM = new DemandeAmiMapper();
		PersonneMapper PM = new PersonneMapper();
			
			
		
		if ((e.getActionCommand().equals("Refuser")) && (NomComptePersSend.getText().length()==0))
			Erreur.setVisible(true);
		
		if (e.getActionCommand().equals("Accepter"))
			if ((NomComptePersSend.getText().length()==0)) 
				Erreur.setVisible(true);
			else 
			try {
					
					AM.insert(PM.FindByComptePers(NomComptePersSend.getText()),
							PM.FindByComptePers(NomComptePersReceive.getText()));
					DM.UpdateAmis(PM.FindByComptePers(NomComptePersSend.getText()),
							PM.FindByComptePers(NomComptePersReceive.getText()));
					
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

					
		if (e.getActionCommand().equals("Refuser"))
			if ((NomComptePersSend.getText().length()==0)) Erreur.setVisible(true);
			else 
			try {
					DM.delete(PM.FindByComptePers(NomComptePersSend.getText()),
							PM.FindByComptePers(NomComptePersReceive.getText()));
					
				
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		//rafraichir la page 
		this.removeAll();
		try {
			this.add(new AccepterDemande(PM.FindByComptePers(NomComptePersReceive.getText())));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.validate();
		this.repaint();
		
		 ListeDemande.setModel(ModelDemande);



}//Action performed

//Inscription
	}
