class ContactList extends Content {

  static hasMany = [contacts: Contact]

  static mapping = {
    table 'ContactLists'
  }

  def returnFormatContactList(){
    def formatCL=[]
//    contacts.each { sme ->
//      formatCL.add([sme.name, sme.contactEmail, sme.sector])
//    }
    formatCL
  }

  String toString(){
    "ContactList with ${contacts?.size()} contacts"
  }

  // Añade una solicitud de contacto de la empresa peticionaria
  def addRequest(String petitioner, String petitionerName) {
    this.addToContacts(new Contact(sme:petitioner, smeName:petitionerName, state:Contact.INACTIVE, recommender:false))
    log.info "Request added."    
  }

  //Elimina la solicitud de contacto de la empresa sme.
  def deleteRequest(String petitioner){
    def contact=null
    contacts.each{
      if(it.sme.equals(petitioner)){
        contact=it
      }
    }
    if(!contact){
      log.info "Contact ${petitioner} not found."
    }else{
      this.removeFromContacts(contact)
      contact.delete()
      log.info "Request deleted."
    }

  }

  //Comprueba si existe la solicitud de un contacto en la contactlist
  boolean hasRequest(String sme){
    def result=false
    def contact=null
    contacts.each{
      if(it.sme.equals(sme) && it.state==Contact.INACTIVE){
        contact=it
      }
    }
    if(contact){
      result=true
    }
    result
  }

  //A�ade un contacto nuevo a la lista como d�bil, o bien modifica el estado de la conexi�n de inactivo a d�bil.
  def addContact(String petitioner, String name){
    def contact=null
    contacts.each{
      if(it.sme.equals(petitioner)){
        contact=it
      }
    }
    if(!contact){
      log.info "A new WEAK conexion is created with sme ${petitioner}."
      this.addToContacts(new Contact(sme:petitioner, smeName:name, state:Contact.WEAK, recommender:false))
    }else{
      log.info "The conexion with sme ${petitioner} is now WEAK."
      contact.changeState(Contact.WEAK)
    }
  }

  //Borra un contacto de la lista.
  def deleteContact(String sme){
    def contact=null
    contacts.each{
      if(it.sme.equals(sme)){
        contact=it
      }
    }
    if(!contact){
      log.info "Contact ${sme} not found."
    }else{
      this.removeFromContacts(contact)
      contact.delete()
      log.info "Contact deleted ${sme}"
    }
  }

  //Comprueba si existe un contacto en la contactlist
  boolean isContact(String sme){
    def result=false
    def contact=null
    contacts.each{
      if(it.sme.equals(sme) && (it.state==Contact.WEAK ||it.state==Contact.STRONG )){
        contact=it
      }
    }
    if(contact){
      result=true
    }
    result
  }


  //Marca un contacto como recomendador.
  def addRecommender(String sme){
    def contact=null
    contacts.each{
      if(it.sme.equals(sme)){
        contact=it
      }
    }
    if(!contact){
      log.info "Contact ${sme} not found."
    }else{
      contact.setRecommender()
      log.info "${sme} is now recommender."
    }
  }

  //Elimina la marca de un contacto como recomendador.
  def delRecommender(String sme){
    def contact=null
    contacts.each{
      if(it.sme.equals(sme)){
        contact=it
      }
    }
    if(!contact){
      log.info "Contact ${sme} not found."
    }else{
      contact.delRecommender()
      log.info "${sme} is no recommender."
    }
  }

  //Pone una conexi�n en estado STRONG
  def setStrong(String sme){
    def contact=null
    contacts.each{
      if(it.sme.equals(sme)){
        contact=it
      }
    }
    if(!contact){
      log.info "Contact ${sme} not found."
    }else{
      contact.changeState(Contact.STRONG)
      log.info "The conexion with ${sme} is now STRONG."
    }
  }

  // Cambia una conexión a estado WEAK
  def setWeak(String sme) {
    def contact
    contacts.each {
      if (it.sme.equals(sme)) {
        contact=it
      }
    }
    if (!contact) {
      log.info "Contact ${sme} not found."
    } else {
      contact.changeState(Contact.WEAK)
      log.info "The conexion with ${sme} is now WEAK."
    }
  }

  def getInactiveContacts() {
    contacts.findAll {
      it.state.equals(Contact.INACTIVE)
    }
  }

  def getWeakContacts() {
    contacts.findAll {
      it.state.equals(Contact.WEAK)
    }.sort()
  }

  def getStrongContacts() {
    contacts.findAll {
      it.state.equals(Contact.STRONG)
    }.sort()
  }

  def returnLastContacts(int nmax, int noffset) {
    Contact.findAllByContactList(this, [max:nmax, offset:noffset, sort:'dateCreated', order:'desc'])
  }

  def returnContactsFromLastLogout(Date logout) {
    Contact.findAllByContactListAndDateCreatedGreaterThan(this, logout)
  }

}