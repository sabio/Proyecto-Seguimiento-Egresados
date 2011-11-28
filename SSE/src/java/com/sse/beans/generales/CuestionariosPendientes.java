/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class CuestionariosPendientes {
    Integer idCuestionario;
    String cuestionario;
    String fechaInicio;
    String fechaFin;
    Boolean cuestionarioYaEmpezado;

    public CuestionariosPendientes(Integer idCuestionario, String cuestionario, String fechaInicio, String fechaFin, Boolean cuestionarioYaEmpezado) {
        this.idCuestionario = idCuestionario;
        this.cuestionario = cuestionario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cuestionarioYaEmpezado = cuestionarioYaEmpezado;
    }

    public String getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(String cuestionario) {
        this.cuestionario = cuestionario;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean getCuestionarioYaEmpezado() {
        return cuestionarioYaEmpezado;
    }

    public void setCuestionarioYaEmpezado(Boolean cuestionarioYaEmpezado) {
        this.cuestionarioYaEmpezado = cuestionarioYaEmpezado;
    }
    
    
    
}
