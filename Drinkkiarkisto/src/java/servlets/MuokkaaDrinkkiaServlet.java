/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import arkisto.Drinkkiresepti;
import arkisto.Rekisteri;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Keni
 */
public class MuokkaaDrinkkiaServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();

    
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long drinkinId = Long.parseLong(request.getParameter("edit"));
        Drinkkiresepti muokattava = rekisteri.haeDrinkki(drinkinId);
        
        request.setAttribute("drinkki", muokattava);
        request.setAttribute("lajit", rekisteri.getJuomaLajit());
        request.setAttribute("ainesosat", rekisteri.getAinesOsat());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("muokkaa.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
}
