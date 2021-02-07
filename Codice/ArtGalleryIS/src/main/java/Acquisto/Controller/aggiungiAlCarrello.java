package Acquisto.Controller;

import Acquisto.Model.Carrello;
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

@WebServlet("/AggiungiAlcarrello")
public class aggiungiAlCarrello extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("Carrello");
        QuadroDAO service = new QuadroDAO();
        Quadro quadro = service.doRetrieveById(Integer.parseInt(request.getParameter("id")));
        carrello.aggiungiQuadro(quadro);
        session.removeAttribute("Carrello");
        session.setAttribute("Carrello", carrello);

        String address = "index.html";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
