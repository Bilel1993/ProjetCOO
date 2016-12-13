package Presentation.PageUtilisateur;
 import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domaine.Personne;
import Persistance.AmisMapper;
import Persistance.PersonneMapper;

import java.awt.Color;


@SuppressWarnings({ "serial", "unused" })
public class ChatUtilisateur extends JPanel implements ActionListener  { 
	    String message;
	    
	    JTextField saisie;
	    JTextField MessageEnvoyer;
	    JButton Envoyer = new JButton("Envoyer");
	    
	    //JLabel avec le Nom Compte Pers 
		JLabel NomComptePersReceive;
		JLabel NomComptePersSend;
		
		//JLabel Message Envoyer
		JLabel MessageEnvoyerSucces;
        

	    public ChatUtilisateur(Personne Utilisateur1, Personne Utilisateur2) throws Exception { 
	    	AmisMapper AM = new AmisMapper();
	    	Envoyer.addActionListener(this);
	    	new JPanel(new FlowLayout());
	    	this.setOpaque(true);
	    	this.setLayout(null);
	    	this.setBackground(Color.LIGHT_GRAY);
	    	saisie= new JTextField(); 
	    	saisie.setColumns(120);
	    	saisie.setSize(475,80);
	    	saisie.setLocation(3,320);
	    	MessageEnvoyer= new JTextField(); 
	    	MessageEnvoyer.setColumns(120);
	    	MessageEnvoyer.setSize(400,300);
	    	MessageEnvoyer.setLocation(3,3);
	    	MessageEnvoyer.setText(AM.FindMessage(Utilisateur1,Utilisateur2));
	    	Envoyer.setSize(150,25);
	    	Envoyer.setLocation(165,415);
	    	this.add(MessageEnvoyer);
	    	this.add(saisie);
	    	this.add(Envoyer);
	        this.setSize(500,500);
	        this.setVisible(true);
	    	//garder une trace du  nom de l'utilisateur 
			NomComptePersSend= new JLabel(Utilisateur1.getNomComptePers(),JLabel.CENTER);
			NomComptePersSend.setVisible(false);
			NomComptePersReceive= new JLabel(Utilisateur2.getNomComptePers(),JLabel.CENTER);
			NomComptePersReceive.setVisible(false);
			MessageEnvoyerSucces= new JLabel("Message envoyé avec succes",JLabel.CENTER);
			MessageEnvoyerSucces.setForeground(Color.green);
			
			
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
			AmisMapper AM = new AmisMapper();
			PersonneMapper PM = new PersonneMapper();
			if (e.getActionCommand().equals("Envoyer")){
				try {
					AM.UpdateMessage(PM.FindByComptePers(NomComptePersSend.getText()),
							PM.FindByComptePers(NomComptePersReceive.getText()),saisie.getText());
					MessageEnvoyer.setText(MessageEnvoyerSucces.getText()+" : "+saisie.getText());
					saisie.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}

				}
		
	    }
		
	


