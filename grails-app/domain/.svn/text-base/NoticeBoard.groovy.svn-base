class NoticeBoard extends Content {

  static hasMany = [notices: Notice]

  static mapping = {
    table 'NoticeBoards'
  }

  static constraints = {
  }

  String toString() {
    "NoticeBoard with ${notices?.size()} notices"
  }

  //A�ade un anuncio al talb�n de la empresa.
  def addNotice(String senderSme, String title, String text){
    this.addToNotices(new Notice(sender:senderSme, title:title, text:text))
    log.info "Notice added."
  }


  //Elimina un anuncio del tabl�n de la empresa.
  boolean deleteNotice(String idNotice){
    def result=true
    def notice=Notice.get(idNotice)
    if(!notice || !notices.contains(notice)){
      log.info "Notice not found."
      result=false
    }else{
      this.removeFromNotices(notice)
      notice.delete()
      log.info "Notice deleted."
    }
    result
  }

  def returnLastNotices(int nmax, int noffset) {
    Notice.findAllByNoticeBoard(this, [max:nmax, offset:noffset, sort:'dateCreated', order:'desc'])
  }

  def returnNoticesFromLastLogout(Date logout) {
    Notice.findAllByNoticeBoardAndDateCreatedGreaterThan(this, logout)
  }

}
