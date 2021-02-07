package Acquisto.Model;

import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Controller.modUtente;
import org.junit.jupiter.api.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarrelloTest
{
   private QuadroDAO quadroDAO=new QuadroDAO();
   private Quadro quadro=quadroDAO.doRetrieveById(2);
    private Quadro quadro2 = quadroDAO.doRetrieveById(3);
    private Quadro quadro3 = quadroDAO.doRetrieveById(4);
   private Carrello carrello= new Carrello();


    /*caso in cui quadro pieno*/
    @Test
    @Order(1)
    void TC_CARRELLO_1()
    {
        String oracolo="1";
        carrello.aggiungiQuadro(quadro);
        String nelCarrello;
        nelCarrello=String.valueOf(carrello.getSize());
        assertEquals(nelCarrello,oracolo);
    }

    /*rimuovi quadro tramite id*/
    @Test
    @Order(2)
    void TC_CARRELLO_2()
    {
        int id= quadro.getId();
        carrello.rimuoviQuadrobyId(id);
        assertNull(carrello.doretrieveById(id));
    }

    //caso in cui il quadro è null
    @Test
    @Order(3)
    void TC_CARRELLO_3()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->carrello.aggiungiQuadro(null));
        assertEquals("il quadro è null",exception.getMessage());
    }

    //caso in cui il quadro è già nel carrello
    @Test
    @Order(4)
    void TC_CARRELLO_4()
    {
        carrello.aggiungiQuadro(quadro);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->carrello.aggiungiQuadro(quadro));
        assertEquals("il quadro è già nel carrello",exception.getMessage());
        carrello.svuotaCarrello();
    }

    //caso in cui svuoto il carrello
    @Test
    @Order(5)
    void TC_CARRELLO_5()
    {
        carrello.aggiungiQuadro(quadro);
        carrello.aggiungiQuadro(quadro2);
        carrello.aggiungiQuadro(quadro3);
        carrello.svuotaCarrello();
        int oracle =0;
        int nelCarrello=carrello.getSize();
        Double totaleCarrello = 0.0;
        Double totaleFinale =0.0;
        assertEquals(nelCarrello,oracle);
        assertEquals(totaleCarrello,totaleFinale);

    }

    //totale del carrello vuoto
    @Test
    @Order(6)
    void TC_CARRELLO_6()
    {
        Double totale = carrello.getTotale();
        Double oracle = 0.00;
        assertEquals(totale,oracle);
    }

    //totale del carrello con un solo quadro
    @Test
    @Order(7)
    void TC_CARRELLO_7()
    {
        Double costoquadro = quadro.getPrezzo();
        carrello.aggiungiQuadro(quadro);
        Double totaleCarrello = carrello.getTotale();
        assertEquals(costoquadro,totaleCarrello);
        carrello.svuotaCarrello();
    }

    //totale del carrello con più quadri
    @Test
    @Order(8)
    void TC_CARRELLO_8()
    {
        carrello.aggiungiQuadro(quadro);
        carrello.aggiungiQuadro(quadro2);
        carrello.aggiungiQuadro(quadro3);
        Double costoQuadri = quadro.getPrezzo()+quadro2.getPrezzo()+quadro3.getPrezzo();
        Double totaleCarrello = carrello.getTotale();
        assertEquals(costoQuadri,totaleCarrello);
        carrello.svuotaCarrello();
    }

    //rimozione quadro che non è nel carrello
    @Test
    @Order(9)
    void TC_CARRELLO_9()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->carrello.rimuoviQuadrobyId(quadro.getId()));
        assertEquals("il quadro non è presente nel carrello",exception.getMessage());

    }

    //svuto un carrello vuoto
    @Test
    @Order(10)
    void TC_CARRELLO_10()
    {
        int oracle =0;
        int nelCarrello=carrello.getSize();
        Double totaleCarrello = 0.0;
        Double totaleFinale =0.0;
        assertEquals(nelCarrello,oracle);
        assertEquals(totaleCarrello,totaleFinale);
    }




}