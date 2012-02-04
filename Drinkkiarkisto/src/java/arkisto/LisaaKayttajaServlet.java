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
public class LisaaKayttajaServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Kayttaja kayttaja = new Kayttaja("Jopi", "Jalkapuoli");
        request.setAttribute("tunnus", kayttaja.getTunnus());
        
        request.setAttribute("kayttajat", new Rekisteri().getKayttajat());
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        
        if (rekisteri.haeKayttaja(tunnus) != null) { // jos tunnus löytyy jo,
            request.setAttribute("virhe", "Tunnus \""+tunnus+"\" on jo olemassa!"); // näytetään käyttäjälle seuraava viesti
            doGet(request, response);
            return;
        }
            
        if (tunnus.length() > 3 && salasana.length() > 3) {
        
        Kayttaja uusi = new Kayttaja(tunnus, salasana); // luodaan lomakkeen tietojen perusteella uusi käyttäjä
        rekisteri.lisaaKayttaja(uusi); // lisätään se tietokantaan
        response.sendRedirect(request.getRequestURI()); // ohjataan pyyntö samalle sivulle
        } else {
            response.sendRedirect(request.getContextPath()+"/LisaaKayttaja"); // jos huono tunnus tai salasana, palataan sivulle
        }
    }
}
