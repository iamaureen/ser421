<xsl:stylesheet version="2.0"
     xmlns="http://www.brics.dk/ixwt/categories"
     xmlns:jml="http://www.brics.dk/ixwt/jokes"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="jml:collection">
    <categories>
      <xsl:for-each-group select="jml:joke" group-by="@category">
        <category>
          <xsl:value-of select="current-grouping-key()"/>
        </category>
    </categories>
  </xsl:template>

</xsl:stylesheet>
