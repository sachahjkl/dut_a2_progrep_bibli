package users;

public class Bibliothecaire extends AUtilisateur {

	public Bibliothecaire(int id, String login) {
		super(id, login);
	}

	@Override
	public boolean isBibliothecaire() {
		return true;
	}

	@Override
	public String toString() {
		return "Bibliothecaire: " + super.toString();
	}

}
