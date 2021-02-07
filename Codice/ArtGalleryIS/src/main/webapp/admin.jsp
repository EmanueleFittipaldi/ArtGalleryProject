<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=((Admin) request.getSession().getAttribute("Admin")).getUsername()%>
    </title>
    <link rel="stylesheet" type="text/css" href="css/stylesheetAdmin.css">
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <%--Inport di JQuery    --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        //Scripting con JQuery per far mostrare e nascondere le tabelle
        $("document").ready(function () {
            $("#mostra_utenti").click(function () {
                $("#lista_utenti").toggle();
            });

            $("#mostra_opere").click(function () {
                $("#lista_opere").toggle();
            });
        });
    </script>
</head>

<body>
<%@include file="navbar.jsp" %></div>



<%@ page import="Autenticazione.Model.Admin" %>

<div id="Contenuti">
    <h3>Benvenuto <%=((Admin) request.getSession().getAttribute("Admin")).getUsername()%>
    </h3>
    <button id="mostra_utenti" type="button" onclick="loadUtenti(this.value)" value="Utenti">Mostra utenti</button>
    <div id="ListaUtenti" style="overflow-x: auto">
        <div id="lista_utenti">
        </div>
    </div>

    <button id="mostra_opere" type="button" onclick="loadPaintings(this.value)" value="Quadri">Mostra opere</button>
    <div id="ListaOpere" style="overflow-x: auto; ">
        <div id="lista_opere">
        </div>
    </div>

    <div id="InserimentoQuadro">

        <form action="InserisciQuadro" method="post" enctype = "multipart/form-data" >
            <input type="text" name="titolo" placeholder="Titolo">
            <input type="text" name="artista" placeholder="Artista">
            <input type="text" name="anno" placeholder="Anno">
            <input type="text" name="genere" placeholder="Genere">
            <input type="text" name="tecnica" placeholder="Tecnica">
            <input type="text" name="dimensione" placeholder="Dimensione: Altezza*Larghezza">
            <input type="text" name="prezzo" placeholder="Prezzo">

            <input type="file" name="file">
            <input type="submit"  id="submitInserisci" value="Inserisci quadro">
        </form>
    </div>
</div>

<%@include file="footer.jsp" %>

<script>
    function loadPaintings(param) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var jsonOBJ = JSON.parse(xhttp.responseText);

                document.getElementById("lista_opere").innerHTML = "";

                var str = '<table> <tr> <th>Genere</th> <th>Id</th> <th>Prezzo</th> <th>Titolo</th> <th>Anno</th> <th>Artista</th> <th>Tecnica</th> <th>Dimensione</th> <th></th></tr>';

                for (var i = 0; i < jsonOBJ.length; i++) {
                    var item = jsonOBJ[i];
                    //preleviamo le proprietà dell'item
                    var Genere = item.Genere;
                    var Id = item.ID;
                    var Prezzo = item.Prezzo;
                    var Titolo = item.Titolo;
                    var Anno = item.Anno;
                    var Artista = item.Artista;
                    var Tecnica = item.Tecnica;

                    var Dimensione = item.Dimensione;


                    str += '<tr><td>' + Genere + '</td>' + '<td>' + Id
                        + '</td>' + '<td>' + Prezzo + '</td>' + '<td>' + Titolo + '</td>' + '<td>' + Anno + '</td>' +
                        '<td>' + Artista + '</td>' + '<td>' + Tecnica + '</td>' +
                        '<td>' + Dimensione + '</td><td><form action="modQuadro"><input type="submit" value="modifica"><input type="hidden" name="id" value=' + Id + '></form></td><td><form action="deletePermanently"><input type="submit" value="rimuovi"><input type="hidden" name="id" value=' + Id + '></form></td>';

                }
                document.getElementById("lista_opere").innerHTML += str + '</table>';
            }
        };

        xhttp.open("GET", "gestione?valore=" + param, true);
        xhttp.send();
    }

    function loadUtenti(param) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var jsonOBJ = JSON.parse(xhttp.responseText);

                document.getElementById("lista_utenti").innerHTML = "";

                var str = '<table> <tr> <th>Username</th> <th>Nome</th> <th>Cognome</th> <th>Nazionalità</th> <th>Email</th>  <th></th></tr>';


                for (var i = 0; i < jsonOBJ.length; i++) {
                    var item = jsonOBJ[i];
                    var Username = item.Username;
                    var Nazionalità = item.Nazionalità;
                    var Nome = item.Nome;
                    var Cognome = item.Cognome;
                    var Email = item.Email;

                    str += '<tr><td>' + Username + '</td>' + '<td>' + Nazionalità + '</td>' + '<td>' + Nome + '</td>' + '<td>' + Cognome + '</td>' +
                        '<td>' + Email + '</td></td><td><form action="modUtente"><input type="submit" value="modifica"><input type="hidden" name="username" value=' + Username + '></form></td><td><form action="rimuoviUtente"><input type="submit" value="rimuovi"><input type="hidden" name="username" value=' + Username + '></form></td></tr>';

                }
                document.getElementById("lista_utenti").innerHTML += str + '</table>';
            }
        };

        xhttp.open("GET", "gestione?valore=" + param, true);
        xhttp.send();
    }



</script>

</body>
</html>
