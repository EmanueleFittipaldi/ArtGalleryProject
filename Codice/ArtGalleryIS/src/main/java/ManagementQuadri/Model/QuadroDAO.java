package ManagementQuadri.Model;

import Autenticazione.Model.ConPool;
import ManagementOrdini.Model.Ordine;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuadroDAO
{
    public Quadro doRetrieveById(int id)
    {
        if(id<0||id==0)
            throw new RuntimeException("ID NOT VALID");
        try
        {
            Connection con = ConPool.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM quadro WHERE idQuadro=?");
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if (res.next())
            {
                Quadro quadro = new Quadro();
                Ordine ordine = new Ordine();
                ordine.setIdOrdine(res.getInt(10));
                quadro.setId(res.getInt(1));
                quadro.setGenere(res.getString(2));
                quadro.setPrezzo(res.getDouble(3));
                quadro.setTitolo(res.getString(4));
                quadro.setAnno(res.getInt(5));
                quadro.setArtista(res.getString(6));
                quadro.setTecnica(res.getString(7));
                quadro.setDimensione(res.getString(8));
                quadro.setImmagine(res.getString(9));
                quadro.setOrdine(ordine);

                return quadro;
            }
            return null;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public JSONArray AjaxLoadAll()
    {
        try
        {
            Connection con = ConPool.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM quadro");

            ResultSet res = st.executeQuery();
            //Creating a JSONObject object
            //It will contain the table containing all
            //the paintings with the genre selected
            // JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (res.next())
            {
                JSONObject record = new JSONObject();
                //inserting key-value pairs into the json object
                record.put("ID", res.getInt(1));
                record.put("Genere", res.getString(2));
                record.put("Prezzo", res.getDouble(3));
                record.put("Titolo", res.getString(4));
                record.put("Anno", res.getInt(5));
                record.put("Artista", res.getString(6));
                record.put("Tecnica", res.getString(7));
                record.put("Dimensione", res.getString(8));
                record.put("Immagine", res.getString(9));
                record.put("Ordine",res.getInt(10));
                array.add(record);
            }
            //jsonObject.put("Quadri",array);
            //return jsonObject;
            return array;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public JSONArray AjaxGenereLoader(String genere)
    {
        try
        {
            Connection con = ConPool.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM quadro WHERE Genere=?");
            st.setString(1, genere);
            ResultSet res = st.executeQuery();
            //Creating a JSONObject object
            //It will contain the table containing all
            //the paintings with the genre selected
            // JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (res.next())
            {
                JSONObject record = new JSONObject();
                //inserting key-value pairs into the json object
                record.put("ID", res.getInt(1));
                record.put("Genere", res.getString(2));
                record.put("Prezzo", res.getDouble(3));
                record.put("Titolo", res.getString(4));
                record.put("Anno", res.getInt(5));
                record.put("Artista", res.getString(6));
                record.put("Tecnica", res.getString(7));
                record.put("Dimensione", res.getString(8));
                record.put("Immagine", res.getString(9));
                record.put("Ordine",res.getInt(10));
                array.add(record);
            }
            //jsonObject.put("Quadri",array);
            //return jsonObject;
            return array;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public List<Quadro> doRetrieveAll()
    {
        List<Quadro> quadri = new ArrayList<>();
        /*Mi connetto al database*/
        try (Connection con = ConPool.getConnection())
        {
            /*eseguo la query e salvo il risultato in ResultSet rs*/
            PreparedStatement ps = con.prepareStatement("SELECT * FROM quadro");
            ResultSet rs = ps.executeQuery();

        /*leggo le righe della tabella risutante creando nuovi Customer sulla base di questi campi
          e li devo inserire nella lista*/
            while (rs.next())
            {
                Quadro p = new Quadro();
                Ordine o = new Ordine();
                o.setIdOrdine(rs.getInt(10));
                p.setId(rs.getInt(1));
                p.setGenere(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setTitolo(rs.getString(4));
                p.setAnno(rs.getInt(5));
                p.setArtista(rs.getString(6));
                p.setTecnica(rs.getString(7));
                p.setDimensione(rs.getString(8));
                p.setImmagine(rs.getString(9));
                p.setOrdine(o);

                quadri.add(p);
            }
            return quadri;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean doSave(Quadro quadro)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO quadro (genere,prezzo,titolo,anno,artista,tecnica,dimensione,immagine) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, quadro.getGenere());
            ps.setDouble(2, quadro.getPrezzo());
            ps.setString(3, quadro.getTitolo());
            ps.setInt(4, quadro.getAnno());
            ps.setString(5, quadro.getArtista());
            ps.setString(6, quadro.getTecnica());
            ps.setString(7, quadro.getDimensione());
            ps.setString(8, quadro.getImmagine());



            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                quadro.setId(id);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return true;

    }

    public boolean doDeletebyIndex(int id)
    {
        if(id<0||id==0)
            throw new IllegalArgumentException("UPDATE error.");

        try (Connection con = ConPool.getConnection())
        {

            PreparedStatement ps = con.prepareStatement("DELETE FROM quadro WHERE IdQuadro=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("UPDATE error.");
            }
            return true;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean doUpdate(Quadro quadro)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE quadro SET Genere=?, Prezzo=?, Titolo=?, Anno=?, Artista=?, Tecnica=?,  Dimensione=?, Immagine=?  WHERE idQuadro=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, quadro.getGenere());
            ps.setDouble(2, quadro.getPrezzo());
            ps.setString(3, quadro.getTitolo());
            ps.setInt(4, quadro.getAnno());
            ps.setString(5, quadro.getArtista());
            ps.setString(6, quadro.getTecnica());
            ps.setString(7, quadro.getDimensione());
            ps.setString(8, quadro.getImmagine());
            ps.setInt(9, quadro.getId());

            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("UPDATE error.");
            }
            return true;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean acquista(Quadro quadro,Ordine ordine)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE quadro SET Genere=?, Prezzo=?, Titolo=?, Anno=?, Artista=?, Tecnica=?,  Dimensione=?, Immagine=?, IdOrdine=?  WHERE idQuadro=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, quadro.getGenere());
            ps.setDouble(2, quadro.getPrezzo());
            ps.setString(3, quadro.getTitolo());
            ps.setInt(4, quadro.getAnno());
            ps.setString(5, quadro.getArtista());
            ps.setString(6, quadro.getTecnica());
            ps.setString(7, quadro.getDimensione());
            ps.setString(8, quadro.getImmagine());
            ps.setInt(9, quadro.getOrdine().getIdOrdine());
            ps.setInt(10,quadro.getId());

            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("UPDATE error.");
            }
            return true;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
