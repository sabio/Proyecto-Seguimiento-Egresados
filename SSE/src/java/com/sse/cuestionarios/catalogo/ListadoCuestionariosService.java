/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.catalogo;

import com.sse.beans.generales.Cuestionario;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author armando
 */
public class ListadoCuestionariosService {
    SQLExecutor execute;
    
    ListadoCuestionariosService(){
        execute = new SQLExecutor();
    }
    
    public ArrayList<Cuestionario> getListadoCuestionarios(HttpServletRequest req) throws SQLException{
        ArrayList<Cuestionario> cuestionarios = new ArrayList<Cuestionario>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idcuestionario,cuestionario,activo from diccuestionario");
        
        Cuestionario cuestionario;
        while(res.next()){            
            cuestionario = new Cuestionario(res.getInt(1),res.getString(2),res.getString(3));
            cuestionarios.add(cuestionario);
        }
                
        return cuestionarios;
    }
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    void eliminarCuestionario(Integer idCuestionarioAEliminar) throws SQLException{
        execute.addParametro(1, idCuestionarioAEliminar);
        execute.executeUpdate("delete from diccuestionario where idcuestionario=?");
        execute.commit();
    }
    
}

