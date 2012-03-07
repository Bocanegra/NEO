<html>
  <head>
    <title><g:layoutTitle default="iPhone"/></title>
    <style type="text/css" media="screen">@import "${createLinkTo(dir:'css',file:'iphone2.css')}";</style>
    <style type="text/css" media="screen">@import "${createLinkTo(dir:'WebApp/Design',file:'Render.css')}";</style>
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <link rel="apple-touch-icon" href="${createLinkTo(dir: 'images', file: 'apple-touch-icon.png')}"/>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:layoutHead/>
    <script type="application/x-javascript" src="${createLinkTo(dir:'WebApp/Action',file:'Logic.js')}"></script>
  </head>

  <body>
    <div id="WebApp">
      <g:render template="/iphone2/header"/>
      <g:layoutBody/>
      <g:render template="/iphone2/footer"/>
    </div>  
  </body>
</html>
