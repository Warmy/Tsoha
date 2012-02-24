/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Juomalaji;
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
 * Lisää uuden juomalajin tietokantaan.
 * 
 */
public class LisaaJuomalajiServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    /**
     * Ohjaa HTTP-pyynnön Lista-servletille.
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
     * Lisää uuden juomalajin tietokantaan lomakkeessa annettujen tietojen avulla.
     * 
     * Kun admin-tunnuksilla sisään kirjautunut käyttäjä täyttää juomalajin lisäämistä
     * varten tarkoitetun lomakkeen sivulla "drinkkilista.jsp", tämä metodi käsittelee
     * lomakkeen tiedot. Jos syöte on oikean pituinen, luodaan uusi Juomalaji-olio ja lisätään
     * se Rekisteri-olion avulla tietokantaan ja ohjataan käyttäjä Lista-servletille.
     * Jos tiedot olivat väärin, ei tehdä mitään ja ohjataan käyttäjä takaisin samalle sivulle.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#lisaaJuomalaji(arkisto.Juomalaji) 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String juomalaji = request.getParameter("laji"); // napataan lomakkeesta lajin nimi
        juomalaji = estaCrossSiteScripting(juomalaji);
        
        if (juomalaji.length() > 0 && juomalaji.length() <= 30) {
            Juomalaji uusi = new Juomalaji(juomalaji); // tehdään uusi juomalaji ja lisätään rekisteriin
            rekisteri.lisaaJuomalaji(uusi);
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
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
}
