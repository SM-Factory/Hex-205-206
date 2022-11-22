package tests.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sources.hex.Pion;
import sources.hex.Plateau;

class PlateauTest {

	@Test
	void test() {
		final int taille = 4;
		Plateau p = new Plateau(taille);
		assertEquals(taille, p.taille());
		
		System.out.println(p);
		assertEquals(
				" A B C D\n" + 
				"1 . . . .\n" + 
				"2  . . . .\n" + 
				"3   . . . .\n" + 
				"4    . . . .\n", p.toString());
		
	
		// jouer un coup en B2
		//assertEquals(Pion.Croix, p.getCase("B2"));
		
		String pos1 = "....XOXXOO.OX...";
		
		String[] lignes = Plateau.decouper(taille, pos1);
		
		String[] lignes_rep = {"....", "XOXX", "OO.O", "X..."};
		for(int i = 0; i<taille; ++i)
			assertEquals(lignes_rep[i], lignes[i]);
		
		//String s ="";
		//s.substring(beginIndex, endIndex);
		
	}
}
