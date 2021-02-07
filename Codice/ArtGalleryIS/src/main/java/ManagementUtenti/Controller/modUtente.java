package ManagementUtenti.Controller;


import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modUtente")
public class modUtente extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String username = req.getParameter("username");
        Utente utente;
        UtenteDAO utenteDAO = new UtenteDAO();
        utente = utenteDAO.doRetrieveByUsername(username);


        if(username.equals("") || username.equals(null) || utente==null)
            throw new IllegalArgumentException();

        req.setAttribute("utente", utente);
        RequestDispatcher dispatcher = req.getRequestDispatcher("modificaUtente.jsp");
        dispatcher.forward(req, resp);
    }
}
