<html>
  <head>
    <meta name="layout" content="itheme"/>
  </head>

  <body>

  <div id="left-col">
    <div id="nav">
      <ul>
        <li class="page_item current_page_item"><a href="${createLink(controller:'employee')}" title="Employee">Employee</a></li>
        <li class="page_item"><a href="${createLink(controller:'administrator')}" title="Administrator">Administrator</a></li>
        <li class="page_item"><a href="${createLink(controller:'help')}" title="Help">Help</a></li>
      </ul>
    </div><!-- /nav -->

    <div id="content">
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <div class="post">
        <div class="date"><span>Apr</span> 12</div>
        <div class="title">
          <h2>Select one from the SMEs you belongs</h2>
          <div class="postdata">
            <table>
              <thead>
              <tr>
                <g:sortableColumn property="id" title="Id"/>
                <g:sortableColumn property="cif" title="CIF"/>
                <g:sortableColumn property="name" title="Name"/>
                <g:sortableColumn property="address" title="Address"/>
                <g:sortableColumn property="town" title="Town"/>
                <g:sortableColumn property="state" title="State"/>
                <g:sortableColumn property="country" title="Country"/>
                <g:sortableColumn property="zipCode" title="Zip Code"/>
                <g:sortableColumn property="url" title="URL"/>
                <g:sortableColumn property="sector" title="sector"/>
              </tr>
              </thead>
              <tbody>
               <g:each in="${data}" status="i" var="smeInstance">
                 <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="renderContent" params="[sme:smeInstance.cif, employee:employee.nif, smeToRender:smeInstance.cif]">${fieldValue(bean: smeInstance, field: 'id') }</g:link></td>
                    <td>${fieldValue(bean: smeInstance, field: 'cif')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'name')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'address')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'town')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'state')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'country')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'zipCode')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'url')}</td>
                    <td>${fieldValue(bean: smeInstance, field: 'sector')}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>
            <span class="category">Pruebas</span>
            <span class="comments">Comentarios</span>
          </div>
        </div>
        <div class="entry">
        </div><!--/entry -->
      </div><!--/post -->
    </div> <!--/content -->

    <!-- Se mete el pie -->
    <g:render template="/footer"/>

  </div><!-- /left-col -->

  </body>
</html>
