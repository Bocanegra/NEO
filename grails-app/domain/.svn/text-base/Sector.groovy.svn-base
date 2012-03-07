class Sector {

  String name
  String feeds

  static constraints = {
    name(blank: false, size: 1..100)
    feeds(nullable: true, maxSize: 4096)
  }

  static mapping = {
    table 'Sectors'
  }

  String toString(){
    "Sector: $name"
  }

  def addFeed(String feed) {
    if (!feeds) {
      feeds=feed
    } else {
      feeds+="\n"+feed
    }
  }

}
