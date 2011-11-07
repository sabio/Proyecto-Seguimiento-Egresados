<%-- 
    Document   : edicionPregunta
    Created on : Oct 16, 2011, 6:07:31 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoPreguntas.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function hacerSubmit(){
                var pregunta = document.getElementById("txtPregunta");
                var activo = document.getElementById("slcActivo");                                
                if(Trim(pregunta.value)==''){
                    jAlert('Ingrese una pregunta valida', 'Error');                    
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
        <title>Edicion Pregunta</title>
    </head>
    <body>
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Edicion pregunta
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="hdnIdPregunta" id="hdnIdPregunta" value="${pregunta.idPregunta}" />
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
                                    Pregunta
                                </td>
                                <td align="left">
                                    <input type="text" name="txtPregunta" id="txtPregunta" value="${pregunta.pregunta}" class="textbox" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${pregunta.activo eq 'S'}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${pregunta.activo eq 'N'}">selected</c:if>>No</option>
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
