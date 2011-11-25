/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.asignacion;

import com.sse.beans.generales.AsignacionCuestionario;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author armando.gomez
 */
public class ListadoAsignacionCuestionariosService {
    SQLExecutor execute;
    
    ListadoAsignacionCuestionariosService(){
        execute = new SQLExecutor();
    }
    public ArrayList<AsignacionCuestionario> getListadoAsignaciones() throws SQLException{
        ArrayList<AsignacionCuestionario> listado = new ArrayList<AsignacionCuestionario>();        
        execute.limpiaParameros();
        String query =  "select idasignacioncuestionario,idcuestionario, cuestionario, idgrupoalumno, grupoalumno, " +
                        "date_format( fechainicio , '%Y/%m/%d %I:%i %p' )," +
                        "date_format( fechafin , '%Y/%m/%d %I:%i %p' ), " +
                        "tblasignacioncuestionario.activo "+
                        "from tblasignacioncuestionario "+
                        "inner join diccuestionario using (idcuestionario) "+
                        "inner join dicgrupoalumnos using (idgrupoalumno) ";
        
        ResultSet res = execute.executeQuery(query);
        
        AsignacionCuestionario asignacionCuestionario;
        while(res.next()){
            asignacionCuestionario = new AsignacionCuestionario(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
            listado.add(asignacionCuestionario);
        }
        
        return listado;
    }

    void eliminarAsignacion(Integer idAsignacionAEliminar) throws SQLException{
        execute.addParametro(1, idAsignacionAEliminar);        
        execute.executeUpdate("delete from tblasignacioncuestionario where idasignacioncuestionario=?");
        execute.commit();
    }
    
}
