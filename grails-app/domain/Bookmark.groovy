class Bookmark {

  String url
  Date dateCreated

  static belongsTo = [seachEngine: SearchEngine]

  static constraints = {
    url(blank:false, size:1..300)
  }

  static mapping = {
    table 'Bookmarks'
  }

  String toString() {
    "Bookmark $url"
  }

}
