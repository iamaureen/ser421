<xsl:stylesheet version="2.0"
     xmlns="http://www.brics.dk/ixwt/categories"
     xmlns:jml="http://www.brics.dk/ixwt/jokes"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="jml:collection">
    <categories>
      <xsl:apply-templates select="jml:joke[not(@category=following::jml:joke/@category)]"/>
    </categories>
  </xsl:template>

  <xsl:template match="jml:joke">
    <category>
      <xsl:value-of select="@category"/>
    </category>
  </xsl:template>
</xsl:stylesheet>
