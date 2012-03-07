<div class="iLayer" id="list2">
  <div class="iBlock">
    <p><img src="${resource(dir:'images', file:'Stocks_peq.png')}"/> <span class="pestanas">Sector info</span></p>
  </div>

  <div class="iList">
    <g:each in="${sme.sector.tokenize(',')}" var="s">
      <% def sector=Sector.findByName(s) %>
      <g:if test="sector">
        <h2>${sector.name}</h2>
        <ul class="iArrow">
        <g:each in="${sector.feeds?.readLines()}" var="feed">
          <% def titulo=feed.tokenize(';')[0]
             def rss=feed.tokenize(';')[1] %>
          <li id="peque">
            <a href="rss.iphone?rss=${rss.encodeAsURL()}#_Info" rev="async">${titulo}</a>
          </li>
        </g:each>
        </ul>
      </g:if>
    </g:each>

  </div>

</div>
