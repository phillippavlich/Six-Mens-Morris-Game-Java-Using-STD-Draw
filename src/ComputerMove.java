/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: computerMove Class
 * Class Description: This is a class that deals with all operations of a computer. It allows the user to move a piece, get a mill,
 * remove an opponents piece if a mill is made, place pieces at the start of the game. This class acts as the computer when the game
 * is being played.
 *
 */

import java.util.Random;
import java.util.ArrayList;

public class ComputerMove {

	// For piece positions during placement
	private int randomPosition;

	// For piece positions during movement
	private int randomPiece;
	private int randomMove;

	private ArrayList<Integer> movablePieces = new ArrayList<Integer>();
	private ArrayList<Integer> movablePositions = new ArrayList<Integer>();

	int[][] positionMatrix = { { 1, 7, -1 }, { 0, 2, 9 }, { 1, 3, -1 }, { 2, 4, 11 }, { 3, 5, -1 }, { 4, 6, 13 },
			{ 5, 7, -1 }, { 0, 6, 15 }, { 9, 15, -1 }, { 1, 8, 10 }, { 9, 11, -1 }, { 3, 10, 12 }, { 11, 13, -1 },
			{ 5, 12, 14 }, { 13, 15, -1 }, { 7, 8, 14 } };

	public ComputerMove() {
		
	}
	
	//method to allow the computer to remove an opponents piece if the computer made a mill
	public void computerRemoval(boolean computerColor){
		int[][] mill={{0,1,2},{2,3,4},{4,5,6},{6,7,0},{8,9,10},{10,11,12},{12,13,14},{14,15,8}};
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500,900,100,50);
		StdDraw.filledRectangle(500,50,350,30);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 920, "Computer Mill");
		StdDraw.text(500, 880, "It removed one of your pieces");
		StdDraw.text(500, 50, "Click Done to proceed!");
		
		//remove piece
		int opponentCount=0;
		if(computerColor){
			opponentCount=6;
		}
		
		ArrayList<Integer> possibilities=new ArrayList<Integer>();
		ArrayList<Integer> enabledOnes=new ArrayList<Integer>();
		
		int value=gamePlay.secondMillIndex;
		if(opponentCount==0){
			value=gamePlay.firstMillIndex;
		}
		
		
		for(int R=0+opponentCount;R<opponentCount+6;R++){
			if(SixMensMorris.pieceArray[R].getEnabled()){	
				enabledOnes.add(R);
				boolean checkMillSpot=false;
				if(value!=-1){
					for(int a=0;a<3;a++){
						if(mill[value][a]!=-1){
							if(mill[value][a]==SixMensMorris.pieceArray[R].getPiecePosition()){
								checkMillSpot=true;
							}
						}
					}
				}
				if(!checkMillSpot){
					possibilities.add(R);
				}
				
			}
		}
		
		if(possibilities.size()==0){
			Random rand=new Random();
			int rand1=enabledOnes.get(rand.nextInt(enabledOnes.size()));//this gives an index of pieceArray
			int positionSpot=SixMensMorris.pieceArray[rand1].getPiecePosition();
			
			SixMensMorris.spots[positionSpot].changeOccupied();
			SixMensMorris.pieceArray[rand1].changeEnabled();
			SixMensMorris.pieceArray[rand1].changePiecePosition(16);
			
			//do drawing
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(SixMensMorris.spots[positionSpot].getMidX(), SixMensMorris.spots[positionSpot].getMidY(), 35);
			
		}
		else{
			Random rand=new Random();
			int rand1=possibilities.get(rand.nextInt(possibilities.size()));//this gives an index of pieceArray
			int positionSpot=SixMensMorris.pieceArray[rand1].getPiecePosition();
			
			SixMensMorris.spots[positionSpot].changeOccupied();
			SixMensMorris.pieceArray[rand1].changeEnabled();
			SixMensMorris.pieceArray[rand1].changePiecePosition(16);
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(SixMensMorris.spots[positionSpot].getMidX(), SixMensMorris.spots[positionSpot].getMidY(), 35);
		}
						
		if(opponentCount==6){//decrement opponents number of pieces
			SixMensMorris.G.redPieces--;
			SixMensMorris.numRed--;
		}
		else{//decrement opponents number of pieces
			SixMensMorris.G.bluePieces--;
			SixMensMorris.numBlue--;
		}
					
		millButton();
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500,900,250,50);
		
		
	}
	
	//button to confirm with the user that the computer has made a mill and removed one of the users pieces
	public void millButton(){
		boolean i=true;
		boolean mouseClicked10=false;
		double xLocation10=0;
		double yLocation10=0;
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(50, 50, 40, 30);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(50, 50,"Done");
		
		while(i){
			mouseClicked10 = StdDraw.mousePressed();
			xLocation10 = StdDraw.mouseX();
			yLocation10 = StdDraw.mouseY();
			if(mouseClicked10&&SixMensMorris.mouseInRange(xLocation10, yLocation10, 10, 90, 20, 80)){
				i=false;
			}
			
		}
		
		mouseClicked10=false;
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(50, 50, 42, 32);
	}
	
	//allows the computer to place pieces onto the board at the start of a game 
	public void placeAIPiece(int pieceNum) {
		Random rand = new Random();
		this.randomPosition = rand.nextInt(16);
		while (checkOccupied(this.randomPosition)) {
			this.randomPosition = rand.nextInt(16);
		}
		
		SixMensMorris.pieceArray[pieceNum].changePiecePosition(this.randomPosition);
		SixMensMorris.spots[this.randomPosition].changeOccupied();
		if (pieceNum<6) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(SixMensMorris.spots[this.randomPosition].getMidX(),SixMensMorris.spots[this.randomPosition].getMidY(),35);
			
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			
			//removing side circles on the board
			if(pieceNum==0){
				StdDraw.filledCircle(80, 700, 35);
			}
			else if(pieceNum==1){
				StdDraw.filledCircle(80, 600, 35);
			}
			if(pieceNum==2){
				StdDraw.filledCircle(80, 500, 35);
			}
			if(pieceNum==3){
				StdDraw.filledCircle(80, 400, 35);
			}
			if(pieceNum==4){
				StdDraw.filledCircle(80, 300, 35);
			}
			if(pieceNum==5){
				StdDraw.filledCircle(80, 200, 35);
			}
			
		}
		else {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(SixMensMorris.spots[this.randomPosition].getMidX(),SixMensMorris.spots[this.randomPosition].getMidY(),35);
			
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			//removing side circles on the board
			if(pieceNum==6){
				StdDraw.filledCircle(920, 700, 35);
			}
			else if(pieceNum==7){
				StdDraw.filledCircle(920, 600, 35);
			}
			if(pieceNum==8){
				StdDraw.filledCircle(920, 500, 35);
			}
			if(pieceNum==9){
				StdDraw.filledCircle(920, 400, 35);
			}
			if(pieceNum==10){
				StdDraw.filledCircle(920, 300, 35);
			}
			if(pieceNum==11){
				StdDraw.filledCircle(920, 200, 35);
			}
		}
	}
	
	//method to check if a location on the game board is occupied or not
	public boolean checkOccupied(int position) {
	
		if (SixMensMorris.spots[position].checkOccupied()) {
			return true;
		} else {
			return false;
		}
	}
	
	//allows the computer to make a move, it updates all the properties of the piece and locations
	public void moveAIPiece(String aiColor) {
		this.movablePieces = new ArrayList<Integer>();
		this.movablePositions = new ArrayList<Integer>();
		if (aiColor.equals("BLUE")) {

			initializeMovable(aiColor);
			
			
			Random rand1 = new Random();
			
			if(this.movablePieces.size()!=0){
				this.randomPiece = this.movablePieces.get(rand1.nextInt(this.movablePieces.size()));//error 1 cant use size need values
			
				int position = SixMensMorris.pieceArray[this.randomPiece].getPiecePosition();
			
				for (int i = 0; i < 3; i++){
					if(positionMatrix[position][i] != -1){
						if (!(SixMensMorris.spots[positionMatrix[position][i]].checkOccupied())){
							movablePositions.add(positionMatrix[position][i]);
						}
					}
				}
			}
			if(this.movablePositions.size()!=0){
				Random rand2 = new Random();
			
				this.randomMove = this.movablePositions.get(rand2.nextInt(this.movablePositions.size()));
			
				int old=SixMensMorris.pieceArray[this.randomPiece].getPiecePosition();
				SixMensMorris.pieceArray[this.randomPiece].changePiecePosition(this.randomMove);
				SixMensMorris.spots[old].changeOccupied();
				SixMensMorris.spots[SixMensMorris.pieceArray[this.randomPiece].getPiecePosition()].changeOccupied();
			
				redrawPieces(old,SixMensMorris.pieceArray[this.randomPiece].getPiecePosition(), aiColor);
			}
			else{
				cornered(aiColor);
			}
			
		} else if (aiColor.equals("RED")) {

			initializeMovable(aiColor);
			
			Random rand1 = new Random();
			
			if(this.movablePieces.size()!=0){
				this.randomPiece = this.movablePieces.get(rand1.nextInt(this.movablePieces.size()));
			
				int position = SixMensMorris.pieceArray[this.randomPiece].getPiecePosition();
			
				for (int i = 0; i < 3; i++){
					if(positionMatrix[position][i] != -1){
						if (!(SixMensMorris.spots[positionMatrix[position][i]].checkOccupied())){
							movablePositions.add(positionMatrix[position][i]);
						}
					}
				}
			}
			
			if(this.movablePositions.size()!=0){
				Random rand2 = new Random();
				this.randomMove = this.movablePositions.get(rand2.nextInt(this.movablePositions.size()));

				int old=SixMensMorris.pieceArray[this.randomPiece].getPiecePosition();
				SixMensMorris.pieceArray[this.randomPiece].changePiecePosition(this.randomMove);
				SixMensMorris.spots[old].changeOccupied();
				SixMensMorris.spots[SixMensMorris.pieceArray[this.randomPiece].getPiecePosition()].changeOccupied();
			
				redrawPieces(old,SixMensMorris.pieceArray[this.randomPiece].getPiecePosition(), aiColor);
			}
			else{
				cornered(aiColor);
			}
		}
	}
	
	//method to add all pieces that can potentially make a move to an arrayList so that they can be used for the computer's move
	public void initializeMovable(String aiColor) {
		
		if (aiColor.equals("BLUE")) {
			for (int i = 0; i < 6; i++) {
				
				if (SixMensMorris.pieceArray[i].getEnabled() && movable(SixMensMorris.pieceArray[i])){
					this.movablePieces.add(i);
				}
			}

		} else if (aiColor.equals("RED")) {
			for (int i = 6; i < 12; i++) {
				
				if (SixMensMorris.pieceArray[i].getEnabled() && movable(SixMensMorris.pieceArray[i])){
					this.movablePieces.add(i);
				}
			}

		}
	}
	
	//returns a boolean based on if the piece is movable or not
	public boolean movable(PlayerPiece piece){
		int position = piece.getPiecePosition();
		boolean movableBool = false;
		for (int i = 0; i < 3; i++){
			if(positionMatrix[position][i] != -1){
				if (!(SixMensMorris.spots[positionMatrix[position][i]].checkOccupied())){
					movableBool = true;
				}
			}
		}
		return movableBool;
	}
	
	//after the computer has chosen a move to make, this method draws the move and undraws the previous spot where the piece was
	public void redrawPieces(int from, int to, String aiColor){
		if (aiColor.equals("BLUE")) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(SixMensMorris.spots[from].getMidX(),SixMensMorris.spots[from].getMidY(),35);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(SixMensMorris.spots[to].getMidX(),SixMensMorris.spots[to].getMidY(),35);
			
			
		} else if (aiColor.equals("RED")) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(SixMensMorris.spots[from].getMidX(),SixMensMorris.spots[from].getMidY(),35);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(SixMensMorris.spots[to].getMidX(),SixMensMorris.spots[to].getMidY(),35);

		} 
	}
	
	//deals with a game ending option if the computer is not able to make a move
	public void cornered(String aiColor){
		//deals with if a player cannot move which ends the game
	     if(aiColor.equals("BLUE")){
	      StdDraw.setPenColor(StdDraw.BLACK);
	      StdDraw.text(500, 50, "Red Player wins!");
	      StdDraw.text(500, 100, "Blue Player cannot move!");
	      gamePlay.game=false;
	     }
	     else if(aiColor.equals("RED")){
	      StdDraw.setPenColor(StdDraw.BLACK);
	      StdDraw.text(500, 50, "Blue Player wins!");
	      StdDraw.text(500, 100, "Red Player cannot move!");
	      gamePlay.game=false;
	     }
	}

}
