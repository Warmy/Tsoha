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
        <title>${drinkinNimi}</title>
    </head>
    <body>
        <ul id="list-nav">
            <li><a href="lista.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        
        </br>
        
        <h1>Drinkin tiedot</h1>
        <div style="width:auto;height:auto;border:1px solid black;padding:10px">
        <p><b>Drinkin nimi: </b>${drinkinNimi}</p>
        <hr/>
        <p><b>Juomalaji:</b></p>
        ${drinkinLaji}
        </br>
        <p><b>Kuvaus:</b></p>
        ${drinkinKuvaus}
        </br>
        <p><b>Ohjeet:</b></p>
        ${drinkinOhjeet}
        </div>
        
        <!-- Listaa kaikki drinkkiin liittyvät arvostelut -->
        <c:if test="${not empty drinkinArvostelut}">
            <h2> Arvostelut </h2>
            <c:forEach var="arvostelu" items="${drinkinArvostelut}">
                <div style="width:auto;height:auto;border:1px solid black;padding:10px">
                Nimimerkki: <b><font size="4px">${arvostelu.nimimerkki}</font></b></br> 
                <hr/>
                ${arvostelu.teksti}</br>
                </br>Arvosana: ${arvostelu.arvosana}/5</br>
                </div>
                </br>
            </c:forEach>
        </c:if>

        <h3>Kirjoita arvostelu</h3>
        
        <form action="${pageContext.request.contextPath}/LisaaArvostelu"
              method="post">
            Nimimerkki:</br> <input type="text" name="nick"/></br>
            Arvostelu:</br> <textarea name="arvostelu"></textarea></br>
            Arvosana: <input type="radio" name="arvo" value="1"/> 1
            <input type="radio" name="arvo" value="2" /> 2
            <input type="radio" name="arvo" value="3" /> 3
            <input type="radio" name="arvo" value="4" /> 4
            <input type="radio" name="arvo" value="5" /> 5 <br/><br/>
            <input type="hidden" name="drinkId" value="${drinkinId}"/>
            <input type="submit" value="Arvostele"/>
        </form>
    </body>
</html>
