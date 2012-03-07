<html>
  <head>
    <meta name="layout" content="itheme"/>
  </head>
  <body>

  <div id="left-col">
    <div id="nav">
      <ul>
        <li class="page_item current_page_item"><a href="${createLink(controller:'employee')}" title="Employee">Employee</a></li>
        <li class="page_item"><a href="${createLink(controller:'administrator')}" title="Administrator">Administrator</a></li>
        <li class="page_item"><a href="${createLink(controller:'help')}" title="Help">Help</a></li>
      </ul>
    </div><!-- /nav -->

    <div id="content">
      <div class="post" id="post-11">
        <div class="date"><span>Apr</span> 12</div>
        <div class="title">
          <h2>Prueba</h2>
          <div class="postdata">
            <span class="category">Pruebas</span>
            <span class="comments">Comentarios</span>
          </div>
        </div>
        <div class="entry">
        </div><!--/entry -->
      </div><!--/post -->
      <!--<div class="page-nav">-->
        <!--<span class="previous-entries"><a href="">Previous Entries</a></span>-->
        <!--<span class="next-entries"></span>-->
      <!--</div>--><!-- /page nav -->
    </div> <!--/content -->

    <!-- Se mete el pie -->
    <g:render template="/footer"/>

  </div><!-- /left-col -->

  <div id="sidebar" class="dbx-group">

    <div id="id_sme1" class="dbx-box">
      <h3 class="dbx-handle">SME name 1</h3>
      <div class="dbx-content">
        <ul>
          <li><a href="/" title="View all posts filed under Design">Design</a></li>
          <li><a href="/" title="View all posts filed under Notes">Notes</a></li>
        </ul>
      </div>
    </div>

    <div id="id_sme2" class="dbx-box">
      <h3 class="dbx-handle">SME name 2</h3>
      <div class="dbx-content">
        <ul id="themeswitcher">
          <li><a href="/">GlossyBlue</a></li>
        </ul>
      </div>
    </div>

    <div id="id_sme3" class="dbx-box">
      <h3 class="dbx-handle">SME name 3</h3>
      <div class="dbx-content">
        <ul>
          <li><a class='rsswidget' href='/' title='If [...]'>Finding Inspiration From Your Environment - Slides</a></li>
        </ul>
      </div>
    </div>

  </div><!--/sidebar -->
  <hr class="hidden"/>

  </body>
</html>