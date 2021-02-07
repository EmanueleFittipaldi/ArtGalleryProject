<%@ page import="Autenticazione.NazioniGenerator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/stylesheetLoginFed.css">
    <!--caricamento icona da visualizzare nelle tab aperte -->
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <title>Account</title>
</head>

<body>
<%@include file="navbar.jsp" %></div>

<%
    NazioniGenerator nazioni = (NazioniGenerator) this.getServletConfig().getServletContext().getAttribute("listaNazioni");
ArrayList<String> listaNazioni = nazioni.getListaNazioni();%>

<div class="registrationContainer">
    <form action="registration-servlet" method="post" >
        <h2>Registration</h2>
        <input type="text" id="usernameRegistration" class="val" name="username" placeholder="Username"  >

        <input type="password" id="passwordRegistration" class="val" name="password" placeholder="Password">
        <input type="email" id="email" class="val" name="email" placeholder="Email">
        <input type="text" id="nome" class="val" name="nome" placeholder="Nome">
        <input type="text" id="cognome" class="val" name="cognome" placeholder="Cognome">



        <select name="nazionalita" id="naz">

                <% for (String s : listaNazioni)
                {%>
            <option value=<%=s%>><%=s%>
            </option>
            <%}%>
        </select>




        <input type="submit" class="val" value="Register">
    </form>
</div>

<%@include file="footer.jsp" %>

</body>
</html>

