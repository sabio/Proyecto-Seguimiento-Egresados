<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>SSE</title>
                <link rel="stylesheet" type="text/css">
                    <xsl:attribute name="href"><xsl:value-of select="MenuXML/path"/>/css/menu.css</xsl:attribute>
                </link>
                <link rel="stylesheet" type="text/css">
                    <xsl:attribute name="href"><xsl:value-of select="MenuXML/path"/>/css/page.css</xsl:attribute>
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
                                            <xsl:value-of select="menu"/>
                                        </a>

                                        <ul>
                                        <xsl:for-each select="Nivel2">
                                            <li>
                                                <a>
                                                    <xsl:value-of select="menu"/>
                                                </a>   
                                                <ul>
                                                <xsl:for-each select="Nivel3">
                                                    <li>
                                                        <a>
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
                         <iframe width="100%" height="100%" src="jsp/index.jsp"></iframe>
                    </div>                        

                    
                    <div class="footer">
                            footer
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
