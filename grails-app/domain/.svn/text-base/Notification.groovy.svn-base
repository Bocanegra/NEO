class Notification implements Comparable {

  static final String SMEPROFILECHANGED = "The SME profile has changed"
  static final String NEWCONEXION = "New conexion between SMEs"
  static final String DROPCONEXION = "Dropped conexion between SMEs"
  static final String NEWRECOMMENDATION = "New recommendation to SME"
  static final String NEWPOST = "New blog post"

  String title
  String text
  Date dateCreated

  static belongsTo = [feed: Feed]

  static constraints = {
    title(blank:false, size:1..100)
    text(nullable:true, size:1..1000)
  }

  static mapping = {
    table 'Notifications'
  }

  String toString() {
    "$title $text"
  }

  int compareTo(obj) {
    dateCreated.compareTo(obj.dateCreated)
  }

}
