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
    private Integer idTipoPregunta;
    private String tipoPregunta;
    private Integer idIndicador;
    private String indicador;
    private String activo;    
    private String respuestaString;
    private Integer respuestaInt;

    public Pregunta(Integer idPregunta, String pregunta, Integer idTipoPregunta, Integer idIndicador, String activo) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.idTipoPregunta = idTipoPregunta;
        this.idIndicador = idIndicador;
        this.activo = activo;
    }
    
    public Pregunta(Integer idPregunta, String pregunta, Integer idTipoPregunta, Integer idIndicador, String activo, String respuestaString, Integer respuestaInt) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.idTipoPregunta = idTipoPregunta;
        this.idIndicador = idIndicador;
        this.activo = activo;
        this.respuestaString = respuestaString;
        this.respuestaInt = respuestaInt;
    }

    public Pregunta(Integer idPregunta, String pregunta, Integer idTipoPregunta, String tipoPregunta, Integer idIndicador, String indicador, String activo) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.idTipoPregunta = idTipoPregunta;
        this.tipoPregunta = tipoPregunta;
        this.idIndicador = idIndicador;
        this.indicador = indicador;
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

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Integer getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(Integer idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public Integer getRespuestaInt() {
        return respuestaInt;
    }

    public void setRespuestaInt(Integer respuestaInt) {
        this.respuestaInt = respuestaInt;
    }

    public String getRespuestaString() {
        return respuestaString;
    }

    public void setRespuestaString(String respuestaString) {
        this.respuestaString = respuestaString;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    
    
}
