/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class Pregunta {
    private Integer idPregunta;
    private String pregunta;
    private String activo;

    public Pregunta(Integer idPregunta, String pregunta, String activo) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.activo = activo;
    }
    
    public Pregunta() {        
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    
}
