package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

@WebServlet("/abonne")
public class Abonne extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Abonne() {
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
		else if (u.isBibliothecaire())
			resp.sendRedirect(req.getContextPath() + "/bibliotheque");
		else {
			process(req, resp);
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = "" + req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		if (action.equals("emprunt")) {
			int id = Integer.parseInt(req.getParameter("emprunt"));
			Document d = Mediatheque.getInstance().getDocument(id);
			try {
				synchronized (d) {
					d.emprunter((Utilisateur) req.getSession().getAttribute("user"));
				}
			} catch (EmpruntException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		} else if (action.equals("retour")) {
			int id = Integer.parseInt(req.getParameter("retour"));
			Document d = Mediatheque.getInstance().getDocument(id);
			synchronized (d) {
				d.retour();
			}
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		req.setAttribute("title", "Dashboard Abonne");
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/abonne/dash.jsp").forward(req, resp);
	}
}
