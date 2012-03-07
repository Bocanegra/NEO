import static cr.co.arquetipos.password.PasswordTools.*

class Employee {

  String name
  String surname
  String nif
  String position
  String password
  String passwordHash
  Map contactData=[:]
  boolean smsAdvice=false
  boolean mailAdvice=true
  boolean clientO2=true
  boolean logged=false
  Date lastLogin=new Date()
  Date lastLogout=new Date()
  byte[] avatar
  String urlAvatar
  String avatarType
  Date dateCreated
  Date lastUpdated

  static hasMany=[memberships:Membership]
  static belongsTo=Sme
  static transients=['password', 'contactData']

  static constraints = {
    name(blank:false, size:1..30)
    surname(blank:false, size:1..40)
    nif(blank:false, unique:true, size:1..20)
    position(nullable:true)
    password(blank:false, size:1..40)
    avatar(nullable:true, maxSize: 65535)
    urlAvatar(nullable:true)
    avatarType(nullable:true)
  }

  static mapping = {
    table 'Employees'
  }

  String toString() {
    "$name (nif: $nif)"
  }

  def smes(){
		return memberships.collect{it.sme}
	}

  boolean checkPassword(String password) {
    checkDigestHex(password, passwordHash)
  }

  void setPassword(String password) {
    passwordHash=saltPasswordHex(password)
  }

  static def getById(String idEmployee) {
    if (idEmployee) Employee.findByNif(idEmployee)
  }

  def setProfile(Map userProfile){
    this.properties=userProfile
  }

  def assignContactData(Sme sme){
    def membership=Membership.findByEmployeeAndSme(this, sme)
    if(membership){
      this.contactData=membership.getContactData()
      log.info "ContactData: ${contactData}"
    }
    this.contactData
  }

}