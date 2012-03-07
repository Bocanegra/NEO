class MessageBox extends Content {

  static hasMany = [messages: Message]

  static mapping = {
    table 'MessageBoxes'
  }

  static constraints = {
  }

  String toString() {
    "MessageBox with ${messages?.size()} messages"
  }

  //A�ade un mensaje para un empleado de la empresa.
  def addMessage(String addressee, String senderUser, String senderSme, String title, String text){
    this.addToMessages(new Message(addressee:addressee, senderUser:senderUser, senderSme:senderSme, title:title, text:text))
    log.info "Message to $addressee added."
  }

  //Elimina un mensaje del buz�n de la empresa.
  boolean deleteMessage(String idMsg, String user){
    def result=true
    def message=Message.get(idMsg)
    if(!message || !message.addressee.equals(user)){
      log.info "Message to ${user} not found."
      result=false
    }else{
      this.removeFromMessages(message)
      message.delete()
      log.info "Message deleted."
    }
    result
  }

  def returnLastMessages(int nmax, int noffset) {
    Message.findAllByMessageBox(this, [max:nmax, offset:noffset, sort:'dateCreated', order:'desc'])
  }

  def returnMessagesFromLastLogout(Employee employee) {
    def result=[]
    Message.findAllByMessageBoxAndDateCreatedGreaterThan(this, employee.lastLogout).each {
      if (it.addressee.equals(employee.nif)) {
        result+=it
      }
    }
    result
  }

}
