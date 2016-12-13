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
public class SupprimerUnAmi extends JPanel implements ActionListener, ListSelectionListener  { 
	//Label
	JLabel Description = new JLabel("Supprimer un ami :");
	JLabel PersSupprime;
	JLabel Util ;
	JLabel MESSAGE;
	//JListe
	 JList<String> ListeAmis = new JList<String>();
	 DefaultListModel<String> ModelAmis = new DefaultListModel<String>(); 
	
	//JButton
	JButton BoutonSupprimer= new JButton("Supprimer");
	

	public SupprimerUnAmi(Personne Utilisateur) throws Exception {
		//utilisateur qui supprime un ami
		Util = new JLabel ("",JLabel.CENTER);
		Util.setText(Utilisateur.getNomComptePers());
		
		//Description
		Description.setSize(250,50);
		Description.setLocation(200,10);

		//Remplir JLIST
		AmisMapper AM = new AmisMapper();
    	ArrayList <Personne> Amis = new ArrayList<Personne>();
    	Amis=AM.FindPersFriend(Utilisateur);
    	Iterator<Personne> it =Amis.iterator() ;
		 while (it.hasNext()) {
			 Personne p = it.next() ;
			 ModelAmis.addElement(p.getNomComptePers());
		 }
		 
		 //Message a la suppression
		 MESSAGE = new JLabel("",JLabel.CENTER);
		 MESSAGE.setSize(200,25);
		 MESSAGE.setLocation(175,390);
		 
		//Jlabel qui va contenir le nom du compte de la personne selectionnée
		PersSupprime= new JLabel("",JLabel.CENTER);
		PersSupprime.setSize(200,200);
		PersSupprime.setLocation(150,230);
		 
		//Boutton Chercher
		BoutonSupprimer.setSize(150,25);
		BoutonSupprimer.setLocation(200,350);
		BoutonSupprimer.addActionListener(this);

		//ajouter le model a la liste 
		ListeAmis.setModel(ModelAmis);
		ListeAmis.getSelectionModel().addListSelectionListener(this);
		ListeAmis.setSize(150,150);
		ListeAmis.setLocation(180,100);
				
		//Ajout au JPanel
		add(Description);
		add(BoutonSupprimer);
		add(ListeAmis);
				
		//JPanel
		setLayout(null);
	    setOpaque(true);
		setVisible(true);
		setBackground(Color.LIGHT_GRAY);	
		setSize(500,500);



	}; 


    public void valueChanged(ListSelectionEvent e) {
        int debutIndex = ListeAmis.getSelectionModel().getMinSelectionIndex();
        int finIndex = ListeAmis.getSelectionModel().getMaxSelectionIndex();
        if (ListeAmis.getSelectionModel().isSelectionEmpty()) {
            PersSupprime.setText("vide");
        } else {
        	for (int i = debutIndex; i <= finIndex; i++) {
        	PersSupprime.setText(ListeAmis.getModel().getElementAt(i) );
        	}
        }
    }
	
	public void actionPerformed(ActionEvent e) {
		PersonneMapper PM = new PersonneMapper();
		AmisMapper AM = new AmisMapper();
		DemandeAmiMapper DAM = new DemandeAmiMapper();
		if (e.getActionCommand().equals("Supprimer"))
				if ((PersSupprime.getText().length() == 0 )) 
					MESSAGE.setText("Erreur, Cliquez sur un utilisateur");
				else
					try {
						DAM.deleteAMI(PM.FindByComptePers(Util.getText()),PM.FindByComptePers(PersSupprime.getText()));
						AM.DeleteAmis(PM.FindByComptePers(Util.getText()),PM.FindByComptePers(PersSupprime.getText()));
						MESSAGE.setText("Supprimé avec succès !");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
		}
		
		
		
		//rafraichir la page 
		this.removeAll();
		try {
			this.add(new SupprimerUnAmi(PM.FindByComptePers(Util.getText())));
			ListeAmis.setModel(ModelAmis);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.validate();
			this.repaint();

		
		
		
	}//Action performed

}//SupprimerUnAmi()




