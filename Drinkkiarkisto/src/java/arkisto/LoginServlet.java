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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Keni
 */
public class LoginServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tunnus = request.getParameter("tunnus"); // napataan lomakkeen tiedoista tunnus
        String salasana = request.getParameter("salasana"); // ja salasana
        
        Kayttaja kayttaja = rekisteri.haeKayttaja(tunnus);
        
//        if (kayttaja == null) {
//            request.setAttribute("virhe", "Käyttäjätunnus tai salasana on väärin."); // jos syötettiin väärät tiedot,
//            doGet(request, response); // näytetään varoitusteksti käyttäjälle
//            return;
//        }
        /*
         * Lomakkeen mukana lähetetty tunnus ja salasana napataan ja jos
         * tunnus ja salasana ovat oikein, luodaan uusi istunto, jonka
         * attribuutiksi asetetaan juuri luotu tunnus ja käyttäjä ohjataan
         * etusivulle.
         */
        
        if (kayttaja != null && kayttaja.getTunnus().equals(tunnus) && kayttaja.getSalasana().equals(salasana)) {
            HttpSession session = request.getSession(); // jos kirjautumistiedot oikein, luodaan istunto
            session.setAttribute("tunnus", tunnus); // asetetaan attribuutiksi annettu tunnus
            
            response.sendRedirect(request.getContextPath()+"/Lista");
            // lähetä tiedot vastaukseen ja palaa metodikutsusta
            response.setHeader("tunnus", tunnus);
            response.flushBuffer();
        } else {
            request.setAttribute("virhe", "Käyttäjätunnus tai salasana on väärin."); // jos syötettiin väärät tiedot,
            doGet(request, response); // näytetään varoitusteksti käyttäjälle
            return;
    }
    }
    
}
