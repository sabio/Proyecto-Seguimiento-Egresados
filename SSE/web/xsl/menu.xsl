<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
    <xsl:output method="xml" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" indent="yes" /> 
    <xsl:template match="/">
        <html>
            <head>
                <title>SSE</title>
                <link rel="stylesheet" type="text/css">
                    <xsl:attribute name="href"><xsl:value-of select="MenuXML/path"/>/css/menu.css</xsl:attribute>
                </link>
                <link rel="stylesheet" type="text/css">
                    <xsl:attribute name="href"><xsl:value-of select="MenuXML/path"/>/css/paginaPrincipal.css</xsl:attribute>
                </link>
            </head>
            <body>
                <div class="principal">
                    <div class="header">
                       <div>
                            <xsl:attribute name="class">titulo</xsl:attribute>                            
                            Sistema de Seguimiento a Egresados
                       </div> 
                       <img>
                           <xsl:attribute name="src"><xsl:value-of select="MenuXML/path"/>/imagenes/logo.png</xsl:attribute>
                           <xsl:attribute name="class">imagencucea</xsl:attribute>
                       </img>  
                       <br /><br /><br />
                        <div class="menu">                           
                            <ul id="menu">
                                <xsl:for-each select="MenuXML/Nivel1">
                                    <li>
                                        <a>
                                            <xsl:choose>
                                                <xsl:when test="url!=''">
                                                    <xsl:attribute name="style">cursor:hand; cursor:pointer;</xsl:attribute>
                                                    <xsl:attribute name="onclick">
                                                            javascript:                                                                    
                                                            frames['idIframe'].window.location='<xsl:value-of select="url"/>';
                                                    </xsl:attribute>
                                                </xsl:when>                                                       
                                            </xsl:choose>
                                            <xsl:value-of select="menu"/>
                                        </a>  

                                        <ul>
                                        <xsl:for-each select="Nivel2">
                                            <li>
                                                <a>
                                                    <xsl:choose>
                                                        <xsl:when test="url!=''">
                                                            <xsl:attribute name="style">cursor:hand; cursor:pointer;</xsl:attribute>
                                                            <xsl:attribute name="onclick">
                                                                    javascript:                                                                    
                                                                    frames['idIframe'].window.location='<xsl:value-of select="url"/>';
                                                            </xsl:attribute>
                                                        </xsl:when>                                                       
                                                    </xsl:choose>
                                                    <xsl:value-of select="menu"/>
                                                </a>   
                                                <ul>
                                                <xsl:for-each select="Nivel3">
                                                    <li>                                                        
                                                        <a>
                                                            <xsl:choose>
                                                                <xsl:when test="url!=''">
                                                                    <xsl:attribute name="style">cursor:hand; cursor:pointer;</xsl:attribute>
                                                                    <xsl:attribute name="onclick">
                                                                            javascript:                                                                    
                                                                            frames['idIframe'].window.location='<xsl:value-of select="url"/>';
                                                                    </xsl:attribute>
                                                                </xsl:when>                                                       
                                                            </xsl:choose>
                                                            <xsl:value-of select="menu"/>
                                                        </a>                                          
                                                    </li>
                                                </xsl:for-each>
                                                </ul>
                                            </li>
                                        </xsl:for-each>
                                        </ul>
                                    </li>
                                </xsl:for-each>

                            </ul>
                            
                            <div class="divCuenta">
                                Bienvenido | 
                                
                                <a>
                                    <xsl:attribute name="href">
                                        <xsl:value-of select="MenuXML/path"/>/invalidate
                                    </xsl:attribute>
                                    Salir
                                </a>
                            </div>    
                        </div>
                    </div>
                    
                    <div class="contenido">
                         <!--<iframe width="100%" height="100%" name="idIframe" id="idIframe" src="jsp/index.jsp"></iframe>-->
                         <iframe>
                            <xsl:attribute name="width">100%</xsl:attribute>
                            <xsl:attribute name="height">100%</xsl:attribute>
                            <xsl:attribute name="name">idIframe</xsl:attribute>
                            <xsl:attribute name="id">idIframe</xsl:attribute>
                            <xsl:attribute name="style">border:none</xsl:attribute>
                            <xsl:attribute name="src"><xsl:value-of select="MenuXML/urlFrame"/></xsl:attribute>
                         </iframe>
                         
                             
                    </div>                        

                    <div class="footer">
                        <div style="padding-left:30px; text-align:left;">
                            <img>
                                   <xsl:attribute name="src"><xsl:value-of select="MenuXML/path"/>/imagenes/bg_firm.gif</xsl:attribute>                               
                            </img>
                        </div>
                        <div style="padding-left:77px; text-align:left;">
                            <strong>©2010 - 2013 Centro Universitario de Ciencias Económico Administrativas.</strong>
                            <span style="color:gray">
                                <br />
                                Coordinación de la Maestría en Tecnologías de Información.
                                <br />
                                Periférico Norte N° 799, Núcleo Universitario Los Belenes, C.P. 45100, Zapopan, Jalisco, México.
                                <br />
                            </span>
                            <strong>Teléfonos: (0133) 3770- 3440, conmutador 3770 3300 Extensiones 25326 y 25327.</strong>
                        </div>
                    </div>
                    
                </div>
                
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
