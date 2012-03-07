import grails.converters.*

class AdministratorController {

  // Se definen los servicios que vamos a utilizar en este controlador
  def administratorService
  def invitationService
  def smeService
  def employeeService

  def index={
    redirect(action:'list')
  }

  def list={
  }

  def login={
    //Carga views\administrator\login.gsp
  }

  def validate={
    Map response
    if(!params.user || !params.password){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response=administratorService.validate(params.user, params.password)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    if(AppConstants.isResponseOk(response)){
      //Si el login es correcto se apunta el usuario en la sesión y se retorna la lista de empresas.
      session.administrator=params.user
      withFormat{
        html { render(view:'list', model:response)}
        json { render response as JSON }
        xml { render response as XML }
      }
    }else{
      //Si el login no es correcto se vuelve a la pantalla de login
      flash.message=response.info
      withFormat{
        html { render(view:'login', model:response) }
        json { render response as JSON }
        xml { render response as XML }
      }
    }
  }

  def logout={
    session.invalidate()
    Map response
    if(!params.user){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response=administratorService.logout(params.user)
    }
    response.info=message(code:response.info, args:response.data)
    flash.message=response.info
    withFormat{
      html { render(view:'index', model:response)}
      json { render response as JSON }
      xml { render response as XML }
    }
  }

  def inviteSME={
    Map response
    if (!params.email || !params.smeName) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      def sender=params.sender?:InvitationService.INVITATION_ADMINISTRATOR
        response=invitationService.invite(sender, params.email, params.smeName, params.userName, (params.origen? params.origen:request.getHeader("User-Agent")))
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat {
      html { redirect(action:'list') }
      json { render response as JSON }
      xml { render response as XML }
    }
  }

  def setPrimaryUser={
    Map response
    if(!params.sme || !params.user){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response= smeService.setPrimaryUser(params.sme, params.user)
      response.info=message(code:response.info)
      flash.message=response.info
    }
    withFormat {
      json { render response as JSON }
      xml { render response as XML }
      html { render(view:'list', model:response) }
    }
  }

  def delete={
    Map response
    if(!params.user || !params.company){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response=employeeService.delete(params.user, params.company)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat{
      html { render(view:'list', model:response)}
      json { render response as JSON }
      xml { render response as XML}
    }
  }

  def publicizeOperatorInfo={
    Map response
    if(!params.user ||!params.title || !params.text ){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response=smeService.publicizeOperatorInfo(params.user, params.title, params.text, params.companies)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat{
      html { render(view:'list', model:response)}
      json { render response as JSON }
      xml { render response as XML}
    }
  }

}
