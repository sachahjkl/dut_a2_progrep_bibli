package users;

import mediatheque.Utilisateur;

public abstract class AUtilisateur implements Utilisateur {
	private String login;
	private int id;

	public AUtilisateur(int id, String login) {
		this.login = login;
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " " + login;
	}

}
