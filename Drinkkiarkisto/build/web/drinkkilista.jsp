<%-- 
    Document   : drinkkilista
    Created on : Feb 4, 2012, 10:14:48 PM
    Author     : Keni
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <title>Drinkkiarkisto</title>

    <!-- Le styles -->

    <link rel="stylesheet" type="text/css" href="/Drinkkiarkisto/bootstrap/css/bootstrap.css" />
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
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

              <li class="active"><a href="index.jsp">Etusivu</a></li>
              <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
              <li><a href="/Drinkkiarkisto/HaeDrinkki">Hae</a></li>
              <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
              <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
              <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
            </ul>
          </div>
        </div>
      </div>
        
    </div>
        
    <div class="container">

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
                <table border="1" width="360" cellpadding="3" cellspacing="1">
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
                        
              <h3>Juomalajit</h3>
              
            <ul>
                <c:forEach var="laji" items="${lajit}">
                    <li>${laji.nimi}</li>
                </c:forEach>
            </ul>
    </c:if>
        
 
              <!-- Jos ei ole adminina kirjautunut sisään, ei voi lisätä juomalajia tai ainesosia -->  
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
              </c:if>
        </div>
        <img style="position:fixed; TOP:60px; LEFT:750px; WIDTH:658px; HEIGHT:831px" src="kuvat/10.PNG"/>
    </body>
</html>
