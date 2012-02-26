/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
 * Hakee drinkkejä hakusanan ja juomalajin perusteella.
 * 
 */
public class HaeDrinkkiServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    /**
     * Asettaa HTTP-pyynnön attribuutiksi eri juomalajit.
     * 
     * Kun käyttäjä klikkaa linkkiä "Hae", päädytään tähän servletiin. Metodi
     * asettaa pyynnön attribuutiksi kaikki mahdolliset juomalajit, joita tietokannasta
     * löytyy ja ohjaa käyttäjän sivulle "hae.jsp", jossa hakusanan lisäksi voidaan
     * rajata hakua valitsemalla drinkkiä koskeva juomalaji.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lajit", rekisteri.getJuomaLajit());
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("hae.jsp"); // ohjataan pyyntö drinkkilista.jsp-sivulle
        dispatcher.forward(request, response);
    }

    /**
     * Hakee lomakkeen tietojen perusteella drinkkejä.
     * 
     * Kun käyttäjä syöttää "hae.jsp"-sivulla lomakkeeseen hakusanan, jolla
     * tiettyä drinkkiä/drinkkejä käyttäjä etsii, niin tämä metodi nappaa
     * lomakkeessa annetut tiedot eli hakusanan ja mahdollisen juomalajin.
     * Tiedot syötetään Rekisteri-olion metodille, joka tekee kyselyn ja palauttaa
     * listan drinkkejä, jotka sopivat annettuihin tietoihin.
     * 
     * Tämän jälkeen ohjataan käyttäjä "hae.jsp"-sivulle, jossa löydetyt drinkit
     * ovat listattuna. Jos hakusana oli kelvoton tai haku ei tuottanut tuloksia,
     * näytetään virheilmoitus.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#haeDrinkkiHaunTuloksena(java.lang.String, long) 
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sana = request.getParameter("sana");
        String lajinId = request.getParameter("lajinId");
        
        sana = estaCrossSiteScripting(sana);
        lajinId = estaCrossSiteScripting(lajinId);
       
       if (sana.length() > 0 && sana.length() <= 30) {
           long juomalajinId = Long.parseLong(lajinId);
           request.setAttribute("drinkit", rekisteri.haeDrinkkiHaunTuloksena(sana, juomalajinId));
           
           if (request.getAttribute("drinkit").toString().equals("[]"))
               request.setAttribute("virhe", "Hakusi ei tuottanut tuloksia.");
           
           doGet(request, response);
       } else {
           request.setAttribute("virhe", "Anna kelvollinen syöte.");
           doGet(request, response);
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
}
