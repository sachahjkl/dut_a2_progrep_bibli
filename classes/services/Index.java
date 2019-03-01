package services;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mediatheque.Utilisateur;

@WebServlet("/")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String className = getServletContext().getInitParameter("dataSource");
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void init() throws ServletException {
//		super.init();
//		String classe = getInitParameter("dataSource");
//		try {
//			Class.forName(classe);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		check(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		check(req, resp);
	}

	protected void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur u = (Utilisateur) req.getSession().getAttribute("user");
		String action = "" + (String) req.getParameter("action");
		if (action.equals("logoff")) {
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		if (u != null) {
			if (u.isBibliothecaire()) {
				this.getServletContext().getRequestDispatcher("/bibliothecaire").forward(req, resp);
			} else {
				this.getServletContext().getRequestDispatcher("/abonne").forward(req, resp);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/login").forward(req, resp);
		}
	}
}
