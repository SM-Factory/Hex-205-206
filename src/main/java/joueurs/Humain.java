package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.ihm.IPlateau;

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
