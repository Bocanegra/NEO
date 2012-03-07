import org.codehaus.groovy.grails.commons.*
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class InvitationService {

  boolean transactional = true
  // Otros servicios necesarios
  def emailAndSmsService
  def applicationTagLib=new ApplicationTagLib()

  // Constantes
  public static final String INVITATION_ADMINISTRATOR="NEO Administrator"

  /**
   * Método para generar una invitación con su hash único, y se envía un mail que contiene
   * una url de acceso al registro de empresa y usuario primario.
   */
  def invite(String sender, String email, String smeName, String userName, String userAgent){
    def result=AppConstants.INVITATION_INVITE_RESULT_OK
    def invitation=null
    try{
      //Se crea el hash
      def hash=Long.toHexString((new Random()).nextLong()).toString()
      //Se crea la invitación
      invitation=new Invitation(sender:sender, addresseeMail:email, addresseeSMEName:smeName, code:hash)
      // Se envía el mail con la invitación
      def urlInvitation
      if (userAgent && userAgent =~ "NEOWEB") {
        urlInvitation="${ConfigurationHolder.config.neo.invitation.url.flex}?action=validate&email=${email.encodeAsURL()}&code=${hash.encodeAsURL()}"
      } else {
        urlInvitation=applicationTagLib.createLink(controller: 'invitation', action: 'validate',
                                                   absolute: true, params: [email:email, code:hash])
      }
      String body="""
      Hello ${userName?:""}
      You have been invited to join NEO, a new social network for SMEs.
      Click here to start: ${urlInvitation}

      Thanks in advance...
      """
      //Guardamos la invitación en BD y enviamos mail
      if(!invitation.save()){
          invitation.errors.each{
            log.warn(it)
          }
          //No ha sido posible guardar en BD
          result=AppConstants.INVITATION_INVITE_RESULT_ERROR
      }else{
        def resultEmail=emailAndSmsService.sendEmailSmtp(email, ConfigurationHolder.config.neo.email.from,
                                                         "Neo invitation", body)
        if(resultEmail){
          log.info("Invitation with $hash created and email sent")
        }else{
          //No ha sido posible enviar el mail.
          delete(invitation.code)
          result=AppConstants.INVITATION_INVITE_RESULT_NOMAIL
        }
      }
    }catch(Exception e){
       log.error(e.message)
       result=AppConstants.INVITATION_VALIDATE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.invitation=invitation
    }
    result
  }

  /**
   * Método para validar una invitación, se comprueba que el code de dicha invitación está almacenado para ese usuario.
   * Hay que comprobar si la empresa existe ya, en cuyo caso no podrá volver a darse de alta.
   */
  def validate(String code, String email){
    def result=AppConstants.INVITATION_VALIDATE_RESULT_OK
    def invitation=null
    def sme=null
    try{
      //Se comprueba si la invitación existe.
      invitation=Invitation.findWhere(code:code, addresseeMail:email)
      if(!invitation){
        log.error "Could not find Invitation for code $code and user $email"
        result=AppConstants.INVITATION_VALIDATE_RESULT_FRAUD
      }else{
        //Se comprueba si la empresa a dar de alta ya existe.
        def smeName=invitation.addresseeSMEName
        sme=Sme.findByNameOrCif(smeName, smeName)
        if(sme){
          log.warn "The company $sme.cif has been registered previously"
          result=AppConstants.INVITATION_VALIDATE_RESULT_SMEEXISTS
          result.sme=sme.getPublicProfile()
          result.invitation=invitation
        }else{
          log.debug "Invitation with code $code correctly validated"
        }
      }
    }catch(Exception e){
       log.error(e.message)
       result=AppConstants.INVITATION_VALIDATE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.invitation=invitation //Si se completa el registro se borrará la invitación
      result.sme=sme
    }
    result
  }

  /**
   * Elimina una invitación de la base de datos.
   * @param code El código hash de la invitación
   */
  def delete(String code){
    def result=AppConstants.INVITATION_DELETE_RESULT_OK
    def invitation=null
    try{
      invitation=Invitation.findByCode(code)
      //Se comprueba si la invitación existe
      if(!invitation){
        log.debug "Unable to delete Invitation with code $code. The invitation doesn't exist."
      }else{
        invitation.delete()
        log.debug "Invitation deleted."
      }
    }catch(Exception e){
       log.error(e.message)
       result=AppConstants.INVITATION_DELETE_RESULT_ERROR
    }
    result
  }
}
