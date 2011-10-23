/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.preguntas.catalogo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author armando
 */
public class PreguntasListadoController  extends AbstractController{
    private String successView;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        PreguntasListadoService service = new PreguntasListadoService();
        if(req.getParameter("hdnElimina")!=null && !req.getParameter("hdnElimina").equals(""))
            service.eliminarPregunta(new Integer(req.getParameter("hdnElimina")));
        
        mv.addObject("listadoPreguntas", service.getListadoPreguntas(req));
        
        try {
            service.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(PreguntasListadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
    
    
}
