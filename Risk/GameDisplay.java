package Risk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameDisplay extends JFrame {
	private JPanel PanelGrid[][] = new JPanel[7][7];
	private JLabel LabelGrid[][] = new JLabel[7][7];
	private JLabel LabelNameGrid[][] = new JLabel[7][7];
	private JButton ButtonGrid[][] = new JButton[7][7];
	private int clicked[][] = new int[7][7];
	private int cordX, cordY;
	private int MapType = 1;
	private Game Game = new Game();
	private Color color = new Color(249, 84, 84);
	private Color color2 = new Color(67, 149, 239);
	private String Name1 = "Jordon";
	private String Name2 = "Bob";
	private int click = 0;


	public GameDisplay() {
		// Method that creates UI elements
		create();
		setLayout(new GridLayout(7, 7));
		setTitle("RiskLegacy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1100,900);
		setLocationRelativeTo(null);
	}

	public GameDisplay(String Hi, String Bye, int map) {
		Name1 = Hi;
		Name2 = Bye;
		MapType = map;
		Game.Open(MapType);
		// Method that creates UI elements
		Game.SetNames(Name1, Name2);
		create();
		setLayout(new GridLayout(7, 7));
		setTitle("RiskLegacy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1100,900);
		setLocationRelativeTo(null);
	}

	private void create() {
		//Creates the Grid
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				final int i = x;
				final int o = y;
				clicked[x][y] = 0;
				PanelGrid[x][y] = new JPanel(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				getContentPane().add(PanelGrid[x][y]);
				LabelGrid[x][y] = new JLabel();

				ButtonGrid[x][y] = new JButton(String.valueOf(x + 1) + " , "
						+ String.valueOf(y + 1));
				ButtonGrid[x][y].setPreferredSize(new Dimension(60, 30));

				LabelNameGrid[x][y] = new JLabel();
				ButtonGrid[x][y].addActionListener(new ActionListener() {
					@Override
					//Buttons for each tile on the grid, and their actions 
					public void actionPerformed(ActionEvent e) {
						cordX = i;
						cordY = o;
						//Determines which is clicked
						if (click == 0) {
							//Checks if the tile is the right player's
							if (Game.getTrun() == Game.GetSide(cordX, cordY)) {
								//If it is than turn it black and pass X, Y to Game
								LabelGrid[cordX][cordY].setText("Selected");
								PanelGrid[cordX][cordY]
										.setBackground(Color.BLACK);
								clicked[cordX][cordY] = 1;
								Game.SetSelected(cordX, cordY);
								click++;
							} else {
								//Else do not select and tell the players
								Game.AddText("That's not yours!");
							}
						} else if (click == 1) {
							//If cliked same tile, than deselect and erase X and Y
							if (clicked[cordX][cordY] == 1) {
								GridPaint(cordX, cordY);
								clicked[cordX][cordY] = 0;
								Game.SetSelected(-1, -1);
								click--;
							}
							//If New Tile, Check if it's a valid targe
							else if (clicked[cordX][cordY] == 0) {
								Game.SetTarget(cordX, cordY);
								if (Game.Adj() == 1) {
									clicked[cordX][cordY] = 1;
									click++;
								} else {
									Game.AddText("That's not a valid target");
									Game.SetTarget(-1, -1);
								}
							}
						}
						if (click == 2) {
							//if both targes are good than action will happen -> calss game to find out more
							Game.Action();
							click = 0;
							for (int x = 0; x < 7; x++) {
								for (int y = 0; y < 7; y++) {

									clicked[x][y] = 0;
									if (Game.GetMap(x, y) == 0) {
										GridPaint(x, y);
									} else if (Game.GetMap(x, y) == 1) {
										WaterPaint(x, y);
									}

								}
							}
						}

					}
				});
				//Right colour, text on the tile

				if (Game.GetMap(x, y) == 0) {
					//If it's a land tile
					GridPaint(x, y);
				} else if (Game.GetMap(x, y) == 1) {
					//If it's a water tile
					WaterPaint(x, y);
				}

				c.gridx = 0;
				c.gridy = 1;
				PanelGrid[x][y].add(LabelGrid[x][y], c);
				if(Game.GetMap(x, y) == 0){
				c.gridx = 0;
				c.gridy = 2;
				PanelGrid[x][y].add(ButtonGrid[x][y], c);
				}
				c.gridx = 0;
				c.gridy = 0;
				PanelGrid[x][y].add(LabelNameGrid[x][y], c);
			}
		}
	}

	private void WaterPaint(int x, int y) {
		//If tile is water Via the array Map[][][]
		LabelGrid[x][y].setText("~~~~~~~~~~~~~~~~~");
		LabelNameGrid[x][y].setText("IT's Water! and it's deadly");
		PanelGrid[x][y].setBackground(Color.BLUE);
	}

	private void GridPaint(int x, int y) {
		//If tile is land Via the array Map[][][]
		LabelGrid[x][y].setText(String.valueOf((int)Game.GetSol(x, y))+" Soldier");
		Font font = new Font("Verdana", Font.BOLD, 14);
		LabelGrid[x][y].setFont(font);
		if (Game.GetSide(x, y) == 0) {
			LabelNameGrid[x][y].setText(Name1);
			PanelGrid[x][y].setBackground(color);
		} else if (Game.GetSide(x, y) == 1) {
			LabelNameGrid[x][y].setText(Name2);
			PanelGrid[x][y].setBackground(color2);
		}
	}
}
