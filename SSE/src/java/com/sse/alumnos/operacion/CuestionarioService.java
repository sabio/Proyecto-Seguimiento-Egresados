/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.alumnos.operacion;

import com.sse.beans.generales.Pregunta;
import com.sse.beans.generales.Usuario;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author armando
 */
public class CuestionarioService {
    SQLExecutor execute;
    Integer idAsignacion;
    Integer idUsuario;
    Integer idAplicacionCuestionario;
    /*
     0=Nada
     1=Guardar y continuar despues
     2=Terminar
     */
    int opcion;
    
    CuestionarioService(){
        execute = new SQLExecutor();
    }
    void establecerDatos(HttpServletRequest req) throws SQLException{
        ResultSet res;
        String query;
        this.idAsignacion = new Integer(req.getParameter("idAsignacion"));
        this.idUsuario = ((Usuario)req.getSession(false).getAttribute("usuario")).getIdUsuario();
        
        if(req.getParameter("accion")==null)
            this.opcion=0;
        else 
            this.opcion= Integer.parseInt(req.getParameter("accion"));        
        
        if(this.opcion==0){
            //Insertamos en BD que ya se empezo el cuestionario, en caso de que se haya emoe
            query="select count(1) from tblaplicacioncuestionario where idasignacioncuestionario="+this.idAsignacion+" and idusuario="+this.idUsuario;
            res = execute.executeQuery(query);
            res.next();
            
            if(res.getInt(1)==0){
                query="insert into tblaplicacioncuestionario "
                        + "(idasignacioncuestionario,idusuario,fechainicio)"
                        + "values "
                        + "("+this.idAsignacion+","+this.idUsuario+",now())";
                execute.executeUpdate(query);
                execute.commit();
            }
                        
        }
        
        query="select idAplicacionCuestionario from tblaplicacioncuestionario where idasignacioncuestionario="+this.idAsignacion+" and idusuario="+this.idUsuario;            
        res = execute.executeQuery(query);
        res.next();        
        this.idAplicacionCuestionario = res.getInt(1);

    }

    ArrayList<Pregunta> getPreguntas() throws SQLException{
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        String query = "select dicpregunta.idpregunta,dicpregunta.pregunta, dicpregunta.idtipopregunta, "+
                        "dicpregunta.idindicador, respuestaString,respuestaInt "+
                        "from tblasignacioncuestionario "+
        "inner join tblcuestionariopreguntas on (tblcuestionariopreguntas.idcuestionario=tblasignacioncuestionario.idcuestionario) "+
        "inner join dicpregunta on (dicpregunta.idpregunta=tblcuestionariopreguntas.idpregunta) "+
        "left join tblaplicacioncuestionario on (tblaplicacioncuestionario.idasignacioncuestionario=tblasignacioncuestionario.idasignacioncuestionario) "+
        "left join tblrespuesta on (tblrespuesta.idpregunta=dicpregunta.idpregunta and tblrespuesta.idaplicacioncuestionario=tblaplicacioncuestionario.idaplicacioncuestionario) "+
        "where tblasignacioncuestionario.idasignacioncuestionario="+this.idAsignacion+
        " and dicpregunta.activo='S' "+
        "order by tblcuestionariopreguntas.orden";
        ResultSet res = execute.executeQuery(query);
        
//public Pregunta(Integer idPregunta, String pregunta, Integer idTipoPregunta, Integer idIndicador, String activo) {        
        while(res.next()){
            preguntas.add(new Pregunta(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),"S",res.getString(5)!=null ? res.getString(5).replace("`", "'") : res.getString(5)  ,res.getInt(6) ));
        }
        
        return preguntas;
    }

    void guardarCuestionarioPaContinuarDespues(HttpServletRequest req) throws SQLException{
        guardarCuestionario(req);
    }
        
    void guardarCuestionario(HttpServletRequest req) throws SQLException{    
        Enumeration enu = req.getParameterNames();
        String query="";
        String resultado;
        String idPregunta;
        while(enu.hasMoreElements()){
            String parametro = (String)enu.nextElement();
            
            if(parametro.startsWith("pregunta")){
                String[] paramSplit = parametro.split("-");
                resultado = req.getParameter(parametro);
                idPregunta = paramSplit[2];
                
                if(paramSplit[1].equals("1")){//idTipoPregunta es de Respuesta Abierta
                    resultado = resultado.replace("'", "`");
                    query="insert into tblrespuesta (idaplicacioncuestionario,idpregunta,respuestaString,respuestaInt) "
                            + "values "
                            + "("+this.idAplicacionCuestionario+","+idPregunta+",'"+resultado+"',null) "
                            + "on duplicate key update "
                            + "respuestaString='"+resultado+"', respuestaInt=null ";                                                
                }
                else {//idTipoPregunta es de opciones del 1 al 5, o es el de opciones Si y No
                    query="insert into tblrespuesta (idaplicacioncuestionario,idpregunta,respuestaString,respuestaInt) "
                            + "values "
                            + "("+this.idAplicacionCuestionario+","+idPregunta+",null,"+resultado+") "
                            + "on duplicate key update "
                            + "respuestaString=null, respuestaInt="+resultado; 
                }
                
                execute.executeUpdate(query);
                execute.commit();
            }            
        }
    }

    void guardarYTerminar(HttpServletRequest req) throws SQLException{
        guardarCuestionario(req);
        String query="update tblaplicacioncuestionario set fechafin = now()"
                + " where idAplicacionCuestionario="+this.idAplicacionCuestionario+" and idusuario="+this.idUsuario;
        execute.executeUpdate(query);
        execute.commit();
    }

    String getNombreCuestionario() throws SQLException{
        String query = "select cuestionario from diccuestionario "+
                        "inner join tblasignacioncuestionario using (idcuestionario) "+
                        "where idasignacioncuestionario="+this.idAsignacion;
       
        ResultSet res = execute.executeQuery(query);
        res.next();
        return res.getString(1);        
    }
    
}
