package Risk;

	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
	 
	public class StartMenu extends JFrame{
		private JButton buttonNew, buttonLoad, buttonScore, buttonRules;
		private JLabel label;
	 
		public StartMenu() {
			// Method that creates UI elements
			create();
			setTitle("RiskLegacy");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setSize(1000,600);
			setLocationRelativeTo(null);
		}
		private ImageIcon img;
		private JLabel ulabel;
		private void create() {
			//Creates and adds Panels
			JPanel panel = new JPanel();
			getContentPane().add(panel);
			panel.setBackground(Color.GRAY);
			//Creates and places Labels inside the panel
			label = new JLabel();
			label.setText("Welcome!");
			//Makes it a fancy font!
			Font font = new Font("Verdana", Font.BOLD, 20);
			label.setFont(font);
			label.setForeground(new Color(153,0,0));
			label.setPreferredSize(new Dimension(150, 40));
			panel.add(label);
			
			//Creates a buttons -> Creates a new game
			buttonNew = new JButton("New Game");
			buttonNew.addActionListener(new buttonNewActionListener());
			panel.add(buttonNew);
			
			//Creats a button -> Pops up rules
			buttonRules = new JButton("Rules");
			buttonRules.addActionListener(new buttonRulesActionListener());
			panel.add(buttonRules);
			
			//Creates a button -> that will popup credits (not done)
			buttonScore = new JButton("Score");
			buttonScore.addActionListener(new buttonScoreActionListener());
			panel.add(buttonScore);
			
			// location of image (Need to change to final fonlder)
			img= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/game.jpg");
			//adds a imge to a Jlabel
			ulabel=new JLabel(img);
			ulabel.setPreferredSize(new Dimension(1000, 522));
			panel.add(ulabel);
			
		}
	 
	 //Main method which starts the chain, makes JFrame visible
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new StartMenu().setVisible(true);
				}
			});
		}
		//Button Listener, opens names and disposes This one
		private class buttonNewActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				dispose();
				Names.SetName();
			}
		}
		//Button Listener, Will open score frame
		private class buttonScoreActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				DisplayScore d = null;
				try {
					d = new DisplayScore();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        try {
					d.createAndShowGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		}
		//Button Listener, opens the rules Frame
		private class buttonRulesActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Rules.Rules();
			}
		}
		
	}

