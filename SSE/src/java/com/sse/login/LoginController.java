package com.sse.login;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class LoginController extends AbstractController{
    private String successView;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView(this.successView);
        Integer idUsuario;
        LoginService service = new LoginService();
        if(request.getParameter("txtUsuario")!=null && request.getParameter("txtPassword")!=null){//Si se desea loguearse
            idUsuario = service.isUsuarioValido(request.getParameter("txtUsuario"), request.getParameter("txtPassword"));
            if(idUsuario==null){//si ingreso un login y/o passwors invalido
                mv.addObject("invalido", true);
            }
            else{//Si si es correcto el usuario y password
                //Creamos la sesion
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", service.generaObjetoUsuario(idUsuario));
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/inicio");
                dispatcher.forward(request,response);
            }
            try {
                service.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
