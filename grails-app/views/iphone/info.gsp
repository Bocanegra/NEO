<g:if test="${flash.message}">
  <div id="info" class="panel" title="Info">
    <h2>${flash.message}</h2>
    <g:link class="whiteButton" action="renderContent.iphone" params="[sme:sme, employee:employee, smeToRender:sme]">
      Back to ${Sme.getById(sme)?.name} page
    </g:link>
  </div>
</g:if>
