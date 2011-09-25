package com.sse.login;

import com.sse.dao.SQLExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginService {
    SQLExecutor execute;
    
    LoginService(){
        execute = new SQLExecutor();
    }
    
    public Integer isUsuarioValido(String login, String password) throws SQLException{
        execute.addParametro(1, login);
        execute.addParametro(2, password);
        
        ResultSet res = execute.executeQuery("select idusuario from dicusuario where usuario = ? and password = md5(?)");
        
        if(res.next()){
            return res.getInt("idusuario");
        }else{
            return null;
        }
            
    } 
}
