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
                            Bienvenido
                        </span>
                    <hr />
                </div>
                <div class="contenido">
                    <div>
                        Bienvenido al Sistema de Seguimiento a Egresados de la Maestría en Tecnologías de Información.
                    </div>
                    <br />
                    <input type="submit" value="Comenzar" class="boton" />

                </div>
            </div>    
        </form>
           
    </body>
</html>
