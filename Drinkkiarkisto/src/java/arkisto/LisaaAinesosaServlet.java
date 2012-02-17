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
public class LisaaAinesosaServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/Lista"); // ohjataan pyyntö Lista-servletille
        dispatcher.forward(request, response);
    }

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

    private String estaCrossSiteScripting(String ainesosa) {
        ainesosa = ainesosa.replace("<", "&lt;");
        ainesosa = ainesosa.replace(">", "&gt;");
        return ainesosa;
    }
}
