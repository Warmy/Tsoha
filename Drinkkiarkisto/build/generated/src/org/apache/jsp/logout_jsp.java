package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class logout_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("ul#list-nav {\n");
      out.write("list-style:none;\n");
      out.write("margin:0px;\n");
      out.write("padding:0;\n");
      out.write("width:700px\n");
      out.write("}\n");
      out.write("\n");
      out.write("ul#list-nav li {\n");
      out.write("display:inline\n");
      out.write("}\n");
      out.write("\n");
      out.write("ul#list-nav li a {\n");
      out.write("text-decoration:none;\n");
      out.write("margin-right:5px;\n");
      out.write("padding:5px 0;\n");
      out.write("width:120px;\n");
      out.write("font-size:16px;\n");
      out.write("background:skyblue;\n");
      out.write("float:left;\n");
      out.write("text-align:center;\n");
      out.write("border-style:solid;\n");
      out.write("border-width:2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ul#list-nav li a:hover {\n");
      out.write("background:#a2b3a1;\n");
      out.write("color:#000\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Logout</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <ul id=\"list-nav\">\n");
      out.write("            <li><a href=\"lista.jsp\">Etusivu</a></li>\n");
      out.write("            <li><a href=\"/Drinkkiarkisto/LisaaDrinkki\">Selaa</a></li>\n");
      out.write("            <li><a href=\"/Drinkkiarkisto/Login\">Kirjaudu sisään</a></li>\n");
      out.write("            <li><a href=\"/Drinkkiarkisto/LisaaKayttaja\">Rekisteröidy</a></li>\n");
      out.write("            <li><a href=\"/Drinkkiarkisto/Logout\">Kirjaudu ulos</a></li>\n");
      out.write("        </ul>\n");
      out.write("        \n");
      out.write("        <br/>\n");
      out.write("        <h1>Kiitos!</h1>\n");
      out.write("    </body>\n");
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
