package test.java.joueurs;

import main.java.hex.IJoueur;
import main.java.hex.Plateau;
import main.java.joueurs.OrdinateurAleatoire;
import main.java.ihm.IPlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdinateurAleatoireTest {

    @Test
    void testJouer() {
        IJoueur j1 = new OrdinateurAleatoire();
        IJoueur j2 = new OrdinateurAleatoire();
        IPlateau p = new Plateau(5,new IJoueur[]{j1,j2});
        assertTrue(p.estLibre(j1.jouer(p)));
    }
}
