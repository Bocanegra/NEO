<root>
  <destination mode="self" zone="waInfo"/>
  <data><![CDATA[

  <div class="iLayer"id="waInfo">
    <a href="#" rel="back" class="iButton iBAction" id="waBackButton">Back</a>
    <div class="iList">
      <ul class="iArrow">
        <h2>${title}</h2>
        <g:each in="${feeds}" status="i" var="item">
          <li id="peque"><a href="#_Item${i}">${item.title.text()}</a></li>
        </g:each>
      </ul>
    </div>
  </div>

  <g:if test="${type.equals('RSS')}">
    <g:each in="${feeds}" status="i" var="item">
      <div class="iLayer" id="waItem${i}">
        <a href="#_Info" rel="back" class="iButton iBAction" id="waBackButton">Back</a>
        <div class="iBlock">
          <p>${item.description.text()}</p>
          <p align="center"><a href="${item.link.text()}" target="_new">Open in Safari</a></p>
        </div>
      </div>
    </g:each>
  </g:if>

  <g:if test="${type.equals('Atom')}">
    <g:each in="${feeds}" status="i" var="item">
      <div class="iLayer" id="waItem${i}">
        <a href="#_Info" rel="back" class="iButton iBAction" id="waBackButton">Back</a>
        <div class="iBlock">
          <p>${item.content.text()}</p>
        </div>
      </div>
    </g:each>
  </g:if>

  ]]></data>
</root>
