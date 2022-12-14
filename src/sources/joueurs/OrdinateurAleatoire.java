package sources.joueurs;

import sources.hex.IJoueur;
import sources.hex.Plateau;
import sources.ihm.IIHM;

import java.util.Random;

public class OrdinateurAleatoire implements IJoueur {
    public String jouer(Plateau p) {
        String s = "";
        int min = 0;
        int max = p.taille();

        while(!p.estValide(s)) {
            s = "";
            s += (char) (('A' + (int) (Math.random() * (max - min)) + min));
            s += (char) (int) (Math.random() * (max - min)) + min + 1;
        }
        System.out.println(s);
        return s;
    }

}
