/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: PlayerPiece Class
 * Class Description: This is a class that keeps track of all 12 pieces. 
It has a parameter of  type string that is the color of which team it is (either blue or red). 
We have labeled our board with place values starting at the upper left corner on the outside box, 
going counter clockwise and continuing to the middle one. We are keeping track of where all the 
pieces are located on the board. Pieces are also labeled as 1 to 12 so that we can isolate a specific piece. 
All pieces created from this class are saved into an array. There are get methods to retrieve the color and position.
 *
 */

public class PlayerPiece {
	private String pieceColor;
	private int pieceNum;
	private boolean pieceActivated;
	private final int pieceRadius = 35;
	private int piecePosition;
	private boolean enabled;
	
	public PlayerPiece(String color, int pieceNum){
		this.pieceColor = color;
		this.pieceNum = pieceNum;
		this.pieceActivated = false;
		this.piecePosition=0;
		this.enabled=false;//
	
	}
	
	//allows you to enable or disable a piece(when the piece is milled)
	public void changeEnabled(){
		if(this.enabled){
			this.enabled=false;
		}
		else{
			this.enabled=true;
		}
	}
	
	public boolean getEnabled(){
		return this.enabled;
	}
	
	public String getColor(){
		return this.pieceColor;
	}
	public int getPieceNum(){
		return this.pieceNum;
	}
	
	//to get the position that the piece is on
	public int getPiecePosition(){
		return this.piecePosition;
	}
	
	//change the position
	public void changePiecePosition(int i){
		this.piecePosition=i;
	}
	public void toggleActivation(){
		if(this.pieceActivated==true){
			this.pieceActivated=false;
		}
		else{
			this.pieceActivated=true;
		}
	}
	
	//check activation for if the piece is clicked on
	public boolean getActivation(){
		return this.pieceActivated;
	}
}
