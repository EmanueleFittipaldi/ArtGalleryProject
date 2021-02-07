package ManagementOrdini.Model;
import ManagementQuadri.Model.Quadro;
import ManagementUtenti.Model.Utente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdineDAOTest
{
    /*caso in cui va tutto bene*/
    @Test
    @Order(1)
     void TC_ORDINE_1()
    {
        Date data = new Date(2020,1,1);
        Utente utente = new Utente();
        utente.setUsername("usr1");

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        ordine.setDataAcquisto(data);
        ordine.setTipoCarta("Mastercard");
        ordine.setUtente(utente);

        assertNotNull(ordineDAO.doSave(ordine));

    }

    /*caso in cui passo un ordine vuoto*/
    @Test
    @Order(2)
     void TC_ORDINE_2()
    {

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        assertThrows(RuntimeException.class, ()->ordineDAO.doSave(ordine));
    }

    /*caso in cui l'ordine non è completamente vuoto, ma c'è qualcosa di vuoto*/
    /*caso in cui passo un ordine vuoto*/
    @Test
    @Order(3)
    void TC_ORDINE_3()
    {

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        ordine.setUtente(null);
        assertThrows(RuntimeException.class, ()->ordineDAO.doSave(ordine));
    }



    @Test
    @Order(4)
    void TC_ORDINE_4()
    {
        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        ordine.setUtente(new Utente());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->ordineDAO.doSave(ordine));
        assertEquals(exception.getMessage(),"Ordine o Utente non valido");
    }

    /*caso in cui l'ordine non è completamente vuoto, ma c'è qualcosa di vuoto*/
    /*caso in cui passo un ordine vuoto*/
    @Test
    @Order(5)
    void TC_ORDINE_5()
    {

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        Utente utente = new Utente();
        utente.setUsername("Giancarlo66");
        ordine.setUtente(utente);
        assertThrows(RuntimeException.class, ()->ordineDAO.doSave(ordine));
    }

/*****************************RETRIEVE BY USERNAME TESTING**/

/*caso in cui ci sono quadri associati a quell'ordine*/
@Test
@Order(6)
   void TC_ORDINE_6()
{
    String username ="usr1";
    OrdineDAO ordineDAO = new OrdineDAO();

    assertNotNull(ordineDAO.retrieveByUsername(username));
}

/*caso in cui non ci sono quadri associati a quell'oridine, perché
* magari lo username passato non corrisponde ad un utente realmente presente
* nel sistema*/
@Test
@Order(7)
void TC_ORDINE_7()
{
    String username ="usr1asdasdasdasd";
    OrdineDAO ordineDAO = new OrdineDAO();
    ArrayList<Quadro> emptyList = new ArrayList<>();
    assertEquals(emptyList,ordineDAO.retrieveByUsername(username));
}



}