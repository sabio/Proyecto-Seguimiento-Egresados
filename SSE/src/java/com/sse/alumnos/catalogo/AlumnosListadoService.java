/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.catalogo;

import com.sse.beans.generales.Alumno;
import com.sse.beans.generales.Indicador;
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
public class AlumnosListadoService {
    SQLExecutor execute;
    
    AlumnosListadoService(){
        execute = new SQLExecutor();
    }
    
    public ArrayList<Alumno> getListadoAlumnos(HttpServletRequest req) throws SQLException{
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idgrupoalumno,idusuario,usuario,nombre,apaterno,amaterno,email,activo from dicalumno inner join dicusuario using(idusuario)");
        
        Alumno alumno;
        while(res.next()){
            alumno = new Alumno(res.getInt(1), new Usuario(res.getInt(2),res.getString(3),"",res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8) ));
            alumnos.add(alumno);
        }
                
        return alumnos;
    }
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    void eliminarAlumno(Integer idUsuarioAEliminar) throws SQLException{
        execute.addParametro(1, idUsuarioAEliminar);
        execute.executeUpdate("delete from dicusuario where idusuario=?");
        execute.commit();
    }
    
}
