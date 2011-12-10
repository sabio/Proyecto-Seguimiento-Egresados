<%-- 
    Document   : asignacionCuestionarios
    Created on : Nov 24, 2011, 3:42:55 PM
    Author     : armando.gomez
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />   
        <script type="text/javascript" charset="utf-8">
            function isIndicadorUsadoEnUnaOMasPreguntas(idIndicador){
                var respuesta = $.ajax({
                                      type: "GET",
                                      url: "${pageContext.request.contextPath}/ajax/consultasAjax1.jsp",
                                      data: "consulta=1&idIndicador="+idIndicador,
                                      async:false
                                  }).responseText;
                
                if(parseInt(respuesta)>0)                
                    return true;
                else 
                    return false;
            }
            
        </script>
        
        
        <script type="text/javascript" charset="utf-8">
            var oTable;
            var asInitVals = new Array();

            jQuery(document).ready(function() {

            oTable = jQuery('#tablaCatalogo').dataTable( {       
                "aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Todos"]],
                "sDom": '<"top" lf>rt<"bottom" p<"clear">',
                "sPaginationType": "full_numbers",
                "oLanguage": {
                    "sUrl": "others/DT_ES.txt"
                }

            
            } );
            /*
            * This is for  individual Column Filter in Footer text box
            */
            jQuery("tfoot input").keyup( function () {
            /* Filter on the column (the index) of this element */
            oTable.fnFilter( this.value, jQuery("tfoot input").index(this) );
            } );

            jQuery("tfoot input").each( function (i) {
            asInitVals[i] = this.value;
            } );

            jQuery("tfoot input").focus( function () {
            if ( this.className == "search_init" )
            {
            this.className = "";
            this.value = "";
            }
            } );

            jQuery("tfoot input").blur( function (i) {
            if ( this.value == "" )
            {
            this.className = "search_init";
            this.value = asInitVals[jQuery("tfoot input").index(this)];
            }
            } );
            } );
            
            
            function eliminar(id){
                jConfirm('¿Desea eliminar la asignación? Serán eliminados todos los resultados asociados a ésta asignación', 'Confirmacion', function(r) {
                    if(r){
                        document.getElementById("hdnElimina").value=id;
                        document.getElementById("formaListado").submit();
                        /*
                        if(isIndicadorUsadoEnUnaOMasPreguntas(id)){
                            jConfirm('Existe una o mas preguntas asociadas a este indicador. Si lo elimina tambien seran eliminadas las preguntas asociadas. Desea continuar?', 'Confirmacion', function(s) {
                                if(s){
                                    document.getElementById("formaListado").submit();
                                    //alert("submit");
                                }
                            });
                        }
                        else{                            
                            document.getElementById("formaListado").submit();
                            //alert("submit");
                        }
                        */
                       
                            
                    }
                });

                
            }
        </script>
        <title>Indicadores</title>
    </head>
    <body>
        <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Catalogo de asignaciones de cuestionarios
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form name="formaListado" id="formaListado" >
                    <input type="hidden" name="hdnElimina" id="hdnElimina" />
                    <br />
                        <a style="float:right" class="linker" href="asignacionCuestionario.run">
                            <img style="border: 0" src="${pageContext.request.contextPath}/imagenes/iconos/agregar.gif" />
                            Agregar asignacion
                        </a>
                    <br />
                    <br />
                
                    <table id="tablaCatalogo" class="display">
                        <thead>
                            <tr>
                                <th>Cuestionario</th>
                                <th>Grupo</th>
                                <th>Fecha Inicio <i>(año/mes/dia hora)</i></th>
                                <th>Fecha Fin <i>(año/mes/dia hora)</i></th>
                                <th>Activo</th>
                                <th>Eliminar</th>
                            </tr>

                        </thead>
                        <tbody>
                            <c:forEach  items="${listadoAsignaciones}" var="asignacion">
                                <tr>
                                    <td>
                                        <a href="asignacionCuestionario.run?idAsignacionCuestionario=${asignacion.idAsignacionCuestionario}">
                                            ${asignacion.cuestionario}
                                        </a>

                                    </td>
                                    <td>
                                        <a href="asignacionCuestionario.run?idAsignacionCuestionario=${asignacion.idAsignacionCuestionario}">
                                            ${asignacion.grupoAlumno}
                                        </a>
                                    </td>
                                    <td>
                                        <a href="asignacionCuestionario.run?idAsignacionCuestionario=${asignacion.idAsignacionCuestionario}">
                                            ${asignacion.fechaInicio}
                                        </a>
                                    </td>
                                    <td>
                                        <a href="asignacionCuestionario.run?idAsignacionCuestionario=${asignacion.idAsignacionCuestionario}">
                                            ${asignacion.fechaFin}
                                        </a>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${asignacion.activo eq 'S'}">
                                                Si
                                            </c:when>
                                            <c:otherwise>
                                                No
                                            </c:otherwise>
                                        </c:choose>                                        
                                    </td>
                                    <td>
                                        <span src="/imagenes/iconos/eliminar.gif" onclick="eliminar(${asignacion.idAsignacionCuestionario})">
                                            <img src="${pageContext.request.contextPath}/imagenes/iconos/eliminar.gif" />
                                        </span>
                                    </td>    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>        
    </body>
</html>

