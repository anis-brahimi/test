package display;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.types.Node;

import neo4j0.Test;

public class Display implements ActionListener {
	
	private JFrame frame = new JFrame("Application sante publique");
	private JButton[] button ;
	private JButton[] inscription ;
	private Test controle ;
	CardLayout layout = new CardLayout();
	JTextField text1 = new JTextField(20);
	JTextField text2 = new JTextField(20);
	JLabel label[] ;
	private boolean cond = false;
	private boolean first = true;
	private String nom ;
	private String prenom ;
	private Node noeudIndi ;
	private List<Record> noeudEquiSport ;
	private List<Record> noeudEspaceVert ;
	private List<Record> noeudEquiCulturel ;
	
	
	
	
	JPanel component0= new JPanel() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			
			
		}
	};
		
	JPanel component1 = new JPanel() {
			
			List<Record> list ;
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
			
				
				
				
			}
		};
		
	JPanel component2 = new JPanel() {
		
		protected void paintComponent(Graphics g) {
			JLabel[] labelSport = new JLabel[noeudEquiSport.size()];
			JLabel[] lab = new JLabel[noeudEquiSport.size()];
			
			super.paintComponent(g);
			this.setBackground(Color.yellow);
			/*if(cond ) {
				
				
				for(int i =0; i<noeudEquiSport.size();i++) {
			    	 Iterator it =  noeudEquiSport.get(i).get(0).asNode().values().iterator();
			    		labelSport[i] = new JLabel(noeudEquiSport.get(i).get(0).asNode().get("Name").asString());
			    		lab[i] = new JLabel("\n");
			    		this.add(labelSport[i]);
			    		this.add(lab[i]); 
			    		}
			    			   
			    		cond = false ;
			    		
			    	  }*/
			    	  
			    	}
			
	
		};
		
		
	JPanel component3 = new JPanel() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setBackground(Color.red);
			
			
		}
		
	};
	
	JPanel component4 = new JPanel() {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		this.setBackground(Color.magenta);
		}
	};
	JPanel pane = new JPanel(layout) {
		
	};
		
	

	
	public Display(Test controle) {
		
		this.controle = controle ;
		Color myBlue = new Color(199, 168, 132);
		
		frame.add(component0,BorderLayout.CENTER);
		frame.setSize(1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setFocusable(true);
		
		
		component0.setBackground(myBlue);
		component0.setLayout(null);
		component1.setLayout(null);
		component1.setBackground(myBlue);
		
		pane.setBackground(Color.red);
		pane.add(component2, "1");
		pane.add(component3, "2");
		pane.add(component4,"3");
		
		
		
		
		label = new JLabel[5];
		button = new JButton[5];
		
		label[0]= new JLabel("Entrer votre Nom       :");
		label[1]= new JLabel("Entrer votre Prenom :");
		
		button[0]= new JButton("Envoyer");
		button[1]= new JButton("Retour");
		button[2] =new JButton("Visualiser les centres sportifs");
		button[3] = new JButton("Visualiser les centres culturels");
		button[4] = new JButton("Visualiser les Espaces verts") ;
		
		component0.add(label[0]);
		component0.add(text1);
		component0.add(label[1]);
		component0.add(text2);
		component0.add(button[0]);
		component1.add(button[1]);
		component1.add(button[2]);
		component1.add(button[3]);
		component1.add(button[4]);
		
		button[0].addActionListener(this);
		button[1].addActionListener(this);
		button[2].addActionListener(this);
		button[3].addActionListener(this);
		button[4].addActionListener(this);
		
		
		
	    label[0].setBounds(10, 10,140,20);
	    text1.setBounds(150, 10, 120, 20);
	    button[0].setBounds(300,25, 80, 20);
	    label[1].setBounds(10, 40, 140, 20);
	    text2.setBounds(150, 40, 120, 20);
	    button[1].setBounds(810, 700, 80, 40);
	    
	    button[2].setBounds(10,10, 250, 40);
	    button[3].setBounds(10,100, 250, 40);
	    button[4].setBounds(10,200, 250, 40);
	    
	    layout.show(pane, "0");
	   
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0]) {
			
			if(exist(text1.getText(),text2.getText(),"MATCH (n:Individus) RETURN n"))
			{    
				 nom = text1.getText();
			     prenom = text2.getText();
			     component0.setVisible(false);
			     component1.setVisible(true);
			     pane.setVisible(true);
			     component1.setPreferredSize(new Dimension(300,1000));
			     pane.setPreferredSize(new Dimension(700,1000));
				 frame.add(component1, BorderLayout.EAST);
				 frame.add(pane, BorderLayout.WEST);
				 frame.repaint();
				 frame.revalidate();
			}
			
		}
		else if(e.getSource()==button[1]) {
			component0.setVisible(true);
			component1.setVisible(false);
		    pane.setVisible(false);
			frame.repaint();
			frame.revalidate();
		}
		else if(e.getSource()==button[2]) {
			 StringBuilder str = new StringBuilder(); 
			 str.append("MATCH (a:Individus),(b:equipement_sport) where a.code_postal= b.code_postal AND a.Prenom = \"");
			 str.append(prenom);
			 str.append("\" Return b");
			 noeudEquiSport = controle.printRead(str.toString());
			 if(!noeudEquiSport.isEmpty()) {
				 cond =true ;
			 }
				 
			 component2.repaint();
			 layout.show(pane, "1");
		     
		}
		else if(e.getSource()== button[3]) {
			component3.repaint();
			layout.show(pane, "2");
		}
		else if(e.getSource()== button[4]) {
			component3.repaint();
			layout.show(pane, "3");
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
			
		public boolean exist(String nom, String prenom, String statement)
		{   
			List<Record> list = controle.printRead(statement);
		
			for(int i =0; i<list.size();i++) {
		    	 Iterator it =  list.get(i).get(0).asNode().values().iterator();
		    	  while(it.hasNext()) {
		    		if(prenom.equals(list.get(i).get(0).asNode().get("Prenom").asString())&& nom.equals(list.get(i).get(0).asNode().get("Nom").asString())) {
		    			noeudIndi = list.get(i).get(0).asNode();
		    			return true ;
		    			
		    		}
		    			   
		    		 it.next();
		    	  }
		    	  
		    	}
			return false ;

			
		}
		

}

















