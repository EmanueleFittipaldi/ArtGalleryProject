package ManagementQuadri.Controller;

import ManagementOrdini.Model.Ordine;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.*;
import javax.servlet.http.Part;


@WebServlet("/InserisciQuadro")
@MultipartConfig
public class InserisciQuadro extends HttpServlet
{

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Quadro quadro = new Quadro();
        QuadroDAO service = new QuadroDAO();

        String genere = req.getParameter("genere");
        String titolo = req.getParameter("titolo");
        String artista = req.getParameter("artista");
        String anno = req.getParameter("anno");
        String dimensione = req.getParameter("dimensione");
        String tecnica = req.getParameter("tecnica");
        String prezzo = req.getParameter("prezzo");




        /***********************************************************************************************************/
        /*TITOLO*/
        String pattern = "^[\"'a-zA-Zèéààòì]+((\\s|\\-)+['\"a-zA-Zèéààòì]+)*$";
        if(!titolo.matches(pattern) || titolo.length() <1 || titolo.length() > 30)
            throw new IOException("Formato o lunghezza del titolo non accettabile");

        /*ARTISTA*/
        pattern = "^[\"'a-zA-Z]+((\\s|\\-)+['\"a-zA-Z]+)*$";
        if(!artista.matches(pattern) || artista.length() < 1 || artista.length() > 30)
            throw new IOException("Formato o lunghezza del nome dell'artista non accettabile");

        /*ANNO*/
        pattern = "^([0-9]{4})$";
        if(!anno.matches(pattern))
            throw new IOException("Formato o lunghezza dell'anno non accettabile");

        /*GENERE*/
        pattern = "^[\"'a-zA-Z]+((\\s|\\-)+['\"a-zA-Z]+)*$";
        if(!genere.matches(pattern) || genere.length() <5 || genere.length() > 20)
            throw new IOException("Formato o lunghezza del genere non accettabile");

        /*TECNICA*/
        pattern = "^[\"'a-zA-Z]+((\\s|\\-)+['\"a-zA-Z]+)*$";
        if(!tecnica.matches(pattern) ||tecnica.length() <4  || tecnica.length() > 20)
            throw new IOException("Formato o lunghezza della tecnica non accettabile");

        /*DIMENSIONE*/
        pattern = "[0-9.]+\\*[0-9.]+"; //aggiungere il controllo charat
        if(!dimensione.matches(pattern) || dimensione.length() <3 || dimensione.length() >7)
            throw  new IOException("Formato o lunghezza della dimensione non accettabile");

        /*PREZZO*/
        pattern = "(^[^0])[0-9.]+";
        if(!prezzo.matches(pattern) || prezzo.length() > 15 || prezzo.length() < 3)
            throw new IOException("Formato o lunghezza del prezzo non accettabile");
        /***********************************************************************************************************/


        Part filereq = req.getPart("file");
        String path = getServletContext().getRealPath("")+"images";
        System.out.println("path:"+path);
        String pathImg ="images/"+filereq.getSubmittedFileName();
        System.out.println("pathImg:"+pathImg);
        ImageSaver i = new ImageSaver(path);
        i.save(filereq.getInputStream(),filereq.getSubmittedFileName());


        /*controllo della stringa immagine?*/
//        pattern = "^[\\s_a-z-A-Z]{1,}\\.{1}((png)|(jpg)){1}$";
//        if(!immagine.contains(pattern))
//            throw  new IOException("Formato o lunghezza dell'immagine non accettabile");

        quadro.setTitolo(titolo);
        quadro.setGenere(genere);
        quadro.setAnno(Integer.parseInt(anno));
        quadro.setArtista(artista);
        quadro.setTecnica(tecnica);
        quadro.setDimensione(dimensione);
        quadro.setPrezzo(Double.parseDouble(prezzo));
        quadro.setImmagine(pathImg);

        Ordine ordine= new Ordine();
        quadro.setOrdine(ordine);
        service.doSave(quadro);

        /*una volta finito vado avanti dinuovo alla servlet*/
        RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
        dispatcher.forward(req, resp);

    }
}
