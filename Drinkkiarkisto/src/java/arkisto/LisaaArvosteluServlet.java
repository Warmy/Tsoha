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
 */
public class LisaaArvosteluServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/DrinkinTiedot");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nimiMerkki = request.getParameter("nick");
        String arvostelu = request.getParameter("arvostelu");
        String arvo = request.getParameter("arvo"); // arvosana
        
        long drinkinId = Long.parseLong(request.getParameter("drinkId")); // napataan drinkin id
        Drinkkiresepti drinkki = rekisteri.haeDrinkki(drinkinId); // haetaan drinkki, jolle arvostelu kirjoitettiin
        
        if ((nimiMerkki.length() > 0 && arvostelu.length() > 0) && arvo != null) {
            int arvosana = Integer.parseInt(arvo);
            Arvostelu uusi = new Arvostelu(arvosana, arvostelu, nimiMerkki);
            uusi.setResepti(drinkki);
            rekisteri.lisaaArvostelu(uusi);
            response.sendRedirect(request.getContextPath()+"/DrinkinTiedot?id="+drinkinId); // ohjataan samalle sivulle
        } else {
            response.sendRedirect(request.getContextPath()+"/DrinkinTiedot?id="+drinkinId); // ohjataan samalle sivulle
            return;
        }
            
    }
}
