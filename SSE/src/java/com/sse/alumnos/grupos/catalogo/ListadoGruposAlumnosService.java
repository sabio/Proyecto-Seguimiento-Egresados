/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.grupos.catalogo;

import com.sse.beans.generales.Alumno;
import com.sse.beans.generales.GrupoAlumnos;
import com.sse.beans.generales.Usuario;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class ListadoGruposAlumnosService {
    SQLExecutor execute;
    
    ListadoGruposAlumnosService(){
        execute = new SQLExecutor();
    }
    
    public ArrayList<GrupoAlumnos> getListadoGruposAlumnos(HttpServletRequest req) throws SQLException{
        ArrayList<GrupoAlumnos> Grupos = new ArrayList<GrupoAlumnos>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idgrupoalumno,grupoalumno from dicgrupoalumnos");
        
        GrupoAlumnos grupoAlumnos;
        while(res.next()){
            grupoAlumnos = new GrupoAlumnos(res.getInt(1), res.getString(2));
            Grupos.add(grupoAlumnos);
        }
                
        return Grupos;
    }
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    void eliminarGrupoAlumno(Integer idAEliminar) throws SQLException{
        execute.addParametro(1, idAEliminar);
        execute.executeUpdate("delete from dicgrupoalumnos where idgrupoalumno=?");
        execute.commit();
    }
    
}
