
public class Submarine extends Ship {

	public Submarine(int bowRow, int bowColumn, boolean horizontal) {
		super(bowRow, bowColumn, 1, horizontal);
	}

	@Override
	String getShipType() {
		return "submarine";
	}
	
}
