package main.java.ihm;

import main.java.hex.Pion;
import main.java.hex.Plateau;

public interface IPlateau {
    void jouer();
    void jouer(String coord);
    boolean estValide(String coord);
    int getNb(Pion pion);
    int taille();
    String toString();
    IPlateau clone();
    boolean estFinie();
    int getGagnant();
    boolean estLibre(String coord);
}
