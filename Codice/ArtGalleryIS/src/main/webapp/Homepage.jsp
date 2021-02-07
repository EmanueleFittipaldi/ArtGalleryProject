<%@ page import="java.util.ArrayList" %>
<%@ page import="ManagementQuadri.Model.Quadro" %><%--
  Created by IntelliJ IDEA.
  User: Emanuele Fittipaldi
  Date: 12/04/2020
  Time: 11:25
--%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <script>
        function loadPaintings(genere) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {

                    //arrivati a questo punto dovrei avere la lista dei quadri in formato JSON
                    var jsonOBJ = JSON.parse(xhttp.responseText);
                    //cancella dalla visuale, i quadri caricati di default
                    document.getElementById("masonry").innerHTML = " ";

                    //vado a visualizzare i quadri che ho ricevuto dalla risposta
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
                        var Vendita = item.Vendita;
                        var Dimensione = item.Dimensione;
                        var Immagine = item.Immagine;

                        console.log("Id del quadro:" + Id);
                        var str = '<div class="item"><form action="vaiAlQuadro" method="get"><input type="submit" class="sub" value="Vedi Quadro"><input type="hidden" name="Id"value=' + Id + '><img src="' + Immagine + '"onclick="sendToQuadroPage()" /></form> </div>';

                        document.getElementById("masonry").innerHTML += str;
                    }
                }
            };
            xhttp.open("GET", "CaricaGenere?CatName=" + encodeURIComponent(genere), true);
            xhttp.send();
        }
    </script>
</head>

<body>
<% ArrayList<Quadro> listaQuadri = (ArrayList<Quadro>) request.getAttribute("listaQuadri");%>


<%@include file="navbar.jsp" %>

<select name="Genere" id="Genere" onchange="loadPaintings(this.value)">
    <option value="Tutti">Tutti</option>
    <option value="Ritratto">Ritratto</option>
    <option value="Paesaggio">Paesaggio</option>
    <option value="Scena">Scena</option>
</select>
</div>


<div class="content">
    <div id="masonry" class="masonry">


        <%for (int i = 0; i < listaQuadri.size(); i++) {%>

        <div class="item">
            <form action="vaiAlQuadro" method="get">
                <!--<a href="Da qualche parte">-->
                <input type="submit" class="sub" value="Vedi Quadro">
                <input type="hidden" name="Id" value=<%=listaQuadri.get(i).getId()%>>
                <img src=<%=listaQuadri.get(i).getImmagine()%> onclick="sendToQuadroPage()"/>
            </form>
            <!--</a>-->
        </div>

        <%}%>

    </div>
</div>
<%@include file="footer.jsp" %>
</body>

</html>
