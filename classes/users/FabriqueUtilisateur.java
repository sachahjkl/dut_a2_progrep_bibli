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
			u = new Abonne((String) args[0]);
			break;
		case 1:
			u = new Bibliothecaire((String) args[0]);
			break;
		default:
			break;
		}
		return u;
	}

}
