<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>SME register page</title>
    <meta name="layout" content="main"/>
  </head>

  <body>
  <h1 align="center">Register page for NEO</h1>
  <g:if test="${flash.message}">
    <div class="message"><h3>${flash.message}</h3></div>
  </g:if>

  <g:uploadForm name="registerForm" controller="employee" action="register.html">
    <!-- smeProfile -->
    <div id="left" style="float:left; width:450px">
      <h2>SME Data</h2>
      <table>
        <tr>
          <td>CIF *</td>
          <td><g:textField name="smeProfile.basicProfile.cif" value="A-33-XX"/></td>
        </tr>
        <tr>
          <td>Name *</td>
          <td><g:textField name="smeProfile.basicProfile.name" value="TID"/></td>
        </tr>
        <tr>
          <td>Address *</td>
          <td><g:textField name="smeProfile.basicProfile.address" value="Emilio Vargas"/></td>
        </tr>
        <tr>
          <td>Town *</td>
          <td><g:textField name="smeProfile.basicProfile.town" value="Madrid"/></td>
        </tr>
        <tr>
          <td>Sectors</td>
          <td><g:select name="smeProfile.basicProfile.sector" multiple="multiple" optionKey="name" optionValue="name" from="${Sector.list()}"/></td>
        </tr>
        <tr>
          <td>State</td>
          <td><g:textField name="smeProfile.basicProfile.state"/></td>
        </tr>
        <tr>
          <td>Country *</td>
          <td><g:countrySelect name="smeProfile.basicProfile.country" default="esp" value="esp"/></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><g:textField name="smeProfile.basicProfile.zipcode" value="28080"/></td>
        </tr>
        <tr>
          <td>URL Sme</td>
          <td><g:textField name="smeProfile.basicProfile.url" value="http://www.tid.es"/></td>
        </tr>
        <tr>
          <td>Telephone Number</td>
          <td><g:textField name="smeProfile.basicProfile.telephoneNumber"/></td>
        </tr>
        <tr>
          <td>Cellular Number</td>
          <td><g:textField name="smeProfile.basicProfile.cellularNumber"/></td>
        </tr>
        <tr>
          <td>Fax Number</td>
          <td><g:textField name="smeProfile.basicProfile.faxNumber"/></td>
        </tr>
        <tr>
          <td>Contact email</td>
          <td><g:textField name="smeProfile.basicProfile.contactEmail"/></td>
        </tr>
        <tr>
          <td>Tax code</td>
          <td><g:textField name="smeProfile.advancedProfile.taxCode"/></td>
        </tr>
        <tr>
          <td>Corporate Name</td>
          <td><g:textField name="smeProfile.advancedProfile.corporateName"/></td>
        </tr>
        <tr>
          <td>Metatags</td>
          <td><g:textField name="smeProfile.advancedProfile.metaTags"/></td>
        </tr>
        <tr>
          <td>Logo (32K)</td>
          <td><input type="file" name="logo_sme"/></td>
        </tr>
        <tr>
          <td>Description of SME</td>
          <td><g:textArea name="smeProfile.basicProfile.description"/></td>
        </tr>
        <tr>
          <td>Solutions</td>
          <td><g:textArea name="smeProfile.basicProfile.solutions"/></td>
        </tr>
        <tr>
          <td>Products</td>
          <td><g:textArea name="smeProfile.basicProfile.products"/></td>
        </tr>
      </table>
    </div>

    <!-- userProfile -->
    <div id="right" style="float:right; width:500px">
      <h2>Primary User Data</h2>
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
          <td>NIF *</td>
          <td><g:textField name="userProfile.nif" value="12345678P"/></td>
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
        <tr>
          <td>Sms Advice (true/false)</td>
          <td><g:textField name="userProfile.smsAdvice"/></td>
        </tr>
        <tr>
          <td>Mail Advice (true/false)</td>
          <td><g:textField name="userProfile.mailAdvice"/></td>
        </tr>
        <tr>
          <td>Avatar (32K)</td>
          <td><input type="file" name="avatar_emp"/></td>
        </tr>
      </table>
      <h3>SME Content Profile</h3>
      <table>
        <tr>
          <td>ContactList</td>
          <td><g:select name="smeProfile.contentProfile.contactList" from="['PUBLIC','CONTACTS','PRIVATE']"></g:select></td>
        </tr>
        <tr>
          <td>OperatorInfo</td>
          <td><g:select name="smeProfile.contentProfile.operatorInfo" from="['PUBLIC','CONTACTS','PRIVATE']"></g:select></td>
        </tr>
        <tr>
          <td>Feeds</td>
          <td><g:select name="smeProfile.contentProfile.feed" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Notice Board</td>
          <td><g:select name="smeProfile.contentProfile.noticeBoard" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Profile Data</td>
          <td><g:select name="smeProfile.contentProfile.profileData" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Search Engine</td>
          <td><g:select name="smeProfile.contentProfile.searchEngine" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Recomendation Box</td>
          <td><g:select name="smeProfile.contentProfile.recomendationBox" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Message Box</td>
          <td><g:select name="smeProfile.contentProfile.messageBox" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
        <tr>
          <td>Blog</td>
          <td><g:select name="smeProfile.contentProfile.blog" from="['PUBLIC','CONTACTS','PRIVATE','NONE']"></g:select></td>
        </tr>
      </table>
      </div>
      <div>
        <span><input type="hidden" name="invitation.code" value="${invitation.code}"/></span>
        <span><input type="hidden" name="invitation.addresseeMail" value="${invitation.addresseeMail}"/></span>
        <span><input type="hidden" name="invitation.addresseeSMEName" value="${invitation.addresseeSMEName}"/></span>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" align="middle" value="Create SME y PU"/></span>
      </div>
  </g:uploadForm>

  </body>
</html>