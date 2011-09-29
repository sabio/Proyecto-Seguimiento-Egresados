package com.sse.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
                System.out.println("jdbc:mysql://"+host+":"+puerto+"/"+nombreBD+"    "+usuario+"   "+password);
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
                    System.out.println(urlProperties);
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
                resp = false;                
        }
        return resp;
    }

}













/*


public class ConnectionJDBC {	
	
	private String bdName = "";
    private String userName = "";
    private String userPass = "";
    private String host = "";
    private String port = "";
    private Long timeOutConnection = 60l;
	
	public Connection getConection() {
	    Connection con = null;
	    Context initCtx = null;
	    DataSource ds = null;
	    //PreparedStatement ps;
	    	    
	    
	    try {
	       // obtencion del ambiente del context
	      // busqueda del datasource por nombre
	      // para este caso "jdbc/mydatasourcejndi".
	      initCtx = new InitialContext(); //Se quita para trabajar con driver
	      //initCtx.getNameInNamespace()
	      ds = (DataSource) initCtx.lookup("jdbc/sic_o");//Se quita para trabajar con driver
	      //ds = (DataSource) initCtx.lookup("jdbc/sic_o_qa");
	      con = ds.getConnection();//Se quita para trabajar con driver	    	
	      //con = DriverManager.getConnection("jdbc:oracle:thin:venus:venusdes-0707@gdl26:1521:dvlatala");

	      /*if(loadConnectionProperties()){
	    	  String conexion = String.format("jdbc:oracle:thin:%s/%s@%s:%s:%s", 
					  						  userName, userPass, host, port, bdName);	    	  
	    	  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    	  con = DriverManager.getConnection(conexion);
	      }
	      */
              /*
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (con != null) {
	        try {
	        	//con.close();
	        	//initCtx.close();
	        	//System.out.println("ConnectionJDBC.getConection:finally: objecto:" + this);
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
		return con;
	  }
	
	
	private boolean loadConnectionProperties() throws IOException{
		Properties prope = new Properties();
		boolean resp = true;
		try {
			URL url = getClass().getResource("/");
			if(url != null){
				String urlProperties = url.getPath();
				urlProperties += "sic_connect.properties";
				
				//System.out.println(urlProperties);
				
				File fileProp = new File(urlProperties);				

				if(fileProp != null && fileProp.exists()){
					prope.load(new FileInputStream(fileProp));
					bdName = prope.getProperty("sic.connect.name.bd");
					if(bdName == null && bdName.length() <= 0){
						return false;
					}
					
				    userName = prope.getProperty("sic.connect.name.user");
				    if(userName == null && userName.length() <= 0){
						return false;
					}
				    
				    userPass = prope.getProperty("sic.connect.user.pass");
				    if(userPass == null && userPass.length() <= 0){
						return false;
					}
				    
				    host = prope.getProperty("sic.connect.host");
				    if(host == null && host.length() <= 0){
						return false;
					}
				    
				    port = prope.getProperty("sic.connect.port");
				    if(port == null && port.length() <= 0){
						return false;
					}
				    
				    String timeOut = prope.getProperty("sic.connect.timeoutsession");
				    setTimeOutConnection(timeOut);				   
				}else{
					resp = false;
					System.err.println("Asegurese de que el archivo \"sic_connect.properties\" existe en la ruta del perfil del WebSphere.");
				}
			}						
		} catch (IOException e1) {
			resp = false;
			System.err.println("Problemas al leer los elementos de sic_connect.properties del perfil del WebSphere");
			throw e1;
		}
		return resp;
	}

	public Long getTimeOutConnection(){
		return timeOutConnection;
	}
	
	private void setTimeOutConnection(String time){
		if(time != null && time.length() > 0){
			Long timeTotal = Long.parseLong(time);
			timeOutConnection = timeTotal;
		}else{
			timeOutConnection = 60l;
		}
	}
	
}
*/