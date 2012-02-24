<%-- 
    Document   : index
    Created on : Feb 4, 2012, 6:46:03 PM
    Author     : Keni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Drinkkiarkisto</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="/Drinkkiarkisto/bootstrap/css/bootstrap.css" />
    <style>
      body {
          background-color: black;
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

              <!-- Linkit eri sivuille -->
              <li class="active"><a href="index.jsp">Etusivu</a></li>
              <li><a href="/Drinkkiarkisto/Lista"><span style="color: black">Selaa</span></a></li>
              <li><a href="/Drinkkiarkisto/HaeDrinkki"><span style="color: black">Hae</span></a></li>
              <li><a href="/Drinkkiarkisto/Login"><span style="color: black">Kirjaudu sisään</span></a></li>
              <li><a href="/Drinkkiarkisto/LisaaKayttaja"><span style="color: black">Rekisteröidy</span></a></li>
              <li><a href="/Drinkkiarkisto/Logout"><span style="color: black">Kirjaudu ulos</span></a></li>
            </ul>
          </div>
        </div>
        </div>
    </div>
    <div class="container">

        <div class="hero-unit">
      <h1>Tervetuloa drinkkiarkistoon!</h1>
      <p>Drinkkiarkisto sisältää paljon erilaisia drinkkireseptejä, joita voi selailla ja etsiä hakusanojen perusteella.
         Rekisteröitymällä pääset lisäämään ja arvostelemaan drinkkireseptejä itse!</p>
      
        </div>
    </div>
      <img src="/Drinkkiarkisto/kuvat/9-2.png"/>
  </body>
</html>
