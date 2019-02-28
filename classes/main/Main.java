package main;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, EmpruntException {
		Mediatheque m = Mediatheque.getInstance();
		Class.forName("persistantdata.MediathequeData");
		System.out.println(m.tousLesDocuments());
		System.out.println(m.getDocument(3));
		Document d = m.getDocument(3);
		Utilisateur u = m.getUser("test", "test");
		m.emprunt(d, u);
		System.out.println(m.getUser("test", "test"));
	}
}
