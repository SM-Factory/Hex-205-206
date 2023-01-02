package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.ihm.IPlateau;

/**
  * @author SM-Factory
  * @version 1.0
 */
public interface IIHM {
    /**
     * @param p
     * @return la case du joueur
     */
    String recupererCase(IPlateau p);

    /**
     * @return la taille du plateau
     */
    int recupererTaille();

    /**
     * @return le choix de la partie soit 1,2,3
     */
    int choixPartie();

    /**
     * @param partie
     * @param ihm
     * @return les joueurs crée
     */
    IJoueur[] creationJoueur(int partie, IIHM ihm);

    /**
     * @param plateau
     * @throws InterruptedException
     */
    void lancerPartie(IPlateau plateau) throws InterruptedException;
}
