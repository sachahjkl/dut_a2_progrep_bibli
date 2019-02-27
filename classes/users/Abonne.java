package users;

public class Abonne extends AUtilisateur {

	public Abonne(int id, String login) {
		super(id, login);
	}

	@Override
	public boolean isBibliothecaire() {
		return false;
	}

	@Override
	public String toString() {
		return "Abonne: " + super.toString();
	}
}
