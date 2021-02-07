<%@ page import="ManagementQuadri.Model.Quadro" %>
<%@ page import="ManagementUtenti.Model.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Autenticazione.NazioniGenerator" %>
<%@ page import="ManagementUtenti.Model.UtenteDAO" %><%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 26/06/2020
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/stylesheetModificaUtente.css">
    <title><%=((Utente)request.getAttribute("utente")).getUsername()%></title>
</head>

<body>
<%@include file="navbar.jsp" %></div>
<%
    NazioniGenerator nazioni = (NazioniGenerator) this.getServletConfig().getServletContext().getAttribute("listaNazioni");
    ArrayList<String> listaNazioni = nazioni.getListaNazioni();

    Utente utente=((Utente)request.getAttribute("utente"));
    System.out.println(utente.toString());

    String nome= utente.getNome();
    String cognome=utente.getCognome();
    String email=utente.getEmail();

%>

<div id="modifica">
    <form action="SaveModUtente"  method="get">
        <input type="hidden" id=username name="Username" value=<%=((Utente)request.getAttribute("utente")).getUsername()%>>
        <input type="text" id=nome name="Nome" value=<%=nome%>>
        <input type="text" id=cognome name="Cognome" value=<%=cognome%>>
        <input type="text" id=email name="Email" value=<%=email%>>
        <select class="slct" name="Nazionalita" id="nazionalita">
            <% for (String s : listaNazioni)
            {%>
            <option value=<%=s%>><%=s%>
            </option>
            <%}%>
        </select>
        <input type="submit" value="Aggiorna Utente">
    </form>
</div>


<%@include file="footer.jsp" %>
</body>
</html>
