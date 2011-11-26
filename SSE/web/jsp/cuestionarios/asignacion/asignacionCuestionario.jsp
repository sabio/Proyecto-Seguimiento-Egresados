<%-- 
    Document   : asignacionCuestionario
    Created on : Nov 24, 2011, 9:28:52 PM
    Author     : armando
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoAsignacionCuestionarios.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function fechasValidas(idAsignacionCuestionario,idCuestionario,idGrupoAlumno,txtFechaInicio,txtFechaFin){
                 if(Trim(idAsignacionCuestionario)=='')
                     idAsignacionCuestionario=0;
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "/SSE/ajax/consultasAjax1.jsp",
                                      data: "consulta=5&idAsignacionCuestionario="+idAsignacionCuestionario+"&idCuestionario="+idCuestionario+"&idGrupoAlumno="+idGrupoAlumno+"&txtFechaInicio="+txtFechaInicio+"&txtFechaFin="+txtFechaFin,
                                      async:false
                                  }).responseText;
                
                
                return Trim(respuesta);
            }
            
            function setPageStatus(){    
            }

            function hacerSubmit(){
                var idAsignacionCuestionario = document.getElementById("idAsignacionCuestionario").value;
                var idCuestionario = document.getElementById("slcCuestionario").value;
                var idGrupoAlumno = document.getElementById("slcGrupo").value;
                var txtFechaInicio = document.getElementById("txtFechaInicio").value;
                var txtFechaFin = document.getElementById("txtFechaFin").value;
                var activo = document.getElementById("slcActivo").value;
                
                
                if(activo=="-1"){
                    jAlert('Seleccione un estado', 'Error');                    
                    return false;
                }
                var validacionFechas = fechasValidas(idAsignacionCuestionario,idCuestionario,idGrupoAlumno,txtFechaInicio,txtFechaFin);
                if(validacionFechas=='iguales'){
                    jAlert('Las fechas proporcionadas son iguales. Favor de corregir', 'Error');                    
                    return false;
                }
                
                if(validacionFechas=='fechainicialsuperiorafinal'){
                    jAlert('La fecha inicial es superior a la fecha final. Favor de corregir', 'Error');                    
                    return false;
                }
                
                if(validacionFechas=='fechatraslapada'){
                    jAlert('Ya existe una asignacion de cuestionario que contiene el mismo grupo, el mismo cuestionario y las fechas de asignacion se traslapan. Favor de corregir', 'Error');
                    return false;
                }
                
                return true;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion Asignación de Cuestionario</title>
    </head>
    <body onload="setPageStatus();">
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Edicion asignacion de cuestionario
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="idAsignacionCuestionario" id="idAsignacionCuestionario" value="${asignacionCuestionario.idAsignacionCuestionario}" />
                    <table align="center">
                        <thead>
                            <tr>
                                <th>
                                    Campo
                                </th>
                                <th>
                                    Valor
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    Cuestionario
                                </td>
                                <td align="left">
                                    <select id="slcCuestionario" name="slcCuestionario" class="textbox">
                                        <option value="-1">Seleccione...</option>
                                        <c:forEach var="cuestionario" items="${listadoCuestionarios}" >
                                            <option value="${cuestionario.idCuestionario}" <c:if test="${cuestionario.idCuestionario eq asignacionCuestionario.idCuestionario}">selected</c:if>>${cuestionario.cuestionario}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Grupo
                                </td>
                                <td align="left">
                                    <select id="slcGrupo" name="slcGrupo" class="textbox">
                                        <option value="-1">Seleccione...</option>
                                        <c:forEach var="grupo" items="${listadoGruposAlumnos}" >
                                            <option value="${grupo.idGrupoAlumnos}" <c:if test="${grupo.idGrupoAlumnos eq asignacionCuestionario.idGrupoAlumnos}">selected</c:if>>${grupo.grupoAlumnos}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Fecha inicio
                                </td>
                                <td align="left">
                                    <input value="${asignacionCuestionario.fechaInicio}" size="20" id="txtFechaInicio" name="txtFechaInicio" readonly="true"  /><button id="btnFechaInicio">...</button><br />

                                    <script type="text/javascript">//<![CDATA[
                                      Calendar.setup({
                                        inputField : "txtFechaInicio",
                                        trigger    : "btnFechaInicio",
                                        onSelect   : function() { this.hide() },
                                        showTime   : 24,
                                        dateFormat : "%Y/%m/%d %I:%M %p"
                                      });
                                    //]]></script>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Fecha fin
                                </td>
                                <td align="left">
                                    <input value="${asignacionCuestionario.fechaFin}" size="20" id="txtFechaFin" name="txtFechaFin" readonly="true"  /><button id="btnFechaFin">...</button><br />

                                    <script type="text/javascript">//<![CDATA[
                                      Calendar.setup({
                                        inputField : "txtFechaFin",
                                        trigger    : "btnFechaFin",
                                        onSelect   : function() { this.hide() },
                                        showTime   : 12,
                                        dateFormat : "%Y/%m/%d %I:%M %p"
                                      });
                                    //]]></script>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${asignacionCuestionario.activo eq 'S' or asignacionCuestionario.activo eq null}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${asignacionCuestionario.activo eq 'N'}">selected</c:if>>No</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" id="guardar" name="guardar" class="boton" value="Aceptar" />
                                    <input type="button" id="cancelar" name="cancelar" class="boton" value="Cancelar" onclick="history.go(-1)" />
                                </td>
                            </tr>
                        </tbody>

                    </table>
                </form> 
            </div>
        </div>    
        
           
    </body>
</html>
