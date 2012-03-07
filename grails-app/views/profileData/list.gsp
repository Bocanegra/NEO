

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProfileData List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProfileData</g:link></span>
        </div>
        <div class="body">
            <h1>ProfileData List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="visibility" title="Visibility" />
                        
                   	        <g:sortableColumn property="vatNumber" title="Vat Number" />
                        
                   	        <g:sortableColumn property="cif" title="Cif" />
                        
                   	        <g:sortableColumn property="corporateName" title="Corporate Name" />
                        
                   	        <g:sortableColumn property="metaTags" title="Meta Tags" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${profileDataInstanceList}" status="i" var="profileDataInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${profileDataInstance.id}">${fieldValue(bean:profileDataInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:profileDataInstance, field:'visibility')}</td>
                        
                            <td>${fieldValue(bean:profileDataInstance, field:'vatNumber')}</td>
                        
                            <td>${fieldValue(bean:profileDataInstance, field:'cif')}</td>
                        
                            <td>${fieldValue(bean:profileDataInstance, field:'corporateName')}</td>
                        
                            <td>${fieldValue(bean:profileDataInstance, field:'metaTags')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${profileDataInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
