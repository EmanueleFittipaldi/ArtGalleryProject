package Autenticazione.Controller;

import Autenticazione.Model.Admin;
import Autenticazione.Model.AdminDAO;
import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        /*reperisco i parametri dal form di login*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

     username.trim();
     password.trim();

     System.out.println("LoginServlet| username: "+username);
        System.out.println("LoginServlet| password: "+password);

        /*non facciamo nessun controllo qui perchè, per questioni
        * di sicurezza, non vogliamo andare a fornire informazioni
        * su come deve essere il formato di username e password, in caso
        * qualche male intenzionato, stia cercando di penetrare nel
        * sistema attraverso qualche algoritmo di forza bruta che
        * esplora tutte le possibili combinazioni di parametri*/


        UtenteDAO service = new UtenteDAO();
        Utente utente = service.doRetrieveByUsernamePassword(username, password);


        /*controllo per capire se si sta facendo il login
        * con delle credenziali di amministratore
        * o con delle credenziali di utente*/
        if (utente == null)
        {
            //Vediamo se c'è un amministratore con quelle credenziali
            AdminDAO adminService = new AdminDAO();
            Admin admin = adminService.doRetrieveByUsernamePassword(username, password);

            //Se admin è null anch'esso allora l'utente non è nè un admin e nè un collezionista
            if (admin == null)
            {
                throw new RuntimeException("Non c'è un utente con queste credenziali");
            } else
            {
                //admin non è null, quindi l'utente loggato è un admin
                request.getSession().setAttribute("Admin", admin);
                //response.sendRedirect("index.html");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginSuccesso.jsp");
                requestDispatcher.forward(request, response);
            }
            //Caso in cui è un utente
        } else
        {
            request.getSession().setAttribute("Utente", utente);
            //response.sendRedirect("index.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginSuccesso.jsp");
            requestDispatcher.forward(request, response);
        }

    }

}
