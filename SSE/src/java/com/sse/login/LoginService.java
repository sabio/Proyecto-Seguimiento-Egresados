package com.sse.login;

import com.sse.beans.generales.Usuario;
import com.sse.dao.SQLExecutor;
import java.lang.Integer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

    Usuario generaObjetoUsuario(Integer idUsuario) throws SQLException {
        Usuario usuario = new Usuario();
        String query = "select idpermiso,idperfil from tblpermisosperfil where idperfil=(select idperfil from dicadministrativo where idusuario=?)";
        ResultSet res;
        ArrayList<Integer> permisosAsignados = new ArrayList<Integer>();        
        
        usuario.setIdPerfil(idUsuario);        
        execute.limpiaParameros();
        execute.addParametro(1, idUsuario);
        res = execute.executeQuery(query);        
        
        if(res.next()){
            usuario.setIdPerfil(res.getInt("idperfil"));   
            permisosAsignados.add(res.getInt("idpermiso"));
            while(res.next()){
                permisosAsignados.add(res.getInt("idpermiso"));
            }
        }        
        usuario.setPermisosAsignados(permisosAsignados);
        
        return usuario;
    }

    @Override
    protected void finalize() throws Throwable {
        execute.cerrarConexion();
    }
    
    
}
