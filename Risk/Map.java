package Risk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Map extends JFrame{
	private String name1, name2;
	private JLabel label, labelMap;
	private JButton buttonNext, buttonBack, buttonSubmit;
	private ImageIcon img, img2, img3, img4, img5;
	private JLabel ulabel;
	int x = 0;
	
	public Map(String hi, String bye) {
		name1 = hi;
		name2 = bye;
		// Method that creates UI elements
		create();
		setTitle("RiskLegacy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1300,1050);
		setLocationRelativeTo(null);
	}
	private void create() {
		
		//GridBagConstriant Format
		GridBagConstraints c = new GridBagConstraints();
		
		
		//Creates and adds Panels
		JPanel panel = new JPanel(new GridBagLayout());
		getContentPane().add(panel);
		panel.setBackground(Color.GRAY);
		//Creates and places Labels inside the panel
		label = new JLabel();
		label.setText("Please Pick a map");
		//Makes it a fancy font!
		Font font = new Font("Verdana", Font.BOLD, 20);
		label.setFont(font);
		label.setForeground(new Color(153,0,0));
		label.setPreferredSize(new Dimension(300, 40));
		panel.add(label);
		
		//Creates a buttons -> Creates a new game
		buttonNext = new JButton("Next");
		buttonNext.addActionListener(new buttonNextActionListener());
		
		
		//Creates a button -> Does nothing right now (Prob going to be replaced)
		buttonBack = new JButton("Back");
		buttonBack.addActionListener(new buttonBackActionListener());

		
		//Creates a button -> that will popup credits (not done)
		buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new buttonSubmitActionListener());
	
		
		// location of image (Need to change to final fonlder)
		img= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/MapPic/AllLand.png");
		img2= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/MapPic/TwoKingDom.png");
		img3= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/MapPic/FourKingDom.png");
		img4= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/MapPic/SmallDoughNut.png");
		img5= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/MapPic/LargeDoughNut.png");
		//adds a imge to a Jlabel
		ulabel=new JLabel();
		ulabel.setIcon(img);
		ulabel.setPreferredSize(new Dimension(1100, 875));
		
		
		c.gridx = 0;
	    c.gridy = 0;
	    panel.add(label,c);
	    c.gridx = 0;
	    c.gridy = 1;
		panel.add(ulabel,c);
		//
		 c.fill = GridBagConstraints.HORIZONTAL;
		 c.insets = new Insets(0,0,0,0);
		 c.weightx = 0.5; 
		//Buttons after img
		JPanel Inpanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
	    c.gridy = 2;
		panel.add(Inpanel,c);
		c.gridx = 0;
	    c.gridy = 0;
		Inpanel.add(buttonBack,c);
		c.gridx = 1;
	    c.gridy = 0;
		Inpanel.add(buttonNext,c);
		c.gridx = 2;
	    c.gridy = 0;
		Inpanel.add(buttonSubmit,c);
		
	}
	public void changeMap(int op){
		
		x += op;
		if(x > 4){
			x = 0;
		}else if(x < 0){
			x = 4;
		}

		if(x == 0){
			ulabel.setIcon(img);
		}else if(x == 1){
			ulabel.setIcon(img2);
		}else if(x == 2){
			ulabel.setIcon(img3);
		}else if(x == 3){
			ulabel.setIcon(img4);
		}else if(x == 4){
			ulabel.setIcon(img5);
		}
	}
	
	
	private class buttonNextActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			changeMap(1);
		}
	}
	private class buttonBackActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			changeMap(-1);
		}
	}
	private class buttonSubmitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
			new GameDisplay(name1, name2, x).setVisible(true);
		}
	}
}
