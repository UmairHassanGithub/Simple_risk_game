package Risk;

import java.io.IOException;
import java.util.Scanner;


public class Game {
	// size of grid
	final static int max = 7;
	static int[][] Map = new int[max][max];
	// 0 is player 1 and 1 is second
	static double[][][] Dice = new double[max][max][3];
	static Scanner scan = new Scanner(System.in);
	static int selectedX, selectedY;
	static int targetX, targetY;
	static int turn = 0; // 0 is first player, 1 is second
	int MapType;
	int reforcement = 0;
	static String P1Name;
	static String P2Name;
	public static int trans = 0;
	private static Console console = new Console();
	private static Transfer Transfer = new Transfer();

	

	//Sets the names of palyers
	public void SetNames(String Name1, String Name2){
		P1Name = Name1;
		P2Name = Name2; 
	}
	//The inital conditions to start generating the map
	public void Open(int Map){
		MapType = Map;
		SetMap();
		Start();
	}
	//Gets whos turn is it
	public int getTrun() {
		return turn;
	}
	//Gets which sides
	public int GetSide(int x, int y) {
		return (int) Dice[x][y][1];
	}
	//get Num of soliders on tile
	public double GetSol(int x, int y) {
		return (double) Dice[x][y][0];
	}
	//Sets the slected tile
	public void SetSelected(int x, int y) {
		selectedX = x;
		selectedY = y;
	}
	//Sets the target
	public void SetTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}
	//Adds reinforcements depending on number of troops on tile
	private static void Ref() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				if (Dice[x][y][0] != 0) {
					Dice[x][y][2] += 3 / Dice[x][y][0];
				}
			}
		}
	}
	//Main action of game
	public void Action() {
		//adds reiforcements if there are any
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				int loop = 0;
				do {
					if (Dice[x][y][2] > 1) {
						Dice[x][y][0] += 1;
						Dice[x][y][2] -= 1;
					} else {
						loop = 1;
					}
				} while (loop == 0);
			}
		}
		//if targer and slected belong to the same palyer, Transfer
		if (Dice[selectedX][selectedY][1] == Dice[targetX][targetY][1]) {
			if (Dice[selectedX][selectedY][0] == 1) {
				console.addText("Too few troops to transfer!");
			} else {
				Transfer();
				if(trans == 0){
					TurnFlip();
				}
				TurnFlip();
				if(trans != 0){
				reforcement++;
				}
			
			}
			//If not than attack
		} else {
			if (Dice[selectedX][selectedY][0] == 1) {
				console.addText("Too few troops to attack!");
			} else {
				Attack();
				TurnFlip();
				reforcement++;
			}
		}
		//Every 10 turns (5 Back and forths) Renforcments
		if (reforcement == 10) {
			console.addText("RENFORCMENTS HAVE ARRIVED!!!");
			Ref();
			reforcement = 0;
		}
		//Check who wins if anyone
		if (WinCheck() == 0) {
			TurnFlip();
			if (turn == 0) {
				console.addText("Player 1 Wins!");
				try {
					Score score = new Score(0);
	
					Victory victory = new Victory(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (turn == 1) {
				console.addText("Player 2 wins!");
				
				try {
					Score score = new Score(1);
					Victory victory = new Victory(2);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//Set which Map it should be
	public void SetMap() {
		if (MapType == 0) {
			// All land map
			AllLandMap();
		} else if (MapType == 1) {
			TwoKingDom();
		} else if (MapType == 2) {
			FourKingDom();
		} else if (MapType == 3) {
			SmallDoughnut();
		} else if (MapType == 4) {
			LargeDoughnut();
		}
	}
	//Map AllLand
	private void AllLandMap() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				Map[x][y] = 0;
			}
		}
	}
	//Map (TwoKingDom)
	private void TwoKingDom() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				Map[x][y] = 0;
			}
		}
		Map[3][0] = 1;
		Map[3][1] = 1;
		Map[3][5] = 1;
		Map[3][6] = 1;

	}
	//Map (FourKingDom)
	private void FourKingDom() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				Map[x][y] = 0;
			}
		}
		Map[3][0] = 1;
		Map[3][1] = 1;
		Map[3][5] = 1;
		Map[3][6] = 1;
		Map[0][3] = 1;
		Map[1][3] = 1;
		Map[5][3] = 1;
		Map[6][3] = 1;
		Map[3][3] = 1;
	}
	//Map (Small Doughnut)
	private void SmallDoughnut() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				Map[x][y] = 0;
			}
		}
		Map[3][3] = 1;
		Map[1][3] = 1;
		Map[5][3] = 1;
		Map[3][1] = 1;
		Map[3][5] = 1;
	}
	//Map (LargeDoughnut)
	private void LargeDoughnut() {
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				Map[x][y] = 0;
			}
		}
		for (int x = 2; x <= 4; x++) {
			for (int y = 2; y <= 4; y++) {
				Map[x][y] = 1;
			}
		}
	}
	//Gets map from Game Dispaly
	public int GetMap(int x, int y) {
		int Terrain;// 0 = land 1 = water
		Terrain = Map[x][y];
		return Terrain;
	}
	//checks who wins
	private int WinCheck() {
		int win = 0;
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				if (Dice[x][y][1] == turn) {
					if (Dice[x][y][0] > 1) {
						win = 1;
					}
				}
			}
		}
		return win;
	}
	//Flips who's turn is it
	private void TurnFlip() {
		if (turn == 0) {
			turn = 1;
		} else if (turn == 1) {
			turn = 0;
		}
	}
	//Checks if target and slected tiles are valid 
	public int Adj() {
		int Valid = 0;
		if (targetX - 1 == selectedX && targetY == selectedY) {
			Valid = 1;
		} else if (targetX + 1 == selectedX && targetY == selectedY) {
			Valid = 1;
		} else if (targetX == selectedX && targetY - 1 == selectedY) {
			Valid = 1;
		} else if (targetX == selectedX && targetY + 1 == selectedY) {
			Valid = 1;
		}
		return Valid;
	}
	private static void Transfer() {
		
		int loop1 = 0;
		do {
			console.addText("How many troops would you like to transfer?");
			trans = Transfer.GetTrans();
			loop1 = 1;
			//checks if it can transfer or if it cannot
			if (trans >= (int) Dice[selectedX][selectedY][0]) {
				console.addText("You don't have that many to transfer!");
				loop1 = 0;
			}
			if (Dice[targetX][targetY][0] + trans > 12) {
				console.addText("That tile can't support that many! (Max 12)");
				loop1 = 0;
			}
		} while (loop1 == 0);
		Dice[targetX][targetY][0] += trans;
		Dice[selectedX][selectedY][0] -= trans;

	}

	private static void Attack() {
		// selectedX and selectedY are Attackers, targetX and targetY are
		// defenders
		double atk;
		double def;
		atk = Dice[selectedX][selectedY][0];
		def = Dice[targetX][targetY][0];
		double[] atck = new double[(int) atk];
		double[] defc = new double[(int) def];
		// Dice Rolls for each side
		int A = atck.length, D = defc.length;
		//Sorsts the Dices into order
		boolean loop = true;
		do {
			if (D == defc.length) {
				boolean loopSomeMore;
				for (int x = 0; x < def; x++) {
					defc[x] = (int) (6 * Math.random() + 1);
				}
				do {
					loopSomeMore = false;
					for (int j = 0; j < defc.length - 1; j++) {
						if (defc[j] < defc[j + 1]) {
							// swap a[j] and a[j+1]
							int temp = (int) defc[j];
							defc[j] = defc[j + 1];
							defc[j + 1] = temp;

							loopSomeMore = true;
						}
					}
				} while (loopSomeMore);
				D = 0;
			}
			if (A == atck.length) {
				for (int x = 0; x < atk; x++) {
					atck[x] = (int) (6 * Math.random() + 1);
				}
				boolean loopSomeMore;
				do {
					loopSomeMore = false;
					for (int j = 0; j < atck.length - 1; j++) {
						if (atck[j] < atck[j + 1]) {
							// swap a[j] and a[j+1]
							int temp = (int) atck[j];
							atck[j] = atck[j + 1];
							atck[j + 1] = temp;

							loopSomeMore = true;
						}
					}
				} while (loopSomeMore);
				A = 0;
			}
			//if attackers lose
			if (atk == 1) {
				console.addText((targetX+1) + " , " + (targetY+1) + " repelled the attack!");
				Dice[selectedX][selectedY][0] = atk;
				Dice[targetX][targetY][0] = def;
				loop = false;
			} else if (def == 0) {
				//If attacker win
				Dice[selectedX][selectedY][2] = 0;
				console.addText((targetX+1) + " , " + (targetY+1) + " has fallen!");
				//How man troops to transfer and to swap the sides to conqour
				if (atk == 2) {
					Dice[selectedX][selectedY][0] = 1;
					Dice[targetX][targetY][0] = 1;
					Dice[targetX][targetY][1] = turn;
				} else if (atk == 3) {
					Dice[selectedX][selectedY][0] = 1;
					Dice[targetX][targetY][0] = 2;
					Dice[targetX][targetY][1] = turn;
				} else {
					int charge = (int) atk / 4;
					Dice[selectedX][selectedY][0] = charge;
					Dice[targetX][targetY][0] = atk - charge;
					Dice[targetX][targetY][1] = turn;
				}
				loop = false;
				//else keep on fighting
			} else {
				if (atck[A] <= defc[D]) {

					D++;
					atk--;
				} else if (atck[A] > defc[D]) {

					A++;
					def--;
				}
			}
		} while (loop);

	}

	private void Start() {
		// places the dices on the board
		int z = 0;

		do {
			for (int x = 0; x < max; x++) {
				for (int y = 0; y < max; y++) {
					if (Map[x][y] == 0) {
						Dice[x][y][0] = (int) (12 * Math.random() + 1);
						Dice[x][y][1] = (int) (2 * Math.random());
					} else {
						Dice[x][y][1] = 2; // Void Tile
					}
				}
			}
			int S1 = 0, S2 = 0;
			// adds the number of dices of each side
			for (int x = 0; x < max; x++) {
				for (int y = 0; y < max; y++) {
					if (Dice[y][x][1] == 0) {
						S1 += Dice[y][x][0];
					} else if (Dice[y][x][1] == 1) {
						S2 += Dice[y][x][0];
					}
				}
			}
			// checks if the difference between two sets of dice is less than 6
			// else break the loop and move on
			if (Math.abs(S1 - S2) < 2) {
				z = 1;
			}
		} while (z == 0);
	}


	//adds text to console
	public void AddText(String Hi){
		console.addText(Hi);
	}
}
