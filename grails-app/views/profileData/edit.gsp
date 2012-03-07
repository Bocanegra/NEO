

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ProfileData</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProfileData List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProfileData</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ProfileData</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${profileDataInstance}">
            <div class="errors">
                <g:renderErrors bean="${profileDataInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${profileDataInstance?.id}" />
                <input type="hidden" name="version" value="${profileDataInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="visibility">Visibility:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'visibility','errors')}">
                                    <g:select id="visibility" name="visibility" from="${profileDataInstance.constraints.visibility.inList}" value="${profileDataInstance.visibility}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vatNumber">Vat Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'vatNumber','errors')}">
                                    <input type="text" id="vatNumber" name="vatNumber" value="${fieldValue(bean:profileDataInstance,field:'vatNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cif">Cif:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'cif','errors')}">
                                    <input type="text" id="cif" name="cif" value="${fieldValue(bean:profileDataInstance,field:'cif')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="corporateName">Corporate Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'corporateName','errors')}">
                                    <input type="text" id="corporateName" name="corporateName" value="${fieldValue(bean:profileDataInstance,field:'corporateName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="metaTags">Meta Tags:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'metaTags','errors')}">
                                    <input type="text" id="metaTags" name="metaTags" value="${fieldValue(bean:profileDataInstance,field:'metaTags')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated">Date Created:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'dateCreated','errors')}">
                                    <g:datePicker name="dateCreated" value="${profileDataInstance?.dateCreated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated">Last Updated:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileDataInstance,field:'lastUpdated','errors')}">
                                    <g:datePicker name="lastUpdated" value="${profileDataInstance?.lastUpdated}" ></g:datePicker>
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
