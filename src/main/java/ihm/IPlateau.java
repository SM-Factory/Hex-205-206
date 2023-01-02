package main.java.ihm;

import main.java.hex.Pion;
import main.java.hex.Plateau;

/**
 * @author SM-Factory
 * @version 1.0
 */
public interface IPlateau {
    /**
     *
     */
    void jouer();

    /**
     * @param coord
     */
    void jouer(String coord);

    /**
     * @param coord
     * @return true si les coordonnées sont bonnes
     */
    boolean estValide(String coord);

    /**
     * @param pion
     * @return le nombre de pion sur le plateau du pion rentré
     */
    int getNb(Pion pion);

    /**
     * @return la taille du plateau
     */
    int taille();

    /**
     * @return l'affichage du plateau
     */
    String toString();

    /**
     * @return un Plateau équivalent à celui rentré en paramètre
     */
    IPlateau clone();

    /**
     * @return si la partie est finie
     */
    boolean estFinie();

    /**
     * @return le gagnant de la partie
     */
    int getGagnant();

    /**
     * @return le gagnant de la partie
     */
    String getGagnantNom();

    /**
     * @param coord
     * @return Vérifie si y a aucun pion sur coord
     */
    boolean estLibre(String coord);
}
