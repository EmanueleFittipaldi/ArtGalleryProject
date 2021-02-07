package ManagementUtenti.Controller;


import Autenticazione.NazioniGenerator;
import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/SaveModUtente")
public class saveModUtente extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //Verificare i parametri se sono tutti
        String username = req.getParameter("Username");
        String nazionalita = req.getParameter("Nazionalita");
        String nome = req.getParameter("Nome");
        String cognome = req.getParameter("Cognome");
        String email = req.getParameter("Email");





        //Controllo Username
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        boolean b=m.find();
        if(username.equals("") || username.length()<1 || username.length()>15 || username.contains(" ") || b)
            throw new IllegalArgumentException("Campi non corretti");

        //Controllo Email
        p= Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        m = p.matcher(email); b=m.find();
        if(email.length()<6 || email.length()>30 || email.equals("")|| email.contains(" ") || !b)
            throw new IllegalArgumentException("Campi non corretti");

        //Controllo nome
        p= Pattern.compile("[^A-Za-z ]");
        m = p.matcher(nome);
        b=m.find();
        if(nome.length()<2 || nome.length()>30 || nome.equals("") || b)
            throw new IllegalArgumentException("Campi non corretti");

        //Controllo cognome
        p= Pattern.compile("[^A-Za-z ]"); m = p.matcher(cognome);
        b=m.find();
        if(cognome.length()<2 || cognome.length()>30 || cognome.equals("")|| b)
            throw new IllegalArgumentException("Campi non corretti");

        // Controllo nazionalità
        NazioniGenerator nazioniList = new NazioniGenerator();
        ArrayList<String> nazioni = nazioniList.getListaNazioni();

        String checkNaz="";
        for(int i=0;i<nazioni.size();i++)
        {
            if (nazioni.get(i).equals(nazionalita))
            {
                checkNaz = nazioni.get(i);
                break;
            }
        }

        if(checkNaz.equals(""))
            throw new IllegalArgumentException("Campi non corretti");



        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByUsername(username);

        //Utente utente = new Utente();
        utente.setEmail(email);
       // utente.setUsername(username);
        utente.setCognome(cognome);
        utente.setNazionalità(nazionalita);
        utente.setNome(nome);
        //utente.setPassword(password);

       // UtenteDAO utenteDAO = new UtenteDAO();

        System.out.println("utente che passa al DAO\n "+utente.toString());
        utenteDAO.doUpdate(utente);

        RequestDispatcher dispatcher = req.getRequestDispatcher("admin.jsp");
        dispatcher.forward(req, resp);
    }
}
