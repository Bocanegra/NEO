<html>
  <head>
    <meta name="layout" content="itheme"/>
  </head>
  <body>

  <div id="left-col">
    <div id="nav">
      <ul>
        <li class="page_item"><a href="${createLink(controller:'employee')}" title="Employee">Home</a></li>
        <li class="page_item current_page_item"><a href="${createLink(controller:'employee', action:'blog')}" title="Blog">Blog</a></li>
        <li class="page_item"><a href="${createLink(controller:'employee', action:'logout')}" title="Logout">Logout</a></li>
      </ul>
    </div><!-- /nav -->

    <div id="content">

    <!-- BLOG -->
    <% sme.returnBlog().returnLastPosts(10, 0).each { post -> %>
      <div class="post" id="${post.id}">
        <div class="date"><span>${post.dateCreated.format("MMM")}</span> ${post.dateCreated.format("dd")}</div>
        <div class="title">
          <h2>${post.title}</h2>
          <div class="postdata">
            <span class="comments">Blog comentary by: ${post.author}</span>
          </div>
        </div>
        <div class="entry">
          ${post.text}
        </div><!--/entry -->
      </div><!-- /post -->
      <br/>
    <% } %>

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
