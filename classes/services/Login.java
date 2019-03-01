package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		check(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		check(req, resp);
	}

	private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur u = (Utilisateur) req.getSession().getAttribute("user");
		if (u != null) {
			if (u.isBibliothecaire()) {
				resp.sendRedirect(req.getContextPath() + "/bibliothecaire");
			} else {
				resp.sendRedirect(req.getContextPath() + "/abonne");
			}
		} else {
			process(req, resp);
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = "" + (String) req.getParameter("action");
		if (action.equals("login")) {
			if (login(req)) {
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			} else {
				req.setAttribute("error", "le login et/ou le mot de passe sont incorrects.");
			}
		}
		req.setAttribute("title", "Connexion");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(req, resp);
	}

	private boolean login(HttpServletRequest req) {
		String login = "" + (String) req.getParameter("login");
		String password = "" + (String) req.getParameter("password");
		Utilisateur u = Mediatheque.getInstance().getUser(login, password);
		if (u == null)
			return false;
		else {
			req.getSession().setAttribute("user", u);
			return true;
		}
	}

}
