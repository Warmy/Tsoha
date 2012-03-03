<%-- 
    Document   : login
    Created on : Feb 4, 2012, 9:27:47 PM
    Author     : Kenny Heinonen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                            <li class="active"><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
                            <li><a href="/Drinkkiarkisto/LisaaKayttaja"><span style="color: black">Rekisteröidy</span></a></li>
                            <li><a href="/Drinkkiarkisto/Logout"><span style="color: black">Kirjaudu ulos</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <div class="container">
            <div class="btn">
                <h1>Kirjaudu sisään</h1>

                <form action="${pageContext.request.contextPath}/Login" 
                      method="POST">
                    Käyttäjätunnus: </br><input type="text" name="tunnus" /><br/>
                    Salasana: </br><input type="password" name="salasana"><br/>
                    <input type="submit" value="Kirjaudu sisään"/>    
                </form> 

                <!-- näytetään virheilmoitus, jos kirjautuminen meni pieleen //-->

                <c:if test="${not empty virhe}">
                    <font color="red">${virhe}</font>    
                </c:if>

            </div>
        </div>
    </body>
</html>
