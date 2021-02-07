package ManagementQuadri.Controller;

import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vaiAlQuadro")
public class vaiAlQuadroServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        QuadroDAO service = new QuadroDAO();
        Quadro quadro = service.doRetrieveById(Integer.parseInt(req.getParameter("Id")));
        req.setAttribute("quadro", quadro);
        String address = "prodotto.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
