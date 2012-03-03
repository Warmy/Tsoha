/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Kayttaja;
import arkisto.Rekisteri;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * Kirjaudutaan sisään web-sovellukselle.
 * 
 */
public class LoginServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Ohjaa käyttäjän "login.jsp"-sivulle.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Kirjaa käyttäjän sisään web-sovellukseen.
     * 
     * Kun käyttäjä täyttää kirjautumislomakkeen "login.jsp"-sivulla, tämä metodi
     * nappaa lomakkeessa annetut tiedot. Jos tiedoilla löydetään tietokannasta
     * käyttäjä ja salasana vastaa tietokannasta haetun käyttäjätunnuksen salasanaa,
     * luodaan uusi istunto ja ohjataan käyttäjä Lista-servletille.
     * 
     * Jos tiedot olivat virheelliset, ohjataan käyttäjä takaisin "login.jsp"-sivulle
     * ja näytetään virheilmoitus.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String tunnus = request.getParameter("tunnus"); // napataan lomakkeen tiedoista tunnus
        String salasana = request.getParameter("salasana"); // ja salasana
        
        Kayttaja kayttaja = rekisteri.haeKayttaja(tunnus);
        
        /*
         * Lomakkeen mukana lähetetty tunnus ja salasana napataan ja jos
         * tunnus ja salasana ovat oikein, luodaan uusi istunto, jonka
         * attribuutiksi asetetaan juuri luotu tunnus ja käyttäjä ohjataan
         * drinkkilista-sivulle.
         */
        
        if (kayttaja != null && kayttaja.getTunnus().equals(tunnus) && kayttaja.getSalasana().equals(salasana)) {
            HttpSession session = request.getSession(); // jos kirjautumistiedot oikein, luodaan istunto
            session.setAttribute("tunnus", tunnus); // asetetaan attribuutiksi annettu tunnus
            
            response.sendRedirect(request.getContextPath()+"/Lista");
            // lähetä tiedot vastaukseen ja palaa metodikutsusta
            response.setHeader("tunnus", tunnus);
            response.flushBuffer();
        } else {
            request.setAttribute("virhe", "Käyttäjätunnus tai salasana on väärin."); // jos syötettiin väärät tiedot,
            doGet(request, response); // näytetään varoitusteksti käyttäjälle
            return;
    }
    }
    
}
