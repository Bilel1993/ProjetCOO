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
import Persistance.PersonneMapper;
import java.awt.*;

@SuppressWarnings("serial")
public class EnvoyerUnMessage extends JPanel implements ActionListener, ListSelectionListener   { 
	Personne Precherche;

	//Jbutton
	JButton BoutonChat= new JButton("Passez en mode chat");
	
	//la personne connectée
	Personne Utilisateur;
    //JLabel avec le Nom Compte Pers 
	JLabel NomComptePersReceive;
	JLabel NomComptePersSend;
	
	//Jliste
	 JList<String> ListeAmis = new JList<String>();
	 DefaultListModel<String> ModelAmis = new DefaultListModel<String>(); 
	 
	//Erreur
	 JLabel Erreur;
	 
	public EnvoyerUnMessage(Personne Utilisateur) throws Exception {

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
		NomComptePersSend= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
		NomComptePersSend.setVisible(false);
		
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
		Erreur.setSize(250,25);
		Erreur.setLocation(150,300);
		Erreur.setVisible(false);
		
		//ajouter le model a la liste 
		ListeAmis.setModel(ModelAmis);
		ListeAmis.getSelectionModel().addListSelectionListener(this);
		ListeAmis.setSize(150,150);
		ListeAmis.setLocation(180,100);

		this.add(BoutonChat);
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
		if (e.getActionCommand().equals("Passez en mode chat"))
			if ((NomComptePersReceive.getText().length()==0)) Erreur.setVisible(true);
			else
				try {
					this.removeAll();
					this.add(new ChatUtilisateur(PM.FindByComptePers(NomComptePersSend.getText()),
							PM.FindByComptePers(NomComptePersReceive.getText())));
					this.validate();
					this.repaint();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
	


}//Action performed

//Inscription
	}
