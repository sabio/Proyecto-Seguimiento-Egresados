<%-- 
    Document   : edicionCuestionario
    Created on : Dec 5, 2011, 6:27:45 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoNuevo}">
            <script type="text/javascript">
                window.location = 'edicionCuestionario.run?idCuestionario=${idNuevoCuestionario}';
            </script>
            
        </c:if>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoCuestionarios.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function setPageStatus(){                              
            }
            
            function isCuestionarioRepetido(idCuestionario,cuestionario){
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "/SSE/ajax/consultasAjax1.jsp",
                                      data: "consulta=6&idCuestionario="+idCuestionario+"&cuestionario="+cuestionario,
                                      async:false
                                  }).responseText;
                
                if(parseInt(respuesta)>0)                
                    return true;
                else 
                    return false;
            }             
            
            function asignarPregunta(idPregunta){
                document.getElementById("preguntaAAsignar").value=idPregunta;
                document.getElementById("formaCatalogo").submit();
                
            }
            
            function subirPregunta(idPregunta){
                document.getElementById("preguntaASubir").value=idPregunta;
                document.getElementById("formaCatalogo").submit();
            }
            
            function bajarPregunta(idPregunta){
                document.getElementById("preguntaABajar").value=idPregunta;
                document.getElementById("formaCatalogo").submit();
            }
            
            function eliminarPregunta(idPregunta){
                document.getElementById("preguntaAEliminar").value=idPregunta;
                document.getElementById("formaCatalogo").submit();
            }
            r
            function hacerSubmit(){
                var idCuestionario = document.getElementById("idCuestionario");
                var cuestionario = document.getElementById("txtCuestionario");
                var activo = document.getElementById("slcActivo");
                if(Trim(cuestionario.value)==''){
                    jAlert('Ingrese el nombre del cuestionario', 'Error');                    
                    return false;
                }                                
                
                if(activo.value=="-1"){
                    jAlert('Seleccione un estado', 'Error');                    
                    return false;
                }
                
                if(isCuestionarioRepetido(idCuestionario.value,cuestionario.value)){
                    jAlert('Ya existe un cuestionario con el mismo nombre. Ingrese otro.', 'Error');                    
                    return false;
                }
                
                return true;
            }
        </script>
        <style>
            .divConScroll{
                height:200px;
                width:900px;
                overflow:auto;
                
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion Cuestionario</title>
    </head>
    <body onload="setPageStatus();">
         <div class="principal"  style="width:900px;">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Edicion cuestionario
                    </span>
                <hr />
            </div>
            <div class="contenido" style="width:900px;">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="idCuestionario" id="idCuestionario" value="${cuestionario.idCuestionario}" />
                    <input type="hidden" name="preguntaAAsignar" id="preguntaAAsignar" />
                    <input type="hidden" name="preguntaASubir" id="preguntaASubir" />
                    <input type="hidden" name="preguntaABajar" id="preguntaABajar" />
                    <input type="hidden" name="preguntaAEliminar" id="preguntaAEliminar" />
                    
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
                                    <input type="text" name="txtCuestionario" id="txtCuestionario" value="${cuestionario.cuestionario}" class="textbox" size="40" />
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${cuestionario.activo eq 'S' or cuestionario.activo eq null}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${cuestionario.activo eq 'N'}">selected</c:if>>No</option>
                                    </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" id="guardar" name="guardar" class="boton" value="Aceptar" />
                                    <input type="button" id="cancelar" name="cancelar" class="boton" value="Cancelar" onclick="window.location = 'listadoCuestionarios.run';" />
                                </td>
                            </tr>
                        </tbody>

                    </table>
                    <div class="divConScroll">                
                        <table width="800px" align="center" >
                            <c:if test="${param.idCuestionario ne null}">
                                <tr>
                                    <td colspan="2" >
                                        <br />
                                        <c:choose>
                                            <c:when test="${preguntasDisponibles eq null}">
                                                <b>No hay Preguntas Disponibles</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b>Preguntas disponibles</b>
                                                <table >
                                                    <tr>
                                                        <td width="50%"><b>Pregunta</b></td>
                                                        <td width="20%"><b>Indicador</b></td>
                                                        <td width="20%"><b>Tipo pregunta</b></td>
                                                        <td width="10%"><b>Agregar</b></td>                                                
                                                    </tr>
                                                    <c:set var="fondoRow" value="#e0e0d3" scope="page" />
                                                    <c:forEach var="pregunta" items="${preguntasDisponibles}" >
                                                        <c:choose>
                                                            <c:when test="${fondoRow eq '#e0e0d3'}">
                                                               <c:set var="fondoRow" value="#e2e4ff" scope="page" /> 
                                                            </c:when>
                                                            <c:when test="${fondoRow eq '#e2e4ff'}">
                                                               <c:set var="fondoRow" value="#e0e0d3" scope="page" /> 
                                                            </c:when>
                                                        </c:choose>
                                                        <tr style="background-color: ${fondoRow}">
                                                            <td align="left">${pregunta.pregunta}</td>
                                                            <td align="left">${pregunta.indicador}</td>
                                                            <td NOWRAP align="left">${pregunta.tipoPregunta}</td>
                                                            <td>
                                                                <a class="linker" style="border: 0" >
                                                                    <img src="${pageContext.request.contextPath}/imagenes/iconos/agregar.gif" onclick="asignarPregunta(${pregunta.idPregunta})" />
                                                                </a>
                                                            </td>
                                                        </tr>
                                                </c:forEach>
                                                </table>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                </tr>
                            </c:if>
                        </table>
                    </div>
                                   
                    <div class="divConScroll">                
                        <table width="800px" align="center" >
                            <c:if test="${param.idCuestionario ne null}">
                                <tr>
                                    <td colspan="2" >
                                        <br />
                                        <c:choose>
                                            <c:when test="${preguntasAsignadas eq null}">
                                                <b>No hay Preguntas Asignadas</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b>Preguntas Asignadas</b>
                                                <table>
                                                    <tr>
                                                        <td width="60%"><b>Pregunta</b></td>
                                                        <td><b>Indicador</b></td>
                                                        <td><b>Tipo pregunta</b></td>
                                                        <td><b>Orden</b></td>
                                                        <td><b>Eliminar</b></td>
                                                    </tr>
                                                    <c:set var="fondoRow" value="#e0e0d3" scope="page" />
                                                    <c:forEach varStatus="status" var="pregunta" items="${preguntasAsignadas}" >
                                                        <c:choose>
                                                            <c:when test="${fondoRow eq '#e0e0d3'}">
                                                               <c:set var="fondoRow" value="#e2e4ff" scope="page" /> 
                                                            </c:when>
                                                            <c:when test="${fondoRow eq '#e2e4ff'}">
                                                               <c:set var="fondoRow" value="#e0e0d3" scope="page" /> 
                                                            </c:when>
                                                        </c:choose>
                                                        <tr style="background-color: ${fondoRow}">
                                                            <td align="left">${pregunta.pregunta}</td>
                                                            <td align="left">${pregunta.indicador}</td>
                                                            <td NOWRAP align="left">${pregunta.tipoPregunta}</td>
                                                            <td>
                                                                <c:if test="${!status.first}">
                                                                    <a class="linker" style="border: 0" >
                                                                        <img src="${pageContext.request.contextPath}/imagenes/iconos/arrow_Up.gif" onclick="subirPregunta(${pregunta.idPregunta})" />
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${!status.last}">
                                                                    <a class="linker" style="border: 0" >
                                                                        <img src="${pageContext.request.contextPath}/imagenes/iconos/arrow_Down.gif" onclick="bajarPregunta(${pregunta.idPregunta})" />
                                                                    </a>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <a class="linker" style="border: 0" >
                                                                    <img src="${pageContext.request.contextPath}/imagenes/iconos/eliminar.gif" onclick="eliminarPregunta(${pregunta.idPregunta})" />
                                                                </a>
                                                            </td>    
                                                        </tr>
                                                </c:forEach>
                                                </table>
                                             </c:otherwise>
                                        </c:choose>
                                    </td>

                                </tr>
                            </c:if>
                        </table>
                    </div>
                                    
                </form> 
            </div>
        </div>    
        
           
    </body>
</html>
