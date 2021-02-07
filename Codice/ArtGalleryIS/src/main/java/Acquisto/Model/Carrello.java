package Acquisto.Model;

import ManagementQuadri.Model.Quadro;

import java.util.ArrayList;

public class Carrello
{

    private ArrayList<Quadro> nelCarrello = new ArrayList<>();
    private Double totale = 0.0;

    public ArrayList<Quadro> quadriNelCarrello()
    {
        return this.nelCarrello;
    }

    public void aggiungiQuadro(Quadro quadro)
    {

        /*Controllo se il quadro non è stato già
        * aggiunto al carrello*/
        boolean presente = false;
        for (int i = 0; i < nelCarrello.size(); i++)
        {
            if (nelCarrello.get(i).getId() == quadro.getId())
            {
                presente = true;
            }
        }



        /*Se quadro è diverso da null e non è presente, allora
        * lo aggiungo nel carrello*/
        if (quadro != null && presente == false)
        {
            this.nelCarrello.add(quadro);
            this.totale = quadro.getPrezzo() + totale;
        }
        if (quadro==null)
            throw new NullPointerException("il quadro è null");

        if (presente==true)
            throw new IllegalArgumentException("il quadro è già nel carrello");
    }

    public void svuotaCarrello()
    {
        nelCarrello.removeAll(nelCarrello);
        this.totale = 0.0;
    }

    public Quadro doretrieveById(int id)
    {
        Quadro quadro = null;
        for (int i = 0; i < nelCarrello.size(); i++)
        {
            if (nelCarrello.get(i).getId() == id)
            {
                quadro = nelCarrello.get(i);
            }
        }
        return quadro;
    }

    public Quadro doretrieveByIndex(int index)
    {
        return nelCarrello.get(index);
    }

    public int getSize()
    {
        return this.nelCarrello.size();
    }

    public void rimuoviQuadrobyId(int id) {
        Boolean trovato = false;

        for (int i = 0; i < nelCarrello.size(); i++) {
            Quadro temp = nelCarrello.get(i);
            if (temp.getId() == id) {
                this.totale = this.totale - temp.getPrezzo();
                trovato=true;
                nelCarrello.remove(i);
            }
        }

        if (trovato==false)
            throw new IllegalArgumentException("il quadro non è presente nel carrello");
    }

    public Double getTotale()
    {
        return this.totale;
    }
}
