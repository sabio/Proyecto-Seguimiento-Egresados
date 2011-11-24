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
            function isLoginRepetido(idUsuario,login){
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "${pageContext.request.contextPath}/ajax/consultasAjax1.jsp",
                                      data: "consulta=2&idUsuario="+idUsuario+"&login="+login,
                                      async:false
                                  }).responseText;
                
                if(parseInt(respuesta)>0)                
                    return true;
                else 
                    return false;
            }
            
            function isEmailRepetido(idUsuario,email){
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "${pageContext.request.contextPath}/ajax/consultasAjax1.jsp",
                                      data: "consulta=3&idUsuario="+idUsuario+"&email="+email,
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
                var idUsuario = document.getElementById("hdnIdUsuario").value;
                var nombre = document.getElementById("txtNombre");
                var paterno = document.getElementById("txtPaterno");
                var materno = document.getElementById("txtMaterno");
                var email = document.getElementById("txtEmail");                
                var grupo = document.getElementById("slcGrupo");
                var activo = document.getElementById("slcActivo");
                var login = document.getElementById("txtLogin");
                var pwd = document.getElementById("txtPWD");
                var pwd2 = document.getElementById("txtPWD2");
                
                if(Trim(nombre.value)==''){
                    jAlert('Ingrese un nombre válido', 'Error');                    
                    return false;
                }
                if(Trim(paterno.value)==''){
                    jAlert('Ingrese un apellido paterno válido', 'Error');                    
                    return false;
                }
                if(Trim(materno.value)==''){
                    jAlert('Ingrese un apellido materno válido', 'Error');                    
                    return false;
                }
                if(! /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/.test(email.value) ){
                    jAlert('Ingrese un correo electronico válido', 'Error');
                    return false;
                }
                
                if(isEmailRepetido(idUsuario,Trim(email.value))){
                    jAlert('Ya existe un usuario/alumno registrado con el mismo correo electronico. Ingrese otro', 'Error');
                    return false;
                }
                
                if(Trim(grupo.value)=='-1'){
                    jAlert('Seleccione un grupo', 'Error');
                    return false;
                }
                                                
                if(activo.value=="-1"){
                    jAlert('Seleccione un estado', 'Error');                    
                    return false;
                }
                
                if(Trim(login.value)==''){
                    jAlert('Ingrese un login/codigo válido', 'Error');                    
                    return false;
                }
                
                if(isLoginRepetido(idUsuario,Trim(login.value))){
                    jAlert('Ya existe otro alumno con el mismo login/codigo. Ingrese otro ', 'Error');
                    return false;
                }
                
                if(  Trim(pwd.value)!=Trim(pwd2.value)  ){
                    jAlert('La contrasena no coincide. Favor de corregir', 'Error');                    
                    return false;
                }
                
                
                if(idUsuario=='' && Trim(pwd.value)==''){
                    jAlert('La contrasena no puede quedar vacia', 'Error');                    
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
                        Edicion Usuario
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()" autocomplete="off">
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
                                    Correo Electronico
                                </td>
                                <td align="left">
                                    <input type="text" name="txtEmail" id="txtEmail" value="${alumno.usuario.email}" class="textbox" size="40" />
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    Grupo
                                </td>
                                <td align="left">
                                    <select id="slcGrupo" name="slcGrupo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <c:forEach var="grupoAlumno" items="${GruposAlumnos}">
                                            <option value="${grupoAlumno.idGrupoAlumnos}" <c:if test="${alumno.idGrupoAlumnos eq grupoAlumno.idGrupoAlumnos}">selected</c:if>>${grupoAlumno.grupoAlumnos}</option>
                                        </c:forEach>                                        
                                    </select>
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
                                <td colspan="2">
                                    <br />
                                    <b>Datos de acceso</b>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Login/Codigo
                                </td>
                                <td align="left">
                                    <input type="text" name="txtLogin" id="txtLogin" value="${alumno.usuario.usuario}" class="textbox" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Contrasena
                                </td>
                                <td align="left">
                                    <input type="password"  name="txtPWD" id="txtPWD" value="${alumno.usuario.password}" class="textbox" size="40" />
                                    <br />
                                    <c:if test="${alumno.usuario.idUsuario ne null}">Dejar vacio para conservar la contrasena actual</c:if>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Repetir contrasena
                                </td>
                                <td align="left">
                                    <input type="password" name="txtPWD2" id="txtPWD2" value="" class="textbox" size="40" />
                                    <br />                                    
                                    
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
