<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
  <meta name="layout" content="main"/>
  <title>SME options</title>
</head>
<body>
  <div class="body">
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>

    <h1>Obtener la lista de contactos de una SME</h1>
    <g:form name="formulario" action="getcontactlist.json">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="name">Nombre de la SME *</label>
              </td>
              <td valign="top">
                <input type="text" id="smeName" name="smeName"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="getContactList"/></span>
      </div>
    </g:form>

    <h1>Obtener la lista de feeds de una SME</h1>
    <g:form name="formulario" action="getfeed.json">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="name">Nombre de la SME *</label>
              </td>
              <td valign="top">
                <input type="text" id="smeName" name="smeName"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="getFeed"/></span>
      </div>
    </g:form>

    <h1>Create SME and PU</h1>
    <g:form name="formulario" action="create.json">
      <!-- smeBasicProfile -->
      <h3>Datos de SME</h3>
      Name (obligatorio) <g:textField name="smeBasicProfile.name"/><br/>
      Address (obligatorio) <g:textField name="smeBasicProfile.address"/><br/>
      Town (obligatorio) <g:textField name="smeBasicProfile.town"/><br/>
      Sector (obligatorio) <g:textField name="smeBasicProfile.sector"/><br/>
      State (opcional) <g:textField name="smeBasicProfile.state"/><br/>
      Country (obligatorio) <g:textField name="smeBasicProfile.country"/><br/>
      Zip code (opcional) <g:textField name="smeBasicProfile.zipcode"/><br/>
      Url (opcional) <g:textField name="smeBasicProfile.url"/><br/>
      Telephone Number (opcional) <g:textField name="smeBasicProfile.telephoneNumber"/><br/>
      Cellular Number (opcional) <g:textField name="smeBasicProfile.cellularNumber"/><br/>
      Fax Number (opcional) <g:textField name="smeBasicProfile.faxNumber"/><br/>
      Contact email (opcional) <g:textField name="smeBasicProfile.contactEmail"/><br/>
      <!-- smeAdvancedProfile -->
      <h3>Otros datos de SME (opcionales)</h3>
      Vat number <g:textField name="smeAdvancedProfile.vatNumber"/><br/>
      Cif <g:textField name="smeAdvancedProfile.cif"/><br/>
      Corporate Name <g:textField name="smeAdvancedProfile.corporateName"/><br/>
      Meta tags <g:textField name="smeAdvancedProfile.metaTags"/><br/>

      <!-- smeContentProfile -->
      <h3>Datos del perfil de SME (obligatorios)</h3>
      ContactList <g:textField name="smeContentProfile.contactList"/><br/>
      Feeds <g:textField name="smeContentProfile.feed"/><br/>
      Advance Profile <g:textField name="smeContentProfile.smeAdvancedProfile"/><br/>

      <!-- userProfile -->
      <h3>Datos del PU</h3>
      Name (obligatorio)<g:textField name="userProfile.name"/><br/>
      Surname (obligatorio)<g:textField name="userProfile.surname"/><br/>
      Nif (opcional)<g:textField name="userProfile.nif"/><br/>
      Email (obligatorio)<g:textField name="userProfile.email"/><br/>
      Cellular Number (opcional)<g:textField name="userProfile.cellularNumber"/><br/>
      Telephone Number (opcional)<g:textField name="userProfile.telephoneNumber"/><br/>
      Sms Advice (opcional, true/false)<g:textField name="userProfile.smsAdvice"/><br/>
      Mail Advice (opcional, true/false)<g:textField name="userProfile.mailAdvice"/><br/>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="Crear SME y PU"/></span>
      </div>
    </g:form>

    <h1>Set Primary User to SME</h1>
    <g:form name="formulario" action="changePU.json">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label for="name">Id de Employee *</label>
              </td>
              <td valign="top">
                <input type="text" id="idEmployee" name="idEmployee"/>
              </td>
              <td valign="top" class="name">
                <label for="name">Nombre de la SME *</label>
              </td>
              <td valign="top">
                <input type="text" id="smeName" name="smeName"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="Set PU"/></span>
      </div>
    </g:form>

  </div>
</body>
</html>
