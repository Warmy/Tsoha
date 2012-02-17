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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muokkaa drinkkiä</title>
    </head>
    <body>
        <h1>Muokkaa drinkkiä</h1>
        
    <c:if test="${not empty drinkki}">
        ${drinkki.nimi}
        ${drinkki.id}
        <p> <font color="red">*</font> Tähdellä merkityt tiedot ovat pakollisia.</p>
        <form action="${pageContext.request.contextPath}/MuokkaaDrinkkia"
              method="post">          
            Kuvaus: </br> <textarea name="kuvaus"></textarea> <br/>
            <font color="red">*</font> Ohjeet: </br> <textarea name="ohjeet"></textarea> <br/>  
            
                    <input type="hidden" name="id" value="${drinkki.id}"/>
                    <input type="submit" value="Lisää resepti"/>
            </form>
    </c:if>
    </body>
</html>
