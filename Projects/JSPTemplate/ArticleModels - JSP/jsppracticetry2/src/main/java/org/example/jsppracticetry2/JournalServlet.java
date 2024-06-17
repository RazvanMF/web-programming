package org.example.jsppracticetry2;

import DAOs.ArticleDAO;
import DAOs.JournalDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "JournalServlet", value = "/JournalServlet")
public class JournalServlet extends HttpServlet {
    final private ArticleDAO articleDAO = new ArticleDAO();
    final private JournalDAO journalDAO = new JournalDAO();
    private String username;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        java.io.PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.print("<form action=\"ArticleServlet\" method=\"get\">");
        out.print("<input type=\"text\" name=\"journalId\">");
        out.print("<input type=\"submit\" value=\"Gib journal Id\">");
        out.print("<input type=\"hidden\" name=\"username\" value=\"" + username + "\">");
        out.print("</form><a href=\"addpage.jsp\"><button>Add an article!</button></a></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(journalDAO.getIdByName(req.getParameter("journalName")) == 0){
            journalDAO.insertJournal(req.getParameter("journalName"));
        }
        articleDAO.insertArticle(username, journalDAO.getIdByName(req.getParameter("journalName")), req.getParameter("articleSummary"));
    }
}
