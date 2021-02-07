package ManagementQuadri.Controller;

import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Model.Utente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaveModQuadroTest {

    private SaveModQuadro servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;

    private Quadro quadro;
    private QuadroDAO quadroDAO;

    private static final String GENERE="Ritratto";
    private static final String TITOLO="La Gioconda";
    private static final String ARTISTA="Leonardo Da Vinci";
    private static final String ANNO="1536";
    private static final String DIMENSIONE="25*50";
    private static final String TECNICA="Olio";
    private static final String PREZZO="15000";
    private static final String IMMAGINE ="images/LeoLaGioconda.jpg";

    @BeforeEach
    void setUp() {
        servlet = new SaveModQuadro();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        quadroDAO= new QuadroDAO();

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);

    }

    @Test
    @Order(1)
    void TC_SAVEMODQUADRO_1() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        Mockito.when(mockedRequest.getRequestDispatcher("admin.jsp")).thenReturn(mockedDispatcher);

    }

    @Test
    @Order(2)
    void TC_SAVEMODQUADRO_2() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn("?a-");
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza del titolo non accettabile");
    }


    @Test
    @Order(3)
    void TC_SAVEMODQUADRO_3() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn("a?!");
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza del nome dell'artista non accettabile");
    }

    @Test
    @Order(4)
    void TC_SAVEMODQUADRO_4() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn("aaaa");
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza dell'anno non accettabile");
    }


    @Test
    @Order(5)
    void TC_SAVEMODQUADRO_5() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn("?-ada");
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza del genere non accettabile");
    }

    @Test
    @Order(6)
    void TC_SAVEMODQUADRO_6() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn("?^A");
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza della tecnica non accettabile");
    }

    @Test
    @Order(7)
    void TC_SAVEMODQUADRO_7() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn("2550");
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn(PREZZO);
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza della dimensione non accettabile");
    }

    @Test
    @Order(8)
    void TC_SAVEMODQUADRO_8() throws IOException, ServletException {
        Mockito.when(mockedRequest.getParameter("id")).thenReturn("2");
        Mockito.when(mockedRequest.getParameter("Genere")).thenReturn(GENERE);
        Mockito.when(mockedRequest.getParameter("Titolo")).thenReturn(TITOLO);
        Mockito.when(mockedRequest.getParameter("Artista")).thenReturn(ARTISTA);
        Mockito.when(mockedRequest.getParameter("Anno")).thenReturn(ANNO);
        Mockito.when(mockedRequest.getParameter("Dimensione")).thenReturn(DIMENSIONE);
        Mockito.when(mockedRequest.getParameter("Tecnica")).thenReturn(TECNICA);
        Mockito.when(mockedRequest.getParameter("Prezzo")).thenReturn("aa");
        Mockito.when(mockedRequest.getParameter("Immagine")).thenReturn(IMMAGINE);

        IOException exception = assertThrows(IOException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Formato o lunghezza del prezzo non accettabile");
    }

    @AfterEach
    void tearDown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
        mockedDispatcher=null;
    }
}