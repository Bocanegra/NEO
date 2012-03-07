class Feed extends Content {

  static hasMany = [notifications: Notification]

  static mapping = {
    table 'Feeds'
  }

  def returnFormatFeed() {
    def formatFeed = []
    notifications.each {notification ->
      formatFeed.add([notification.title, notification.text, notification.dateCreated])
    }
    formatFeed
  }

  static constraints = {
  }

  String toString() {
    "Feed with ${notifications?.size()} notifications"
  }

  def addNotification(String title, String text){
    this.addToNotifications(new Notification(title:title, text:text))
    log.info "Notification added."
  }


  //Elimina una notificación del feed de la empresa
  def deleteNotification(String idNotif){
    def result=true
    def notification=Notification.get(idNotif)
    if(!notification || !this.notifications.contains(notification)){
      log.info "Notification not found."
      result=false
    }else{
      this.removeFromNotifications(notification)
      notification.delete()
      log.info "Notification deleted."
    }
    result
  }

  def returnLastNotifications(String nmax, String noffset) {
    Notification.findAllByFeed(this, [max:Integer.parseInt(nmax), offset:Integer.parseInt(noffset), sort:'dateCreated', order:'desc'])
  }

  def returnNotifsFromLastLogout(Date logout) {
    Notification.findAllByFeedAndDateCreatedGreaterThan(this, logout)
  }

}
