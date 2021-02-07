package ManagementUtenti.Model;

import Autenticazione.Model.ConPool;
import org.json.JSONArray;
import org.json.JSONObject;


import java.sql.*;

public class UtenteDAO
{
    public boolean doSave(Utente utente)
    {
        try (Connection con = ConPool.getConnection())
        {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (Username, Psswrd,Nome,Cognome,Nazionalità,Email) " +
                            "VALUES(?,?,?,?,?,?)");
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword().trim());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getNazionalità());
            ps.setString(6, utente.getEmail());

            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
            else
            {
                return true;
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean delete(String username)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("DELETE  FROM utente WHERE Username=?");
            ps.setString(1, username);

            if ( ps.executeUpdate() != 1)
            {
                throw new RuntimeException("UPDATE error.");
            }
            else
                return true;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean doUpdate(Utente utente)
    {
        try (Connection con = ConPool.getConnection())
        {


            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente SET  Psswrd=?, Nome=?, Cognome=?,Nazionalità=?, Email=? WHERE Username=?",
                    Statement.RETURN_GENERATED_KEYS);


            ps.setString(1, utente.getPassword());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getNazionalità());
            ps.setString(5, utente.getEmail());
            ps.setString(6,utente.getUsername().trim());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("UPDATE error.");
            }
            else return true;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public Utente doRetrieveByUsernamePassword(String username, String password)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM utente WHERE Username=? and Psswrd=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente p = new Utente();

                p.setUsername(rs.getString(1));
                p.setPassword(rs.getString(2));
                p.setNazionalità(rs.getString(5));
                p.setNome(rs.getString(3));
                p.setCognome(rs.getString(4));
                p.setEmail(rs.getString(6));
                return p;
            }
            return null;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


    }

    public Utente doRetrieveByUsername(String username)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM utente WHERE Username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente p = new Utente();

                p.setUsername(rs.getString(1));
                p.setPasswordNOTSHAI(rs.getString(2));
                p.setNazionalità(rs.getString(5));
                p.setNome(rs.getString(3));
                p.setCognome(rs.getString(4));
                p.setEmail(rs.getString(6));
                return p;
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
            PreparedStatement st = con.prepareStatement("SELECT * FROM utente");
            ResultSet res = st.executeQuery();
            JSONArray array = new JSONArray();
            while (res.next())
            {
                JSONObject record = new JSONObject();
                record.put("Username", res.getString(1));
                record.put("Psswrd", res.getString(2));
                record.put("Nazionalità", res.getString(3));
                record.put("Nome", res.getString(4));
                record.put("Cognome", res.getString(5));
                record.put("Email", res.getString(6));
                array.put(record);
            }
            return array;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
