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
    Integer idGrupoAlumno;
    Usuario usuario;

    public Alumno(Integer idGrupoAlumno, Usuario usuario) {
        this.idGrupoAlumno = idGrupoAlumno;
        this.usuario = usuario;
    }
    public Alumno() {
    }

    public Integer getIdGrupoAlumno() {
        return idGrupoAlumno;
    }

    public void setIdGrupoAlumno(Integer idGrupoAlumno) {
        this.idGrupoAlumno = idGrupoAlumno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
