package com.sse.alumnos.catalogo;

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
public class AlumnosListadoController extends AbstractController{
    private String successView;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        AlumnosListadoService service = new AlumnosListadoService();
        if(req.getParameter("hdnElimina")!=null && !req.getParameter("hdnElimina").equals(""))
            service.eliminarAlumno(new Integer(req.getParameter("hdnElimina")));
        
        mv.addObject("listadoAlumnos", service.getListadoAlumnos(req));
        
        try {
            service.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(AlumnosListadoController.class.getName()).log(Level.SEVERE, null, ex);
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
