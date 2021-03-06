/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kenny Heinonen
 */

/**
 * Kirjaa käyttäjän ulos.
 *
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Kirjaa käyttäjän ulos eli lopettaa tämän istunnon.
     * Metodi nollaa kaikki tiedot istunnosta ja ohjaa käyttäjän
     * "logout.jsp"-sivulle.
     * @param request HTTP-pyyntö.
     * @param response HTTP-vastaus.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // lopettaa nykyisen istunnon, vaatii uuden kirjautumisen, jotta chattiin pääsee taas
        
        if (request.getSession().getAttribute("tunnus") != null) // jos istunnon aikana ei ole kirjauduttu sisään
        {
            request.getSession().setAttribute("tunnus", null);
        }
            
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("logout.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
