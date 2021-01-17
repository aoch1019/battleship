
public abstract class Ship {

	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean[] hit;	
	
	/**
	 * Creates a new Ship with the given location, length, and horizontal status
	 * @param bowRow
	 * @param bowColumn
	 * @param length
	 * @param horizontal
	 */
	public Ship(int bowRow, int bowColumn, int length, boolean horizontal) {
		this.bowRow = bowRow;
		this.bowColumn = bowColumn;
		this.length = length;
		this.horizontal = horizontal;
		
		boolean[] hitArr = new boolean[length];
		for(int i = 0; i < length; i++) {
			hitArr[i] = false;
		}
		this.hit = hitArr;
	}
	
	/**
	 * Checks if the current ship type can be placed at the desired location within the current ocean
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return true if it can be legally placed there. False otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if(row < 0 || column < 0 || row > 9 || column > 9) {
			return false;
		}
		
		if(horizontal) {
			if(column + length > 9) {
				return false;
			}
		}
		else {
			if(row + length > 9) {
				return false;
			}
		}
		
		for(int i = 0; i < length; i++) {
			if(horizontal) {
				// Check if the potential spot is occupied
				if(!isEmptySpot(row, column + i, ocean)) {
					return false;
				}
				
				// Check neighboring spots
				if(row != 0) {
					if(!isEmptySpot(row - 1, column + i, ocean)) {
						return false;
					}
				}
				if(row != 9) {
					if(!isEmptySpot(row + 1, column + i, ocean)) {
						return false;
					}					
				}				
				
				// Check the leftmost diagonals if they exist
				if(i == 0 && column != 0) {
					// If we aren't placing the ship on the first row, then check upper left diagonal
					if(row != 0 ) {
						if(!isEmptySpot(row - 1, column - 1, ocean)) {
							return false;
						}
					}
					
					// If we aren't placing the ship on the last row, then check bottom left diagonal
					if(row != 9) {
						if(!isEmptySpot(row + 1, column - 1, ocean)) {
							return false;
						}						
					}
					
					// Also check the spot in between the diagonals
					if(!isEmptySpot(row, column - 1, ocean)) {
						return false;
					}					
				}
				// Check the rightmost diagonals if they exist
				if(i == length - 1 && column != 9) {
					// If we aren't placing the ship on the first row, then check upper right diagonal
					if(row != 0 ) {
						if(!isEmptySpot(row - 1, column + i + 1, ocean)) {
							return false;
						}
					}
					
					// If we aren't placing the ship on the last row, then check bottom right diagonal
					if(row != 9) {
						if(!isEmptySpot(row + 1, column + i + 1, ocean)) {
							return false;
						}						
					}
					
					// Also check the spot in between the diagonals
					if(!isEmptySpot(row, column + i + 1, ocean)) {
						return false;
					}						
				}
			}
			else {
				// Check if the potential spot is occupied
				if(!isEmptySpot(row + i, column, ocean)) {
					return false;
				}
				
				// Check neighboring spots
				if(column != 0) {
					if(!isEmptySpot(row + i, column - 1, ocean)) {
						return false;
					}
				}
				if(column != 9) {
					if(!isEmptySpot(row + i, column + 1, ocean)) {
						return false;
					}					
				}				
				
				// Check the top diagonals if they exist
				if(i == 0 && row != 0) {
					// If we aren't placing the ship on the first row, then check upper left diagonal
					if(column != 0 ) {
						if(!isEmptySpot(row - 1, column - 1, ocean)) {
							return false;
						}
					}
					
					// If we aren't placing the ship on the last row, then check upper right diagonal
					if(column != 9) {
						if(!isEmptySpot(row - 1, column + 1, ocean)) {
							return false;
						}						
					}
					
					// Also check the spot in between the diagonals
					if(!isEmptySpot(row - 1, column, ocean)) {
						return false;
					}					
				}
				// Check the bottom-most diagonals if they exist
				if(i == length - 1 && row != 9) {
					// If we aren't placing the ship on the first row, then check bottom left diagonal
					if(column != 0 ) {
						if(!isEmptySpot(row + i + 1, column - 1, ocean)) {
							return false;
						}
					}
					
					// If we aren't placing the ship on the last row, then check bottom right diagonal
					if(column != 9) {
						if(!isEmptySpot(row + i + 1, column + 1, ocean)) {
							return false;
						}						
					}
					
					// Also check the spot in between the diagonals
					if(!isEmptySpot(row + i + 1, column, ocean)) {
						return false;
					}					
				}							
			}
		}
		
		return true;
	}
	
	
	/**
	 * Places the ship object at the desired location
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
		
		for(int i = 0; i < length; i++) {
			if(horizontal) {
				ocean.getShipArray()[row][column + i] = this;
			}
			else {
				ocean.getShipArray()[row + i][column] = this;				
			}
		}
	}
	
	/**
	 * Attempts to shoot at the ship at the given coordinates.
	 * @param row
	 * @param column
	 * @return true if the ship was successfully shot at an untouched location. False otherwise.
	 */
	boolean shootAt(int row, int column) {
		if(isSunk()) {
			return false;
		}
	
		if(horizontal && row == bowRow) {
			if((column - bowColumn) <= length && (column - bowColumn) >= 0){
				if(hit[column - bowColumn]) {
					return false;
				}
				else {
					hit[column - bowColumn] = true;
					return true;
				}
			}
			else {
				return false;
			}
		}
		
		if(!horizontal && column == bowColumn) {
			if((row - bowRow) <= length && (row - bowRow) >= 0){
				if(hit[row - bowRow]) {
					return false;
				}
				else {
					hit[row - bowRow] = true;
					return true;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks if the ship has been shot at all locations
	 * @return true if it's been sunk. False otherwise.
	 */
	boolean isSunk() {
		for(boolean b : hit) {
			if(!b) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if a given spot in the ocean is empty sea.
	 * @param row
	 * @param column
	 * @param ocean
	 * @return Whether the given coordinates represent an empty spot 
	 */
	public static boolean isEmptySpot(int row, int column, Ocean ocean) {
		return (ocean.getShipArray()[row][column]).getShipType().equals("empty");
	}
	
	/**
	 * Checks if a particular part of a ship has been hit yet
	 * @param row
	 * @param column
	 * @return true if that spot has been shot already. False otherwise.
	 */
	boolean getHitStatus(int row, int column) {
		if(horizontal) {
			return hit[column - bowColumn];
		}
		else {
			return hit[row - bowRow];
		}
	}
	
	/**
	 * Takes a section of the ship and identifies how it should be printed.
	 * @param row
	 * @param column
	 * @return "x" if the whole ship is sunk. "S" if that part of the ship has been shot. "." if that part of the ship is untouched.
	 */
	public String toString(int row, int column) {
		if(isSunk()) {
			return "x";
		}
		else {
			if(getHitStatus(row, column)) {
				return "S";
			}
			else {
				return ".";
			}
		}
	}
	
	public int getBowRow() {
		return bowRow;
	}
	
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	
	public int getBowColumn() {
		return bowColumn;
	}
	
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	abstract String getShipType();	
	
}
