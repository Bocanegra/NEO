<g:applyLayout name="xml">
<result>
  <code>${code}</code>
  <message><g:message code="${info}"/></message>

  <messages>
    <g:if test="${messagedata}">
      <size>${messagedata.size()}</size>
      <% messagedata.each { message -> %>
        <message>
          <message_id>${message.id}</message_id>
          <senderUser>${message.senderUser}</senderUser>
          <senderSme>${message.senderSme}</senderSme>
          <senderInfo>${Employee.getById(message.senderUser)?.name}</senderInfo>
          <senderSmeInfo>${Sme.getById(message.senderSme)?.name}</senderSmeInfo>
          <addressee>${message.addressee}</addressee>
          <title_message><![CDATA[${message.title}]]></title_message>
          <text><![CDATA[${message.text}]]></text>
          <dateCreated>${message.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
        </message>
      <% } %>
    </g:if>
  </messages>

</result>
</g:applyLayout>
