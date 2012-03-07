<%@ page import="groovy.time.*" %>
<div class="iLayer" id="list0">
  <%  def objects=[]
      if (sme.returnFeed()) objects+=sme.returnFeed().notifications
      if (sme.returnNoticeBoard()) objects+=sme.returnNoticeBoard().notices
  %>

  %{--<g:if test="${sme.returnOperatorInfo()}">--}%
    %{--<div class="iBlock">--}%
      %{--<p><span style="font-weight:bold; text-align:center;">OPERATOR INFO</span><br/>--}%
      %{--${sme.returnOperatorInfo().text}</p>--}%
    %{--</div>--}%
  %{--</g:if>--}%

  <div class="iBlock">
    <p class="pestanas">
     From last time you have ${sme.returnMessageBox().returnMessagesFromLastLogout(employee).size()}
     new messages.</br>
     Your company has received ${sme.returnNoticeBoard().returnNoticesFromLastLogout(employee.lastLogout).size()}
     notices.
    </p>
  </div>

  <g:if test="${!objects}">
    <div class="iBlock">
      <p class="pestanas">You have no feeds or notices</p>
    </div>
  </g:if>

  <g:if test="${objects}">
    <div class="iList">
      <h2>Today</h2>
      <g:each in="${objects.findAll { it.dateCreated>(new Date()-1) }}" var="object">
        <ul class="iArrow">
          <li id="peque">${object.text} (<g:formatDate format="HH:mm" date="${object.dateCreated}"/>)</li>
        </ul>
      </g:each>

      <h2>Last week</h2>
      <g:each in="${objects.findAll { it.dateCreated<=new Date()-1 && it.dateCreated>=new Date()-7 }}" var="object">
        <ul class="iArrow">
          <li id="peque">${object.text} (<g:formatDate format="dd/MMM" date="${object.dateCreated}"/>)</li>
        </ul>
      </g:each>

      <h2>Old notices</h2>
      <g:each in="${objects.sort().findAll { it.dateCreated<new Date()-7 }}" var="object">
        <ul class="iArrow">
          <li id="peque">${object.text} (<g:formatDate format="dd/MMM" date="${object.dateCreated}"/>)</li>
        </ul>
      </g:each>

    </div>
  </g:if>

</div>
