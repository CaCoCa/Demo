<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="3.0">
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <th>
                        <td>name</td>
                        <td>country</td>
                        <td>type</td>
                        <td>accountId</td>
                        <td>depositor</td>
                        <td>amount</td>
                        <td>profitability</td>
                        <td>constraints</td>
                    </th>
                    <xsl:for-each select="banks/bank">
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="country"/>
                            </td>
                            <td>
                                <xsl:value-of select="deposit/@type"/>
                            </td>
                            <td>
                                <xsl:value-of select="deposit/@accountId"/>
                            </td>
                            <td>
                                <xsl:value-of select="depositor"/>
                            </td>
                            <td>
                                <xsl:value-of select="amount"/>
                            </td>
                            <td>
                                <xsl:value-of select="profitability"/>
                            </td>
                            <td>
                                <xsl:value-of select="constraints"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>