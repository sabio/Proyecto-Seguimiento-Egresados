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
            <div class="contenido"  style="font-size:14px">                                
                
                <div style="width:800px;  margin:0 auto 0 auto; background-color: #ffffff; border: 1px solid; ">
                    <div style="background-image: url('${pageContext.request.contextPath}/imagenes/topnav_bg.gif'); width: 100%; padding-bottom:2px; color: #666; font-size: 14px; font-weight: bold;">
                        Coordinacion de la Maestría en Tecnologías de Información                        
                    </div>                    
                    <div style="color: #666666; text-align: left; font-weight: normal; padding: 0px 10px 0px 10px;">
                		<p>&nbsp;</p>
                		<p>
                                    Bienvenido al Sistema de Seguimiento a Egresados de la Coordinacion de la Maestria de Tecnologias de Informacion.
                                    <br />
                                    Si tienes alguna dificultad o duda envia un correo a <a href="mailto: mtriati@cucea.udg.mx"> mtriati@cucea.udg.mx</a>
                                    
                                </p>                		
                          
                          <div>
                              <table><caption><b>Encuestas disponibles</b></caption>
                                    <tr align="center">
                                        <td>
                                            <b>Cuestionario</b>
                                        </td>
                                        <td>
                                            <b>Fecha inicio</b>
                                        </td>
                                        <td>
                                            <b>Fecha fin</b>
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <c:set var="fondoRow" value="#e0e0d3" scope="page" />                                    
                                    <c:forEach var="cuestionario" items="${cuestionariosPendientes}" >
                                        <c:choose>
                                            <c:when test="${fondoRow eq '#e0e0d3'}">
                                               <c:set var="fondoRow" value="#e2e4ff" scope="page" /> 
                                            </c:when>
                                            <c:when test="${fondoRow eq '#e2e4ff'}">
                                               <c:set var="fondoRow" value="#e0e0d3" scope="page" /> 
                                            </c:when>
                                        </c:choose>
                                        
                                        <tr style="background-color: ${fondoRow}" />
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
                          
                    				 
                        <p>Horarios de atención: </p>
                            <ul>
                              <li>Lunes a Viernes de 8:00  a 15:00 hrs. y de 16:00 a 20:00 hrs.</li>
                              <li class="Estilo24">Sábados de 9:00 a 13:00 hrs.</li>
                            </ul>
                        <p>
                            Periférico Norte N° 799, Núcleo Universitario Los Belenes, C.P. 45100, Zapopan, Jalisco, México.  
                            <br />
                            Informes al Tel: (0133) 3770- 3440, conmutador 3770 3300 Extensiones 25326 y 25327
                        
                        </p>
                        
                    </div>
                </div>
            </div>
        </div>    
        
           
    </body>
</html>
