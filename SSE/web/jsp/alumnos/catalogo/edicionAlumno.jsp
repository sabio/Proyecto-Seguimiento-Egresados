<%-- 
    Document   : edicionAlumno
    Created on : Nov 21, 2011, 12:13:42 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoAlumnos.run';
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
                        Edicion Usuario
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="hdnIdUsuario" id="hdnIdUsuario" value="${alumno.usuario.idUsuario}" />
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
                                    Nombre
                                </td>
                                <td align="left">
                                    <input type="text" name="txtNombre" id="txtNombre" value="${alumno.usuario.nombre}" class="textbox" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Apellido Paterno
                                </td>
                                <td align="left">
                                    <input type="text" name="txtPaterno" id="txtPaterno" value="${alumno.usuario.apaterno}" class="textbox" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Apellido Materno
                                </td>
                                <td align="left">
                                    <input type="text" name="txtMaterno" id="txtMaterno" value="${alumno.usuario.amaterno}" class="textbox" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${alumno.usuario.activo eq 'S' or alumno.usuario.activo eq null}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${alumno.usuario.activo eq 'N'}">selected</c:if>>No</option>
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
