class Message {

  String senderUser
  String senderSme
  String addressee
  String title
  String text
  Date dateCreated

  static belongsTo = [messageBox: MessageBox]

  static constraints = {
    senderUser(blank:false, size:1..100)
    senderSme(blank:false, size:1..100)
    addressee(blank:false, size:1..100)
    title(nullable:true, size:1..100)
    text(nullable:true, size:1..1000)
  }

  static mapping = {
    table 'Messages'
  }

  static searchable = {
    title: spellCheck 'include'
    text: spellCheck 'include'
    senderUser: spellCheck 'include'
    senderSme: spellCheck 'include'
    dateCreated: spellCheck 'include'
  }

  String toString() {
    "$title: $text"
  }

}
