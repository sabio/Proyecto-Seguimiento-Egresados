package com.sse.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author armando
 */
public class Conexion {
    private String host=null,usuario=null,password=null,puerto=null,nombreBD=null;
    
    public Connection getConnection(){
        Connection con=null;
        
        try{
            /*Context c = new InitialContext();
            DataSource ds = (DataSource)c.lookup("java:comp/env/sse");
            con = ds.getConnection();*/            
            
            if(cargaInfoParaLaConexion()){
                Class.forName("com.mysql.jdbc.Driver");
                //System.out.println("jdbc:mysql://"+host+":"+puerto+"/"+nombreBD+"    "+usuario+"   "+password);
                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+nombreBD, usuario, password);
            }
            
            if(con==null)
                throw new IOException("No se pudo establecer conexion a la BD");


        }catch(Exception e){
            e.printStackTrace();
            con = null;
        }
        finally{
            return con;
        }
    }



    private boolean cargaInfoParaLaConexion() {
        Properties prope = new Properties();
        boolean resp = true;
        try {
            URL url = getClass().getResource("/");
            if(url != null){
                    String urlProperties = url.getPath();
                    urlProperties += "sse.properties";
                    //System.out.println(urlProperties);
                    File fileProp = new File(urlProperties);				

                    if(fileProp != null && fileProp.exists()){
                        prope.load(new FileInputStream(fileProp));
                        host = prope.getProperty("sse.bd.host");
                        if(host == null && host.length() <= 0){
                            return false;
                        }

                        usuario = prope.getProperty("sse.bd.user");
                        if(usuario == null && usuario.length() <= 0){
                            return false;
                        }

                        puerto = prope.getProperty("sse.bd.puerto");
                        if(puerto == null && puerto.length() <= 0){
                            return false;
                        }
                        
                        nombreBD = prope.getProperty("sse.bd.nombreBD");
                        if(nombreBD == null && nombreBD.length() <= 0){
                            return false;
                        }

                        password = prope.getProperty("sse.bd.pwd");                        

                    }else{
                            resp = false;    
                            throw new IOException("No existe el archivo de propiedades del sistema");
                    }
            }						
        } catch (IOException e1) {
                e1.printStackTrace();
                resp = false;                
        }
        return resp;
    }

}