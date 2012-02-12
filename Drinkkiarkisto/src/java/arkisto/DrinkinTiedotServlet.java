/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Keni
 * Servlet nappaa käyttäjän linkin mukana antaman parametrin, joka on drinkin id.
 * Tämän perusteella luokka tunnistaa drinkin ja
 * asettaa attribuuteiksi drinkin erinäisiä tietoja ja ohjaa käyttäjän
 * tiedot.jsp-sivulle, jossa drinkin tiedot näytetään.
 */
public class DrinkinTiedotServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();

    
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
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("tiedot.jsp");
        dispatcher.forward(request, response);
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
