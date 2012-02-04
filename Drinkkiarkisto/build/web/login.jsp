<%-- 
    Document   : login
    Created on : Feb 4, 2012, 9:27:47 PM
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
        <title>Login</title>
    </head>
    <body>
        <h1>Kirjaudu sisään</h1>
        
        <ul id="list-nav">
            <li><a href="lista.jsp">Etusivu</a></li>
            <li><a href="/Drinkkiarkisto/LisaaDrinkki">Selaa</a></li>
            <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
            <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
            <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
        </ul>
        
        <br/>
        <br/>
        <br/>
                    
        <form action="${pageContext.request.contextPath}/Login" 
            method="POST">
            Käyttäjätunnus: </br><input type="text" name="tunnus" /><br/>
            Salasana: </br><input type="password" name="salasana"<br/></br>
            <input type="submit" value="Kirjaudu sisään"/>    
        </form>
         <br/>   
              
         <!-- näytetään virheilmoitus, jos rekisteröinti meni pieleen //-->
              
    <c:if test="${not empty virhe}">
        <font color="red">${virhe}</font>    
    </c:if>
    
    
    </body>
</html>
