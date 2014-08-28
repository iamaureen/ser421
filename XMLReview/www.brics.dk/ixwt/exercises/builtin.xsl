<xsl:stylesheet version="2.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
     xmlns:rcp="http://www.brics.dk/ixwt/recipes">

  <xsl:template match="rcp:collection">
    <units>
      <xsl:apply-templates select="rcp:recipe"/>
    </units>
  </xsl:template>

  <xsl:template match="rcp:ingredient">
    <unit>
      <xsl:value-of select="@unit"/>
    </unit>
  </xsl:template>

</xsl:stylesheet>

