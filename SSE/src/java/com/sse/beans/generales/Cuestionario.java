/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando.gomez
 */
public class Cuestionario {
    Integer idCuestionario;
    String Cuestionario;
    String activo;

    public Cuestionario(Integer idCuestionario, String Cuestionario) {
        this.idCuestionario = idCuestionario;
        this.Cuestionario = Cuestionario;
    }

    public Cuestionario(Integer idCuestionario, String Cuestionario, String activo) {
        this.idCuestionario = idCuestionario;
        this.Cuestionario = Cuestionario;
        this.activo = activo;
    }
    
    

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }        
    
    public String getCuestionario() {
        return Cuestionario;
    }

    public void setCuestionario(String Cuestionario) {
        this.Cuestionario = Cuestionario;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
    
    
}
