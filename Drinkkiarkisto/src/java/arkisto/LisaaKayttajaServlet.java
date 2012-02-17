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
        
        request.setAttribute("kayttajat", new Rekisteri().getKayttajat()); // annetaan pyynnön attribuutiksi lista käyttäjistä
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        
        tunnus = estaCrossSiteScripting(tunnus);
        salasana = estaCrossSiteScripting(salasana);
        
        if (onkoTunnusJoOlemassa(tunnus, request, response))
            return;
            
        // tunnuksen ja salasanan pituuden tarkistus
        if ((tunnus.length() > 3 && tunnus.length() <= 15) && (salasana.length() > 3 && salasana.length() <= 30)) {
        
        Kayttaja uusi = new Kayttaja(tunnus, salasana); // luodaan lomakkeen tietojen perusteella uusi käyttäjä
        rekisteri.lisaaKayttaja(uusi); // lisätään se tietokantaan
        response.sendRedirect(request.getRequestURI()); // ohjataan pyyntö samalle sivulle
        } else {
            request.setAttribute("virhe2", "Et antanut kelvollisen pituisia syötteitä!");
            doGet(request, response);
            return; // jos huono tunnus tai salasana, palataan sivulle ja näytetään virheilmoitus
        }
    }
    
    private boolean onkoTunnusJoOlemassa(String tunnus, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (rekisteri.haeKayttaja(tunnus) != null) { // jos tunnus löytyy jo,
            request.setAttribute("virhe", "Tunnus \""+tunnus+"\" on jo olemassa!"); // näytetään käyttäjälle seuraava viesti
            doGet(request, response);
            return true;
        }
        
        return false;
    }
    
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
}
