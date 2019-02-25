package users;

import mediatheque.Utilisateur;

public abstract class AUtilisateur implements Utilisateur {
	private String login;

	public AUtilisateur(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return login;
	}

}
