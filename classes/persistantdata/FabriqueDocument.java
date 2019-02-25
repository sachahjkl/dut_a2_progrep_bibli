package persistantdata;

import mediatheque.Document;

public class FabriqueDocument {

	public static Document make(int type, Object... args) {
		Document d = null;
		switch (type) {
		case 0:
			d = new Livre((String) args[0], (String) args[1]);
			break;
		case 1:
			d = new CD((String) args[0], (String) args[1]);
			break;
		case 2:
			d = new DVD((String) args[0], (String) args[1]);
			break;
		default:
			break;
		}
		return d;
	}

}
