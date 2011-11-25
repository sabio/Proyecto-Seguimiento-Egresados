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
    private Integer idGrupoAlumno;
    private String grupoAlumno;
    private String fechaInicio;
    private String fechaFin;
    private String activo;

    public AsignacionCuestionario() {
        
    }

    public AsignacionCuestionario(Integer idAsignacionCuestionario, Integer idCuestionario, String cuestionario, Integer idGrupoAlumno, String grupoAlumno, String fechaInicio, String fechaFin, String activo) {
        this.idAsignacionCuestionario = idAsignacionCuestionario;
        this.idCuestionario = idCuestionario;
        this.cuestionario = cuestionario;
        this.idGrupoAlumno = idGrupoAlumno;
        this.grupoAlumno = grupoAlumno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
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

    public Integer getIdGrupoAlumno() {
        return idGrupoAlumno;
    }

    public void setIdGrupoAlumno(Integer idGrupoAlumno) {
        this.idGrupoAlumno = idGrupoAlumno;
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
