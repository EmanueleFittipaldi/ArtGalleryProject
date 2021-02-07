package Autenticazione.Controller;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GestioneServletTest
{
    private  GestioneServlet servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private PrintWriter mockedPrintWriter;

    @BeforeAll
    void setUp() throws IOException
    {
        servlet = new GestioneServlet();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedPrintWriter = Mockito.mock(PrintWriter.class);

        /*per gestire il mock della sessione*/
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedResponse.getWriter()).thenReturn(mockedPrintWriter);

    }

    /*caso management quadri*/
    @Test
    @Order(1)
    void TC_GESTIONE_1() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("valore")).thenReturn("Quadri");
        servlet.doGet(mockedRequest,mockedResponse);

    }

    /*caso management quadri*/
    @Test
    @Order(2)
    void TC_GESTIONE_2() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("valore")).thenReturn("Utenti");
        servlet.doGet(mockedRequest,mockedResponse);
    }

    /*caso nessuno dei due*/
    @Test
    @Order(3)
    void TC_GESTIONE_3() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("valore")).thenReturn("");
        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        ()->servlet.doGet(mockedRequest,mockedResponse));

    }

    @AfterAll
    void tearDown()
    {
        servlet=null;
        mockedResponse=null;
        mockedResponse=null;
        mockedSession=null;
    }
}