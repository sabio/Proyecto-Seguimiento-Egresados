/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.catalogo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 *
 * @author armando
 */
public class edicionCuestionarioController extends AbstractController{
    private String successView;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        edicionCuestionarioService service = new edicionCuestionarioService();
        service.establecerDatos(req);
        if(service.getElUsuarioDioParaGuardar()){
            service.guardar();
            mv.addObject("guardadoExitoso", true);
        }else{
            if(req.getParameter("preguntaAAsignar")!=null && !req.getParameter("preguntaAAsignar").equals("")){
                service.asignarPregunta(new Integer(req.getParameter("preguntaAAsignar")));
            }
            if(req.getParameter("preguntaABajar")!=null && !req.getParameter("preguntaABajar").equals("")){
                service.bajarPregunta(new Integer(req.getParameter("preguntaABajar")));
            }
            if(req.getParameter("preguntaASubir")!=null && !req.getParameter("preguntaASubir").equals("")){
                service.subirPregunta(new Integer(req.getParameter("preguntaASubir")));
            }
            if(req.getParameter("preguntaAEliminar")!=null && !req.getParameter("preguntaAEliminar").equals("")){
                service.eliminarPregunta(new Integer(req.getParameter("preguntaAEliminar")));
            }                        
            
            mv.addObject("cuestionario", service.cuestionario);
            mv.addObject("preguntasDisponibles", service.getPreguntasDisponibles());
            mv.addObject("preguntasAsignadas", service.getPreguntasAsignadas());
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
