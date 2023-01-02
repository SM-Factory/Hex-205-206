package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.ihm.IPlateau;

/**
 * @author SM-Factory
 * @version 1.0
 */
public class OrdinateurAleatoire implements IJoueur {

    public String jouer(IPlateau p) {
        String s = "";
        int min = 0;
        int max = p.taille();

        while(!p.estLibre(s)) {
            s = "";
            s += (char) (('A' + (int) (Math.random() * (max - min)) + min));
            s += (char) (int) (Math.random() * (max - min)) + min + 1;
        }
        //System.out.println(s);
        return s;
    }

}
