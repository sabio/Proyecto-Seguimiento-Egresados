package com.sse.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author armando
 */
public class Conexion {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Context c = new InitialContext();
            DataSource ds = (DataSource)c.lookup("java:comp/env/sse");
            con = ds.getConnection();
        }catch(Exception e){
            e.printStackTrace();
            con = null;
        }
        finally{
            return con;
        }
    }
}
