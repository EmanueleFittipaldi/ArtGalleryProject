package Autenticazione.Controller;

/*IMPORT*/
import Acquisto.Model.Carrello;
import Autenticazione.NazioniGenerator;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*Questa servlet si occupa di caricare dal database un numero che noi stabiliamo
* di quadri, e li inserisce nell'oggetto di request da passare poi all'index.*/
@WebServlet("/index.html")
public class StartServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        /*Creo la sessione se non esiste*/
        HttpSession mysession= request.getSession(true);

        /*Creo l'oggetto Carrello legato alla sessione, se non è stato
        * già creato.*/
        if(request.getSession().getAttribute("Carrello")==null)
            request.getSession().setAttribute("Carrello",new Carrello());

//        if(request.getSession().getAttribute("listaNazioni")==null)
//            request.getSession().setAttribute("listaNazioni",new NazioniGenerator());

        if(getServletContext().getAttribute("listaNazioni")==null)
            getServletContext().setAttribute("listaNazioni",new NazioniGenerator());


        /*Reperimento dei quadri da mostarre nella Landing Page*/
        QuadroDAO service = new QuadroDAO();
        List<Quadro> listaQuadri = service.doRetrieveAll();

        /*li inseriamo nella richiesta e li mandiamo alla jsp della Homepage*/
        request.setAttribute("listaQuadri",listaQuadri);
        String address = "Homepage.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
