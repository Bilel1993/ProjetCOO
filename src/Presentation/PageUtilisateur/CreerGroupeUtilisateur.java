package Presentation.PageUtilisateur;
import Persistance.GroupeChatMapper;
import Persistance.GroupeMapper;
import Persistance.PersonneMapper;
import Persistance.UnitOfWork;
import Domaine.Groupe;
import Domaine.Personne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CreerGroupeUtilisateur extends JPanel implements ActionListener  { 	
	//Label et TextField
	Groupe groupe;
	JLabel CeerGroupe;
	JLabel NomLabel;
    JTextField Nom;
    JLabel Message;
    
    //Jbutton
    JButton Boutonvalider = new JButton("Valider");
    
    
    public CreerGroupeUtilisateur(Personne utilsateur)throws Exception {
      	//Option du Panel
    	new JPanel(new FlowLayout());
    	this.setLayout(null);
    	this.setOpaque(true);
    	this.setBackground(Color.LIGHT_GRAY);	
        this.setSize(500,500);
        this.setVisible(true);
        
        //Creation d'un groupe
        CeerGroupe= new JLabel("Creation d'un groupe :");
        CeerGroupe.setSize(250,50);
        CeerGroupe.setLocation(175,10);
        
    	//saisie du nom de groupe
    	NomLabel = new JLabel("Entrez le nom de votre groupe :",JLabel.CENTER);
    	NomLabel.setSize(120,100);
    	NomLabel.setLocation(30,50);
    	Nom = new JTextField(); 
    	Nom.setColumns(20);
    	Nom.setSize(150,25);
    	Nom.setLocation(200,87);
    	
    	
    	//Bouton Valider
    	Boutonvalider.setSize(150,25);
    	Boutonvalider.setLocation(200,200);
    	Boutonvalider.addActionListener(this);
    	
    	
    	
    	//Ajout d'un message 
    	Message = new JLabel();

    	
    	//Ajout au Panel 
    	this.add(CeerGroupe);
    	this.add(NomLabel);
    	this.add(Nom);
    	this.add(Boutonvalider);
    	this.add(Message);
    	
    	//construire un groupe avec nom groupe et une personne (pour l(instant nom groupe est vide) 
    	groupe = new Groupe(Nom.getText(),utilsateur);
    	
    	
      }; 

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		PersonneMapper PM = new PersonneMapper();
		GroupeMapper GP = new GroupeMapper();
		GroupeChatMapper GCM= new GroupeChatMapper();
		// nom goupe prend le contenu du JTextField
		groupe.setNomGroupe(Nom.getText());
		UnitOfWork.getInstance().action(groupe);
		//Si on  Appuie sur VALIDER
		if(e.getActionCommand().equals("Valider"))
			try{ //si le groupe n'existe pas 
				if (GP.IsExistNomComptePers(Nom.getText()) == 0){
					UnitOfWork.getInstance().commit();
					GCM.insert(groupe.getNomGroupe(),groupe.getModerateurGroupe());
					
				
				}
				
				else {
					//si le groupe existe
					Message.setText("Groupe deja existant :) ");
				 	Message.setForeground(Color.red);
			    	Message.setSize(300,25);
			    	Message.setLocation(190,340);
					Message.setVisible(true);
				}
			}catch (SQLException e2){
				e2.printStackTrace();
			}
			
}//Action performed

}//CeerUnGroupe
