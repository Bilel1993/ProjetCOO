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
import Persistance.GroupeChatMapper;
import Persistance.PersonneMapper;

import java.awt.Color;


@SuppressWarnings({ "serial", "unused" })
public class ChatGroupe extends JPanel implements ActionListener  { 
	    String message;
	    
	    JTextField saisie;
	    JTextField MessageEnvoyer;
	    JButton Envoyer = new JButton("Envoyer");
	    
	    //JLabel avec le Nom Compte Pers 
		JLabel NomCompteUtil;
		JLabel NomGroupe;
		
		//JLabel Message Envoyer
		JLabel MessageEnvoyerSucces;
       

	    public ChatGroupe(Personne Utilisateur,String nomGroupe) throws Exception { 
	    	GroupeChatMapper GCM = new GroupeChatMapper();
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
	    	MessageEnvoyer.setText(GCM.FindMessage(nomGroupe,Utilisateur));
	    	Envoyer.setSize(150,25);
	    	Envoyer.setLocation(165,415);
	    	this.add(MessageEnvoyer);
	    	this.add(saisie);
	    	this.add(Envoyer);
	        this.setSize(500,500);
	        this.setVisible(true);
	    	//garder une trace du  nom de l'utilisateur 
			NomCompteUtil= new JLabel(Utilisateur.getNomComptePers(),JLabel.CENTER);
			NomCompteUtil.setVisible(false);
			NomGroupe= new JLabel(nomGroupe,JLabel.CENTER);
			NomGroupe.setVisible(false);
			MessageEnvoyerSucces= new JLabel("Message envoyé avec succes",JLabel.CENTER);
			MessageEnvoyerSucces.setForeground(Color.green);
			
			
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
	    	GroupeChatMapper GCM = new GroupeChatMapper();
			PersonneMapper PM = new PersonneMapper();
			if (e.getActionCommand().equals("Envoyer")){
				try {
					GCM.UpdateMessage(NomGroupe.getText(),PM.FindByComptePers(NomCompteUtil.getText()),
							saisie.getText());
					MessageEnvoyer.setText(MessageEnvoyerSucces.getText()+" : "+saisie.getText());
					saisie.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}

				}
		
	    }
		

