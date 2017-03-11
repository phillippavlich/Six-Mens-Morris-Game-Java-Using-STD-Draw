/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: SaveGame Class
 * Class Description: This is a class that allows the user to save the game at any point in the game after the pieces have
 * been set up. By saving the game it writes the necessary information to a file. This file is read from when you click
 * the button on the main menu to load game. This restores the game board to the way it previously was. It allows the user 
 * to continue playing the game from where they left off. Our group made the assumption that you can only keep 1 saved game 
 * at a time. Saving another game will override the older saved game.  
 *
 */



import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//class that allows the user to save the game and reload the game to play at a later time
//provides a button to save the data
public class SaveGame {
	public SaveGame(){
		
	}
	
	//button that is displayed that allows the user to save the game at the end of every turn
	public void saveButton(){
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(910, 60, 50, 40);
		StdDraw.setPenColor(StdDraw.BLACK);
		Font font = new Font("Sans Serif", 18, 18);
		StdDraw.setFont(font);
		StdDraw.text(910, 70,"Save");
		StdDraw.text(910, 40,"Game");
	
	}
	
	//writes to a file called savegame.txt
	//it writes who's turn it is, how many blue pieces there are, how many red pieces there are and all the positions of each piece
	public void saveInfo(boolean humanChecker,boolean computerColor){
		try{
			FileWriter FR=new FileWriter("savedgame.txt",false);
			BufferedWriter BR=new BufferedWriter(FR);
			String whosturn="";
			if(SixMensMorris.whosFirst){
				whosturn="Blue";
			}
			else{
				whosturn="Red";
			}
			BR.write(whosturn);
			BR.newLine();
			BR.write(Integer.toString(SixMensMorris.G.bluePieces));
			BR.newLine();
			BR.write(Integer.toString(SixMensMorris.G.redPieces));
			BR.newLine();
			for(int i=0;i<6;i++){
				if(SixMensMorris.pieceArray[i].getEnabled()){
					BR.write(Integer.toString(SixMensMorris.pieceArray[i].getPiecePosition()));
					BR.newLine();
			
				}
			}
			for(int I=6;I<12;I++){
				if(SixMensMorris.pieceArray[I].getEnabled()){
					BR.write(Integer.toString(SixMensMorris.pieceArray[I].getPiecePosition()));
					BR.newLine();
			
				}
			}
			
			BR.write(Boolean.toString(humanChecker));
			BR.newLine();
			BR.write(Boolean.toString(computerColor));
			BR.newLine();
			
			BR.close();
		}
		catch(IOException e){
			
		}
		Font font = new Font("Arial", 20, 20);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 875, "Please Restart the Game!");
		StdDraw.text(500, 920, "Your game has been saved!");
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(910, 60, 50, 40);
		
	}
	
	//Reads the data saved to a file in order to reproduce a previously saved game
	public void buildGame(){
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledCircle(80, 200, 35);
		StdDraw.filledCircle(80, 300, 35);
		StdDraw.filledCircle(80, 400, 35);
		StdDraw.filledCircle(80, 500, 35);
		StdDraw.filledCircle(80, 600, 35);
		StdDraw.filledCircle(80, 700, 35);
		StdDraw.filledCircle(920, 200, 35);
		StdDraw.filledCircle(920, 300, 35);
		StdDraw.filledCircle(920, 400, 35);
		StdDraw.filledCircle(920, 500, 35);
		StdDraw.filledCircle(920, 600, 35);
		StdDraw.filledCircle(920, 700, 35);
		SixMensMorris.pieceArray = new PlayerPiece[12];
		for (int i = 0; i < 12; i++) {
			if (i < 6) {
				SixMensMorris.pieceArray[i] = new PlayerPiece("BLUE", i + 1);
			} else {
				SixMensMorris.pieceArray[i] = new PlayerPiece("RED", i + 1);
			}
		}
		
		//creating objects to represent each location on the game board
		SixMensMorris.spots = new moveLocations[17];
		SixMensMorris.spots[0] = new moveLocations(165, 235, 765, 835);
		SixMensMorris.spots[1] = new moveLocations(465, 535, 765, 835);
		SixMensMorris.spots[2] = new moveLocations(765, 835, 765, 835);
		SixMensMorris.spots[3] = new moveLocations(765, 835, 465, 535);
		SixMensMorris.spots[4] = new moveLocations(765, 835, 165, 235);
		SixMensMorris.spots[5] = new moveLocations(465, 535, 165, 235);
		SixMensMorris.spots[6] = new moveLocations(165, 235, 165, 235);
		SixMensMorris.spots[7] = new moveLocations(165, 235, 465, 535);
		SixMensMorris.spots[8] = new moveLocations(315, 385, 615, 685);
		SixMensMorris.spots[9] = new moveLocations(465, 535, 615, 685);
		SixMensMorris.spots[10] = new moveLocations(615, 685, 615, 685);
		SixMensMorris.spots[11] = new moveLocations(615, 685, 465, 535);
		SixMensMorris.spots[12] = new moveLocations(615, 685, 315, 385);
		SixMensMorris.spots[13] = new moveLocations(465, 535, 315, 385);
		SixMensMorris.spots[14] = new moveLocations(315, 385, 315, 385);
		SixMensMorris.spots[15] = new moveLocations(315, 385, 465, 535);
		SixMensMorris.spots[16] = new moveLocations(1000, 2000, 1000, 2000);
		FileReader Fr;
		try {
			
			Fr = new FileReader("savedgame.txt");
			BufferedReader Br=new BufferedReader(Fr);
			String first=Br.readLine();
			
			if(first.equals("Blue")){
				SixMensMorris.whosFirst=true;
			}
			else{
				SixMensMorris.whosFirst=false;
			}
			
			int blue=Integer.parseInt(Br.readLine());
			int red=Integer.parseInt(Br.readLine());
			
			for(int i=0;i<blue;i++){//load each blue piece
				SixMensMorris.pieceArray[i].changeEnabled();
				SixMensMorris.pieceArray[i].changePiecePosition(Integer.parseInt(Br.readLine()));	
				SixMensMorris.spots[SixMensMorris.pieceArray[i].getPiecePosition()].changeOccupied();
				double a=SixMensMorris.spots[SixMensMorris.pieceArray[i].getPiecePosition()].getMidX();
				double b=SixMensMorris.spots[SixMensMorris.pieceArray[i].getPiecePosition()].getMidY();
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(a, b, 35);
			}
			for(int I=6;I<6+red;I++){//load each red piece
				SixMensMorris.pieceArray[I].changeEnabled();
				SixMensMorris.pieceArray[I].changePiecePosition(Integer.parseInt(Br.readLine()));	
				SixMensMorris.spots[SixMensMorris.pieceArray[I].getPiecePosition()].changeOccupied();
				double l=SixMensMorris.spots[SixMensMorris.pieceArray[I].getPiecePosition()].getMidX();
				double m=SixMensMorris.spots[SixMensMorris.pieceArray[I].getPiecePosition()].getMidY();
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledCircle(l, m, 35);
			}
			boolean humanChecker=Boolean.parseBoolean(Br.readLine());
			boolean computerColor=Boolean.parseBoolean(Br.readLine());
			Br.close();
			
			boolean whoTurn=false;
			if(first.equals("Blue")){
				SixMensMorris.whosFirst=true;
				if(computerColor){
					
				}
				else{}
			}
			else{
				SixMensMorris.whosFirst=false;
			}
			
			//saving any mills that are present
			int[][] mill={{0,1,2},{2,3,4},{4,5,6},{6,7,0},{8,9,10},{10,11,12},{12,13,14},{14,15,8}};
			//checks for mills in the setup so that when the game is played, it will already have record of a mill in a location
			int[] tempB=new int[3];
			for(int P=0;P<mill.length;P++){
				int Row=0;
				for(int T=0;T<3;T++){
					for(int R=0;R<6;R++){
						if(SixMensMorris.pieceArray[R].getEnabled()&&SixMensMorris.pieceArray[R].getPiecePosition()==mill[P][T]){
							tempB[Row]=R;
							Row++;
						}
					}
				}
				if(Row==3){//blue mill
					gamePlay.blueArray[0]=tempB[0];
					gamePlay.blueArray[1]=tempB[1];
					gamePlay.blueArray[2]=tempB[2];
					gamePlay.firstMillIndex=P;
					gamePlay.AA=SixMensMorris.pieceArray[gamePlay.blueArray[0]].getPiecePosition();
					gamePlay.BB=SixMensMorris.pieceArray[gamePlay.blueArray[1]].getPiecePosition();
					gamePlay.CC=SixMensMorris.pieceArray[gamePlay.blueArray[2]].getPiecePosition();
					gamePlay.blueCountMill=2;
				}
			}
			
			int[] tempR=new int[3];
			for(int P=0;P<mill.length;P++){
				int Row1=0;
				for(int T=0;T<3;T++){
					for(int R=6;R<12;R++){
						if(SixMensMorris.pieceArray[R].getEnabled()&&SixMensMorris.pieceArray[R].getPiecePosition()==mill[P][T]){
							tempR[Row1]=R;//
							Row1++;
						}
					}
				}
				if(Row1==3){//red mill
					gamePlay.redArray[0]=tempR[0];
					gamePlay.redArray[1]=tempR[1];
					gamePlay.redArray[2]=tempR[2];
					
					gamePlay.secondMillIndex=P;
					gamePlay.A=SixMensMorris.pieceArray[gamePlay.redArray[0]].getPiecePosition();
					gamePlay.B=SixMensMorris.pieceArray[gamePlay.redArray[1]].getPiecePosition();
					gamePlay.C=SixMensMorris.pieceArray[gamePlay.redArray[2]].getPiecePosition();
					gamePlay.redCountMill=2;
				}
			}
			SixMensMorris.G=new gamePlay();
			SixMensMorris.G.createGame(red, blue,humanChecker,whoTurn,computerColor,"remake");
		} 
		catch (FileNotFoundException e) {//deals with if there was no previously saved games
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(500,100,"Sorry there was no previously");
			StdDraw.text(500,60,"saved game...");
			StdDraw.text(500,20,"Please Restart the game!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
