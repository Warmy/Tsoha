/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Juomalaji;
import arkisto.Rekisteri;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * Poistaa juomalajin tietokannasta.
 * 
 */
public class PoistaJuomalajiServlet extends HttpServlet {

    /**
     * Tietokantaoperaatioita hoitava olio.
     */
    private Rekisteri rekisteri = new Rekisteri();
    
    /**
     * Poistaa juomalajin tietokannasta.
     * 
     * Kun admin painaa "drinkkilista.jsp"-sivulla kyseisen juomalajin kohdalta
     * "Poista"-nappia, tullaan tähän servletiin. Napin mukana annettiin parametrina
     * poistettavan juomalajin pääavain. Jos mikään drinkkiresepti ei kuulu kyseiseen
     * juomalajiin, poistetaan se Rekisteri-olion avulla tietokannasta.
     * 
     * Lopuksi ohjataan käyttäjä Lista-servletille.
     * 
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     * @see arkisto.Rekisteri#poistaJuomalaji(long) 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!onKirjautunut(request, response)) // tarkistetaan onko adminina kirjautunut
            return;
        
        long lajinId = Long.parseLong(request.getParameter("id")); // napataan juomalajin id  
        Juomalaji laji = rekisteri.haeJuomalaji(lajinId);
        
        if (laji.getDrinkit().isEmpty())
            rekisteri.poistaJuomalaji(lajinId); // poistetaan juomalaji tietokannasta, jos siihen ei kuulu mitään drinkkejä
        
        response.sendRedirect(request.getContextPath()+"/Lista");
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
            response.sendRedirect(request.getContextPath()+"/Lista"); // ohjataan takaisin kirjautumissivulle
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
