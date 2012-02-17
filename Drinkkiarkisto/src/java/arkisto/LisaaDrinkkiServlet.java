/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

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
public class LisaaDrinkkiServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Lista"); // ohjataan pyyntö Lista-servletille
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!onKirjautunut(request,response)) {
            return;
        }
        String nimi = request.getParameter("nimi"); // otetaan lomakkeesta drinkin nimi
        String kuvaus = request.getParameter("kuvaus"); // kuvaus
        String ohjeet = request.getParameter("ohjeet"); // ohjeet
        String[] ainesosa = request.getParameterValues("aines"); // drinkille merkityt ainesosat
        String lajinId = request.getParameter("lajinId");
        
        nimi = estaCrossSiteScripting(nimi);
        kuvaus = estaCrossSiteScripting(kuvaus);
        ohjeet = estaCrossSiteScripting(ohjeet);
        lajinId = estaCrossSiteScripting(lajinId);
        
        ArrayList<Ainesosa> lista = new ArrayList<Ainesosa>();
        if (ainesosa != null) {
            for (int i=0; i < ainesosa.length; ++i) {
                if (ainesosa[i] != null) {
                Ainesosa uusi = rekisteri.haeAinesosa(ainesosa[i]);
                lista.add(uusi);
                }   
            }
        }
        
        if ((nimi.length() > 0 && nimi.length() <= 30) && (ohjeet.length() > 0 && ohjeet.length() <= 300) 
                && lajinId != null && kuvaus.length() <= 300) { // jos drinkin nimi ja ohjeet löytyvät, tehdään uusi drinkki
            
            long juomalajinId = Long.parseLong(request.getParameter("lajinId"));
            // haetaan juomalaji, joka vastaa parametrina saatua lajin id:tä
            Juomalaji laji = rekisteri.haeJuomalaji(juomalajinId);
            Drinkkiresepti resepti = new Drinkkiresepti(nimi, kuvaus, ohjeet);
            resepti.setLaji(laji); // asetetaan mihin lajiin drinkki kuuluu
            resepti.setAinesosat(lista); // lisätään ainesosat drinkille
            rekisteri.lisaaDrinkki(resepti); // ja lisätään se tietokantaan, lopuksi
            
            request.getRequestDispatcher("/Lista").forward(request, response); // ohjataan pyyntö Lista-servletille -> doGet suoritetaan
        } else {
            request.getRequestDispatcher("/Lista").forward(request, response); // jos tietoja puuttuu, ohjataan samalle sivulle
            return;
        }
        
    }
    
    private boolean onKirjautunut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        if (request.getSession().getAttribute("tunnus") == null) { // jos istunnon aikana ei ole kirjauduttu sisään
            response.sendRedirect(request.getContextPath()+"/Login"); // ohjataan takaisin kirjautumissivulle
            return false;
        } else
            return true;
    }
    
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
    
}
