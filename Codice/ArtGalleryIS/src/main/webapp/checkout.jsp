<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 16/06/2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/stylesheetCheckout.css">
    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/d/db/CMYK-color_model.png"/>
    <title>Checkout</title>
</head>
<body>
<%@include file="navbar.jsp" %></div>

<% List<String> listaCarte = new ArrayList<>();
    listaCarte.add("Poste Pay");
    listaCarte.add("Mastercard");
    listaCarte.add("Visa");
    listaCarte.add("American Express");
    listaCarte.add("Diners");
    listaCarte.add("JCB");
    listaCarte.add("Hype");
    listaCarte.add("Discover Card");
    listaCarte.add("China UnionPay");
    listaCarte.add("YAP");%>


<div id="moduloCheckout">

    <form action="saveOrder" method="get">
        <input type="text" name="numeroCarta" placeholder="Numero Carta">
<%--        <input type="text" name="tipoCarta" placeholder="Tipo Carta">--%>
        <select name="tipoCarta" >
            <% for (String s : listaCarte)
            {%>
            <option value=<%=s%>><%=s%>
            </option>
            <%}%>
        </select>
        <input type="submit" id="compra" value="Compra">
    </form>
</div>
<%@include file="footer.jsp" %>
<script>

</script>
</body>
</html>
