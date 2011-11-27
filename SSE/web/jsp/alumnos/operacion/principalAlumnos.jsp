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
    </head>
    <body onload="setPageStatus();">
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Bienvenido
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
                                    Indicador
                                </td>
                                <td align="left">
                                    <input type="text" name="txtIndicador" id="txtIndicador" value="${indicador.indicador}" class="textbox" size="40" />
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
