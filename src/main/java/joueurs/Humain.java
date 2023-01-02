package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.ihm.IPlateau;

/**
 * @author SM-Factory
 * @version 1.0
 */
public class Humain implements IJoueur {
    private IIHM ihm;

    public Humain(IIHM ihm) {
        this.ihm = ihm;
    }
    @Override
    public String jouer(IPlateau p) {
        return ihm.recupererCase(p);
    }
}
