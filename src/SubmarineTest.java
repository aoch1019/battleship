import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubmarineTest {

	@Test
	void testGetShipType() {
		Submarine s = new Submarine(1, 1, true);
		assertTrue(s.getShipType().equals("submarine"));
	}
	
	@Test
	void testGetBowRow() {
		Submarine s = new Submarine(2, 1, true);
		assertTrue(s.getBowRow() == 2);
	}
	
	@Test
	void testGetBowColumn() {
		Submarine s = new Submarine(1, 4, true);
		assertTrue(s.getBowColumn() == 4);
	}	
	
	@Test
	void testGetLength() {
		Submarine s = new Submarine(3, 5, true);
		assertTrue(s.getLength() == 1);
	}		
	
	@Test
	void testGetHorizontal() {
		Submarine s = new Submarine(3, 5, true);
		assertTrue(s.isHorizontal());
	}	
	
	@Test
	void testShootAt() {
		Submarine s = new Submarine(3, 5, true);
		assertFalse(s.getHitStatus(3, 5));
		assertTrue(s.toString(3, 5).equals("."));
		assertFalse(s.isSunk());
		
		assertTrue(s.shootAt(3, 5));
		assertFalse(s.shootAt(1, 1));
		assertFalse(s.shootAt(10, 8));
		
		assertTrue(s.getHitStatus(3, 5));
		
		assertTrue(s.isSunk());
		
		assertTrue(s.toString(3, 5).equals("x"));
	}	

}
