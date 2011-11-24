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

public class EdicionGrupoAlumnoService {
    SQLExecutor execute;    
    boolean elUsuarioDioParaGuardar;   
    GrupoAlumnos grupo;
    
    EdicionGrupoAlumnoService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idGrupoAlumno = req.getParameter("idGrupoAlumno")!=null && !req.getParameter("idGrupoAlumno").equals("") ? new Integer(req.getParameter("idGrupoAlumno")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){            
            grupo = new GrupoAlumnos(idGrupoAlumno,req.getParameter("txtNombre"));
        }
        else{
            if(idGrupoAlumno!=null){
                execute.addParametro(1,idGrupoAlumno);
                ResultSet res = execute.executeQuery("select grupoalumno from dicgrupoalumnos where idgrupoalumno = ?");                
                res.next();
                grupo = new GrupoAlumnos(idGrupoAlumno, res.getString(1));
            }
            else{
                grupo=null;
            }
        }
         
         
    }
    
    public synchronized void guardar() throws SQLException {
        String query;
        execute.limpiaParameros();
        if(grupo.getIdGrupoAlumnos()==null){//Agregar nuevo grupo
            query="insert into dicgrupoalumnos (grupoalumno) "
                    + "values "
                    + "('"+grupo.getGrupoAlumnos()+"')";
            
        }else{//Edicion de grupo ya existente
            
            
            
            query = "update dicgrupoalumnos set grupoalumno = '"+grupo.getGrupoAlumnos()+"' where "
                    + "idgrupoalumno = "+grupo.getIdGrupoAlumnos();                        
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
    
    ArrayList<GrupoAlumnos> getGruposAlumnos() throws SQLException {
        ArrayList<GrupoAlumnos> grupoAlumnos = new ArrayList<GrupoAlumnos>(); 
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idgrupoalumno,grupoalumno from dicgrupoalumnos order by grupoalumno");
        while(res.next()){
            grupoAlumnos.add(new GrupoAlumnos(res.getInt(1),res.getString(2)));
        }        
        return grupoAlumnos;
    }
}
