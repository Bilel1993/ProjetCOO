package Presentation.PageUtilisateur;
 import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;


@SuppressWarnings({ "serial", "unused" })
public class ChatUtilisateur extends JPanel implements ActionListener  { 
	    String message;
	    
	    JTextField saisie;
	    JTextField MessageEnvoyer;
	    JButton Envoyer = new JButton("Envoyer");
        

	    public ChatUtilisateur() throws Exception { 
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
	    	Envoyer.setSize(150,25);
	    	Envoyer.setLocation(165,415);
	    	this.add(MessageEnvoyer);
	    	this.add(saisie);
	    	this.add(Envoyer);
	        this.setSize(500,500);
	        this.setVisible(true);
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
		
			if (e.getActionCommand().equals("Envoyer")){
				MessageEnvoyer.setText(saisie.getText());
				saisie.setText("");
			}

				}
		
	    }
		
	


