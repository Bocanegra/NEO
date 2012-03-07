<g:applyLayout name="xml">
<result>
  <code>${code}</code>
  <message><g:message code="${info}"/></message>

  <% if (employee!=null) { %>
  <employee id="${employee.id}">
    <nif>${employee.nif}</nif>
    <name>${employee.name}</name>
    <surname>${employee.surname}</surname>
    <smsAdvice>${employee.smsAdvice}</smsAdvice>
    <mailAdvice>${employee.mailAdvice}</mailAdvice>
    <dateCreated>${employee.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
    <avatar>${employee.avatar?.encodeAsBase64()}</avatar>
    <avatarType>${employee.avatarType}</avatarType>
    <memberships>
      <% employee.memberships.each { member -> %>
        <membership id="${member.id}">
          <email>${member.email}</email>
          <telephoneNumber>${member.telephoneNumber}</telephoneNumber>
          <cellularNumber>${member.cellularNumber}</cellularNumber>
        </membership>
      <% } %>
    </memberships>
  </employee>
  <% } %>

  <% if (sme!=null) { %>
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
    <numRecommendations>${sme.numRecommendations}</numRecommendations>
    <urlLogo>${sme.urlLogo}</urlLogo>
    <logoType>${sme.logoType}</logoType>
    <dateCreated>${sme.dateCreated.format("dd/MM/yyyy")}</dateCreated>
    <description><![CDATA[${sme.description}]]></description>
    <solutions><![CDATA[${sme.solutions}]]></solutions>
    <products><![CDATA[${sme.products}]]></products>
  </sme>
  <%
    def visibility=[:]
    sme.contents.each {
      visibility.put(new String("${it.class}"),new String("${it.visibility}"))
    }
  %>

  <employees>
  <g:each in="${sme.employees()}" var="employee">
    <employee>
      <name>${employee.name+' '+employee.surname}</name>
      <nif>${employee.nif}</nif>
      <position>${employee.position}</position>
      <urlAvatar>${employee.urlAvatar}</urlAvatar>
      <primaryUser>${sme.primaryUser.equals(employee.nif)}</primaryUser>
    </employee>
  </g:each>
  </employees>

  <feed visibility="${visibility['class Feed']}">
    <g:if test="${sme.returnFeed()}">
      <size>${sme.returnFeed().notifications.size()}</size>
      <% sme.returnFeed().returnLastNotifications(nmaxNotif?:"10", noffsetNotif?:"0").eachWithIndex { notif, idx -> %>
        <notification>
          <n>${Integer.parseInt(noffsetNotif?:"0")+idx+1}</n>
          <title_notification>${notif.title}</title_notification>
          <dateCreated>${notif.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
          <text><![CDATA[${notif.text}]]></text>
        </notification>
      <% } %>
      <lastlogoutMessages>${sme.returnMessageBox().returnMessagesFromLastLogout(employee).size()}</lastlogoutMessages>
      <lastlogoutContacts>${sme.returnContactList().getInactiveContacts().size()}</lastlogoutContacts>
      <lastlogoutNotices>${sme.returnNoticeBoard().returnNoticesFromLastLogout(employee.lastLogout).size()}</lastlogoutNotices>
      <lastlogoutRecommendations>${sme.returnRecommendationBox().returnRecommendationsFromLastLogout(employee.lastLogout).size()}</lastlogoutRecommendations>
      <lastlogoutPosts>${sme.returnBlog().returnPostsFromLastLogout(employee.lastLogout).size()}</lastlogoutPosts>
      <lastlogoutFeeds>${sme.returnFeed().returnNotifsFromLastLogout(employee.lastLogout).size()}</lastlogoutFeeds>
    </g:if>
  </feed>
  <blog visibility="${visibility['class Blog']}">
    <g:if test="${sme.returnBlog()}">
      <size>${sme.returnBlog().posts.size()}</size>
      <% sme.returnBlog().returnLastPosts(nmaxPosts?:"10", noffsetPosts?:"0").eachWithIndex { post, idx -> %>
        <post>
          <n>${Integer.parseInt(noffsetPosts?:"0")+idx+1}</n>
          <post_id>${post.id}</post_id>
          <author>${post.author}</author>
          <title_post><![CDATA[${post.title}]]></title_post>
          <dateCreated>${post.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
          <text><![CDATA[${post.text}]]></text>
        </post>
      <% } %>
    </g:if>
  </blog>
  <messagebox visibility="${visibility['class MessageBox']}">
    <g:if test="${sme.returnMessageBox()}">
      <size>${sme.returnMessageBox().messages.size()}</size>
      <% sme.returnMessageBox().messages.each { message -> %>
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
  </messagebox>
  <noticeboard visibility="${visibility['class NoticeBoard']}">
    <g:if test="${sme.returnNoticeBoard()}">
      <size>${sme.returnNoticeBoard().notices.size()}</size>
      <% sme.returnNoticeBoard().returnLastNotices(10, 0).each { notice -> %>
        <notice>
          <notice_id>${notice.id}</notice_id>
          <sender>${Sme.getById(notice.sender).name}</sender>
          <title_post><![CDATA[${notice.title}]]></title_post>
          <dateCreated>${notice.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
          <text><![CDATA[${notice.text}]]></text>
        </notice>
      <% } %>
    </g:if>
  </noticeboard>
  <recommendationbox visibility="${visibility['class RecommendationBox']}">
    <g:if test="${sme.returnRecommendationBox()}">
      <size>${sme.returnRecommendationBox().recommendations.size()}</size>
      <% sme.returnRecommendationBox().returnLastRecommendations(10, 0).each { recommendation -> %>
        <notice>
          <recommendedSme>${recommendation.recommendedSme}</recommendedSme>
          <name>${Sme.getById(recommendation.recommendedSme).name}</name>
          <description><![CDATA[${recommendation.description}]]></description>
          <dateCreated>${recommendation.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
        </notice>
      <% } %>
    </g:if>
  </recommendationbox>
  <contactlist visibility="${visibility['class ContactList']}">
    <g:if test="${sme.returnContactList()}">
      <size>${sme.returnContactList().contacts.size()}</size>
      <% (sme.returnContactList().getStrongContacts()+sme.returnContactList().getWeakContacts()).each { contact ->
           Sme smeContact=Sme.getById(contact.sme)
      %>
        <contact>
          <sme>${contact.sme}</sme>
          <name>${contact.smeName}</name>
          <state>${contact.state}</state>
          <recommender>${contact.recommender}</recommender>
          <dateCreated>${contact.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
          <numRecommendations>${smeContact.numRecommendations}</numRecommendations>
          <urlLogo>${smeContact.urlLogo}</urlLogo>
        </contact>
      <% } %>
      <% sme.returnContactList().getInactiveContacts().each { pendingContact -> %>
        <pending_contact>
          <sme>${pendingContact.sme}</sme>
          <name>${pendingContact.smeName}</name>
          <state>${pendingContact.state}</state>
          <recommender>${pendingContact.recommender}</recommender>
          <dateCreated>${pendingContact.dateCreated.format(grailsApplication.config.neo.date.format)}</dateCreated>
        </pending_contact>
      <% } %>
    </g:if>
  </contactlist>

  <% } %>

</result>
</g:applyLayout>
