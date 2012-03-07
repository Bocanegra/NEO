class Sme {

  String cif
  String name
  String address
  String town
  String state
  String country
  String zipCode
  String url
  String telephoneNumber
  String cellularNumber
  String faxNumber
  String contactEmail
  String sector
  String primaryUser
  Integer numRecommendations=0
  byte[] logo
  String urlLogo
  String logoType
  String description
  String solutions
  String products
  Date dateCreated
  Date lastUpdated

  static searchable = {
    numRecommendations index: 'not_analyzed'
    [only: ['cif', 'name', 'town' , 'state', 'sector']]
    sector: spellCheck 'include'
    town: spellCheck 'include'
  }

  static mapping = {
    table 'Smes'
  }
  
  static hasMany = [contents: Content, memberships:Membership]

  static constraints = {
    cif(blank: false, unique: true, size: 1..20)
    name(blank: false, size: 1..100)
    address(blank: false, size: 1..150)
    town(blank: false, size: 1..100)
    state(nullable: true, size: 1..100)
    country(blank: false, size: 1..100)
    zipCode(nullable: true, size: 1..10)
    url(nullable: true, size: 1..150)
    telephoneNumber(nullable: true)
    cellularNumber(nullable: true)
    faxNumber(nullable: true)
    contactEmail(nullable: true) 
    sector(blank: false)
    logo(nullable: true, maxSize: 65535)
    urlLogo(nullable: true)
    logoType(nullable:true)
    description(nullable:true, maxSize: 2048)
    solutions(nullable:true, maxSize: 2048)
    products(nullable:true, maxSize: 2048)
  }

  String toString(){
    "$name"
  }

  def employees(){
      return memberships.collect{it.employee}
  }

  static def getById(String idSme){
    Sme.findByCif(idSme)
  }

  def setPU(String employee){
    this.primaryUser=employee
  }

  ContactList returnContactList(){
    ContactList.findBySme(this)
  }

  Feed returnFeed(){
    Feed.findBySme(this)
  }

  ProfileData returnProfileData(){
    ProfileData.findBySme(this)
  }

  RecommendationBox returnRecommendationBox(){
    RecommendationBox.findBySme(this)
  }

  OperatorInfo returnOperatorInfo(){
    OperatorInfo.findBySme(this)
  }

  NoticeBoard returnNoticeBoard(){
    NoticeBoard.findBySme(this)
  }

  SearchEngine returnSearchEngine(){
    SearchEngine.findBySme(this)
  }

  MessageBox returnMessageBox(){
    MessageBox.findBySme(this)
  }

  Blog returnBlog(){
    Blog.findBySme(this)
  }

  def addVote(){
    numRecommendations++
  }

  def delVote(){
    numRecommendations--
  }


  def getPublicProfile(){
    def publicProfile=[cif:"${this.cif}",
                      name:"${this.name}",
                      address:"${this.address}",
                      town:"${this.town}",
                      state:"${this.state}",
                      country:"${this.country}",
                      zipCode:"${this.zipCode}",
                      url:"${this.url}",
                      telephoneNumber:"${this.telephoneNumber}",
                      cellularNumber:"${this.cellularNumber}",
                      faxNumber:"${this.faxNumber}",
                      contactEmail:"${this.contactEmail}",
                      sector:"${this.sector}",
                      ]
    def contents=[]
    this.contents.each{
      if(it.visibility==Content.PUBLIC){
        contents.add(it)
      }
    }
    publicProfile.contents=contents
    publicProfile
  }

  def setProfile(Map smeBasicProfile, Map smeAdvancedProfile, Map smeContentProfile) {
    if (smeBasicProfile) {
      this.properties=smeBasicProfile
      log.info "Basic profile assigned"
    }
    if (smeAdvancedProfile) {
      this.returnProfileData()?.properties=smeAdvancedProfile
      log.info "Advanced profile assigned"
    }
    if (smeContentProfile) {
      this.returnFeed()?.visibility=smeContentProfile.feed
      this.returnContactList()?.visibility=smeContentProfile.contactList
      this.returnProfileData()?.visibility=smeContentProfile.profileData
      this.returnRecommendationBox()?.visibility=smeContentProfile.recommendationBox
      this.returnOperatorInfo()?.visibility=smeContentProfile.operatorInfo
      this.returnNoticeBoard()?.visibility=smeContentProfile.noticeBoard
      this.returnSearchEngine()?.visibility=smeContentProfile.searchEngine
      this.returnMessageBox()?.visibility=smeContentProfile.messageBox
      this.returnBlog()?.visibility=smeContentProfile.blog
      log.info "Content profile assigned"
    }
  }

  String returnStringContacts() {
    returnContactList().contacts.size()==1?"1 contact":returnContactList().contacts.size()+" contacts"
  }

  def returnInfoRecommendations(){
    def result=[:]
    def recommenders=[]
    //Se retornan los identificadores de las empresas que recomendadoras
    this.returnContactList().contacts.each{
      if(it.recommender){
        recommenders+=it.sme
      }
    }
    //Se recupera el contenido de las recomendaciones a la empresa actual en la RB de cada recomendadora
    recommenders.each { cifRecommender ->
      Sme smeRecommender=Sme.getById(cifRecommender)
      smeRecommender.returnRecommendationBox().recommendations.each { recommendation ->
        if (this.cif.equals(recommendation.recommendedSme)){
          result.put(cifRecommender, recommendation)
        }
      }
    }
    result
  }

}