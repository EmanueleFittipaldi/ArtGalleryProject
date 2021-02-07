package ManagementOrdini.Controller;


import Acquisto.Model.Carrello;
import ManagementOrdini.Model.Ordine;
import ManagementOrdini.Model.OrdineDAO;
import ManagementQuadri.Model.Quadro;
import ManagementQuadri.Model.QuadroDAO;
import ManagementUtenti.Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/saveOrder")
public class inserisciOrdineServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        /*uniche carte ammesse*/
        List<String> listaCarte = new ArrayList<>();
        listaCarte.add("Poste Pay");
        listaCarte.add("Mastercard");
        listaCarte.add("Visa");
        listaCarte.add("American Express");
        listaCarte.add("Diners");
        listaCarte.add("JCB");
        listaCarte.add("Hype");
        listaCarte.add("Discover Card");
        listaCarte.add("China UnionPay");
        listaCarte.add("YAP");

        /*reperisco la carta usata. Il numero non ce lo salviamo per privacy,
         *ma al momento del pagamento lo facciamo immettere lo stesso giusto per
         *completezza*/
        String tipoCarta = req.getParameter("tipoCarta");

        if(tipoCarta==null)
            throw new NullPointerException("La carta non può essere null");

        /*Controllo che è la carta è effettivamente valida*/
        String checkCarta = "";
        for (int i = 0; i < listaCarte.size(); i++)
        {
            if (listaCarte.get(i).equals(tipoCarta))
                checkCarta = listaCarte.get(i);
        }
        if (checkCarta.equals(""))
            throw new IllegalArgumentException("Carta non valida");


        //----INSERIMENTO ORDINE (Superati i controlli si può continuare con l'ordine)
        Ordine ordine = new Ordine(); //costruisco un nuovo ordine
        OrdineDAO ordineDAO = new OrdineDAO();  //var di servizio per inserire l'ordine
        Utente utente = (Utente) req.getSession().getAttribute("Utente"); // reperisco l'utente dalla sessione
        Carrello carrello = (Carrello) req.getSession().getAttribute("Carrello"); // reperisco il carrello dalla sessione per poter prendere i quadri

        if(carrello.getSize()==0)
            throw new IllegalArgumentException("Non ci sono quadri");
        if(utente==null || carrello==null)
            throw new NullPointerException();

        /*devo settare tutti i parametri dell'ordine
         * e salvo l'ordine*/
        ordine.setUtente(utente);
        ordine.setDataAcquisto(Date.valueOf(java.time.LocalDate.now()));
        ordine.setTipoCarta(checkCarta);


        /*devo prendere la chiave di quesot ordine inserito*/
        int chiave = ordineDAO.doSave(ordine);
        Ordine orderFittizio = new Ordine();//mi serve soltanto per essere in grado di settare l'idOrdine all'interno del quadro
        orderFittizio.setIdOrdine(chiave);

        /*adesso devo associare per ogni quadro presente nel carrello
         * l'id dell'ordine */
        List<Quadro> listaQuadri = carrello.quadriNelCarrello();
        for (int i = 0; i < listaQuadri.size(); i++)
        {
            listaQuadri.get(i).setOrdine(orderFittizio);
        }

        QuadroDAO quadroDAO = new QuadroDAO();
        /*devo eseguire per ognuno di questi quadri l'update in modo che le modifiche
         * saranno riflesse anche nel database*/
        for (int i = 0; i < listaQuadri.size(); i++)
        {
            quadroDAO.acquista(listaQuadri.get(i),orderFittizio);
        }

        //---SVUOTO IL CARRELLO una volta finito tutto
        carrello.svuotaCarrello();


        //--RITORNO A PAGINA INIZIALE
//        String address = "/index.html";
//        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
//        dispatcher.forward(req, resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("acquistoSuccesso.jsp");
        requestDispatcher.forward(req, resp);
    }
}
