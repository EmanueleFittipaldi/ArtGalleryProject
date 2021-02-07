

<%@ page import="Autenticazione.Model.Admin" %>
<%@ page import="ManagementUtenti.Model.Utente" %>

<link rel="stylesheet" type="text/css" href="css/navbarLogin.css">
<div id="navbar">
    <img src="images/CMYK-color_model.png" id="logo" alt="logo">

        <%
    //Ci rendiamo conto se è loggato un utente oppure un admin
    //Se è loggato un utente allora viene fatta vedere la navbar degli utenti altrimenti
    //viene fatta vedere la navbar degli amministratori


    //Caso in cui non è un admin e non è un utente
    if(request.getSession().getAttribute("Admin") == null && request.getSession().getAttribute("Utente")== null)
        {%>

    <h1 id="title">Art Gallery </h1>
    <form action="handler">
        <button>Home</button>
        <input type="hidden" name="form" value="home">
    </form>

    <form action="handler">
        <button>Contact</button>
        <input type="hidden" name="form" value="contact">
    </form>

    <%--
    <form action="handler">
        <button>Log-In</button>
        <input type="hidden" name="form" value="login">
    </form>
    --%>
    <form action="login-servlet" method="post">
        <div class="dropdown">
            <span >Account</span>
            <div class="dropdown-content">
                <input class ="input" type="text" name="username" placeholder="Username"><br>
                <input class ="input" type="password" name="password" placeholder="Password"><br>
                <input class ="log" type="submit" value="Login">
                <hr class="solid">
                <a class= "email" href="login-servlet">Registrazione</a>
            </div>
        </div>
    </form>









    <form action="handler" value="cart">
        <button>Cart</button>
        <input type="hidden" name="form" value="cart">
    </form>


        <%} else
 //Caso in cui è un collezionista
 if(request.getSession().getAttribute("Admin") == null && request.getSession().getAttribute("Utente") != null)
       {

%>
    <h1 id="title">Art Gallery </h1>
    <form action="handler">
        <button>Home</button>
        <input type="hidden" name="form" value="home">
    </form>

    <form action="handler">
        <button>Contact</button>
        <input type="hidden" name="form" value="contact">
    </form>

    <form action="accountHandler">
        <button><%=((Utente) request.getSession().getAttribute("Utente")).getUsername()%></button>
        <input type="hidden" name="form" value="account">
    </form>

    <form action="handler">
        <button>Log-out</button>
        <input type="hidden" name="form" value="logout">
    </form>

    <form action="handler" value="cart">
        <button>Cart</button>
        <input type="hidden" name="form" value="cart">
    </form>

        <%  }
 //Caso in cui un utente si è loggato come amministratore
 else if(request.getSession().getAttribute("Admin")!= null && request.getSession().getAttribute("Utente")== null){
%>

    <h1 id="title">Art Gallery </h1>
    <form action="handler">
        <button>Home</button>
        <input type="hidden" name="form" value="home">
    </form>

    <form action="handler">
        <button>Contact</button>
        <input type="hidden" name="form" value="contact">
    </form>

    <form action="accountHandler">
        <button><%=((Admin)request.getSession().getAttribute("Admin")).getUsername()%></button>
        <input type="hidden" name="form" value="account">
    </form>

    <form action="handler">
        <button>Log-out</button>
        <input type="hidden" name="form" value="logout">
    </form>

        <%}%>



