class OperatorInfo extends Content {

  String title
  String text
  Date dateCreated

  static constraints = {
    title(nullable:true, size:1..100)
    text(nullable:true, size:1..1000)
  }

  static mapping = {
    table 'OperatorInfos'
  }

  String toString() {
    "OperatorInfo $title"
  }

  //Publica la informaci�n del operador
  def setOperatorInfo(String title, String text){
    this.title=title
    this.text=text
    log.info "OperatorInfo publicized"
  }

}
