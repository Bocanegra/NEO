<div class="title>">
  <h2>Select one from the SMEs you belongs</h2>
  <div class="entry">
    <table>
      <tr align="center">
        <td>Name</td>
        <td>Town</td>
        <td>Sector</td>
        <td>Recommentations</td>
        <td/>
      </tr>
      <g:each in="${data}" status="i" var="smeInstance">
        <tr>
          <td>${fieldValue(bean: smeInstance, field: 'name')}</td>
          <td>${fieldValue(bean: smeInstance, field: 'town')}</td>
          <td>${fieldValue(bean: smeInstance, field: 'sector')}</td>
          <td align="center">${fieldValue(bean: smeInstance, field: 'numRecommendations')}</td>
          <td class="next"><g:link action="renderContent.html" params="[sme:smeInstance.cif, employee:employee.nif, smeToRender:smeInstance.cif]">Show</g:link></td>
        </tr>
      </g:each>
    </table>
  </div>
</div>