<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
response.reset();
response.setHeader("Content-type","application/vnd.ms-excel");
response.setHeader("Content-disposition","inline; filename=ExpenseAson.xls");
--%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoIndicadores.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function setPageStatus(){
                
                
            }

            
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion Indicador</title>
    </head>
    <body onload="setPageStatus();">
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Consulta de Resultados 
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <br />
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="hdnIdIndicador" id="hdnIdIndicador" value="${indicador.idIndicador}" />
                    <c:choose>
                        <c:when test="${listadoAsignaciones eq null}">
                            <b>No hay resultados para consultar</b>
                        </c:when>
                        <c:otherwise>
                            <table align="center">
                                <thead>
                                    <tr>
                                        <th>
                                            Cuestionario
                                        </th>
                                        <th>
                                            Grupo
                                        </th>
                                        <th>
                                            Fecha Inicio <i>(año/mes/dia hora)</i>
                                        </th>
                                        <th>
                                            Fecha Fin <i>(año/mes/dia hora)</i>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="fondoRow" value="#e0e0d3" scope="page" />                                                                

                                    <c:forEach var="asignacion" items="${listadoAsignaciones}">
                                        <c:choose>
                                            <c:when test="${fondoRow eq '#e0e0d3'}">
                                               <c:set var="fondoRow" value="#e2e4ff" scope="page" /> 
                                            </c:when>
                                            <c:when test="${fondoRow eq '#e2e4ff'}">
                                               <c:set var="fondoRow" value="#e0e0d3" scope="page" /> 
                                            </c:when>
                                        </c:choose>
                                        <tr style="cursor:pointer; background-color: ${fondoRow};" onclick="window.location = 'resultadoDeAsignacion.run?idAsignacion=${asignacion.idAsignacionCuestionario}'">
                                            <td>${asignacion.cuestionario}</td>
                                            <td>${asignacion.grupoAlumno}</td>
                                            <td>${asignacion.fechaInicio}</td>
                                            <td>${asignacion.fechaFin}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </c:otherwise>    
                    </c:choose>
                            
                </form>
                <br />
                <c:if test="${param.idAsignacion ne null}">
                    <br />
                    Total de alumnos que han contestado la encuesta: <b>${numeroDeAlumnosQueHanContestadoLaAsignacion}</b>
                    <br />

                    <input type="button" class="boton" value="imprimir" onclick="window.print();" />
                </c:if>
                
                <br />
                <c:forEach var="id" items="${listadoIdsIndicadores}">
                    <img height="260px" width="450px" src="${pageContext.request.contextPath}/ImagenIndicadorCreator?idAsignacion=${param.idAsignacion}&idIndicador=${id}" />
                    <br /><br />
                </c:forEach>
                
                <br />
                <c:if test="${param.idAsignacion ne null}">
                    <input type="button" class="boton" value="imprimir" onclick="window.print();" />
                </c:if>
                
            </div>
        </div>    
        
           
    </body>
</html>
