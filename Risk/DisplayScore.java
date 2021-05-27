package Risk;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DisplayScore extends JPanel {
	/** infile is what's in the file line by line
	 ** file is for just the important parts
	 ** sort is a clone of file, needed for sorting the list
	 ** for all 2d arrays: [x][0] is names, [x][1] is wins, [x][2] is losses, [x][3] is win streak
	**/
	private static String[][] file=new String [100][4];
	private static String[][] infile=new String [100][4];
	private static String [][] sort=file.clone();

	public DisplayScore() throws IOException {
	  super(new GridLayout(1, 1));
         
	//this whole section is used to obtain the icon and get the score from the file.
	  int y=0;
	  ImageIcon icon = new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/Cat.gif"); // change this, icon is a cat gif that is mainly used to space out the tabs, and it looks nice.
			File score = new File(System.getProperty("user.home")+"\\Desktop\\Risk Files\\Dice Legacy Score.txt"); // Dice Legacy Score file location
			for(int n=0;n<100;n++){
				file[n][0]="-1";	//This is used to make user there is no null errors further down.
			}
			if (score.exists()){	// if the file exists it reads and saves it into infile.
				int x=0;
				BufferedReader bin = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Desktop\\Risk Files\\Dice Legacy Score.txt"));	// change this
				int check=0;
				
				do{
					String str=bin.readLine();		// stops reading as it hits a empty line in the file
					if(str!=(null)){
						infile[y][x]=(str);
					x++;
					if(x==4){	// every 4 lines it switches people 
						y++;
						x=0;
					}
					}
					else{check=1;}
				}while(check==0);
				bin.close();
				for (int t = 0; t <y ; t++){	//puts only the important parts from infile to file
					file[t][0]=infile[t][0].replaceAll("Name: ","");
					file[t][1]=infile[t][1].replaceAll("Wins: ","");;
					file[t][2]=infile[t][2].replaceAll("lost: ","");;
					file[t][3]=infile[t][3].replaceAll("Win Streak: ","");;
					}
			}
			else{			//if the file doesn't exist a dialog box opens up to explain why there is no score.
				JFrame frame=new JFrame();
				//BTW I don't know why you have to click the OK button 2 times, and i can't fix it.
				JOptionPane.showMessageDialog(frame,
					    "There is no score, no one has won or lost yet.",
					    "Come back later",
					    JOptionPane.INFORMATION_MESSAGE,
					    icon);
			}
			//For sorting the people by number of wins.
			sort=file.clone();
			double[] sort2=new double[100];
			for(int g=0;g<y;g++){
				sort2[g]=Integer.parseInt(sort[g][1]);
			}
			BubbleSort(sort2,y);
			//Adds the top 10 people into a string with a sentence.
			String [] w=new String[10];
			for(int u=0;u<10;u++){
				w[u]=(u+1)+". "+sort[u][0]+": "+sort2[u]+" Wins";
			}
			//For sorting the people by win rate.
			sort=file.clone();
			for(int g=0;g<y;g++){
				if(sort[g][2].equals("0")){ // Can't divide by 0.
				sort2[g]=100;	//since the losses is 0 the score is 100% win rate.
				}
				else{
					int a=Integer.parseInt(sort[g][1]);// wins
					int b=Integer.parseInt(sort[g][2]);// losses
					int c=a+b;							// total games
					sort2[g]=100*a/c; // wins divided by total games times 100 for percentage.
				}
			}
			BubbleSort(sort2,y);
			//Adds the top 10 people into a string with a sentence.
			String [] r=new String[10];
			for(int u=0;u<10;u++){
				r[u]=(u+1)+". "+sort[u][0]+": "+sort2[u]+"% Win Rate";
			}
			//For sorting the people by win streak.
			sort=file.clone();
			for(int g=0;g<y;g++){
				sort2[g]=Integer.parseInt(sort[g][3]);	
			}
			BubbleSort(sort2,y);
			//Adds the top 10 people into a string with a sentence.
			String [] s=new String[10];
			for(int u=0;u<10;u++){
				s[u]=(u+1)+". "+sort[u][0]+": "+sort2[u]+" Win Streak";
			}
			// if there aren't 10 people who have played the game it will say none, this is where the "-1" comes in.
			for(int m=0;m<10;m++){
				if(sort[m][0].equals("-1")){
					 w[m]="none";
					 r[m]="none";
					 s[m]="none";
				}
			}
			// Setting up the layout of each of the panels.
			GridBagConstraints c = new GridBagConstraints();
			c.weighty = 1;
			Font font = new Font("Verdana", Font.BOLD, 20);	// Just sets the font of all the text in the labels.
	    JLabel labels[] = new JLabel[10]; // These 10 labels are resets and used for each panel.
        JTabbedPane tabbedPane = new JTabbedPane(); 
        JPanel panel1 = new JPanel(new GridBagLayout()); //GridBagLayout is applied to the panel not the tabs.
        //Sets the top 10 people from the appropriate string array.
        for(int x=0;x<10;x++){
        	c.gridx = 0;
		    c.gridy = x;
			labels[x] = new JLabel();
			labels[x].setText(w[x]);
			labels[x].setFont(font);
			labels[x].setForeground(new Color(0,128,255));	//This color is a shade of blue.
			panel1.add(labels[x],c);
		}
        tabbedPane.addTab("Most Wins", icon, panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1); 
        JPanel panel2 = new JPanel(new GridBagLayout());
        //Sets the top 10 people from the appropriate string array.
        for(int x=0;x<10;x++){
        	c.gridx = 0;
		    c.gridy = x;
			labels[x] = new JLabel();
			labels[x].setText(r[x]);
			labels[x].setFont(font);
			labels[x].setForeground(new Color(0,128,255));	//This color is a shade of blue.
			panel2.add(labels[x],c);
		}
        tabbedPane.addTab("Best Win Rate", icon, panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        JPanel panel3 = new JPanel(new GridBagLayout());
        //Sets the top 10 people from the appropriate string array.
        for(int x=0;x<10;x++){
        	c.gridx = 0;
		    c.gridy = x;
			labels[x] = new JLabel();
			labels[x].setText(s[x]);
			labels[x].setFont(font);
			labels[x].setForeground(new Color(0,128,255));	//This color is a shade of blue.
			panel3.add(labels[x],c);
		}
        tabbedPane.addTab("Top Consecutive Wins", icon, panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        tabbedPane.setPreferredSize(new Dimension(500, 700)); //Sets the size of the tabs which also changes the size of the whole frame.
        //Add the tabbed pane to the frame.
        add(tabbedPane);
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
     //Create the GUI and show it, its in a separate method because I started with the tabs first.
    void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Score Board");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Add content to the window.
        frame.add(new DisplayScore(), BorderLayout.CENTER);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    //A edited BubbleSort, I like easy and simple sorting algorithms. 
    public static void BubbleSort( double [] num,int y){
         int j;
         boolean flag = true;   // set flag to true to begin first pass
         double temp;				//holding variable
         String [] temp2=new String[4];; 		//holding variables	
         while (flag){
                flag=false;    //set flag to false awaiting a possible swap
                for(j=0;j<y;j++){
                       if (num[j] < num[j+1]) {		// change to > for ascending sort
                               temp = num[j];            //swaps elements 
                               temp2[0] = sort[j][0];
                               temp2[1] = sort[j][1];
                               temp2[2] = sort[j][2];
                               temp2[3] = sort[j][3];
                               num[j] = num[j+1];
                               sort[j][0]=sort[j+1][0];
                               sort[j][1]=sort[j+1][1];
                               sort[j][2]=sort[j+1][2];
                               sort[j][3]=sort[j+1][3];
                               num[j+1] = temp;
                               sort[j+1][0]=temp2[0];
                               sort[j+1][1]=temp2[1];
                               sort[j+1][2]=temp2[2];
                               sort[j+1][3]=temp2[3];
                              flag = true;              //shows a swap occurred  
                      }
                }
          }
    }
}
