package Acquisto.Controller;

import Acquisto.Model.Carrello;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class aggiungiAlCarrelloTest
{
    private aggiungiAlCarrello servlet;
    private Carrello carrello;
    private QuadroDAO quadroDAO;
    private Quadro quadro;
    private Quadro quadro2;
    private Quadro quadro3;

    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;
    private RequestDispatcher mockedDispatcher;



    @BeforeAll
    void setUp()
    {
        servlet = new aggiungiAlCarrello();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);
        mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        quadroDAO= new QuadroDAO();
        carrello = new Carrello();
        quadro = quadroDAO.doRetrieveById(2);
        quadro2 = quadroDAO.doRetrieveById(3);
        quadro3 = quadroDAO.doRetrieveById(4);
        /*Gestione mock della sessione*/
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
    }

    //Aggiunta quadro al carrello
    @Test
    @Order(1)
    void TC_ADDCART_1() throws IOException, ServletException
    {

        String id = String.valueOf(quadro2.getId());
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedRequest.getParameter("id")).thenReturn(id);
        Mockito.when(mockedRequest.getRequestDispatcher("index.html")).thenReturn(mockedDispatcher);

        servlet.doGet(mockedRequest,mockedResponse);
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