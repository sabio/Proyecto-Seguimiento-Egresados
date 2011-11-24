<%-- 
    Document   : edicionGrupoAlumno
    Created on : Nov 24, 2011, 7:01:49 AM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoGruposAlumnos.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function isNombreRepetido(idGrupoAlumno,nombre){
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "${pageContext.request.contextPath}/ajax/consultasAjax1.jsp",
                                      data: "consulta=4&idGrupoAlumno="+idGrupoAlumno+"&nombre="+nombre,
                                      async:false
                                  }).responseText;
                
                if(parseInt(respuesta)>0)                
                    return true;
                else 
                    return false;
            }
            
            function setPageStatus(){
                
                
            }

            function hacerSubmit(){
                var idGrupoAlumno = document.getElementById("idGrupoAlumno").value;
                var nombre = document.getElementById("txtNombre");                
                
                if(Trim(nombre.value)==''){
                    jAlert('Ingrese un nombre válido', 'Error');                    
                    return false;
                }                
                
                if(isNombreRepetido(idGrupoAlumno,Trim(nombre.value))){
                    jAlert('Ya existe un grupo registrado con el mismo nombre. Ingrese otro', 'Error');
                    return false;
                }
                
                
                //alert('Submit');
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
                        Edicion Grupo de Usuarios
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()" autocomplete="off">
                    <input type="hidden" name="idGrupoAlumno" id="idGrupoAlumno" value="${grupo.idGrupoAlumnos}" />
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
                                    <input type="text" name="txtNombre" id="txtNombre" value="${grupo.grupoAlumnos}" class="textbox" size="40" />
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
