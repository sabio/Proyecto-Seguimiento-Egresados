/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.preguntas.catalogo;

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
        mv.addObject("listadoPreguntas", service.getListadoPreguntas(req));
        
        return mv;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
    
    
}
