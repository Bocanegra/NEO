<root>
  <destination mode="before" zone="waInfo"/>
  <data><![CDATA[

  <div class="iMenu" id="waInfo">
    <a href="#" rel="back" class="iButton iBClassic" id="waBackButton">Back</a>
    <h1>
      <img class="logo" src="${resource(dir:'', file:sme.urlLogo)}"/>
      <div class="name">${sme.name}</div>
    </h1>
    <br/>
    <ul class="iArrow">
      <li class="carac">CIF - ${sme.cif}</li>
      <li class="carac">${sme.numRecommendations} recommendations</li>
      <li class="carac">${sme.returnStringContacts()}</li>
      <li class="carac"><a href="http://maps.google.com/maps?q=${sme.address}, ${sme.town}">${sme.address}, ${sme.town} (${sme.country})</a></li>
      <g:if test="${sme.telephoneNumber}">
        <li class="carac"><a href="tel:${sme.telephoneNumber}">${sme.telephoneNumber}</a></li>
      </g:if>
      <g:if test="${sme.url}">
        <li class="carac"><a href="${sme.url}" target="_blank">${sme.url}</a></li>
      </g:if>
      <g:if test="${sme.contactEmail}">
        <li class="carac">Contact Email: <a href="mailto:${sme.contactEmail}">${sme.contactEmail}</a></li>
      </g:if>
      <li class="carac"><a href="#_Desc">Description</a></li>
      <li class="carac"><a href="#_Solu">Solutions</a></li>
      <li class="carac"><a href="#_Prod">Products</a></li>
    </ul>
    %{--<!-- Botón de solicitud de contacto -->--}%
    %{--<form id="sme${sme.id}" action="requestContact.iphone">--}%
      %{--<g:hiddenField name="smeOrig" value="${smeOrig}"/>--}%
      %{--<g:hiddenField name="employee" value="${employee}"/>--}%
      %{--<g:hiddenField name="smeDest" value="${sme.cif}"/>--}%
      %{--<div align="center">--}%
        %{--<a class="iPush iBAction iBClassic" type="submit" onclick="return WA.Submit('sme${sme.id}')">Request contact</a>--}%
      %{--</div>--}%
    %{--</form>--}%
  </div>

  <div class="iLayer" id="waDesc" title="Description">
    <a href="#_Info" rel="back" class="iButton iBClassic" id="waBackButton">Back</a>
    <div class="iBlock">
      <p>${sme.description}</p>
    </div>
  </div>

  <div class="iLayer" id="waSolu" title="Solutions">
    <a href="#_Info" rel="back" class="iButton iBClassic" id="waBackButton">Back</a>
    <div class="iBlock">
      <p>${sme.solutions}</p>
    </div>
  </div>

  <div class="iLayer" id="waProd" title="Products">
    <a href="#_Info" rel="back" class="iButton iBClassic" id="waBackButton">Back</a>
    <div class="iBlock">
      <p>${sme.products}</p>
    </div>
  </div>

  ]]></data>
</root>
