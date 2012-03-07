<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Admin page</title>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${createLink(url:'/')}">Home</a></span>
      <span class="menuButton"><g:link class="list" action="list">Admin List</g:link></span>
      <span class="menuButton"><g:link class="create" action="create">New Admin</g:link></span>
    </div>
    <div class="body">
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:form name="formulario" action="validate.html">
        <div class="dialog">
          <table>
            <tbody>
              <tr class="prop">
                <td valign="top" class="user">
                  <label>Username</label>
                </td>
                <td valign="top">
                  <input type="text" id="user" name="user"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="password">
                  <label>Password</label>
                </td>
                <td valign="top">
                  <input type="password" id="password" name="password"/>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span class="button"><input class="save" type="submit" value="Login"/></span>
        </div>
      </g:form>
    </div>
  </body>
</html>