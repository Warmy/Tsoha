/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Ainesosa;
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
 * Lisää uuden ainesosan tietokantaan.
 * 
 */
public class LisaaAinesosaServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();

    /**
     * Ohjaa HTTP-pyynnön Lista-servletille.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.ListaServlet
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Lista"); // ohjataan pyyntö Lista-servletille
        dispatcher.forward(request, response);
    }

    /**
     * Lisää uuden ainesosan tietokantaan lomakkeessa annettujen tietojen avulla.
     * 
     * Kun admin-tunnuksilla sisään kirjautunut käyttäjä täyttää ainesosan lisäämistä
     * varten tarkoitetun lomakkeen sivulla "drinkkilista.jsp", tämä metodi käsittelee
     * lomakkeen tiedot. Jos syöte on oikean pituinen, luodaan uusi ainesosa ja lisätään
     * se Rekisteri-olion avulla tietokantaan. Jos syöte on väärän pituinen, ei tehdä mitään
     * ja palautetaan käyttäjä takaisin samalla sivulle.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#lisaaAinesosa(arkisto.Ainesosa) 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ainesosa = request.getParameter("aines"); // napataan lomakkeesta ainesosan nimi
        ainesosa = estaCrossSiteScripting(ainesosa);
        
        if (ainesosa.length() > 0 && ainesosa.length() <= 30) { // tarkistetaan pituus
            Ainesosa uusi = new Ainesosa(ainesosa); // tehdään uusi ainesosa ja lisätään rekisteriin
            rekisteri.lisaaAinesosa(uusi);
            request.getRequestDispatcher("/Lista").forward(request, response);
        } else {
            request.getRequestDispatcher("/Lista").forward(request, response); // jos tietoja puuttuu, ohjataan Lista-servletille
            return;
        }      
    }
    
    /**
     * Estää "Cross-site Scripting"-hyökkäykset, joita voi esiintyä lomakkeisiin
     * annetuissa syötteissä.
     * @param mjono Lomakkeessa annettu syöte.
     * @return Korjattu syöte.
     */
    private String estaCrossSiteScripting(String ainesosa) {
        ainesosa = ainesosa.replace("<", "&lt;");
        ainesosa = ainesosa.replace(">", "&gt;");
        return ainesosa;
    }
}
