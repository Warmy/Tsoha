<%-- 
    Document   : hae
    Created on : Feb 21, 2012, 4:46:33 PM
    Author     : Kenny Heinonen
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li class="active"><a href="/Drinkkiarkisto/HaeDrinkki">Hae</a></li>
                            <li><a href="/Drinkkiarkisto/Login"><span style="color: black">Kirjaudu sisään</span></a></li>
                            <li><a href="/Drinkkiarkisto/LisaaKayttaja"><span style="color: black">Rekisteröidy</span></a></li>
                            <li><a href="/Drinkkiarkisto/Logout"><span style="color: black">Kirjaudu ulos</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <div class="container">
            <div class="hero-unit">
                <h1>Etsi drinkki</h1>

                <!-- Jos haku meni jollain tavalla pieleen, näytetään virheilmoitus -->
                <c:if test="${not empty virhe}">    
                    <font color="red">${virhe}</font>    
                </c:if>

                <c:if test="${not empty drinkit}">
                    <!-- Listaa drinkit -->
                    <table border="1" width="360" cellpadding="3" cellspacing="1" style="background-color: white">
                        <tr>
                            <th>Nimi</th>
                            <th>Id</th>
                            <th>Juomalaji</th>
                        </tr>
                        <c:forEach var="drinkki" items="${drinkit}">
                            <tr>
                                <!-- Jokaisesta drinkistä on linkki DrinkinTiedot-servletiin, jolle annetaan parametrina
                                drinkin id. Servlet hoitaa drinkin tietojen käsittelyn ja ohjaa sivulle, jossa näytetään
                                drinkin kaikki tiedot. -->
                                <td><a href="/Drinkkiarkisto/DrinkinTiedot?id=${drinkki.id}">${drinkki.nimi}</a></td>
                                <td>${drinkki.id}</td>
                                <td>${drinkki.laji.nimi}</td>   
                            </tr>
                        </c:forEach>
                    </table>
                    <br/>
                </c:if>


                <form action="${pageContext.request.contextPath}/HaeDrinkki" 
                      method="post">
                    Hakusanat: </br><input type="text" name="sana" /><br/>

                    <br/>
                    <!-- Drinkille valitaan sopiva juomalaji -->
                    Juomalaji: 
                    <br/>
                    <select name="lajinId">          
                        <option value="0">Kaikki juomalajit</option>
                        <c:forEach var="laji" items="${lajit}">                  
                            <option value="${laji.id}">${laji.nimi}</option>       
                        </c:forEach>    
                    </select><br/><br/>
                    <input type="submit" value="Etsi"/>    
                </form>
            </div>
        </div>
    </body>
</html>
