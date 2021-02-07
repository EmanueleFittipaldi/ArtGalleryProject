package Acquisto.Controller;

import Acquisto.Model.Carrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/totale")
public class totaleCarrello extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
        double soldi = cart.getTotale();
        soldi = Math.round(soldi * 100);
        soldi = soldi / 100;
        Writer wr = response.getWriter();
        wr.write(String.valueOf(soldi));
    }
}
