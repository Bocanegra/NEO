

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Employee</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Employee List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Employee</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Employee</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${employeeInstance}">
            <div class="errors">
                <g:renderErrors bean="${employeeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${employeeInstance?.id}" />
                <input type="hidden" name="version" value="${employeeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'name','errors')}">
                                    <input type="text" maxlength="30" id="name" name="name" value="${fieldValue(bean:employeeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="surname">Surname:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'surname','errors')}">
                                    <input type="text" maxlength="40" id="surname" name="surname" value="${fieldValue(bean:employeeInstance,field:'surname')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nif">Nif:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'nif','errors')}">
                                    <input type="text" maxlength="20" id="nif" name="nif" value="${fieldValue(bean:employeeInstance,field:'nif')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'email','errors')}">
                                    <input type="text" maxlength="40" id="email" name="email" value="${fieldValue(bean:employeeInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telephoneNumber">Telephone Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'telephoneNumber','errors')}">
                                    <input type="text" maxlength="11" id="telephoneNumber" name="telephoneNumber" value="${fieldValue(bean:employeeInstance,field:'telephoneNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cellularNumber">Cellular Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'cellularNumber','errors')}">
                                    <input type="text" maxlength="11" id="cellularNumber" name="cellularNumber" value="${fieldValue(bean:employeeInstance,field:'cellularNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password">Password:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'password','errors')}">
                                    <input type="text" id="password" name="password" value="${fieldValue(bean:employeeInstance,field:'password')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="clientO2">Client O2:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'clientO2','errors')}">
                                    <g:checkBox name="clientO2" value="${employeeInstance?.clientO2}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated">Date Created:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'dateCreated','errors')}">
                                    <g:datePicker name="dateCreated" value="${employeeInstance?.dateCreated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="departments">Departments:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'departments','errors')}">
                                    <g:select name="departments"
from="${Department.list()}"
size="5" multiple="yes" optionKey="id"
value="${employeeInstance?.departments}" />

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="happenings">Happenings:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'happenings','errors')}">
                                    <g:select name="happenings"
from="${Happening.list()}"
size="5" multiple="yes" optionKey="id"
value="${employeeInstance?.happenings}" />

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastLogin">Last Login:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'lastLogin','errors')}">
                                    <g:datePicker name="lastLogin" value="${employeeInstance?.lastLogin}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated">Last Updated:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'lastUpdated','errors')}">
                                    <g:datePicker name="lastUpdated" value="${employeeInstance?.lastUpdated}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="logged">Logged:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'logged','errors')}">
                                    <g:checkBox name="logged" value="${employeeInstance?.logged}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mailAdvice">Mail Advice:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'mailAdvice','errors')}">
                                    <g:checkBox name="mailAdvice" value="${employeeInstance?.mailAdvice}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="smes">Smes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'smes','errors')}">
                                    
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="smsAdvice">Sms Advice:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:employeeInstance,field:'smsAdvice','errors')}">
                                    <g:checkBox name="smsAdvice" value="${employeeInstance?.smsAdvice}" ></g:checkBox>
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
