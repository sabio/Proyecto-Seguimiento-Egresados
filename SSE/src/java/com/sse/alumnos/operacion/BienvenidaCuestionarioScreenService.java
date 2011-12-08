/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.operacion;

import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando.gomez
 */
public class BienvenidaCuestionarioScreenService {
    SQLExecutor execute;
    Integer idAsignacion;
    
    BienvenidaCuestionarioScreenService(){
        execute = new SQLExecutor();
    }

    void establecerDatos(HttpServletRequest req) {
        this.idAsignacion = new Integer(req.getParameter("idAsignacion"));
                
    }

    String getNombreCuestionario() throws SQLException{
        String query = "select cuestionario from diccuestionario "+
                        "inner join tblasignacioncuestionario using (idcuestionario) "+
                        "where idasignacioncuestionario=?";
        execute.addParametro(1,this.idAsignacion);
        ResultSet res = execute.executeQuery(query);
        res.next();
        return res.getString(1);        
    }
}
