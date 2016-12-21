package Presentation.PageUtilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Domaine.Personne;
import Persistance.DemandeGroupeMapper;
import Persistance.GroupeChatMapper;
import Persistance.GroupeMapper;
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class EnvoyerMessageGroupe extends JPanel implements ActionListener, ListSelectionListener   { 
	Personne Precherche;

	//Jbutton
	JButton BoutonChat= new JButton("Passez en mode chat");
	
	//la personne connectée
	Personne Utilisateur;
    //JLabel avec le Nom Compte Pers 
	JLabel NomCompteUtil;
	JLabel groupe;
	
	//Jliste
	 JList<String> ListeGroupe= new JList<String>();
	 DefaultListModel<String> ModelGroupe= new DefaultListModel<String>(); 
	 
	//Erreur
	 JLabel Erreur;
	 
	public EnvoyerMessageGroupe(Personne Utilisateur) throws Exception {

		//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);

		//Boutton passer en mode chat
        BoutonChat.setSize(170,25);
        BoutonChat.setLocation(170,350);
        BoutonChat.addActionListener(this);
		
		
		//garder une trace du  nom de l'utilisateur 
        NomCompteUtil= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
        NomCompteUtil.setVisible(false);
		
		//Jlabel qui va contenir le groupe selectionnée
		groupe= new JLabel("",JLabel.CENTER);
		groupe.setSize(200,200);
		groupe.setLocation(150,230);
		
    	// remplir notre JListe avec la liste des amis de la personne passer en parametre 
		GroupeChatMapper DGM = new GroupeChatMapper();
    	ArrayList <String> gr = new ArrayList<String>();
    	gr=DGM.FindGroupe(Utilisateur);
    	Iterator<String> it =gr.iterator() ;
		 while (it.hasNext()) {
			 String g = it.next() ;
			 ModelGroupe.addElement(g);
		 }
		 
		//Ajout d une erreur  Non visible pour le moment
		Erreur = new JLabel("Veuillez selectionner un groupe  ",JLabel.CENTER);
		Erreur.setForeground(Color.RED);
		Erreur.setSize(250,25);
		Erreur.setLocation(150,300);
		Erreur.setVisible(false);
		
		//ajouter le model a la liste 
		ListeGroupe.setModel(ModelGroupe);
		ListeGroupe.getSelectionModel().addListSelectionListener(this);
		ListeGroupe.setSize(150,150);
		ListeGroupe.setLocation(180,100);

		this.add(BoutonChat);
		this.add(ListeGroupe);
		this.add(Erreur);
	
	}; 

    public void valueChanged(ListSelectionEvent e) {
        int debutIndex = ListeGroupe.getSelectionModel().getMinSelectionIndex();
        int finIndex = ListeGroupe.getSelectionModel().getMaxSelectionIndex();
        if (ListeGroupe.getSelectionModel().isSelectionEmpty()) {
            groupe.setText("vide");
        } else {
        	for (int i = debutIndex; i <= finIndex; i++) {
        	groupe.setText(ListeGroupe.getModel().getElementAt(i) );
        	}
        }
    }
	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		if (e.getActionCommand().equals("Passez en mode chat"))
			if ((groupe.getText().length()==0)) Erreur.setVisible(true);
			else
				try {
					this.removeAll();
					this.add(new ChatGroupe(PM.FindByComptePers(NomCompteUtil.getText())
							,groupe.getText()));
					this.validate();
					this.repaint();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
	


}//Action performed

//Inscription
	}


