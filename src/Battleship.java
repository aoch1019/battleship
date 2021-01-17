
public class Battleship extends Ship {

	public Battleship(int bowRow, int bowColumn, boolean horizontal) {
		super(bowRow, bowColumn, 4, horizontal);
	}

	@Override
	String getShipType() {
		return "battleship";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}	

}
