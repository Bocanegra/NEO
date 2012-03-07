import org.apache.commons.mail.*

class EmailAndSmsService {

  boolean transactional = true

  /**
   * Envía un mail mediante el servidor SMTP de salida de TID
   */
  boolean sendEmailSmtp(String dirMail, String dirFrom, String title, String body) throws IOException {
    try {
      SimpleEmail email = new SimpleEmail()
      email.setHostName(ConfigurationHolder.config.neo.smtp.mail.server)
      email.addTo(dirMail)
      email.setFrom(dirFrom)
      email.setSubject(title)
      email.setMsg(body)
      email.send()
      return true
    } catch (Exception e) {
      log.warn("Error sending email: "+e.getMessage())
      log.debug("Stacktrace", e)
      return false
    }
  }

  def sendSms(String number, String message){
  true
  }

}
