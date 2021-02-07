package Autenticazione.Controller;

import Autenticazione.Model.Admin;
import ManagementOrdini.Model.OrdineDAO;
import ManagementQuadri.Model.Quadro;
import ManagementUtenti.Model.Utente;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



@WebServlet("/accountHandler")
public class accountServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        /*se non c'è nessun utente loggato allora non posso accedere alla pagina e dunque faccio un forward
         * alla pagina in cui mi devo loggare*/
        Utente utente = (Utente) request.getSession().getAttribute("Utente");
        Admin admin = (Admin) request.getSession().getAttribute("Admin");

        /*devo loggarmi con qualcosa se voglio accedere
        * all'area personale, quindi vengo reindirizzato alla pagina
        * per effettuare il login.*/
        if (utente == null && admin == null)
        {
            String address = "login.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        //Se sono un amministratore vado nella pagina
        //di gestione per gli amministratori
        else if (admin != null && utente == null)
        {
            String address = "admin.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
        //Caso in cui sono un utente registrato, devo caricare tutti i quadri
        //acquistati
        else
        {
            /*retrieveByUsername, permette di reperire tutti i quadri
            acquistati da un determinato utente, pescando prima tutti gli ordini
            che ha effettuato, e facendo join e filtering delle righe, con la
            tabella dei quadri.*/

            OrdineDAO service = new OrdineDAO();
            ArrayList<Quadro> listaQuadri = service.retrieveByUsername(utente.getUsername());

            request.setAttribute("listaQuadri", listaQuadri);
            String address = "account.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

        }
    }
}
