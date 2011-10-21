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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion Pregunta</title>
    </head>
    <body>
        <form>
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
                        <td>
                            <input type="text" name="txtPregunta" id="txtPregunta" value="${pregunta.pregunta}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Activo
                        </td>
                        <td>
                            <select id="activo" name="activo"> 
                                <option value="-1">Seleccione...</option>
                                <option value="S" <c:if test="${pregunta.activo eq 'S'}">selected</c:if>>Si</option>
                                <option value="N" <c:if test="${pregunta.activo eq 'N'}">selected</c:if>>No</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" id="guardar" name="guardar" value="Aceptar" />
                        </td>
                    </tr>
                </tbody>

            </table>
        </form>    
    </body>
</html>
