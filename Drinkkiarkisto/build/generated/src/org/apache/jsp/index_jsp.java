package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <title>Drinkkiarkisto</title>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/Drinkkiarkisto/bootstrap/css/bootstrap.css\" />\n");
      out.write("    <style>\n");
      out.write("      body {\n");
      out.write("          background-color: black;\n");
      out.write("        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */\n");
      out.write("      }\n");
      out.write("    </style>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/Drinkkiarkisto/bootstrap/css/bootstrap-responsive.css\" />\n");
      out.write("  </head>\n");
      out.write("\n");
      out.write("  <body>\n");
      out.write("\n");
      out.write("    <div class=\"navbar navbar-fixed-top\">\n");
      out.write("      <div class=\"navbar-inner\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            \n");
      out.write("          <a class=\"brand\">Drinkkiarkisto</a>\n");
      out.write("          <div class=\"nav-collapse\">\n");
      out.write("            <ul class=\"nav\">\n");
      out.write("\n");
      out.write("              <!-- Linkit eri sivuille -->\n");
      out.write("              <li class=\"active\"><a href=\"#\">Etusivu</a></li>\n");
      out.write("              <li><a href=\"/Drinkkiarkisto/Lista\">Selaa</a></li>\n");
      out.write("              <li><a href=\"/Drinkkiarkisto/HaeDrinkki\">Hae</a></li>\n");
      out.write("              <li><a href=\"/Drinkkiarkisto/Login\">Kirjaudu sisään</a></li>\n");
      out.write("              <li><a href=\"/Drinkkiarkisto/LisaaKayttaja\">Rekisteröidy</a></li>\n");
      out.write("              <li><a href=\"/Drinkkiarkisto/Logout\">Kirjaudu ulos</a></li>\n");
      out.write("            </ul>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("\n");
      out.write("        <div class=\"hero-unit\">\n");
      out.write("      <h1>Tervetuloa drinkkiarkistoon!</h1>\n");
      out.write("      <p>Drinkkiarkisto sisältää paljon erilaisia drinkkireseptejä, joita voi selailla ja etsiä hakusanojen perusteella.\n");
      out.write("         Rekisteröitymällä pääset lisäämään ja arvostelemaan drinkkireseptejä itse!</p>\n");
      out.write("      \n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("      <img src=\"/Drinkkiarkisto/kuvat/9.jpg\"/>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
