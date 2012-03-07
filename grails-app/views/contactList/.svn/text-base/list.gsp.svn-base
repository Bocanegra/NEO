

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ContactList List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ContactList</g:link></span>
        </div>
        <div class="body">
            <h1>ContactList List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="visibility" title="Visibility" />
                        
                   	        <g:sortableColumn property="dateCreated" title="Date Created" />
                        
                   	        <g:sortableColumn property="lastUpdated" title="Last Updated" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contactListInstanceList}" status="i" var="contactListInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${contactListInstance.id}">${fieldValue(bean:contactListInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:contactListInstance, field:'visibility')}</td>
                        
                            <td>${fieldValue(bean:contactListInstance, field:'dateCreated')}</td>
                        
                            <td>${fieldValue(bean:contactListInstance, field:'lastUpdated')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactListInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
