/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.indicadores.catalogo;

import com.sse.beans.generales.Indicador;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author armando
 */
public class EdicionIndicadorService {
    SQLExecutor execute;
    Indicador indicador;
    boolean elUsuarioDioParaGuardar;   
    
    EdicionIndicadorService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idPregunta = req.getParameter("idIndicador")!=null ? new Integer(req.getParameter("idIndicador")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            indicador = new Indicador();
            indicador.setIdIndicador(req.getParameter("hdnIdIndicador").equals("") ? null : new Integer(req.getParameter("hdnIdIndicador")));
            indicador.setIndicador(req.getParameter("txtIndicador"));
            indicador.setActivo(req.getParameter("slcActivo"));
        }
        else{
            if(idPregunta!=null){
                execute.addParametro(1,idPregunta);
                ResultSet res = execute.executeQuery("select * from dicindicador where idindicador = ?");
                res.next();
                indicador = new Indicador(res.getInt(1),res.getString(2),res.getString(3));
            }
            else{
                indicador=null;
            }
        }
    }
    
    public void guardar() throws SQLException {
        String query;
        if(indicador.getIdIndicador()==null){//Agregar nueva pregunta
            query="insert into dicindicador (indicador,activo) values (?,?)";
            execute.addParametro(1, indicador.getIndicador());
            execute.addParametro(2, indicador.getActivo());
        }else{//Edicion de pregunta ya existente
            execute.addParametro(1, indicador.getIndicador());
            execute.addParametro(2, indicador.getActivo());
            execute.addParametro(3, indicador.getIdIndicador());
            query="update dicindicador set indicador = ?, activo = ? where idindicador = ?";
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
