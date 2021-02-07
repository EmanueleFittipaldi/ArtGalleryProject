package Autenticazione.Controller;

import Autenticazione.NazioniGenerator;
import ManagementUtenti.Model.UtenteDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegistrationServletTest
{
    private  RegistrationServlet servlet;
    private  HttpServletRequest mockedRequest;
    private  HttpServletResponse mockedResponse;
    private  HttpSession mockedSession;
    private  List<String> lista;
    private ServletContext mockedContext;
    private RequestDispatcher mockedDispatcher;

    @BeforeAll
    void setUp()
    {
        servlet = new RegistrationServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedContext = Mockito.mock(ServletContext.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);


        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);



    }

    /*lunghezza username minore di 1*/
    @Test
    @Order(1)
    void TC_REGISTRATION_1()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*lunghezza username maggiore di 15*/
    @Test
    @Order(2)
    void TC_REGISTRATION_2()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("SanFrancescoDiAssisiSantissimoC\n" +
                "hiesa");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*caso lunghezza giusta ma formato errato*/
    @Test
    @Order(3)
    void TC_REGISTRATION_3()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRoss;");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*username giusto e password sbagliata in lunghezza*/
    @Test
    @Order(4)
    void TC_REGISTRATION_4()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("pass");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*caso password lunghezza eccessiva*/
    @Test
    @Order(5)
    void TC_REGISTRATION_5()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*caso lunghezza giusta ma formato errato*/
    @Test
    @Order(6)
    void TC_REGISTRATION_6()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italianos 4");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    /*caso password corretta */
    @Test
    @Order(7)
    void TC_REGISTRATION_7()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("a@b");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(8)
    void TC_REGISTRATION_8()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("emaileccessivamentelunga@fastweb.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(9)
    void TC_REGISTRATION_9()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.co;");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(10)
    void TC_REGISTRATION_10()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("a");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(11)
    void TC_REGISTRATION_11()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("nomeEsgeratamenteLungoambarabaciccicocco");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(12)
    void TC_REGISTRATION_12()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario;");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(13)
    void TC_REGISTRATION_13()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("c");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(14)
    void TC_REGISTRATION_14()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("asdasdasdasdasdasdasdasdasdasdasdasdadsasd");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(15)
    void TC_REGISTRATION_15()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("???????");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(16)
    void TC_REGISTRATION_16()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("Rossi");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("bob");

        assertThrows(IllegalArgumentException.class, ()-> servlet.doPost(mockedRequest,mockedResponse));

    }

    @Test
    @Order(17)
    void TC_REGISTRATION_17() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("MarioRossi");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("Italia94");
        Mockito.when(mockedRequest.getParameter("email")).thenReturn("newUser@tiscali.com");
        Mockito.when(mockedRequest.getParameter("nome")).thenReturn("Mario");
        Mockito.when(mockedRequest.getParameter("cognome")).thenReturn("Rossi");
        Mockito.when(mockedRequest.getParameter("nazionalita")).thenReturn("Italia");
        Mockito.when(mockedRequest.getRequestDispatcher("registrazioneSuccesso.jsp")).thenReturn(mockedDispatcher);

       servlet.doPost(mockedRequest,mockedResponse);


    }


    @AfterAll
    void tearDown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
        mockedDispatcher=null;

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.delete("MarioRossi");
        utenteDAO=null;
    }
}