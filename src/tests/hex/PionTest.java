package tests.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sources.hex.Pion;

class PionTest {

	@Test
	void test() {
		assertEquals("X", Pion.Croix.toString());
		assertEquals("O", Pion.Rond.toString());
		assertEquals(".", Pion.Vide.toString());
		
		assertTrue(Pion.get('X') == Pion.Croix);
		assertTrue(Pion.get('O') == Pion.Rond);
		assertTrue(Pion.get('.') == Pion.Vide);
	}

}
