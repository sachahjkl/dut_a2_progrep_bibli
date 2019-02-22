package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mediatheque.*;

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
	private static final String url = "jdbc:mysql://https://mysql.sachafroment.fr/bibliotheque", user = "",
			password = "";
	private static Connection conn = null;

	private MediathequeData() {
	}

	private static Connection getConn(String url, String user, String password) {
		if (conn == null) {
			try {

				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				return null;
			}
		} else
			return conn;
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		Connection conn = getConn(url, user, password);
		if (conn == null)
			return null;
		String req = "SELECT * FROM document";
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return null;
	}

	/*
	 * 0 : Abonné 1 : Bibliothécaire va récupérer le User dans la BD et le renvoie
	 * si pas trouvé, renvoie null
	 */
	@Override
	public Utilisateur getUser(String login, String password) {
		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	/*
	 * 0 : Livre 1 : CD 2 : DVD
	 */
	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
