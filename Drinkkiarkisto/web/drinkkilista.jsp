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
        <style type="text/css">
ul#list-nav {
list-style:none;
margin:0px;
padding:0;
width:700px
}

ul#list-nav li {
display:inline
}

ul#list-nav li a {
text-decoration:none;
margin-right:5px;
padding:5px 0;
width:120px;
font-size:16px;
background:skyblue;
float:left;
text-align:center;
border-style:solid;
border-width:2px;
}

ul#list-nav li a:hover {
background:#a2b3a1;
color:#000
}
</style>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reseptilista</title>
    </head>
    <body>
        <h1>Reseptilista</h1>
        
        <ul id="list-nav">
            <li><a href="lista.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        
        <br/>
        <br/>
        <br/>
              <!-- Jos ei yhtään drinkkiä olemassa, älä tee mitään //-->   
            <c:if test="${not empty juomat}">
                
                <!-- kutsuu servletin doGet-metodia, joka järjestää drinkit nimen tai lajin mukaan -->
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
                    <td>${drinkki.nimi}</td>
                    <td>${drinkki.id}</td>
                    <td>${drinkki.laji.nimi}</td>
                </tr>
            </c:forEach>
                </table>
            </c:if>
    <br/>
    </br>
    <!-- Reseptilomake, jossa annetaan nimi, kuvaus, ohjeet, arvosana ja juomalaji.
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
            Juomalaji:               
            <select name="lajinId">              
                <c:forEach var="laji" items="${lajit}">                  
                    <option value="${laji.id}">${laji.nimi}</option>       
                </c:forEach>    
            </select><br/>
            
            Arvosana: <input type="radio" name="arvo" value="1" /> 1
            <input type="radio" name="arvo" value="2" /> 2
            <input type="radio" name="arvo" value="3" /> 3
            <input type="radio" name="arvo" value="4" /> 4
            <input type="radio" name="arvo" value="5" /> 5 <br/><br/>
            <input type="submit" value="Lisää resepti"/>
            </form>
                                  
              <h3>Juomalajit</h3>
              
            <ul>
                <c:forEach var="laji" items="${lajit}">
                    <li>${laji.nimi}</li>
                    <c:forEach var="juoma" items="${lajit}">
                        ${juoma.getNimi()}<br/>
                    </c:forEach>
                </c:forEach>
            </ul>
    </c:if>
        
        <h4>Lisää juomalaji</h4>
        
        <form action="${pageContext.request.contextPath}/LisaaJuomalaji"
              method="post">
            Nimi: <input type="text" name="laji"/>
            <input type="submit" value="Lisää laji"/>
        </form>
    </body>
</html>