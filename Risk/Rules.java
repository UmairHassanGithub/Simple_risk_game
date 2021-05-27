package Risk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.*;
 
import javax.swing.*;
 
public class Rules extends JFrame{
	
	
	public static void Rules(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Rules().setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	
	public Rules() throws IOException{
		//get file from the folder
		File theDir = new File(System.getProperty("user.home")+"\\Desktop\\Risk Files\\Rules.txt"); 
		//file exists
		if(theDir.exists()){
 

			    
			    JPanel MainPanel = new JPanel(new GridBagLayout());
			    
			    //Sets up Strings to be placed into a array
			    JLabel label1 = new JLabel();
			    String[] lines=new String[10];
			    String str;
			    //reads the file 
			    FileReader in=new FileReader(theDir);
			    BufferedReader readFile=new BufferedReader(in);
			    int x=0;
			    //As long as there is lines to be read, it adds lines
				while ((str=readFile.readLine()) !=null){
					lines[x]=str;x++;
				}
				readFile.close();
				in.close();
				GridBagConstraints Location = new GridBagConstraints();
				
				//Sets text of all labels
			    label1.setText("<html><h1>"+lines[0]+"</h1></html>");
			    
			    JLabel label2 = new JLabel();
			    label2.setText("<html><h2>"+lines[1]+"</h2></html>");
			    
			    JLabel label3 = new JLabel();
			    label3.setText("<html><h2>"+lines[2]+"</h2></html>");
			    
			    JLabel label4 = new JLabel();
			    label4.setText("<html><h2>"+lines[3]+"</h2></html>");
			    
			    JLabel label5 = new JLabel();
			    label5.setText("<html><h2>"+lines[4]+"</h2></html>");
			   
			    JLabel label6 = new JLabel();
			    label6.setText("<html><h2>"+lines[5]+"</h2></html>");
			    
			    JLabel label7 = new JLabel();
			    label7.setText("<html><h2>"+lines[6]+"</h2></html>");
			    
			    JLabel label8 = new JLabel();
			    label8.setText("<html><h2>"+lines[7]+"</h2></html>");
			    
			    JLabel label9 = new JLabel();
			    label9.setText("<html><h2>"+lines[8]+"</h2></html>");
			    
			    JLabel label10 = new JLabel();
			    label10.setText("<html><h2>"+lines[9]+"</h2></html>");
			    //Location of the labels and adds them to the panel
			    Location.gridx = 0;
			    Location.gridy = 0;
			    MainPanel.add(label1, Location);
			    Location.gridx = 0;
			    Location.gridy = 1;
			    MainPanel.add(label2, Location);
			    Location.gridx = 0;
			    Location.gridy = 2;
			    MainPanel.add(label3, Location);
			    Location.gridx = 0;
			    Location.gridy = 3;
			    MainPanel.add(label4, Location);
			    Location.gridx = 0;
			    Location.gridy = 4;
			    MainPanel.add(label5, Location);
			    Location.gridx = 0;
			    Location.gridy = 5;
			    MainPanel.add(label6, Location);
			    Location.gridx = 0;
			    Location.gridy = 6;
			    MainPanel.add(label7, Location);
			    Location.gridx = 0;
			    Location.gridy = 7;
			    MainPanel.add(label8, Location);
			    Location.gridx = 0;
			    Location.gridy = 8;
			    MainPanel.add(label9, Location);
			    Location.gridx = 0;
			    Location.gridy = 9;
			    MainPanel.add(label10, Location);
			    
			    
			    //Manipluates the elements into proper order/ Changing the JFrame around
			    add(MainPanel);
			    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			    setLocationRelativeTo(null);
			    pack();
			    setSize(1000,500);
			    setResizable(false);
			    
			    setVisible(true);
			    
			    setTitle("RiskLegacy");
 
			
		}
		//if file does not exit
		else{
			//tells the user that the file does not exist or cannot be found
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		    setSize(100,600);
		    setLocationRelativeTo(null);
		    
		    JPanel MainPanel = new JPanel(new GridBagLayout());
		    
		    
		    JLabel label1 = new JLabel();
		    GridBagConstraints Location = new GridBagConstraints();
			
		    label1.setText("<html><h1>Sorry could not find the file.</h1></html>");
		    JLabel label2 = new JLabel();
		    label2.setText("<html><h1>Try downloading the rules file again.</h1></html>");
		    Location.gridx = 0;
		    Location.gridy = 0;
		    MainPanel.add(label1, Location);
		    Location.gridx = 0;
		    Location.gridy = 1;
		    MainPanel.add(label2, Location);
		    add(MainPanel);
 
		    setVisible(true);
		}
		
	
	}
	
 
}
