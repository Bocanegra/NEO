

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit SearchEngine</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SearchEngine List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SearchEngine</g:link></span>
        </div>
        <div class="body">
            <h1>Edit SearchEngine</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${searchEngineInstance}">
            <div class="errors">
                <g:renderErrors bean="${searchEngineInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${searchEngineInstance?.id}" />
                <input type="hidden" name="version" value="${searchEngineInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="visibility">Visibility:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:searchEngineInstance,field:'visibility','errors')}">
                                    <g:select id="visibility" name="visibility" from="${searchEngineInstance.constraints.visibility.inList}" value="${searchEngineInstance.visibility}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated">Date Created:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:searchEngineInstance,field:'dateCreated','errors')}">
                                    <g:datePicker name="dateCreated" value="${searchEngineInstance?.dateCreated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated">Last Updated:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:searchEngineInstance,field:'lastUpdated','errors')}">
                                    <g:datePicker name="lastUpdated" value="${searchEngineInstance?.lastUpdated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sme">Sme:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:searchEngineInstance,field:'sme','errors')}">
                                    <g:select optionKey="id" from="${Sme.list()}" name="sme.id" value="${searchEngineInstance?.sme?.id}" ></g:select>
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
