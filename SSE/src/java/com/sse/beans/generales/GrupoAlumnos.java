/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class GrupoAlumnos {
    Integer idGrupoAlumnos;
    String grupoAlumnos;

    public GrupoAlumnos(Integer idGrupoAlumnos, String grupoAlumnos) {
        this.idGrupoAlumnos = idGrupoAlumnos;
        this.grupoAlumnos = grupoAlumnos;
    }
    
    public GrupoAlumnos() {}

    public String getGrupoAlumnos() {
        return grupoAlumnos;
    }

    public void setGrupoAlumnos(String grupoAlumnos) {
        this.grupoAlumnos = grupoAlumnos;
    }

    public Integer getIdGrupoAlumnos() {
        return idGrupoAlumnos;
    }

    public void setIdGrupoAlumnos(Integer idGrupoAlumnos) {
        this.idGrupoAlumnos = idGrupoAlumnos;
    }
    
    
    
}
