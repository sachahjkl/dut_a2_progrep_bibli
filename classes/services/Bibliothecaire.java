package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/bibliothecaire")
public class Bibliothecaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Bibliothecaire() {
		super();
	}

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
		if (u == null)
			resp.sendRedirect(req.getContextPath() + "/login");
		else if (!u.isBibliothecaire())
			resp.sendRedirect(req.getContextPath() + "/abonne");
		else {
			process(req, resp);
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = "" + req.getParameter("action");
		if (action.equals("addDoc")) {
			int type = Integer.parseInt(req.getParameter("type"));
			String nomDoc = (String) req.getParameter("nomDoc"), auteurDoc = (String) req.getParameter("auteurDoc");
			System.out.println(nomDoc + "," + auteurDoc);
			Mediatheque.getInstance().nouveauDocument(type, nomDoc, auteurDoc);
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		req.setAttribute("title", "Dashboard Bibliothecaire");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/bibliothecaire/dash.jsp").forward(req, resp);
	}
}
