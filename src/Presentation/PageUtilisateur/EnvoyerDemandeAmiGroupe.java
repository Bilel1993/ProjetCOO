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
import Persistance.DemandeGroupeMapper;
import Persistance.GroupeMapper;
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class EnvoyerDemandeAmiGroupe extends JPanel implements ActionListener, ListSelectionListener   { 
	Personne Precherche;

	//Jbutton
	JButton BoutonAjouter= new JButton("Ajouter");
	
	//la personne connectée
	Personne Utilisateur;
    //JLabel avec le Nom Compte Pers 
	JLabel NomComptePersReceive;
	JLabel NomComptePersSend;
	JLabel nomGroupeLabel;
	
	//Jliste
	 JList<String> ListeAmis = new JList<String>();
	 DefaultListModel<String> ModelAmis = new DefaultListModel<String>(); 
	 
	//Erreur
	 JLabel Erreur;
	 
	public EnvoyerDemandeAmiGroupe(Personne Utilisateur,String nomGroupe) throws Exception {

		//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);

		//Boutton passer en mode chat
        BoutonAjouter.setSize(170,25);
        BoutonAjouter.setLocation(170,350);
        BoutonAjouter.addActionListener(this);
		
		
		//garder une trace du  nom de l'utilisateur 
		NomComptePersSend= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
		NomComptePersSend.setVisible(false);
		//garder une trace du nom du groupe 
		nomGroupeLabel= new JLabel(nomGroupe,JLabel.CENTER);
		nomGroupeLabel.setVisible(false);
		
		//Jlabel qui va contenir le nom du compte de la personne selectionnée
		NomComptePersReceive= new JLabel("",JLabel.CENTER);
		NomComptePersReceive.setSize(200,200);
		NomComptePersReceive.setLocation(150,230);
		
    	// remplir notre JListe avec la liste des amis de la personne passer en parametre 
		AmisMapper AM = new AmisMapper();
    	ArrayList <Personne> Friend = new ArrayList<Personne>();
    	Friend=AM.FindPersFriend(Utilisateur);
    	Iterator<Personne> it =Friend.iterator() ;
		 while (it.hasNext()) {
			 Personne p = it.next() ;
			 ModelAmis.addElement(p.getNomComptePers());
		 }
		 
		//Ajout d une erreur  Non visible pour le moment
		Erreur = new JLabel("Veuillez selectionner une personne ",JLabel.CENTER);
		Erreur.setForeground(Color.RED);
		Erreur.setSize(350,25);
		Erreur.setLocation(135,300);
		Erreur.setVisible(false);
		
		//ajouter le model a la liste 
		ListeAmis.setModel(ModelAmis);
		ListeAmis.getSelectionModel().addListSelectionListener(this);
		ListeAmis.setSize(150,150);
		ListeAmis.setLocation(180,100);

		this.add(BoutonAjouter);
		this.add(ListeAmis);
		this.add(Erreur);
	
	}; 

    public void valueChanged(ListSelectionEvent e) {
        int debutIndex = ListeAmis.getSelectionModel().getMinSelectionIndex();
        int finIndex = ListeAmis.getSelectionModel().getMaxSelectionIndex();
        if (ListeAmis.getSelectionModel().isSelectionEmpty()) {
            NomComptePersReceive.setText("vide");
        } else {
        	for (int i = debutIndex; i <= finIndex; i++) {
        	NomComptePersReceive.setText(ListeAmis.getModel().getElementAt(i) );
        	}
        }
    }
	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		GroupeMapper GP = new GroupeMapper();
		DemandeGroupeMapper DGM = new DemandeGroupeMapper();
		if (e.getActionCommand().equals("Ajouter"))
			if ((NomComptePersReceive.getText().length()==0)) Erreur.setVisible(true);
			else
				try {
					int id_Groupe= GP.IdGroupe(PM.FindByComptePers(NomComptePersSend.getText()),
							nomGroupeLabel.getText());
					if(DGM.IsDemande(id_Groupe,
							PM.FindByComptePers(NomComptePersReceive.getText())) > 0){
							Erreur.setText("Vous Avez Deja envoyer une demande a cette personne");
							Erreur.setVisible(true);
					}
						
					else DGM.insert(id_Groupe,PM.FindByComptePers(NomComptePersReceive.getText()));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
	


}//Action performed
}
