package test.java.joueurs;

import main.java.hex.IJoueur;
import main.java.hex.Plateau;
import main.java.joueurs.OrdinateurIntelligent;
import main.java.ihm.IPlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdinateurIntelligentTest {
    @Test
    void testJouer() {
        IJoueur j1 = new OrdinateurIntelligent(0);
        IJoueur j2 = new OrdinateurIntelligent(1);
        IPlateau p = new Plateau(2,new IJoueur[]{j1,j2});
        assertTrue(p.estLibre(j1.jouer(p)));
    }
}
