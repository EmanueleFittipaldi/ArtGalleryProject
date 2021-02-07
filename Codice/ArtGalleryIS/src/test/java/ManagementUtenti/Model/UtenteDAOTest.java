package ManagementUtenti.Model;
import org.apache.commons.mail.Email;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UtenteDAOTest
{

    /********************************DOSAVE() TESTING*****************************************************/
    /*da questo test mi aspetto che l'utente venga inserito correttamente*/
    @Test
    @Order(1)
    void TC_UTENTE_DAO_1()
    {

        /*creo uno username valido*/
        Utente utente = new Utente();
        utente.setUsername("utenteTest1");
        utente.setPassword("password");
        utente.setNome("Test");
        utente.setCognome("Test");
        utente.setNazionalità("italiana");
        utente.setEmail("Test@Test.it");
        UtenteDAO utenteDAO = new UtenteDAO();

        assertTrue(utenteDAO.doSave(utente));
    }


    /*caso in cui passo null come utente deve lanciare una eccezione*/
    @Test
    @Order(2)
    void TC_UTENTE_DAO_2()
    {
        /*creo uno username valido*/
        UtenteDAO utenteDAO = new UtenteDAO();

         assertThrows(NullPointerException.class, ()->utenteDAO.doSave(null));
    }

    /*caso in cui passo un utente non inizializzato deve lanciare una eccezione,*/
    @Test
    @Order(3)
    void TC_UTENTE_DAO_3()
    {
        /*creo uno username valido*/
        Utente utente=new Utente();
        UtenteDAO utenteDAO = new UtenteDAO();

       assertThrows(NullPointerException.class, ()->utenteDAO.doSave(utente));

    }

/****************************************************UPDATE TESTING******************************************************/

@Test
@Order(4)
void TC_UTENTE_DAO_8()
{

    /*creo uno username valido*/
    Utente utente = new Utente();
    utente.setUsername("utenteTest1");
    utente.setPassword("password");
    utente.setNome("Alibabà");
    utente.setCognome("Test");
    utente.setNazionalità("italiana");
    utente.setEmail("Test@Test.it");
    UtenteDAO utenteDAO = new UtenteDAO();

    assertTrue(utenteDAO.doUpdate(utente));
}


    /*caso in cui passo null come utente deve lanciare una eccezione*/
    @Test
    @Order(5)
    void TC_UTENTE_DAO_9()
    {
        /*creo uno username valido*/
        UtenteDAO utenteDAO = new UtenteDAO();

        assertThrows(NullPointerException.class, ()->utenteDAO.doUpdate(null));
    }

    /*caso in cui passo un utente non inizializzato deve lanciare una eccezione,*/
    @Test
    @Order(6)
    void TC_UTENTE_DAO_10()
    {
        /*creo uno username valido*/
        Utente utente=new Utente();
        UtenteDAO utenteDAO = new UtenteDAO();

        assertThrows(RuntimeException.class, ()->utenteDAO.doUpdate(utente));

    }
/****************************doRetrieveByUsernamePassword************************************************************/

/*caso in cui username e password sono vuoti*/
@Test
@Order(7)
    void TC_UTENTE_DAO_11()
{
    String username="";
    String password="";
    UtenteDAO utenteDAO= new UtenteDAO();
    RuntimeException exception = assertThrows(RuntimeException.class, (Executable) utenteDAO.doRetrieveByUsernamePassword(username,password));
}

    @Test
    @Order(8)
    void TC_UTENTE_DAO_12()
    {
        String username="";
        String password="password";
        UtenteDAO utenteDAO= new UtenteDAO();
        RuntimeException exception = assertThrows(RuntimeException.class, (Executable) utenteDAO.doRetrieveByUsernamePassword(username,password));
    }

    @Test
    @Order(9)
    void TC_UTENTE_DAO_13()
    {
        String username="utenteTest1";
        String password="";
        UtenteDAO utenteDAO= new UtenteDAO();
        RuntimeException exception = assertThrows(RuntimeException.class, (Executable) utenteDAO.doRetrieveByUsernamePassword(username,password));
    }

    /*caso tutto ok*/
    @Test
    @Order(10)
    void TC_UTENTE_DAO_14()
    {
        String username="utenteTest1";
        String password="password";
        UtenteDAO utenteDAO= new UtenteDAO();
        assertNotNull(utenteDAO.doRetrieveByUsernamePassword(username,password));
    }

    /*caso tutto ok*/
    @Test
    @Order(11)
    void TC_UTENTE_DAO_15()
    {
        String username="nonEsiste";
        String password="nonEsiste";
        UtenteDAO utenteDAO= new UtenteDAO();
        assertNull(utenteDAO.doRetrieveByUsernamePassword(username,password));
    }

/************************************************doRetrieveByUsername******************************************************************/
/*caso in cui username vuota*/
@Test
@Order(12)
void TC_UTENTE_DAO_16()
{
    String username="";
    UtenteDAO utenteDAO= new UtenteDAO();
    RuntimeException exception = assertThrows(RuntimeException.class, (Executable) utenteDAO.doRetrieveByUsername(username));
}


    /*caso tutto ok*/
    @Test
    @Order(13)
    void TC_UTENTE_DAO_17()
    {
        String username="utenteTest1";
        UtenteDAO utenteDAO= new UtenteDAO();
        assertNotNull(utenteDAO.doRetrieveByUsername(username));
    }

    /*caso tutto ok*/
    @Test
    @Order(14)
    void TC_UTENTE_DAO_18()
    {
        String username="nonEsiste";
        UtenteDAO utenteDAO= new UtenteDAO();
        assertNull(utenteDAO.doRetrieveByUsername(username));
    }

/***********************************************AjaxLoadAll***********************************************************************/
    /*metodo che va tutto ok, perchè abbiamo già gli utentei nel DB*/
    @Test
    @Order(15)
    void TC_UTENTE_DAO_19()
    {
        UtenteDAO utenteDAO = new UtenteDAO();
        assertNotNull(utenteDAO.AjaxLoadAll());
    }

    /*Nel caso di db senza utenti questo test case sarà verificato*/
    @Disabled
    @Test
    void TC_UTENTE_DAO_20()
    {
        UtenteDAO utenteDAO = new UtenteDAO();
        assertNull(utenteDAO.AjaxLoadAll());
    }

    /*********************************REMOVE BY ID TESTING*************************************************************/

    /*caso in cui la username per la quale vogliamo eliminare l'utente è vuota*/
    @Test
    @Order(16)
    void TC_UTENTE_DAO_4()
    {
        String username="";
        UtenteDAO utenteDAO= new UtenteDAO();
        RuntimeException exception = assertThrows(RuntimeException.class,()->utenteDAO.delete(username));
        assertEquals("UPDATE error.",exception.getMessage());

    }

    /*caso in cui non passo niente*/
    @Test
    @Order(17)
    void TC_UTENTE_DAO_5()
    {
        UtenteDAO utenteDAO= new UtenteDAO();
        assertThrows(RuntimeException.class,()->utenteDAO.delete(null));
    }

    /*caso in cui va tutto ok*/
    @Test
    @Order(18)
    void TC_UTENTE_DAO_6()
    {
        UtenteDAO utenteDAO= new UtenteDAO();
        String username="utenteTest1";
        assertTrue(utenteDAO.delete(username));
    }


    /*caso in cui passo una username che non c'è*/
    @Test
    @Order(19)
    void TC_UTENTE_DAO_7()
    {
        UtenteDAO utenteDAO= new UtenteDAO();
        String username="NonEsistoAhAh";
        RuntimeException exception = assertThrows(RuntimeException.class,()->utenteDAO.delete(username));
        assertEquals("UPDATE error.",exception.getMessage());
    }

}

