
public class EmptySea extends Ship{
	
	private boolean shotAtYet;

	public EmptySea(int bowRow, int bowColumn) {
		super(bowRow, bowColumn, 1, true);
		shotAtYet = false;
	}
	
	/**
	 * Marks the empty space as shot at so we know how to print it and not to count future shots against total shot count.
	 */
	@Override
	boolean shootAt(int row, int column) {
		this.shotAtYet = true;
		return false;
	}
	
	@Override
	boolean isSunk() {
		return false;
	}
	
	public boolean isShotAtYet() {
		return shotAtYet;
	}

	public void setShotAtYet(boolean shotAtYet) {
		this.shotAtYet = shotAtYet;
	}	
	/**
	 * @return "-' if the empty space has been shot already and "." is returned otherwise.
	 */
	@Override
	public String toString(int row, int column) {
		if(isShotAtYet()) {
			return "-";
		}
		return ".";
	}
	
	
	@Override
	String getShipType() {
		return "empty";
	}

}
