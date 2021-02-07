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

@WebServlet("/deletePermanently")
public class deletePermanently  extends HttpServlet
{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        QuadroDAO service = new QuadroDAO();
        Quadro quadro = service.doRetrieveById(Integer.parseInt(req.getParameter("id")));
        String path = getServletContext().getRealPath("") + "images";   //path cartella immagini
        ImageSaver i = new ImageSaver(path);

        i.delete(quadro.getImmagine().substring(7));
        service.doDeletebyIndex(quadro.getId());

        RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
        dispatcher.forward(req, resp);
    }
}
