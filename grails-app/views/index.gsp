<html>
  <head>
    <meta name="layout" content="main"/>
  </head>
  <body>

    <p>Lista de controladores disponibles:</p>
    <ul>
      <g:each var="c" in="${grailsApplication.controllerClasses}">
        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
      </g:each>
    </ul>
  </body>
</html>