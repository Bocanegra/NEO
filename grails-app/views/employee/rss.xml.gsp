<g:applyLayout name="xml">
<result>
  <code>${code}</code>
  <message><g:message code="${info}"/></message>

  <data>
  <g:each in="${data}" var="canal">
    <channel title="${canal.key}">
      <size>${canal.value.size()}</size>
      <g:each in="${canal.value}" status="i" var="item">
        <item id="${i}">
          <title_item><![CDATA[${item.title.text()}]]></title_item>
          <g:if test="${item.description}">
            <description><![CDATA[${item.description.text()}]]></description>
          </g:if>
          <g:if test="${item.content}">
            <description><![CDATA[${item.content.text()}]]></description>
          </g:if>
          <g:if test="${item.link}">
            <feed_link><![CDATA[${item.link.text()}]]></feed_link>
          </g:if>
          <g:if test="${item.pubDate}">
            <pubDate><![CDATA[${item.pubDate.text()}]]></pubDate>
          </g:if>
        </item>
      </g:each>
    </channel>
  </g:each>
  </data>

</result>
</g:applyLayout>
