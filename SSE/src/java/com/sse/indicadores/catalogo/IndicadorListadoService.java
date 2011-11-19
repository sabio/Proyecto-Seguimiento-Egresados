package com.sse.indicadores.catalogo;

import com.sse.beans.generales.Pregunta;
import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


public class IndicadorListadoService {
    SQLExecutor execute;
    
    IndicadorListadoService(){
        execute = new SQLExecutor();
    }
    
    public ArrayList<Pregunta> getListadoPreguntas(HttpServletRequest req) throws SQLException{
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select * from dicindicador");
        
        Pregunta pregunta;
        while(res.next()){
            pregunta = new Pregunta(res.getInt(1),res.getString(2),res.getString(3));
            preguntas.add(pregunta);
        }
                
        return preguntas;
    }
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    void eliminarPregunta(Integer idPreguntaAEliminar) throws SQLException{
        execute.addParametro(1, idPreguntaAEliminar);
        execute.executeUpdate("delete from dicindicador where idindicador=?");
        execute.commit();
    }
    
}
