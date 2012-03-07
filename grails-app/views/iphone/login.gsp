<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>NEO</title>
    <meta name="layout" content="iphone"/>
  </head>

  <body>

  <div class="toolbar">
    <h1 id="pageTitle"></h1>
    <a id="backButton" class="button" href="#"></a>
    <a class="button" href="#help">Help</a>
  </div>

  <ul id="home" title="NEO" selected="true">
    <li><a href="#login">User login</a></li>
    <!--<li><a href="#admin">Administrator</a></li>-->
    <li><a href="#help">Who to use...</a></li>
  </ul>

  <g:form name="login" title="User" class="panel" action="validate.iphone" target="_self">
    <fieldset>
      <div class="row">
        <label>Email</label><input type="text" name="user" value="lagm@tid.es"/>
      </div>
      <div class="row">
        <label>Password</label><input type="password" name="password" value="luis"/>
      </div>
    </fieldset>
    <a class="whiteButton" type="submit" href="#">Login</a>
  </g:form>

  <g:form name="admin" title="Administrator" class="panel" action="administrator/validate.iphone" target="_self">
    <fieldset>
      <div class="row">
        <label>Email</label><input type="text" name="user"/>
      </div>
      <div class="row">
        <label>Password</label><input type="password" name="password"/>
      </div>
    </fieldset>
    <a class="whiteButton" type="submit" href="#">Login</a>
  </g:form>

  <div id="help" class="panel" title="Ayuda">
    <h2>Esto es el porting de la aplicación a iPhone. Para utilizarlo, se usan los controles
    habituales del teléfono, tales como listas, formularios y demás.</h2>
    <h2>Los datos son sincronizados con el servidor automáticamente, de forma que se puedan ver también 
    en la web.</h2>
  </div>

  </body>
</html>
