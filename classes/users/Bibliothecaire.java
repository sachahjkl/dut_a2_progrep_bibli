package users;

public class Bibliothecaire extends AUtilisateur {

	public Bibliothecaire(String login) {
		super(login);
	}

	@Override
	public boolean isBibliothecaire() {
		return true;
	}

	@Override
	public String toString() {
		return "Bibliothecaire : " + super.toString();
	}

}
