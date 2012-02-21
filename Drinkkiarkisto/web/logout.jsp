<%-- 
    Document   : logout
    Created on : Feb 5, 2012, 12:56:51 AM
    Author     : Keni
--%>

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
        <title>Logout</title>
    </head>
    <body>
        
        <ul id="list-nav">
            <li><a href="index.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/HaeDrinkki">Hae</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        
        <br/>
        <h1>Kiitos!</h1>
    </body>
</html>
