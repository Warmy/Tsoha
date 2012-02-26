/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Drinkkiresepti;
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
 * Muokkaa drinkkireseptin tietoja.
 *
 */
public class MuokkaaDrinkkiaServlet extends HttpServlet {
    
    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();

    /**
     * Ohjaa käyttäjän "muokkaa.jsp"-sivulle, jossa attribuuteiksi on asetettu
     * muokattavan drinkin tiedot, kaikki juomalajit ja ainesosat.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @param drinkId Muokattavan drinkin pääavain.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String drinkId)
            throws ServletException, IOException {
        
        Long drinkinId = Long.parseLong(drinkId);
        request.setAttribute("drinkki", rekisteri.haeDrinkki(drinkinId));
        request.setAttribute("lajit", rekisteri.getJuomaLajit());
        request.setAttribute("ainesosat", rekisteri.getAinesOsat());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("muokkaa.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Asettaa drinkille muokattavat tiedot.
     * 
     * Kun admin on jonkin drinkkireseptin tieto-sivulla painanut lomaketta
     * "Muokkaa", se ohjautuu tälle servletille. Lomakkeen mukana annetaan
     * drinkkireseptin id, jolla muokattava resepti löydetään tietokannasta.
     * 
     * Tämän jälkeen asetetaan pyynnön attribuuteiksi muokattava drinkki, lista
     * juomalajeista ja ainesosista ja ohjataan käyttäjä "muokkaa.jsp"-sivulle,
     * jossa nämä tiedot näkyvät.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!onKirjautunut(request,response)) { // tarkistetaan, että on kirjautunut adminina
            return;
        }
        
        long drinkinId = Long.parseLong(request.getParameter("edit"));
        Drinkkiresepti muokattava = rekisteri.haeDrinkki(drinkinId);
        
        request.setAttribute("drinkki", muokattava);
        request.setAttribute("lajit", rekisteri.getJuomaLajit());
        request.setAttribute("ainesosat", rekisteri.getAinesOsat());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("muokkaa.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Muokkaa drinkin tietoja.
     * 
     * Kun admin on "muokkaa.jsp"-sivulla täyttänyt lomakkeisiin drinkin muokattavat
     * tiedot, tämä metodi nappaa nämä tiedot ja käsittelee ne. Jos syötteet olivat
     * oikean pituiset, niin Rekisteri-olion avulla päivitetään drinkkireseptille
     * nämä syötteet uusiksi tiedoiksi ja ohjataan käyttäjä drinkin tietosivulle, jossa
     * uusien päivitettyjen tietojen pitäisi näkyä.
     * 
     * Jos syötteet eivät olleet oikean pituiset tai tietoja puuttui, asetetaan
     * attribuutiksi virheilmoitus ja ohjataan käyttäjä samalle muokkaus-sivulle.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#paivitaDrinkki(long, java.lang.String, java.lang.String) 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!onKirjautunut(request,response)) { // tarkistetaan, että on kirjautunut adminina
            return;
        }
        
        String kuvaus = request.getParameter("kuvaus");
        String ohjeet = request.getParameter("ohjeet");
        String drinkId = request.getParameter("id");
        
        if ((kuvaus.length() > 0 && kuvaus.length() <= 300) && (ohjeet.length() > 0 && ohjeet.length() <= 300)) {
            kuvaus = estaCrossSiteScripting(kuvaus);
            ohjeet = estaCrossSiteScripting(ohjeet);
            drinkId = estaCrossSiteScripting(drinkId);
            
            long drinkinId = Long.parseLong(drinkId);
            
            rekisteri.paivitaDrinkki(drinkinId, kuvaus, ohjeet);
            response.sendRedirect(request.getContextPath()+"/DrinkinTiedot?id="+drinkinId);
        } else {
            request.setAttribute("virhe", "Et syöttänyt kaikkia tietoja!");
            processRequest(request, response, drinkId); // jos tietoja puuttuu, ohjataan samalle sivulle
            return;
        }
        
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
    
    /**
     * Tarkistaa, että käyttäjä on kirjautunut sisään adminina eli hänellä on istunto "meneillään".
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @return Tieto siitä onko kirjautunut vai ei.
     */
    private boolean onKirjautunut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String tunnus = (String) request.getSession().getAttribute("tunnus");
        
        if (tunnus != null && tunnus.equals("Admin")) { 
            return true;
        } else {                                                      // jos istunnon aikana ei ole kirjauduttu sisään
            response.sendRedirect(request.getContextPath()+"/Login"); // ohjataan takaisin kirjautumissivulle
            return false;
        }
    }
}
