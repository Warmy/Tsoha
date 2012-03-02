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
 * Listaa drinkkireseptit.
 * @author Kenny Heinonen
 * Asettaa drinkkilista.jsp-sivulle attribuutteja ja sisältää metodit, joiden avulla
 * voi järjestää listattavat drinkit nimen, juomalajin tai ainesosan mukaan.
 */
public class ListaServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    /**
     * Käytetään drinkkilistan järjestämiseen. Riippuen muuttujan arvosta
     * drinkkilista järjestetään joko nousevasti tai laskevasti nimen/juomalajin
     * perusteella.
     */
    private boolean jarjestys = true;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Kun käyttäjä menee "drinkkilista.jsp"-sivulle, käydään tässä servletissä ensin.
     * Jos käyttäjä on kirjautunut sisään, luodaan attribuutti, jolla annetaan sivulla
     * oikeus lisätä uusia drinkkejä. Jos on kirjautunut adminina sisään, luodaan lisäksi
     * attribuutti, jolla annetaan oikeus lisätä/poistaa juomalajeja & ainesosia.
     * 
     * Jos kyseisellä sivulla käyttäjä painoi lomaketta, jolla järjestetään listatut
     * drinkkireseptit nimen tai juomalajin mukaan, tehdään se. Joka tapauksessa
     * asetetaan attribuutti, jossa on arvona lista drinkkiresepteistä.
     * 
     * Lopuksi aina asetetaan attribuutit, joissa on arvoina lista juomalajeista
     * ja ainesosista ja tämän jälkeen ohjataan käyttäjä "drinkkilista.jsp"-sivulle.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getSession().getAttribute("tunnus") != null) {// jos ei ole kirjautunut sisälle, ei pysty lisäämään drinkkiä
            request.setAttribute("lisays", "lisaa");
            
            String tunnus = (String) request.getSession().getAttribute("tunnus");
            if (tunnus.equals("Admin"))
                request.setAttribute("AdminRights", "AdminOK");
        }
        
        if (request.getParameter("sortByName") == null && request.getParameter("sortByCategory") == null)
            request.setAttribute("juomat", rekisteri.getJuomat()); // pyynnön attribuutiksi lista juomista
        else if (request.getParameter("sortByName") != null)
            jarjestaNimenMukaan(request); // jos painettiin form-nappia "järjestä nimen mukaan", järjestetään juomat nimen mukaan
        else if (request.getParameter("sortByCategory") != null)
            jarjestaLajinMukaan(request);
        
        
        request.setAttribute("lajit", rekisteri.getJuomaLajit()); // pyynnön attribuutiksi lista juomalajeista
        request.setAttribute("ainesosat", rekisteri.getAinesOsat()); // pyynnön attribuutiksi lista ainesosista
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("drinkkilista.jsp"); // ohjataan pyyntö drinkkilista.jsp-sivulle
        dispatcher.forward(request, response);
    }
    
    /**
     * Ohjaa POST-pyynnöt kyseiselle osoitteelle, jossa satutaan olemaan ja yleensä
     * mennään sitä rataa suorittamaan doGet()-metodi.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille, esim Loginin doPost ohjaa
    }                                                   // pyynnön tämän servletin doPost-metodiin, joka ohjaa sen doGet:iin
    
    /**
     * Järjestää drinkkilistan drinkkien nimien perusteella nousevaan tai laskevaan järjestykseen.
     * 
     * Jos boolean-muuttuja jarjestys on true, palautetaan lista drinkeistä nimen perusteella
     * laskevassa järjestyksessä ja asetetaan jarjestys = false, jolloin seuraavalla kerralla, kun
     * tähän metodiin tullaan, niin palautetaan lista drinkeistä nimen perusteella nousevassa
     * järjestyksessä, jolloin jarjestys-muuttujan arvo taas vaihtuu.
     * @param request HTTP-pyyntö.
     */
    private void jarjestaNimenMukaan(HttpServletRequest request)
        throws ServletException, IOException {
        
        if (request.getParameter("sortByName") != null && jarjestys) { // jos painettiin, sort-nappia, järjestetään
            request.setAttribute("juomat", rekisteri.getOrderedJuomat()); // juomat laskevasti
            jarjestys = false;
        } else if (request.getParameter("sortByName") != null && !jarjestys) { // jos juomat on jo järjestetty laskevasti ja painetaan
            request.setAttribute("juomat", rekisteri.getJuomat()); // nappia sort, palautetaan nouseva järjestys
            jarjestys = true;
        }  
    }

    /**
     * Järjestää drinkkilistan drinkkien juomalajien perusteella nousevaan tai laskevaan järjestykseen.
     * 
     * Jos boolean-muuttuja jarjestys on true, palautetaan lista drinkeistä juomalajin perusteella
     * nousevassa järjestyksessä ja asetetaan jarjestys = false, jolloin seuraavalla kerralla, kun
     * tähän metodiin tullaan, niin palautetaan lista drinkeistä juomalajin perusteella laskevassa
     * järjestyksessä, jolloin jarjestys-muuttujan arvo taas vaihtuu.
     * @param request HTTP-pyyntö.
     */
    private void jarjestaLajinMukaan(HttpServletRequest request)
        throws ServletException, IOException {
        
        if (request.getParameter("sortByCategory") != null && jarjestys) { // jos painettiin, sort-nappia, järjestetään
            request.setAttribute("juomat", rekisteri.sortJuomatByLajitAsc()); // juomat lajin perusteella nousevasti
            jarjestys = false;
        } else if (request.getParameter("sortByCategory") != null && !jarjestys) { // jos juomat on jo järjestetty lajin mukaan nousevasti ja painetaan
            request.setAttribute("juomat", rekisteri.sortJuomatByLajitDesc()); // nappia sort, järjestetään lajin mukaan laskevasti
            jarjestys = true;
        }  
    }
}
