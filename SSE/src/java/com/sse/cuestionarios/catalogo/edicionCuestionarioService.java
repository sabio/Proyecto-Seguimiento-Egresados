/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.cuestionarios.catalogo;

import com.sse.beans.generales.Cuestionario;
import com.sse.beans.generales.Pregunta;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author armando
 */
public class edicionCuestionarioService {
    SQLExecutor execute;
    Cuestionario cuestionario;
    boolean elUsuarioDioParaGuardar;   
    boolean seGuardoUnaConfiguracionNueva;
    
    edicionCuestionarioService(){
        execute = new SQLExecutor();
    }
    
    public void establecerDatos(HttpServletRequest req) throws SQLException {
        Integer idCuestionario = req.getParameter("idCuestionario")!=null && !req.getParameter("idCuestionario").equals("") ? new Integer(req.getParameter("idCuestionario")) : null;
        elUsuarioDioParaGuardar = req.getParameter("guardar")!=null ? true:false;
        
        if(elUsuarioDioParaGuardar){
            this.cuestionario = new Cuestionario();
            this.cuestionario.setIdCuestionario(idCuestionario);
            this.cuestionario.setCuestionario(req.getParameter("txtCuestionario"));        
            this.cuestionario.setActivo(req.getParameter("slcActivo"));
        }
        else{
            
            if(idCuestionario!=null){
                execute.addParametro(1,idCuestionario);
                ResultSet res = execute.executeQuery("select idcuestionario,cuestionario,activo from diccuestionario where idcuestionario = ?");
                res.next();
                cuestionario = new Cuestionario(res.getInt(1),res.getString(2),res.getString(3));
            }
            else{
                cuestionario=null;
            }             
             
        }
         
        execute.limpiaParameros(); 
    }
    
    public synchronized void guardar() throws SQLException {
        String query="";
        ResultSet res;
        
        
        if(cuestionario.getIdCuestionario()==null){//Agregar nuevo cuestionario
            seGuardoUnaConfiguracionNueva=true;
            res = execute.executeQuery("SELECT max(idcuestionario) FROM diccuestionario");
            res.next();
            
            Integer nextId=res.getInt(1);
            if(nextId==null || nextId==0) 
                nextId=1;
            else
                nextId++;
            
            cuestionario.setIdCuestionario(nextId);
            
            query="insert into diccuestionario (idcuestionario,cuestionario,activo) values (?,?,?)";
            execute.addParametro(1, cuestionario.getIdCuestionario());
            execute.addParametro(2, cuestionario.getCuestionario());
            execute.addParametro(3, cuestionario.getActivo());
        }else{//Edicion de pregunta ya existente
            seGuardoUnaConfiguracionNueva=false;
            query="update diccuestionario set cuestionario=?, activo=? where idcuestionario=?";            
            execute.addParametro(1, cuestionario.getCuestionario());
            execute.addParametro(2, cuestionario.getActivo());
            execute.addParametro(3, cuestionario.getIdCuestionario());
        }        
        execute.executeUpdate(query);
        execute.commit();
        execute.limpiaParameros();
        
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    boolean getElUsuarioDioParaGuardar() {
        return this.elUsuarioDioParaGuardar;
    }

    ArrayList<Pregunta> getPreguntasDisponibles() throws SQLException{
        if(this.cuestionario==null) return null;
        
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        String query = "select idpregunta,pregunta,idTipoPregunta,tipoPregunta,idindicador,indicador from dicpregunta "+
                        "inner join dictipopregunta using(idtipopregunta) "+
                        "inner join dicindicador using (idindicador) "+
                        "where dicpregunta.activo='S' and idpregunta not in "+
                        "(select idpregunta from tblcuestionariopreguntas where idcuestionario = "+this.cuestionario.getIdCuestionario()+")"+
                        "order by pregunta";
        ResultSet res = execute.executeQuery(query);
        
        Pregunta pregunta;
        while(res.next()){
            pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),"S");
            preguntas.add(pregunta);
        }
        
        return preguntas.isEmpty() ? null : preguntas; 
    }

    
    ArrayList<Pregunta> getPreguntasAsignadas() throws SQLException{
        if(this.cuestionario==null) return null;
        
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        String query = "select idpregunta,pregunta,idTipoPregunta,tipoPregunta,idindicador,indicador from tblcuestionariopreguntas "+
                        "inner join dicpregunta using (idpregunta) "+
                        "inner join dictipopregunta using(idtipopregunta) "+
                        "inner join dicindicador using (idindicador) "+
                        "where dicpregunta.activo='S' and idcuestionario = "+this.cuestionario.getIdCuestionario()+
                        " order by orden";
        ResultSet res = execute.executeQuery(query);
        
        Pregunta pregunta;
        while(res.next()){
            pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),"S");
            preguntas.add(pregunta);
        }
        return preguntas.isEmpty() ? null : preguntas;        
    }

    void asignarPregunta(Integer idPregunta) throws SQLException{
        String query = "select max(orden) from tblcuestionariopreguntas where idcuestionario = "+this.cuestionario.getIdCuestionario();
        ResultSet res = execute.executeQuery(query);
        res.next();
        Integer orden = res.getInt(1);
        if(orden==null) orden = 1;
        else    orden++;
        
        query="insert into tblcuestionariopreguntas values "
                + "("+this.cuestionario.getIdCuestionario()+","+idPregunta+","
                + orden+" )";
        System.out.println(query);
        execute.executeUpdate(query);
    }

    void bajarPregunta(Integer idPregunta) throws SQLException{
        String query = "SELECT idpregunta,orden FROM tblcuestionariopreguntas WHERE "+
                        "idcuestionario = "+this.cuestionario.getIdCuestionario()+" and "+
                        "orden > (select orden from tblcuestionariopreguntas where idpregunta="+idPregunta+" and idcuestionario="+this.cuestionario.getIdCuestionario()+") "+
                        " order by orden limit 1";
        ResultSet res = execute.executeQuery(query);
        res.next();
        
        query = "update tblcuestionariopreguntas set orden = "+res.getInt(2)+" where idcuestionario="+this.cuestionario.getIdCuestionario()+
                " and idpregunta = "+idPregunta;
        execute.executeUpdate(query);
        
        query = "update tblcuestionariopreguntas set orden = "+(res.getInt(2)-1)+" where idcuestionario="+this.cuestionario.getIdCuestionario()+
                " and idpregunta = "+res.getInt(1);
        execute.executeUpdate(query);
        execute.commit();
    }

    void subirPregunta(Integer idPregunta) throws SQLException{
        String query = "SELECT idpregunta,orden FROM tblcuestionariopreguntas WHERE "+
                        "idcuestionario = "+this.cuestionario.getIdCuestionario()+" and "+
                        "orden < (select orden from tblcuestionariopreguntas where idpregunta="+idPregunta+" and idcuestionario="+this.cuestionario.getIdCuestionario()+")"+
                        " order by orden desc limit 1";
        ResultSet res = execute.executeQuery(query);
        res.next();
        
        query = "update tblcuestionariopreguntas set orden = "+res.getInt(2)+" where idcuestionario="+this.cuestionario.getIdCuestionario()+
                " and idpregunta = "+idPregunta;
        execute.executeUpdate(query);
        
        query = "update tblcuestionariopreguntas set orden = "+(res.getInt(2)+1)+" where idcuestionario="+this.cuestionario.getIdCuestionario()+
                " and idpregunta = "+res.getInt(1);
        execute.executeUpdate(query);
        execute.commit();
    }

    void eliminarPregunta(Integer idPregunta) throws SQLException{
        String query = "delete from tblcuestionariopreguntas where idpregunta="+idPregunta+" and " +
                       "idcuestionario="+this.cuestionario.getIdCuestionario();
        execute.executeUpdate(query);
        
        query="SELECT idpregunta,orden FROM tblcuestionariopreguntas WHERE "+
                "idcuestionario = "+this.cuestionario.getIdCuestionario()+
                " order by orden";
        
        ResultSet res = execute.executeQuery(query);
        
        query="update tblcuestionariopreguntas set orden = ? where idpregunta=? and idcuestionario="+this.cuestionario.getIdCuestionario();
        int i=1;
        while(res.next()){
            execute.addParametro(1, i);
            execute.addParametro(2, res.getInt(1));
            execute.executeUpdate(query);
            i++;
        }              
        execute.commit();
        execute.limpiaParameros();
    }
    
}

