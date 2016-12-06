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
public class ConnectionAdmin extends JFrame implements ActionListener  { 
	Inscription im;
	Chat chat;
    JPanel PAdmin ;   
    JButton Creation = new JButton("Créer un compte");
    JButton Chat = new JButton("Passer en Mode Chat");
    

		    public ConnectionAdmin() throws Exception { 
		    	Creation.addActionListener(this);
		    	Chat.addActionListener(this);
		    	PAdmin= new JPanel(new FlowLayout());
		    	PAdmin.setOpaque(true);
		    	PAdmin.setLayout(null);

		    	PAdmin.setBackground(Color.LIGHT_GRAY);	
		    	Creation.setSize(150,25);
		    	Creation.setLocation(60,110);
		    	Chat.setSize(170,25);
		    	Chat.setLocation(260,110);
		    	PAdmin.add(Chat);
		    	PAdmin.add(Creation);
		        this.setSize(500,500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        this.setContentPane(PAdmin);
		        //this.pack();
		        this.setVisible(true);
		    }; 
		    
		   
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Créer un compte"))
					try {
						im= new Inscription();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				if (e.getActionCommand().equals("Passer en Mode Chat"))
					try {
						chat= new Chat();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			
			}
			
			public static void main(String[] args) throws Exception {
				ConnectionAdmin connectAdmin = new ConnectionAdmin();
			}
}

			
		



