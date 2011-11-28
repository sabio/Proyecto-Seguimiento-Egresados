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

    public CuestionariosPendientes(Integer idCuestionario, String cuestionario, String fechaInicio, String fechaFin) {
        this.idCuestionario = idCuestionario;
        this.cuestionario = cuestionario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
    
    
    
}
