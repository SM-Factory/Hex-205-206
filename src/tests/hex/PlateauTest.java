package tests.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sources.hex.Pion;
import sources.hex.Plateau;
import sources.ihm.IHM;

class PlateauTest {
	private String pos1 = ".X..XOXXOO.OX...", pos2 = ".XX.XOXXOO.OXO..", pos3 = ".XO.XOXXOO.OXO..";
	private String[] lignes1_rep = {
			".X..",
			"XOXX",
			"OO.O",
			"X..."
	};
	private String display1_rep =
			" A B C D\n" +
					"1 . X . .\n" +
					"2  X O X X\n" +
					"3   O O . O\n" +
					"4    X . . .\n";

	@Test
	void test() {
		final int taille = 4;
		Plateau p = new Plateau(taille);
		assertEquals(taille, p.taille());
		assertEquals(
				" A B C D\n" +
						"1 . . . .\n" +
						"2  . . . .\n" +
						"3   . . . .\n" +
						"4    . . . .\n", p.toString());


		// jouer un coup en B2
		p.jouer("B2");
		assertEquals(Pion.Croix, p.getCase("B2"));

		//jouer un coup en D3
		p.jouer("D3");
		assertEquals(Pion.Rond, p.getCase("D3"));

		assertEquals(
				" A B C D\n" +
						"1 . . . .\n" +
						"2  . X . .\n" +
						"3   . . . O\n" +
						"4    . . . .\n", p.toString());

	}

	@Test
	public void testerPositions() {
		testerPosition(pos1, lignes1_rep, display1_rep);
	}

	private void testerPosition(String pos, String[] lignes_rep, String display_rep) {
		String[] lignes;
		lignes  = Plateau.decouper(pos);
		int taille = Plateau.getTaille(pos);

		for (int i = 0; i< taille; ++i)
			assertEquals(lignes_rep[i], lignes[i]);

		Plateau p = new Plateau(taille, pos);
		assertEquals(display_rep, p.toString());
	}


	@Test
	void testFinie() {
		testFinie(pos2,Pion.Croix.name());
		testFinie(pos3,Pion.Rond.name());
	}

	private void testFinie(String pos2, String s) {
		int taille = Plateau.getTaille(pos2);
		Plateau p = new Plateau(taille, pos2);
		System.out.println(p.getNb(Pion.Croix));
		System.out.println(p.getNb(Pion.Rond));
		//IHM.affichePlateau(p);
		assertTrue(p.estFinie());
		assertEquals(p.getGagnant(),s);
	}
}
