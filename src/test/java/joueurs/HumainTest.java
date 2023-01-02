package test.java.joueurs;

import main.java.hex.IJoueur;
import main.java.hex.Plateau;
import main.java.ihm.IHM;
import main.java.joueurs.Humain;
import main.java.joueurs.IIHM;
import main.java.ihm.IPlateau;
import org.junit.jupiter.api.Test;

public class HumainTest {

    @Test
    void testJouer() {
        IIHM ihm = new IHM();
        IJoueur j1 = new Humain(ihm);
        IJoueur j2 = new Humain(ihm);
        IPlateau p = new Plateau(5,new IJoueur[]{j1,j2});
        j1.jouer(p);

        assert(p.estLibre(j1.jouer(p)));

    }
}
