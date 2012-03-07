<html>

  <head>
    <title>SME Communities</title>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
    <g:javascript library="prototype"/>
  </head>

  <body>
    <div id="spinner" class="spinner" style="display:none;">
      <img src="${createLinkTo(dir: 'images', file: 'spinner.gif')}" alt="Spinner"/>
    </div>

    <div id="container">
      <div id="header">
        <g:render template="/login_info"/>
        <div class="header_top">
          <div id="logo">
            <img alt="Home" src="${createLinkTo(dir: 'images', file: 'logo.png')}"/>
          </div>
        </div>
        <g:render template="/menu_sup"/>
      </div>

      <div class="wrapper">
        <g:layoutBody/>
      </div>

    </div>
  </body>
</html>