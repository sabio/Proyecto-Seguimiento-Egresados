/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.catalogo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armando
 */
public class ListadoCuestionariosController  extends AbstractController{
    private String successView;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        ListadoCuestionariosService service = new ListadoCuestionariosService();
        if(req.getParameter("hdnElimina")!=null && !req.getParameter("hdnElimina").equals(""))
            service.eliminarCuestionario(new Integer(req.getParameter("hdnElimina")));
        
        mv.addObject("listadoPreguntas", service.getListadoCuestionarios(req));
        
        try {
            service.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ListadoCuestionariosController.class.getName()).log(Level.SEVERE, null, ex);
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
