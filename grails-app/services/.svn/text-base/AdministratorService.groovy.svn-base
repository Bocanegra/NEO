class AdministratorService {

  boolean transactional = true

  /**
   * Login del usuario: se actualiza la fecha del último login, y se apunta como logueado.
   * @param idAdmin pwdAdmin: ideintificador y password del usuario administrador.
   */
  def validate(String user, String pwdAdmin){
    def result=AppConstants.ADM_LOGIN_RESULT_OK
    def admin=null
    try{
      admin=Administrator.getById(user)
      if(!admin){
        log.debug "Could not find Administrator with id $user"
        result=AppConstants.ADM_LOGIN_RESULT_NOEXISTS
        result.args=["$user"]
      }else{
        if(!admin.checkPassword(pwdAdmin)){
        log.debug "Password incorrect for Administrator id $user"
        result=AppConstants.ADM_LOGIN_RESULT_PASSWORD_FAIL
        result.args=[user]
        }else{
          log.debug "Administrator ${user} logged in."
          admin.logged=true
        }
      }
    }catch(Exception e){
      log.warn("Exception in Administrator login", e)
      result=AppConstants.ADM_LOGIN_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.data=Sme.findAll()
      result.size=result.data.size()
      result.admin=admin
    }
    result
  }

  def logout(String user){
    def result=AppConstants.ADM_LOGOUT_RESULT_OK
    def admin=null
    try{
      admin=Administrator.getById(user)
      if(!admin){
        log.warn "Could not find Administrator with id $user"
        result=AppConstants.ADM_LOGOUT_RESULT_NOUSER
      }else{
        admin.logged=false
        admin.save()
        log.warn "Administrator $user logged out."
      }
    }catch(Exception e){
      log.warn(e)
      result=AppConstants.ADM_LOGOUT_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result))
      result.admin=admin
    result
  }

}
