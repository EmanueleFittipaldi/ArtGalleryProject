package Acquisto.Controller;

import Acquisto.Model.Carrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeItem")
public class rimuoviQuadro extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
        cart.rimuoviQuadrobyId(Integer.parseInt(request.getParameter("id")));
    }
}
