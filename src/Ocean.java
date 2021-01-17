import java.util.Random;

public class Ocean {

	private Ship[][] ships;
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	/**
	 * Creates a new Ocean object with all EmptySea objects
	 */
	public Ocean() {
		ships = new Ship[10][10];
		for(int i = 0; i < ships.length; i++) {
			for(int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea(i, j);
			}
		}
		
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}	
	
	/**
	 * Iterates through all ships to be placed and places each on legally.
	 */
	void placeAllShipsRandomly() {
		
		Battleship b = new Battleship(0, 0, true);
		Cruiser c1 = new Cruiser(0, 0, true);
		Cruiser c2 = new Cruiser(0, 0, true);
		Destroyer d1 = new Destroyer(0, 0, true);
		Destroyer d2 = new Destroyer(0, 0, true);
		Destroyer d3 = new Destroyer(0, 0, true);
		Submarine s1 = new Submarine(0, 0, true);
		Submarine s2 = new Submarine(0, 0, true);
		Submarine s3 = new Submarine(0, 0, true);
		Submarine s4 = new Submarine(0, 0, true);
		
		Ship[] toPlace = {b, c1, c2, d1, d2, d3, s1, s2, s3, s4};
		
		for(int i = 0; i < toPlace.length; i++) {
			Random r = new Random();
			int row = r.nextInt(10);
			int column = r.nextInt(10);
			boolean horizontal = r.nextBoolean();
			// Checks if the ship can be placed at the randomly selected coordinates. If not, reselect coordinates.
			while(!((toPlace[i]).okToPlaceShipAt(row, column, horizontal, this))) {
				row = r.nextInt(10);
				column = r.nextInt(10);
				horizontal = r.nextBoolean();
			}
			// Once valid coordinates are identified, place the ship.
			toPlace[i].placeShipAt(row, column, horizontal, this);
		}
	}
	
	/**
	 * Checks if a spot has a ship or an EmptySea
	 * @param row
	 * @param column
	 * @return true if the spot has one of the four ship types. False otherwise.
	 */
	boolean isOccupied(int row, int column) {
		return !(ships[row][column]).getShipType().equals("empty");
	}
	
	/**
	 * Registers the user's attempt to fire at a spot on the board
	 * @param row
	 * @param column
	 * @return true if there was a successful hit on an untouched spot. False otherwise.
	 */
	boolean shootAt(int row, int column) {
		if(isOccupied(row, column) && !ships[row][column].isSunk()) {
			if(ships[row][column].shootAt(row, column)) {
				if(ships[row][column].isSunk()) {
					shipsSunk++;
					System.out.println("You just sank a " + (ships[row][column]).getShipType() + "!");
					System.out.println();
				}
				else {
					System.out.println("Hit!");
					System.out.println();
				}
				shotsFired++;
				hitCount++;
				return true;	
			}
			else {
				return false;
			}
		}
		else if(!isOccupied(row, column)) {
			// mark the empty spot as shot at if it hasn't been shot already. Otherwise, ignore the attempted shot.
			EmptySea es = (EmptySea) ships[row][column];
			if(!es.isShotAtYet()) {
				es.shootAt(row, column);
				System.out.println("Miss!");
				System.out.println();		
				shotsFired++;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks if all 10 ships have been sunk
	 * @return Whether all 10 ships have been sunk
	 */
	boolean isGameOver() {
		return shipsSunk == 10;
	}
	
	/**
	 * Prints out the status of the board at that stage in the gameplay
	 */
	void print() {
		System.out.print("  ");
		for(int k = 0; k < ships.length; k++) {
			System.out.print(k + " ");
		}
		System.out.println();
		
		for(int i = 0; i < ships.length; i++) {
			System.out.print(i + " ");
			for(int j = 0; j < ships[i].length; j++) {
				System.out.print(ships[i][j].toString(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints out the board while revealing the ships' locations
	 * @param o the board's Ocean
	 */
	public static void revealBoard(Ocean o) {
		System.out.println("Here's a look at the ship placement for grading purposes only...");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(Ship.isEmptySpot(i, j, o)) {
					System.out.print("- ");
				}
				else {
					System.out.print("S ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public Ship[][] getShipArray() {
		return ships;
	}

	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}

	public void setShipsSunk(int shipsSunk) {
		this.shipsSunk = shipsSunk;
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ocean o = new Ocean();
		o.placeAllShipsRandomly();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(o.getShipArray()[i][j].getShipType().equals("empty")) {
					System.out.print("- ");
				}
				else {
					System.out.print("S ");
				}
			}
			System.out.println();
		}
	}

}
