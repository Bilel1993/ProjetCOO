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
import Persistance.GroupeMapper;
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class AjouterAmisGroupe extends JPanel implements ActionListener, ListSelectionListener   { 
	Personne Precherche;

	//Jbutton
	JButton BoutonAjouter= new JButton("Ajouter");
	
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
	 
	public AjouterAmisGroupe(Personne Utilisateur) throws Exception {

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
        NomCompteUtil= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
        NomCompteUtil.setVisible(false);
		
		//Jlabel qui va contenir le groupe selectionnée
		groupe= new JLabel("",JLabel.CENTER);
		groupe.setSize(200,200);
		groupe.setLocation(150,230);
		
    	// remplir notre JListe avec la liste des amis de la personne passer en parametre 
		GroupeMapper GP = new GroupeMapper();
    	ArrayList <String> gr = new ArrayList<String>();
    	gr=GP.GroupePers(Utilisateur);
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

		this.add(BoutonAjouter);
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
		if (e.getActionCommand().equals("Ajouter"))
			if ((groupe.getText().length()==0)) Erreur.setVisible(true);
			else
				try {
					this.removeAll();
					this.add(new EnvoyerDemandeAmiGroupe(PM.FindByComptePers(NomCompteUtil.getText())
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

