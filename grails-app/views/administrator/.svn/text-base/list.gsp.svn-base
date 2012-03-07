<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Admin page</title>
    <meta name="layout" content="main"/>
  </head>
  <body>
    <g:if test="${flash.message}">
      <div class="message"><h3>${flash.message}</h3></div>
    </g:if>
    <%if (admin){ %>
    <g:link controller="administrator" action="logout" params="[user:admin.user]">Log Out</g:link>
    <%}%>

    <div class="body">
      <div class="list">
        <table>
          <thead>
          <tr>
            <g:sortableColumn property="id" title="Id"/>
            <g:sortableColumn property="cif" title="CIF"/>
            <g:sortableColumn property="name" title="Name"/>
            <g:sortableColumn property="address" title="Address"/>
            <g:sortableColumn property="town" title="Town"/>
            <g:sortableColumn property="state" title="State"/>
            <g:sortableColumn property="country" title="Country"/>
            <g:sortableColumn property="zipCode" title="Zip Code"/>
            <g:sortableColumn property="url" title="URL"/>
            <g:sortableColumn property="sector" title="sector"/>
          </tr>
          </thead>
          <tbody>
          <g:each in="${Sme.findAll()}" status="i" var="smeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${fieldValue(bean: smeInstance, field: 'id')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'cif')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'name')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'address')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'town')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'state')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'country')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'zipCode')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'url')}</td>
              <td>${fieldValue(bean: smeInstance, field: 'sector')}</td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>

     <div id="envio_invitaciones">
        <h2>Envío de invitaciones</h2>
        <g:form action="inviteSME.html" method="post">
          <div class="dialog">
            <table>
              <tbody>
              <tr class="prop">
                <td valign="top" class="name">Email *</td>
                <td valign="top" class="value">
                  <input type="text" size="50" maxlength="200" name="email"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">Nombre de la SME *</td>
                <td valign="top" class="value">
                  <input type="text" size="50" maxlength="200" name="smeName"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">Persona de contacto</td>
                <td valign="top" class="value">
                  <input type="text" size="50" maxlength="200" name="userName"/>
                </td>
              </tr>
               <tr class="prop">
                <td valign="top" class="name">Código Promocional</td>
                <td valign="top" class="value">
                  <input type="text" size="50" maxlength="200" name="sender"/>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span>Campos con * son obligatorios </span>
            <span class="button"><input class="save" type="submit" value="Enviar invitación"/></span>
          </div>
        </g:form>
      </div>

      <h1><u>Primary User</u></h1>
      <div id="set_primary_user">
        <h3>Set Primary User</h3>
        <g:form action="setPrimaryUser.html" method="post">
          <div class="dialog">
            <table>
              <tbody>
              <tr class="prop">
                <td valign="top" class="name">Cif de la SME a la que se asignar&aacute; el PU</td>
                <td valign="top" class="value">
                  <input type="text" name="sme" value="A-33-XX"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name" >Nif del usuario a promocionar</td>
                <td valign="top" class="value">
                  <input type="text" name="user" value="12345678P"/>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Set PU to SME"/></span>
          </div>
        </g:form>
      </div>

      <h1><u>Delete User</u></h1>
      <div id="delete_user">
        <g:form action="delete.html" method="post">
          <div class="dialog">
            <table>
              <tbody>
              <tr class="prop">
                <td valign="top" class="name">Empresa de la que se eliminará el usuario</td>
                <td valign="top" class="value">
                  <input type="text" name="company" value="A-33-XX"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name" >Nif del usuario a borrar</td>
                <td valign="top" class="value">
                  <input type="text" name="user" value="12345678P"/>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Delete User from SME"/></span>
          </div>
        </g:form>

      </div>
      <h1><u>Publicize Operator Information</u></h1>
      <div id="publicize_operator_info">
        <g:form action="publicizeOperatorInfo.html" method="post">
          <div class="dialog">
            <table>
              <tbody>
              <tr class="prop">
                <td valign="top" class="name">Title of the notice to publicize</td>
                <td valign="top" class="value">
                  <input type="text" name="title" size="60"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name" >Information</td>
                <td valign="top" class="value">
                  <input type="text" name="text" size="60"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">Addressees (cif1,cif2,etc. or empty field to all SMEs) </td>
                <td valign="top" class="value">
                  <input type="text" name="companies" size="60"/>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <g:hiddenField name="user" value="admin"/>           
            <span class="button"><input class="save" type="submit" value="Publicize Operator Information"/></span>
          </div>
        </g:form>
      </div>


    </div>
  </body>
</html>