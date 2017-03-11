/*
 * Name & Student Number: Phillip Pavlich, 001414960
		  				  Prakhar Jalan, 001450321
		  				  Dinesh Balakrishnan, 001409123
 * Class Name: moveLocations Class
 * Class Description: This class keeps track of all locations that a 
player can put a piece on the board.It take in a minX value, maxX, minY and maxY 
which are the outer limits of the spot. These values are used as limits when checking 
if the location was clicked by the user. It uses get methods in order to return these values. 
The locations also have a numbering system to keep track of each.
 *
 */


public class moveLocations {
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private boolean occupied;

	public moveLocations(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.occupied = false;
	}
	
	//to get specific locations on the window for a spot
	public double getMinX() {
		return this.minX;
	}

	public double getMaxX() {
		return this.maxX;
	}

	public double getMinY() {
		return this.minY;
	}

	public double getMaxY() {
		return this.maxY;
	}
	
	//to check if a spot is occupied
	public boolean checkOccupied() {
		return this.occupied;
	}

	//change the occupancy of a location
	public void changeOccupied() {
		if (this.occupied) {
			this.occupied = false;
		} else {
			this.occupied = true;
		}
	}
	public double getMidX() {
		return (this.minX+this.maxX)/2;
	}
	
	public double getMidY() {
		return (this.minY+this.maxY)/2;
	}
}