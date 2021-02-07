package Autenticazione.Controller;

import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Model.UtenteDAO;
import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gestione")
public class GestioneServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String op = req.getParameter("valore");
        String risposta = "";

        if (op.equalsIgnoreCase("Quadri"))
        {
            QuadroDAO service = new QuadroDAO();
            JSONArray listaQuadri;
            listaQuadri = service.AjaxLoadAll();
            risposta = listaQuadri.toString();
        }
        else if (op.equalsIgnoreCase("Utenti"))
        {
            UtenteDAO service = new UtenteDAO();
            org.json.JSONArray listaUtenti;
            listaUtenti = service.AjaxLoadAll();
            risposta = listaUtenti.toString();

        }
        else
        {
            throw new RuntimeException();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        resp.getWriter().append(risposta);


    }

}
