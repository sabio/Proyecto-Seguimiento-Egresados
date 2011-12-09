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
 * @author armando
 */
public class CuestionarioController extends AbstractController{
    String successView;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        CuestionarioService service = new CuestionarioService();
        service.establecerDatos(req);
        
        if(service.opcion==1){//El usuario le dio para guardar y continuar despues el cuestionario
            service.guardarCuestionarioPaContinuarDespues(req);
            mv.addObject("cerrarVentanaseSuspendio", true);
        }
        else if(service.opcion==2){//El usuario le dio para terminar el cuestionario
            service.guardarYTerminar(req);
            mv.addObject("cerrarVentanaseTermino", true);
        }
        
        mv.addObject("nombreCuestionario", service.getNombreCuestionario());
        mv.addObject("idAsignacion", req.getParameter("idAsignacion"));
        mv.addObject("preguntas", service.getPreguntas());
        
        return mv;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
    
}
