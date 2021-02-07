package Autenticazione.Model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin
{
    private String username, password;

    public Admin()
    {
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {// password è inserita dall’admin
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password= String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

}
