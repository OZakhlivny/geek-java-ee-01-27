package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/catalog"})
public class CatalogPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "Каталог");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
        req.setAttribute("currentPage", "/catalog");
        getServletContext().getRequestDispatcher("/page_menu").include(req, resp);
    }
}
