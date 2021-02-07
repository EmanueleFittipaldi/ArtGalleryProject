<%@ page import="ManagementQuadri.Model.Quadro" %><%--
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
    <title><%=((Quadro)request.getAttribute("quadro")).getTitolo()%></title>
</head>

<body>
<%@include file="navbar.jsp" %></div>
<div id="modifica">
    <form action="SaveModQuadro" method="get">
        <input type="text" id=titolo name="Titolo" placeholder=<%=((Quadro)request.getAttribute("quadro")).getTitolo()%> value=<%=((Quadro)request.getAttribute("quadro")).getTitolo()%>>
        <input type="text" id=artista name="Artista" placeholder=<%=((Quadro)request.getAttribute("quadro")).getArtista()%> value=<%=((Quadro)request.getAttribute("quadro")).getArtista()%>>
        <input type="text" id=genere name="Genere" placeholder=<%=((Quadro)request.getAttribute("quadro")).getGenere()%> value=<%=((Quadro)request.getAttribute("quadro")).getGenere()%> >
        <input type="text" id=anno name="Anno" placeholder=<%=((Quadro)request.getAttribute("quadro")).getAnno()%> value=<%=((Quadro)request.getAttribute("quadro")).getAnno()%> >
        <input type="text" id=tecnica name="Tecnica" placeholder=<%=((Quadro)request.getAttribute("quadro")).getTecnica()%> value=<%=((Quadro)request.getAttribute("quadro")).getTecnica()%> >
        <input type="text" id=dimensione name="Dimensione" placeholder=<%=((Quadro)request.getAttribute("quadro")).getDimensione()%> value=<%=((Quadro)request.getAttribute("quadro")).getDimensione()%>>
        <input type="text" id=immagine name="Immagine" placeholder=<%=((Quadro)request.getAttribute("quadro")).getImmagine()%> value=<%=((Quadro)request.getAttribute("quadro")).getImmagine()%>>
        <input type="text" id=prezzo name="Prezzo" placeholder=<%=((Quadro)request.getAttribute("quadro")).getPrezzo()%> value= <%=((Quadro)request.getAttribute("quadro")).getPrezzo()%>>
        <input type="hidden" name="id" value=<%=((Quadro)request.getAttribute("quadro")).getId()%>>
        <input type="submit" value="Aggiorna quadro">
    </form>
</div>
<%@include file="footer.jsp" %>

</body>
</html>
