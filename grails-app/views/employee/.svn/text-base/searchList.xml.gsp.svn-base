<g:applyLayout name="xml">
<result>
  <code>${code}</code>
  <message><g:message code="${info}"/></message>
  <% if (suggestion!=null) { %>
    <sugested_query>${suggestion}</sugested_query>
  <% } %>

  <% if (data!=null) { %>
  <data>
    <size>${data.size()}</size>
    <% data.each { sme -> %>
      <sme id="${sme.id}">
        <cif>${sme.cif}</cif>
        <name>${sme.name}</name>
        <address>${sme.address}</address>
        <town>${sme.town}</town>
        <state>${sme.state}</state>
        <country>${sme.country}</country>
        <zipCode>${sme.zipCode}</zipCode>
        <url>${sme.url}</url>
        <telephoneNumber>${sme.telephoneNumber}</telephoneNumber>
        <cellularNumber>${sme.cellularNumber}</cellularNumber>
        <faxNumber>${sme.faxNumber}</faxNumber>
        <contactEmail>${sme.contactEmail}</contactEmail>
        <sector>${sme.sector}</sector>
        <recommendations>
          <size>${sme.numRecommendations}</size>
          <% sme.returnInfoRecommendations().each { rec ->
             Sme smeOrig=Sme.getById(rec.key) %>
            <recommendation id="${rec.value.id}">
              <recommender><![CDATA[${smeOrig.name}]]></recommender>
              <recommenderDescription><![CDATA[${smeOrig.getDescription()}]]></recommenderDescription>
              <dateCreated>${rec.value.dateCreated}</dateCreated>
              <description><![CDATA[${rec.value.description}]]></description>
            </recommendation>
          <% } %>
        </recommendations>
        <urlLogo>${sme.urlLogo}</urlLogo>
        <logoType>${sme.logoType}</logoType>
        <dateCreated>${sme.dateCreated.format("dd/MM/yyyy")}</dateCreated>
        <description><![CDATA[${sme.description}]]></description>
        <solutions><![CDATA[${sme.solutions}]]></solutions>
        <products><![CDATA[${sme.products}]]></products>
      </sme>
    <% } %>
  </data>
  <% } %>
</result>
</g:applyLayout>
