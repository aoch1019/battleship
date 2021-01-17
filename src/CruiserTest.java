import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CruiserTest {

	@Test
	void testGetShipType() {
		Cruiser c = new Cruiser(1, 1, true);
		assertTrue(c.getShipType().equals("cruiser"));
	}
	
	@Test
	void testGetBowRow() {
		Cruiser c = new Cruiser(2, 1, true);
		assertTrue(c.getBowRow() == 2);
	}
	
	@Test
	void testGetBowColumn() {
		Cruiser c = new Cruiser(1, 4, true);
		assertTrue(c.getBowColumn() == 4);
	}	
	
	@Test
	void testGetLength() {
		Cruiser c = new Cruiser(3, 5, true);
		assertTrue(c.getLength() == 3);
	}		
	
	@Test
	void testGetHorizontal() {
		Cruiser c = new Cruiser(3, 5, true);
		assertTrue(c.isHorizontal());
	}	
	
	@Test
	void testShootAt() {
		Cruiser c = new Cruiser(3, 5, true);
		assertTrue(c.shootAt(3, 5));
		assertFalse(c.shootAt(1, 1));
		assertFalse(c.shootAt(10, 8));
		
		assertTrue(c.getHitStatus(3, 5));
		assertFalse(c.getHitStatus(3, 6));
		
		assertTrue(c.toString(3, 5).equals("S"));
		assertTrue(c.toString(3, 6).equals("."));
		
		assertFalse(c.isSunk());
		
		assertTrue(c.shootAt(3, 6));
		assertTrue(c.shootAt(3, 7));
		
		assertTrue(c.isSunk());
		assertTrue(c.toString(3, 5).equals("x"));
		assertTrue(c.toString(3, 6).equals("x"));
	}	

}
