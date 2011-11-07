package com.sse.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class FiltroAutenticado implements javax.servlet.Filter{
    
    private FilterConfig fc;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sesion = ((HttpServletRequest)request).getSession(false);
        if(peticionDesdePantallaDeLogin((HttpServletRequest)request)==false && (sesion==null || sesion.getAttribute("usuario")==null)  ){//Si no esta logueado
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<script type='text/javascript'>");
            out.println("window.location = 'login.run'");
            out.println("</script>");
            out.println("</HEAD>");
            out.println("<BODY>");
            out.println("</BODY>");
            out.println("</HTML>");
        }else//Si si esta logueado, entonces que siga su curso normal
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

    private boolean peticionDesdePantallaDeLogin(HttpServletRequest request) {
         String reqUrl = request.getRequestURL().toString();
         if(reqUrl.contains("login.run"))
             return true;         
         return false;
    }
    
}
