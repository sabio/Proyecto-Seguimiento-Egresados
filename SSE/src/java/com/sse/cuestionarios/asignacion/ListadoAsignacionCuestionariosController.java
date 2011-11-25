/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.asignacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author armando.gomez
 */
public class ListadoAsignacionCuestionariosController extends AbstractController {
    private String successView;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
         ModelAndView mv = new ModelAndView(this.successView);
         ListadoAsignacionCuestionariosService service = new ListadoAsignacionCuestionariosService();
         mv.addObject("listadoAsignaciones", service.getListadoAsignaciones());
         
         
         return mv;
    }
    
    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
}
