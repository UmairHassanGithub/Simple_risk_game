package Risk;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Console extends JFrame {
	public String Command = "-1"; //Used for a check in one of the other classes.
	private static JLabel labels[] = new JLabel[10]; // the console hold 10 labels, which are 10 sentences. 
	public Console() {
		//Changing shouldFill, shouldWeightX, RIGHT_TO_LEFT are fast way to mess around with the look of the console looks like.
		final boolean shouldFill = true;	//Max width.
		final boolean shouldWeightX = true;	//Max height.
		final boolean RIGHT_TO_LEFT = false;	//Goes right to left for the labels.
		setTitle("Console");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel(new GridBagLayout());	// Uses GridBagLayout to space everything out.
		GridBagConstraints c = new GridBagConstraints();
		getContentPane().add(panel);
		panel.setBackground(Color.BLACK);	// I wanted the console to look cool so i made it green text on black, I think it looks nice.
		setSize(450, 500);
		setLocation(15, 150);	//It's off to the left and a little down.
		if (RIGHT_TO_LEFT) {
			panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		if (shouldWeightX) {
			c.weightx = 0.5;	//The weight of how separated the labels on one line are.
		}
		Font font = new Font("Verdana", Font.BOLD, 16);	//Just the font used for the text.
		c.insets = new Insets(0, 0, 0, 0);
		c.weighty = 0.5;	// Spaces out the lines by the height, changing this really messes everything up.
		for (int x = 0; x < 10; x++) {	// Sets up the labels.
			c.gridx = 0;
			c.gridy = x;
			labels[x] = new JLabel();
			labels[x].setText("   ");	//Right now all the labels are set to empty.
			labels[x].setFont(font);
			labels[x].setForeground(new Color(14, 255, 6));	// Green text.
			panel.add(labels[x], c);
		}
		addText("Welcome to Risk Legacy"); // Adds the first line to the console.
		setVisible(true);	//Shows the frame.
	}
	// addText() is used by a lot of classes to display text on the console.
	public static void addText(String text) {
		// Gets a String and moves everything else up, and the top line is removed.
		labels[0].setText(labels[1].getText());
		labels[1].setText(labels[2].getText());
		labels[2].setText(labels[3].getText());
		labels[3].setText(labels[4].getText());
		labels[4].setText(labels[5].getText());
		labels[5].setText(labels[6].getText());
		labels[6].setText(labels[7].getText());
		labels[7].setText(labels[8].getText());
		labels[8].setText(labels[9].getText());
		labels[9].setText(text);
	}
}
