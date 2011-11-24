/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.catalogo;

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
public class EdicionAlumnoService {
    SQLExecutor execute;
    Alumno alumno;
    boolean elUsuarioDioParaGuardar;   
    
    EdicionAlumnoService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idUsuario = req.getParameter("idUsuario")!=null ? new Integer(req.getParameter("idUsuario")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(req.getParameter("hdnIdUsuario").equals("") ? null : new Integer(req.getParameter("hdnIdUsuario")));
            usuario.setUsuario(req.getParameter("txtLogin").trim());
            usuario.setPassword(req.getParameter("txtPWD").trim().equals("") ? null : req.getParameter("txtPWD"));
            usuario.setNombre(req.getParameter("txtNombre").trim());
            usuario.setApaterno(req.getParameter("txtPaterno").trim());
            usuario.setAmaterno(req.getParameter("txtPaterno").trim());
            usuario.setEmail(req.getParameter("txtEmail").trim());
            usuario.setActivo(req.getParameter("slcActivo"));
            System.out.println("Le di guardar y el idusuario objeto = "+usuario.getIdUsuario());
            
            alumno = new Alumno(new Integer(req.getParameter("slcGrupo")),usuario);            
        }
        else{
            if(idUsuario!=null){
                execute.addParametro(1,idUsuario);
                ResultSet res = execute.executeQuery("select idgrupoalumno,idusuario,usuario,nombre,apaterno,amaterno,email,activo from dicalumno inner join dicusuario using(idusuario) where idusuario = ?");                
                res.next();
                alumno = new Alumno(res.getInt(1), new Usuario(res.getInt(2),res.getString(3),"",res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8) ));                                
            }
            else{
                alumno=null;
            }
        }
         
         
    }
    
    public synchronized void guardar() throws SQLException {
        String query;
        execute.limpiaParameros();
        if(alumno.getUsuario().getIdUsuario()==null){//Agregar nuevo alumno y usuario
            query="insert into dicusuario (usuario,password,nombre,apaterno,amaterno,activo,email) "
                    + "values "
                    + "('"+alumno.getUsuario().getUsuario()+"',md5('"+alumno.getUsuario().getPassword()+"'),'"+alumno.getUsuario().getNombre()+"','"+alumno.getUsuario().getApaterno()+"','"+alumno.getUsuario().getAmaterno()+"','"+alumno.getUsuario().getActivo()+"','"+alumno.getUsuario().getEmail()+"')";
            execute.executeUpdate(query);
            
            query="insert into dicalumno values ( (select max(idusuario) from dicusuario), "+alumno.getidGrupoAlumnos()+" )";
            execute.executeUpdate(query);
        }else{//Edicion de alumno y usuario ya existente
            
            query="update dicusuario set usuario = '"+alumno.getUsuario().getUsuario()+"', ";
            if(alumno.getUsuario().getPassword()!=null)
                query += "password = md5('"+alumno.getUsuario().getPassword()+"'), ";            
            query += "nombre = '"+alumno.getUsuario().getNombre()+"', apaterno = '"+alumno.getUsuario().getNombre()+"', ";            
            query += "apaterno = '"+alumno.getUsuario().getApaterno()+"', activo = '"+alumno.getUsuario().getActivo()+"', "
                    + "email = '"+alumno.getUsuario().getEmail()+"' where idusuario = "+alumno.getUsuario().getIdUsuario();
            
            execute.executeUpdate(query);
            
            query = "update dicalumno set idgrupoalumno = "+alumno.getidGrupoAlumnos()+" where "
                    + "idusuario = "+alumno.getUsuario().getIdUsuario();            
            execute.executeUpdate(query);
        }
        
        
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
