<%-- 
    Document   : register
    Created on : Feb 4, 2012, 6:47:21 PM
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
width:800px
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
        <title>Rekisteröidy</title>
    </head>
    <body>        
        <h1>Rekisteröityminen</h1>
                
        <ul id="list-nav">
            <li><a href="index.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/HaeDrinkki">Hae</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        
        <br/>
        <br/>
        <br/>
        <p> Luo käyttäjätunnus ja salasana. Käyttäjätunnuksen pituuden tulee olla 4-15 merkkiä pitkä
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
    </body>
</html>
