package Autenticazione.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class AdminDAOTest
{
    /**************************DOSAVE*******************/
    /*caso tutto ok*/
    @Test
    @Order(1)
     void TC_ADMIN_1()
    {
        AdminDAO adminDAO= new AdminDAO();
        Admin admin = new Admin();
        admin.setPassword("matrix");
        admin.setUsername("test");
        assertTrue(adminDAO.doSave(admin));
    }

    /*caso admin vuoto*/
    @Test
    @Order(8)
    void TC_ADMIN_8()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        admin.setUsername("abra");
        admin.setPassword("");
        assertThrows(RuntimeException.class,()->adminDAO.doSave(admin));
    }
    /*caso admin vuoto*/
    @Test
    @Order(9)
     void TC_ADMIN_9()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        admin.setUsername("");
        admin.setPassword("cadabra");
        assertThrows(RuntimeException.class,()->adminDAO.doSave(admin));
    }
    /*caso admin vuoto*/
    @Test
    @Order(2)
     void TC_ADMIN_2()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        admin.setUsername("");
        admin.setPassword("");
        assertThrows(RuntimeException.class,()->adminDAO.doSave(admin));
    }


    /*******************************************************/


    /************doRetrieveAll*******************************/
    @Test
    @Order(3)
     void TC_ADMIN_3()
    {
        AdminDAO adminDAO = new AdminDAO();
        assertNotNull(adminDAO.doRetrieveAll());

    }
    /************************************************************/


    /*************************doRetrieveByUsernamePassword****************/

    /*caso tutt ok*/
    @Test
    @Order(4)
     void TC_ADMIN_4()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        assertNotNull(adminDAO.doRetrieveByUsernamePassword("test","matrix"));

    }

    /*caso stringhe vuote*/
    @Test
    @Order(5)
    void TC_ADMIN_5()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        assertThrows(RuntimeException.class,()->adminDAO.doRetrieveByUsernamePassword("",""));

    }

    /*caso null*/
    @Test
    @Order(6)
     void TC_ADMIN_6()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        assertThrows(RuntimeException.class,()->adminDAO.doRetrieveByUsernamePassword(null,null));

    }

    /*caso giusto ma semplicemente non c'è*/
    @Test
    @Order(7)
     void TC_ADMIN_7()
    {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin= new Admin();
        assertNull(adminDAO.doRetrieveByUsernamePassword("nonCiSono","testing"));
    }
    /*********************************************************************/


}