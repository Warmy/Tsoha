package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class drinkkilista_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Reseptilista</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Reseptilista</h1>\n");
      out.write("        \n");
      out.write("              <!-- listaa drinkit //-->   \n");
      out.write("            <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty lista}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">                        \n");
      out.write("            <c:forEach var=\"drinkki\" items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${lista}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                Drinkki: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${drinkki.nimi}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</br>\n");
      out.write("            </c:forEach>\n");
      out.write("            </c:if>\n");
      out.write("    \n");
      out.write("    </br>\n");
      out.write("    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${juoma}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("    </br>\n");
      out.write("    <h2>Lisää resepti</h2>\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/LisaaDrinkki\"\n");
      out.write("              method=\"post\">            \n");
      out.write("            Drinkin nimi: </br> <input type=\"text\" name=\"tunnus\"/> <br/>             \n");
      out.write("            Kuvaus: </br> <textarea name=\"kuvaus\"></textarea> <br/>\n");
      out.write("            Ohjeet: </br> <textarea name=\"ohjeet\"></textarea> <br/>\n");
      out.write("            Arvosana: <input type=\"radio\" name=\"arvo\" value=\"yksi\" /> 1\n");
      out.write("            <input type=\"radio\" name=\"arvo\" value=\"kaksi\" /> 2\n");
      out.write("            <input type=\"radio\" name=\"arvo\" value=\"kolme\" /> 3\n");
      out.write("            <input type=\"radio\" name=\"arvo\" value=\"nelja\" /> 4\n");
      out.write("            <input type=\"radio\" name=\"arvo\" value=\"viisi\" /> 5 <br/><br/>\n");
      out.write("            <input type=\"submit\" value=\"Lisää resepti\"/>\n");
      out.write("            </form>\n");
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
