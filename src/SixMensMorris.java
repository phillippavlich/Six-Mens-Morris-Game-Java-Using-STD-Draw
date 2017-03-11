/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: SixMensMorris Class
 * Class Description: This class holds the main program structure and sequential flow as the game state changes. 
 * There are a variety of methods that are used in this class that allow the user to either play the game, 
 * or continue a game.
 *
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SixMensMorris {
	//Main method where drawing methods are called and basic structure of program can be followed
	public static moveLocations[] spots; 
	public static PlayerPiece[] pieceArray; 
	public static boolean whosFirst;
	public static gamePlay G;
	public static int numBlue;
	public static int numRed;
	public static boolean mouseClicked=false;
	
	public static void main(String[] args) {
		newMain();
	}
	public static void newMain() {
		mainMenu();

		SixMensMorris.mouseClicked = false;
		
		double xLocation = 0;
		double yLocation = 0;
		while (!SixMensMorris.mouseClicked || (!(mouseInRange(xLocation, yLocation, 295, 705, 425, 575))
				&& !(mouseInRange(xLocation, yLocation, 295, 705, 225, 375))&& !(mouseInRange(xLocation, yLocation, 295, 705, 25, 175)))) {
			
			SixMensMorris.mouseClicked = false;
			
			SixMensMorris.mouseClicked = StdDraw.mousePressed();
			xLocation = StdDraw.mouseX();
			yLocation = StdDraw.mouseY();

		}
		
		SixMensMorris.mouseClicked=false;
		
		
		// start new game
		if (mouseInRange(xLocation, yLocation, 295, 705, 425, 575)) {
			Computer person=new Computer();
			boolean humanChecker=person.checkPlayer();
			
			setBoard();
			//human
			if(!humanChecker){
				newGame(humanChecker,true,true);
			}
			//computer
			else{
				boolean whoTurn=person.whosTurn();
				boolean computerColor=person.whosTurn();
				newGame(humanChecker,whoTurn,computerColor);
			}
		}
		else if (mouseInRange(xLocation, yLocation, 295, 705, 25, 175)) {
			
			
			setBoard();
			SaveGame load=new SaveGame();
			load.buildGame();
		} 
		else {
			Computer person=new Computer();
			boolean humanChecker=person.checkPlayer();
			
			setBoard();
			//human
			if(!humanChecker){
				continueGame(humanChecker,true);
			}
			//computer
			else{
				boolean whoturn=person.whosTurn();
				boolean computerColor=person.whosTurn();
				continueGame(humanChecker,computerColor);
			}
		}
		
	}

	// returns true if in range
	public static boolean mouseInRange(double xLoc, double yLoc, double minX, double maxX, double minY, double maxY) {
		if (minX <= xLoc && xLoc <= maxX) {
			if (minY <= yLoc && yLoc <= maxY) {
				return true;
			}
		}
		return false;
	}
	
	//Main menu visuals using StdDraw
	public static void mainMenu() {
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setScale(0, 1000);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(500, 500, 1000, 1000);
		StdDraw.setPenColor(StdDraw.BLACK);
		Font font = new Font("Sans Serif", 20, 20);
		StdDraw.setFont(font);
		StdDraw.text(500, 850, "Six Men Morris Game");
		StdDraw.text(500, 750, "Game Created By: ");
		StdDraw.text(500, 700, "Phillip Pavlich, Prakhar Jalan, Dinesh Balakrishnan");
		StdDraw.filledRectangle(500, 500, 205, 75);
		StdDraw.filledRectangle(500, 300, 205, 75);
		StdDraw.filledRectangle(500, 100, 205, 75);
		
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius(0.05);

		StdDraw.filledRectangle(500, 500, 200, 70);
		StdDraw.filledRectangle(500, 300, 200, 70);
		StdDraw.filledRectangle(500, 100, 200, 70);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 500, "Start New Game");
		StdDraw.text(500, 300, "Continue Game");
		StdDraw.text(500, 100, "Load Saved Game");
	}
	
	//Game board visuals using StdDraw
	public static void setBoard() {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500, 500, 1000, 1000);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(200, 800, 35);
		StdDraw.filledCircle(500, 800, 35);
		StdDraw.filledCircle(800, 800, 35);
		StdDraw.filledCircle(200, 200, 35);
		StdDraw.filledCircle(500, 200, 35);
		StdDraw.filledCircle(800, 200, 35);
		StdDraw.filledCircle(200, 500, 35);
		StdDraw.filledCircle(800, 500, 35);
		StdDraw.filledCircle(350, 650, 35);
		StdDraw.filledCircle(500, 650, 35);
		StdDraw.filledCircle(650, 650, 35);
		StdDraw.filledCircle(350, 350, 35);
		StdDraw.filledCircle(500, 350, 35);
		StdDraw.filledCircle(650, 350, 35);
		StdDraw.filledCircle(350, 500, 35);
		StdDraw.filledCircle(650, 500, 35);

		StdDraw.setPenRadius(0.01);
		StdDraw.line(200, 200, 800, 200);
		StdDraw.line(200, 800, 800, 800);
		StdDraw.line(350, 350, 650, 350);
		StdDraw.line(350, 650, 650, 650);
		StdDraw.line(200, 500, 350, 500);
		StdDraw.line(650, 500, 800, 500);
		StdDraw.line(200, 800, 200, 200);
		StdDraw.line(800, 800, 800, 200);
		StdDraw.line(350, 650, 350, 350);
		StdDraw.line(650, 650, 650, 350);
		StdDraw.line(500, 800, 500, 650);
		StdDraw.line(500, 200, 500, 350);

		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(80, 200, 35);
		StdDraw.filledCircle(80, 300, 35);
		StdDraw.filledCircle(80, 400, 35);
		StdDraw.filledCircle(80, 500, 35);
		StdDraw.filledCircle(80, 600, 35);
		StdDraw.filledCircle(80, 700, 35);

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(920, 200, 35);
		StdDraw.filledCircle(920, 300, 35);
		StdDraw.filledCircle(920, 400, 35);
		StdDraw.filledCircle(920, 500, 35);
		StdDraw.filledCircle(920, 600, 35);
		StdDraw.filledCircle(920, 700, 35);

		StdDraw.setPenColor(StdDraw.BLACK);

	}
	
	//newGame method for players to take turns during placement of pieces
	public static void newGame(boolean humanChecker,boolean whoTurn, boolean computerColor) {
		G=new gamePlay();
		numRed=0;
		numBlue=0;
		
		
		double value = Math.random();
		Font font = new Font("Sans Serif", 20, 20);
		StdDraw.setFont(font);
		
		whosFirst = true;
		if (value < 0.5) {
			StdDraw.text(500, 950, "Blue Player goes first!");
			if(computerColor){
				whoTurn=true;
				
			}
			else{
				whoTurn=false;
				
			}
		} else {
			StdDraw.text(500, 950, "Red Player goes first!");
			if(computerColor){
				whoTurn=false;
				
			}
			else{
				whoTurn=true;
				
			}
			whosFirst = false;
		}
		
		StdDraw.text(500, 100, "Please click on the location that you wish to place your piece.");
		
		//creating objects to represent each piece that you are playing with
		pieceArray = new PlayerPiece[12];
		for (int i = 0; i < 12; i++) {
			if (i < 6) {
				pieceArray[i] = new PlayerPiece("BLUE", i + 1);
			} else {
				pieceArray[i] = new PlayerPiece("RED", i + 1);
			}
		}
		
		//creating objects to represent each location on the game board
		spots = new moveLocations[17];
		spots[0] = new moveLocations(165, 235, 765, 835);
		spots[1] = new moveLocations(465, 535, 765, 835);
		spots[2] = new moveLocations(765, 835, 765, 835);
		spots[3] = new moveLocations(765, 835, 465, 535);
		spots[4] = new moveLocations(765, 835, 165, 235);
		spots[5] = new moveLocations(465, 535, 165, 235);
		spots[6] = new moveLocations(165, 235, 165, 235);
		spots[7] = new moveLocations(165, 235, 465, 535);
		spots[8] = new moveLocations(315, 385, 615, 685);
		spots[9] = new moveLocations(465, 535, 615, 685);
		spots[10] = new moveLocations(615, 685, 615, 685);
		spots[11] = new moveLocations(615, 685, 465, 535);
		spots[12] = new moveLocations(615, 685, 315, 385);
		spots[13] = new moveLocations(465, 535, 315, 385);
		spots[14] = new moveLocations(315, 385, 315, 385);
		spots[15] = new moveLocations(315, 385, 465, 535);
		spots[16] = new moveLocations(1000, 2000, 1000, 2000);
		
		
		if(humanChecker){
			StdDraw.setPenColor(StdDraw.BLACK);
			
			if(computerColor){
				
				StdDraw.text(80, 820, "Computer");
			}
			else{
			
				StdDraw.text(920, 820, "Computer");
			}

		}
		
		int j = 0;
		int num1 = 700;
		int num2 = 700;
		
		boolean mouseClicked = false;
		boolean mouseClicked1 = false;
		boolean boolVal = true;
		boolean boolVal2 = true;
		double xLocation = 0;
		double yLocation = 0;
		double xLocation1 = 0;
		double yLocation1 = 0;
		int countBlue=0;
		int countRed=6;
		boolean turn=true;
		boolean fixingTurn=false;
		//Main while loop for 12 turns
		//setting up the game board when the user clicks new game
		while (j < 12) {
			
			//all the if's and else if's were designed in assignment 1 to represent where a player can put there piece
			//we realize that this is a very inefficient way of coding although it does work
			//This class is designed for setting up the board with the first moves
			//the rest of the game is in the gamePlay class
			//we made the gamePlay class for assignment 2 and we made sure to modularize it
			//we also reduced the conditional statements to make the code easier to read and improve efficiency
			if (j == 1) {
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(500, 950, 400, 50);
			}
			if(whoTurn&&humanChecker){
				ComputerMove moving=new ComputerMove();
				if(computerColor){
					moving.placeAIPiece(countBlue);
					
					countBlue++;
					numBlue++;
					if(fixingTurn){
						fixingTurn=false;
					}
					else{
						fixingTurn=true;
					}
				}
				else{
					moving.placeAIPiece(countRed);
					
					countRed++;
					numRed++;
					if(fixingTurn){
						fixingTurn=false;
					}
					else{
						fixingTurn=true;
					}
				}
			}
			else{
			if (j % 2 == 0) {// first
				if (whosFirst) {// blue goes first
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledRectangle(900, 900, 100, 50);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(100, 900, "Blue's Turn!");
					fixingTurn=true;
					while (boolVal) {

						mouseClicked = StdDraw.mousePressed();
						xLocation = StdDraw.mouseX();
						yLocation = StdDraw.mouseY();
						if (!spots[0].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[0].getMinX(), spots[0].getMaxX(), spots[0].getMinY(), spots[0].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[0].changeOccupied();
							pieceArray[countBlue].changePiecePosition(0);
							
						} else if (!spots[1].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[1].getMinX(), spots[1].getMaxX(), spots[1].getMinY(), spots[1].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[1].changeOccupied();
							pieceArray[countBlue].changePiecePosition(1);
							
						} else if (!spots[2].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[2].getMinX(), spots[2].getMaxX(), spots[2].getMinY(), spots[2].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[2].changeOccupied();
							pieceArray[countBlue].changePiecePosition(2);
							
						} else if (!spots[3].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[3].getMinX(), spots[3].getMaxX(), spots[3].getMinY(), spots[3].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[3].changeOccupied();
							pieceArray[countBlue].changePiecePosition(3);
							
						} else if (!spots[4].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[4].getMinX(), spots[4].getMaxX(), spots[4].getMinY(), spots[4].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[4].changeOccupied();
							pieceArray[countBlue].changePiecePosition(4);
							
						} else if (!spots[5].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[5].getMinX(), spots[5].getMaxX(), spots[5].getMinY(), spots[5].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[5].changeOccupied();
							pieceArray[countBlue].changePiecePosition(5);
							
						} else if (!spots[6].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[6].getMinX(), spots[6].getMaxX(), spots[6].getMinY(), spots[6].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[6].changeOccupied();
							pieceArray[countBlue].changePiecePosition(6);
							
						} else if (!spots[7].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[7].getMinX(), spots[7].getMaxX(), spots[7].getMinY(), spots[7].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[7].changeOccupied();
							pieceArray[countBlue].changePiecePosition(7);
							
						} else if (!spots[8].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[8].getMinX(), spots[8].getMaxX(), spots[8].getMinY(), spots[8].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[8].changeOccupied();
							pieceArray[countBlue].changePiecePosition(8);
							
						} else if (!spots[9].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[9].getMinX(), spots[9].getMaxX(), spots[9].getMinY(), spots[9].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[9].changeOccupied();
							pieceArray[countBlue].changePiecePosition(9);
							
						} else if (!spots[10].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[10].getMinX(), spots[10].getMaxX(), spots[10].getMinY(), spots[10].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[10].changeOccupied();
							pieceArray[countBlue].changePiecePosition(10);
							
						} else if (!spots[11].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[11].getMinX(), spots[11].getMaxX(), spots[11].getMinY(), spots[11].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[11].changeOccupied();
							pieceArray[countBlue].changePiecePosition(11);
							
						} else if (!spots[12].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[12].getMinX(), spots[12].getMaxX(), spots[12].getMinY(), spots[12].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[12].changeOccupied();
							pieceArray[countBlue].changePiecePosition(12);
							
						} else if (!spots[13].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[13].getMinX(), spots[13].getMaxX(), spots[13].getMinY(), spots[13].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[13].changeOccupied();
							pieceArray[countBlue].changePiecePosition(13);
							
						} else if (!spots[14].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[14].getMinX(), spots[14].getMaxX(), spots[14].getMinY(), spots[14].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[14].changeOccupied();
							pieceArray[countBlue].changePiecePosition(14);
							
						} else if (!spots[15].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[15].getMinX(), spots[15].getMaxX(), spots[15].getMinY(), spots[15].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[15].changeOccupied();
							pieceArray[countBlue].changePiecePosition(15);
							
						}
						xLocation = 0;
						yLocation = 0;
						mouseClicked = false;
					}
					countBlue++;
					numBlue++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledRectangle(100, 900, 100, 50);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(900, 900, "Red's Turn!");//this is for red's turn assuming blue got to go first
					fixingTurn=false;
					//all the conditions below on where red can put their piece
					while (boolVal2) {
						mouseClicked = StdDraw.mousePressed();
						xLocation = StdDraw.mouseX();

						yLocation = StdDraw.mouseY();
						if (!spots[0].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[0].getMinX(), spots[0].getMaxX(), spots[0].getMinY(), spots[0].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[0].changeOccupied();
							pieceArray[countRed].changePiecePosition(0);
							
						} else if (!spots[1].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[1].getMinX(), spots[1].getMaxX(), spots[1].getMinY(), spots[1].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[1].changeOccupied();
							pieceArray[countRed].changePiecePosition(1);
							
						} else if (!spots[2].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[2].getMinX(), spots[2].getMaxX(), spots[2].getMinY(), spots[2].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[2].changeOccupied();
							pieceArray[countRed].changePiecePosition(2);
							
						} else if (!spots[3].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[3].getMinX(), spots[3].getMaxX(), spots[3].getMinY(), spots[3].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[3].changeOccupied();
							pieceArray[countRed].changePiecePosition(3);
						}

						else if (!spots[4].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[4].getMinX(), spots[4].getMaxX(), spots[4].getMinY(), spots[4].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[4].changeOccupied();
							pieceArray[countRed].changePiecePosition(4);
							
						} else if (!spots[5].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[5].getMinX(), spots[5].getMaxX(), spots[5].getMinY(), spots[5].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[5].changeOccupied();
							pieceArray[countRed].changePiecePosition(5);
							
						} else if (!spots[6].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[6].getMinX(), spots[6].getMaxX(), spots[6].getMinY(), spots[6].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[6].changeOccupied();
							pieceArray[countRed].changePiecePosition(6);
							
						} else if (!spots[7].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[7].getMinX(), spots[7].getMaxX(), spots[7].getMinY(), spots[7].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[7].changeOccupied();
							pieceArray[countRed].changePiecePosition(7);
							
						} else if (!spots[8].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[8].getMinX(), spots[8].getMaxX(), spots[8].getMinY(), spots[8].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[8].changeOccupied();
							pieceArray[countRed].changePiecePosition(8);
							
						} else if (!spots[9].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[9].getMinX(), spots[9].getMaxX(), spots[9].getMinY(), spots[9].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[9].changeOccupied();
							pieceArray[countRed].changePiecePosition(9);
							
						} else if (!spots[10].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[10].getMinX(), spots[10].getMaxX(), spots[10].getMinY(), spots[10].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[10].changeOccupied();
							pieceArray[countRed].changePiecePosition(10);
							
						} else if (!spots[11].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[11].getMinX(), spots[11].getMaxX(), spots[11].getMinY(), spots[11].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[11].changeOccupied();
							pieceArray[countRed].changePiecePosition(11);
							
						} else if (!spots[12].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[12].getMinX(), spots[12].getMaxX(), spots[12].getMinY(), spots[12].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[12].changeOccupied();
							pieceArray[countRed].changePiecePosition(12);
							
						} else if (!spots[13].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[13].getMinX(), spots[13].getMaxX(), spots[13].getMinY(), spots[13].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[13].changeOccupied();
							pieceArray[countRed].changePiecePosition(13);
							
						} else if (!spots[14].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[14].getMinX(), spots[14].getMaxX(), spots[14].getMinY(), spots[14].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[14].changeOccupied();
							pieceArray[countRed].changePiecePosition(14);
							
						} else if (!spots[15].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
								spots[15].getMinX(), spots[15].getMaxX(), spots[15].getMinY(), spots[15].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation = 0;
							yLocation = 0;
							mouseClicked = false;
							spots[15].changeOccupied();
							pieceArray[countRed].changePiecePosition(15);
						}
					}
					countRed++;
					numRed++;
				}

			} else {// second
				if (!whosFirst) {// if blue does not get to go first, these are for blue's moves
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledRectangle(900, 900, 100, 50);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(100, 900, "Blue's Turn!");
					fixingTurn=true;
					while (boolVal) {
						mouseClicked1 = StdDraw.mousePressed();
						xLocation1 = StdDraw.mouseX();
						yLocation1 = StdDraw.mouseY();

						if (!spots[0].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[0].getMinX(), spots[0].getMaxX(), spots[0].getMinY(), spots[0].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[0].changeOccupied();
							pieceArray[countBlue].changePiecePosition(0);
							
						} else if (!spots[1].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[1].getMinX(), spots[1].getMaxX(), spots[1].getMinY(), spots[1].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[1].changeOccupied();
							pieceArray[countBlue].changePiecePosition(1);
							
						} else if (!spots[2].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[2].getMinX(), spots[2].getMaxX(), spots[2].getMinY(), spots[2].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 800, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[2].changeOccupied();
							pieceArray[countBlue].changePiecePosition(2);
							
						} else if (!spots[3].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[3].getMinX(), spots[3].getMaxX(), spots[3].getMinY(), spots[3].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[3].changeOccupied();
							pieceArray[countBlue].changePiecePosition(3);
							
						} else if (!spots[4].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[4].getMinX(), spots[4].getMaxX(), spots[4].getMinY(), spots[4].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(800, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[4].changeOccupied();
							pieceArray[countBlue].changePiecePosition(4);
							
						} else if (!spots[5].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[5].getMinX(), spots[5].getMaxX(), spots[5].getMinY(), spots[5].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[5].changeOccupied();
							pieceArray[countBlue].changePiecePosition(5);
							
						} else if (!spots[6].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[6].getMinX(), spots[6].getMaxX(), spots[6].getMinY(), spots[6].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 200, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[6].changeOccupied();
							pieceArray[countBlue].changePiecePosition(6);
							
						} else if (!spots[7].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[7].getMinX(), spots[7].getMaxX(), spots[7].getMinY(), spots[7].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(200, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[7].changeOccupied();
							pieceArray[countBlue].changePiecePosition(7);
							
						} else if (!spots[8].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[8].getMinX(), spots[8].getMaxX(), spots[8].getMinY(), spots[8].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[8].changeOccupied();
							pieceArray[countBlue].changePiecePosition(8);
							
						} else if (!spots[9].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[9].getMinX(), spots[9].getMaxX(), spots[9].getMinY(), spots[9].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[9].changeOccupied();
							pieceArray[countBlue].changePiecePosition(9);
							
						} else if (!spots[10].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[10].getMinX(), spots[10].getMaxX(), spots[10].getMinY(), spots[10].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 650, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[10].changeOccupied();
							pieceArray[countBlue].changePiecePosition(10);
							
						} else if (!spots[11].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[11].getMinX(), spots[11].getMaxX(), spots[11].getMinY(), spots[11].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[11].changeOccupied();
							pieceArray[countBlue].changePiecePosition(11);
							
						} else if (!spots[12].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[12].getMinX(), spots[12].getMaxX(), spots[12].getMinY(), spots[12].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(650, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[12].changeOccupied();
							pieceArray[countBlue].changePiecePosition(12);
							
						} else if (!spots[13].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[13].getMinX(), spots[13].getMaxX(), spots[13].getMinY(), spots[13].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(500, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[13].changeOccupied();
							pieceArray[countBlue].changePiecePosition(13);
							
						} else if (!spots[14].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[14].getMinX(), spots[14].getMaxX(), spots[14].getMinY(), spots[14].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 350, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[14].changeOccupied();
							pieceArray[countBlue].changePiecePosition(14);
							
						} else if (!spots[15].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[15].getMinX(), spots[15].getMaxX(), spots[15].getMinY(), spots[15].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(80, num1, 35);
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledCircle(350, 500, 35);
							num1 -= 100;
							boolVal = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[15].changeOccupied();
							pieceArray[countBlue].changePiecePosition(15);
						}
					}
					countBlue++;
					numBlue++;
					
				} else {//finally this is for if red gets to go first
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledRectangle(100, 900, 100, 50);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(900, 900, "Red's Turn!");
					fixingTurn=false;
					while (boolVal2) {

						mouseClicked1 = StdDraw.mousePressed();

						xLocation1 = StdDraw.mouseX();
						yLocation1 = StdDraw.mouseY();
						if (!spots[0].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[0].getMinX(), spots[0].getMaxX(), spots[0].getMinY(), spots[0].getMaxY())) {

							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[0].changeOccupied();
							pieceArray[countRed].changePiecePosition(0);
						} else if (!spots[1].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[1].getMinX(), spots[1].getMaxX(), spots[1].getMinY(), spots[1].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[1].changeOccupied();
							pieceArray[countRed].changePiecePosition(1);
							
						} else if (!spots[2].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[2].getMinX(), spots[2].getMaxX(), spots[2].getMinY(), spots[2].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 800, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[2].changeOccupied();
							pieceArray[countRed].changePiecePosition(2);
							
						} else if (!spots[3].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[3].getMinX(), spots[3].getMaxX(), spots[3].getMinY(), spots[3].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[3].changeOccupied();
							pieceArray[countRed].changePiecePosition(3);
						}

						else if (!spots[4].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[4].getMinX(), spots[4].getMaxX(), spots[4].getMinY(), spots[4].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(800, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[4].changeOccupied();
							pieceArray[countRed].changePiecePosition(4);
							
						} else if (!spots[5].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[5].getMinX(), spots[5].getMaxX(), spots[5].getMinY(), spots[5].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[5].changeOccupied();
							pieceArray[countRed].changePiecePosition(5);
							
						} else if (!spots[6].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[6].getMinX(), spots[6].getMaxX(), spots[6].getMinY(), spots[6].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 200, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[6].changeOccupied();
							pieceArray[countRed].changePiecePosition(6);
							
						} else if (!spots[7].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[7].getMinX(), spots[7].getMaxX(), spots[7].getMinY(), spots[7].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(200, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[7].changeOccupied();
							pieceArray[countRed].changePiecePosition(7);
							
						} else if (!spots[8].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[8].getMinX(), spots[8].getMaxX(), spots[8].getMinY(), spots[8].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[8].changeOccupied();
							pieceArray[countRed].changePiecePosition(8);
							
						} else if (!spots[9].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[9].getMinX(), spots[9].getMaxX(), spots[9].getMinY(), spots[9].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[9].changeOccupied();
							pieceArray[countRed].changePiecePosition(9);
							
						} else if (!spots[10].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[10].getMinX(), spots[10].getMaxX(), spots[10].getMinY(), spots[10].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 650, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[10].changeOccupied();
							pieceArray[countRed].changePiecePosition(10);
							
						} else if (!spots[11].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[11].getMinX(), spots[11].getMaxX(), spots[11].getMinY(), spots[11].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[11].changeOccupied();
							pieceArray[countRed].changePiecePosition(11);
							
						} else if (!spots[12].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[12].getMinX(), spots[12].getMaxX(), spots[12].getMinY(), spots[12].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(650, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[12].changeOccupied();
							pieceArray[countRed].changePiecePosition(12);
							
						} else if (!spots[13].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[13].getMinX(), spots[13].getMaxX(), spots[13].getMinY(), spots[13].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(500, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[13].changeOccupied();
							pieceArray[countRed].changePiecePosition(13);
							
						} else if (!spots[14].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[14].getMinX(), spots[14].getMaxX(), spots[14].getMinY(), spots[14].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 350, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[14].changeOccupied();
							pieceArray[countRed].changePiecePosition(14);
							
						} else if (!spots[15].checkOccupied() && mouseClicked1 && mouseInRange(xLocation1, yLocation1,
								spots[15].getMinX(), spots[15].getMaxX(), spots[15].getMinY(), spots[15].getMaxY())) {
							StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
							StdDraw.filledCircle(920, num2, 35);
							StdDraw.setPenColor(StdDraw.RED);
							StdDraw.filledCircle(350, 500, 35);
							num2 -= 100;
							boolVal2 = false;
							xLocation1 = 0;
							yLocation1 = 0;
							mouseClicked1 = false;
							spots[15].changeOccupied();
							pieceArray[countRed].changePiecePosition(15);
						}
					}
					countRed++;
					numRed++;

				}
			}
			
			}
			//after each piece is placed on the game board, the piece is enabled so that it can be used later in the game
			if((j%2==0&&!whosFirst)||(j%2==1&&whosFirst)){
				pieceArray[countRed-1].changeEnabled();
			}
			else{
				pieceArray[countBlue-1].changeEnabled();
			}
			j++;
			
			
			boolVal = true;
			boolVal2 = true;
			int pieces=6;
			if((j%2==0&&!whosFirst)||(j%2==1&&whosFirst)){
				pieces=0;
			}
			//checking for mills in the set up of new game
			if(j>=0){
				
				G.millBoard(pieces,numBlue,numRed,fixingTurn,value,humanChecker,computerColor);//to deal with a mill
				G.resetMill();//to reset previous mills if they are not left in a line
				//prevents a player from getting a mill each turn for not moving their line of 3
			}
			if(humanChecker){
				if(j==1&&!whoTurn){
					G.turnButton();//button created in the gamePlay class to help prevent double clicking
				}
			}
			
			if(humanChecker){
				if(j!=1&& !whoTurn){
					G.turnButton();//button created in the gamePlay class to help prevent double clicking
				}
			}
			if(!humanChecker){	
				G.turnButton();//button created in the gamePlay class to help prevent double clicking
				
			}
			if(turn){
				turn=false;
			}
			else{
				turn=true;
			}
			
			if(whoTurn){
				whoTurn=false;
			}
			else{
				whoTurn=true;
			}
		}
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500, 100, 500, 50);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 130, "Piece Placement Complete!");
		mouseClicked=false;
		mouseClicked1=false;
		boolean fixer=true;
		if(humanChecker){
			fixer=whoTurn;
		}
		G.createGame(numRed,numBlue,humanChecker,fixer,computerColor,"new");//calls on the gamePlay.createGame method which allows the user to play the game until one player wins
	}
	
	//Continue game method for user to place pieces based on a previous game state, and informs them of any errors in set up
	//if the setup is valid, it will allow the user to continue their game
	public static void continueGame(boolean humanChecker,boolean computerColor) {
		
		
		StdDraw.setPenRadius(0.05);
		// Done button
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(500, 100, 85, 55);
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle(500, 100, 80, 50);

		// Toggle Blue
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(700, 900, 55, 55);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(700, 900, 50, 50);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(900, 900, 55, 55);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(900, 900, 50, 50);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(700, 900, "BLUE");
		StdDraw.text(900, 900, "RED");
		StdDraw.text(500, 100, "DONE");

		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(700, 980, 10);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(270, 900, "Please click on a position to place a piece");
		StdDraw.text(270, 870, "Toggle the color of the piece you wish to place");
		
		//creating objects for all the pieces that can be played
		pieceArray = new PlayerPiece[12];
		for (int i = 0; i < 12; i++) {
			if (i < 6) {
				SixMensMorris.pieceArray[i] = new PlayerPiece("BLUE", i + 1);
			} else {
				SixMensMorris.pieceArray[i] = new PlayerPiece("RED", i + 1);
			}
		}

		//creating objects for each spot on the game board that a player can put a piece
		spots = new moveLocations[16];
		spots[0] = new moveLocations(185, 215, 785, 815);
		spots[1] = new moveLocations(485, 515, 785, 815);
		spots[2] = new moveLocations(785, 815, 785, 815);
		spots[3] = new moveLocations(785, 815, 485, 515);
		spots[4] = new moveLocations(785, 815, 185, 215);
		spots[5] = new moveLocations(485, 515, 185, 215);
		spots[6] = new moveLocations(185, 215, 185, 215);
		spots[7] = new moveLocations(185, 215, 485, 515);
		spots[8] = new moveLocations(335, 365, 635, 665);
		spots[9] = new moveLocations(485, 515, 635, 665);
		spots[10] = new moveLocations(635, 665, 635, 665);
		spots[11] = new moveLocations(635, 665, 485, 515);
		spots[12] = new moveLocations(635, 665, 335, 365);
		spots[13] = new moveLocations(485, 515, 335, 365);
		spots[14] = new moveLocations(335, 365, 335, 365);
		spots[15] = new moveLocations(335, 365, 485, 515);

		
		if(humanChecker){
			StdDraw.setPenColor(StdDraw.BLACK);

			if(computerColor){
				
				StdDraw.text(700, 865, "Comp");
			}
			else{
				StdDraw.text(900, 865, "Comp");
			}

		}
		boolean mouseClicked3 = false;
		double xLocation3 = 0;
		double yLocation3 = 0;
		int color = 1; // Blue
		int num1 = 700;
		int num2 = 700;
		boolean mouseClicked = false;
		double xLocation = 0;
		double yLocation = 0;
		
		int bluePieces = 0;
		int redPieces = 0;
		
		while (!mouseClicked3 || (!(mouseInRange(xLocation3, yLocation3, 415, 585, 45, 155)))) {
			mouseClicked3 = StdDraw.mousePressed();
			xLocation3 = StdDraw.mouseX();
			yLocation3 = StdDraw.mouseY();
			
			//toggle button for which color the user wants to place onto the game board
			if (color == 1 && mouseClicked3 && (mouseInRange(xLocation3, yLocation3, 845, 955, 845, 955))) {
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.filledCircle(900, 980, 10);
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledCircle(700, 980, 10);
				color = 2;
			} else if (color == 2 && mouseClicked3 && (mouseInRange(xLocation3, yLocation3, 645, 755, 845, 955))) {
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.filledCircle(700, 980, 10);
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledCircle(900, 980, 10);
				color = 1;
			}
			
			//a bunch of ifs and else ifs to allow the user to place a piece on a specific spot
			//it alternates between movements for blue and movements for red
			mouseClicked = StdDraw.mousePressed();
			xLocation = StdDraw.mouseX();
			yLocation = StdDraw.mouseY();
			if (!spots[0].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation, spots[0].getMinX(),
					spots[0].getMaxX(), spots[0].getMinY(), spots[0].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(200, 800, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(0);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(200, 800, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(0);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[0].changeOccupied();
			} else if (!spots[1].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[1].getMinX(), spots[1].getMaxX(), spots[1].getMinY(), spots[1].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(500, 800, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(1);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(500, 800, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(1);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[1].changeOccupied();
			} else if (!spots[2].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[2].getMinX(), spots[2].getMaxX(), spots[2].getMinY(), spots[2].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(800, 800, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(2);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(800, 800, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(2);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[2].changeOccupied();
			} else if (!spots[3].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[3].getMinX(), spots[3].getMaxX(), spots[3].getMinY(), spots[3].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(800, 500, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(3);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(800, 500, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(3);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[3].changeOccupied();
			} else if (!spots[4].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[4].getMinX(), spots[4].getMaxX(), spots[4].getMinY(), spots[4].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(800, 200, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(4);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(800, 200, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(4);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[4].changeOccupied();
			} else if (!spots[5].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[5].getMinX(), spots[5].getMaxX(), spots[5].getMinY(), spots[5].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(500, 200, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(5);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(500, 200, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(5);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[5].changeOccupied();
			} else if (!spots[6].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[6].getMinX(), spots[6].getMaxX(), spots[6].getMinY(), spots[6].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(200, 200, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(6);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(200, 200, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(6);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[6].changeOccupied();
			} else if (!spots[7].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[7].getMinX(), spots[7].getMaxX(), spots[7].getMinY(), spots[7].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(200, 500, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(7);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(200, 500, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(7);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[7].changeOccupied();
			} else if (!spots[8].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[8].getMinX(), spots[8].getMaxX(), spots[8].getMinY(), spots[8].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(350, 650, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(8);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(350, 650, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(8);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[8].changeOccupied();
			} else if (!spots[9].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[9].getMinX(), spots[9].getMaxX(), spots[9].getMinY(), spots[9].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(500, 650, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(9);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(500, 650, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(9);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[9].changeOccupied();
			} else if (!spots[10].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[10].getMinX(), spots[10].getMaxX(), spots[10].getMinY(), spots[10].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(650, 650, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(10);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(650, 650, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(10);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[10].changeOccupied();
			} else if (!spots[11].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[11].getMinX(), spots[11].getMaxX(), spots[11].getMinY(), spots[11].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(650, 500, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(11);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(650, 500, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(11);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[11].changeOccupied();
			} else if (!spots[12].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[12].getMinX(), spots[12].getMaxX(), spots[12].getMinY(), spots[12].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(650, 350, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(12);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(650, 350, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(12);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[12].changeOccupied();
			} else if (!spots[13].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[13].getMinX(), spots[13].getMaxX(), spots[13].getMinY(), spots[13].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(500, 350, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(13);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(500, 350, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(13);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[13].changeOccupied();
			} else if (!spots[14].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[14].getMinX(), spots[14].getMaxX(), spots[14].getMinY(), spots[14].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(350, 350, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(14);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(350, 350, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(14);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[14].changeOccupied();
			} else if (!spots[15].checkOccupied() && mouseClicked && mouseInRange(xLocation, yLocation,
					spots[15].getMinX(), spots[15].getMaxX(), spots[15].getMinY(), spots[15].getMaxY())) {
				if (color == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(80, num1, 35);
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledCircle(350, 500, 35);
					num1 -= 100;
					
					if(bluePieces<6){
						SixMensMorris.pieceArray[bluePieces].changeEnabled();
						SixMensMorris.pieceArray[bluePieces].changePiecePosition(15);
					}
					
					bluePieces++;
				} else {
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(920, num2, 35);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(350, 500, 35);
					num2 -= 100;
					
					if(redPieces+6<12){
						SixMensMorris.pieceArray[redPieces+6].changeEnabled();
						SixMensMorris.pieceArray[redPieces+6].changePiecePosition(15);
					}
					
					redPieces++;
				}
				xLocation = 0;
				yLocation = 0;
				mouseClicked = false;
				spots[15].changeOccupied();
			}
			xLocation = 0;
			yLocation = 0;
			mouseClicked = false;

		}
		mouseClicked3=false;
		//get rid of some shapes
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledCircle(700, 980, 10);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledCircle(900, 980, 10);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500, 100, 105, 55);
		StdDraw.filledRectangle(700, 900, 705, 55);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//checks if the setup they gave is valid
		if(!(redPieces < 3 || bluePieces < 3)&&!(redPieces > 6 || bluePieces > 6)){
			boolean w=true;
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(500, 130, "Who goes first?");
			
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledRectangle(360, 70, 40, 40);
			StdDraw.filledRectangle(640, 70, 40, 40);

			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(360, 70, "BLUE");
			StdDraw.text(640, 70, "RED");
			
			if(humanChecker){
				StdDraw.setPenColor(StdDraw.BLACK);
				
				Font font = new Font("Sans Serif", 16, 16);
				StdDraw.setFont(font);
				if(computerColor){
		
					StdDraw.text(361, 45, "Comp");
				}
				else{
			
					StdDraw.text(639, 45, "Comp");
				}
				Font font1 = new Font("Sans Serif", 20, 20);
				StdDraw.setFont(font1);

			}
			
			boolean mouseClicked4=false;
			double xLocation4 = 0;
			double yLocation4 = 0;
			//while loop to ask who's turn it is to go first
			while(w){
				mouseClicked4 = StdDraw.mousePressed();
				xLocation4 = StdDraw.mouseX();
				yLocation4 = StdDraw.mouseY();
				if(mouseClicked4&&SixMensMorris.mouseInRange(xLocation4, yLocation4, 320, 400, 30, 110)){
					SixMensMorris.whosFirst=true;//blue's turn first
					w=false;
				}
				else if(mouseClicked4&&SixMensMorris.mouseInRange(xLocation4, yLocation4, 600, 680, 30, 110)){
					SixMensMorris.whosFirst=false;//red's turn first
					w=false;
				}
			}
			mouseClicked4=false;
			
			//reset shapes
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.filledRectangle(500, 130, 120, 25);
			StdDraw.filledRectangle(360, 70, 40, 40);
			StdDraw.filledRectangle(640, 70, 40, 40);
			StdDraw.filledCircle(80, 700, 35);
			StdDraw.filledCircle(80, 600, 35);
			StdDraw.filledCircle(80, 500, 35);
			StdDraw.filledCircle(80, 400, 35);
			StdDraw.filledCircle(80, 300, 35);
			StdDraw.filledCircle(80, 200, 35);
			StdDraw.filledCircle(920, 700, 35);
			StdDraw.filledCircle(920, 600, 35);
			StdDraw.filledCircle(920, 500, 35);
			StdDraw.filledCircle(920, 400, 35);
			StdDraw.filledCircle(920, 300, 35);
			StdDraw.filledCircle(920, 200, 35);
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
			if(humanChecker){
				StdDraw.setPenColor(StdDraw.BLACK);
				
				if(computerColor){
					StdDraw.text(80, 820, "Computer");
				}
				else{
					
					StdDraw.text(920, 820, "Computer");
				}

			}
			boolean fixer=true;
			if(humanChecker){
				fixer=SixMensMorris.whosFirst;
			}
			G=new gamePlay();
			G.createGame(redPieces,bluePieces,humanChecker, fixer,computerColor,"cont");//calls on the game so that they can proceed
		}
		else{//errors in the setup that the user gave
			if (redPieces < 3 || bluePieces < 3){//not enough pieces
				StdDraw.text(500, 125, "There must be a minimum of 3 pieces for each color!");
			}
			if (redPieces > 6 || bluePieces > 6){//too many pieces
				StdDraw.text(500, 75, "There cannot be more than 6 pieces of a color!");
			}
			Font font = new Font("Sans Serif",Font.BOLD,16);
			StdDraw.setFont(font);
			StdDraw.text(500, 905, "Please Restart Game!");
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.filledRectangle(910, 60, 50, 40);
		}
		
		
	}

}

