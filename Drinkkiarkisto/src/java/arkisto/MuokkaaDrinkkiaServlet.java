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
public class MuokkaaDrinkkiaServlet extends HttpServlet {
    
    private Rekisteri rekisteri = new Rekisteri();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
}
