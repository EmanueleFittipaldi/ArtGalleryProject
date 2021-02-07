<%@ page import="Acquisto.Model.Carrello" %>
<%@ page import="ManagementQuadri.Model.QuadroDAO" %>
<%@ page import="ManagementQuadri.Model.Quadro" %>
<%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 12/06/2020
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Carrello carrello = (Carrello) request.getSession().getAttribute("Carrello");%>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <link rel="stylesheet" type="text/css" href="css/stylesheetCarrello.css">
    <script>
        $(document).ready(function () {
            if ($('#cartItems').children().length <= 4) {
                // do something
                $("p#noElements").toggle();
            }

        });
    </script>
    <title>Carrello</title>
</head>

<body>
<%@include file="navbar.jsp" %></div>

<!--Cart section -->
<div id="cartContainer">
    <div id="cartItems">
        <h1>Cart</h1>
        <%for (int i = 0; i < carrello.getSize(); i++) {%>
        <%Quadro quadro = carrello.doretrieveByIndex(i);%>
        <div id="<%=quadro.getId()%>" class="cartItem">
            <img src=<%=quadro.getImmagine()%>>
            <div class="informazioni">
                <h4><%=quadro.getTitolo()%>
                </h4>
                <p><%=quadro.getArtista()%>
                </p>
                <p>$<%=quadro.getPrezzo()%>
                </p>
            </div>
            <div class="removeButtonDiv">
                <input type="button" id="removeButton" value="Remove" onclick="deleteItem(<%=quadro.getId()%>)">
            </div>
        </div>
        <%}%>

        <p id="totaleCarrello">Total: $<%=carrello.getTotale()%>
        </p>
        <p hidden id="noElements">Empty cart</p>
        <form action="checkOut" method="get">
            <input id="checkoutButton" type="submit" name="checkout" value="Checkout">
        </form>
    </div>

    <div id="cartSidebar">
        <h1>Payment methods</h1>
        <img src="images/PaymentsMethods.png" id="PaymentMethods" alt="PaymentMethods">
        <h3 id="reviews">Reviews</h3>
        <blockquote>This Is definitely the best site ever!</blockquote>
        <blockquote>Only top notch paintings, at a very low price.</blockquote>

    </div>
</div>
<script>
    function deleteItem(id) {
        /*un pò di jQuery*/
        $.get("removeItem?id=" + id, null); /*rimuovo il quadro dalla sessione*/
        $("div" + "#" + id).remove(); /*rimuovo il div relativo al quadro dal DOM della pagina*/
        $.get("totale", function (data, status) {
            $("p#totaleCarrello").html("Total: $" + data);
        });
        if ($('#cartItems').children().length <= 4) {
            // mostro la scritta "Empty Cart"
            $("p#noElements").toggle();
        }
    }
</script>
<%@include file="footer.jsp" %>
</body>
</html>
