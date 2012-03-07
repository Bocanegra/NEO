

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProfileData</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProfileData List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProfileData</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProfileData</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Visibility:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'visibility')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Vat Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'vatNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Cif:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'cif')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Corporate Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'corporateName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Meta Tags:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'metaTags')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Created:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'dateCreated')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Updated:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:profileDataInstance, field:'lastUpdated')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${profileDataInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
