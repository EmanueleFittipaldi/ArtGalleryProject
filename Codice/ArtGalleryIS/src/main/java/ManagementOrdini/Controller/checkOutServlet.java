package ManagementOrdini.Controller;


import Acquisto.Model.Carrello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkOut")
public class checkOutServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {


        /*semplicemente se l'utente è null allora non ha fatto accesso nessuno e quindi devo fare accesso
         * altrimenti vado alla pagina di checkout*/
        HttpSession session = req.getSession();

        Carrello carrello= (Carrello)req.getSession().getAttribute("Carrello");

        if (carrello.getSize()==0 && session.getAttribute("Utente")== null)
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        if (carrello.getSize()==0 && session.getAttribute("Utente")!= null)
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        if (session.getAttribute("Utente")== null && carrello.getSize()>0)
        {

            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }


        if (session.getAttribute("Utente")!= null && carrello.getSize()!=0)
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("checkout.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
