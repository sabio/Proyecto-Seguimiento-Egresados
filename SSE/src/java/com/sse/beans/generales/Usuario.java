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
    private String usuario;
    private String password;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String email;
    private String activo;
    
    private Integer idPerfil;
    private ArrayList<Integer> permisosAsignados;

    public Usuario(Integer idUsuario, String usuario, String password, String nombre, String apaterno, String amaterno, String email, String activo) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.email = email;
        this.activo = activo;
    }
    
    
    
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
