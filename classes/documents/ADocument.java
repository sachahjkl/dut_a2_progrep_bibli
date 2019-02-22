package documents;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class ADocument implements Document {

	private String titre, auteur;
	private Utilisateur emprunteur;

	public ADocument(String titre, String auteur, Utilisateur emprunteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.emprunteur = emprunteur;
	}

	@Override
	public Object[] affiche() {
		return new Object[] { titre, auteur, emprunteur };
	}

	@Override
	public void emprunter(Utilisateur arg0) throws EmpruntException {
		synchronized (emprunteur) {
			if (this.emprunteur != null)
				throw new EmpruntException();
			else
				this.emprunteur = arg0;
		}
	}

	@Override
	public void retour() {
		this.emprunteur = null;

	}

}
