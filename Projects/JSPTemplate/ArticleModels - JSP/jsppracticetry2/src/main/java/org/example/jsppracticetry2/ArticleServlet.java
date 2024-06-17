package org.example.jsppracticetry2;

import DAOs.ArticleDAO;
import Models.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticleServlet", value = "/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    final private ArticleDAO articleDAO = new ArticleDAO();
    private String username;
    private int journalId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        journalId = Integer.parseInt(req.getParameter("journalId"));
        List<Article> articles = articleDAO.filteredSelect(username, journalId);
        java.io.PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<table><tr><td>id</td><td>user</td><td>journalId</td><td>summary</td><td>date</td><tr>");
        articles.forEach(article -> {
            out.println("<table><tr>");
            out.println("<td>" + article.id +"</td>");
            out.println("<td>" + article.user +"</td>");
            out.println("<td>" + article.journalId +"</td>");
            out.println("<td>" + article.summary +"</td>");
            out.println("<td>" + article.date +"</td>");
            out.println("</tr>");
        });
        out.print("</table></body></html>");
    }
}
