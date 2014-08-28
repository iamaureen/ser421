<xsl:stylesheet version="1.0"
     xmlns="http://www.w3.org/1999/xhtml"
     xmlns:jml="http://www.brics.dk/ixwt/jokes"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="jml:collection">
    <html>
      <head>
        <title>Jokes</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>
      </head>
      <body>
        <hr/>
        <ul>
          <xsl:apply-templates select="jml:joke" mode="summary"/>
        </ul>
        <xsl:apply-templates select="jml:joke" mode="full"/>
        <hr/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="jml:joke" mode="summary">
    <li><a href="#{@id}"><xsl:value-of select="jml:title"/></a></li>
  </xsl:template>

  <xsl:template match="jml:joke" mode="full">
    <hr/>
    <h3><a name="{@id}"><xsl:value-of select="jml:title"/></a></h3>
    <i><xsl:value-of select="jml:author"/> (<xsl:value-of select="jml:date"/>)</i>
    <ul>
      <xsl:apply-templates select="jml:setup"/>
      <xsl:apply-templates select="jml:punchline"/>
    </ul>
  </xsl:template>

  <xsl:template match="jml:setup|jml:punchline">
    <li><xsl:value-of select="."/></li>
  </xsl:template>
</xsl:stylesheet>

