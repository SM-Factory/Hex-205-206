package main.java.hex;

import main.java.ihm.IPlateau;

/**
 * @author SM-Factory
 * @version 1.0
 */
public interface IJoueur {
    /**
     * @param p
     * @return les coordonnées du coup joué
     */
    String jouer(IPlateau p);
}
