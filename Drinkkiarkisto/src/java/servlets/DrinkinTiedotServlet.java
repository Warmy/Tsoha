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
 * Käsittelee drinkin tietoja.
 * 
 * Servlet nappaa käyttäjän linkin mukana antaman parametrin, joka on drinkin id.
 * Tämän perusteella luokka tunnistaa drinkin ja
 * asettaa attribuuteiksi drinkin erinäisiä tietoja ja ohjaa käyttäjän
 * tiedot.jsp-sivulle, jossa drinkin tiedot näytetään.
 */
public class DrinkinTiedotServlet extends HttpServlet {
    
    /**
     * Käsittelee tietokantaoperaatioita. Tässä luokassa hakee drinkkireseptejä
     * tietokannasta.
     */
    private Rekisteri rekisteri = new Rekisteri();

    /**
     * Asettaa HTTP-pyynnön attribuuteiksi drinkin kaikki mahdolliset tiedot.
     * 
     * Kun käyttäjä on sivulla "drinkkilista.jsp", joka listaa drinkit, niin jokaisella drinkillä
     * on linkki, joka vie tähän servletiin. Linkin klikkauksen myötä servletille on
     * annettu drinkin yksilöivä pääavain, jonka metodi nappaa. Pääavaimen perusteella
     * haetaan drinkki tietokannasta ja asetetaan pyynnön attribuuteiksi kaikki drinkin
     * tiedot. Jos on kirjautunut adminina sisään, luodaan attribuutti "AdminRights",
     * jonka ansiosta sivulla voi sitten poistaa drinkkireseptin/arvosteluja tai muokata
     * arvosteluja.
     * 
     * Jos on ylipäätään kirjautunut sisään, luodaan attribuutti "ReviewRights", jonka
     * ansiosta sivulla voi luoda uusia arvosteluja drinkkireseptille.
     * 
     * Tämän jälkeen ohjataan pyyntö "tiedot.jsp"-sivulle, joka näyttää
     * HTML-sivulla attribuutteina annetut drinkin tiedot.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        long juomaId = Long.parseLong(request.getParameter("id")); // napataan drinkin id
        Drinkkiresepti drinkki = rekisteri.haeDrinkki(juomaId); // etsitään sen perusteella tietty drinkkiresepti-ilmentymä
        
        request.setAttribute("drinkinNimi", drinkki.getNimi());
        request.setAttribute("drinkinKuvaus", drinkki.getKuvaus());
        request.setAttribute("drinkinOhjeet", drinkki.getOhjeet());
        request.setAttribute("drinkinLaji", drinkki.getLaji().getNimi());
        request.setAttribute("drinkinId", drinkki.getId());
        request.setAttribute("drinkinArvostelut", drinkki.getArvostelut());
        request.setAttribute("drinkinAinesosat", drinkki.getAinesosat());
        
        if (request.getSession().getAttribute("tunnus") != null &&
            request.getSession().getAttribute("tunnus").equals("Admin")) { // jos admin kirjautunut sisään,
            request.setAttribute("AdminRights", "AdminOK"); // annetaan oikeus poistaa/muokata drinkkejä
            request.setAttribute("ReviewRights", "ReviewOK");  // ja poistaa/lisätä arvosteluja
        }
        
        if (request.getSession().getAttribute("tunnus") != null) { // jos kirjautunut sisään, saa kirjoittaa arvosteluja
            request.setAttribute("ReviewRights", "ReviewOK");
        }
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("tiedot.jsp");
        dispatcher.forward(request, response);
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
