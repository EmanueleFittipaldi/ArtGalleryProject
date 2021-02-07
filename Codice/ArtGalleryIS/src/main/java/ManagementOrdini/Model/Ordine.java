package ManagementOrdini.Model;

import ManagementUtenti.Model.Utente;

import java.sql.Date;

public class Ordine
{
   private int idOrdine;
   private String tipoCarta;
   private Date dataAcquisto;
   private Utente utente;

    public int getIdOrdine()
    {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine)
    {
        this.idOrdine = idOrdine;
    }

    public String getTipoCarta()
    {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta)
    {
        this.tipoCarta = tipoCarta;
    }

    public Date getDataAcquisto()
    {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto)
    {
        this.dataAcquisto = dataAcquisto;
    }

    public Utente getUtente()
    {
        return utente;
    }

    public void setUtente(Utente utente)
    {
        this.utente = utente;
    }
}
