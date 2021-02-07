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

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class rimuoviQuadroTest {

    private rimuoviQuadro servlet;

    private HttpServletRequest mockedRequest;
    private HttpServletResponse mockedResponse;
    private HttpSession mockedSession;


    private Carrello carrello;
    private QuadroDAO quadroDAO;
    private Quadro quadro;

    @BeforeAll
    void setUp()
    {
        servlet = new rimuoviQuadro();
        mockedRequest= Mockito.mock(HttpServletRequest.class);
        mockedResponse= Mockito.mock(HttpServletResponse.class);
        mockedSession = Mockito.mock(HttpSession.class);


        quadroDAO= new QuadroDAO();
        carrello = new Carrello();
        quadro = quadroDAO.doRetrieveById(2);
        carrello.aggiungiQuadro(quadro);

        /*Gestione mock della sessione*/
        Mockito.when(mockedRequest.getSession()).thenReturn(mockedSession);
    }


    //Rimozione quadro dal carrello
    @Test
    @Order(1)
    void TC_REMOVECART_1() throws IOException, ServletException {

        String id = String.valueOf(quadro.getId());
        Mockito.when(mockedSession.getAttribute("Carrello")).thenReturn(carrello);
        Mockito.when(mockedRequest.getParameter("id")).thenReturn(id);

        servlet.doGet(mockedRequest,mockedResponse);
    }


    @AfterAll
    void tearDown()
    {
        mockedRequest=null;
        mockedResponse=null;
        servlet=null;
        mockedSession=null;
    }

}