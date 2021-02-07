package ManagementOrdini.Model;

import Autenticazione.Model.ConPool;
import ManagementQuadri.Model.Quadro;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDAO
{

    /*Metodo che mi ritorna tutti gli ordini effettuati*/
    public int doSave(Ordine ordine)
    {
        if(ordine.getIdOrdine()<0 || ordine.getDataAcquisto()==null || ordine.getUtente()==null
                || ordine.getTipoCarta()=="" || ordine.getUtente().getUsername()==null)
            throw new RuntimeException("Ordine o Utente non valido");

        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (TipoCarta,DataAcquisto,IdUtente) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ordine.getTipoCarta());
            ps.setDate(2, ordine.getDataAcquisto());
            ps.setString(3, ordine.getUtente().getUsername());

            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            ordine.setIdOrdine(id);
            return id;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Quadro> retrieveByUsername(String username)
    {
        ArrayList<Quadro> listaQuadri = new ArrayList<>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ordine o, quadro q WHERE IdUtente=? AND q.IdOrdine=o.IdOrdine");
            ps.setString(1, username);
            ResultSet res = ps.executeQuery();
            while (res.next())
            {
                Quadro quadro = new Quadro();
               quadro.setId(res.getInt(5));
               quadro.setGenere(res.getString(6));
               quadro.setPrezzo(res.getDouble(7));
               quadro.setTitolo(res.getString(8));
               quadro.setAnno(Integer.parseInt(res.getString(9)));
               quadro.setArtista(res.getString(10));
               quadro.setTecnica(res.getString(11));
               quadro.setDimensione(res.getString(12));
               if(res.getString(13) !=null)
                    quadro.setImmagine(res.getString(13));
               else
                   quadro.setImmagine("");

               Ordine ordine = new Ordine();
               ordine.setIdOrdine(res.getInt(1));
               quadro.setOrdine(ordine);

               listaQuadri.add(quadro);
            }
            return listaQuadri;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    }

