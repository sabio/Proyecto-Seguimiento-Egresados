<%-- 
    Document   : preguntasListado
    Created on : Oct 16, 2011, 10:43:11 AM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css" title="currentStyle">                
                @import "css/datatable/demo_table.css";
                @import "css/pagina.css";
                @import "css/datatable/jquery-ui-1.8.4.custom.css";

        </style>
        <script type="text/javascript" language="javascript" src="js/jquery.js"></script>
        <script type="text/javascript" language="javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf-8">
            var oTable;
            var asInitVals = new Array();

            jQuery(document).ready(function() {

            oTable = jQuery('#tablaCatalogo').dataTable( {       
                "aLengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Todos"]],
                "sDom": '<"top" lf>rt<"bottom" p<"clear">',/*,"sDom": '<"top"i>rt<"bottom"flp<"clear">' */
                "sPaginationType": "full_numbers"         
                /*"iDisplayLength": 2*/

            
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
        </script>
        <title>Preguntas</title>
    </head>
    <body>
        <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Catalogo de preguntas
                    </span>
                <hr />
            </div>
            <div class="contenido">
                    
                <a href="edicionPregunta.run">Agregar Pregunta</a>
                <br />
                <br />
                
                    <table id="tablaCatalogo" class="display">
                        <thead>
                            <tr>
                                <th>Pregunta</th>
                                <th>Activo</th>
                                <th>Eliminar</th>
                            </tr>

                        </thead>
                        <tbody>
                            <c:forEach  items="${listadoPreguntas}" var="pregunta">
                                <tr>
                                    <td>
                                        ${pregunta.pregunta}
                                    </td>
                                    <td>
                                        ${pregunta.activo}
                                    </td>
                                    <td>
                                        eliminame
                                    </td>    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                
            </div>
        </div>        
    </body>
</html>
