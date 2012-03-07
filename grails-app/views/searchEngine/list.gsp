

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>SearchEngine List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New SearchEngine</g:link></span>
        </div>
        <div class="body">
            <h1>SearchEngine List</h1>
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
                        
                   	        <th>Sme</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${searchEngineInstanceList}" status="i" var="searchEngineInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${searchEngineInstance.id}">${fieldValue(bean:searchEngineInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:searchEngineInstance, field:'visibility')}</td>
                        
                            <td>${fieldValue(bean:searchEngineInstance, field:'dateCreated')}</td>
                        
                            <td>${fieldValue(bean:searchEngineInstance, field:'lastUpdated')}</td>
                        
                            <td>${fieldValue(bean:searchEngineInstance, field:'sme')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${searchEngineInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
