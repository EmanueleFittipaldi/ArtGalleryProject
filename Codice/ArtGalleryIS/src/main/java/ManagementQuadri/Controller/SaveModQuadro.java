package ManagementQuadri.Controller;

import ManagementQuadri.Model.*;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveModQuadro")
public class SaveModQuadro extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        /*Quadro corrisponde al quadro che verrà usato per aggiornare
        * il corrispettivo quadro presente nel database.*/
       // Quadro quadro = new Quadro();
        QuadroDAO service = new QuadroDAO();


        /*Estrapolo i parametri passati dal form di modifica, così posso
        * costruire il Quadro aggiornato che prenderà il posto di quello
        * presente nel database.*/
        int id = Integer.parseInt(request.getParameter("id"));
        Quadro quadro = service.doRetrieveById(id);

        String titolo = request.getParameter("Titolo");
        String genere = request.getParameter("Genere");
        String anno = request.getParameter("Anno");
        String artista = request.getParameter("Artista");
        String tecnica = request.getParameter("Tecnica");
        String dimensione = request.getParameter("Dimensione");
        String prezzo = request.getParameter("Prezzo");
        String immagine = request.getParameter("Immagine");



        //Inizio controlli sui formati dati in input
/*
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

        //quadro.setId(id);
        System.out.println(quadro.getId());
        quadro.setTitolo(titolo);
        quadro.setGenere(genere);
        quadro.setAnno(Integer.parseInt(anno));
        quadro.setArtista(artista);
        quadro.setTecnica(tecnica);

        quadro.setDimensione(dimensione);
        quadro.setPrezzo(Double.parseDouble(prezzo));
        quadro.setImmagine(immagine);

        service.doUpdate(quadro);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);


    }
}
