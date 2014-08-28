<xsl:stylesheet version="2.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
  <xsl:output method="html"/>

  <xsl:template match="/">
    <html><head><title>XML file</title></head><body>
      <xsl:apply-templates/>
    </body></html>
  </xsl:template>

  <xsl:template match="*">
    <xsl:param name="ind" select="0"/>
    <xsl:call-template name="indent">
      <xsl:with-param name="ind" select="$ind"/>
    </xsl:call-template>
    <xsl:text>&lt;</xsl:text><xsl:value-of select="name()"/>
    <xsl:apply-templates select="attribute::*"/>
    <xsl:text>&gt;</xsl:text><br/>
    <xsl:apply-templates>
      <xsl:with-param name="ind" select="$ind + 1"/>
    </xsl:apply-templates>
    <xsl:call-template name="indent">
      <xsl:with-param name="ind" select="$ind"/>
    </xsl:call-template>
    <xsl:text>&lt;/</xsl:text><xsl:value-of select="name()"/><xsl:text>&gt;</xsl:text><br/>
  </xsl:template>

  <xsl:template match="text()">
    <xsl:param name="ind"/>
    <xsl:call-template name="indent">
      <xsl:with-param name="ind" select="$ind"/>
    </xsl:call-template>
    <xsl:value-of select="normalize-space(.)"/><br/>
  </xsl:template>

  <xsl:template match="attribute()">
    <xsl:text> </xsl:text>
    <xsl:value-of select="name()"/>="<xsl:value-of select="."/><xsl:text>"</xsl:text>
  </xsl:template>

  <xsl:template name="indent">
    <xsl:param name="ind"/>
    <xsl:value-of select="for $i in (1 to $ind) return '&#xa0;&#xa0;'"/>
  </xsl:template>

</xsl:stylesheet>
