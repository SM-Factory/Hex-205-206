package main.java.main;

import main.java.hex.Plateau;
import main.java.ihm.IHM;
import main.java.ihm.IPlateau;
import main.java.joueurs.IIHM;

/**
 * @author SM-Factory
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        IIHM ihm = new IHM();
        int partie = ihm.choixPartie();
        int taille = ihm.recupererTaille();
        IPlateau plateau = new Plateau(taille, ihm.creationJoueur(partie,ihm));
        ihm.lancerPartie(plateau);
    }
}
