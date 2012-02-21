/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arkisto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Keni
 */
public class HaeDrinkkiServlet extends HttpServlet {

    private Rekisteri rekisteri = new Rekisteri();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HaeDrinkkiServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HaeDrinkkiServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {            
            out.close();
        }
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
        request.setAttribute("lajit", rekisteri.getJuomaLajit());
        request.setAttribute("ainesosat", rekisteri.getAinesOsat());
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("hae.jsp"); // ohjataan pyyntö drinkkilista.jsp-sivulle
        dispatcher.forward(request, response);
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
        String sana = request.getParameter("sana");
        String lajinId = request.getParameter("lajinId");
        String[] ainesosat = request.getParameterValues("aines");
        
        sana = estaCrossSiteScripting(sana);
        lajinId = estaCrossSiteScripting(lajinId);
        
        ArrayList<Ainesosa> lista = new ArrayList<Ainesosa>();
        if (ainesosat != null) {
            for (int i=0; i < ainesosat.length; ++i) {
                if (ainesosat[i] != null) {
                Ainesosa uusi = rekisteri.haeAinesosa(ainesosat[i]);
                lista.add(uusi);
                }
            }
        }
       
       if (sana.length() > 0 && sana.length() <= 30) {
           long juomalajinId = Long.parseLong(lajinId);
           request.setAttribute("drinkit", rekisteri.haeDrinkkiHaunTuloksena(sana, lista, juomalajinId));
           
           if (request.getAttribute("drinkit").toString().equals("[]"))
               request.setAttribute("virhe", "Hakusi ei tuottanut tuloksia.");
           
           doGet(request, response);
       } else {
           request.setAttribute("virhe", "Anna kelvollinen syöte.");
           doGet(request, response);
       }
    }
    
    private String estaCrossSiteScripting(String mjono) {
        mjono = mjono.replace("<", "&lt;");
        mjono = mjono.replace(">", "&gt;");
        return mjono;
    }
}
