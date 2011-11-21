package com.sse.indicadores.catalogo;

import com.sse.beans.generales.Indicador;
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
    
    public ArrayList<Indicador> getListadoIndicadores(HttpServletRequest req) throws SQLException{
        ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
        execute.limpiaParameros();
        ResultSet res = execute.executeQuery("select * from dicindicador");
        
        Indicador indicador;
        while(res.next()){
            indicador = new Indicador(res.getInt(1),res.getString(2),res.getString(3));
            indicadores.add(indicador);
        }
                
        return indicadores;
    }
    
    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }

    void eliminarIndicador(Integer idIndicadorAEliminar) throws SQLException{
        execute.addParametro(1, idIndicadorAEliminar);
        execute.executeUpdate("delete from dicindicador where idindicador=?");
        execute.commit();
    }
    
}
