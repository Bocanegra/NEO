import org.springframework.web.context.support.WebApplicationContextUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.util.GrailsUtil

class BootStrap {

  def init = { servletContext ->

    // Si no existe el administrador del sistema se crea
    def admin = Administrator.findByUser("admin")
    if (!admin) {
      log.debug("No admin, creating Administrator admin...")
      admin = new Administrator(user: "admin", email: "support_neo@tid.es")
      admin.assignPassword("admin")
      if (!admin.save()) {
        log.debug("Failed to create Administrator admin. See error log...")
        admin.errors.each {
          log.error it
        }
      }
    }

    // Si los sectores no están creados, se crean
    if (!Sector.list()) {
      def sectors = ConfigurationHolder.config.neo.sectors
      def rssSectors = ConfigurationHolder.config.rss.sectors
      sectors.tokenize(";").each {
        def sector = new Sector(name: it.trim())
        def feeds = rssSectors[it.trim()]
        feeds.each {
          sector.addFeed(it.trim())
        }
        if (!sector.save()) {
          log.debug("Failed to create Sectors. See error log...")
          sector.errors.each {
            log.error it
          }
        }
      }
    }

    //Se captura la sesion hibernate para el funcionamiento de las colas.
    def context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
    def sessionFactory = context.getBean('sessionFactory')
    HibernateUtil.setSessionFactory(sessionFactory)


    switch (GrailsUtil.environment) {
      case "development":
      case "production":
        def employee1 = Employee.findByNif("12345678P")
        if (!employee1) {
          employee1 = new Employee(name: "Paul", surname: "Jones", nif: "12345678P", urlAvatar: "/avatars/12345678P.png")
          employee1.setPassword("paul")
          if (!employee1.save()) log.info("Failed to create Employee. See error log...")
        }

        def employee2 = Employee.findByNif("12345686P")
        if (!employee2) {
          employee2 = new Employee(name: "Jack", surname: "Sheppard", nif: "12345686P", urlAvatar: "/avatars/12345686P.jpg")
          employee2.setPassword("jack")
          if (!employee2.save()) log.info("Failed to create Employee. See error log...")
        }

        def employee3 = Employee.findByNif("12345695P")
        if (!employee3) {
          employee3 = new Employee(name: "Kate", surname: "Watson", nif: "12345695P", urlAvatar: "/avatars/12345660P.png")
          employee3.setPassword("kate")
          if (!employee3.save()) log.info("Failed to create Employee. See error log...")
        }

        def employee4 = Employee.findByNif("12345650P")
        if (!employee4) {
          employee4 = new Employee(name: "Tom", surname: "Bridge", nif: "12345650P", urlAvatar: "/avatars/12345650P.jpg")
          employee4.setPassword("tom")
          if (!employee4.save()) log.info("Failed to create Employee. See error log...")
        }

        def employee5 = Employee.findByNif("12345658P")
        if (!employee5) {
          employee5 = new Employee(name: "David", surname: "Bennett", nif: "12345658P", urlAvatar: "/avatars/12345658P.jpg")
          employee5.setPassword("david")
          if (!employee5.save()) log.info("Failed to create Employee. See error log...")
        }

        def employee6 = Employee.findByNif("12345659P")
        if (!employee6) {
          employee6 = new Employee(name: "Robert", surname: "Lewis", nif: "12345659P", urlAvatar: "/avatars/12345659P.jpg")
          employee6.setPassword("robert")
          if (!employee6.save()) log.info("Failed to create Employee. See error log...")
        }

       def employee7 = Employee.findByNif("12345660P")
        if (!employee7) {
          employee7 = new Employee(name: "Natalie", surname: "Perry", nif: "12345660P", urlAvatar: "/avatars/12345695P.jpg")
          employee7.setPassword("natalie")
          if (!employee7.save()) log.info("Failed to create Employee. See error log...")
        }

       def employee8 = Employee.findByNif("12345661P")
        if (!employee8) {
          employee8 = new Employee(name: "Rose", surname: "Anderson", nif: "12345661P", urlAvatar: "/avatars/12345661P.jpg")
          employee8.setPassword("rose")
          if (!employee8.save()) log.info("Failed to create Employee. See error log...")
        }

       def employee9 = Employee.findByNif("12345662P")
        if (!employee9) {
          employee9 = new Employee(name: "Emily", surname: "Cale", nif: "12345662P", urlAvatar: "/avatars/12345662P.png")
          employee9.setPassword("emily")
          if (!employee9.save()) log.info("Failed to create Employee. See error log...")
        }

        def sme1 = Sme.findByCif("A58818501")
        if (!sme1) {
          sme1 = new Sme(primaryUser: "12345650P", cif: "A58818501", name: "Isango", address: "48-54 Charlotte Street", town: "London", sector: "Trips", country: "United Kingdom", numRecommendations: "2", urlLogo: "/avatars/isango.png", logoType: "png", telephoneNumber: "0203 355 1240", contactEmail:"support@isango.es")
          sme1.description = "Isango! is an ambitious young company launched in 2007. We offer both consumers and travel professionals an authoritative source of exciting and colorful experiences."
          sme1.solutions = """
    * Holiday Tours
    * Sightseeing
    * Attractions
    * Activities
    * Best things to do in more than 50 countries across the world to plan and book ahead
          """
          sme1.url = "http://www.isango.com"
          sme1.products = "Holiday tours, sightseeing, attractions, activities and best things to do in more than 50 countries across the world to plan and book ahead."
          sme1.addToContents(new ProfileData(metaTags: "Travels, holiday tours, sightseeing, attractions, activities", visibility: "PUBLIC"))
          sme1.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist1 = new ContactList(visibility: "PUBLIC")
          contactlist1.addToContacts(new Contact(sme: "A58818503", smeName: "GoLearnTo", state: Contact.WEAK, recommender:true))
          contactlist1.addToContacts(new Contact(sme: "A58818504", smeName: "GoodToSee", state: Contact.WEAK, recommender:true))
          contactlist1.addToContacts(new Contact(sme: "A58818506", smeName: "Silver Saints", state: Contact.WEAK, recommender:false))
          contactlist1.addToContacts(new Contact(sme: "A58818508", smeName: "Lords Builder Merchants", state: Contact.STRONG, recommender:false))
          contactlist1.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          sme1.addToContents(contactlist1)
          def recommendation1=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation1.addRecommendation("A58818508", "Lords is the part you'd want. Our company warranty people sent them out and we were impressed with them professionalism and work.")
          sme1.addToContents(recommendation1)
          def feed1 = new Feed (visibility: "PUBLIC")
          feed1.addNotification("New recommendation to SME","Sme GoLearnTo has recommended SME Colebrook BS ")
          feed1.addNotification("New blog post","Sme Travel Zone Ltd has a new post in his blog")
          sme1.addToContents(feed1)
          def notice1 = new NoticeBoard(visibility: "PUBLIC")
          notice1.addNotice("A58818501","Big Groups Means Big Savings","If you are planning a big group ski trip, whatever you do, don't stroll up to the ticket office and expect to get a group discount. In ordert o qualify for group deals, you will need 20+ people, and definitely need to purchase your tickets in advance either on the phone or via the Internet")
          sme1.addToContents(notice1)
          def blog = new Blog(visibility: "PUBLIC")
          blog.addPost("Carlton Jones", "Where is everybody?", "Through with exhausting jungle trekking I'd made my way once more to the east coast for a few relaxing days by the beach before flying on the 11th. Although now passed it's heyday, Cherating was once a favourite stop on the travellers route up the coast, with a long stretch of sand, good accommodation options and a bohemian paced atmosphere people quite often lingered for much longer than anticipated")
          sme1.addToContents(blog)
          def message1 = new MessageBox(visibility:"PUBLIC")
          message1.addMessage("12345678P","12345678P","A58818509","Trade Fair in September","Hi Paul, do you know anything about the new trade fair what will be organizated in Paris. The date is 20th September. Could we prepare a trip, what do you think? See you!!")
          sme1.addToContents(message1)
          if (!sme1.save()) {
            log.info("Failed to create Sme1. See error log...")
            sme1.errors.each { log.error it }
          }
        }

        def sme2 = Sme.findByCif("A58818502")
        if (!sme2) {
          sme2 = new Sme(primaryUser: "12345686P", cif: "A58818502", name: "Travel Zone Ltd", address: "93 Judd Street", town: "London", sector: "Trips", country: "United Kingdom", numRecommendations: "1", urlLogo: "/avatars/travelzone.png", logoType: "png", telephoneNumber: "2073874444", contactEmail:"sales@travelzoneltd.co.uk", faxNumber:"0207 387 4494")
          sme2.description = "If you are looking for budget flights, holidays, lesiure break, business trip or just a weekend away then you have come to the right place. "
          sme2.solutions="Flights, Hotel booking, carhire, visa service"
          sme2.products = "Flights, Hotel booking, carhire, visa service"
          sme2.url = "http://www.travelzoneltd.co.uk/"
          sme2.addToContents(new ProfileData(metaTags: "Flights, Hotel booking, carhire", visibility: "PUBLIC"))
          sme2.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme2.addToContents(new Feed(visibility: "PUBLIC"))
          sme2.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          def contactlist2 = new ContactList(visibility: "PUBLIC")
          contactlist2.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          sme2.addToContents(contactlist2)
          def blog = new Blog(visibility: "PUBLIC")
          blog.addPost("Chris Ellis", "Today I began a new life", "Today I shed my old skin which hath, too long, suffered the bruises of failure and the wounds of mediority.   runescape goldToday I am born anew and my birthplace is a vineyard where there is fruit for all.Today I will pluck grapes of wisdom from the tallest and fullest vines in the vineyard,for these were planted by the wisest of my profession who have come before me,generation upon generation...")
          blog.addPost("Alexandra Miller", "Underwater Lakes near Portocristo", "Just south of the town of Portocristo are the Cuevas del Drach, which are really four caves all connected to each other. This cave however has a large lake in it, called Martel Lake. It's large enough to ride a boat in and to see all the stalactites hanging from the ceiling. It’s a beautiful place and one you shouldn’t miss if you ever make it to Mallorca. ...")
          sme2.addToContents(blog)
          sme2.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          sme2.addToContents(new MessageBox(visibility: "PUBLIC"))          
          if (!sme2.save()) {
            log.info("Failed to create Sme2. See error log...")
            sme2.errors.each { log.error it }
          }
        }

        def sme3 = Sme.findByCif("A58818503")
        if (!sme3) {
          sme3 = new Sme(primaryUser: "12345678P", cif: "A58818503", name: "GoLearnTo", address: "27 Rochester Square", town: "London", sector: "Trips", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/golearnto.png", logoType: "png", telephoneNumber: "08445 020445", contactEmail:"hello@golearnto.com")
          sme3.description = "GoLearnTo.com offer a huge selection of learning holidays in countries all around the world. We specialise in offering courses from learning to speak a new language, cooking courses, dancing and photography, to learning how to surf, kitesurf, skydive and horse ride – to name but a few."
          sme3.url = "http://www.GoLearnTo.es"
          sme3.products = "Learning holidays, cooking courses, dancing, courses, photography courses, surf"
          sme3.addToContents(new ProfileData(metaTags: "Learning holidays, cooking courses, dancing, courses, photography courses, surf", visibility: "PUBLIC"))
          sme3.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist3 = new ContactList(visibility: "PUBLIC")
          contactlist3.addToContacts(new Contact(sme: "A58818501", smeName: "Isango", state: Contact.STRONG, recommender:false))
          contactlist3.addToContacts(new Contact(sme: "A58818506", smeName: "Silver Saints", state: Contact.WEAK, recommender:false))
          contactlist3.addToContacts(new Contact(sme: "A58818508", smeName: "Lords Builder Merchants", state: Contact.STRONG, recommender:false))
          sme3.addToContents(contactlist3)
          def feed = new Feed (visibility: "PUBLIC")
          feed.addNotification("New blog post","Sme Isango has a new post in his blog")
          sme3.addToContents(feed)
          def notice = new NoticeBoard(visibility: "PUBLIC")
          notice.addNotice("A58818503","We are looking for an employee","We need an qualified employee to drive a big truck to London. Basic salary. 699875458")
          notice.addNotice("A58818503","New local","GoLearnTo has opened a new local in Birmingham, you're always welcome !!")
          sme3.addToContents(notice)
          def blog = new Blog(visibility: "PUBLIC")
          blog.addPost("Karl White", "The job", "So in case you were wondering, I DO actually work here. That is, I have a place that I go forty hours a week.  And I think there will be a paycheck at some point. But I realized that I haven't written that much about my job as a teacher over here... ")
          blog.addPost("Phillip Chapman", "End of the Latin American road", "So here we are, the end of the Latin American line, the last hurrah, as it were.  Although there wasn't much hurrah to be had, in fairness: on rejoining the lads I found them to be quite unexuberrant.  In fact, this was probably the most feeble I have witnessed them on the whole trip - I guess the Cici-reunion celebrations of the previous few days had taken their toll, bless...")
          blog.addPost("Rebecca Hall", "Bharatpur, Fatepur Sikri and Agra", "We left Jaipur early the next day, as we had two stops to make on the way to Agra. The first was Bharatpur, where we wandered and rode (by cycle-rickshaw) round the wildlife park for a couple of hours. We were lucky to see a variety of animals and birds including; antelope, white-faced monkeys, mud-shell turtles, herons, kingfishers, spotted owlets, a night jar and swarms of colourful butterflies to name a few....")
          sme3.addToContents(blog)
          RecommendationBox recommendation3=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation3.addRecommendation("A58818508", "Experience in treatment and contract suppliers. Excellent in treatment with clients")
          recommendation3.addRecommendation("A58818501", "Guarantee of the seriousness")
          sme3.addToContents(recommendation3)
          sme3.addToContents(new MessageBox(visibility: "PUBLIC"))
          if (!sme3.save()) log.info("Failed to create Sme3. See error log...")
        }

        def sme4 = Sme.findByCif("A58818504")
        if (!sme4) {
          sme4 = new Sme(primaryUser: "12345678P", cif: "A58818504", name: "GoodToSee", address: "30 Culver Rd", town: "St. Albans", sector: "Trips", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/goodtosee.png", logoType: "jpeg", telephoneNumber: "1727 834475", faxNumber: "1727 847418",contactEmail:"sales@goodtosee.com")
          sme4.url = "http://www.goodtosee.com/"
		  sme4.description="Good To See are dedicated to providing enrichment to the lives, knowledge and experiences of the students in the schools we supply and have been doing so for 25 years"
          sme4.solutions="School trips, class outings and educational travel itineraries, providing travel, accommodation, lectures, talks, tours, tickets"
          sme4.addToContents(new ProfileData(metaTags: "School trips, class outings, educational travel itineraries", visibility: "PUBLIC"))
          sme4.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist4 = new ContactList(visibility: "PUBLIC")
          contactlist4.addToContacts(new Contact(sme: "A58818501", smeName: "Isango", state: Contact.STRONG, recommender:false))
          contactlist4.addToContacts(new Contact(sme: "A58818508", smeName: "Lords", state: Contact.STRONG, recommender:false))
          contactlist4.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          sme4.addToContents(contactlist4)
          sme4.addToContents(new Feed(visibility: "PUBLIC"))
          sme4.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme4.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme4.addToContents(new Blog(visibility: "PUBLIC"))
          RecommendationBox recommendation4=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation4.addRecommendation("A58818501", "Amazing activities and nice teachers. If you want relax and enjoy of you free time, the choice is clear:Isango")
          recommendation4.addRecommendation("A58818508", "Union Jack Plumbing, run by porefssional people. We just used him recently and he was freaking awesome.")
          sme4.addToContents(recommendation4)
          if (!sme4.save()) log.info("Failed to create Sme4. See error log...")
        }

        def sme5 = Sme.findByCif("A58818505")
        if (!sme5) {
          sme5 = new Sme(primaryUser: "12345678P", cif: "A58818505", name: "Norwegian Log Cabins", address: "22 Church Street", town: "London", sector: "Construction Equipment", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/norwegian.jpg", logoType: "jpeg", telephoneNumber: "845 409 1590", contactEmail:"sales@norwegianlogcabins.co.uk")          
		  sme5.url = "http://www.norwegianlogcabins.co.uk/"
		  sme5.description = "We supply the VERY BEST Norwegian manufactured luxury log cabins and log homes up to three storeys high! "
          sme5.addToContents(new ProfileData(metaTags: "Norwegian Log cabins", visibility: "PUBLIC"))
          sme5.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme5.addToContents(new ContactList(visibility: "PUBLIC"))
          sme5.addToContents(new Feed(visibility: "PUBLIC"))
          sme5.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme5.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme5.addToContents(new Blog(visibility: "PUBLIC"))
          sme5.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme5.save()) log.info("Failed to create Sme5. See error log...")
        }

        def sme6 = Sme.findByCif("A58818506")
        if (!sme6) {
          sme6 = new Sme(primaryUser: "12345678P", cif: "A58818506", name: "Silver Saints", address: "91A Ramsden Road", town: "London", sector: "Construction Equipment", country: "United Kingdom", numRecommendations: "1", urlLogo: "/avatars/silversaints.png", logoType: "png", telephoneNumber: "2070999199", contactEmail: "fixit@silversaints.co.uk")
          sme6.description="It’s no longer necessary to call separate trades people to get your ‘to do’ list done. Silver Saints will send you one multi-skilled and very experienced handyman who can take care of all your Plumbing, Electrical, Fitting, Fixing, Hanging and Assembling jobs"
		  sme6.url="http://www.silversaints.com/"
          sme6.solutions = """
			Plumbing
			Electrics
			Hanging Stuff
			Fitting, Fixing, Assembling
          """
          sme6.addToContents(new ProfileData(metaTags: "Plumbing,Electric,Hanging Stuff,Fitting, Fixing, Assembling", visibility: "PUBLIC"))
          sme6.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist6 = new ContactList(visibility: "PUBLIC")
          contactlist6.addToContacts(new Contact(sme: "A58818501", smeName: "Isango", state: Contact.WEAK, recommender:false))
          contactlist6.addToContacts(new Contact(sme: "A58818503", smeName: "GoLearnTo", state: Contact.WEAK, recommender:false))
          contactlist6.addToContacts(new Contact(sme: "A58818512", smeName: "Namco Station", state: Contact.WEAK, recommender:false))
          contactlist6.addToContacts(new Contact(sme: "A58818513", smeName: "Grape Entertainers", state: Contact.WEAK, recommender:true))
          sme6.addToContents(contactlist6)
          sme6.addToContents(new Feed(visibility: "PUBLIC"))
          sme6.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme6.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme6.addToContents(new Blog(visibility: "PUBLIC"))
          sme6.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme6.save()) log.info("Failed to create Sme6. See error log...")
        }

        def sme7 = Sme.findByCif("A58818507")
        if (!sme7) {
          sme7 = new Sme(primaryUser: "12345695P", cif: "A58818507", name: "Colebrook BS", address: "35 Union Street", town:"London",sector: "Construction Equipment,Trips", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/colebrook.png", logoType: "png", telephoneNumber: "207 940 4266", faxNumber:"4207 940 4299", contactEmail: "sales@cbsproducts.co.uk")
		  sme7.url="www.colebrookbossonsaunders.com/"
          sme7.description="Colebrook BS are designers, manufacturers and worldwide distributors of award-winning ergonomic office furniture accessories."
          sme7.solutions="""
			Monitor Supports
			CPU Support
			Cable Management
          """
          sme7.addToContents(new ProfileData(metaTags: "Ergonomic, Monitor Support, Furniture, Ergonomic furniture", visibility: "PUBLIC"))
          sme7.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme7.addToContents(new ContactList(visibility: "PUBLIC"))
          sme7.addToContents(new Feed(visibility: "PUBLIC"))
          sme7.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme7.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme7.addToContents(new Blog(visibility: "PUBLIC"))
          sme7.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme7.save()) log.info("Failed to create Sme7. See error log...")
        }

        def sme8 = Sme.findByCif("A58818508")
        if (!sme8) {
          sme8 = new Sme(primaryUser: "12345678P", cif: "A58818508", name: "Lords Builder Merchants", address: "119 Westbourne Grove", town: "London", sector: "Construction Equipment", country: "United Kingdom", numRecommendations: "4", urlLogo: "/avatars/lords.png", logoType: "png", telephoneNumber: "020-7221 4756", faxNumber:"020-8838 6833", contactEmail:"info@lordsbm.co.uk")
		  sme8.url = "http://www.lordsbuildersmerchants.com/"
          sme8.description="We have a great understanding of the building, joinery and decorating trades, and our sales team are knowledgeable, friendly and efficient. We can source almost any item for our clients and give advice on the most suitable products to use. "
		  sme8.solutions="Much larger warehouse facilities, timber stocks and extended heavyside storage and parking facilities. Fast Service, accurate billing and fast delivery"
          sme8.addToContents(new ProfileData(metaTags: "Decorating, Blocks, Bricks, Paving, Plumbing", visibility: "PUBLIC"))
          sme8.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist8 = new ContactList(visibility: "PUBLIC")
          contactlist8.addToContacts(new Contact(sme: "A58818501", smeName: "Isango", state: Contact.WEAK, recommender:true))
          contactlist8.addToContacts(new Contact(sme: "A58818503", smeName: "GoLearnTo", state: Contact.WEAK, recommender:true ))
          contactlist8.addToContacts(new Contact(sme: "A58818504", smeName: "GoodToSee", state: Contact.WEAK, recommender:true ))
          contactlist8.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          contactlist8.addToContacts(new Contact(sme: "A58818512", smeName: "Namco Station", state: Contact.WEAK, recommender:true))
          sme8.addToContents(contactlist8)
          sme8.addToContents(new Feed(visibility: "PUBLIC"))
          sme8.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme8.addToContents(new MessageBox(visibility: "PUBLIC"))
          def blog8 = new Blog(visibility: "PUBLIC")
          blog8.addPost("John Farrow", "Update: Liebherr + Tons of Water + Car = Crushed Car…", "It appears the German show Galileo may be the ones behind the destroy a car with a leibherr’s bucket full of water.  The video on the next page for all who want to see them test an even smaller excavator with poor results. Since two machines went to Australia I wonder if they will be using the machine to clean their trucks too?...")
          blog8.addPost("Andrew Dearson", "Gradall XL4100 III Responds to Customers", "Responding to contemporary economic pressures, Gradall Industries, Inc., has designed greater operational savings as well as features that hold down the purchase price for its new Gradall XL 4100 III excavator. Now more than ever, this new Gradall excavator’s versatility delivers greater value with their ability to perform many jobs that used to require many different machines...")
          blog8.addPost("Peter Petrelli", "Cat’s new mining trucks 793F,797F, and 796F", "Caterpillar is moving rapidly forward with the new large mining trucks showcased at MINExpo last year and representing the company’s largest investment ever in new mining technology. The Cat® 793F and 797F mechanical drive trucks and the 795F AC electric drive truck are all achieving milestones.... ")
          blog8.addPost("John Farrow", "Footage of Construction Crane Collapse in New Dehli India", "Amazing footage from a crane collapse today in New Dehli India. The First crane has catastrophic structural failure and then the 2nd and third fall in. Very troubling. ...")
          blog8.addPost("Bruce Fox", "Joystick Steering Now Available on Case Wheel Loaders", "Case Construction Equipment has announced the availability of joystick steering on Case E Series wheel loaders, models 521E through 921E. With the joystick steering option, operators can expect more efficiency, increased productivity and less fatigue...")
          sme8.addToContents(blog8)
          sme8.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme8.save()) log.info("Failed to create Sme8. See error log...")
        }

        def sme9 = Sme.findByCif("A58818509")
        if (!sme9) {
          sme9 = new Sme(primaryUser: "12345678P", cif: "A58818509", name: "The Hireman", address: "53 Tanner Street", town: "London", sector: "Construction Equipment", country: "United Kingdom", numRecommendations: "1", urlLogo: "/avatars/thehireman.png", logoType: "jpeg", faxNumber: "9020 7407 7562",telephoneNumber: "020 7378 3050.",contactEmail:"enquiries@thehireman.co.uk. ")
          sme9.description="The Hireman represents the very best of Tool Hire in London. We offer unparallelled service and unbeatable value - guaranteed by our Price Promise!"
		  sme9.url="http://www.thehireman.co.uk"
          sme9.solutions="air tools, cleaning, dust extraction, cutting, painting, fixing, pumping"
          sme9.addToContents(new ProfileData(metaTags: "air tools, cleaning, dust extraction, cutting, painting, fixing, pumping", visibility: "PUBLIC"))
          sme9.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme9.addToContents(new Feed(visibility: "PUBLIC"))
          def feed9 = new Feed (visibility: "PUBLIC")
          feed9.addNotification("New blog post","Sme Isango has a new post in his blog")
          feed9.addNotification("New conexion between SMEs","Sme Isango and Sme Silver Saints has a new conexion")
          feed9.addNotification("New recommendation to SME","Sme Lords Builder Merchants has recommended SME Colebrook BS ")
          feed9.addNotification("The SME profile has changed","Sme Isango has changed his address")
          feed9.addNotification("New conexion between SMEs","Sme Namco Station and Sme Grape Entertainers has a new conexion")
          feed9.addNotification("New blog post","Sme Isango has a new post in his blog")
          feed9.addNotification("New recommendation to SME","Sme goodtosee has recommended SME Isango ")
          feed9.addNotification("New SME join to NEO","Sme Colebrook Ltd is a new member of Sme Communities. Primary User: Kate Watson ")
          feed9.addNotification("New secundary user","David Bennett is a new employee")
          sme9.addToContents(feed9)

          def message9 = new MessageBox(visibility:"PUBLIC")
          message9.addMessage("12345678P","12345661P","A58818509","Trip to Manchester","Hi Paul, I have the tickets to Manchester, there is a change in our plan because the meeting has been postponed. The new day of the trip will be on October 30th. Bye")
          message9.addMessage("12345678P","12345659P","A58818506","Change the address shop","Hello Paul, how are you? I would tell you that we have relocated our shop. The new address is 53 Tanner Street. I hope see you soon!")

          def blog = new Blog(visibility: "PUBLIC")
          blog.addPost("Paul Jones", "Update: Liebherr + Tons of Water + Car = Crushed Car…", "It appears the German show Galileo may be the ones behind the destroy a car with a leibherr’s bucket full of water.  The video on the next page for all who want to see them test an even smaller excavator with poor results. Since two machines went to Australia I wonder if they will be using the machine to clean their trucks too?...")
          blog.addPost("Paul Jones", "Gradall XL4100 III Responds to Customers", "Responding to contemporary economic pressures, Gradall Industries, Inc., has designed greater operational savings as well as features that hold down the purchase price for its new Gradall XL 4100 III excavator. Now more than ever, this new Gradall excavator’s versatility delivers greater value with their ability to perform many jobs that used to require many different machines...")
          sme9.addToContents(blog)
          def notice9 = new NoticeBoard(visibility: "PUBLIC")
          notice9.addNotice("A58818506","We are trying to find a SME of cleaning","I would like find a sme of cleaning for big surfaces to work during the next month, we need three employees and offer flextime. Telephone: 654888888")
          notice9.addNotice("A58818511","Cut of prices","Since today till 30th November we offer discounts in all products, We wait for you!!")
          notice9.addNotice("A58818508","Product called cenixil","We need a product called cenixil, but we don't find it. Help please")
          sme9.addToContents(notice9)
          def contactlist9 = new ContactList(visibility: "PUBLIC")
          contactlist9.addToContacts(new Contact(sme: "A58818501", smeName: "Isango", state: Contact.WEAK, recommender:false))
          contactlist9.addToContacts(new Contact(sme: "A58818502", smeName: "Travelzone", state: Contact.WEAK, recommender:false))
          contactlist9.addToContacts(new Contact(sme: "A58818508", smeName: "Lords Builder Merchants", state: Contact.WEAK, recommender:false))
          contactlist9.addToContacts(new Contact(sme: "A58818513", smeName: "Grape Entertainers", state: Contact.STRONG, recommender:true))
          contactlist9.addToContacts(new Contact(sme: "A58818510", smeName: "UK Platforms", state: Contact.WEAK, recommender:false))
          contactlist9.addToContacts(new Contact(sme: "A58818504", smeName: "GoodToSee", state: Contact.WEAK, recommender:false))
          contactlist9.addToContacts(new Contact(sme: "A58818512", smeName: "Namco Station", state: Contact.WEAK, recommender:false))
          sme9.addToContents(contactlist9)
          RecommendationBox recommendation9=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation9.addRecommendation("A58818507", "When we need any tool, any design, we always find it in Colebrook Bosson Saunders. They are amazing")
          recommendation9.addRecommendation("A58818513", "If you don't have any idea for a party, they are the solution: Grape Entertainment, in London")
          sme9.addToContents(recommendation9)
          message9.addMessage("12345678P","12345660P","A58818509","Construction Trade","""Hi Paul,\nHow was on the construction trade fair? Don't forget to tell me what painter is your choice for the new project. See you!!""")
          sme9.addToContents(message9)
          if (!sme9.save()) log.info("Failed to create Sme9. See error log...")
        }

        def sme10 = Sme.findByCif("A58818510")
        if (!sme10) {
          sme10 = new Sme(primaryUser: "12345695P", cif: "A58818510", name: "UK Platforms", address: "Unit 3, The Recovery Centre,Wakefield Road", town: "Barnsley", sector: "Construction Equipment", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/ukplatforms.png", logoType: "png", telephoneNumber: "01226 786 677", faxNumber: "01226 786 688", contactEmail:"barnsley@ukplatforms.co.uk")
          sme10.description="UK Platforms provide a full range of powered access equipment rental, 'cherry pickers' and aerial work platforms for hire, delivered direct to your site"
          sme10.solutions="""
Vertical Masts
Electrical Scissor Lifts
Electric Articulated Booms
Telehandlers
          """
		  sme10.url="www.ukplatforms.co.uk/"
          sme10.addToContents(new ProfileData(metaTags: "Plumbing, Vertical Masts, Electrical Scissor Lifts,Electric Articulated Booms , Telehandlers", visibility: "PUBLIC"))
          sme10.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist10 = new ContactList(visibility: "PUBLIC")
          contactlist10.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          sme10.addToContents(contactlist10)
          sme10.addToContents(new Feed(visibility: "PUBLIC"))
          sme10.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme10.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme10.addToContents(new Blog(visibility: "PUBLIC"))
          sme10.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme10.save()) log.info("Failed to create Sme10. See error log...")
        }

        def sme11 = Sme.findByCif("A58818511")
        if (!sme11) {
          sme11 = new Sme(primaryUser: "12345678P", cif: "A58818511", name: "Soft Play", address: "2c Holmefield", town: "Cheshire", sector: "Construction Equipment, Entertainment", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/softplay.png", logoType: "png", telephoneNumber: "161 973 0010", contactEmail:"mail@softplaysurfaces.com")
          sme11.description="At Soft Play Safety Surfaces, we work with our clients to provide an enjoyable yet ultimately safe environment for all, we specialise in the design and installation of playgrounds, sporting and leisure surfaces."
          sme11.solutions="Wet pour repair kits,Playground markings ,Safety artificial grass ,Golf walkways and pathways "
		  sme11.url="www.softplaysurfaces.com"
          sme11.addToContents(new ProfileData(metaTags: "Wet pour repair kits,Playground markings ,Safety artificial grass ,Golf walkways and pathways", visibility: "PUBLIC"))
          sme11.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme11.addToContents(new ContactList(visibility: "PUBLIC"))
          sme11.addToContents(new Feed(visibility: "PUBLIC"))
          sme11.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme11.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme11.addToContents(new Blog(visibility: "PUBLIC"))
          sme11.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          if (!sme11.save()) log.info("Failed to create Sme11. See error log...")
        }

        def sme12 = Sme.findByCif("A58818512")
        if (!sme12) {
          sme12 = new Sme(primaryUser: "12345678P", cif: "A58818512", name: "Namco Station", address: "County Hall,Riverside Buildings,Westminster Bridge Road", town: "London", sector: "Entertainment", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/namcostation.png", logoType: "png", telephoneNumber: "020-7967-1067", faxNumber:"020-7967-1060", contactEmail:"countyhall@namco.co.uk")
          sme12.description="We are a vibrant and cosmopolitan action packed venue of corporate entertainment, with over 150 of the latest interactive video games, a luxury American Pool Hall, twelve lanes of Techno Bowling and our famous Bumper Cars, all spread over 3 levels of pure enjoyment and big kid fun"
          sme12.solutions="Product launches,Staff evenings and parties,Client entertaining,Networking events,Graduate events"
          sme12.url="www.namcostation.co.uk"
          sme12.addToContents(new ProfileData(metaTags: "Product launches,Staff evenings and parties,Client entertaining,Networking events,Graduate events", visibility: "PUBLIC"))
          sme12.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          def contactlist12 = new ContactList(visibility: "PUBLIC")
          contactlist12.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:false))
          contactlist12.addToContacts(new Contact(sme: "A58818506", smeName: "Silver Saints", state: Contact.WEAK, recommender:false))
          contactlist12.addToContacts(new Contact(sme: "A58818508", smeName: "Lords", state: Contact.STRONG, recommender:false))
          sme12.addToContents(contactlist12)
          sme12.addToContents(new Feed(visibility: "PUBLIC"))
          sme12.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme12.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme12.addToContents(new Blog(visibility: "PUBLIC"))
          RecommendationBox recommendation12=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation12.addRecommendation("A58818508","We recommend it. Lords is the best company doing his job, it's true!!")
          sme12.addToContents(recommendation12)
          if (!sme12.save()) log.info("Failed to create Sme12. See error log...")
        }

        def sme13 = Sme.findByCif("A58818513")
        if (!sme13) {
          sme13 = new Sme(primaryUser: "12345678P", cif: "A58818513", name: "Grape Entertainers", address: "78 Middleton Road", town: "London", sector: "Entertainment", country: "United Kingdom", numRecommendations: "1", urlLogo: "/avatars/grape.png", logoType: "png", telephoneNumber: "0800 849 9067", contactEmail:"info@grapeentertainers.com")
          sme13.description="Want a children's entertainer? A band or a disco? Want to organise a Funday or a Corporate Event? Then take your 'Pick of the Bunch' with Grape Entertainers. All our entertainers are known to us personally and are the best in the industry. We only represent established and experienced artists that we can personally recommend."
		  sme13.solutions="Children's Parties,Fundays,Corporate Events,Christenings,Weddings,Barmitzvahs"
		  sme13.url="http://www.grapeentertainers.com/"
          sme13.addToContents(new ProfileData(metaTags: "Children's Parties,Fundays,Corporate Events,Christenings,Weddings,Barmitzvahs", visibility: "PUBLIC"))
          sme13.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme13.addToContents(new Feed(visibility: "PUBLIC"))
          sme13.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme13.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme13.addToContents(new Blog(visibility: "PUBLIC"))
          RecommendationBox recommendation13=new RecommendationBox(visibility:Content.PUBLIC)
          recommendation13.addRecommendation("A58818509", "Always found www.thehireman.co.uk the best value and the best prices. Repeat withm : The Hire-Man")
          recommendation13.addRecommendation("A58818506", "We purchased my water softener and whole house filter from them and have been very satisifed")
          sme13.addToContents(recommendation13)
          def contactlist13 = new ContactList(visibility: "PUBLIC")
          contactlist13.addToContacts(new Contact(sme: "A58818506", smeName: "Silver Saints", state: Contact.STRONG, recommender:false))
          contactlist13.addToContacts(new Contact(sme: "A58818509", smeName: "The Hireman", state: Contact.WEAK, recommender:true))
          sme13.addToContents(contactlist13)
          if (!sme13.save()) {
            log.info("Failed to create Sme13. See error log...")
            sme13.errors.each { log.error it }
          }
        }


        def sme14 = Sme.findByCif("A58818514")
        if (!sme14) {
          sme14 = new Sme(primaryUser: "12345678P", cif: "A58818514", name: "Bouncy Castles", address: "5 Mapplewell Business Park,Blacker Road", town: "Barnsley", sector: "Entertainment", country: "United Kingdom", numRecommendations: "0", urlLogo: "/avatars/bouncy.jpg", logoType: "jpeg", telephoneNumber: "024 7647 6872", contactEmail: "info@ethicseo.co.uk")
          sme14.description=" Our expert staff provide a wide range of Bouncy Castles, of all sizes, to clients for children’s entertainment.  We ensure that you have peace of mind that your service is fully compliant with health & safety, legal and other regulatory requirements.  We are recommended by our clients in Barnsley. "
          sme14.solutions="Early attendance at event to set-up,Constant supervision throughout duration of event,Fixed Prices"
		  sme14.url="www.bouncycastlesbarnsley.co.uk"
          sme14.addToContents(new ProfileData(metaTags: "bouncy castles", visibility: "PUBLIC"))
          sme14.addToContents(new OperatorInfo(visibility: "PUBLIC", title: "SME Communities advice", text: "Tomorrow evening will reboot out systems"))
          sme14.addToContents(new ContactList(visibility: "PUBLIC"))
          sme14.addToContents(new Feed(visibility:"PUBLIC"))
          sme14.addToContents(new NoticeBoard(visibility: "PUBLIC"))
          sme14.addToContents(new MessageBox(visibility: "PUBLIC"))
          sme14.addToContents(new RecommendationBox(visibility: "PUBLIC"))
          def blog14 = new Blog(visibility: "PUBLIC")
          blog14.addPost("Walt Williams", "No Passport Needed To Go To Paradise!", "Summer's end is creeping up on Road Trip Planners everywhere in the United States and some of them hanker for an exotic beach getaway before Summer gives way to Fall. But what if you don't already have a passport, which takes 6 weeks to arrive and past summer? Or someone in your group doesn't have theirs? Hawaii or the Florida Keys are good options but perhaps you're looking for a more remote vacation? .")
          blog14.addPost("John Locke", "The Grass IS Greener at the Olympic National Park", "There's no doubt that Road Trip Planners know that National Parks offer some of the best family summer vacations.  They also know that millions of other travelers are hitting the massively popular parks like Yellowstone and Yosemite during the summer.  So where do you go when you want to experience the great outdoors, be immersed in natural settings but not too far from the amenities that makes family travel easier and yet feel like you're away from the crowds? ")
          sme14.addToContents(blog14)
        if (!sme14.save()) log.info("Failed to create Sme14. See error log...")
        if (Membership.canAssociate(employee4, sme1, [email: "tom@isango.com"])) Membership.link(sme1, employee4, [email: "tom@isango.com"])
        if (Membership.canAssociate(employee6, sme1, [email: "robert@isango.com"])) Membership.link(sme1, employee6, [email: "robert@isango.com"])
        if (Membership.canAssociate(employee2, sme2, [email: "jack@travelzone.com"])) Membership.link(sme2, employee2, [email: "jack@travelzone.com"])
        if (Membership.canAssociate(employee1, sme3, [email: "paul@golearnto.com"])) Membership.link(sme3, employee1, [email: "paul@golearnto.com"])
        if (Membership.canAssociate(employee1, sme4, [email: "paul@goodtosee.com"])) Membership.link(sme4, employee1, [email: "paul@goodtosee.com"])
        if (Membership.canAssociate(employee1, sme5, [email: "paul@norwegian.com"])) Membership.link(sme5, employee1, [email: "paul@norwegian.com"])
        if (Membership.canAssociate(employee1, sme6, [email: "paul@silversaints.com"])) Membership.link(sme6, employee1, [email: "paul@silversaints.com"])
        if (Membership.canAssociate(employee3, sme7, [email: "kate@colebrook.com"])) Membership.link(sme7, employee3, [email: "kate@colebrook.com"])
        if (Membership.canAssociate(employee1, sme8, [email: "paul@lords.com"])) Membership.link(sme8, employee1, [email: "paul@lords.com"])
        if (Membership.canAssociate(employee1, sme9, [email: "paul@thehireman.com"])) Membership.link(sme9, employee1, [email: "paul@thehireman.com"])
        if (Membership.canAssociate(employee7, sme9, [email: "natalie@thehireman.com"])) Membership.link(sme9, employee7, [email: "natalie@thehireman.com"])
        if (Membership.canAssociate(employee3, sme10, [email: "kate@ukplatforms.com"])) Membership.link(sme10, employee3, [email: "kate@ukplatforms.com"])
        if (Membership.canAssociate(employee1, sme11, [email: "paul@softplay.com"])) Membership.link(sme11, employee1, [email: "paul@softplay.com"])
        if (Membership.canAssociate(employee8, sme9, [email: "rose@thehireman.com"])) Membership.link(sme9, employee8, [email: "rose@thehireman.com"])
        if (Membership.canAssociate(employee9, sme9, [email: "emily@thehireman.com"])) Membership.link(sme9, employee9, [email: "emily@thehireman.com"])
        if (Membership.canAssociate(employee1, sme12, [email: "paul@namco.com"])) Membership.link(sme12, employee1, [email: "paul@namco.com"])
        if (Membership.canAssociate(employee1, sme13, [email: "paul@grape.com"])) Membership.link(sme13, employee1, [email: "paul@grape.com"])
        if (Membership.canAssociate(employee1, sme14, [email: "paul@bouncycastles.com"])) Membership.link(sme14, employee1, [email: "paul@bouncycastles.com"])
        if (Membership.canAssociate(employee5, sme9, [email: "david@thehireman.com"])) Membership.link(sme9, employee5, [email: "david@thehireman.com"])         
        }
        break
    }
  }

  def destroy = {
    HibernateUtil.shutdown()
  }
}