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

    public Cuestionario(Integer idCuestionario, String Cuestionario) {
        this.idCuestionario = idCuestionario;
        this.Cuestionario = Cuestionario;
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
