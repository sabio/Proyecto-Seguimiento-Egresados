<%-- 
    Document   : principalAlumnos
    Created on : Nov 26, 2011, 3:33:19 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        <script type="text/javascript">
            function comienza(idAsignacion){
                document.getElementById("idAsignacion").value=idAsignacion;                
                windowMax("","cuestionario");
                document.getElementById("forma").submit();
            }
            
        </script>
    </head>
    <body>
        <form target="cuestionario" id="forma" action="bienvenida.run" method="post" >
            <input type="hidden" name="idAsignacion" id="idAsignacion" />            
        </form>
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Bienvenido
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <div>
                    Bienvenido al Sistema de Seguimiento a Egresados de la Maestría en Tecnologías de Información.
                </div>
                
                <table>
                    <tr>
                        <td>
                            Cuestionario
                        </td>
                        <td>
                            Fecha inicio
                        </td>
                        <td>
                            Fecha fin
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                    <c:forEach var="cuestionario" items="${cuestionariosPendientes}" >
                        <tr>
                            <td>${cuestionario.cuestionario}</td>
                            <td>${cuestionario.fechaInicio}</td>
                            <td>${cuestionario.fechaFin}</td>
                            <td>
                                <input type="button" id="btnAplicarCuestionario" name="btnAplicarCuestionario" class="boton" 
                                <c:choose>
                                    <c:when test="${cuestionario.cuestionarioYaEmpezado}">
                                        value="Reanudar"
                                    </c:when>
                                    <c:otherwise>
                                        value="Empezar"
                                    </c:otherwise>
                                </c:choose>                                
                                 onclick="comienza(${cuestionario.idAsignacion})" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
            </div>
        </div>    
        
           
    </body>
</html>
