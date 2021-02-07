package Autenticazione.Controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handler")
public class Handler extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String usrChoiche = request.getParameter("form");

        //Indirizzo a cui reindirizzare
        String address = "Homepage.jsp";

        switch (usrChoiche)
        {
            case "home":
                address = "/index.html";
                break;
            case "cart":
                address = "carrello.jsp";
                break;
            case "login":
                address = "login.jsp";
                break;
            case "contact":
                address = "contact.jsp";
                break;
            //Caso in cui l'utente si disconnette, bisogna invaldiare la sessione, verranno rimossi tutti gli attributi di sessione anche
            case "logout":
                address = "/index.html";
                request.getSession().invalidate();
                break;
            default:
                address = "Homepage.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
