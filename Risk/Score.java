package Risk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Anything with Colsole.addText(...) will be displayed on the green/black console we made.
// also int win=0 means player 1 won, and not 0 is player 2 won.
public class Score {
	/** infile is what's in the file line by line
	 ** file is for just the important parts
	 ** for all 2d arrays: [x][0] is names, [x][1] is wins, [x][2] is losses, [x][3] is win streak, and the first box is player
	 ** Can hold up to 9999 people, but theses no way someone will reach that.
	**/
	private static String[][] file=new String [9999][4];
	private static String[][] infile=new String [9999][4];
	// p1acc and p2acc are used to separate all the different people and scores used, set to -1 for when there are no people.
	private static int p1acc=-1;
	private static int p2acc=-1;
	public Score(int win) throws IOException{
		File score = new File(System.getProperty("user.home")+"\\Desktop\\Risk Files\\Dice Legacy Score.txt"); //change
		// If the file already exits the file is updated
		if (score.exists()){
			int x=0,y=0;
			BufferedReader bin = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Desktop\\Risk Files\\Dice Legacy Score.txt")); //change
			int check=0;
			int L=0;
			do{	// Reads the lines until it hits a empty line.
				String str=bin.readLine();
				if(str!=(null)){
					infile[y][x]=(str);
				x++;
				if(x==4){ // Every 4 lines it switches to the next person, because it goes names, wins, losses, and win streak.
					y++;
					x=0;
				}
				}
				else{check=1;}
			}while(check==0);
			bin.close();
			for (int t = 0; t <y ; t++){		//puts only the important parts from infile to file
				file[t][0]=infile[t][0].replaceAll("Name: ","");
				file[t][1]=infile[t][1].replaceAll("Wins: ","");;
				file[t][2]=infile[t][2].replaceAll("lost: ","");;
				file[t][3]=infile[t][3].replaceAll("Win Streak: ","");;
				}
			for(int h=0; h< 9999; h++){ 	// Checks if the person has played the game before and updates the score.
				if(Game.P1Name.equals(file[h][0])){
					p1acc=h;
					if (win == 0){
						int win1=(Integer.parseInt(file[p1acc][1])) + 1;
						int stre1=(Integer.parseInt(file[p1acc][3])) + 1;
						file[p1acc][1]=String.valueOf(win1);file[p1acc][3]=String.valueOf(stre1);
					}
					else {
						int lost1=(Integer.parseInt(file[p1acc][2])) + 1;
						file[p1acc][2]=String.valueOf(lost1);file[p1acc][3]="0";
						}
				}
			}
			if(p1acc==-1){	// if the first player account hasn't changed it adds the wins and losses accordingly.
				p1acc=y;
				L++;
				file[p1acc][0]=Game.P1Name;
				if (win == 0){
					file[p1acc][1]="1";file[p1acc][2]="0";file[p1acc][3]="1";
				}
				else {
					file[p1acc][1]="0";file[p1acc][2]="1";file[p1acc][3]="0";
					}
			}
			for(int h=0; h< 9999; h++){		// Checks if the person has played the game before and updates the score.
				if(Game.P2Name.equals(file[h][0])){
					p2acc=h;
					if (win == 0){
						int lost2=(Integer.parseInt(file[p2acc][2])) + 1;
						file[p2acc][2]=String.valueOf(lost2);file[p2acc][3]="0";
					}
					else {
						int win2=(Integer.parseInt(file[p2acc][1])) + 1;
						int stre2=(Integer.parseInt(file[p2acc][3])) + 1;
						file[p2acc][1]=String.valueOf(win2);file[p2acc][3]=String.valueOf(stre2);
						}
				}
			}
			if(p2acc==-1){		// if the first player account hasn't changed it adds the wins and losses accordingly.
				p2acc=y+L;
				file[p2acc][0]=Game.P2Name;
				if (win == 0){
					file[p2acc][1]="0";file[p2acc][2]="1";file[p2acc][3]="0";
				}
				else {
					file[p2acc][1]="1";file[p2acc][2]="0";file[p2acc][3]="1";
					}
				L++;
			}
			
			for (int t = 0; t <y+L ; t++){		//puts only the important parts from infile to file
				infile[t][0]="Name: "+file[t][0];
				infile[t][1]="Wins: "+file[t][1];
				infile[t][2]="lost: "+file[t][2];
				infile[t][3]="Win Streak: "+file[t][3];
				}
			
			FileWriter out; // Starting up the FileWriter and BufferedWriter
			BufferedWriter write;
			try{ 		//Writes the final and updated score into the file, overwriting the old one.
				out=new FileWriter(score);
				write = new BufferedWriter(out);
				for (int q = 0; q <y+L ; q++){
					for (int w = 0; w <4 ; w++){
						write.write(infile[q][w]);
				write.newLine();
					}
				
				}
				write.close(); // Closing the FileWriter and BufferedWriter
				out.close();
			}catch (IOException e) {
				Console.addText("Oh no, something went wrong, problem writing in file.");		
				Console.addText("IOException: "+e.getMessage()); //Error message will pop up on the console if it fails to write in the file.
			}
			
		}		
		else{		//If there is no file it will create one.
			Console.addText("Creating a Dice Mania Score file.");
			score.createNewFile(); 
			// puts in the score according to who won.
			file[0][0]=Game.P1Name; file[1][0]=Game.P2Name;
			if (win == 0){
				file[0][1]="1";file[0][2]="0";file[0][3]="1";
				file[1][1]="0";file[1][2]="1";file[1][3]="0";
			}
			else if (win != 0) {
				file[1][1]="1";file[1][2]="0";file[1][3]="1";
				file[0][1]="0";file[0][2]="1";file[0][3]="0";
				}
			for (int t = 0; t <2 ; t++){	//Adds the correct sentence in to ifile, which will be written into the file line by line.
			infile[t][0]="Name: "+file[t][0];
			infile[t][1]="Wins: "+file[t][1];
			infile[t][2]="lost: "+file[t][2];
			infile[t][3]="Win Streak: "+file[t][3];
			}
			FileWriter out;
			BufferedWriter write;
			try{	// Just writes the infile into the file.
				out=new FileWriter(score);
				write = new BufferedWriter(out);
				for (int q = 0; q <2 ; q++){
					for (int w = 0; w <4 ; w++){
						write.write(infile[q][w]);
				write.newLine();
					}
				
				}
				write.close();
				out.close();
			}catch (IOException e) { 	//If it fails to write it will display a error message to the console.
				Console.addText("Oh no, something went wrong, problem writing in file.");
				Console.addText("IOException: "+e.getMessage());
			}
		}
	}
	}
