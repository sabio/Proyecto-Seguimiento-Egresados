/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.operacion;

import com.sse.beans.generales.CuestionariosPendientes;
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
public class PrincipalAlumnosService {
    SQLExecutor execute; 
    Usuario usuario;
    
    PrincipalAlumnosService(){
        execute = new SQLExecutor();
    }
    
    void establecerDatos(HttpServletRequest req) {
        this.usuario = (Usuario)req.getSession(false).getAttribute("usuario");
    }
    
    ArrayList<CuestionariosPendientes> getCuestionariosPendientes() throws SQLException{
        ArrayList<CuestionariosPendientes> cuestionarios = new ArrayList<CuestionariosPendientes>(); 
        CuestionariosPendientes cuestionario;
        String query = "select idasignacioncuestionario,idcuestionario,cuestionario, fechainicio,fechafin " +
                        "from tblasignacioncuestionario "+
                        "inner join dicalumno using (idgrupoalumno) "+
                        "inner join diccuestionario using (idcuestionario) "+
                        "where dicalumno.idusuario = "+this.usuario.getIdUsuario()+" and "+
                        "fechainicio <= curdate() and fechafin>=curdate()";
        System.out.println(query);
        ResultSet res = execute.executeQuery(query);
        
        ResultSet res2;
        while(res.next()){
            query = "select count(1) from tblaplicacioncuestionario where idasignacioncuestionario = "+res.getInt(1) +" and fechafin is null";
            res2 = execute.executeQuery(query);
            res2.next();
            
            if(res2.getInt(1)==0){
                cuestionarios.add(new CuestionariosPendientes(res.getInt(2),res.getString(3),res.getString(4),res.getString(5)  ));
                
            }
            
        }
        
        return cuestionarios;
    }

    
    
}