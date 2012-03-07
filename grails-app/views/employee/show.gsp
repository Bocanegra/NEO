<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>SME home page</title>
  </head>

  <body>
    <h1 align="center">SME home page</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>

    <% def primaryUser=false %>
    <% if (employee){ %>
        <g:link controller="employee" action="logout">Log Out</g:link>


      <div class="busqueda">
        <h1><u>Searching SMEs</u></h1>
        <g:form name="searchableForm" controller="employee" action="search.html" method="get">
        <g:textField name="query" size="30"/> <input type="submit" value="Search SMEs" />
          %{--<span><input type="hidden" name="filter.town" value="${sme.town}"/></span>--}%
          <span><input type="hidden" name="user" value="${employee.nif}"/></span>
        </g:form>

        <% if (data) {%>
        RESULTADOS DE LA BÚSQUEDA: <br/>
          <g:each var="result" in="${data}" status="index">
              <div class="result">
                <g:link controller="employee" action="renderContent" params="[smeToRender:result.cif]">${result.name}</g:link>
                <g:set var="desc" value="${result.toString()}" />
                <g:if test="${desc.size() > 120}"><g:set var="desc" value="${desc[0..120] + '...'}" /></g:if>
                <div class="desc">Recomendaciones : ${result.numRecommendations}<br/>Sector : ${result.sector}<br/>Ciudad : ${result.town}</div>
                <g:link controller="employee" action="requestContact" params="[smeDest:result.cif]">Solicitar Contacto</g:link><br/>
               </div><br/>
          </g:each>
        <% } else {%>
        NO HAY RESULTADOS <br/>
        <% }%>

       </div>

       <div class="busquedaavanzada">
        <h1><u>Advanced Searching SMEs</u></h1>
          <g:form name="searchableForm" controller="employee" action="advancedsearch" method="get">
          Sector:</br>
          <g:select size="3" name="query" multiple="multiple" optionKey="name" optionValue="name" from="${Sector.list()}"></g:select> </br> </br>
          Nombre: <g:textField name="filter.name" size="30"/> </br> </br>
          Provincia:
          <select name="filter.town">
             <option value=" ">Todas</option>
             <option value="Coruña">A Coruña</option>
             <option value="Asturias">Asturias</option>
             <option value="Barcelona">Barcelona</option>
             <option value="Bilbao">Bilbao</option>
             <option value="Madrid">Madrid</option>
             <option value="Málaga">Málaga</option>
             <option value="Sevilla">Sevilla</option>
             <option value="Valencia">Valencia</option>
             <option value="Valladolid">Valladolid</option>
             <option value="Zaragoza">Zaragoza</option>
          </select> </br> </br>
         
           <input type="submit" value="Advanced Search SMEs" />
           <span><input type="hidden" name="user" value="${employee.nif}"/></span>
        </g:form>

         <% if (advanceddata) {%>
         RESULTADOS DE LA BÚSQUEDA: <br/>
          <g:each var="result" in="${advanceddata}" status="index">
              <div class="advancedresult">
                <g:link controller="employee" action="renderContent" params="[smeToRender:result.cif]">${result.name}</g:link>
                <g:set var="desc" value="${result.toString()}" />
                <g:if test="${desc.size() > 120}"><g:set var="desc" value="${desc[0..120] + '...'}" /></g:if>
                <div class="desc">Recomendaciones : ${result.numRecommendations}<br/>Sector : ${result.sector}<br/>Ciudad : ${result.town}</div>
                <g:link controller="employee" action="requestContact" params="[smeDest:result.cif]">Solicitar Contacto</g:link><br/>
               </div></br>
          </g:each>
         <% } else {%>
        NO HAY RESULTADOS <br/>
        <% }%>
      </div>


      <% if(sme){%>

        <h1>User ${employee}</h1>
        <div>
        <h1><u>Change User Profile</u></h1>
        <g:form name="userProfileForm" action="changeUserProfile.html">
          <div class="user_profile">
            <table>
              <g:if test="${employee.avatar}">
                <tr>
                  <td>Avatar </td>
                  <td><img alt="avatar" src="${createLink(controller:'employee', action:'avatar_image', id:employee.nif)}"/></td>
                </tr>
              </g:if>
              <tr>
                <td>Name </td>
                <td><g:textField name="userProfile.name" value="${employee.name}"/></td>
              </tr>
              <tr>
                <td>Surname </td>
                <td><g:textField name="userProfile.surname" value="${employee.surname}"/></td>
              </tr>
              <tr>
                <td>NIF</td>
                <td><g:textField name="userProfile.nif" value="${employee.nif}"/></td>
              </tr>
              <tr>
                <td>Email </td>
                <td><g:textField name="userProfile.contactData.email" value="${employee.contactData.email}"/></td>
              </tr>
              <tr>
                <td>Cellular Number</td>
                <td><g:textField name="userProfile.contactData.cellularNumber" value="${employee.contactData.cellularNumber}"/></td>
              </tr>
              <tr>
                <td>Telephone Number</td>
                <td><g:textField name="userProfile.contactData.telephoneNumber" value="${employee.contactData.telephoneNumber}"/></td>
              </tr>
              <%if((sme) && ((employee.nif).equals(sme.primaryUser))){
                primaryUser=true %>
                <span><input type="hidden" name="PU" value="true"/></span>
                <tr>
                  <td>Sms Advice (true/false)</td>
                  <td><g:textField name="userProfile.smsAdvice" value="${employee.smsAdvice}"/></td>
                </tr>
                <tr>
                  <td>Mail Advice (true/false)</td>
                  <td><g:textField name="userProfile.mailAdvice" value="${employee.mailAdvice}"/></td>
                </tr>
              <%}%>
            </table>
          </div>
          <div class="buttons">
            %{--<span><input type="hidden" name="employee" value="${employee.nif}"/></span>--}%
            %{--<span><input type="hidden" name="sme" value="${sme.cif}"/></span>--}%
            <span class="button"><input class="save" type="submit" value="Change User Profile"/></span>
          </div>
        </g:form>
      </div>

      <h1><u>Messages</u></h1>
      <div id="send_message">
        <h3>Enviar mensaje a empleados de las smes</h3>
        <g:form name="formulario" action="sendMessage.html">
          <div class="dialog">
            <table>
              <tbody>
                <tr class="prop">
                  <td valign="top" class="name">
                    <label>NIF de usuarios destino</label>
                  </td>
                  <td valign="top">
                    %{--<g:select size="3" name="addressees" multiple="multiple" optionKey="nif" optionValue="name" from="${Employee.list()}"></g:select>--}%
                    <input type="text" name="addressees" value="A58818501:12345678P,12345685P">
                  </td>
                </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <label>Título</label>
                </td>
                <td valign="top">
                  <input type="text" name="title" value="Mensajito de prueba"/>
                </td>
              </tr>
              <tr class="prop">
                <td valign="top" class="name">
                  <label>Texto</label>
                </td>
                <td valign="top">
                  <input type="text" name="text" size="65"/>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
            <span><input type="hidden" name="senderSme" value="${sme.cif}"/></span>
            <span class="button"><input class="save" type="submit" value="Send Message"/></span>
          </div>
        </g:form>
      </div>

      <div id="answer_message">
        <h3>Respuesta a mensaje</h3>
        <g:form name="formulario" action="answerMessage.html">
          <div class="dialog">
            <table>
              <tbody>
                <tr class="prop">
                  <td valign="top" class="name">
                    <label>Identificador de mensaje origen:</label>
                  </td>
                  <td valign="top">
                    <input type="text" name="idMsg"/>
                  </td>
                </tr>
                <tr>
                  <td valign="top" class="name">
                    <label>Subject:</label>
                  </td>
                  <td valign="top">
                    <input type="text" name="title"/>
                  </td>
                </tr>
                <tr class="prop">
                  <td valign="top" class="name">
                    <label>Mensaje de respuesta:</label>
                  </td>
                  <td valign="top">
                    <input type="text" name="text" size="65"/>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
            <span><input type="hidden" name="senderSme" value="${sme.cif}"/></span>
            <span class="button"><input class="save" type="submit" value="Answer Message"/></span>
          </div>
        </g:form>
      </div>

      <div id="delete_message">
        <h3>Borrado de mensaje</h3>
        <g:form name="formulario" action="deleteMessage.html">
          <div class="dialog">
            <table>
              <tbody>
                <tr class="prop">
                  <td valign="top" class="name">
                    <label>Identificador de mensaje a borrar:</label>
                  </td>
                  <td valign="top">
                    <input type="text" name="idMsg"/>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="buttons">
            <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
            <span><input type="hidden" name="senderSme" value="${sme.cif}"/></span>
            <span class="button"><input class="save" type="submit" value="Delete Message"/></span>
          </div>
        </g:form>
      </div>

  <div class="search_message">
        <h3>Buscar un mensaje</h3>
          <g:form name="searchableForm" controller="employee" action="searchMessage.html" >
          <span><input type="hidden" name="user" value="${employee.nif}"/></span>
          Search: <g:textField name="query" size="30"/> 
          by:
          <select name="searchby" size="1">
                  <option value="subject">Subject</option>
                  <option value="text">Text</option>
                  <option value="sendersme">Sender Sme</option>
                  <option value="senderuser">Sender User</option>

          </select>
          <input type="submit" value="Search Message" />
        </g:form>

        <% if (messagedata) {%>
        RESULTADOS DE LA BÚSQUEDA: <br/>
          <g:each var="result" in="${messagedata}" status="index">
              <div class="result">
                ${result}<br/>
               </div><br/>
          </g:each>
        <% } else {%>
        NO HAY RESULTADOS <br/>
        <% }%>
      </div>

      <%}%>


      <%}%>
      <% if (sme) {%>
      <div>
        <h1>Company ${sme}</h1>
        <h3>Primary User of SME: ${sme.primaryUser}</h3>
      </div>
      <div class="logo">
        <g:if test="${sme.logo}">
          Logo: <img alt="(logo)" src="${createLink(controller:'employee', action:'logo_image', id:sme.cif)}"/>
        </g:if>
      </div>
      <div class="list">
        <table border="3">
          <g:each in="${sme.contents}" var="content">
            <tr>
              <td>${content}</td>
              <g:if test="${content.getClass().toString()=='class RecommendationBox'}">
                <g:each in="${content.recommendations}" var="recommendation" >
                  <td> ${recommendation}</td>
                </g:each>
              </g:if>
              <g:else>
                <g:if test="${content.getClass().toString()=='class MessageBox'}">
                  <g:each in="${content.messages}" var="message" >
                    <td> ${message}</td>
                    <td> ${message.id}</td>
                  </g:each>
                </g:if>
                <g:else>
                  <g:if test="${content.getClass().toString()=='class ContactList'}">
                    <g:each in="${content.contacts}" var="contact" >
                      <td> ${contact}</td>
                    </g:each>
                  </g:if>
                  <g:else>
                    <g:if test="${content.getClass().toString()=='class Feed'}">
                      <g:each in="${content.notifications}" var="notification" >
                        <td> ${notification}</td>
                        <td> ${notification.id}</td>
                      </g:each>
                    </g:if>
                    <g:else>
                      <g:if test="${content.getClass().toString()=='class NoticeBoard'}">
                        <g:each in="${content.notices}" var="notice" >
                          <td> ${notice}</td>
                          <td> ${notice.id}</td>
                        </g:each>
                      </g:if>
                      <g:else>
                        <g:if test="${content.getClass().toString()=='class OperatorInfo'}">
                          <td> ${content.title}</td>
                          <td> ${content.text}</td>
                        </g:if>
                        <g:else>
                          <g:if test="${content.getClass().toString()=='class Blog'}">
                            <g:each in="${content.posts}" var="post" >
                              <td> "${post.author} ${post.title}"</td>
                              <td> ${post.id}</td>
                            </g:each>
                          </g:if>
                        </g:else>
                      </g:else>
                    </g:else>
                  </g:else>
                </g:else>
              </g:else>
            </tr>
          </g:each>
        </table>
      </div>

     <%if (primaryUser) { %>
      <div id="ChangeSmeProfile">
       <h1><u>Change SME Profile</u></h1>
       <g:form name="smeProfileForm" action="changeSmeProfile.html">
        <div id="left" style="float:left; width:450px">
          <h2>Basic profile</h2>
          <table>
            <tr>
              <td>CIF </td>
              <td><g:textField name="smeBasicProfile.cif" value="${sme.cif}"/></td>
            </tr>
            <tr>
              <td>Name </td>
              <td><g:textField name="smeBasicProfile.name" value="${sme.name}"/></td>
            </tr>
            <tr>
              <td>Address </td>
              <td><g:textField name="smeBasicProfile.address" value="${sme.address}"/></td>
            </tr>
            <tr>
              <td>Town </td>
              <td><g:textField name="smeBasicProfile.town" value="${sme.town}"/></td>
            </tr>
            <tr>
              <td>Sector </td>
              <td><g:textField name="smeBasicProfile.sector" value="${sme.sector}"/></td>
            </tr>
            <tr>
              <td>State </td>
              <td><g:textField name="smeBasicProfile.state" value="${sme.state}"/></td>
            </tr>
            <tr>
              <td>Country </td>
              <td><g:countrySelect cols="20" name="smeBasicProfile.country" default="esp" value="${sme.country}"/></td>
            </tr>
            <tr>
              <td>Zip Code </td>
              <td><g:textField name="smeBasicProfile.zipcode" value="${sme.zipCode}"/></td>
            </tr>
            <tr>
              <td>URL Sme </td>
              <td><g:textField name="smeBasicProfile.url" value="${sme.url}"/></td>
            </tr>
            <tr>
              <td>Telephone Number </td>
              <td><g:textField name="smeBasicProfile.telephoneNumber" value="${sme.telephoneNumber}"/></td>
            </tr>
            <tr>
              <td>Cellular Number </td>
              <td><g:textField name="smeBasicProfile.cellularNumber" value="${sme.cellularNumber}"/></td>
            </tr>
            <tr>
              <td>Fax Number </td>
              <td><g:textField name="smeBasicProfile.faxNumber" value="${sme.faxNumber}"/></td>
            </tr>
            <tr>
              <td>Contact email </td>
              <td><g:textField name="smeBasicProfile.contactEmail" value="${sme.contactEmail}"/></td>
            </tr>
            <tr>
          </table>
        </div>

        <div id="right" style="float:right; width:400px">
        <% if (sme.returnProfileData()) { %>
          <div>
          <h2>Advanced profile</h2>
          <table>
          <tr>
            <td>Tax code </td>
            <td><g:textField name="smeAdvancedProfile.taxCode" value="${sme.returnProfileData().taxCode}"/></td>
          </tr>
          <tr>
            <td>Corporate Name </td>
            <td><g:textField name="smeAdvancedProfile.corporateName" value="${sme.returnProfileData().corporateName}"/></td>
          </tr>
          <tr>
            <td>Metatags </td>
            <td><g:textField name="smeAdvancedProfile.metaTags" value="${sme.returnProfileData().metaTags}"/></td>
          </tr>
          </table>
        </div>
        <% } %>

          <%
            def visibility=[:]
            sme.contents.each{
             visibility.put(new String("${it.class}"),new String("${it.visibility}"))
            }
          %>

          <div>
          <h2>Content Profile</h2>
           <table>
              <tr>
                <td>Feed</td>
                <td><g:select name="smeContentProfile.feed" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class Feed']}"></g:select></td>
              </tr>
             <tr>
               <td>Operator Info</td>
               <td><g:select name="smeContentProfile.operatorInfo" from="['PUBLIC','CONTACTS','PRIVATE']" value="${visibility.get('class OperatorInfo')}"></g:select></td>
             </tr>
              <tr>
                <td>Notice Board</td>
                <td><g:select name="smeContentProfile.noticeBoard" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class NoticeBoard']}"></g:select></td>
              </tr>
              <tr>
                <td>Profile Data</td>
                <td><g:select name="smeContentProfile.profileData" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class ProfileData']}"></g:select></td>
              </tr>
              <tr>
                <td>Search Engine</td>
                <td><g:select name="smeContentProfile.searchEngine" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class SearchEngine']}"></g:select></td>
              </tr>
              <tr>
                <td>Recomendation Box</td>
                <td><g:select name="smeContentProfile.recommendationBox" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class RecommendationBox']}"></g:select></td>
              </tr>
              <tr>
                <td>Message Box</td>
                <td><g:select name="smeContentProfile.messageBox" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class MessageBox']}"></g:select></td>
              </tr>
              <tr>
                <td>Blog</td>
                <td><g:select name="smeContentProfile.blog" from="['PUBLIC','CONTACTS','PRIVATE','NONE']" value="${visibility['class Blog']}"></g:select></td>
              </tr>
             <tr>
               <td>Contact List</td>
               <td><g:select name="smeContentProfile.contactList" from="['PUBLIC','CONTACTS','PRIVATE']" value="${visibility['class ContactList']}"></g:select></td>
             </tr>
           </table>
          </div>
        </div>
        <div class="buttons">
          <br>
          <span class="button"><input class="save" type="submit" value="Change SME Profile" align="middle"/></span>
          <br>
        </div>
    </g:form>
    </div>


    <div id="envio_invitaciones">
      <h1><u>Invitations</u></h1>
      <h3>Send Invitation to SME</h3>
      <g:form action="inviteSME.html" method="post">
        <div class="dialog">
          <table>
            <tbody>
            <tr class="prop">
              <td valign="top" class="name">Email *</td>
              <td valign="top" class="value">
                <input type="text" size="40" name="email"/>
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">Nombre de la SME *</td>
              <td valign="top" class="value">
                <input type="text" size="40" name="smeName"/>
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">Persona de contacto</td>
              <td valign="top" class="value">
                <input type="text" size="40" name="userName"/>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <span>Campos con * son obligatorios </span>
          <g:hiddenField name="sender" value="${sme.cif}"/>
          <span class="button"><input class="save" type="submit" value="Send Invitation"/></span>
        </div>
      </g:form>
    </div>

    <div id="invitar_secondary_user">
      <h3>Send Invitation to Secondary User</h3>
      <g:form name="inviteSuForm" action="inviteSU.html" method="post">
        <div class="dialog">
          <table>
            <tbody>
            <tr class="prop">
              <td valign="top" class="name">Email</td>
              <td valign="top" class="value">
                <input type="text" name="email" />
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name" >Nombre del usuario a invitar</td>
              <td valign="top" class="value">
                <input type="text" name="userName" />
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="buttons">
          <g:hiddenField name="sender" value="${sme.primaryUser}"/>
          <g:hiddenField name="smeName" value="${sme.cif}"/>
          <span class="button"><input class="save" type="submit" value="Send Invitation"/></span>
        </div>
      </g:form>
    </div>

  <h1><u>Secondary User</u></h1>
  <div id="delete_user">
    <h3>Delete User</h3>
    <g:form action="delete.html" method="post">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name" >Nif del usuario a borrar</td>
            <td valign="top" class="value">
              <input type="text" name="user" value="${sme.primaryUser}"/>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <g:hiddenField name="company" value="${sme.cif}"/>
        <span class="button"><input class="save" type="submit" value="Delete User from SME"/></span>
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
              <input type="text" name="sme" value="${sme.cif}"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name" >Nif del usuario a promocionar</td>
            <td valign="top" class="value">
              <input type="text" name="user" value="${sme.primaryUser}"/>
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


  <h1><u>Notices</u></h1>
  <div id="add_notice">
    <h3>Add Notice</h3>
    <g:form action="addNotice" method="post">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name" >T&iacute;tulo del anuncio</td>
            <td valign="top" class="value">
              <input type="text" name="title"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name" >Contenido del anuncio</td>
            <td valign="top" class="value">
              <input type="text" name="text" size="80"/>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <g:hiddenField name="senderUser" value="${sme.primaryUser}"/>
        <g:hiddenField name="senderCompany" value="${sme.cif}"/>
        <span class="button"><input class="save" type="submit" value="Add Notice"/></span>
      </div>
    </g:form>
  </div>

  <div id="delete_notice">
    <h3>Delete Notice</h3>
    <g:form action="deleteNotice.html" method="post">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name">Cif de la SME en la que se elimina el anuncio</td>
            <td valign="top" class="value">
              <input type="text" name="senderCompany" value="${sme.cif}"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name" >Identificador del anuncio a eliminar</td>
            <td valign="top" class="value">
              <input type="text" name="idNotice"/>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="Delete Notice"/></span>
      </div>
    </g:form>
  </div>

  <h1><u>Feed Notifications</u></h1>
  <div id="delete_notification">
    <h3>Delete Notification</h3>
    <g:form action="deleteNotification.html" method="post">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name">Cif de la SME en la que se elimina la notificaci&oacute;n</td>
            <td valign="top" class="value">
              <input type="text" name="company" value="${sme.cif}"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name" >Identificador de la notificaci&oacute;n a eliminar</td>
            <td valign="top" class="value">
              <input type="text" name="idNotif"/>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="Delete Notification"/></span>
      </div>
    </g:form>
  </div>


  <h1><u>Blog</u></h1>
  <div id="add_post">
    <h3>Publicar un post</h3>
    <g:form name="formulario" action="addPost.html">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label>T&iacute;tulo para el post:</label>
              </td>
              <td valign="top">
                <input type="text" name="title" size="50"/>
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label>Contenido:</label>
              </td>
              <td valign="top">
                <input type="text" name="text" size="80"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
        <span><input type="hidden" name="senderCompany" value="${sme.cif}"/></span>
        <span class="button"><input class="save" type="submit" value="Publicize Post"/></span>
      </div>
    </g:form>
  </div>

  <div id="modify_post">
    <h3>Modificar un post</h3>
    <g:form name="formulario" action="modifyPost.html">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label>Identificador del post:</label>
              </td>
              <td valign="top">
                <input type="text" name="idPost"/>
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label>T&iacute;tulo para el post:</label>
              </td>
              <td valign="top">
                <input type="text" name="title"  size="50"/>
              </td>
            </tr>
            <tr class="prop">
              <td valign="top" class="name">
                <label>Contenido:</label>
              </td>
              <td valign="top">
                <input type="text" name="text"  size="80"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
        <span><input type="hidden" name="senderCompany" value="${sme.cif}"/></span>
        <span class="button"><input class="save" type="submit" value="Modify Post"/></span>
      </div>
    </g:form>
  </div>

  <div id="delete_post">
    <h3>Eliminar un post</h3>
    <g:form name="formulario" action="deletePost.html">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label>Identificador del post a eliminar:</label>
              </td>
              <td valign="top">
                <input type="text" name="idPost"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span><input type="hidden" name="senderUser" value="${employee.nif}"/></span>
        <span><input type="hidden" name="senderCompany" value="${sme.cif}"/></span>
        <span class="button"><input class="save" type="submit" value="Delete Post"/></span>
      </div>
    </g:form>
  </div>

  <div class="search_post">
        <h3>Buscar un post</h3>
          <g:form name="searchableForm" controller="employee" action="searchBlog.html" >
          <span><input type="hidden" name="user" value="${employee.nif}"/></span>       
          Search: <g:textField name="query" size="30"/> </br>
          Search by:<input type="checkbox" name="search_title" value="yes">Title
                    <input type="checkbox" name="search_text" value="yes">Text
          </br></br><input type="submit" value="Search Post" />
        </g:form>

        <% if (blogdata) {%>
        RESULTADOS DE LA BÚSQUEDA: <br/>
          <g:each var="result" in="${blogdata}" status="index">
              <div class="result">
                ${result}<br/>
               </div><br/>
          </g:each>
        <% } else {%>
        NO HAY RESULTADOS <br/>
        <% }%>

      </div>



  <h1><u>Contacts</u></h1>
  <div id="request_contact">
    <h3>Solicitar contacto a una SME</h3>
    <g:form name="formulario" action="requestContact.html">
      <div class="dialog">
        <table>
          <tbody>
            <tr class="prop">
              <td valign="top" class="name">
                <label>NIF de la SME con la que quiere establecer el contacto *</label>
              </td>
              <td valign="top">
                <g:select name="smeDest" from="${Sme.list()}" optionKey="cif" optionValue="name"/>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        %{--<span><input type="hidden" name="smeOrig" value="${sme.cif}"/></span>--}%
        <span class="button"><input class="save" type="submit" value="Request Contact"/></span>
      </div>
    </g:form>
  </div>

  <div id="accept_contact">
    <h3>Aceptar una solicitud de contacto de una SME</h3>
    <g:form name="formulario" action="acceptContact.html">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name">
              <label>SME destino (si no hay solicitudes pendientes se mostrará vacío)</label>
            </td>
            <td valign="top">
              <g:select name="smeDest" from="${Sme.getById(sme.cif).returnContactList().getInactiveContacts()}" optionKey="sme"/>
              <!--<input type="text" name="smeDest"/>-->
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><input class="save" type="submit" value="Accept Contact"/></span>
      </div>
    </g:form>
  </div>

  <div id="reject_contact">
  <h3>Rechazar una solicitud de contacto de una SME</h3>
  <g:form name="formulario" action="rejectContact.html">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label>SME destino (si no hay solicitudes pendientes se mostrará vacío)</label>
          </td>
          <td valign="top">
            <g:select name="smeDest" from="${Sme.getById(sme.cif).returnContactList().getInactiveContacts()}" optionKey="sme"/>
            <!--<input type="text" name="smeDest"/>-->
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="Reject Contact"/></span>
    </div>
  </g:form>
  </div>

  <div id="delete_contact">
  <h3>Eliminar contacto de una SME</h3>
  <g:form name="formulario" action="deleteContact.html">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label>Contacto a eliminar (si no hay contactos se mostrará vacío)</label>
          </td>
          <td valign="top">
            <g:select name="smeDest" from="${Sme.getById(sme.cif).returnContactList().contacts}" optionKey="sme"/>
            <!--<input type="text" name="smeDest"/>-->
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="Delete Contact"/></span>
    </div>
  </g:form>
  </div>

  <h1><u>Recommendations</u></h1>

  <div id="add_recommendation">
  <h3>Recomendar a una SME</h3>
  <g:form name="formulario" action="addRecommendation.html">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label>SME a la que se quiere recomendar</label>
          </td>
          <td valign="top">
            <g:select name="smeDest" from="${Sme.list()}" optionKey="cif" optionValue="name"/>
            <!--<input type="text" name="smeDest"/>-->
          </td>
        </tr>
        <tr>
          <td valign="top" class="name">
            <label>Descripción (opcional)</label>
          </td>
          <td>
            <input type="text" name="description"/>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="Add Recommendation"/></span>
    </div>
  </g:form>
  </div>

  <div id="delete_recommendation">
  <h3>Eliminar de la web la recomendaci&oacute;n a una SME</h3>
  <g:form name="formulario" action="deleteRecommendation.html">
    <div class="dialog">
      <table>
        <tbody>
        <tr class="prop">
          <td valign="top" class="name">
            <label>SME de la que se desea borrar la recomendaci&oacute;n</label>
          </td>
          <td valign="top">
            <g:select name="smeDest" from="${Sme.getById(sme.cif).returnRecommendationBox()?.recommendations}" optionKey="recommendedSme"/>
            <!--<input type="text" name="smeDest"/>-->
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="buttons">
      <span class="button"><input class="save" type="submit" value="Delete Recommendation"/></span>
    </div>
  </g:form>
  </div>

  <div id="returnLastPosts">
    <h3>returnLastPosts</h3>
    <g:form name="formulario" action="returnLastPosts.xml">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name">
              <label>Número de posts a mostrar</label>
            </td>
            <td valign="top">
              <input type="text" name="nmax"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name">
              <label>Offset del post (10 para mostrar del 11 en adelante...)</label>
            </td>
            <td valign="top">
              <input type="text" name="noffset"/>
            </td>
          </tr>
          </tbody>
        </table>
        <span class="button"><input class="save" type="submit" value="Return Lasts Posts"/></span>
      </div>
    </g:form>
  </div>

  <div id="returnLastPosts">
    <h3>returnLastNotifications</h3>
    <g:form name="formulario" action="returnLastNotifications.xml">
      <div class="dialog">
        <table>
          <tbody>
          <tr class="prop">
            <td valign="top" class="name">
              <label>Número de notifications a mostrar</label>
            </td>
            <td valign="top">
              <input type="text" name="nmax"/>
            </td>
          </tr>
          <tr class="prop">
            <td valign="top" class="name">
              <label>Offset de la notification (10 para mostrar del 11 en adelante...)</label>
            </td>
            <td valign="top">
              <input type="text" name="noffset"/>
            </td>
          </tr>
          </tbody>
        </table>
        <span class="button"><input class="save" type="submit" value="Return Last Post"/></span>
      </div>
    </g:form>
  </div>

  <div id="returnRss">
    <h3>Show RSS</h3>
    <g:form name="formulario" action="showSectorInfo.xml">
      <div class="dialog">
        <span class="button"><input class="save" type="submit" value="Show RSS"/></span>
      </div>
    </g:form>
  </div>

  <% } %>

  <% } %>

  </body>
</html>
