<%-- 
    Document   : bienvenidaCuestionarioScreen
    Created on : Nov 28, 2011, 5:42:50 PM
    Author     : armando.gomez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>                
        <jsp:include page="/jsp/includes/cabecera1.jsp" />
    </head>
     <body>
         <form target="cuestionario" id="forma" action="cuestionario.run" method="post" >
             <input type="hidden" name="idAsignacion" id="idAsignacion" value="${idAsignacion}" />
        
             <div class="principal">
                <div class="header">
                    <hr />
                        <span id="titulo"> 
                            ${nombreCuestionario}
                        </span>
                    <hr />
                </div>
                <div class="contenido">
                    <div style="width:700px;  margin:0 auto 0 auto; background-color: #ffffff; border: 1px solid; ">
                        <div style="background-image: url('${pageContext.request.contextPath}/imagenes/topnav_bg.gif'); width: 100%; padding-bottom:2px; color: #666; font-size: 14px; font-weight: bold;">
                            Instrucciones Generales
                        </div>                    
                        <div style="color: #666666; text-align: left; font-weight:bold; padding: 0px 10px 0px 10px;">
                                    <p>&nbsp;</p>
                                    <p>
                                        Esta encuesta es anónima y personal, dirigida a estudiantes y egresados de la Maestria en Tecnologias de Informacion.                                        
                                        <br />
                                        Siga las instrucciones y conteste adecuadamente las preguntas. De click en 'Comenzar' para iniciar la encuesta. <br />
                                        Una vez iniciada la encuesta, usted podra pausar y reaundar la misma si asi lo desea.
                                        <br />
                                        Si tienes alguna dificultad o duda envia un correo a <a href="mailto: mtriati@cucea.udg.mx"> mtriati@cucea.udg.mx</a>

                                    </p>                		
                                    <center>
                                        <input type="submit" value="Comenzar" class="boton" />
                                    </center>
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
        </form>
    </body>
</html>
