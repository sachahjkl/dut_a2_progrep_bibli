package main;

import mediatheque.EmpruntException;
import mediatheque.Mediatheque;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, EmpruntException {
		Mediatheque m = Mediatheque.getInstance();
		Class.forName("persistantdata.MediathequeData");
		System.out.println(m.tousLesDocuments());
		System.out.println(m.getDocument(3));
		System.out.println(m.getUser("test", "test"));
	}
}
