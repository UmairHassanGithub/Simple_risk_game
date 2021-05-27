package Risk;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Victory extends JFrame {
	private ImageIcon img;
	ImageIcon icon = new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/Cat.gif"); // change this, icon is a cat gif that is mainly used to space out the tabs, and it looks nice.
	public Victory(int winner){
		setTitle("Victory");
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		if(winner==1){
		img= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/Player1.png"); // change 
		}
		else{
			img= new ImageIcon(System.getProperty("user.home")+"/Desktop/Risk Files/Player2.png"); //change
		}
		JLabel ulabel=new JLabel(img);
		setSize(1100,818);
		panel.add(ulabel);
		setLocationRelativeTo(null);
		setVisible(true);
		JOptionPane.showMessageDialog(this,
			    "Score has been saved, bye.",
			    "Game Over",
			    JOptionPane.INFORMATION_MESSAGE,
			    icon);
		System.exit(0);
		
	}


}
