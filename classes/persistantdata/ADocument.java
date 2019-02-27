package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class ADocument implements Document {

	private int id;
	private String titre, auteur;
	private int emprunteur;

	public ADocument(int id, String titre, String auteur, int emprunteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.id = id;
		this.emprunteur = emprunteur;
	}

	@Override
	public Object[] affiche() {
		return new Object[] { id, titre, auteur, emprunteur };
	}

	@Override
	public void emprunter(Utilisateur arg0) throws EmpruntException {
		if (arg0 == null) {
			return;
		}
		Connection conn = MediathequeData.getConn(MediathequeData.url, MediathequeData.user, MediathequeData.pass);
		if (conn == null)
			return;
		int idu = Integer.parseInt(arg0.toString().split("")[1]);
		String sql = "UPDATE document SET idUtilisateur = ? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idu);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retour() {
		Connection conn = MediathequeData.getConn(MediathequeData.url, MediathequeData.user, MediathequeData.pass);
		if (conn == null)
			return;
		String sql = "UPDATE document SET idUtilisateur = NULL WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return Arrays.deepToString(affiche());
		// return "{" + titre + ", " + auteur + (emprunteur == null ? "" : ", " +
		// emprunteur) + "}";
	}

}
