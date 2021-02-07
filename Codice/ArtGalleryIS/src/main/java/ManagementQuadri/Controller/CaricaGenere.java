package ManagementQuadri.Controller;

import ManagementQuadri.Model.QuadroDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CaricaGenere")
public class CaricaGenere extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        QuadroDAO service = new QuadroDAO();
        JSONArray listaQuadri;
        if (request.getParameter("CatName").equalsIgnoreCase("Tutti"))
        {
            listaQuadri = service.AjaxLoadAll();
        } else
        {
            listaQuadri = service.AjaxGenereLoader(request.getParameter("CatName"));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(listaQuadri.toString());

    }
}
