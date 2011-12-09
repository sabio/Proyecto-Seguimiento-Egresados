<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Seguimiento a Egresados SSE</title>
        <link rel='stylesheet'  href='css/login.css' type='text/css'  /> 
        <link rel='stylesheet'  href='css/pagina.css' type='text/css'  /> 
    </head>
    <body style="background-color: #b0b0b0">
        <div id="contenedor">            
            <div id="login">
                    <div id="headerLogin">
                        <img src="imagenes/bg_firm.gif" />
                    </div>
                    <h3>Sistema de Seguimiento a Egresados</h3>
                    <form name="loginform" id="loginform" method="post" target="login.run">
                            <table align="center">
                                <tr>
                                    <td align="right">
                                        <label>Usuario:</label>
                                    </td>
                                    <td align="left">
                                        <input type="text" name="txtUsuario" id="txtUsuario" value="" size="20" tabindex="10"  class="textbox" />
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td align="right">
                                        <label>Contraseña:</label>
                                    </td>
                                    <td align="left">
                                        <input type="password" name="txtPassword" id="txtPassword" value="" size="20" tabindex="20" class="textbox" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" name="wp-submit" id="wp-submit" value="Ingresar" tabindex="100"  class="boton" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <c:if test="${invalido}">
                                            Usuario y/o contraseña inválidos
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                    </form>
                    
            </div>
        </div> 
    </body>
</html>
