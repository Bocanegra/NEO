class Content {

  static final String NONE = "NONE"
  static final String PUBLIC = "PUBLIC"
  static final String CONTACTS = "CONTACTS"
  static final String PRIVATE = "PRIVATE"

  String visibility
  Date dateCreated
  Date lastUpdated

  static belongsTo=[sme:Sme]

  static constraints = {
    visibility(inList:[NONE, PUBLIC, CONTACTS, PRIVATE])
  }

  static mapping = {
    table 'Contents'
    tablePerHierarchy false
  }

}