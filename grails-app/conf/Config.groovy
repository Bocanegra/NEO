// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

//grails.config.locations = [ "classpath:${appName}-config.properties",
//                            "classpath:${appName}-config.groovy",
//                            "file:${userHome}/.grails/${appName}-config.properties",
//                            "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      iphone: ['text/html','application/xhtml+xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
grails.converters.encoding="UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// set per-environment serverURL stem for creating absolute links
environments {
  development {
    grails.serverURL = "http://localhost:8080"
  }
  production {
    grails.serverURL = "http://burrito.hi.inet"
  }
}

// log4j configuration
log4j = {

  debug 'grails.app'
  
  appenders {
    console name:'stdout', layout:pattern(conversionPattern: '%d %-5p %c - %m%n')
  }

  error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
       'org.codehaus.groovy.grails.web.pages', //  GSP
       'org.codehaus.groovy.grails.web.sitemesh', //  layouts
       'org.codehaus.groovy.grails."web.mapping.filter', // URL mapping
       'org.codehaus.groovy.grails."web.mapping', // URL mapping
       'org.codehaus.groovy.grails.commons', // core / classloading
       'org.codehaus.groovy.grails.plugins', // plugins
       'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
       'org.springframework',
       'org.hibernate'

  warn   'org.mortbay.log'
}

neo.smtp.mail.server="burrito.hi.inet"
// Valor para el campo "from" de todos los mails enviados desde la red
neo.email.from="neo@tid.es"
neo.invitation.url.flex="http://10.95.46.170/NEOBE/NEOWEB/NEOWEB.html"
// Lista de sectores iniciales, separados por punto y coma (;)
neo.sectors="Airline Tickets; Business; Building Materials; Cars; Communication & Networking; \
             Computers; Construction Equipment; Entertainment; Finance; Fitness; Furniture; \
             Health; Hotels; Industrial Consumables; Insurance; Music; Restaurants; Security Products; \
             Shopping; Software; Sports; Toys; Transport Equipment; Trips; Vacations"
neo.cleaning.feeds.days="2"
//neo.date.format="dd/MM/yyyy-HH:mm" // Spanish date format
neo.date.format="MMM/dd/yyyy-hh:mm a" // English date format

// RSS para cada sector ----> OJO:  "TITULO ; RSS"
rss.sectors = [
    'Construction Equipment' : [
//        'CINCODIAS.com - Sector construcción y autopistas;http://www.cincodias.com/rss/feed.html?feedId=17130',
//        'CINCODIAS.com - Sector Inmobiliaria;http://www.cincodias.com/rss/feed.html?feedId=17137'
///        'Indicadores económicos;http://feeds.feedburner.com/IndicadoresEconomicos'
//        'Expansion.com - Empresas de construcción;http://rss.expansion.com/rss/descarga.htm?data2=289',
//        'Expansion.com - Inmobiliarias;http://rss.expansion.com/rss/descarga.htm?data2=292',
//        'Construction Equipment Latest News;http://www.constructionequipment.com/rss109.xml',
//        'Construction Bulletin;http://feeds.feedburner.com/ConstructionBulletin?format=xml',
//        'Euribor Rates;http://feeds.feedburner.com/EuriborRates'
//        'Precios oficiales España vivienda;http://elbocanegra.blogspot.com/feeds/posts/default'
        'Construction Equipment Green;http://feeds.feedburner.com/ConstructionEquipmentGreen',
        'Zibb.com: Construction Equipment;http://www.zibb.com/construction/interstitial/newsblogs/Construction_Construction+Equipment?s=date&param=rss'
    ],
    'Restaurants' : [
//        'VARIOS - Escapadas , Restaurantes en ABC.es;http://www.abc.es/rss/feeds/abc_ViajesGourmet.xml',
        'Restaurant News Resource;http://feeds.feedburner.com/restaurantnewsresource/gSvh?format=xml',
        'Suite101: U.K./Ireland Travel Articles;http://rss.suite101.com/ukirelandtravel.xml'
        
    ],
    'Trips' : [
//        'AdventureEngine - Trip Feed;http://www.adventureengine.com/feeds/rssTrip30Days.xml',
//        'CINCODIAS.com - Sector Turismo y Ocio;http://www.cincodias.com/rss/feed.html?feedId=17148',
//        'CINCODIAS.com - Sector Transportes;http://www.cincodias.com/rss/feed.html?feedId=17147'
//        'Revista Viajar;http://www.revistaviajar.es/rss/indice.xml'
        'Frommers.com Cruise Blog;http://community.frommers.com/ver1.0/Blog/BlogRss?plckBlogId=Blog:52f8da68-7f6d-4edc-b082-451715e6da97',
        'Frommers.com Deals & News;http://www.frommers.com/rss/deals_and_news.xml'
    ]
]
