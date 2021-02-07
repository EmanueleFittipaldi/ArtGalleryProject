package Autenticazione.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO
{
    public boolean doSave(Admin admin)
    {

        if(admin.getPassword().equals("da39a3ee5e6b4b0d3255bfef95601890afd80709")||admin.getUsername().equals(""))
            throw new RuntimeException();

        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO admin(AdminUsername, Psswrd) " +
                            "VALUES(?,?)");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword().trim());

            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return true;
    }

    public ArrayList<Admin> doRetrieveAll()
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM admin");
            ResultSet rs = ps.executeQuery();
            //Array da ritornare
            ArrayList<Admin> admins = new ArrayList<>();
            while (rs.next())
            {
                Admin admin = new Admin();
                admin.setPassword(rs.getString(1));
                admin.setUsername(rs.getString(2));
                admins.add(admin);

            }

            return admins;

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Admin doRetrieveByUsernamePassword(String username, String password)
    {
        if(username.equals("")||password.equals("da39a3ee5e6b4b0d3255bfef95601890afd80709")||username==null||password==null)
            throw new RuntimeException();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM admin WHERE AdminUsername=? and Psswrd=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Admin admin = new Admin();
                admin.setUsername(rs.getString(1));
                admin.setPassword(rs.getString(2));
                return admin;
            }
            return null;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
