<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Secondary User register</title>
    <meta name="layout" content="main"/>
  </head>

  <body>
  <h1 align="center">Register page for Secondary User</h1>
  <g:if test="${flash.message}">
    <div class="message"><h3>${flash.message}</h3></div>
  </g:if>

  <g:form name="registerSuForm" controller="employee" action="create.html">
    <!-- smeProfile -->
    <div id="left" style="float:left; width:500px">
      <h2>SME Data</h2>
      <div class="list">
        <table border="3">
          <g:each in="${sme}" var="data">
            <tr>
              <td>${data}</td>
            </tr>
          </g:each>
        </table>
      </div>
    </div>

    <!-- userProfile -->
    <div id="right" style="float:right; width:400px">
      <h2>User Profile</h2>
      <table>
        <tr>
          <td>Name *</td>
          <td><g:textField name="userProfile.name" value="Luis Angel"/></td>
        </tr>
        <tr>
          <td>Surname *</td>
          <td><g:textField name="userProfile.surname" value="García"/></td>
        </tr>
        <tr>
          <td>NIF</td>
          <td><g:textField name="userProfile.nif" value="222222225U"/></td>
        </tr>
        <tr>
          <td>Email *</td>
          <td><g:textField name="userProfile.contactData.email" value="lagm@tid.es"/></td>
        </tr>
        <tr>
          <td>Password *</td>
          <td><g:passwordField name="userProfile.password"/></td>
        </tr>
        <tr>
          <td>Confirm Password *</td>
          <td><g:passwordField name="userProfile.confirmPassword"/></td>
        </tr>
        <tr>
          <td>Cellular Number</td>
          <td><g:textField name="userProfile.contactData.cellularNumber"/></td>
        </tr>
        <tr>
          <td>Telephone Number</td>
          <td><g:textField name="userProfile.contactData.telephoneNumber"/></td>
        </tr>
      </table>
      <div class="buttons">
        <span><input type="hidden" name="invitation.code" value="${invitation.code}"/></span>
        <span><input type="hidden" name="invitation.addresseeSMEName" value="${invitation.addresseeSMEName}"/></span>
        %{--<span><input type="hidden" name="sme" value="${sme.cif}"/></span>--}%
        <span class="button"><input class="save" type="submit" value="Create Secondary User"/></span>
      </div>
    </div>
  </g:form>

  </body>
</html>