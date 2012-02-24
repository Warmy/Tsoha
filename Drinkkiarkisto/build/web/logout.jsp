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
          <div class="nav-collapse">
            <ul class="nav">

              <!-- Linkit eri sivuille -->
              <li><a href="index.jsp"><span style="color: black">Etusivu</span></a></li>
              <li><a href="/Drinkkiarkisto/Lista"><span style="color: black">Selaa</span></a></li>
              <li><a href="/Drinkkiarkisto/HaeDrinkki"><span style="color: black">Hae</span></a></li>
              <li><a href="/Drinkkiarkisto/Login"><span style="color: black">Kirjaudu sisään</span></a></li>
              <li><a href="/Drinkkiarkisto/LisaaKayttaja"><span style="color: black">Rekisteröidy</span></a></li>
              <li class="active"><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
            </ul>
          </div>
        </div>
      </div>
        
    </div>
        
        <div class="container">
            <div class="hero-unit">
        <h1>Kiitos käynnistäsi! Olet nyt kirjautunut ulos.</h1>
            </div>
        </div>
    </body>
</html>
