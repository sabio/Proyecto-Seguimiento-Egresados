package com.sse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLExecutor {
    Connection con;
    //Statement s;
    PreparedStatement stmt;
    ResultSet res;
    HashMap<Integer,Object> mapa;

    public SQLExecutor(){
        con = Conexion.getConnection();
    }
    
    public void addParametro(int orden,Object valor){
        if(mapa==null)
            mapa = new HashMap();
        mapa.put(new Integer(orden), valor);
    }

    public ResultSet executeQuery(String query) throws SQLException{        
        stmt = con.prepareStatement(query);
        if(mapa!=null){
            agregaLosParametros(stmt);
        }
        res = stmt.executeQuery();
        return res;
    }
    
    private void agregaLosParametros(PreparedStatement stmt) throws SQLException {
        Iterator it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            //System.out.println(pairs.getKey() + " = " + pairs.getValue());
            int orden = (Integer)pairs.getKey();
            Object param = pairs.getValue();
            if(param instanceof Integer)
                stmt.setInt(orden, (Integer)param);
            else if(param instanceof String)
                stmt.setString(orden, (String)param);
            else if(param instanceof Long)
                stmt.setLong(orden, (Long)param);
            else if(param instanceof Float)
                stmt.setFloat(orden, (Float)param);
            else{
                throw new SQLException("Tipo de parametro no configurado");
            }
            
            
        }

    }
    
    public void commit(){
        
    }

    public void cerrarConexion() {
        try {
            res.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Ocurrio un error al intentar cerrar la conexion");
            Logger.getLogger(SQLExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiaParameros() {
        this.mapa=null;
    }

    
}
