<%-- 
    Document   : cuestionario
    Created on : Dec 3, 2011, 11:12:47 PM
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${cerrarVentana eq true}">
            <script type="text/javascript">
                window.opener.location.reload(true);
                window.close();
            </script>
        </c:if>
        
        <jsp:include page="/jsp/includes/cabecera1.jsp" />
        
        <script type="text/javascript">
            function getCheckedValue(radioObj) {
                if(!radioObj)
                        return "";
                var radioLength = radioObj.length;
                if(radioLength == undefined)
                        if(radioObj.checked)
                                return radioObj.value;
                        else
                                return "";
                for(var i = 0; i < radioLength; i++) {
                        if(radioObj[i].checked) {
                                return radioObj[i].value;
                        }
                }
                return "";
            }
            
            function hacerSubmit(opcion){
                var forma = document.getElementById("forma");
                var accion = document.getElementById("accion");
                accion.value=opcion;                
                
                if(opcion==1){
                    forma.submit();
                }
                else{
                    if(validar()){
                        forma.submit();
                    }
                }
            }
            
            function validar(){                                
                var cantElementos = document.forma.elements.length;
                
                var i;
                for(i=0;i<cantElementos;i++){
                    var campo = document.forma.elements[i];

                    if (campo.name.indexOf("pregunta") !=-1) {
                        var nombreSplit = campo.name.split('-');
                        //alert("campo = "+campo.name+"     valor = "+campo.value);
                        
                        if(parseInt(nombreSplit[1])==1){
                            if(Trim(campo.value)==''){
                                jAlert('Quedo al menos una pregunta sin contestar. Favor de revisar.', 'Error'); 
                                campo.focus();
                                return false;
                            }
                        }
                        //else if(parseInt(nombreSplit[1])==2){
                        else{
                            //alert("valor de "+nombreSplit[0]+"-"+nombreSplit[1]+"-"+nombreSplit[2]+" = "+document.getElementById(nombreSplit[0]+"-"+nombreSplit[1]+"-"+nombreSplit[2]));
                            //document.forms['radioExampleForm'].elements['number']
                            if(getCheckedValue(document.getElementsByName(campo.name))=="" ){
                                jAlert('Quedo al menos una pregunta sin contestar. Favor de revisar.', 'Error'); 
                                campo.focus();
                                return false;
                            }
                            
                            
                        }
                        
                    }
                }
                
                return true;
            }
        </script>
        
    </head>
     <body>
         <form target="cuestionario" id="forma" name="forma" action="cuestionario.run" method="post" >
             <input type="hidden" name="idAsignacion" id="idAsignacion" value="${idAsignacion}" />
             <input type="hidden" name="accion" id="accion" />
        
             <div class="principal">
                <div class="header">
                    <hr />
                        <span id="titulo"> 
                            Bienvenido
                        </span>
                    <hr />
                </div>
                <div class="contenido">
                    <div>
                        Bienvenido al Sistema de Seguimiento a Egresados de la Maestría en Tecnologías de Información.
                    </div>
                    <br />
                    <br />
                    
                    <table>
                        
                        <c:forEach varStatus="status" var="pregunta" items="${preguntas}" >
                            <tr align="left">
                                <td>
                                    <b>${status.count}.</b> ${pregunta.pregunta}
                                </td>
                            </tr>
                            <tr align="left">
                                <td>
                                    <c:choose>
                                        <c:when test="${pregunta.idTipoPregunta eq 1}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <textarea cols="70" rows="7" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}">${pregunta.respuestaString}</textarea>
                                        </c:when>
                                        <c:when test="${pregunta.idTipoPregunta eq 2}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            5
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-5" value="5" <c:if test="${pregunta.respuestaInt eq 5}">checked</c:if> >
                                            &nbsp;&nbsp;
                                            4
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-4" value="4" <c:if test="${pregunta.respuestaInt eq 4}">checked</c:if> >
                                            &nbsp;&nbsp;
                                            3
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-3" value="3" <c:if test="${pregunta.respuestaInt eq 3}">checked</c:if> >
                                            &nbsp;&nbsp;
                                            2
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-2" value="2" <c:if test="${pregunta.respuestaInt eq 2}">checked</c:if> >
                                            &nbsp;&nbsp;
                                            1
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-1" value="1" <c:if test="${pregunta.respuestaInt eq 1}">checked</c:if> >
                                        </c:when>
                                        <c:when test="${pregunta.idTipoPregunta eq 3}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            Si
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-Si" value="1" <c:if test="${pregunta.respuestaInt eq 1}">checked</c:if> >
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            No
                                            <input type="radio" name="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}" id="pregunta-${pregunta.idTipoPregunta}-${pregunta.idPregunta}-No" value="2" <c:if test="${pregunta.respuestaInt eq 2}">checked</c:if> >
                                        </c:when>                                        
                                    </c:choose>
                                    <br /><br />
                                </td>
                            </tr>
                        </c:forEach>
                            
                            <tr>
                                <td>
                                    <br /><br />
                                </td>
                            </tr>    
                            <tr>
                                <td>
                                    <input type="button" class="boton" name="boton1" value="Terminar" onclick="hacerSubmit(2)" />
                                    <input type="button" class="boton" name="boton2" value="Guardar y continuar despues" onclick="hacerSubmit(1)" />
                                </td>
                            </tr>    
                    </table>
                    
                </div>
            </div>    
        </form>
           
    </body>
</html>
