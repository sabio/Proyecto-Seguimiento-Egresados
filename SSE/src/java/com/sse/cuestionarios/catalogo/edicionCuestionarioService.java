/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.catalogo;

import com.sse.beans.generales.Cuestionario;
import com.sse.beans.generales.Pregunta;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author armando
 */
public class edicionCuestionarioService {
    SQLExecutor execute;
    Cuestionario cuestionario;
    boolean elUsuarioDioParaGuardar;   
    
    edicionCuestionarioService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idCuestionario = req.getParameter("idCuestionario")!=null && !req.getParameter("idCuestionario").equals("") ? new Integer(req.getParameter("idCuestionario")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            this.cuestionario = new Cuestionario();
            this.cuestionario.setCuestionario(req.getParameter("txtCuestionario"));        
            this.cuestionario.setActivo(req.getParameter("slcActivo"));
        }
        else{
            /*
            if(idCuestionario!=null){
                execute.addParametro(1,idCuestionario);
                ResultSet res = execute.executeQuery("select idpregunta,pregunta,idtipopregunta,idindicador,activo from dicpregunta where idpregunta = ?");
                res.next();
                pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getString(5));
            }
            else{
                pregunta=null;
            }
             */
             
        }
         
         
    }
    
    public synchronized void guardar() throws SQLException {
        String query="";
        ResultSet res;
        
        
        if(cuestionario.getIdCuestionario()==null){//Agregar nuevo cuestionario
            res = execute.executeQuery("SELECT max(idcuestionario)+1 FROM diccuestionario");
            res.next();
            cuestionario.setIdCuestionario(res.getInt(1));
            
            query="insert into diccuestionario (idcuestionario,cuestionario,activo) values (?,?,?)";
            execute.addParametro(1, cuestionario.getIdCuestionario());
            execute.addParametro(2, cuestionario.getCuestionario());
            execute.addParametro(3, cuestionario.getActivo());
        }else{//Edicion de pregunta ya existente
            /*execute.addParametro(1, pregunta.getPregunta());
            execute.addParametro(2, pregunta.getIdTipoPregunta());
            execute.addParametro(3, pregunta.getIdIndicador());
            execute.addParametro(4, pregunta.getActivo());
            execute.addParametro(5, pregunta.getIdPregunta());
            query="update dicpregunta set pregunta = ?, idtipopregunta = ?, idindicador = ?, activo = ? where idpregunta = ?";
             * 
             */
        }
        
        execute.executeUpdate(query);
        execute.commit();
        execute.limpiaParameros();
        
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    boolean getElUsuarioDioParaGuardar() {
        return this.elUsuarioDioParaGuardar;
    }

    ArrayList<Pregunta> getPreguntasDisponibles() {
        return null;
    }

    ArrayList<Pregunta> getPreguntasAsignadas() {
        return null;
    }
    
}
