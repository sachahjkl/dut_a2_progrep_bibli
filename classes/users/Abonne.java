package users;

import mediatheque.Utilisateur;

public class Abonne implements Utilisateur {

	@Override
	public boolean isBibliothecaire() {
		return false;
	}

}
