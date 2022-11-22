package sources.hex;

public enum Pion {
	Croix('X'), Rond('O'), Vide('.');
	private char symbole;
	Pion (char symbole) {
		this.symbole = symbole;
	}
	@Override
	public String toString() {
		return ""+symbole;
	}
}
