/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.asignacion;

import com.sse.beans.generales.AsignacionCuestionario;
import com.sse.beans.generales.Cuestionario;
import com.sse.beans.generales.GrupoAlumnos;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Integer idAsignacionCuestionario = req.getParameter("idAsignacionCuestionario")!=null && !req.getParameter("idAsignacionCuestionario").equals("") ? new Integer(req.getParameter("idAsignacionCuestionario")) : null;
        this.elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            asignacionCuestionario = new AsignacionCuestionario();
            asignacionCuestionario.setIdAsignacionCuestionario(idAsignacionCuestionario);
            asignacionCuestionario.setIdCuestionario(new Integer(req.getParameter("slcCuestionario")));
            asignacionCuestionario.setIdGrupoAlumnos(new Integer(req.getParameter("slcGrupo")));
            asignacionCuestionario.setFechaInicio(req.getParameter("txtFechaInicio"));
            asignacionCuestionario.setFechaFin(req.getParameter("txtFechaFin"));
            asignacionCuestionario.setActivo(req.getParameter("slcActivo"));
            
        }
        else{
            if(idAsignacionCuestionario!=null){
                execute.addParametro(1,idAsignacionCuestionario);
                String query =  "select idasignacioncuestionario,idcuestionario, cuestionario, idgrupoalumno, grupoalumno, " +
                        "date_format( fechainicio , '%Y/%m/%d %I:%i %p' )," +
                        "date_format( fechafin , '%Y/%m/%d %I:%i %p' ), " +
                        "tblasignacioncuestionario.activo "+
                        "from tblasignacioncuestionario "+
                        "inner join diccuestionario using (idcuestionario) "+
                        "inner join dicgrupoalumnos using (idgrupoalumno) "+
                        "where idasignacioncuestionario = ?";
                ResultSet res = execute.executeQuery(query);                
                res.next();
                asignacionCuestionario = new AsignacionCuestionario(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8));
            }
            else{
                idAsignacionCuestionario=null;
            }
        }
        
    }
    
    public void guardar() throws SQLException {
        String query;
        if(asignacionCuestionario.getIdAsignacionCuestionario()==null){//Agregar nueva asignacion
            query="insert into tblasignacioncuestionario (idcuestionario,idgrupoalumno,fechainicio,fechafin,activo) values "
                    + "("+asignacionCuestionario.getIdCuestionario()+","+asignacionCuestionario.getIdGrupoAlumnos()+","
                    + " STR_TO_DATE( '"+asignacionCuestionario.getFechaInicio()+"', '%Y/%m/%d %I:%i %p' ),"
                    + " STR_TO_DATE( '"+asignacionCuestionario.getFechaFin()+"', '%Y/%m/%d %I:%i %p' ), "
                    + " '"+asignacionCuestionario.getActivo()+"') ";
            
        }else{//Edicion de pregunta ya existente            
            query="update tblasignacioncuestionario set idcuestionario = "+asignacionCuestionario.getIdCuestionario()+", "
                    + "idgrupoalumno = "+asignacionCuestionario.getIdGrupoAlumnos()+", "
                    + "fechainicio=STR_TO_DATE( '"+asignacionCuestionario.getFechaInicio()+"', '%Y/%m/%d %I:%i %p' ), "
                    + "fechafin=STR_TO_DATE( '"+asignacionCuestionario.getFechaFin()+"', '%Y/%m/%d %I:%i %p' ), "
                    + "activo = '"+asignacionCuestionario.getActivo()+"' "
                    + " where IdAsignacionCuestionario = "+asignacionCuestionario.getIdAsignacionCuestionario();
        }
        System.out.println("query = "+query);
        execute.executeUpdate(query);
        execute.commit();
        
    }

    boolean getElUsuarioDioParaGuardar() {
        return this.elUsuarioDioParaGuardar;
    }

    ArrayList<Cuestionario> getListadoCuestionarios() throws SQLException {
        ArrayList<Cuestionario> cuestionarios = new ArrayList<Cuestionario>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("SELECT idcuestionario,cuestionario FROM diccuestionario where activo = 'S' order by cuestionario");
        while(res.next()){
            cuestionarios.add(new Cuestionario(res.getInt(1),res.getString(2)));
        }        
        return cuestionarios;
    }

    ArrayList<GrupoAlumnos> getListadoGruposAlumnos()  throws SQLException{
        ArrayList<GrupoAlumnos> gruposAlumnos = new ArrayList<GrupoAlumnos>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select idgrupoalumno,grupoalumno from dicgrupoalumnos order by grupoalumno");
        while(res.next()){
            gruposAlumnos.add(new GrupoAlumnos(res.getInt(1),res.getString(2)));
        }        
        return gruposAlumnos;
    }
}
