package com.sse.beans.generales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author armando
 */
public class Usuario implements Serializable{
    private Integer idUsuario;
    private Integer idPerfil;
    private ArrayList<Integer> permisosAsignados;
    
    public Usuario(){
        
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Integer> getPermisosAsignados() {
        return permisosAsignados;
    }

    public void setPermisosAsignados(ArrayList<Integer> permisosAsignados) {
        this.permisosAsignados = permisosAsignados;
    }

    public String getPermisosAsignadosString(String delimitador) {
        String salida="";
        
        Iterator iter = permisosAsignados.iterator();
        while(iter.hasNext()){
            if(salida.equals(""))
                salida = iter.next()+"";
            else
                salida += delimitador+iter.next();
        }
        
        return salida;
    }
    
    
    
}
