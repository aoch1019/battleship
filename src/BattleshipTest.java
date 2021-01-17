import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {

	@Test
	void testGetShipType() {
		Battleship b = new Battleship(1, 1, true);
		assertTrue(b.getShipType().equals("battleship"));
	}
	
	@Test
	void testGetBowRow() {
		Battleship b = new Battleship(2, 1, true);
		assertTrue(b.getBowRow() == 2);
	}
	
	@Test
	void testGetBowColumn() {
		Battleship b = new Battleship(1, 4, true);
		assertTrue(b.getBowColumn() == 4);
	}	
	
	@Test
	void testGetLength() {
		Battleship b = new Battleship(3, 5, true);
		assertTrue(b.getLength() == 4);
	}		
	
	@Test
	void testGetHorizontal() {
		Battleship b = new Battleship(3, 5, true);
		assertTrue(b.isHorizontal());
	}	
	
	@Test
	void testShootAt() {
		Battleship b = new Battleship(3, 5, true);
		assertTrue(b.shootAt(3, 5));
		assertFalse(b.shootAt(1, 1));
		assertFalse(b.shootAt(10, 8));
		
		assertTrue(b.getHitStatus(3, 5));
		assertFalse(b.getHitStatus(3, 6));
		
		assertTrue(b.toString(3, 5).equals("S"));
		assertTrue(b.toString(3, 6).equals("."));
		
		assertFalse(b.isSunk());
		
		assertTrue(b.shootAt(3, 6));
		assertTrue(b.shootAt(3, 7));
		assertTrue(b.shootAt(3, 8));
		
		assertTrue(b.isSunk());
		assertTrue(b.toString(3, 5).equals("x"));
		assertTrue(b.toString(3, 6).equals("x"));
	}		

}
