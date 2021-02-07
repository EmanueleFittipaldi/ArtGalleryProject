package ManagementQuadri.Model;

import ManagementOrdini.Model.Ordine;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class QuadroDAOTest
{
    /*test per id negativo*/
    @Test
    @Order(1)
     void TC_Q_1()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        int id=-1;
        RuntimeException exception=assertThrows(RuntimeException.class, ()->quadroDAO.doRetrieveById(id));
        assertEquals(exception.getMessage(),"ID NOT VALID");

    }

    /*test per id 0*/
    @Test
    @Order(2)
     void TC_Q_2()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        int id=0;
      assertThrows(RuntimeException.class, ()->quadroDAO.doRetrieveById(id));
    }

    /*per id non presente*/
    @Test
    @Order(3)
     void TC_Q_3()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        int id=1000;
        assertNull(quadroDAO.doRetrieveById(id));
    }

    /************************************************DOSAVE TEST***********************************************/
    /*caso in cui è tutto ok*/
    @Test
    @Order(4)
     void TC_Q_4()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        Quadro quadro= new Quadro();
        Ordine ordine = new Ordine();
        ordine.setIdOrdine(4);

        quadro.setOrdine(ordine);
        quadro.setAnno(2020);
        quadro.setArtista("Emanuele Fittipaldi");
        quadro.setDimensione("20*20");
        quadro.setGenere("Pop");
        quadro.setImmagine("C:/emanu/images");
        quadro.setPrezzo(1099.36);
        quadro.setTecnica("olio su tela");
        quadro.setTitolo("Mi illumino di immenso");

        assertTrue(quadroDAO.doSave(quadro));

    }
    /*caso in cui il quadro non è stato inizializzato*/
    @Test
    @Order(5)
     void TC_Q_5()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        Quadro quadro= new Quadro();

        RuntimeException exception = assertThrows(RuntimeException.class,()->quadroDAO.doSave(quadro));

    }

    /*caso ritorna null*/
    @Test
    @Disabled
     void TC_Q_6()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        assertNull(quadroDAO.AjaxLoadAll());
    }
    /*caso ritorna pieno*/
    @Test
    @Order(6)
     void TC_Q_7()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        assertNotNull(quadroDAO.AjaxLoadAll());
    }

    /******************************AJAXLOADGENRE**********************************************/

    /*caso ok*/
    @Test
    @Order(7)
     void TC_Q_8()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        String genere="Ritratto";
        assertNotNull(quadroDAO.AjaxGenereLoader(genere));

    }

    /*caso null*/
    @Test
    @Order(8)
     void TC_Q_9()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        String genere="bho";
        assertTrue(quadroDAO.AjaxGenereLoader(genere).isEmpty());

    }

    /****************************************************************************************/
    /*caso ok*/
    @Test
    @Order(9)
     void TC_Q_10()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        assertNotNull(quadroDAO.doRetrieveAll());

    }

    /*caso in cui non ci sono quadri*/
    @Test
    @Disabled
    void TC_Q_11()
    {
        QuadroDAO quadroDAO= new QuadroDAO();
        assertNotNull(quadroDAO.doRetrieveAll());

    }
    /****************************************************************************************/

    @Test
    @Order(10)
     void TC_Q_12()
    {
        String oracolo="UPDATE error.";
        QuadroDAO quadroDAO = new QuadroDAO();
        int id=-1;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->quadroDAO.doDeletebyIndex(id));
        assertEquals(oracolo,exception.getMessage());
    }

    @Test
    @Order(11)
    void TC_Q_13()
    {
        String oracolo="UPDATE error.";
        QuadroDAO quadroDAO = new QuadroDAO();
        int id=0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->quadroDAO.doDeletebyIndex(id));
        assertEquals(oracolo,exception.getMessage());
    }

    /*caso tutt ok*/
    @Test
    @Disabled
     void TC_Q_14()
    {
        QuadroDAO quadroDAO = new QuadroDAO();
        int id=9;
        assertTrue(quadroDAO.doDeletebyIndex(id));
    }

    /*caso update ok*/
    @Test
    @Order(12)
     void TC_Q_15()
    {
        QuadroDAO quadroDAO = new QuadroDAO();
        Quadro quadro= new Quadro();

        ArrayList<Quadro> quadri=(ArrayList<Quadro>)quadroDAO.doRetrieveAll();
        int id= (quadri.get(quadri.size()-1)).getId();
        quadro.setId(id);
        quadro.setAnno(2020);
        quadro.setArtista("Emanuele Fittipaldi");
        quadro.setDimensione("20*20");
        quadro.setGenere("Pop");
        quadro.setImmagine("C:/emanu/images");
        quadro.setPrezzo(1099.36);
        quadro.setTecnica("olio su tela");
        quadro.setTitolo("casa dolce casa");
        assertTrue(quadroDAO.doUpdate(quadro));

    }

    /*caso in cui quadro non è stato inizializzato*/
    @Test
    @Order(13)
     void TC_Q_16()
    {
        String oracolo="UPDATE error.";
        QuadroDAO quadroDAO = new QuadroDAO();
        Quadro quadro = new Quadro();
        RuntimeException exception = assertThrows(RuntimeException.class, ()->quadroDAO.doUpdate(quadro));
        assertEquals(oracolo,exception.getMessage());
    }

    @Test
    @Order(14)
    void CLEANUP(){
    QuadroDAO quadroDAO=new QuadroDAO();
    ArrayList<Quadro> quadri=(ArrayList<Quadro>)quadroDAO.doRetrieveAll();
    int id= (quadri.get(quadri.size()-1)).getId();
    quadroDAO.doDeletebyIndex(id);
    }



}