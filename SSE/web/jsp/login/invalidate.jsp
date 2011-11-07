<%        
    session.invalidate();
    
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<script type='text/javascript'>");
    out.println("window.location = 'login.run'");
    out.println("</script>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("</BODY>");
    out.println("</HTML>");
%>