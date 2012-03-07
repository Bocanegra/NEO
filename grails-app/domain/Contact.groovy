class Contact implements Comparable {
  //Posibles estados de un contacto
  static final String INACTIVE = "INACTIVE"
  static final String WEAK = "WEAK"
  static final String STRONG = "STRONG"

  String sme
  String smeName
  String state
  boolean recommender
  Date dateCreated

  static belongsTo = [contactList: ContactList]

  static constraints = {
    sme(blank:false, size:1..100)
    state(blank:false, inList:[INACTIVE, WEAK, STRONG])
    recommender(blank:false)
  }

  static mapping = {
    table 'Contacts'
  }

  String toString() {
    "Contact $sme"
  }

  def changeState(String newState){
    state=newState
  }

  def setRecommender(){
    recommender=true
  }

  def delRecommender(){
    recommender=false
  }

  int compareTo(obj) {
    sme.compareToIgnoreCase(obj.sme)
  }

}
