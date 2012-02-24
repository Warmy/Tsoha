<%-- 
    Document   : logout
    Created on : Feb 5, 2012, 12:56:51 AM
    Author     : Keni
--%>

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
          </div><!--/.nav-collapse -->
        </div>
      </div>
        
    </div>
        
        <div class="container">
        <h1>Kiitos käynnistäsi! Olet nyt kirjautunut ulos.</h1>
        </div>
    </body>
</html>
