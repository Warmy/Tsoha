<%-- 
    Document   : hae
    Created on : Feb 21, 2012, 4:46:33 PM
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
        <title>Hae drinkkiresepti</title>
    </head>
    <body>
        
        <ul id="list-nav">
            <li><a href="index.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        <br/>
        <h1>Etsi drinkki</h1>
    
        <c:if test="${not empty virhe}">    
            <font color="red">${virhe}</font>    
        </c:if>
            
        <c:if test="${not empty drinkit}">
               <!-- Listaa drinkit -->
                <table border="1" width="360" cellpadding="3" cellspacing="1">
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
            
        </c:if>

               <br/>
        <form action="${pageContext.request.contextPath}/HaeDrinkki" 
            method="post">
            Hakusanat: </br><input type="text" name="sana" /><br/>
            
            <!-- Drinkille valitaan sopiva juomalaji -->
            Juomalaji:               
            <select name="lajinId">          
                <option value="0">*</option>
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
            <input type="submit" value="Etsi"/>    
        </form>
    </body>
</html>
