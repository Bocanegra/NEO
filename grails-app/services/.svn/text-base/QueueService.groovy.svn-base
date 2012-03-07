import org.springframework.orm.hibernate3.SessionFactoryUtils
import org.springframework.orm.hibernate3.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import org.hibernate.FlushMode

class QueueService {
  static final String NOTIFICATION="NOTIFICATION"
  static final String NOTICE="NOTICE"
  static final String OPERATORINFO="OPERATORINFO"

  boolean transactional = false
  static expose = ['jms']
  static destination = "neoQueue"

  //Cada vez que llega un mensaje a la cola es procesado.
  def onMessage={
    def sessionFactory=null
    def session=null
    try{
      sessionFactory=HibernateUtil.getSessionFactory()
      session = SessionFactoryUtils.getSession(sessionFactory, true)
      session.setFlushMode(FlushMode.MANUAL)
      TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session))
      //Se procesa el mensaje
      log.info "Message received in queue: $it"
      switch(it.type){
        case NOTIFICATION:
          log.info "Message for FEED"
          updateFeeds(it)
          break
        case NOTICE:
          log.info "Message for NOTICEBOARD"
          updateNoticeBoards(it)
          break
        case OPERATORINFO:
          log.info "Message for OPERATORINFO"
          updateOperatorInfos(it)
          break
        default:
          log.error "Message type unknown."
          break
      }
    }catch(Exception e){
      log.error(e.message)
    }finally{
      if(session!=null){
        SessionHolder sessionHolder=TransactionSynchronizationManager.unbindResource(sessionFactory)
        session.flush()
        SessionFactoryUtils.closeSession(sessionHolder.session)
//        SessionFactoryUtils.releaseSession(session, sessionFactory)
//        TransactionSynchronizationManager.unbindResourceIfPossible(sessionFactory)
      }
    }
  }

  //Obtiene los destinatarios de un mensaje
  def getAddressees(List addressees){
    def result=[]
    def sme=null
    addressees.each{
      sme=Sme.getById(it)
      if(!sme){
        log.warn("Could not find addressee: ${it}")
      }else{
        result.add(sme)
      }
    }
    result
  }

  //Procesa las notificaciones
  def updateFeeds(Map message){
    log.info "Updating feeds..."
    // Se identifican los destinatarios
//    def contacts=getAddressees(message.addressees)
//    log.debug contacts
    // Se apuntan las notificaciones
    Sme sme
    message.addressees.each {
      sme=Sme.getById(it)
      sme.returnFeed()?.addNotification(message.title, message.info)
      if (!sme.save()) {
        sme.errors.each{ error->
          log.warn(error)
        }
        log.error "Error saving feed for Sme: $sme"
      } else {
        log.info "Added feed notification for Sme: $sme"
      }
    }
  }

  //Procesa los anuncios entre empresas
  def updateNoticeBoards(Map message){
    log.info "Updating noticeboards..."
    //Se identifican los destinatarios
    def contacts=getAddressees(message.addressees)
    //Se env�an los anuncios
    contacts.each{
      it.returnNoticeBoard()?.addNotice(message.sender, message.title, message.info)
      if(!it.save(flush:true)){
        it.errors.each{ error->
          log.warn(error)
        }
        log.error "Error saving notice Sme: $it"
      }else{
        log.info "Added notice for Sme: $it"
      }
    }
  }

  //Procesa el anuncio del operador
  def updateOperatorInfos(Map message){
    log.info "Updating OperatorInfos..."
    //Se identifican los destinatarios
    def addressees
    if(message.addressees.size()!=0){
      addressees=getAddressees(message.addressees)
    }else{
      //Si no se indica una lista de empresas destinatarias, se env�a el anuncio a todas.
      addressees=Sme.findAll()
    }
    //Se env�an los anuncios
    log.info "Sending Operator info to ${addressees.size()} companies"
    addressees.each{
      it.returnOperatorInfo()?.setOperatorInfo(message.title, message.info)
      if(!it.save(flush:true)){
        it.errors.each{ error->
          log.warn(error)
        }
        log.error "Error saving OperatorInfo Sme: $it"
      }else{
        log.info "Added OperatorInfo for Sme: $it"
      }
    }
  }

}
