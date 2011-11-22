/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class Alumno {
    Integer idGrupoAlumnos;
    Usuario usuario;

    public Alumno(Integer idGrupoAlumnos, Usuario usuario) {
        this.idGrupoAlumnos = idGrupoAlumnos;
        this.usuario = usuario;
    }
    public Alumno() {
    }

    public Integer getidGrupoAlumnos() {
        return idGrupoAlumnos;
    }

    public void setidGrupoAlumnos(Integer idGrupoAlumnos) {
        this.idGrupoAlumnos = idGrupoAlumnos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
