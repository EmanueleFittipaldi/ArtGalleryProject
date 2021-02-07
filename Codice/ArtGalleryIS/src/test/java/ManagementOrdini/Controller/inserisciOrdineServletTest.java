package ManagementOrdini.Controller;

import Acquisto.Model.Carrello;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Model.Utente;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class inserisciOrdineServletTest
{
    private inserisciOrdineServlet servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;
    Utente utente;
    Carrello carrello;

    @BeforeAll
    void setUp()
    {
        servlet = new inserisciOrdineServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        utente= new Utente();
        carrello= new Carrello();

        utente.setUsername("usr1");
        utente.setPassword("marioverdi66");
        utente.setEmail("Mario@Verdi.it");
        utente.setCognome("Verdi");
        utente.setNome("Mario");
        utente.setNazionalità("Italia");

        QuadroDAO quadroDAO = new QuadroDAO();
        ArrayList<Quadro> quadriNelDB = (ArrayList<Quadro>) quadroDAO.doRetrieveAll();
        Quadro quadroA = quadriNelDB.get(6);
        Quadro quadroB = quadriNelDB.get(7);

        carrello.aggiungiQuadro(quadroA);
        carrello.aggiungiQuadro(quadroB);

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedRequest.getRequestDispatcher("/index.html")).thenReturn(mockedDispatcher);


        /*adesso ho l'utente e ho dei quadri in un carrello, in modo che posso testare
        * la Servlet*/
    }

    /*caso tutto ok*/
    @Test
    @Order(1)
    void TC_INSERT_ORDER_1() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(utente);
        Mockito.when(mockedRequest.getSession().getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedRequest.getParameter("tipoCarta")).thenReturn("Mastercard");
        Mockito.when(mockedRequest.getRequestDispatcher("acquistoSuccesso.jsp")).thenReturn(mockedDispatcher);
        servlet.doGet(mockedRequest,mockedResponse);
    }

    @Test
    @Order(2)
    void TC_INSERT_ORDER_2() throws ServletException, IOException
    {
        Utente utenteEmpty=null;
        Carrello carrelloEmpty=null;
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(utenteEmpty);
        Mockito.when(mockedRequest.getSession().getAttribute("Carrello")).thenReturn(carrelloEmpty);
        Mockito.when(mockedRequest.getParameter("tipoCarta")).thenReturn("Mastercard");
        assertThrows(NullPointerException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }

    @Test
    @Order(3)
    void TC_INSERT_ORDER_3() throws ServletException, IOException
    {
        Utente utenteEmpty=new Utente();
        Carrello carrelloEmpty=null;
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(utenteEmpty);
        Mockito.when(mockedRequest.getSession().getAttribute("Carrello")).thenReturn(carrelloEmpty);
        Mockito.when(mockedRequest.getParameter("tipoCarta")).thenReturn("Mastercard");
        assertThrows(NullPointerException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }
    @Test
    @Order(4)
    void TC_INSERT_ORDER_4() throws ServletException, IOException
    {
        Utente utenteEmpty=null;
        Carrello carrelloEmpty=new Carrello();
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(utenteEmpty);
        Mockito.when(mockedRequest.getSession().getAttribute("Carrello")).thenReturn(carrelloEmpty);
        Mockito.when(mockedRequest.getParameter("tipoCarta")).thenReturn("Mastercard");
        assertThrows(IllegalArgumentException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }


    @AfterAll
    void tearDown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
        mockedDispatcher=null;

    }
}