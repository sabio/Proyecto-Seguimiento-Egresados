package com.sse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SQLExecutor {
    Connection con;
    //Statement s;
    //PreparedStatement stmt;
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
        PreparedStatement stmt=null;
        stmt = con.prepareStatement(query);
        if(mapa!=null){
            agregaLosParametros(stmt);
        }
        return stmt.executeQuery();
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

    
}
