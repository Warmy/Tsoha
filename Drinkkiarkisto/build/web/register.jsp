<%-- 
    Document   : register
    Created on : Feb 4, 2012, 6:47:21 PM
    Author     : Keni
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Drinkkiarkisto</title>

    <link rel="stylesheet" type="text/css" href="/Drinkkiarkisto/bootstrap/css/bootstrap.css" />
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            background-color: black;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="/Drinkkiarkisto/bootstrap/css/bootstrap-responsive.css" />
</head>
<body>

    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">

                <a class="brand">Drinkkiarkisto</a>
                <div class="nav-collapse">
                    <ul class="nav">

                        <!-- Linkit eri sivuille -->
                        <li><a href="index.jsp"><span style="color: black">Etusivu</span></a></li>
                        <li><a href="/Drinkkiarkisto/Lista"><span style="color: black">Selaa</span></a></li>
                        <li><a href="/Drinkkiarkisto/HaeDrinkki"><span style="color: black">Hae</span></a></li>
                        <li><a href="/Drinkkiarkisto/Login"><span style="color: black">Kirjaudu sisään</span></a></li>
                        <li class="active"><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
                        <li><a href="/Drinkkiarkisto/Logout"><span style="color: black">Kirjaudu ulos</span></a></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>

    <div class="container">
        <div class="hero-unit">

            <h1>Rekisteröidy</h1>

            <p>Luo käyttäjätunnus ja salasana. Käyttäjätunnuksen pituuden tulee olla 4-15 merkkiä pitkä
                ja salasanan 4-30 merkkiä pitkä. </p>

            <form name="uusiKayttaja"                  
                  action="${pageContext.request.contextPath}/LisaaKayttaja"
                  method="post">            
                <b>Käyttäjätunnus:</b> </br> <input type="text" name="tunnus"/> <br/>             
                <b>Salasana:</b> </br> <input type="password" name="salasana"/> <br/>              
                <input type="submit" value="Rekisteröidy"/>
            </form>

            <!-- näytetään virheilmoitus, jos rekisteröinti meni pieleen //-->
            <c:if test="${not empty virhe}">
                <font color="red">${virhe}</font>
            </c:if>

            <c:if test="${not empty virhe2}">
                <font color="red">${virhe2}</font>
            </c:if>

            <!-- listaa käyttäjät, jos heitä on //-->
            <h2>Käyttäjät</h2>
            <c:if test="${not empty kayttajat}">                        
                <c:forEach var="kayttaja" items="${kayttajat}">
                    Tunnus: ${kayttaja.tunnus}, salasana: ${kayttaja.salasana}</br>
                </c:forEach>
            </c:if>
        </div>
    </div>
</body>
</html>
