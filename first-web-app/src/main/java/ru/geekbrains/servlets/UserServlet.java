package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        if (userRepository == null) {
            throw new ServletException("UserRepository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            User user = userRepository.findById(id);
            if (user == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        } else if(req.getPathInfo().equals("/add")){
            req.setAttribute("user", new User());
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            userRepository.deleteById(id);
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        String s_id = req.getParameter("id");
        if(s_id == null || s_id.isBlank()) id = null;
        else {
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
        }
        User user = new User(id, req.getParameter("name"), req.getParameter("email"));
        userRepository.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }
}
