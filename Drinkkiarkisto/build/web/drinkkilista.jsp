<%-- 
    Document   : drinkkilista
    Created on : Feb 4, 2012, 10:14:48 PM
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
                            <li class="active"><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
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

                <h1>Reseptilista</h1>

                <c:if test="${empty juomat}">
                    <p><font size="4px"><br/>Ei drinkkejä. Rekisteröidy ja lisää itse!</font></p>
                    </c:if>
                <!-- Jos ei yhtään drinkkiä olemassa, älä tee mitään //--> 
                <c:if test="${not empty juomat}">
                    <br/>  
                    <br/>

                    <!-- kutsuu servletin doGet-metodia, joka järjestää drinkit nimen, lajin tai ainesosan mukaan -->
                    <table border="0" width="300" cellpadding="2" cellspacing="1">

                        <td><form action="${pageContext.request.contextPath}/Lista"
                                  name="myForm"   
                                  method="GET">
                                <input type="hidden" name="sortByName" value="myForm"/>
                                <input type="submit" value="Järjestä nimen mukaan" style="width:175px">

                            </form></td>   

                        <td><form action="${pageContext.request.contextPath}/Lista"
                                  name="myForm"
                                  method="GET">
                                <input type="hidden" name="sortByCategory" value="myForm"/>
                                <input type="submit" value="Järjestä juomalajin mukaan" style="width:175px">
                            </form></td>
                    </table>

                    <!-- Listaa drinkit -->
                    <table border="1" width="360" cellpadding="3" cellspacing="1" style="background-color: white">
                        <tr>
                            <th>Nimi</th>
                            <th>Id</th>
                            <th>Juomalaji</th>
                        </tr>
                        <c:forEach var="drinkki" items="${juomat}">
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
                </c:if>

                <br/>

                <!-- Reseptilomake, jossa annetaan nimi, kuvaus, ohjeet, ainesosat ja juomalaji.
                     Reseptin voi lisää vain, jos on kirjautunut. -->
                <c:if test="${not empty lisays}">
                    <h2>Lisää resepti</h2>

                    <p> <font color="red">*</font> Tähdellä merkityt tiedot ovat pakollisia.</p>

                    <form action="${pageContext.request.contextPath}/LisaaDrinkki"
                          method="post">
                        <font color="red">*</font> Drinkin nimi: </br> <input type="text" name="nimi"/> <br/>             
                        Kuvaus: </br> <textarea name="kuvaus"></textarea> <br/>
                        <font color="red">*</font> Ohjeet: </br> <textarea name="ohjeet"></textarea> <br/>  

                        <!-- Drinkille valitaan sopiva juomalaji -->
                        <font color="red">*</font> Juomalaji:               
                        <select name="lajinId">              
                            <c:forEach var="laji" items="${lajit}">                  
                                <option value="${laji.id}">${laji.nimi}</option>       
                            </c:forEach>    
                        </select><br/>

                        <!-- Drinkille valitaan siitä koostuvat ainesosat -->
                        <c:if test="${not empty ainesosat}">
                            Ainesosat:
                            <c:forEach var ="aines" items="${ainesosat}">
                                <input type="checkbox" name="aines" value="${aines.nimi}"/> ${aines.nimi}
                            </c:forEach>
                            </br>
                        </c:if>
                        <input type="submit" value="Lisää resepti"/>
                    </form>
                </c:if>


                <!-- Jos kirjautunut adminina sisään, voi lisätä/poistaa juomalajeja ja ainesosia -->  
                <c:if test="${not empty AdminRights}">

                    <h4>Lisää juomalaji</h4> 

                    <form action="${pageContext.request.contextPath}/LisaaJuomalaji"
                          method="post">  
                        Nimi: <input type="text" name="laji"/> 
                        <input type="submit" value="Lisää laji"/>
                    </form>

                    <h4>Lisää ainesosa</h4>


                    <form action="${pageContext.request.contextPath}/LisaaAinesosa"
                          method="post">
                        Nimi: <input type="text" name="aines"/>        
                        <input type="submit" value="Lisää ainesosa"/>
                    </form>

                    <h3>Juomalajit</h3>

                    <c:if test="${not empty lajit}">
                        <table border="1" width="200" cellpadding="1" cellspacing="1" style="background-color: white">   
                            <tr>      
                                <th>Juomalaji</th>
                                <th></th>
                            </tr>

                            <c:forEach var="laji" items="${lajit}">   
                                <tr>
                                    <td>${laji.nimi}</td>
                                    <td><a href="/Drinkkiarkisto/PoistaJuomalaji?id=${laji.id}">Poista</a></td>
                                </tr>                       
                            </c:forEach>
                        </table>
                    </c:if>

                    <br/>
                    <h3>Ainesosat</h3>


                    <c:if test="${not empty ainesosat}">

                        <table border="1" width="200" cellpadding="1" cellspacing="1" style="background-color: white">   
                            <tr>
                                <th>Ainesosa</th>
                                <th></th>
                            </tr>

                            <c:forEach var="aines" items="${ainesosat}">   
                                <tr> 
                                    <td>
                                        ${aines.nimi}
                                    </td>
                                    <td>
                                        <a href="/Drinkkiarkisto/PoistaAinesosa?id=${aines.nimi}">Poista</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    
                    <br/>
                    <p><b>Huom!</b><font size="3"> Voit poistaa juomalajeja ja ainesosia vain, jos</font></p>
                    <ul>
                        <li>
                            Millään drinkillä ei ole poistettavaa ainesosaa.
                        </li>
                        <li>
                            Mikään drinkki ei kuulu poistettavaan juomalajiin.
                        </li>
                    </ul>
                </c:if>
            </div>
        </div>
    </body>
</html>
