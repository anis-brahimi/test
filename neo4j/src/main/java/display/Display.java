package display;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.neo4j.driver.v1.Record;

import neo4j0.HelloWorldExample;

public class Display implements ActionListener {
	
	private JFrame frame = new JFrame("Application sante publique");
	private JButton[] button ;
	private HelloWorldExample controle ;
	JTextField text1 = new JTextField(20);
	JTextField text2 = new JTextField(20);
	JLabel label[] ;
	private String nom ;
	private String prenom ;
	
	
	
	
	JPanel component= new JPanel() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			
			
		}
		
	};

	
	public Display(HelloWorldExample controle) {
		
		this.controle = controle ;
		frame.add(component,BorderLayout.CENTER);
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.darkGray);
		frame.setVisible(true);
		frame.setFocusable(true);
		component.setLayout(null);
		label = new JLabel[5];
		button = new JButton[5];
		label[0]= new JLabel("Entrer votre Nom       :");
		label[1]= new JLabel("Entrer votre Prenom :");
		button[0]= new JButton("Envoyer");
		button[0].addActionListener(this);
		component.add(label[0]);
		component.add(text1);
		component.add(label[1]);
		component.add(text2);
		component.add(button[0]);
	    label[0].setBounds(10, 10,140,20);
	    text1.setBounds(150, 10, 120, 20);
	    button[0].setBounds(300,25, 80, 20);
	    label[1].setBounds(10, 40, 140, 20);
	    text2.setBounds(150, 40, 120, 20);
	    

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]) {
			nom = text1.getText();
			prenom = text2.getText();
			exist(nom,prenom);
			
		}
		
	}
	private MouseListener mouse = new MouseListener()
			{

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		
			};
			
		public void exist(String nom, String prenom)
		{   
			List<Record> list = controle.printRead();
			for(int i =0; i<list.size();i++) {
		    	 Iterator it =  list.get(i).get(0).asNode().values().iterator();
		    	  while(it.hasNext()) {
		    		if(nom==list.get(i).get(0).asNode().get("Prenom").asString())
		    			   System.out.println("ok");
		    		System.out.println("aaa");
		    	  }
		    	  
		    	}
			System.out.println("ko");

			
		}

}
