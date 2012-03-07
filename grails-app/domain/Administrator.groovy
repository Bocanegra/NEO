import static cr.co.arquetipos.password.PasswordTools.*

class Administrator {

  String user
  String password
  String passwordHash
  String email
  boolean logged=false
  Date dateCreated
  Date lastUpdated

  static hasMany = [smes:Sme]
  static transients=['password']

  static constraints = {
    user(blank: false, unique: true, size: 5..20)
    password(nullable: true, size: 5..20)
    passwordHash(blank: false)
    email(blank:false, email:true, unique:false, size:1..100)
  }

  static mapping = {
    table 'Administrators'
  }

  String toString() {
    "Administrator -> $user"
  }

  boolean checkPassword(String password){
    checkDigestHex(password, passwordHash)
  }

  void assignPassword(String password){
    passwordHash=saltPasswordHex(password)
  }

  static def getById(String user) {
    Administrator.findByUser(user)
  }

}