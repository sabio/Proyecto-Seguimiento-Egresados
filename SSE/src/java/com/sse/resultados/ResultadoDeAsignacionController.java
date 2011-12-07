/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.resultados;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author armando
 */
public class ResultadoDeAsignacionController extends AbstractController{
    private String successView;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        ResultadoDeAsignacionService service = new ResultadoDeAsignacionService();
        service.establecerDatos(req);
        
        mv.addObject("listadoAsignaciones", service.getListadoAsignaciones());      
        
        mv.addObject("listadoIdsIndicadores", service.getListadoIdsIndicadoresUsadasEnLaAsignacion());
        return mv;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
}
