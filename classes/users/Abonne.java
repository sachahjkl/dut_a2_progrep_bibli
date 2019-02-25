package users;

public class Abonne extends AUtilisateur {

	public Abonne(String login) {
		super(login);
	}

	@Override
	public boolean isBibliothecaire() {
		return false;
	}

	@Override
	public String toString() {
		return "Abonne : " + super.toString();
	}
}
