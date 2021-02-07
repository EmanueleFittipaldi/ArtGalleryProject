<%@ page import="ManagementQuadri.Model.Quadro" %>
<%@ page import="Acquisto.Model.Carrello" %>
<%@ page import="Autenticazione.Model.Admin" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Quadro quadro = (Quadro) request.getAttribute("quadro");
    Admin admin = (Admin) request.getSession().getAttribute("Admin");
    /*reperiamo il carrello per verificare che il quadro che stiamo visualizzando
    * non sia stato già aggiunto al carrello; in tal caso dobbiamo nascondere il tasto "aggiungi al carrello"*/
    Carrello carrello = (Carrello) request.getSession().getAttribute("Carrello");

%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/stylesheetProdotto.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <title><%=quadro.getTitolo()%>
    </title>
    <script>
        function checkButtonAdmin(value){
            if(value)
                $(".acquista").toggle();
        }

        function checkAlreadyAdded(value){
            if(value)
                $(".acquista").toggle();
        }
        function hideButton(venduto) {
            if (venduto)
                $(".acquista").toggle();
        }
    </script>
</head>
<% boolean  flagVenduto= false;
   boolean  isAdmin = false;
    if(quadro.getOrdine().getIdOrdine()>0)
    flagVenduto=true;
    if(admin!=null)
    isAdmin=true;
%>

<%--Se è stato venduto è inutile andare a testare le altre condizioni. non carichiamo il bottone "Aggiungi al carrello".--%>
<%--Se non è stato venduto, controllo se sono un admin e in tal caso nascondo il bottone. Se sono un utente, invece devo--%>
<%--andare a nasconderlo soltanto se venduto.--%>

<%if(flagVenduto)
{%>
    <body onload="hideButton(<%=flagVenduto%>)">
        <%}
        else if(isAdmin)
            {%>
                <body onload="checkButtonAdmin(<%=isAdmin%>)">
            <%}
            else{%>
                <body onload="checkAlreadyAdded (<%=carrello.doretrieveById(quadro.getId())!=null%>)">
                <%}
            %>






<%--    questo è quello che avevo prima--%>
<%--<body onload="hideButton(<%=flagVenduto%>);--%>
<%--              checkButtonAdmin(<%=admin!=null%>);--%>
<%--              checkAlreadyAdded (<%=carrello.doretrieveById(quadro.getId())!=null%>)">--%>

<%@include file="navbar.jsp" %></div>
<div class="content">
    <form action="AggiungiAlcarrello" method="get">
        <h1><%=quadro.getTitolo()%>
        </h1>
        <h3 class="subtitle"><%=quadro.getArtista()%>
        </h3>
        <h3 class="subtitle"><%=quadro.getAnno()%>
        </h3>
        <img src="<%=quadro.getImmagine()%>" id="immagineProdotto">
        <p id="descrizione"><strong>Dimensione: </strong><%=quadro.getDimensione()%> <br/>
            <strong>Tecnica: </strong><%=quadro.getTecnica()%><br><strong>Genere: </strong><%=quadro.getGenere()%>
            <br><strong>Prezzo: </strong> $<%=quadro.getPrezzo()%>
        </p>
        <input type="submit" class="acquista" value="Aggiungi al carrello">
        <input type="hidden" name="id" value=<%=quadro.getId()%>>
    </form>
</div>
<%@include file="footer.jsp" %>



</body>
</html>
