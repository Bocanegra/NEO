<g:applyLayout name="xml">
<result>
  <code>${code}</code>
  <message><g:message code="${info}"/></message>
  <smes>
    <g:each in="${data}" status="i" var="smeInstance">
      <sme id="${smeInstance.id}">
        <cif>${smeInstance.cif}</cif>
        <name>${smeInstance.name}</name>
      </sme>
    </g:each>
  </smes>
</result>
</g:applyLayout>
