package Autenticazione.Model;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/*classe che tramite il metodo getConnection, stabilisce una connessione col database*/
public class ConPool
{
    private static DataSource datasource;

    public static Connection getConnection() throws SQLException
    {
        if (datasource == null)
        {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/artgalleryis?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("19641994");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
