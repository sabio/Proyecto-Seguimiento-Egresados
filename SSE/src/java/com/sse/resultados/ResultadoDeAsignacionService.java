/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.resultados;

import com.sse.beans.generales.AsignacionCuestionario;
import com.sse.beans.generales.Indicador;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class ResultadoDeAsignacionService {
    SQLExecutor execute;
    Integer idAsignacion;
    
    ResultadoDeAsignacionService(){
        execute = new SQLExecutor();
    }

    void establecerDatos(HttpServletRequest req) {
        this.idAsignacion = req.getParameter("idAsignacion")!=null ? new Integer(req.getParameter("idAsignacion")) : null;
    }

    ArrayList<Integer> getListadoIdsIndicadoresUsadasEnLaAsignacion() throws SQLException{
        if(this.idAsignacion==null) return null;
        
        ArrayList<Integer> indicadores = new ArrayList<Integer>();
        String query = "select distinct idindicador from tblrespuesta "+
        "inner join tblaplicacioncuestionario on (tblrespuesta.idaplicacioncuestionario=tblaplicacioncuestionario.idaplicacioncuestionario) "+
        "inner join dicpregunta on (tblrespuesta.idpregunta=dicpregunta.idpregunta) "+
        "where tblaplicacioncuestionario.idasignacioncuestionario = "+this.idAsignacion+" and "+
        "idindicador is not null and  "+
        "(tblaplicacioncuestionario.fechafin is not null and tblaplicacioncuestionario.fechafin!='0000-00-00 00:00:00')"; 
        ResultSet res = execute.executeQuery(query);
        while(res.next()){
            indicadores.add(res.getInt(1));
        }
        
        return indicadores;
    }

    ArrayList<AsignacionCuestionario> getListadoAsignaciones() throws SQLException {
        ArrayList<AsignacionCuestionario>  asignaciones = new ArrayList<AsignacionCuestionario> ();
        String query = "select idasignacioncuestionario,cuestionario, grupoalumno, " +
                        "date_format( fechainicio , '%Y/%m/%d %I:%i %p' )," +
                        "date_format( fechafin , '%Y/%m/%d %I:%i %p' ) " +
                        "from tblasignacioncuestionario "+
                        "inner join diccuestionario using (idcuestionario) "+
                        "inner join dicgrupoalumnos using (idgrupoalumno) ";      
        
        if(this.idAsignacion!=null)
            query += " where idasignacioncuestionario="+this.idAsignacion;
        ResultSet res = execute.executeQuery(query);
        AsignacionCuestionario asignacionCuestionario;
        while(res.next()){
            query="select count(1) from tblaplicacioncuestionario "
                    + "where (fechafin is not null and fechafin!='0000-00-00 00:00:00') and "
                    + "idasignacioncuestionario="+res.getInt(1);
            ResultSet res2 = execute.executeQuery(query);
            res2.next();
            if(res2.getInt(1)>0){
                asignacionCuestionario = new AsignacionCuestionario(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
                asignaciones.add(asignacionCuestionario);
            }
        }
        return asignaciones.isEmpty() ? null : asignaciones;
    }

    Integer getNumeroDeAlumnosQueHanContestadoLaAsignacion() throws SQLException{
        if(this.idAsignacion==null) return null;
        String query = "SELECT count(1) FROM tblasignacioncuestionario "+
                       "inner join tblaplicacioncuestionario using (idasignacioncuestionario) "+
           "where (tblaplicacioncuestionario.fechafin is not null and tblaplicacioncuestionario.fechafin!='0000-00-00 00:00:00') "+
           "and idasignacioncuestionario="+this.idAsignacion;
        
        ResultSet res = execute.executeQuery(query);
        res.next();
        return res.getInt(1);
    }
}
