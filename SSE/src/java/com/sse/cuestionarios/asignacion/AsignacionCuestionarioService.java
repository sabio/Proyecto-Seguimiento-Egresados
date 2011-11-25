/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.asignacion;

import com.sse.beans.generales.AsignacionCuestionario;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class AsignacionCuestionarioService {
    SQLExecutor execute;
    AsignacionCuestionario asignacionCuestionario;
    boolean elUsuarioDioParaGuardar;   
    
    AsignacionCuestionarioService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        
    }
    
    public void guardar() throws SQLException {
        String query;
        /*if(indicador.getIdIndicador()==null){//Agregar nueva pregunta
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
        */
    }

    boolean getElUsuarioDioParaGuardar() {
        return this.elUsuarioDioParaGuardar;
    }
}
