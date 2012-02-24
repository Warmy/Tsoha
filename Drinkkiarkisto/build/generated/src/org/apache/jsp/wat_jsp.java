package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class wat_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Bootstrap, from Twitter</title>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("    <!-- Le styles -->\n");
      out.write("\n");
      out.write("    <link href=\"../assets/css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("      body {\n");
      out.write("        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */\n");
      out.write("      }\n");
      out.write("    </style>\n");
      out.write("    <link href=\"../assets/css/bootstrap-responsive.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("      <script src=\"//html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n");
      out.write("    <![endif]-->\n");
      out.write("\n");
      out.write("    <!-- Le fav and touch icons -->\n");
      out.write("    <link rel=\"shortcut icon\" href=\"images/favicon.ico\">\n");
      out.write("\n");
      out.write("    <link rel=\"apple-touch-icon\" href=\"images/apple-touch-icon.png\">\n");
      out.write("    <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"images/apple-touch-icon-72x72.png\">\n");
      out.write("    <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"images/apple-touch-icon-114x114.png\">\n");
      out.write("  </head>\n");
      out.write("\n");
      out.write("  <body>\n");
      out.write("\n");
      out.write("    <div class=\"navbar navbar-fixed-top\">\n");
      out.write("      <div class=\"navbar-inner\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("\n");
      out.write("          <a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("          </a>\n");
      out.write("          <a class=\"brand\" href=\"#\">Project name</a>\n");
      out.write("          <div class=\"nav-collapse\">\n");
      out.write("            <ul class=\"nav\">\n");
      out.write("\n");
      out.write("              <li class=\"active\"><a href=\"#\">Home</a></li>\n");
      out.write("              <li><a href=\"#about\">About</a></li>\n");
      out.write("              <li><a href=\"#contact\">Contact</a></li>\n");
      out.write("            </ul>\n");
      out.write("          </div><!--/.nav-collapse -->\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("\n");
      out.write("      <h1>Bootstrap starter template</h1>\n");
      out.write("      <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>\n");
      out.write("\n");
      out.write("    </div> <!-- /container -->\n");
      out.write("\n");
      out.write("    <!-- Le javascript\n");
      out.write("    ================================================== -->\n");
      out.write("    <!-- Placed at the end of the document so the pages load faster -->\n");
      out.write("    <script src=\"../assets/js/jquery.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-transition.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-alert.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-modal.js\"></script>\n");
      out.write("\n");
      out.write("    <script src=\"../assets/js/bootstrap-dropdown.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-scrollspy.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-tab.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-tooltip.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-popover.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-button.js\"></script>\n");
      out.write("\n");
      out.write("    <script src=\"../assets/js/bootstrap-collapse.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-carousel.js\"></script>\n");
      out.write("    <script src=\"../assets/js/bootstrap-typeahead.js\"></script>\n");
      out.write("\n");
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
