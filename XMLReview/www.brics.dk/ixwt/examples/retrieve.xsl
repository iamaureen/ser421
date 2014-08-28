<xsl:stylesheet version="2.0"
     xmlns="http://www.brics.dk/ixwt/categories"
     xmlns:jml="http://www.brics.dk/ixwt/jokes"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:param name="category"/>

  <xsl:template match="jml:collection">
    <xsl:copy>
      <xsl:apply-templates select="jml:joke[@category=$category]"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="/|@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
