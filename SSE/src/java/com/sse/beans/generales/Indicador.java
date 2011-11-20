/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class Indicador {
    private Integer idIndicador;
    private String Indicador;
    private String activo;

    public Indicador(Integer idIndicador, String Indicador, String activo) {
        this.idIndicador = idIndicador;
        this.Indicador = Indicador;
        this.activo = activo;
    }

    public Indicador() {
    }

    public String getIndicador() {
        return Indicador;
    }

    public void setIndicador(String Indicador) {
        this.Indicador = Indicador;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    
    
}
