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
 * Asettaa drinkkilista.jsp-sivulle attribuutteja.
 */
public class ListaServlet extends HttpServlet {

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
        
        if (request.getParameter("sortByName") == null && request.getParameter("sortByCategory") == null)
            request.setAttribute("juomat", new Rekisteri().getJuomat()); // pyynnön attribuutiksi lista juomista
        else if (request.getParameter("sortByName") != null)
            jarjestaLista(request); // jos painettiin form-nappia "järjestä nimen mukaan", järjestetään juomat nimen mukaan
        else if (request.getParameter("sortByCategory") != null)
            jarjestaLajinMukaan(request);
        
        
        request.setAttribute("lajit", rekisteri.getJuomaLajit()); // pyynnön attribuutiksi lista juomalajeista
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("drinkkilista.jsp"); // ohjataan pyyntö drinkkilista.jsp-sivulle
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect(request.getRequestURI()); // POST-pyynnöt ohjataan doGetille
    }
    
    // järjestää drinkkilistan nimen perusteella nousevaan tai laskevaan järjestykseen
    private void jarjestaLista(HttpServletRequest request)
        throws ServletException, IOException {
        
        if (request.getParameter("sortByName") != null && jarjestys) { // jos painettiin, sort-nappia, järjestetään
            request.setAttribute("juomat", new Rekisteri().getOrderedJuomat()); // juomat laskevasti
            jarjestys = false;
        } else if (request.getParameter("sortByName") != null && !jarjestys) { // jos juomat on jo järjestetty laskevasti ja painetaan
            request.setAttribute("juomat", new Rekisteri().getJuomat()); // nappia sort, palautetaan alkuperäinen järjestys
            jarjestys = true;
        }  
    }

    private void jarjestaLajinMukaan(HttpServletRequest request)
        throws ServletException, IOException {
        
        if (request.getParameter("sortByCategory") != null && jarjestys) { // jos painettiin, sort-nappia, järjestetään
            request.setAttribute("juomat", new Rekisteri().getSortByLajitJuomat()); // juomat lajin perusteella nousevasti
            jarjestys = false;
        } else if (request.getParameter("sortByCategory") != null && !jarjestys) { // jos juomat on jo järjestetty lajin mukaan nousevasti ja painetaan
            request.setAttribute("juomat", new Rekisteri().getSortByLajitJuomat2()); // nappia sort, järjestetään lajin mukaan laskevasti
            jarjestys = true;
        }  
    }
}
