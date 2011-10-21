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
                            HEader
                        <div class="menu">
                            Aqui va el menú
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
                        </div>
                    </div>
                    
                    <div class="contenido">
                         <iframe width="100%" height="100%" name="idIframe" id="idIframe" src="jsp/index.jsp"></iframe>
                    </div>                        

                    
                    <div class="footer">
                            footer
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
