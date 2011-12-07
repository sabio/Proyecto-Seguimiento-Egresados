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
                        Asignaciones
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="hdnIdIndicador" id="hdnIdIndicador" value="${indicador.idIndicador}" />
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
                            <c:forEach var="asignacion" items="${listadoAsignaciones}">
                                <tr style=" cursor:pointer;" onclick="window.location = 'resultadoDeAsignacion.run?idAsignacion=${asignacion.idAsignacionCuestionario}'">
                                    <td>${asignacion.cuestionario}</td>
                                    <td>${asignacion.grupoAlumno}</td>
                                    <td>${asignacion.fechaInicio}</td>
                                    <td>${asignacion.fechaFin}</td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </form>
                    
                <c:forEach var="id" items="${listadoIdsIndicadores}">
                    <img src="${pageContext.request.contextPath}/ImagenIndicadorCreator?idAsignacion=${param.idAsignacion}&idIndicador=${id}" />
                    <br /><br />
                </c:forEach>    
            </div>
        </div>    
        
           
    </body>
</html>
