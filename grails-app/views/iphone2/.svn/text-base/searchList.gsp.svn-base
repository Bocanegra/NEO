<root>
  <destination mode="replace" zone="waResultSearch"/>
  <data><![CDATA[

  <a href="#_Search" rel="back" class="iButton iBAction" id="waBackButton">Back</a>
  <div class="iBlock">
    <p>${advanceddata?.size?:"No"} results for query</p>
  </div>
  <div class="iMenu" id="MenuSme">
    <ul class="iArrow iShop">
      <g:each in="${advanceddata}" status="i" var="sme">
        <li>
          <a href="showSmeInfo.iphone?id=${sme.cif}#_Info" rev="async">
            <img src="${resource(dir:'', file:sme.urlLogo)}" class="iFull"/>
            <em>${sme.numRecommendations} recommendations</em>
            <big>${sme.name}
              <small>${sme.returnStringContacts()}</small>
            </big>
          </a>
        </li>
      </g:each>
    </ul>
  </div>

  ]]></data>
</root>
