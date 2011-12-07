/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class AsignacionCuestionario {
    private Integer idAsignacionCuestionario;
    private Integer idCuestionario;
    private String cuestionario;
    private Integer idGrupoAlumnos;
    private String grupoAlumno;
    private String fechaInicio;
    private String fechaFin;
    private String activo;

    public AsignacionCuestionario() {
        
    }

    public AsignacionCuestionario(Integer idAsignacionCuestionario, Integer idCuestionario, String cuestionario, Integer idGrupoAlumnos, String grupoAlumno, String fechaInicio, String fechaFin, String activo) {
        this.idAsignacionCuestionario = idAsignacionCuestionario;
        this.idCuestionario = idCuestionario;
        this.cuestionario = cuestionario;
        this.idGrupoAlumnos = idGrupoAlumnos;
        this.grupoAlumno = grupoAlumno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }
    
    public AsignacionCuestionario(Integer idAsignacionCuestionario, String cuestionario, String grupoAlumno, String fechaInicio, String fechaFin) {
        this.idAsignacionCuestionario = idAsignacionCuestionario;
        this.cuestionario = cuestionario;
        this.grupoAlumno = grupoAlumno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    

    public Integer getIdAsignacionCuestionario() {
        return idAsignacionCuestionario;
    }

    public void setIdAsignacionCuestionario(Integer idAsignacionCuestionario) {
        this.idAsignacionCuestionario = idAsignacionCuestionario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }        

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Integer getIdGrupoAlumnos() {
        return idGrupoAlumnos;
    }

    public void setIdGrupoAlumnos(Integer idGrupoAlumnos) {
        this.idGrupoAlumnos = idGrupoAlumnos;
    }

    public String getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(String cuestionario) {
        this.cuestionario = cuestionario;
    }

    public String getGrupoAlumno() {
        return grupoAlumno;
    }

    public void setGrupoAlumno(String grupoAlumno) {
        this.grupoAlumno = grupoAlumno;
    }
    
    
    
}
