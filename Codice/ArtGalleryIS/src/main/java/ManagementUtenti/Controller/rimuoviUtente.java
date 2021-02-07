package ManagementUtenti.Controller;

import ManagementUtenti.Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rimuoviUtente")
public class rimuoviUtente extends HttpServlet
{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        UtenteDAO utenteDAO = new UtenteDAO();
        String username= req.getParameter("username");

        if(username.equals("") || username==null)
            throw new IllegalArgumentException();

        utenteDAO.delete(username);
        RequestDispatcher dispatcher= req.getRequestDispatcher("admin.jsp");
        dispatcher.forward(req,resp);
    }
}
