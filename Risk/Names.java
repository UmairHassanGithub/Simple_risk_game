package Risk;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
 
 
public class Names  extends JFrame{
	//Initalizes 
	private JTextField fieldName, fieldName2;
	private JButton buttonSubmit, buttonExit, buttonBack;
	private JLabel label, label2, space,sapcelabel;
	private ImageIcon img;
	private JLabel ulabel;
	Names(){
		create();
		setTitle("RiskLegacy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		setSize(1000,600);
		setLocationRelativeTo(null);
		
	}
	private void create(){
		
		
		 JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setBackground(Color.GRAY);
		
		//Creates JLabels asking for names and their text fields to be filled out
		label = new JLabel("Please enter First player's name");
		panel.add(label);
		
		fieldName = new JTextField();
		fieldName.setPreferredSize(new Dimension (150,30));
		panel.add(fieldName);
		
		
		label2 = new JLabel("Please enter Second player's name");
		panel.add(label2);
		fieldName2 = new JTextField();
		fieldName2.setPreferredSize(new Dimension (150,30));
		panel.add(fieldName2);
		//Submit Button when they are done
		buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//When both fields have names in then, it will open the map (Currently game)
				String name1 = fieldName.getText();
				String name2 = fieldName2.getText();
				int x = 0;
				//if missing one or the other, it will tell user there is no name
				if(name1.isEmpty()){
					label.setText("There is a missing name!");
				}else{
					label.setText("That is a good name!");
					x ++;
					
				}
				if(name2.isEmpty()){
					label2.setText("There is a missing name!");
				}else{
					label2.setText("That is a good name!");
					x ++;
				}
				if(x == 2){
					dispose();
					new Map(name1, name2).setVisible(true);
				}
			}
		});
		panel.add(buttonSubmit);
		//If they wish to close the program
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				RUSure.Exit();
			}
		});
		panel.add(buttonExit);
		//Wants to go back to the main page
		buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new StartMenu().setVisible(true);
				dispose();
			}
		});
		panel.add(buttonBack);
		//Cool images
		img= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/game2.jpg");
		ulabel=new JLabel(img);
		ulabel.setPreferredSize(new Dimension(1100, 560));
		panel.add(ulabel);
		
	}
	public static void SetName(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Names().setVisible(true);
				
				
			}
		});
	}
 
}
