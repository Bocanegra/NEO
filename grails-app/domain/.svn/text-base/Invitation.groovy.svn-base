class Invitation {

  String sender
  String addresseeMail
  String addresseeSMEName
  String code
  Date dateCreated
  Date lastUpdated

  static constraints={
    sender(blank: false)
    addresseeMail(blank: false, email: true)
    code(blank:false)
    addresseeSMEName(nullable: true)
  }

  static mapping={
    table 'Invitations'
  }

  String toString(){
    "$sender -> $addresseeMail from $addresseeSMEName ($code)"
  }

  String toXML(){

  }

}