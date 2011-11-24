<%-- 
    Document   : listadoGruposAlumnos
    Created on : Nov 23, 2011, 9:15:40 PM
    Author     : armando
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />   
        
        
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
                jConfirm('Desea eliminar el grupo?', 'Confirmacion', function(r) {
                    if(r){
                        document.getElementById("hdnElimina").value=id;
                        document.getElementById("formaListado").submit();
                       
                            
                    }
                });

                
            }
        </script>
        <title>Alumnos</title>
    </head>
    <body>
        <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Catalogo de grupos de alumnos
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form name="formaListado" id="formaListado" >
                    <input type="hidden" name="hdnElimina" id="hdnElimina" />
                    <br />
                        <a style="float:right" class="linker" href="edicionGrupoAlumno.run">
                            <img style="border: 0" src="${pageContext.request.contextPath}/imagenes/iconos/agregar.gif" />
                            Agregar grupo
                        </a>
                    <br />
                    <br />
                
                    <table id="tablaCatalogo" class="display">
                        <thead>
                            <tr>
                                <th>Grupo</th>                                
                                <th>Eliminar</th>
                            </tr>

                        </thead>
                        <tbody>
                            <c:forEach  items="${listadoGruposAlumnos}" var="grupo">
                                <tr>
                                    <td>
                                        <a href="edicionGrupoAlumno.run?idGrupoAlumno=${grupo.idGrupoAlumnos}">
                                            ${grupo.grupoAlumnos}
                                        </a>

                                    </td>                                   
                                    <td>
                                        <span src="/imagenes/iconos/eliminar.gif" onclick="eliminar(${grupo.idGrupoAlumnos})">
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
