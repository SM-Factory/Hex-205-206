package sources.hex;

public enum Pion {
	Croix('X'), Rond('O'), Vide('.');
	
	private char symbole;
	Pion (char symbole){
		
		this.symbole = symbole;
	}
	@Override
	public String toString() {
		return ""+symbole;
	}
	
	//retourne le pion correpondant à c
	public static Pion get(char c) {
		for(Pion p : Pion.values())
			if (p.symbole == c)
				return p;
		
		return null;
		
	}
}

