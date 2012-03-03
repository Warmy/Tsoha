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

/**
 *
 * @author Kenny Heinonen
 */

/**
 * Lisää uuden käyttäjän tietokantaan.
 * 
 */
public class LisaaKayttajaServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    /**
     * Boolean-muuttuja, joka kertoo onnistuiko "register.jsp":llä
     * rekisteröityminen uudeksi käyttäjäksi.
     */
    private boolean onnistuikoRekisterointi = false;

    /**
     * Ohjaa käyttäjän "register.jsp"-sivulle.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        // jos rekisteröinti onnistui doPost():ssa, annetaan ilmoitus siitä
        // sivulla
        if (onnistuikoRekisterointi) {
            request.setAttribute("registerOK", "Rekisteröityminen onnistui!");
            onnistuikoRekisterointi = false;
        }
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Lisää uuden käyttäjän tietokantaan lomakkeessa annettujen tietojen avulla.
     * 
     * Kun käyttäjä täyttää uuden käyttäjätunnuksen lisäämistä
     * varten tarkoitetun lomakkeen sivulla "register.jsp", tämä metodi käsittelee
     * lomakkeen tiedot. Jos syötteet menevät tarkistuksista läpi ja tunnusta ei ole vielä olemassa,
     * luodaan uusi Kayttaja-olio ja lisätään se Rekisteri-olion avulla tietokantaan ja
     * lopuksi ohjataan käyttäjä samalle sivulle ja annetaan ilmoitus rekisteröitymisen
     * onnistumisesta.
     * 
     * Jos tiedot olivat väärin tai tunnus on jo olemassa, luodaan virheilmoitus
     * ja ohjataan käyttäjä takaisin samalle sivulle, jossa virheilmoitus näytetään.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#lisaaKayttaja(arkisto.Kayttaja) 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        String vahvistus = request.getParameter("vahvistus");
        
        tunnus = estaCrossSiteScripting(tunnus);
        salasana = estaCrossSiteScripting(salasana);
        vahvistus = estaCrossSiteScripting(vahvistus);
        
        if (onkoTunnusJoOlemassa(tunnus, request, response))
            return;
            
        if ((tunnus.length() > 3 && tunnus.length() <= 15) && (salasana.length() > 3 && salasana.length() <= 30) &&
                (vahvistus.equals(salasana))) {
        
        Kayttaja uusi = new Kayttaja(tunnus, salasana); // luodaan lomakkeen tietojen perusteella uusi käyttäjä
        rekisteri.lisaaKayttaja(uusi); // lisätään se tietokantaan
        onnistuikoRekisterointi = true;
        response.sendRedirect(request.getRequestURI()); // ohjataan pyyntö samalle sivulle
        } else {
            request.setAttribute("virhe2", "Et antanut kelvollisia syötteitä!");
            doGet(request, response);
            return; // jos huono tunnus tai salasana, palataan sivulle ja näytetään virheilmoitus
        }
    }
    
    /**
     * Tarkistaa onko käyttäjätunnus jo olemassa mitä yritetään luoda.
     * 
     * Jos käyttäjätunnus on olemassa, asetetaan pyynnön attribuutiksi virheilmoitus ja
     * ohjataan vastuu doGet()-metodille.
     * 
     * @param tunnus Lomakkeessa annettu tunnus.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @return Tieto siitä onko käyttäjätunnus jo olemassa.
     */
    private boolean onkoTunnusJoOlemassa(String tunnus, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (rekisteri.haeKayttaja(tunnus) != null) { // jos tunnus löytyy jo,
            request.setAttribute("virhe", "Tunnus \""+tunnus+"\" on jo olemassa!"); // näytetään käyttäjälle seuraava viesti
            doGet(request, response);
            return true;
        }
        
        return false;
    }
    
    /**
     * Estää "Cross-site Scripting"-hyökkäykset, joita voi esiintyä lomakkeisiin
     * annetuissa syötteissä.
     * @param mjono Lomakkeessa annettu syöte.
     * @return Korjattu syöte.
     */
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
}
