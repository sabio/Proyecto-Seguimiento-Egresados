/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.preguntas.catalogo;

import com.sse.beans.generales.Indicador;
import com.sse.beans.generales.Pregunta;
import com.sse.beans.generales.TipoPregunta;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class EdicionPreguntaService {
    SQLExecutor execute;
    Pregunta pregunta;
    boolean elUsuarioDioParaGuardar;   
    
    EdicionPreguntaService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idPregunta = req.getParameter("idPregunta")!=null ? new Integer(req.getParameter("idPregunta")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            pregunta = new Pregunta();
            pregunta.setIdPregunta(req.getParameter("hdnIdPregunta").equals("") ? null : new Integer(req.getParameter("hdnIdPregunta")));
            pregunta.setPregunta(req.getParameter("txtPregunta"));
            pregunta.setIdTipoPregunta(new Integer(req.getParameter("slcTipoPregunta")));
            pregunta.setIdIndicador(req.getParameter("slcIndicador")!=null ? new Integer(req.getParameter("slcIndicador")) : null);
            pregunta.setActivo(req.getParameter("slcActivo"));
        }
        else{
            if(idPregunta!=null){
                execute.addParametro(1,idPregunta);
                ResultSet res = execute.executeQuery("select idpregunta,pregunta,idtipopregunta,idindicador,activo from dicpregunta where idpregunta = ?");
                res.next();
                pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getString(5));
            }
            else{
                pregunta=null;
            }
        }
    }
    
    public void guardar() throws SQLException {
        String query;
        if(pregunta.getIdPregunta()==null){//Agregar nueva pregunta
            query="insert into dicpregunta (pregunta,idtipopregunta,idindicador,activo) values (?,?,?,?)";
            execute.addParametro(1, pregunta.getPregunta());
            execute.addParametro(2, pregunta.getIdTipoPregunta());
            execute.addParametro(3, pregunta.getIdIndicador());
            execute.addParametro(4, pregunta.getActivo());
        }else{//Edicion de pregunta ya existente
            execute.addParametro(1, pregunta.getPregunta());
            execute.addParametro(2, pregunta.getIdTipoPregunta());
            execute.addParametro(3, pregunta.getIdIndicador());
            execute.addParametro(4, pregunta.getActivo());
            execute.addParametro(5, pregunta.getIdPregunta());
            query="update dicpregunta set pregunta = ?, idtipopregunta = ?, idindicador = ?, activo = ? where idpregunta = ?";
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

    ArrayList<Indicador> getIndicadores() throws SQLException {
        ArrayList<Indicador> indicadores = new ArrayList<Indicador>(); 
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idindicador,indicador from dicindicador where activo = 'S'");
        while(res.next()){
            indicadores.add(new Indicador(res.getInt(1),res.getString(2),"S"));
        }        
        return indicadores;
    }

    ArrayList<TipoPregunta> getTipoPreguntas() throws SQLException{
        ArrayList<TipoPregunta> tipoPregunta = new ArrayList<TipoPregunta>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idtipopregunta, tipopregunta from dictipopregunta");
        while(res.next()){
            tipoPregunta.add(new TipoPregunta(res.getInt(1),res.getString(2)));
        }        
        return tipoPregunta;
    }

    
}
