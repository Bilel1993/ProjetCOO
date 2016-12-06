package Presentation;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;


@SuppressWarnings({ "serial", "unused" })
public class Accueil extends JFrame implements ActionListener  { 
	Connexion  con;
	static String nom;
    JPanel PAcceuil ;  
    JButton connexion = new JButton("Connexion");
        

	    public Accueil() throws Exception { 
	    	connexion.addActionListener(this);
	    	PAcceuil= new JPanel(new FlowLayout());
	    	PAcceuil.setOpaque(true);
	    	PAcceuil.setLayout(null);

	    	PAcceuil.setBackground(Color.LIGHT_GRAY);	
	    	connexion.setSize(150,25);
	    	connexion.setLocation(165,170);
	    	PAcceuil .add(connexion);
	        this.setSize(500,500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        this.setContentPane(PAcceuil);
	        //this.pack();
	        this.setVisible(true);
	    }; 
	    
	   
		public void actionPerformed(ActionEvent e) {
		
			if (e.getActionCommand().equals("Connexion"))
				try {
					con= new Connexion();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		public static void main(String[] args) throws Exception {
			Accueil affImage = new Accueil();
		}
	    }
		
	

