<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://businesscard.org"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

  <xsl:template match="b:card">
    <fo:root>
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simple"
                               page-height="5.5cm" 
                               page-width="8.6cm"
                               margin-top="0.4cm" 
                               margin-bottom="0.4cm" 
                               margin-left="0.4cm" 
                               margin-right="0.4cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simple">
        <fo:flow flow-name="xsl-region-body">
          <fo:table>
            <fo:table-column column-width="5cm"/>
            <fo:table-column column-width="0.3cm"/>
            <fo:table-column column-width="2.5cm"/>
            <fo:table-body>
              <fo:table-row>
                <fo:table-cell>
                  <fo:block font-size="18pt" 
                            font-family="sans-serif" 
                            line-height="20pt"
                            background-color="#A0D0FF"
                            padding-top="3pt">
                    <xsl:value-of select="b:name"/>
                  </fo:block>
                  <fo:block font-size="14pt"
                            font-family="sans-serif"
                            line-height="16pt"
                            padding-top="7pt">
                    <xsl:value-of select="b:title"/>
                  </fo:block>
                  <fo:block font-size="12pt"
                            font-family="Courier"
                            line-height="16pt"
                            padding-top="7pt">
                    <xsl:value-of select="b:email"/>
                  </fo:block>
                  <xsl:if test="b:phone">
                    <fo:block font-size="14pt"
                              font-family="sans-serif"
                              line-height="16pt"
                              padding-top="7pt">
                      <xsl:value-of select="b:phone"/>
                    </fo:block>
                  </xsl:if>
                </fo:table-cell>
                <fo:table-cell/>
                <fo:table-cell>
                  <xsl:if test="b:logo">
                    <fo:block>
                      <fo:external-graphic src="url({b:logo/@uri})" 
                                           content-width="2.5cm"/>
                    </fo:block>
                  </xsl:if> 
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
        </fo:flow> 
      </fo:page-sequence> 
    </fo:root>
  </xsl:template> 

</xsl:stylesheet>
