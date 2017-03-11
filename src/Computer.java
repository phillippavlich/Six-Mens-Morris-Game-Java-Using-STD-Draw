/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: Computer Class
 * Class Description: This is a class that allows the user to make a choice between playing a new game or continued game that is against
 * another human or against a computer. If it is against a computer, it randomly chooses if the computer is red or blue and whether the 
 * computer gets to go first or second.
 * 
 *
 */

import java.awt.Font;

public class Computer {
	public Computer(){
		
	}
	
	//creates two buttons in which the user can click to choose if they want to play against a computer or against another human
	protected boolean checkPlayer(){
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(500, 500, 1000, 1000);
		
		Font font = new Font("Sans Serif", 20, 20);
		StdDraw.setFont(font);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		StdDraw.filledRectangle(250, 750, 205, 75);
		StdDraw.filledRectangle(750, 750, 205, 75);
		
		
		
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius(0.05);

		StdDraw.filledRectangle(250, 750, 200, 70);
		StdDraw.filledRectangle(750, 750, 200, 70);
		

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(250, 750, "Play Vs Human");
		StdDraw.text(750, 750, "Play Vs Computer");

		boolean mouseClick = false;
		
		double xLocations = 0;
		double yLocations = 0;
		while (!mouseClick || (!(SixMensMorris.mouseInRange(xLocations, yLocations, 45, 455, 675, 825))
				&& !(SixMensMorris.mouseInRange(xLocations, yLocations, 545, 955, 675, 825)))) {
			
			mouseClick = StdDraw.mousePressed();
			xLocations = StdDraw.mouseX();
			yLocations = StdDraw.mouseY();

		}
		//human
		if(SixMensMorris.mouseInRange(xLocations, yLocations, 95, 505, 675, 825)){
			return false;
		}
		//computer
		else{
			return true;
		}
		
		
	}
	
	//randomly chooses a boolean for the computer's color and order
	protected boolean whosTurn(){
		double value=Math.random()*2;
		boolean whoTurn=false;
		if(value<1.0){
			whoTurn=true;
			
		}
		else{
			whoTurn=false;
		
		}
		
		return whoTurn;
	}
	
}
