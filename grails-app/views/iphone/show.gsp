<ul id="datos_sme" title="${sme.name}">
  <li class="group">SME Data</li>
  <li><a href="#contactlist">Contact List (${sme.returnContactList().contacts.size()})...</a></li>
  <li><a href="#feed">Feeds (${sme.returnFeed().notifications.size()})</a></li>
  <li><a href="#noticeboard">Noticeboard (${sme.returnNoticeBoard().notices.size()})</a></li>
  <li class="group">What to do...</li>
  <li><a href="#changeprofile">Change profile...</a></li>
  <li><a href="#sendnotice">Send notice</a></li>
  <li><a href="#requestcontact">Request contact</a></li>
  <li><a href="#search">Search</a></li>
</ul>

<ul id="changeprofile" title="Change profile">
  <li><a href="#profilesme">Change SME data</a></li>
  <li><a href="#profilevisibility">Change visibility profile</a></li>
  <li><a href="#profileemployee">Change my data</a></li>
</ul>

<ul id="contactlist" title="Contact List">
  <g:if test="${sme.returnContactList().getStrongContacts()}">
    <li class="group">Strong contacts</li>
    <g:each in="${sme.returnContactList().getStrongContacts()}" var="contact">
      <li><a href="#c${contact.id}">${Sme.getById(contact.sme)}</a></li>
    </g:each>
  </g:if>
  <g:if test="${sme.returnContactList().getWeakContacts()}">
    <li class="group">Normal contacts</li>
    <g:each in="${sme.returnContactList().getWeakContacts()}" var="contact">
      <li><a href="#c${contact.id}">${Sme.getById(contact.sme)}</a></li>
    </g:each>
  </g:if>
  <g:if test="${sme.returnContactList().getInactiveContacts()}">
    <li class="group">Pending confirmation</li>
    <g:each in="${sme.returnContactList().getInactiveContacts()}" var="contact">
      <li><a href="#c${contact.id}">${Sme.getById(contact.sme)}</a></li>
    </g:each>
  </g:if>
</ul>

<g:each in="${sme.returnContactList().contacts}" var="contact">
  <% Sme smeContact=Sme.getById(contact.sme) %>
  <div id="c${contact.id}" class="panel" title="SME info">
    <h2>SME name: ${smeContact.name}
      <% smeContact.numRecommendations.times {
        %><img src="${createLinkTo(dir: 'images', file: 'starrated_12x12.gif')}"/><%
      } %>
    </h2>
    <fieldset>
      <div class="row">
        <label>Location: <a href="http://maps.google.com/maps?q=${smeContact.address}, ${smeContact.town}">${smeContact.address}, ${smeContact.town}</a></label>
      </div>
      <div class="row">
        <label>Telephone number: <a href="tel:${smeContact.telephoneNumber}">${smeContact.telephoneNumber}</a></label>
      </div>
      <div class="row">
        <label>Cellular number: <a href="tel:${smeContact.cellularNumber}">${smeContact.cellularNumber}</a></label>
      </div>
      <div class="row">
        <label>Contact Email: <a href="mailto:${smeContact.contactEmail}">${smeContact.contactEmail}</a></label>
      </div>
      <div class="row">
        <label>URL web: <a href="${smeContact.url}">${smeContact.url}</a></label>
      </div>
    </fieldset>
    <g:if test="${contact.state.equals(Contact.INACTIVE)}">
      <g:form name="acceptContact" controller="employee" action="acceptContact.iphone">
        <g:hiddenField name="smeOrig" value="${sme.cif}"/>
        <g:hiddenField name="employee" value="${employee.nif}"/>
        <g:hiddenField name="smeDest" value="${smeContact.cif}"/>
        <a class="whiteButton" type="submit" href="#">Accept contact</a>
      </g:form>
      <g:form name="rejectContact" controller="employee" action="rejectContact.iphone">
        <g:hiddenField name="smeOrig" value="${sme.cif}"/>
        <g:hiddenField name="employee" value="${employee.nif}"/>
        <g:hiddenField name="smeDest" value="${smeContact.cif}"/>
        <a class="redButton" type="submit" href="#">Reject contact</a>
      </g:form>
    </g:if>
  </div>
</g:each>

<ul id="feed" title="Notifications">
  <g:each in="${sme.returnFeed().notifications.sort()}" var="notification">
    <li>[<g:formatDate format="dd/MMM-HH:mm" date="${notification.dateCreated}"/>] ${notification.text}</li>
  </g:each>
</ul>

<ul id="noticeboard" title="Noticeboard">
  <g:each in="${sme.returnNoticeBoard().notices.sort()}" var="notice">
    <li>[<g:formatDate format="dd/MMM-HH:mm" date="${notice.dateCreated}"/>] ${notice.text}</li>
  </g:each>
</ul>

<g:form name="sendnotice" title="Send notice" class="panel" controller="employee" action="addNotice.iphone" target="_self">
  <fieldset>
    <div class="row">
      <label>Title</label>
      <input type="text" name="title"/>
    </div>
    <div class="row">
      <label>Text</label>
      <input type="text" name="text"/>
    </div>
    <g:hiddenField name="senderUser" value="${sme.primaryUser}"/>
    <g:hiddenField name="senderCompany" value="${sme.cif}"/>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Send notice</a>
</g:form>

<g:form name="requestcontact" title="Contacts" class="panel" controller="employee" action="search.iphone" target="_self">
  <h2>Find SME to request contact</h2>
  <fieldset>
    <div class="row">
      <label>Keyword</label>
      <input type="text" name="query"/>
    </div>
    <g:hiddenField name="user" value="${employee.nif}"/>
    <g:hiddenField name="filter.town" value="${sme.town}"/>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Find SMEs</a>
</g:form>

<g:form name="search" title="Search" class="panel" controller="employee" action="search.iphone" target="_self">
  <fieldset>
    <div class="row">
      <label>Keyword</label>
      <input type="text" name="query"/>
    </div>
    <g:hiddenField name="user" value="${employee.nif}"/>
    <g:hiddenField name="filter.town" value="${sme.town}"/>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Find SMEs</a>
</g:form>

<g:form name="profilesme" title="Profile" class="panel" controller="employee" action="changeSmeProfile.iphone" target="_self">
  <h2>Change the ${sme.name} profile</h2>
  <fieldset>
    <div class="row">
      <label>Name</label><input type="text" name="smeBasicProfile.name" value="${sme.name}"/>
    </div>
    <div class="row">
      <label>Address</label><input type="text" name="smeBasicProfile.address" value="${sme.address}"/>
    </div>
    <div class="row">
      <label>Town</label><input type="text" name="smeBasicProfile.town" value="${sme.town}"/>
    </div>
    <div class="row">
      <label>Sector</label><input type="text" name="smeBasicProfile.sector" value="${sme.sector}"/>
    </div>
    <div class="row">
      <label>Country</label><input type="text" name="smeBasicProfile.country" value="${sme.country}"/>
    </div>
    <div class="row">
      <label>URL</label><input type="text" name="smeBasicProfile.url" value="${sme.url}"/>
    </div>
    <div class="row">
      <label>Phone</label><input type="text" name="smeBasicProfile.telephoneNumber" value="${sme.telephoneNumber}"/>
    </div>
    <div class="row">
      <label>Email</label><input type="text" name="smeBasicProfile.contactEmail" value="${sme.contactEmail}"/>
    </div>
    <div class="row">
      <label>Tags</label><input type="text" name="smeAdvancedProfile.metaTags" value="${sme.returnProfileData().metaTags}"/>
    </div>
    <g:hiddenField name="smeBasicProfile.cif" value="${sme.cif}"/>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Change profile</a>
</g:form>

<g:form name="profilevisibility" title="Visibility" class="panel" controller="employee" action="changeSmeProfile.iphone" target="_self">
  <h2>Change visibility for ${sme.name}</h2>
  <fieldset>
    <div class="row">
      <label>Contact List</label>
      <div type="checkbox" class="toggle" onclick="" toggled="true">
        <span class="thumb"></span>
        <span class="toggleOn">ON</span>
        <span class="toggleOff">OFF</span>
      </div>
    </div>
    <div class="row">
      <label>Feed</label>
      <div class="toggle" onclick="" toggled="true">
        <span class="thumb"></span>
        <span class="toggleOn">ON</span>
        <span class="toggleOff">OFF</span>
      </div>
    </div>
    <div class="row">
      <label>Noticeboard</label>
      <div class="toggle" onclick="" toggled="true">
        <span class="thumb"></span>
        <span class="toggleOn">ON</span>
        <span class="toggleOff">OFF</span>
      </div>
    </div>
    <g:hiddenField name="smeBasicProfile.cif" value="${sme.cif}"/>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
    <g:hiddenField id="smeCP.contactList" name="smeContentProfile.contactList" value=""/>
    <g:hiddenField id="smeCP.feed" name="smeContentProfile.feed" value=""/>
    <g:hiddenField id="smeCP.noticeBoard" name="smeContentProfile.noticeBoard" value=""/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Change visibility</a>
</g:form>

<g:form name="profileemployee" title="Employee" class="panel" controller="employee" action="changeUserProfile.iphone" target="_self">
  <h2>Change profile for ${employee.name}</h2>
  <fieldset>
    <div class="row">
      <label>Name</label><input type="text" name="userProfile.name" value="${employee.name}"/>
    </div>
    <div class="row">
      <label>Surname</label><input type="text" name="userProfile.surname" value="${employee.surname}"/>
    </div>
    <div class="row">
      <label>Email</label><input type="text" name="userProfile.contactData.email" value="${employee.contactData.email?:''}"/>
    </div>
    <div class="row">
      <label>Telephone</label><input type="text" name="userProfile.contactData.telephoneNumber" value="${employee.contactData.telephoneNumber?:''}"/>
    </div>
    <div class="row">
      <label>SMS Advice</label><input type="text" name="userProfile.smsAdvice" value="${employee.smsAdvice}"/>
    </div>
    <div class="row">
      <label>Mail Advice</label><input type="text" name="userProfile.mailAdvice" value="${employee.mailAdvice}"/>
    </div>
    <g:hiddenField name="sme" value="${sme.cif}"/>
    <g:hiddenField name="employee" value="${employee.nif}"/>
  </fieldset>
  <a class="whiteButton" type="submit" href="#">Change profile</a>
</g:form>

