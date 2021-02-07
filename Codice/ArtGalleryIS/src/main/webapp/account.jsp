<%@ page import="ManagementQuadri.Model.Quadro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ManagementUtenti.Model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 16/06/2020
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" type="text/css" href="css/stylesheetAccount.css">
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <%Utente utente = (Utente) request.getSession().getAttribute("Utente");%>
</head>
<body>
<%@include file="navbar.jsp" %></div>

<% ArrayList<Quadro> listaQuadri = (ArrayList<Quadro>) request.getAttribute("listaQuadri");%>


<div id="userPage">
<div id="nick">
    <h1><%=utente.getUsername()%></h1>
</div>

<div id="info">
    <h3><%=utente.getNome()%></h3>
    <h3><%=utente.getCognome()%></h3>
    <h5><%=utente.getNazionalità()%></h5>
    <a href="mailto:"+<%=utente.getEmail()%>><h3><%=utente.getEmail()%></h3></a>
    <h1>Ordini passati</h1>
</div>

<div id="ordini">
    <%for (int i = 0; i < listaQuadri.size(); i++) {%>
    <div class="item">
        <img src=<%=listaQuadri.get(i).getImmagine()%>>
        <div class="infoQuadro">
        <p><%=listaQuadri.get(i).getTitolo()%></p>
        <p><%=listaQuadri.get(i).getArtista()%></p>
        <p><%=listaQuadri.get(i).getPrezzo()%></p>
        </div >
    </div>
    <%}%>
</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
