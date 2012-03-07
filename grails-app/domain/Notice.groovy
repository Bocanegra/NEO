class Notice implements Comparable {

  String sender
  String title
  String text
  Date dateCreated

  static belongsTo = [noticeBoard: NoticeBoard]

  static constraints={
    sender(blank:false,size:1..100)
    title(blank:false, size:1..100)
    text(nullable:true, size:1..1000)
  }

  static mapping={
    table 'Notices'
    sort 'dateCreated':'desc'
  }

  String toString(){
    "From $sender: $title"
  }

  int compareTo(obj) {
    dateCreated.compareTo(obj.dateCreated)
  }

}
