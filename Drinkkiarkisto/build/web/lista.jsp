<%-- 
    Document   : lista
    Created on : Jan 29, 2012, 3:56:41 PM
    Author     : Keni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <style type="text/css">
ul#list-nav {
list-style:none;
margin:0px;
padding:0;
width:530px
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
        <title>Drinkkiarkisto</title>
    </head>
    <body>
        <title>Drinkkiarkisto</title>
        <h1>Drinkkiarkisto</h1>
        
        <ul id="list-nav">
            <li><a href="lista.jsp">Etusivu</a></li>
            <li><a href="selaa.jsp">Selaa</a></li>
            <li><a href="login.jsp">Kirjaudu sisään</a></li>
        </ul>
        
        </br>
        <c:if test="${not empty lista}">
            <h2>Käyttäjät</h2>

            <c:forEach var="user" items="${lista}">
                ${user.tunnus}<br/>
            </c:forEach>
        </c:if>
                </br>
                <br/>
                
            <form name="uusiKayttaja"
                  action="${pageContext.request.contextPath}/LisaaKayttaja"
                  method="post">
                Käyttäjätunnus: </br> <input type="text" name="nimi"/> <br/>
                Salasana: </br> <input type="text" name="salasana"/> <br/>
                <input type="submit" value="Rekisteröidy"/>
            </form>

                  <br/>
                  <h3>Kirjaudu sisään</h3>

        <form name="kirjautuminen"
              action="${pageContext.request.contextPath}/Login"
              method="post">
            Käyttäjätunnus: </br> <input type="text" name="nimi"/> <br/>
            Salasana: </br> <input type="text" name="salasana"/> <br/>
            <input type="submit" value="Kirjaudu sisään"/>
        </form>
    </body>
</html>
