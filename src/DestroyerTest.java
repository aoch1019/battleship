import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyerTest {
	
	@Test
	void testGetShipType() {
		Destroyer d = new Destroyer(1, 1, true);
		assertTrue(d.getShipType().equals("destroyer"));
	}
	
	@Test
	void testGetBowRow() {
		Destroyer d = new Destroyer(2, 1, true);
		assertTrue(d.getBowRow() == 2);
	}
	
	@Test
	void testGetBowColumn() {
		Destroyer d = new Destroyer(1, 4, true);
		assertTrue(d.getBowColumn() == 4);
	}	
	
	@Test
	void testGetLength() {
		Destroyer d = new Destroyer(3, 5, true);
		assertTrue(d.getLength() == 2);
	}		
	
	@Test
	void testGetHorizontal() {
		Destroyer d = new Destroyer(3, 5, true);
		assertTrue(d.isHorizontal());
	}	
	
	@Test
	void testShootAt() {
		Destroyer d = new Destroyer(3, 5, true);
		assertTrue(d.shootAt(3, 5));
		assertFalse(d.shootAt(1, 1));
		assertFalse(d.shootAt(10, 8));
		
		assertTrue(d.getHitStatus(3, 5));
		assertFalse(d.getHitStatus(3, 6));
		
		assertTrue(d.toString(3, 5).equals("S"));
		assertTrue(d.toString(3, 6).equals("."));
		
		assertFalse(d.isSunk());
		
		assertTrue(d.shootAt(3, 6));
		
		assertTrue(d.isSunk());
		assertTrue(d.toString(3, 5).equals("x"));
		assertTrue(d.toString(3, 6).equals("x"));
	}	

}
