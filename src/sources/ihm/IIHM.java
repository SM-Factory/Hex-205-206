package sources.ihm;

import sources.hex.IJoueur;
import sources.hex.Plateau;

import java.util.Scanner;

public interface IIHM {
    String recupererCase(Plateau p);
    int recupererTaille();
    void affichePlateau(Plateau p);
    int choixPartie();
    IJoueur[] creationJoueur(int partie, IIHM ihm);
}
