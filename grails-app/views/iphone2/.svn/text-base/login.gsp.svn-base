<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>SME Communities</title>
    <meta name="layout" content="iphone2"/>
  </head>

  <body>
    <div id="iGroup">
      <div class="iLayer" id="waLogin" title="Login">
        %{--<a href="#" rel="action" class="iButton iBAction" onclick="return WA.Submit('form_login')" id="logme">Login</a>--}%
        <div class="iBlock">
          <p align="center"><img height="70" src="${resource(dir:'images', file:'moleculas_color.png')}"/></p>
        </div>
        <form id="form_login" action="validate.iphone" onsubmit="return WA.Submit(this)">
          <div class="iPanel">
            <fieldset>
              <ul>
                <li>
                  <label>Email</label>
                  <input type="text" name="user" value="paul@thehireman.com"/>
                </li>
                <li>
                  <label>Password</label>
                  <input type="password" name="password" value="paul"/>
                </li>
              </ul>
            </fieldset>
          </div>
          <div align="center"><a class="iPush iBAction iBClassic" type="submit" onclick="return WA.Submit('form_login')">Login</a></div>
        </form>

        <!-- Para los errores en el login -->
        <div id="form-res"></div>

      </div>

      <div class="iLayer" id="waInfo" title="SME info">
      </div>

      <div class="iLayer" id="waSearch" title="Search">
        <a href="#_Login" rel="back" class="iButton iBAction">Home</a>
        <form id="form_search" action="advancedsearch.iphone#_ResultSearch">
          <div class="iPanel">
            <fieldset>
              <ul>
                <li><input type="text" name="filter.name" placeholder="Query"/></li>
                <li><input type="text" name="filter.town" placeholder="Town"/></li>
                <li>
                  <g:select name="query" multiple="multiple" optionKey="name" optionValue="name" from="${Sector.list()}"></g:select>
                </li>
              </ul>
            </fieldset>
          </div>
          <div align="center">
            <a class="iPush iBClassic" type="submit"
               onclick="return WA.Submit('form_search')">Search</a>
          </div>
        </form>
      </div>
      <div class="iLayer" id="waResultSearch" title="Search">
      </div>

    </div>
  </body>
</html>
