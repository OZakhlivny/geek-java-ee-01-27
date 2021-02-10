package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_menu")
public class PageMenuServlet extends HttpServlet {
    private static final String rootPath = "/first-web-app";
    private static final String[] items = new String[]{"/mainpage", "/catalog", "/product", "/cart", "/order"};
    private static final String[] itemTitles = new String[]{"Главная страница", "Каталог", "Товар", "Корзина", "Заказ"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = (String) req.getAttribute("currentPage");
        resp.getWriter().println("<table border = 0 cellspacing=2 cellpadding=2>");
        for(int i = 0; i < items.length; i++){
            if(!currentPage.equals(items[i]))
                resp.getWriter().printf("<td><a href=\"%s%s\">%s</a></td>", rootPath, items[i], itemTitles[i]);
            else
                resp.getWriter().printf("<td>%s</td>", itemTitles[i]);
        }
        resp.getWriter().println("</table>");
    }
}
