<ul id="smes" title="SME list">
  <li class="group">SMEs find: ${data.size()}</li>
  <g:each in="${data}" status="i" var="smeInstance">
    <li>
      <a href="#sme${smeInstance.id}">${smeInstance.name}
      <% smeInstance.numRecommendations.times {
        %><img src="${createLinkTo(dir: 'images', file: 'starrated_12x12.gif')}"/><%
      } %>
      </a>
    </li>
  </g:each>
</ul>

<g:each in="${data}" status="i" var="sme">
  <g:form name="sme${sme.id}" title="SME info" class="panel" controller="employee"
          action="requestContact.iphone" target="_self">
    <h2>SME name: ${sme.name}</h2>
    <fieldset>
      <div class="row">
        <label>Location: <a href="http://maps.google.com/maps?q=${sme.address}, ${sme.town}">${sme.address}, ${sme.town}</a></label>
      </div>
      <div class="row">
        <label>Telephone number: <a href="tel:${sme.telephoneNumber}">${sme.telephoneNumber}</a></label>
      </div>
      <div class="row">
        <label>Cellular number: <a href="tel:${sme.cellularNumber}">${sme.cellularNumber}</a></label>
      </div>
      <div class="row">
        <label>Contact Email: <a href="mailto:${sme.contactEmail}">${sme.contactEmail}</a></label>
      </div>
      <div class="row">
        <label>URL web: <a href="${sme.url}">${sme.url}</a></label>
      </div>
      <g:hiddenField name="smeOrig" value="${smeOrig}"/>
      <g:hiddenField name="employee" value="${employee}"/>
      <g:hiddenField name="smeDest" value="${sme.cif}"/>
    </fieldset>
    <g:if test="smeOrig">
      <a class="whiteButton" type="submit" href="#">Request contact</a>
    </g:if>
  </g:form>
</g:each>