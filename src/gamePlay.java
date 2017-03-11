/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: gamePlay Class
 * Class Description: This is a class that allows the user to continue the game after the board has been set up and all pieces have been played.
 * The game is run through the createGame method and will end when 1 player has less than 3 pieces meaning that that player loses. It will also end
 * if 1 player cannot move any of their pieces and is cornered. This means that player loses as well. There is a method called turnButton that creates 
 * a button to confirm that their turn is complete. These is also a millBoard method that checks for any mills on the current game board. It would deal 
 * with that situation. There is also a resetMill method that keeps track of previous mills so they are not counted again unless one of the pieces in that
 * mill is moved.
 *
 */
import java.awt.Font;

public class gamePlay {
	//some static variables to be used between classes
	static int firstMillIndex=-1;
	static int secondMillIndex=-1;
	int redPieces;
	int bluePieces;
	static int A=-1;
	static int B=-1;
	static int C=-1;
	static int AA=-1;
	static int BB=-1;
	static int CC=-1;
	
	static int[] redArray={-1,-1,-1};
	static int[] blueArray={-1,-1,-1};
	int[] tempR={-1,-1,-1};
	int[] tempB={-1,-1,-1};
	
	static int blueCountMill=0;
	static int redCountMill=0;
	static boolean game;
	
	public gamePlay(){
		
	}
	
	//to keep track of previous mills for both players
	public void resetMill(){
		
		//one of the pieces in the mill was moves so it deletes the restriction on that mill
		if(redCountMill>1 &&(A!=SixMensMorris.pieceArray[redArray[0]].getPiecePosition()||B!=SixMensMorris.pieceArray[redArray[1]].getPiecePosition() ||C!=SixMensMorris.pieceArray[redArray[2]].getPiecePosition())){
			redCountMill=0;
			secondMillIndex=-1;
		}
		if(blueCountMill>1&&(AA!=SixMensMorris.pieceArray[blueArray[0]].getPiecePosition()||BB!=SixMensMorris.pieceArray[blueArray[1]].getPiecePosition() ||CC!=SixMensMorris.pieceArray[blueArray[2]].getPiecePosition())){
			blueCountMill=0;
			firstMillIndex=-1;
			
		}
		
		//mill is stored here
		if(redCountMill==1){
			A=SixMensMorris.pieceArray[redArray[0]].getPiecePosition();
			B=SixMensMorris.pieceArray[redArray[1]].getPiecePosition();
			C=SixMensMorris.pieceArray[redArray[2]].getPiecePosition();
			redCountMill++;
			
		}
		if(blueCountMill==1){
			AA=SixMensMorris.pieceArray[blueArray[0]].getPiecePosition();
			BB=SixMensMorris.pieceArray[blueArray[1]].getPiecePosition();
			CC=SixMensMorris.pieceArray[blueArray[2]].getPiecePosition();
			blueCountMill++;
			
		}
	}
	
	//simple button to make the user confirm that their turn is done
	public void turnButton(){
		boolean i=true;
		boolean mouseClicked1=false;
		double xLocation1=0;
		double yLocation1=0;
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledRectangle(500, 875, 100, 30);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 875,"NEXT TURN");
		
		while(i){
			mouseClicked1 = StdDraw.mousePressed();
			xLocation1 = StdDraw.mouseX();
			yLocation1 = StdDraw.mouseY();
			if(mouseClicked1&&SixMensMorris.mouseInRange(xLocation1, yLocation1, 400, 600, 845, 905)){
				i=false;
			}
			
		}
		mouseClicked1=false;
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500, 875, 100, 30);
	}
	
	//mill checker that will deal with any mill and update the board, pieces, and locations accordingly
	public void millBoard(int pieces,int countBlue, int countRed, boolean whosFirst,double value,boolean humanChecker,boolean computerColor){
		int[][] mill={{0,1,2},{2,3,4},{4,5,6},{6,7,0},{8,9,10},{10,11,12},{12,13,14},{14,15,8}};

		
		int ans=countRed;
		int ans1=countBlue;
		if(value<0.5){
			ans=countBlue;
			ans1=countRed;
		}
	
		int counter=0;
		
		for(int q=0;q<mill.length;q++){
			int z=0;
			int inRow=0;
		
			if(whosFirst){//blue mill
				
				counter=0;
				if(q!=firstMillIndex){
					for(int t=0;t<3;t++){
						//for(int r=0+pieces;r<pieces+ans;r++){
						for(int r=0;r<6;r++){
							if(mill[q][t]==SixMensMorris.pieceArray[r].getPiecePosition() && SixMensMorris.pieceArray[r].getEnabled()){
								inRow++;
								
								tempB[z]=r;
								z++;
								
							}
						}
					}
				
				}
			}
			
			else{
				
				counter=6;
				if(q!=secondMillIndex){//red mill
					
					for(int t=0;t<3;t++){
						//for(int r=0+pieces;r<pieces+ans1;r++){
						for(int r=6;r<12;r++){
							if(mill[q][t]==SixMensMorris.pieceArray[r].getPiecePosition() &&SixMensMorris.pieceArray[r].getEnabled()){
								inRow++;
								tempR[z]=r;
								z++;
								
							}
						}
					}
				}
			}
			
			if(inRow==3){//if there was 3 in a row of one player's pieces
				
				if(whosFirst){//save mill to blue variables
	
					firstMillIndex=q;
					blueCountMill++;
					blueArray[0]=tempB[0];
					blueArray[1]=tempB[1];
					blueArray[2]=tempB[2];
				}
				else{//save mill to red variables
	
					secondMillIndex=q;
					redCountMill++;
					redArray[0]=tempR[0];
					redArray[1]=tempR[1];
					redArray[2]=tempR[2];
				}
				
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(500,50,"Please Click on an Opponent Piece to remove!");
				
				Font font1 = new Font("Sans Serif", 30, 30);
				StdDraw.setFont(font1);
				StdDraw.text(500,900,"Mill!");
				
				Font font = new Font("Sans Serif", 20, 20);
				StdDraw.setFont(font);
				
				boolean removePiece=true;
				boolean mouseClicked1;
				double xLocation1;
				double yLocation1;
				
				int opponentCount=0;
				if(counter==0){
					opponentCount=6;
				}
				
				boolean otherThanMill=false;
				int D=-1;
				int E=-1;
				int F=-1;
				boolean changed=false;
				
				if(whosFirst){
					for(int Q=0;Q<mill.length;Q++){
						int numbs=0;
						for(int I=0;I<3;I++){
							for(int W=0;W<3;W++){
								if(mill[Q][I]!=-1 && gamePlay.redArray[W]!=-1){
									if(mill[Q][I]==SixMensMorris.pieceArray[gamePlay.redArray[W]].getPiecePosition()){
										numbs++;
									}
								}
							}
						}
						if(numbs==3){
							changed=true;
						}
					}
				}
				else{
					for(int Q=0;Q<mill.length;Q++){
						int numbs1=0;
						for(int I=0;I<3;I++){
							for(int W=0;W<3;W++){
								if(mill[Q][I]!=-1 && gamePlay.blueArray[W]!=-1){
									if(mill[Q][I]==SixMensMorris.pieceArray[gamePlay.blueArray[W]].getPiecePosition()){
										numbs1++;
									}
								}
							}
						}
						if(numbs1==3){
							changed=true;
						}
					}
				}
				
				if(whosFirst&&changed){
					D=gamePlay.redArray[0];
					E=gamePlay.redArray[1];
					F=gamePlay.redArray[2];
					if(SixMensMorris.G.redPieces<=3){
						otherThanMill=true;
					}
				}
				else if(!whosFirst&&changed){
					D=gamePlay.blueArray[0];
					E=gamePlay.blueArray[1];
					F=gamePlay.blueArray[2];
					if(SixMensMorris.G.bluePieces<=3){
						otherThanMill=true;
					}
				}
				
				
				boolean yes=false;
				//blues turn
				if(whosFirst && humanChecker){
					if(computerColor){
						yes=true;
					}
				}
				//reds turn
				else if(!whosFirst && humanChecker){
					if(!computerColor){
						yes=true;
					}
				}
				
				if(humanChecker && yes){
					//execute code for removal based on opponent
					ComputerMove removal=new ComputerMove();
					removal.computerRemoval(computerColor);
				}
				else{
				while(removePiece){//while loop in order to let the player that earned a mill remove an opponent's piece
					mouseClicked1 = StdDraw.mousePressed();
					xLocation1 = StdDraw.mouseX();
					yLocation1 = StdDraw.mouseY();
					
					for(int R=0+opponentCount;R<opponentCount+6;R++){
						if(((otherThanMill)||(F!=R && E!=R && D!=R))&&SixMensMorris.pieceArray[R].getEnabled()&&mouseClicked1 && SixMensMorris.mouseInRange(xLocation1, yLocation1,SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMinX(),SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMaxX(),SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMinY(),SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMaxY())){
							
							
							StdDraw.setPenColor(StdDraw.BLACK);
							StdDraw.filledCircle(SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMidX(),SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].getMidY(),35);
							removePiece=false;
							
							
							SixMensMorris.spots[SixMensMorris.pieceArray[R].getPiecePosition()].changeOccupied();
							SixMensMorris.pieceArray[R].changeEnabled();
							SixMensMorris.pieceArray[R].changePiecePosition(16);
							if(opponentCount==6){//decrement opponents number of pieces
								SixMensMorris.G.redPieces--;
								SixMensMorris.numRed--;
							}
							else{//decrement opponents number of pieces
								SixMensMorris.G.bluePieces--;
								SixMensMorris.numBlue--;
							}
							
							
						}
					}
				
				}
				}
				mouseClicked1=false;
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(500,900,100,50);
				StdDraw.filledRectangle(500,50,350,30);
			}
		}	
		
	}
	
	//createGame method to play the whole game after the setup
	public void createGame(int red,int blue,boolean humanChecker,boolean whoTurn,boolean computerColor, String which){//if a piece is deleted set that index of pieceArray to null
		
		game=true;
		boolean end=false;
		moveLocations currentSpot=null;
		int count2=0;
		int count=0;
		SixMensMorris.G.redPieces=red;
		SixMensMorris.G.bluePieces=blue;
		
		//all possible positions that a specific spot can move to
		int[][] positionMatrix={{1,7,-1},{0,2,9},{1,3,-1},{2,4,11},{3,5,-1},{4,6,13},{5,7,-1},{0,6,15},{9,15,-1},{1,8,10},{9,11,-1},{3,10,12},{11,13,-1},{5,12,14},{13,15,-1},{7,8,14}};
		SaveGame but=new SaveGame();
		but.saveButton();
		boolean compT=false;
		if(humanChecker){
			
			if(whoTurn){
				compT=true;
			}
			if(which.equals("cont")){
				if(!computerColor){
					if(compT){
						compT=false;
					}
					else{
						compT=true;
					}
				}
			}
			
		}
		
		while(game){//while loop to keep the game running
			
			
			int pieces=0;
			String color="";
			
			//if/ else to deal with who's turn it is
			if(!SixMensMorris.whosFirst){
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(100, 900, 100, 50);
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(900, 900, 100, 50);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(900, 900, "Red's Turn!");
				pieces=6;
				color="RED";
				
				
			}
			else{
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(900, 900, 100, 50);
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledRectangle(100, 900, 100, 50);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(100, 900, "Blue's Turn!");
				color="BLUE";
			}
			
			boolean c=false;
			boolean move=true;
			boolean mouseClicked=false;
			double xLocation=0;
			double yLocation=0;
			double previousX=-1.0;
			double previousY=-1.0;
			PlayerPiece previousPiece=null;
			PlayerPiece activatedPiece=null;
			int previousI=-1;
			if(compT){
				ComputerMove compMod=new ComputerMove();
				if(computerColor){
					
					compMod.moveAIPiece("BLUE");
					
				}
				else{
					compMod.moveAIPiece("RED");
				}
				
				SixMensMorris.G.millBoard(pieces,6,6,SixMensMorris.whosFirst,0,humanChecker,computerColor);//0 value???
				//checking if a player has less than 3 pieces which means that the game is over
				if (SixMensMorris.G.redPieces < 3){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 100, "Blue Player wins!");
					game=false;
				
				}
				else if (SixMensMorris.G.bluePieces < 3){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 100, "Red Player wins!");
					game=false;
				}
				//reset mill variables
				SixMensMorris.G.resetMill();
			}
			else{
			while(move){//while loop for each individual move
				mouseClicked = StdDraw.mousePressed();
				xLocation = StdDraw.mouseX();
				yLocation = StdDraw.mouseY();
				
				
				if(mouseClicked&&SixMensMorris.mouseInRange(xLocation, yLocation, 860, 960, 20, 100)){
					but.saveInfo(humanChecker,computerColor);
				}
				
				for(int i=pieces;i<6+pieces;i++){
					if(SixMensMorris.pieceArray[i].getEnabled()){
					double a=SixMensMorris.spots[SixMensMorris.pieceArray[i].getPiecePosition()].getMidX();
					double b=SixMensMorris.spots[SixMensMorris.pieceArray[i].getPiecePosition()].getMidY();
					if (mouseClicked && SixMensMorris.mouseInRange(xLocation, yLocation,a-35, a+35, b-35, b+35)) {//check's if the player clicked one of their pieces
						if(SixMensMorris.whosFirst){
							
							if(previousI!=i  && previousI!=-1){//activates clicked piece
								activatedPiece.toggleActivation();
								StdDraw.setPenColor(StdDraw.BLUE);
								StdDraw.filledCircle(SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidX(), SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidY(), 5);
							}
						}
						
						else{
							if(previousI!=i && previousI!=-1){//activates clicked piece
								activatedPiece.toggleActivation();
								StdDraw.setPenColor(StdDraw.RED);
								StdDraw.filledCircle(SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidX(), SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidY(), 5);
								
							}
						}
						
						
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledCircle(a, b, 5);
						
						activatedPiece=SixMensMorris.pieceArray[i];
						if(activatedPiece.getActivation()==false){
							activatedPiece.toggleActivation();
						}
						
						previousX=a;//saves previous values for as long as this while loop runs
						previousY=b;
						previousI=i;
						c=true;
					}
				}
				
				
				if(c && activatedPiece.getActivation()){//checking if the player has an activated piece
				
					
					//this section checks if the user clicked on a location that the activated piece can move to
					int pieceLocation=activatedPiece.getPiecePosition();
					int numPositions=positionMatrix[pieceLocation].length;
					
					for(int y=0;y<numPositions;y++){
						if(positionMatrix[activatedPiece.getPiecePosition()][y]!=-1){
						moveLocations k=SixMensMorris.spots[positionMatrix[activatedPiece.getPiecePosition()][y]];
						if(SixMensMorris.pieceArray[previousI].getActivation()==true && mouseClicked && SixMensMorris.mouseInRange(xLocation, yLocation,k.getMidX()-35,k.getMidX()+35, k.getMidY()-35, k.getMidY()+35)){
							
							count=0;
							for(int e=0;e<16;e++){
								if(SixMensMorris.mouseInRange(xLocation, yLocation,SixMensMorris.spots[e].getMidX()-35,SixMensMorris.spots[e].getMidX()+35, SixMensMorris.spots[e].getMidY()-35, SixMensMorris.spots[e].getMidY()+35)){
									currentSpot=new moveLocations(SixMensMorris.spots[e].getMinX(),SixMensMorris.spots[e].getMaxX(),SixMensMorris.spots[e].getMinY(),SixMensMorris.spots[e].getMaxY());
									count=e;
									//if the move is possible, all pieces and locations are updated acordingly to make the move
								}
							}
							if(SixMensMorris.spots[count].checkOccupied()!=true){
								StdDraw.setPenColor(StdDraw.BLACK);
								StdDraw.filledCircle(SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidX(), SixMensMorris.spots[activatedPiece.getPiecePosition()].getMidY(), 35);
								if(color.equals("BLUE")){
									StdDraw.setPenColor(StdDraw.BLUE);
									StdDraw.filledCircle(currentSpot.getMidX(), currentSpot.getMidY(), 35);//draw circle for blue
								}
								else{
									StdDraw.setPenColor(StdDraw.RED);
									StdDraw.filledCircle(currentSpot.getMidX(), currentSpot.getMidY(), 35);//draw circle for red
								}
						
								move=false;
							
								//update all variables after the move has been made
								SixMensMorris.spots[activatedPiece.getPiecePosition()].changeOccupied();
								SixMensMorris.spots[count].changeOccupied();
							
								SixMensMorris.pieceArray[previousI].changePiecePosition(count);
							
								activatedPiece.toggleActivation();
							}
							
						}
						}
					}
				}
				
			}
				
			//checks if the player has no place to go and is cornered which would mean that that player loses the game
			end=false;
			for(int i=pieces;i<6+pieces;i++){
				if(SixMensMorris.pieceArray[i].getEnabled()){
					for(int y=0;y<3;y++){
						if(positionMatrix[SixMensMorris.pieceArray[i].getPiecePosition()][y]!=-1){
							if(!SixMensMorris.spots[positionMatrix[SixMensMorris.pieceArray[i].getPiecePosition()][y]].checkOccupied()){
								end=true;
							}
						}
					}
				}
			}
			
			if(!end){//player cannot move end turn
				move=false;
			}
			
			}
			mouseClicked=false;
			if(end){
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle(SixMensMorris.spots[count].getMidX(),SixMensMorris.spots[count].getMidY(),5);
				if(SixMensMorris.whosFirst){
					StdDraw.setPenColor(StdDraw.BLUE);
				}
				else{
					StdDraw.setPenColor(StdDraw.RED);
				}
				StdDraw.filledCircle(currentSpot.getMidX(),currentSpot.getMidY(),5);
				//mill the board
				SixMensMorris.G.millBoard(pieces,bluePieces,redPieces,SixMensMorris.whosFirst,0,humanChecker,computerColor);
			
				if(count2==0){//eliminates shape
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledRectangle(500, 130, 225, 25);
					count2++;
				}
				//change who's turn it is
				if(!SixMensMorris.whosFirst){
					SixMensMorris.whosFirst=true;
				}
				else{
					SixMensMorris.whosFirst=false;
				}
				if(humanChecker){
					turnButton();
				}
				//checking if a player has less than 3 pieces which means that the game is over
				if (SixMensMorris.G.redPieces < 3){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 100, "Blue Player wins!");
					game=false;
				
				}
				else if (SixMensMorris.G.bluePieces < 3){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 100, "Red Player wins!");
					game=false;
				}
			}
			else{
				//deals with if a player cannot move which ends the game
				if(SixMensMorris.whosFirst){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 50, "Red Player wins!");
					StdDraw.text(500, 100, "Blue Player cannot move!");
					game=false;
				}
				else{
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(500, 50, "Blue Player wins!");
					StdDraw.text(500, 100, "Red Player cannot move!");
					game=false;
				}
			}
			//reset mill variables
			SixMensMorris.G.resetMill();
			
			
		}
		if(humanChecker){
				if(compT){
					compT=false;
					if(SixMensMorris.whosFirst){
						SixMensMorris.whosFirst=false;
					}
					else{
						SixMensMorris.whosFirst=true;
					}
				}
				else{
					compT=true;
				}
				
		}
	
		}
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(100, 900, 100, 50);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(900, 900, 100, 50);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(500, 875, "Please Restart Game!");
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(910, 60, 50, 40);
	}
	
}
