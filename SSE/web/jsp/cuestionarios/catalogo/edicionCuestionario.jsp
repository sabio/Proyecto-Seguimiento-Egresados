<%-- 
    Document   : edicionCuestionario
    Created on : Dec 5, 2011, 6:27:45 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${guardadoExitoso}">
            <script type="text/javascript">
                window.location = 'listadoCuestionarios.run';
            </script>
            
        </c:if>
        <jsp:include page="/jsp/includes/cabecera1.jsp" />    
        

        <script type="text/javascript">
            function setPageStatus(){                              
            }
            
            function isCuestionarioRepetido(idCuestionario,cuestionario){
                 var respuesta = $.ajax({
                                      type: "GET",
                                      url: "/SSE/ajax/consultasAjax1.jsp",
                                      data: "consulta=6&idCuestionario="+idCuestionario+"&cuestionario="+cuestionario,
                                      async:false
                                  }).responseText;
                
                if(parseInt(respuesta)>0)                
                    return true;
                else 
                    return false;
            }                   
            
            function hacerSubmit(){
                var cuestionario = document.getElementById("txtCuestionario");
                var activo = document.getElementById("slcActivo");
                if(Trim(cuestionario.value)==''){
                    jAlert('Ingrese el nombre del cuestionario', 'Error');                    
                    return false;
                }                                
                
                if(activo.value=="-1"){
                    jAlert('Seleccione un estado', 'Error');                    
                    return false;
                }
                
                if(isCuestionarioRepetido())
                
                return true;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion Cuestionario</title>
    </head>
    <body onload="setPageStatus();">
         <div class="principal">
            <div class="header">
                <hr />
                    <span id="titulo"> 
                        Edicion cuestionario
                    </span>
                <hr />
            </div>
            <div class="contenido">
                <form id="formaCatalogo" name="formaCatalogo" onsubmit="return hacerSubmit()">
                    <input type="hidden" name="idCuestionario" id="idCuestionario" value="${cuestionario.idCuestionario}" />
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
                                    Cuestionario
                                </td>
                                <td align="left">
                                    <input type="text" name="txtCuestionario" id="txtCuestionario" value="${cuestionario.cuestionario}" class="textbox" size="40" />
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    Activo
                                </td>
                                <td align="left">
                                    <select id="slcActivo" name="slcActivo"  class="textbox"> 
                                        <option value="-1">Seleccione...</option>
                                        <option value="S" <c:if test="${cuestionario.activo eq 'S' or cuestionario.activo eq null}">selected</c:if>>Si</option>
                                        <option value="N" <c:if test="${cuestionario.activo eq 'N'}">selected</c:if>>No</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <table border="1">
                                        
                                    </table>
                                    
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
