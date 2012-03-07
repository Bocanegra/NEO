<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Employee page</title>
    <meta name="layout" content="itheme"/>
  </head>

  <body>

  <div id="left-col">
    <div id="nav">
      <ul>
        <li class="page_item current_page_item"><a href="${createLink(controller:'employee')}">Employee page</a></li>
      </ul>
    </div>

    <div id="content">
      <div class="post" id="login_form">
        <div class="title">
          <h2>Insert the email given in the registry as the Username, and the Password.</h2>
          <g:form name="formulario" action="validate.html">
            <div class="dialog">
              <g:if test="${flash.message}">
                <div class="postdata">${flash.message}</div>
              </g:if>
              <table>
                <tbody>
                  <tr class="prop">
                    <td valign="top" class="user">
                      <label>Username</label>
                    </td>
                    <td valign="top">
                      <input type="text" id="user" name="user" value="paul@travelzone.com"/>
                    </td>
                  </tr>
                  <tr class="prop">
                    <td valign="top" class="password">
                      <label>Password</label>
                    </td>
                    <td valign="top">
                      <input type="password" id="password" name="password" value="paul"/>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="buttons center">
              <span class="button"><input class="submit" type="submit" value="Login"/></span>
            </div>
          </g:form>
        </div>
      </div>

      <div class="post" id="sme_list"/>
    </div> <!-- content -->

  </div> <!-- left-content -->

  <div id="sidebar" class="dbx-group">
  </div>
  <hr class="hidden"/>
  
  </body>
</html>
