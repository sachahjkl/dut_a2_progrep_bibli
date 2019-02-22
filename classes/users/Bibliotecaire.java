package users;

import mediatheque.Utilisateur;

public class Bibliotecaire implements Utilisateur {

	@Override
	public boolean isBibliothecaire() {
		return true;
	}

}
