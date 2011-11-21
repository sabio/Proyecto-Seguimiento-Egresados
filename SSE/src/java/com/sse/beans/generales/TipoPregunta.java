/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.beans.generales;

/**
 *
 * @author armando
 */
public class TipoPregunta {
    Integer idTipoPregunta;
    String tipoPregunta;

    public TipoPregunta() {
        
    }
    
    public TipoPregunta(Integer idTipoPregunta, String tipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
        this.tipoPregunta = tipoPregunta;
    }

    
    
    public Integer getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(Integer idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
    
    
}
