package Autenticazione.Controller;

import Autenticazione.NazioniGenerator;
import ManagementUtenti.Model.Utente;
import ManagementUtenti.Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration-servlet")
public class RegistrationServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String naz = request.getParameter("nazionalita");




        UtenteDAO service = new UtenteDAO();

        /*eliminiamo eventuali spazi all'inizio o alla fine
        * che possono dare fastidio*/
        username = username.trim();
        password = password.trim();
        email = email.trim();
        nome = nome.trim();
        cognome = cognome.trim();
        naz = naz.trim();

        /*USERNAME CHECK*/
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        boolean b=m.find();
            if(username.equals("") || username.length()<1 ||
               username.length()>15 || username.contains(" ") ||
                    b)
                throw new IllegalArgumentException("Campi non corretti");
        System.out.println("username checked");

        /*PASSWORD CHECK*/
        if(password.length()<8 || password.length()>15 || password.equals("") ||
           password.contains(" "))
            throw new IllegalArgumentException("Campi non corretti");
        System.out.println("password checked");

        /*EMAIL CHECK*/
        p= Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        m = p.matcher(email);
        b=m.find();
        if(email.length()<6 || email.length()>30 || email.equals("")||
           email.contains(" ") || !b)
            throw new IllegalArgumentException("Campi non corretti");
        System.out.println("email checked");

        /*CHECK NOME*/
        p= Pattern.compile("[^A-Za-z ]");
        m = p.matcher(nome);
        b=m.find();
        if(nome.length()<2 || nome.length()>30 || nome.equals("") || b)
            throw new IllegalArgumentException("Campi non corretti");
        System.out.println("nome checked");

        /*CHECK COGNOME*/
        p= Pattern.compile("[^A-Za-z ]");
        m = p.matcher(cognome);
        b=m.find();
        if(cognome.length()<2 || cognome.length()>30 || cognome.equals("") || b)
            throw new IllegalArgumentException("Campi non corretti");
        System.out.println("cognome checked");

        /*NAZIONALITA'*/
        /*la nazionalità la facciamo scegliere da un menù a tendina
        * quindi sarà inutile andare a controllare anche altri parametri*/

        //NazioniGenerator nazioniList = (NazioniGenerator) this.getServletContext().getAttribute("listaNazioni");
        //ArrayList<String> nazioni = nazioniList.getListaNazioni();
        NazioniGenerator nazioniList = new NazioniGenerator();
        ArrayList<String> nazioni = nazioniList.getListaNazioni();

        String checkNaz="";
        for(int i=0;i<nazioni.size();i++)
        {
            if(nazioni.get(i).equals(naz))
            {
                checkNaz = nazioni.get(i);
                break;
            }

        }

        if(checkNaz.equals(""))
            throw new IllegalArgumentException("Campi non corretti");
        System.out.println("nazionalità checked");


        /*se Ercole supera tutte le fatiche, arrivando fin qui, allora è degno di
        * essere iscritto.*/
        Utente utente = new Utente();
        utente.setPassword(password);
        utente.setUsername(username);
        utente.setEmail(email);
        utente.setCognome(cognome);
        utente.setNome(nome);
        utente.setNazionalità(naz);

        service.doSave(utente);

//        response.sendRedirect("index.html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("registrazioneSuccesso.jsp");
        requestDispatcher.forward(request, response);

    }


}
