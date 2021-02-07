package ManagementQuadri.Model;

import ManagementOrdini.Model.Ordine;

import java.sql.Date;

public class Quadro
{
    private int id;
    private String genere, titolo, artista, tecnica, dimensione, immagine;
    private int anno;
    private double prezzo;
    private Date dataAcquisto;
    private Ordine ordine;

    /*GETTERS*/


    public int getId()
    {
        return id;
    }

    public String getGenere()
    {
        return genere;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public int getAnno()
    {
        return anno;
    }

    public String getArtista()
    {
        return artista;
    }

    public String getTecnica()
    {
        return tecnica;
    }

    public String getDimensione()
    {
        return dimensione;
    }

    public String getImmagine()
    {
        return immagine;
    }

    public double getPrezzo()
    {
        return prezzo;
    }


    /*SETTERS*/
    public void setId(int id)
    {
        this.id = id;
    }

    public void setGenere(String genere)
    {
        this.genere = genere;
    }

    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }

    public void setArtista(String artista)
    {
        this.artista = artista;
    }

    public void setTecnica(String tecnica)
    {
        this.tecnica = tecnica;
    }

    public void setDimensione(String dimensione)
    {
        this.dimensione = dimensione;
    }

    public void setImmagine(String immagine)
    {
        this.immagine = immagine;
    }

    public void setAnno(int anno)
    {
        this.anno = anno;
    }

    public void setPrezzo(double prezzo)
    {
        this.prezzo = prezzo;
    }

    public Ordine getOrdine() { return ordine; }

    public void setOrdine(Ordine ordine) { this.ordine = ordine;}

    public Date getDataAcquisto()
    {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto)
    {
        this.dataAcquisto = dataAcquisto;
    }
}
