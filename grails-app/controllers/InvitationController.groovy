import grails.converters.*

class InvitationController {

  //Se definen los servicios que utiliza el controlador.
  def invitationService

  def validate={
    Map response
    if(!params.email || !params.code){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response=invitationService.validate(params.code, params.email)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    if(AppConstants.isResponseOk(response)){
      //Invitación correcta. La empresa no existe => registro sme & pu
      withFormat{
        html { render(view:'employee/register', model:response)}
        xml { render response as XML }
        json { render response as JSON }
      }
    }else{
      if(response.code==AppConstants.INVITATION_VALIDATE_RESULT_SMEEXISTS.code){
        //Invitación correcta. La empresa ya ha sido registrada => registro de usuario secundario
        withFormat{
          html { render(view:'employee/registerSecondaryUser', model:response)}
          xml { render response as XML }
          json { render response as JSON }
        }
      }else{
        //Fraude
        withFormat{
          html { render(view:'error') }
          xml { render response as XML }
          json { render response as JSON }
        }
      }
    }
  }

}
