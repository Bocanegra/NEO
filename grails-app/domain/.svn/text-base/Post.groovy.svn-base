class Post {

  String author
  String title
  String text
  Date dateCreated

  static belongsTo = [blog: Blog]

  static constraints = {
    author(blank:false, size:1..100)
    title(blank:false, size:1..100)
    text(nullable:true, size:1..1000)
  }

  static mapping = {
    table 'Posts'
  }

  static searchable = {
    title: spellCheck 'include'
    text: spellCheck 'include'
  }

  String toString(){
    "Post by $author: $title"
  }

  def modify(String title, String text){
    this.title=title
    this.text=text
  }
  
}
