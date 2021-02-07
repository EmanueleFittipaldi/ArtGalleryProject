package Autenticazione.Controller;

import Autenticazione.Model.Admin;
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
class accountServletTest
{
    private  accountServlet servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;
    private ServletContext mockedServletContext;

    @BeforeAll
    void setUp()
    {
        servlet = new accountServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        mockedServletContext = Mockito.mock(ServletContext.class);

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedRequest.getRequestDispatcher("login.jsp")).thenReturn(mockedDispatcher);
        Mockito.when(mockedRequest.getRequestDispatcher("admin.jsp")).thenReturn(mockedDispatcher);
        Mockito.when(mockedRequest.getRequestDispatcher("account.jsp")).thenReturn(mockedDispatcher);

    }

    /*caso in cui ne utente ne admin*/
    @Test
    @Order(1)
    void TC_ACCOUNT_1() throws IOException, ServletException
    {

        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(null);
        Mockito.when(mockedRequest.getSession().getAttribute("Admin")).thenReturn(null);
        servlet.doGet(mockedRequest,mockedResponse);
    }

    /*caso in cui admin*/
    @Test
    @Order(2)
    void TC_ACCOUNT_2() throws IOException, ServletException
    {
        Admin admin = new Admin();
        admin.setUsername("admin0");
        admin.setPassword("emanuele123");
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(null);
        Mockito.when(mockedRequest.getSession().getAttribute("Admin")).thenReturn(admin);
        servlet.doGet(mockedRequest,mockedResponse);
    }

    /*caso in cui utente*/
    @Test
    @Order(3)
    void TC_ACCOUNT_3() throws IOException, ServletException
    {
        Utente utente = new Utente();
        utente.setUsername("usr1");
        utente.setPassword("usr1");
        Mockito.when(mockedRequest.getSession().getAttribute("Utente")).thenReturn(utente);
        Mockito.when(mockedRequest.getSession().getAttribute("Admin")).thenReturn(null);
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