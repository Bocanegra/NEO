

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ContactList</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ContactList List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ContactList</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ContactList</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactListInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactListInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${contactListInstance?.id}" />
                <input type="hidden" name="version" value="${contactListInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="visibility">Visibility:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactListInstance,field:'visibility','errors')}">
                                    <g:select id="visibility" name="visibility" from="${contactListInstance.constraints.visibility.inList}" value="${contactListInstance.visibility}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contacts">Contacts:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactListInstance,field:'contacts','errors')}">
                                    
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated">Date Created:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactListInstance,field:'dateCreated','errors')}">
                                    <g:datePicker name="dateCreated" value="${contactListInstance?.dateCreated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated">Last Updated:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactListInstance,field:'lastUpdated','errors')}">
                                    <g:datePicker name="lastUpdated" value="${contactListInstance?.lastUpdated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="requests">Requests:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactListInstance,field:'requests','errors')}">
                                    <g:select name="requests"
from="${Request.list()}"
size="5" multiple="yes" optionKey="id"
value="${contactListInstance?.requests}" />

                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
