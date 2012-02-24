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

              <li class="active"><a href="#">Etusivu</a></li>
              <li><a href="/Drinkkiarkisto/Lista">Selaa</a></li>
              <li><a href="/Drinkkiarkisto/HaeDrinkki">Hae</a></li>
              <li><a href="/Drinkkiarkisto/Login">Kirjaudu sisään</a></li>
              <li><a href="/Drinkkiarkisto/LisaaKayttaja">Rekisteröidy</a></li>
              <li><a href="/Drinkkiarkisto/Logout">Kirjaudu ulos</a></li>
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
      
      <img src="/Drinkkiarkisto/kuvat/9.jpg"/>
        </div>
    </div>
  </body>
</html>
