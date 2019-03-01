package persistantdata;

public class Livre extends ADocument {

	public Livre(int id, String titre, String auteur, Integer emprunteur) {
		super(id, titre, auteur, emprunteur);
	}

	@Override
	public String toString() {
		return "Livre "+ super.toString();
	}
}
