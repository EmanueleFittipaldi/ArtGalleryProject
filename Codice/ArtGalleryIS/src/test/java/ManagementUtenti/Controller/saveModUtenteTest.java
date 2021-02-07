package ManagementUtenti.Controller;

import Autenticazione.Controller.RegistrationServlet;
import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class saveModUtenteTest
{
    private saveModUtente servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;

    private List<String> lista;
    private Utente utente;
    private UtenteDAO service;
    private Utente UTENTE;

    /*parametri corretti*/
    private static final String USERNAME="mario98";
    private static final String NAZIONALITA="Italia";
    private static final String NOME="Mario";
    private static final String COGNOME="Rossi";
    private static final String EMAIL="mario91@alice.it";
    private static final String PASSWORD="mario94Best";



    @BeforeAll
    void setUp()
    {
        servlet = new saveModUtente();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        service= new UtenteDAO();


        /*setto un utente fittizio che vado a modificare per non andare
        * ad intaccare gli utenti che già ho nel database*/
        if(service.doRetrieveByUsername("mario98")==null)
        {
            UTENTE = new Utente();
            UTENTE.setUsername(USERNAME);
            UTENTE.setNazionalità(NAZIONALITA);
            UTENTE.setNome(NOME);
            UTENTE.setCognome(COGNOME);
            UTENTE.setEmail(EMAIL);
            UTENTE.setPassword(PASSWORD);
            service.doSave(UTENTE);
        }


        lista= new ArrayList<>();
        lista.add("Italia");
        lista.add("Stati Uniti D'America");

        /*per gestire il mock della sessione*/
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedRequest.getSession().getAttribute("listaNazioni")).thenReturn(lista);
        Mockito.when(mockedRequest.getRequestDispatcher("admin.jsp")).thenReturn(mockedDispatcher);

    }

    @Test
    @Order(1)
    void TC_SAVEMODUT_1()
    {


        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn("isolaCheNonCè");
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }

    /*caso nazione valida, e aggiornamento nazione effettuato con successo*/
    @Test
    @Order(2)
    void TC_SAVEMODUT_2() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn("Stati Uniti d'America");
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

      servlet.doGet(mockedRequest,mockedResponse);

    }


    @Test
    @Order(3)
    void TC_SAVEMODUT_3()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn("a");
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(4)
    void TC_SAVEMODUT_4()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn("asdqwezxcasdqweasdqweasdzxcasdqwe");
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(5)
    void TC_SAVEMODUT_5()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn("Mario;");
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    /*caso nome ok*/
    @Test
    @Order(6)
    void TC_SAVEMODUT_6() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn("Giovanni");
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        servlet.doGet(mockedRequest,mockedResponse);


    }


    @Test
    @Order(7)
    void TC_SAVEMODUT_7()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn("a");
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(8)
    void TC_SAVEMODUT_8()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn("asdqwezxcasdqweasdqweasdzxcasdqwe");
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(9)
    void TC_SAVEMODUT_9()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn("Ross;");
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(10)
    void TC_SAVEMODUT_10() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn("De Filippo");
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        servlet.doGet(mockedRequest,mockedResponse);


    }


    @Test
    @Order(11)
    void TC_SAVEMODUT_11()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn("as@a");
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");

    }


    @Test
    @Order(12)
    void TC_SAVEMODUT_12()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn("as@aasdasdasdasdasdasdasdasdasdasdasd.it");
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");


    }


    @Test
    @Order(13)
    void TC_SAVEMODUT_13()
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn("asd:;;@alice.it");
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        RuntimeException exception = assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
        assertEquals(exception.getMessage(),"Campi non corretti");


    }


    @Test
    @Order(14)
    void TC_SAVEMODUT_14() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("Username")).thenReturn(USERNAME);
        Mockito.when(mockedRequest.getParameter("Nazionalita")).thenReturn(NAZIONALITA);
        Mockito.when(mockedRequest.getParameter("Nome")).thenReturn(NOME);
        Mockito.when(mockedRequest.getParameter("Cognome")).thenReturn(COGNOME);
        Mockito.when(mockedRequest.getParameter("Email")).thenReturn(EMAIL);
        Mockito.when(mockedRequest.getParameter("Password")).thenReturn(PASSWORD);

        servlet.doGet(mockedRequest,mockedResponse);

        service.delete("mario98");


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