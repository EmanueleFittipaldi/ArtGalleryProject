package ManagementUtenti.Model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utente
{
        private String username;
        private String password;
        private String nazionalità;
        private String nome;
        private String cognome;
        private String email;

        public String getUsername()
        {
            return username;
        }

        public String getPassword()
        {
            return password;
        }

        public String getEmail()
        {
            return email;
        }

        public String getNazionalità()
        {
            return nazionalità;
        }

        public String getNome()
        {
            return nome;
        }

        public String getCognome()
        {
            return cognome;
        }


        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public void setPasswordNOTSHAI(String password)
        {
            this.password=password;
        }

        public void setPassword(String password)
        {// password è inserita dall’utente
            try
            {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                digest.update(password.getBytes(StandardCharsets.UTF_8));
                this.password = String.format("%040x", new BigInteger(1, digest.digest()));
            } catch (NoSuchAlgorithmException e)
            {
                throw new RuntimeException(e);
            }
        }

        public void setNazionalità(String nazionalità)
        {
            this.nazionalità = nazionalità;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public void setCognome(String cognome)
        {
            this.cognome = cognome;
        }

    @Override
    public String toString()
    {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nazionalità='" + nazionalità + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
