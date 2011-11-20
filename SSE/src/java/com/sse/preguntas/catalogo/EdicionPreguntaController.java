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
public class EdicionPreguntaController extends AbstractController{
    private String successView;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        EdicionPreguntaService service = new EdicionPreguntaService();
        service.establecerDatos(req);
        if(service.getElUsuarioDioParaGuardar()){
            service.guardar();
            mv.addObject("guardadoExitoso", true);
        }else{
            mv.addObject("pregunta", service.pregunta);
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
