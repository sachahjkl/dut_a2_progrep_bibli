package persistantdata;

import mediatheque.Document;

public class FabriqueDocument {

	public static Document make(int type, Object... args) {
		Document d = null;
		switch (type) {
		case 0:
			d = new Livre((int) args[0], (String) args[1], (String) args[2], (Integer) args[3]);
			break;
		case 1:
			d = new CD((int) args[0], (String) args[1], (String) args[2], (Integer) args[3]);
			break;
		case 2:
			d = new DVD((int) args[0], (String) args[1], (String) args[2], (Integer) args[3]);
			break;
		default:
			break;
		}
		return d;
	}

}
