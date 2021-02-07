package Autenticazione.Controller;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServletTest
{
    private  LoginServlet servlet;
    private  HttpServletRequest mockedRequest;
    private  HttpServletResponse mockedResponse;
    private  HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;

    @BeforeAll
    void setUp()
    {
        servlet = new LoginServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);

        /*per gestire il mock della sessione*/
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);

    }

    /*caso c'è ed è un utente*/
    @Test
    @Order(1)
    void TC_LOGIN_1() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("usr1");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("usr1");
        Mockito.when(mockedRequest.getRequestDispatcher("loginSuccesso.jsp")).thenReturn(mockedDispatcher);
        servlet.doPost(mockedRequest,mockedResponse);
    }

    /*caso c'è ed è un admin*/
    @Test
    @Order(2)
    void TC_LOGIN_2() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("admin0");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("emanuele123");
        Mockito.when(mockedRequest.getRequestDispatcher("loginSuccesso.jsp")).thenReturn(mockedDispatcher);
        servlet.doPost(mockedRequest,mockedResponse);
    }

    /*caso semplicemente non c'è, quindi sia la password sia il nome non sono associati a nessuno*/
    @Test
    @Order(3)
    void TC_LOGIN_3() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("admin033");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("emanuele1232");
        RuntimeException exception= assertThrows(RuntimeException.class,()->servlet.doPost(mockedRequest,mockedResponse));
    }

    /*utente, solo username presente, e password non combacia*/
    @Test
    @Order(4)
    void TC_LOGIN_4() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("usr1");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("emanuele1232");
        RuntimeException exception= assertThrows(RuntimeException.class,()->servlet.doPost(mockedRequest,mockedResponse));
    }
    /*utente, solo password combacia ma non utente*/
    @Test
    @Order(5)
    void TC_LOGIN_5() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("admin033");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("usr1");
        RuntimeException exception= assertThrows(RuntimeException.class,()->servlet.doPost(mockedRequest,mockedResponse));
    }
    /*admin e solo username presente e password non combacia*/
    @Test
    @Order(6)
    void TC_LOGIN_6() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("admin033");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("magoMerlino");
        RuntimeException exception= assertThrows(RuntimeException.class,()->servlet.doPost(mockedRequest,mockedResponse));
    }
    /*admin e solo password presente e username non combacia*/
    @Test
    @Order(7)
    void TC_LOGIN_7() throws IOException, ServletException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("IdoNotExist");
        Mockito.when(mockedRequest.getParameter("password")).thenReturn("emanuele1232");
        RuntimeException exception= assertThrows(RuntimeException.class,()->servlet.doPost(mockedRequest,mockedResponse));
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