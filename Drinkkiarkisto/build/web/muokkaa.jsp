<%-- 
    Document   : muokkaa
    Created on : Feb 17, 2012, 5:26:59 PM
    Author     : Keni
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
          </div>
      </div>       
    </div>
        
        <div class="container">
            <div class="hero-unit">
        <h1>Muokkaa drinkkiä</h1>
        
    <c:if test="${not empty drinkki}">
        <p> <font color="red">*</font> Tähdellä merkityt tiedot ovat pakollisia.</p>
        <form action="${pageContext.request.contextPath}/MuokkaaDrinkkia"
              method="post">          
            Kuvaus: </br> <textarea name="kuvaus"></textarea> <br/>
            <font color="red">*</font> Ohjeet: </br> <textarea name="ohjeet"></textarea> <br/>  
            
                    <input type="hidden" name="id" value="${drinkki.id}"/>
                    <input type="submit" value="Lisää resepti"/>
            </form> 
    </c:if>
        
        <font color="red">${virhe}</font>
        
        </div>
        </div>
    </body>
</html>
