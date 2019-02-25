package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;
import users.FabriqueUtilisateur;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static final String url = "jdbc:mysql://51.77.210.114/bibliotheque", user = "bibliotheque",
			pass = "bibliotheque";
	private static Connection conn = null;

	private MediathequeData() {
	}

	private static Connection getConn(String url, String user, String password) {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		Connection conn = getConn(url, user, pass);
		if (conn == null)
			return null;
		String sql = "SELECT type, titre, auteur FROM document";
		ResultSet rs = null;
		List<Document> al = new ArrayList<>();
		try {
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				al.add(FabriqueDocument.make(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al.isEmpty() ? null : al;
	}

	/*
	 * 0 : Abonné, 1 : Bibliothécaire, va récupérer le User dans la BD et le renvoie
	 * si pas trouvé, renvoie null
	 */
	@Override
	public Utilisateur getUser(String login, String password) {
		Connection conn = getConn(url, user, pass);
		Utilisateur u = null;
		if (conn == null)
			return null;
		String sql = "SELECT type, login FROM utilisateur WHERE login=? AND pwdSHA1=SHA1(?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				u = FabriqueUtilisateur.make(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		Connection conn = getConn(url, user, pass);
		Document d = null;
		if (conn == null)
			return null;
		String sql = "SELECT type, titre, auteur FROM document WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numDocument);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				d = FabriqueDocument.make(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	/*
	 * 0 : Livre, 1 : CD, 2 : DVD
	 */
	@Override
	public void nouveauDocument(int type, Object... args) {
		Connection conn = getConn(url, user, pass);
		if (conn == null)
			return;
		String sql = "INSERT INTO document (type, titre, auteur) VALUES (?, ?, ?);";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setString(2, (String) args[0]);
			ps.setString(3, (String) args[1]);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
