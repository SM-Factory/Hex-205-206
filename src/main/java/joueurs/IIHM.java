package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.ihm.IPlateau;

public interface IIHM {
    String recupererCase(IPlateau p);
    int recupererTaille();
    int choixPartie();
    IJoueur[] creationJoueur(int partie, IIHM ihm);
    void lancerPartie(IPlateau plateau) throws InterruptedException;
}
