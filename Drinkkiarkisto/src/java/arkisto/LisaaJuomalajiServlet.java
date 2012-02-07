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
public class LisaaJuomalajiServlet extends HttpServlet {

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
        
        String juomalaji = request.getParameter("laji"); // napataan lomakkeesta lajin nimi
        
        if (juomalaji.length() > 0) {
            Juomalaji uusi = new Juomalaji(juomalaji); // tehdään uusi juomalaji ja lisätään rekisteriin
            rekisteri.lisaaJuomalaji(uusi);
            request.getRequestDispatcher("/Lista").forward(request, response);
        } else {
            request.getRequestDispatcher("/Lista").forward(request, response); // jos tietoja puuttuu, ohjataan Lista-servletille
            return;
        }      
    }
}
