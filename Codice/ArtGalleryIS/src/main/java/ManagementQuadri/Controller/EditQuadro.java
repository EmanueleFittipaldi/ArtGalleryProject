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

@WebServlet("/modQuadro")
public class EditQuadro extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Quadro quadro = new Quadro();
        QuadroDAO service = new QuadroDAO();

        quadro = service.doRetrieveById(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("quadro", quadro);
        RequestDispatcher dispatcher = req.getRequestDispatcher("modificaQuadro.jsp");
        dispatcher.forward(req, resp);
    }
}
