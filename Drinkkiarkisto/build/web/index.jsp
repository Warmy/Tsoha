<%-- 
    Document   : index
    Created on : Jan 28, 2012, 8:40:15 PM
    Author     : Keni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Drinkkiarkisto</title>
    </head>
    <body>
        <h1>Drinkkiarkisto</h1>
        
        </br>
        
        <form name="uusiKayttaja"
              action="${pageContext.request.contextPath}/LisaaKayttaja"
              method="post">
            Käyttäjätunnus: <input type="text" name="nimi"/></br>
            Salasana: <input type="text" name="salasana"/></br>
            Vahvista salasana: <input type="text" name="vahvistus"/></br>
            <input type="submit" value="Rekisteröidy"/>
        </form>
              
              </br>
              </br>
              <h2>Kirjaudu sisään</h2>
              
        <form name="kirjautuminen"
              action="${pageContext.request.contextPath}/Login"
              method="post">
            Käyttäjätunnus: <input type="text" name="nimi"/></br>
            Salasana: <input type="text" name="salasana"/></br>
            <input type="submit" value="Kirjaudu sisään"/>
        </form>
</html>
