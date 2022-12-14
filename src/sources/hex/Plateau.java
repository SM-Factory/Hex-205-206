package sources.hex;

import sources.joueurs.Humain;
import sources.joueurs.OrdinateurAleatoire;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Plateau {
	private final static int TAILLE_MAX = 10;
	private final static int NB_JOUEURS = 2;
	private final static int START_COLONNE = 'A';
	private final static int START_LIGNE = '1';

	private Pion[][] t;
	private int joueur = 0; // prochain à jouer
	private IJoueur[] joueurs = new IJoueur[2];

	private void suivant() {
		joueur = (joueur +1) % NB_JOUEURS;
	}

	public void jouer() {
		jouer(joueurs[joueur].jouer(this));
	}

	public void jouer(String coord) {
		assert estValide(coord);
		assert getCase(coord) == Pion.Vide;
		Pion pion = Pion.values()[joueur];
		int col = getColonne (coord);
		int lig = getLigne(coord);
		t[col][lig] = pion;
		suivant();
	}

	public static int getTaille(String pos) {
		int taille = (int) Math.sqrt(pos.length());
		assert taille * taille == pos.length();
		return taille;
	}

	public boolean estValide(String coord) {
		if (coord.length() !=2)
			return false;
		int col = getColonne (coord);
		int lig = getLigne(coord);
		//System.out.println(coord + " "+ col+ " "+ lig);
		if (col <0 || col >= taille())
			return false;
		if (lig <0 || lig >= taille())
			return false;
		if (t[col][lig] == Pion.Rond || t[col][lig] == Pion.Croix) return false;
		if (t[col][lig] == Pion.Vide) return true;
		return false;
	}

	public Pion getCase(String coord) {
		assert estValide(coord);
		int col = getColonne (coord);
		int lig = getLigne(coord);
		return t[col][lig];
	}

	private int getColonne(String coord) {
		return coord.charAt(0) - START_COLONNE; // ex 'B' -'A' == 1
	}

	private int getLigne(String coord) {
		return coord.charAt(1) - START_LIGNE; // ex '2' - '0' == 2
	}

	public Plateau(int taille) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];

		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] = Pion.Vide;
		//joueurs[0] = new OrdinateurAleatoire();
		//joueurs[1] = new OrdinateurAleatoire();
	}

	public Plateau(int taille, IJoueur[] joueurs) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];

		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] = Pion.Vide;
		this.joueurs[0] = joueurs[0];
		this.joueurs[1] = joueurs[1];
	}

	public Plateau(int taille, String pos) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];

		String[] lignes = decouper(pos);

		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] =
						Pion.get(lignes[lig].charAt(col));

		if (getNb(Pion.Croix) != getNb(Pion.Rond) &&
				getNb(Pion.Croix) != (getNb(Pion.Rond)+1) &&
				getNb(Pion.Croix) != (getNb(Pion.Rond)-1))
			throw new IllegalArgumentException(
					"position non valide");
		if (getNb(Pion.Croix) > getNb(Pion.Rond)) joueur = 1;
	}



	public int getNb(Pion pion) {
		int nb = 0;
		for (Pion [] ligne : t)
			for (Pion p : ligne)
				if (p == pion)
					++nb;
		return nb;
	}

	public int taille() {
		return t.length;
	}


	private String espaces(int n) {
		String s = "";
		for(int i = 0; i < n; ++i)
			s+= " ";
		return s;
	}
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < taille(); ++i)
			s+=" "+(char)( 'A' + i);
		s+='\n';
		for (int lig = 0; lig < taille(); ++lig) {
			s+= lig+1 + espaces (lig);
			for (int col = 0; col < taille(); ++col)
				s+= " "+ t[col][lig];
			s+='\n';
		}
		return s;
	}

	public static String[] decouper(String pos) {
		int taille = getTaille(pos);
		String[] lignes = new String[taille];
		for (int i = 0; i <taille; ++i)
			lignes[i] = pos.substring(i*taille,
					(i+1)*taille);
		return lignes;

	}

	public static final int getTailleMax() {
		return TAILLE_MAX;
	}

	private ArrayList<Integer[]> getVoisins(int x, int y) {
		ArrayList<Integer[]> voisins = new ArrayList<>();
		if (x == 0 && y==0) {
			voisins.add(new Integer[]{x,y+1});
			voisins.add(new Integer[]{x+1,y});
		} else if (x == taille()-1 && y == taille()-1) {
			voisins.add(new Integer[]{x-1,y});
			voisins.add(new Integer[]{x,y-1});
		} else if (x == 0 && y == taille()-1) {
			voisins.add(new Integer[]{x+1,y});
			voisins.add(new Integer[]{x+1,y-1});
			voisins.add(new Integer[]{x,y-1});
		} else if (x == taille()-1 && y == 0) {
			voisins.add(new Integer[]{x-1,y});
			voisins.add(new Integer[]{x-1,y+1});
			voisins.add(new Integer[]{x,y+1});
		} else if (x == 0) {
			voisins.add(new Integer[]{x,y+1});
			voisins.add(new Integer[]{x+1,y});
			voisins.add(new Integer[]{x+1,y-1});
			voisins.add(new Integer[]{x,y-1});
		} else if (x == taille()-1) {
			voisins.add(new Integer[]{x,y-1});
			voisins.add(new Integer[]{x-1,y+1});
			voisins.add(new Integer[]{x,y+1});
			voisins.add(new Integer[]{x,y-1});
		} else if (y == 0) {
			voisins.add(new Integer[]{x-1,y});
			voisins.add(new Integer[]{x-1,y+1});
			voisins.add(new Integer[]{x,y+1});
			voisins.add(new Integer[]{x+1,y});
		} else if (y == taille()-1) {
			voisins.add(new Integer[]{x-1,y});
			voisins.add(new Integer[]{x+1,y});
			voisins.add(new Integer[]{x+1,y-1});
			voisins.add(new Integer[]{x,y-1});
		} else {
			voisins.add(new Integer[]{x-1,y});
			voisins.add(new Integer[]{x-1,y+1});
			voisins.add(new Integer[]{x,y+1});
			voisins.add(new Integer[]{x+1,y});
			voisins.add(new Integer[]{x+1,y-1});
			voisins.add(new Integer[]{x,y-1});
		}
		return voisins;
	}

	private boolean estBordure(int x, int y, Pion p) {
		if (p == Pion.Croix && x == taille()-1) return true;
		if (p == Pion.Rond && y == taille()-1) return true;
		return false;
	}

	private boolean check(int x, int y, boolean[][] visitee, Pion p) {
		visitee[x][y] = true;
		//System.out.println("test " + x + " " + y + " " + t[x][y] + " " + p);
		if (t[x][y] == p && estBordure(x,y,p)) return true;
		else if (t[x][y] == Pion.Vide) return false;
		else {
			ArrayList<Integer[]> voisins = getVoisins(x,y);
			for (Integer[] voisin : voisins) {
				//System.out.println("test voisin " + voisin[0] + " " + voisin[1] + " " + (visitee[voisin[0]][voisin[1]]));
				if (visitee[voisin[0]][voisin[1]] == false && t[voisin[0]][voisin[1]] == p) {
					//System.out.println("test voisin " + voisin[0] + " " + voisin[1]);
					return check(voisin[0],voisin[1],visitee,p);
				}
			}
		}
		return false;
	}

	public boolean estFinie() {
		if (getNb(Pion.values()[getAutreJoueur()]) < taille()) return false;
		if (joueur == 1) {
			for (int y=0;y<taille();y++) {
				boolean visitee[][] = new boolean[taille()][taille()];
				for (boolean[] b : visitee) {
					Arrays.fill(b, false);
				}
				if (check(0,y, visitee, Pion.Croix)) return true;
			}
		} else if (joueur == 0) {
			for (int x = 0; x<taille();x++) {
				boolean visitee[][] = new boolean[taille()][taille()];
				for (boolean[] b : visitee) {
					Arrays.fill(b, Boolean.FALSE);
				}
				if (check(x, 0, visitee, Pion.Rond)) return true;
			}
		}
		return false;
	}

	private int getAutreJoueur() { return (joueur+1)%2; }

	public String getGagnant() {
		if (estFinie()) return Pion.values()[getAutreJoueur()].name();
		throw new IllegalArgumentException("partie non finie");
	}

}
