package users;

import mediatheque.Utilisateur;

public class FabriqueUtilisateur {
	/*
	 * args[0] : login
	 * 
	 */
	public static Utilisateur make(int type, Object... args) {
		Utilisateur u = null;
		switch (type) {
		case 0:
			u = new Abonne((int) args[0],(String) args[1]);
			break;
		case 1:
			u = new Bibliothecaire((int) args[0], (String) args[1]);
			break;
		default:
			break;
		}
		return u;
	}

}
