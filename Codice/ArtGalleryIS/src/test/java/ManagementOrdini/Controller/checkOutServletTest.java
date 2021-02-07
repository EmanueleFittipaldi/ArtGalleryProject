package ManagementOrdini.Controller;

import Acquisto.Model.Carrello;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Controller.modUtente;
import ManagementUtenti.Model.Utente;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class checkOutServletTest
{
    private checkOutServlet servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;
    private ServletContext mockedServletContext;

    private Carrello carrello;


    @BeforeAll
    void setUp()
    {
        servlet = new checkOutServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        mockedServletContext = Mockito.mock(ServletContext.class);

        carrello = new Carrello();

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);

    }


    //Carrello vuoto e Utente non loggato
    @Test
    @Order(1)
    void TC_CCK_1() throws ServletException, IOException
    {
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedSession.getAttribute("Utente")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher("index.html")).thenReturn(mockedDispatcher);
        servlet.doGet(mockedRequest,mockedResponse);

    }


    //Carrello vuoto e Utente loggato
    @Test
    @Order(2)
    void TC_CCK_2() throws ServletException, IOException
    {
        Utente utente = new Utente();
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedSession.getAttribute("Utente")).thenReturn(utente);
        Mockito.when(mockedRequest.getRequestDispatcher("index.html")).thenReturn(mockedDispatcher);
        servlet.doGet(mockedRequest,mockedResponse);
    }


    //Carrello con quadro e Utente non loggato
    @Test
    @Order(3)
    void TC_CCK_3() throws ServletException, IOException
    {
        QuadroDAO quadroDAO = new QuadroDAO();
        Quadro quadro1 = quadroDAO.doRetrieveById(2);
        carrello.aggiungiQuadro(quadro1);
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedSession.getAttribute("Utente")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher("login.jsp")).thenReturn(mockedDispatcher);
        servlet.doGet(mockedRequest,mockedResponse);
        carrello.svuotaCarrello();
    }

    //Carrello con quadro e Utente loggato
    @Test
    @Order(4)
    void TC_CCK_4() throws ServletException, IOException
    {
        Utente utente = new Utente();
        QuadroDAO quadroDAO = new QuadroDAO();
        Quadro quadro1 = quadroDAO.doRetrieveById(2);
        carrello.aggiungiQuadro(quadro1);
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedSession.getAttribute("Utente")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher("checkout.jsp")).thenReturn(mockedDispatcher);
        servlet.doGet(mockedRequest,mockedResponse);
    }


    @AfterAll
    void tearDown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
        mockedServletContext=null;
        mockedDispatcher=null;
    }
}