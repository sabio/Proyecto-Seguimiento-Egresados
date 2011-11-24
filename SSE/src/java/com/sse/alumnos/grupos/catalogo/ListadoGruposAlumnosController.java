/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.grupos.catalogo;

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
public class ListadoGruposAlumnosController  extends AbstractController{
    private String successView;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        ListadoGruposAlumnosService service = new ListadoGruposAlumnosService();
        if(req.getParameter("hdnElimina")!=null && !req.getParameter("hdnElimina").equals(""))
            service.eliminarGrupoAlumno(new Integer(req.getParameter("hdnElimina")));
        
        mv.addObject("listadoGruposAlumnos", service.getListadoGruposAlumnos(req));
        
        try {
            service.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ListadoGruposAlumnosController.class.getName()).log(Level.SEVERE, null, ex);
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
