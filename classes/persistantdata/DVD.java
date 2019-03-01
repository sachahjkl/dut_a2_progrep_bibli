package persistantdata;

public class DVD extends ADocument {

	public DVD(int id,String titre, String auteur, Integer emprunteur) {
		super(id, titre, auteur, emprunteur);
	}
	
	@Override
	public String toString() {
		return "DVD "+ super.toString();
	}
}
