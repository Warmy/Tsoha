<%-- 
    Document   : tiedot
    Created on : Feb 11, 2012, 12:44:01 PM
    Author     : Keni
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
                            <li><a href="/Drinkkiarkisto/HaeDrinkki"><span style="color: black">Hae</span></a></li>
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

                <h1>Drinkin tiedot</h1>
                <div style="width:auto;height:auto;border:1px solid black;padding:10px;background-color: white">

                    <!-- Jos kirjauduttu admin-tunnuksilla sisään, voidaan poistaa drinkki
                         tai muokata drinkin tietoja -->

                    <c:if test="${not empty AdminRights}">
                        <table border="0" cellpadding="2" cellspacing="1">
                            <td><form action="/Drinkkiarkisto/PoistaDrinkki"
                                      method="POST">          
                                    <input type="hidden" name="delete" value="${drinkinId}"/>        
                                    <input type="submit" value="Poista drinkki"> 
                                </form></td>

                            <td><form action="/Drinkkiarkisto/MuokkaaDrinkkia"
                                      method="GET">          
                                    <input type="hidden" name="edit" value="${drinkinId}"/>        
                                    <input type="submit" value="Muokkaa"></td>
                                </form> 
                        </table>
                    </c:if>

                    <p><b><font size="4px">Drinkin nimi: ${drinkinNimi}</font></b></p>
                    <hr/>
                    <p><b>Juomalaji:</b></p>
                    ${drinkinLaji}
                    </br>
                    <p><b>Kuvaus:</b></p>
                    ${drinkinKuvaus}
                    </br>
                    <p><b>Ohjeet:</b></p>
                    ${drinkinOhjeet}
                    </br>
                    <p><b>Ainesosat:</b></p>
                    <ul>
                        <c:forEach var="aines" items="${drinkinAinesosat}">            
                            <li>${aines.nimi}</li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- Listaa kaikki drinkkiin liittyvät arvostelut -->
                <c:if test="${not empty drinkinArvostelut}">
                    <br/>
                    <h2> Arvostelut </h2>
                    <c:forEach var="arvostelu" items="${drinkinArvostelut}">
                        <div style="width:auto;height:auto;border:1px solid black;padding:10px;background-color: white">
                            Käyttäjä: <b><font size="4px">${arvostelu.nimimerkki}</font></b></br> 
                            <hr/>
                            ${arvostelu.teksti}</br>
                            </br>Arvosana: ${arvostelu.arvosana}/5</br>

                            <c:if test="${not empty AdminRights}">

                                <!-- Jos kirjautunut adminina sisään, saa poistaa arvosteluja -->
                                <form action="/Drinkkiarkisto/PoistaArvostelu"     
                                      method="POST">              
                                    <input type="hidden" name="delete" value="${arvostelu.id}"/> 
                                    <input type="hidden" name="drinkinId" value="${drinkinId}"/>
                                    <input type="submit" value="Poista"> 
                                </form>
                            </c:if>

                        </div>
                        </br>
                    </c:forEach>
                </c:if>

                <!-- Jos kirjautunut sisään, saa kirjoittaa arvosteluja. -->
                <c:if test="${not empty ReviewRights}">
                    <h3>Kirjoita arvostelu</h3>

                    Arvostelun maksimipituus on 300 merkkiä.
                    <br/>
                    <br/>
                    <form action="${pageContext.request.contextPath}/LisaaArvostelu"
                          method="post">
                        Arvostelu:</br> <textarea name="arvostelu"></textarea></br>
                        Arvosana: <input type="radio" name="arvo" value="1"/> 1
                        <input type="radio" name="arvo" value="2" /> 2
                        <input type="radio" name="arvo" value="3" /> 3
                        <input type="radio" name="arvo" value="4" /> 4
                        <input type="radio" name="arvo" value="5" /> 5 <br/><br/>
                        <input type="hidden" name="drinkId" value="${drinkinId}"/>
                        <input type="submit" value="Arvostele"/>
                    </form>
                </c:if>
                <font color="red">${virhe}</font>
            </div>
        </div>
    </body>
</html>
