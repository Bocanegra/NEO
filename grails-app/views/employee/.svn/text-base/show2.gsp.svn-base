<html>
  <head>
    <meta name="layout" content="itheme"/>
  </head>
  <body>

  <div id="wrapper">
    <g:if test="${sme.logo}">
      <img alt="(logo)" src="${createLink(controller:'employee', action:'logo_image', id:sme.cif)}"/>
    </g:if>
  </div>

  <div id="left-col">
    <div id="nav">
      <ul>
        <li class="page_item current_page_item"><a href="${createLink(controller:'employee')}" title="Noticeboard">Home</a></li>
        <li class="page_item"><a href="${createLink(controller:'employee', action:'blog')}" title="Blog">Blog</a></li>
        <li class="page_item"><a href="${createLink(controller:'employee', action:'feed')}" title="Feeds">Feeds</a></li>
        <li class="page_item"><a href="${createLink(controller:'employee', action:'logout')}" title="Logout">Logout</a></li>
      </ul>
    </div><!-- /nav -->

    <div id="content">

    <div class="post">
      <div class="title"><h2>SME description</h2></div>
      <div class="entry">
        ${sme.description}
      </div>
    </div>

    <!-- NOTICE BOARD -->
    <% sme.returnNoticeBoard().returnLastNotices(10, 0).each { notice -> %>
      <div class="post" id="${notice.id}">
        <div class="date"><span>${notice.dateCreated.format("MMM")}</span> ${notice.dateCreated.format("dd")}</div>
        <div class="title">
          <h2>${notice.title}</h2>
          <div class="postdata">
            <span class="category">
              <a href="${createLink(controller:'employee', action:'deleteNotice.html', params:[senderCompany:sme.cif, idNotice:notice.id])}">Delete Notice</a></span>
            <span class="comments">Notice sent by: ${notice.sender} at ${notice.dateCreated.format("HH:mm")}</span>
          </div>
        </div>
        <div class="entry">
          ${notice.text}
        </div><!--/entry -->
      </div><!-- /post -->
      <br/>
    <% } %>

    <h3 id="respond">Add Notice</h3>
    <h3>The notice will be show to other members of the SME, and to other SMEs contacting you</h3>
    <g:form class="commentform" action="addNotice.html" method="post">
      <p>
        <input type="text" name="title" id="author" value="" size="22" tabindex="1"/>
        <label for="author"><strong>Title</strong></label>
      </p>
      <p><textarea name="text" id="text" cols="100%" rows="10" tabindex="4"></textarea></p>
      <p><input name="submit" type="submit" id="submit" tabindex="5" value="Add Notice"/></p>
      <g:hiddenField name="senderUser" value="${sme.primaryUser}"/>
      <g:hiddenField name="senderCompany" value="${sme.cif}"/>
    </g:form>

  </div> <!--/content -->

    <!-- Se mete el pie -->
    <g:render template="/footer"/>

  </div><!-- /left-col -->

  <div id="sidebar" class="dbx-group">
    <g:each in="${sme.returnContactList().contacts}" var="contact">
      <% Sme smeContact=Sme.getById(contact.sme) %>
      <div id="${smeContact.id}" class="dbx-box">
        <h3 class="dbx-handle">${smeContact.name}</h3>
        <div class="dbx-content">
          <ul>
            <li>Address: ${smeContact.address}</li>
            <li>Town: ${smeContact.town}</li>
            <li>Sector: ${smeContact.sector}</li>
            <li><a href="${smeContact.url}">URL: ${smeContact.url}</a></li>
            <li><a href="tel:${smeContact.telephoneNumber}">Telephone: ${smeContact.telephoneNumber}</a></li>
            <li><a href="${createLink(controller:'employee', action:'renderContent', params:[smeToRender:smeContact.cif])}">More details...</a></li>
          </ul>
        </div>
      </div>
    </g:each>
  </div><!--/sidebar -->
  <hr class="hidden"/>

  </body>
</html>
