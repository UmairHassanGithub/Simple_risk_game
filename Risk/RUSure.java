package Risk;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RUSure extends JFrame{
	private JButton buttonYes, buttonNo;
	private JLabel label;
	RUSure(){
		//Creates the all compents for JFrame
		create();
		//modifys the JFrame
		setTitle("RiskLegacy");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(200,150);
		setLocationRelativeTo(null);
	}
	private void create(){
		

		   
	
			
		//Creates panel to store componets
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints Location = new GridBagConstraints();
		//Layout Location
		getContentPane().add(panel);
	    Location.gridx = 0;
	    Location.gridy = 0;
		label = new JLabel("Are you sure?");
		panel.add(label,Location);
		
		//A button if they wish to close the program
		buttonYes = new JButton("Yes");
		buttonYes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		//Add an inner panel to have better formating 
		JPanel Inpanel = new JPanel(new GridBagLayout());
	    Location.gridx = 0;
	    Location.gridy = 1;
		Inpanel.add(buttonYes,Location);
		panel.add(Inpanel, Location);
		
		//Button if they wish to not close the program
		buttonNo = new JButton("No");
		buttonNo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	    Location.gridx = 1;
	    Location.gridy = 1;
		Inpanel.add(buttonNo,Location);
	}
	public static void Exit(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RUSure().setVisible(true);
				
				
			}
		});
	}
}
