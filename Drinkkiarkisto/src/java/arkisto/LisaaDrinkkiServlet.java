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
public class LisaaDrinkkiServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    private boolean jarjestys = true;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getSession().getAttribute("tunnus") != null) // jos ei ole kirjautunut sisälle, ei pysty lisäämään drinkkiä
            request.setAttribute("lisays", "lisaa");
        
        if (request.getParameter("sort") == null)
            request.setAttribute("juomat", new Rekisteri().getJuomat()); // pyynnön attribuutiksi lista juomista
        else
            jarjestaLista(request);
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("drinkkilista.jsp"); // ohjataan pyyntö drinkkilista.jsp-sivulle
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
        
        if (request.getParameterValues("arvo") != null) {
            String[] arvo = request.getParameterValues("arvo"); // arvosana
            int arvosana = Integer.parseInt(arvo[0]);
        }
        
        if (nimi.length() > 0 && ohjeet.length() > 0) { // jos drinkin nimi ja ohjeet löytyvät, tehdään uusi drinkki
            Drinkkiresepti resepti = new Drinkkiresepti(nimi, kuvaus, ohjeet); 
            rekisteri.lisaaDrinkki(resepti); // ja lisätään se tietokantaan, lopuksi
            response.sendRedirect(request.getRequestURI()); // ohjataan pyyntö samalle sivulle -> doGet suoritetaan
        } else {
            response.sendRedirect(request.getRequestURI()); // jos tietoja puuttuu, ohjataan samalle sivulle
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

    // järjestää drinkkilistan nimen perusteella nousevaan tai laskevaan järjestykseen
    private void jarjestaLista(HttpServletRequest request)
        throws ServletException, IOException {
        
        if (request.getParameter("sort") != null && jarjestys) { // jos painettiin, sort-nappia, järjestetään
            request.setAttribute("juomat", new Rekisteri().getOrderedJuomat()); // juomat laskevasti
            jarjestys = false;
        } else if (request.getParameter("sort") != null && !jarjestys) { // jos juomat on jo järjestetty laskevasti ja painetaan
            request.setAttribute("juomat", new Rekisteri().getJuomat()); // nappia sort, palautetaan alkuperäinen järjestys
            jarjestys = true;
        }  
    }
    
}
