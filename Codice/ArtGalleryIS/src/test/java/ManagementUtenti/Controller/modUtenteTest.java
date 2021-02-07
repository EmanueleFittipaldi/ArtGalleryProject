package ManagementUtenti.Controller;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class modUtenteTest
{
    private modUtente servlet;
    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;
    private ServletContext mockedServletContext;

    @BeforeAll
    void setUp()
    {
        servlet = new modUtente();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        mockedServletContext = Mockito.mock(ServletContext.class);

        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
        Mockito.when(mockedRequest.getRequestDispatcher("modificaUtente.jsp")).thenReturn(mockedDispatcher);

    }

    /*username vuoto*/
    @Test
    @Order(1)
    void TC_MODUT_1() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("");
        RuntimeException exception=
                assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }

    /*username null*/
    @Test
    @Order(2)
    void TC_MODUT_2()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn(null);
        NullPointerException exception=
                assertThrows(NullPointerException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }

    /*utente non presente*/
    @Test
    @Order(3)
    void TC_MODUT_3()
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("aliBabà");
        RuntimeException exception=
                assertThrows(RuntimeException.class,()->servlet.doGet(mockedRequest,mockedResponse));
    }

    /*username che c'è*/
    @Test
    @Order(4)
    void TC_MODUT_4() throws ServletException, IOException
    {
        Mockito.when(mockedRequest.getParameter("username")).thenReturn("usr1");

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