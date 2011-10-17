/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.preguntas.catalogo;

import com.sse.beans.generales.Pregunta;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class edicionPreguntaService {
    SQLExecutor execute;
    Pregunta pregunta;
    boolean elUsuarioDioParaGuardar;   
    
    edicionPreguntaService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idPregunta = req.getParameter("idPregunta")!=null ? new Integer(req.getParameter("idPregunta")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            pregunta = new Pregunta();
            pregunta.setIdPregunta(req.getParameter("hdnIdPregunta").equals("") ? null : new Integer(req.getParameter("hdnIdPregunta")));
            pregunta.setPregunta(req.getParameter("txtPregunta"));
            pregunta.setActivo(req.getParameter("activo"));
        }
        else{
            if(idPregunta!=null){
                execute.addParametro(1,idPregunta);
                ResultSet res = execute.executeQuery("select * from dicpregunta where idpregunta = ?");
                res.next();
                pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getString(3));
            }
            else{
                pregunta=null;
            }
        }
    }
    
    public void guardar() throws SQLException {
        String query;
        if(pregunta.getIdPregunta()==null){//Agregar nueva pregunta
            query="insert into dicpregunta (pregunta,activo) values (?,?)";
            execute.addParametro(1, pregunta.getPregunta());
            execute.addParametro(2, pregunta.getActivo());
        }else{//Edicion de pregunta ya existente
            execute.addParametro(1, pregunta.getPregunta());
            execute.addParametro(2, pregunta.getActivo());
            execute.addParametro(3, pregunta.getIdPregunta());
            query="update dicpregunta set pregunta = ?, activo = ? where idpregunta = ?";
        }
        
        execute.executeUpdate(query);
        execute.commit();
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    boolean getElUsuarioDioParaGuardar() {
        return this.elUsuarioDioParaGuardar;
    }

    
}
