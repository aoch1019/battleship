
public class Destroyer extends Ship {

	public Destroyer(int bowRow, int bowColumn, boolean horizontal) {
		super(bowRow, bowColumn, 2, horizontal);
	}

	@Override
	String getShipType() {
		return "destroyer";
	}
	
}
