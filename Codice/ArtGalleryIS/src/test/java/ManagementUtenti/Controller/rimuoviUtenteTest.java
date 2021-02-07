package ManagementUtenti.Controller;

import Autenticazione.Controller.accountServlet;
import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;
import org.junit.After;
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
class rimuoviUtenteTest
{
    private rimuoviUtente servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;
    private ServletContext mockedServletContext;

    @BeforeAll
    void setUp()
    {
        servlet = new rimuoviUtente();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        mockedServletContext = Mockito.mock(ServletContext.class);

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedRequest.getRequestDispatcher("admin.jsp")).thenReturn(mockedDispatcher);

    }

    /*caso di username corrispondente ad un utente che davvero c'è da eliminare*/
    @Test
    @Order(1)
    void TC_RIMUOVI_1() throws ServletException, IOException
    {
        /*inserisco un utente dummy da eliminare per
        * testare la servlet e per non intaccare
        * quelli che ci sonog gia dentro*/
        UtenteDAO service = new UtenteDAO();
        Utente utente = new Utente();
        utente.setUsername("UserTest");
        utente.setEmail("alice@alice.it");
        utente.setNazionalità("Italia");
        utente.setCognome("De filippo");
        utente.setNome("Edoardo");
        utente.setPassword("19881887");
        service.doSave(utente);

        Mockito.when(mockedRequest.getParameter("username")).thenReturn(utente.getUsername());
        servlet.doGet(mockedRequest,mockedResponse);
    }

    /*caso utente che semplicemente non c'è */
    @Test
    @Order(2)
    void TC_RIMUOVI_2() throws ServletException, IOException
    {
        /*inserisco un utente dummy da eliminare per
         * testare la servlet e per non intaccare
         * quelli che ci sonog gia dentro*/
        UtenteDAO service = new UtenteDAO();
        Utente utente = new Utente();
        utente.setUsername("UserTest");
        utente.setEmail("alice@alice.it");
        utente.setNazionalità("Italia");
        utente.setCognome("De filippo");
        utente.setNome("Edoardo");
        utente.setPassword("19881887");

        Mockito.when(mockedRequest.getParameter("username")).thenReturn(utente.getUsername());
        RuntimeException exception=
                assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));

    }
    @Test
    @Order(3)
    void TC_RIMUOVI_3()
    {

        Mockito.when(mockedRequest.getParameter("username")).thenReturn("");
        IllegalArgumentException exception=
                assertThrows(IllegalArgumentException.class,()->servlet.doGet(mockedRequest,mockedResponse));

    }
    @Test
    @Order(4)
    void TC_RIMUOVI_4()
    {

        Mockito.when(mockedRequest.getParameter("username")).thenReturn(null);
        NullPointerException exception=
                assertThrows(NullPointerException.class,()->servlet.doGet(mockedRequest,mockedResponse));

    }

    @AfterAll
    void teardown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
        mockedServletContext=null;
        mockedDispatcher=null;

    }
}