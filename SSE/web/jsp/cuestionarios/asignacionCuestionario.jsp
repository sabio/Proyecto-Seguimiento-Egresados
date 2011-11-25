<%-- 
    Document   : asignacionCuestionario
    Created on : Nov 24, 2011, 9:28:52 PM
    Author     : armando
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

            function hacerSubmit(){
                var indicador = document.getElementById("txtIndicador");
                var activo = document.getElementById("slcActivo");                                
                if(Trim(indicador.value)==''){
                    jAlert('Ingrese un indicador válido', 'Error');                    
                    return false;
                }
                
                if(activo.value=="-1"){
                    jAlert('Seleccione un estado', 'Error');                    
                    return false;
                }
                
                return true;
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
                        Edicion asignacion de cuestionario
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
                                    fecha inicio
                                </td>
                                <td align="left">
                                    <input size="30" id="f_date1" /><button id="f_btn1">...</button><br />

    <script type="text/javascript">//<![CDATA[
      Calendar.setup({
        inputField : "f_date1",
        trigger    : "f_btn1",
        onSelect   : function() { this.hide() },
        showTime   : 12,
        dateFormat : "%Y-%m-%d %I:%M %p"
      });
    //]]></script>

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${indicador.activo eq 'S' or indicador.activo eq null}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${indicador.activo eq 'N'}">selected</c:if>>No</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" id="guardar" name="guardar" class="boton" value="Aceptar" />
                                    <input type="button" id="cancelar" name="cancelar" class="boton" value="Cancelar" onclick="history.go(-1)" />
                                </td>
                            </tr>
                        </tbody>

                    </table>
                </form> 
            </div>
        </div>    
        
           
    </body>
</html>
