package Service;


import DAO.ProductDAO;
import Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductDAO productDAO = new ProductDAO();

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        String choice = request.getParameter("choice");
        switch (choice) {
            case "add": {
                addProduct(request, response);
                return;
            }
            case "filter":{
                filterProduct(request, response);
                return;
            }
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        productDAO.insertProduct(product);
        response.sendRedirect("mainpage.jsp");
    }

    private void filterProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String filterName = request.getParameter("FilterName");
        if (filterName != null) {
            try {
                List<Product> products = productDAO.filterProducts(filterName);
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Description</th>");
                out.println("</tr>");
                for (Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getName() + "</td>");
                    out.println("<td>" + product.getDescription() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (SQLException e) {
                out.println(e.getMessage());
                out.flush();
                return;
            }
        }
    }
}
