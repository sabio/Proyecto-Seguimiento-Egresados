/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.operacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author armando.gomez
 */
public class BienvenidaCuestionarioScreenController extends AbstractController{
    String successView;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        BienvenidaCuestionarioScreenService service = new BienvenidaCuestionarioScreenService();
        service.establecerDatos(req);
        
        mv.addObject("idAsignacion", req.getParameter("idAsignacion"));
        mv.addObject("nombreCuestionario", service.getNombreCuestionario());
        return mv;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
    
    
}
