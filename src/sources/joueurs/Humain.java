package sources.joueurs;

import sources.hex.IJoueur;
import sources.hex.Plateau;
import sources.ihm.IHM;
import sources.ihm.IIHM;

public class Humain implements IJoueur {
    IIHM ihm;

    public Humain(IIHM ihm) {
        this.ihm = ihm;
    }
    public String jouer(Plateau p) {
        return ihm.recupererCase(p);
    }
}
