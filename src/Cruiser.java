
public class Cruiser extends Ship {

	public Cruiser(int bowRow, int bowColumn, boolean horizontal) {
		super(bowRow, bowColumn, 3, horizontal);
	}

	@Override
	String getShipType() {
		return "cruiser";
	}
	
}
