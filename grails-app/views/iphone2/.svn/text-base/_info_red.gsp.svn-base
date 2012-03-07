<div class="iLayer" id="list1">
  <div class="iBlock">
    <p><img src="${resource(dir:'images', file:'Translation_peq.png')}"/> <span class="pestanas">Connections</span></p>
  </div>
  <div class="iMenu" id="MenuSme">
    <ul class="iArrow iShop">
      <g:each in="${(sme.returnContactList().getStrongContacts()+sme.returnContactList().getWeakContacts())}" var="contact">
        <% Sme smeContact=Sme.getById(contact.sme) %>
        <li>
          <a href="showSmeInfo.iphone?id=${smeContact.cif}#_Info" rev="async">
            <img src="${resource(dir:'', file:smeContact.urlLogo)}" class="iFull"/>
            <em><g:if test="${contact.state==Contact.WEAK}">Normal contact</g:if><g:if test="${contact.state==Contact.STRONG}">Strong contact</g:if></em>
            <big>${smeContact.name}
              <small>${smeContact.returnStringContacts()}</small>
            </big>
          </a>
        </li>
      </g:each>
    </ul>
  </div>
</div>
