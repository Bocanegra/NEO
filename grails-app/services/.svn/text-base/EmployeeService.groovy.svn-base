import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib
import org.springframework.context.*
import org.codehaus.groovy.grails.commons.*

class  EmployeeService implements ApplicationContextAware {

  boolean transactional = true
  ApplicationContext applicationContext
  def applicationTagLib = new ApplicationTagLib()
  def config = ConfigurationHolder.config
  def messageSource

  //Servicios utilizados
  def emailAndSmsService
  def smeService
  def invitationService

  def validate(String user, String pwd){
    def result=AppConstants.EMP_LOGIN_RESULT_OK
    def employee=null
    def sme=null
    try{
      def validUserName=true
      def c=Membership.createCriteria()
      def memberEmployees=c.list {
        'in'('email', [user])
      }
      if (memberEmployees.size()==1) {
        employee=memberEmployees[0].employee
        sme=memberEmployees[0].sme
        //Se comprueba que el mail corresponde de forma unívoca a un empleado
        memberEmployees.each{
          if(it.employee.nif!=employee.nif){
            log.error "More than one employee with the same email."
            validUserName=false
          }
        }
        result.data=sme
      } else if (memberEmployees.size()>1) {
        // Asociado a este mail tiene varias empresas
        employee=memberEmployees[0].employee
        sme=memberEmployees[0].sme
        result.data=[]
        memberEmployees.each {
          result.data+=it.sme
        }
      }
      if(validUserName){
        if(!employee){
          log.debug "Could not find Employee with id $user"
          result=AppConstants.EMP_LOGIN_RESULT_NOEXISTS
          result.args=[user]
        }else{
          if(!employee.checkPassword(pwd)){
            log.debug "Password incorrect for Employee id $user"
            result=AppConstants.EMP_LOGIN_RESULT_PASSWORD_FAIL
            result.args=[user]
          }else{
            employee.logged=true
            employee.lastLogin=new Date()
            if(!employee.save(flush:true)){
              employee.errors.each{
                log.warn(it)
              }
              result=AppConstants.EMP_LOGIN_RESULT_ERROR
              log.error "Error saving employee ${user}"
            }
            result.sme=sme
          }
        }
      }else{
        result=AppConstants.EMP_LOGIN_RESULT_INVALID
      }
    }catch(Exception e){
       log.error(e.message)
       result=AppConstants.EMP_LOGIN_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result))
      result.employee=employee
    result
  }

  def logout(String idEmployee){
    def result=AppConstants.EMP_LOGOUT_RESULT_OK
    def employee=null
    try{
      employee=Employee.getById(idEmployee)
      if(!employee){
        log.warn "Could not find Employee with id $idEmployee"
        result=AppConstants.EMP_LOGOUT_RESULT_NOEXISTS
      }else{
        employee.logged=false
        employee.lastLogout=new Date()
        employee.save()
        log.warn "Employee $idEmployee logged out."
      }
    }catch(Exception e){
      log.warn(e)
      result=AppConstants.EMP_LOGOUT_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result))
      result.employee=employee
    return result
  }

  def register(Map smeProfile, Map userProfile, Map invitation){
    def result=AppConstants.SME_CREATE_RESULT_OK
    def pu=null
    def sme=null
    try{
      //Se crea usuario si no esxiste.
      def newEmployee=false
      pu=Employee.getById(userProfile.nif)
      if(!pu){
        if(!userProfile.password){
          log.error "Password field needs a value"
          result=AppConstants.SME_CREATE_RESULT_ERROR_PU_DATA
        }else{
          if(!userProfile.password.equals(userProfile.confirmPassword)){
            log.error "Password and password confirmation are not equal"
            result=AppConstants.SME_CREATE_RESULT_ERROR_PU_DATA
          }else{
            newEmployee=true
            pu=new Employee(userProfile)
            pu.setPassword(userProfile.password)
            // Se almacena el avatar
            if (userProfile.avatarEmpl) {
              pu.avatarType=userProfile.avatarEmpl.getContentType()-"image/"
              pu.avatar=userProfile.avatarEmpl.getBytes()
              def basePath=applicationContext.getResource("avatars").getFile().toString()
              def urlAvatar=new File(basePath, userProfile.nif+"."+pu.avatarType)
              userProfile.avatarEmpl.transferTo(urlAvatar)
              pu.urlAvatar=urlAvatar
            }
            log.info "PrimaryUser not found, created with id ${userProfile.nif}"
          }
        }
      }else{
        //Ya existe el usuario. Se comprueba el password.
        if(!userProfile.password.equals(userProfile.confirmPassword)){
          log.error "Password and password confirmation are not equal"
          result=AppConstants.SME_CREATE_RESULT_ERROR_PU_DATA
        }else{
          if(!pu.checkPassword(userProfile.password)){
            log.error "Incorrect password"
            result=AppConstants.SME_CREATE_RESULT_ERROR_PU_DATA
          }else{
            //Se actualizaran los datos del usuario existente.
            log.info "The user exists."
          }
        }
      }
      //Usuario válido. Nuevo o antiguo.
      if(result.code==AppConstants.SME_CREATE_RESULT_OK.code){
        //Se crea la SME y se le asigna el usuario como primario.
        sme=smeService.create(smeProfile, pu)
        //Se establece la relación entre el usuario y la empresa.
          if(!sme.validate() || !sme.save()){
            sme.errors.allErrors.each {
              log.warn messageSource.getMessage(it, Locale.getDefault())
            }
            result=AppConstants.SME_CREATE_RESULT_ERROR_SME_DATA
          }else{
            if(!pu.validate() || !pu.save()){
              pu.errors.allErrors.each{
                log.warn messageSource.getMessage(it, Locale.getDefault())
              }
              result=AppConstants.SME_CREATE_RESULT_ERROR_PU_DATA
            }else{
              if(Membership.canAssociate(pu, sme, userProfile.contactData)){
                Membership.link(sme, pu, userProfile.contactData)
                //Una vez completado el registro, se elimina la invitación.
                invitationService.delete(invitation.code)
              }else{
                //No es posible crear la asociación. Se deshacen los cambios
                log.error "Imposible to associate user $pu.nif and sme $sme.cif"
                result=AppConstants.SME_CREATE_RESULT_ERROR_NOASSOCIATE
                if(newEmployee) pu.delete()
                sme.delete()
              }
            }
          }
      }
    }catch(Exception e){
      log.error(e.getMessage(), e)
      result=AppConstants.SME_CREATE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.employee=pu
      result.sme=sme
    }else{
      //Se retorna la invitación para volver al render
      result.invitation=invitation
    }
    result
  }

  def create(Map userProfile, String company, Map invitation){
    def result=AppConstants.SU_CREATE_RESULT_OK
    def sme=null
    def su=null
    try{
      //Se localiza la empresa a la que asignar el usuario.
      def newEmployee=false
      sme=Sme.getById(company)
      if(!sme){
        result=AppConstants.SU_CREATE_RESULT_NOSME
      }else{
        su=Employee.getById(userProfile.nif)
        if(!su){
          if(!userProfile.password){
            log.error "Password not found creating Secondary User"
            result=AppConstants.SU_CREATE_RESULT_ERROR_USER_DATA
          }else{
            if(!userProfile.password.equals(userProfile.confirmPassword)){
              log.error "Password and password confirmation are not equal"
              result=AppConstants.SU_CREATE_RESULT_ERROR_USER_DATA
            }else{
              newEmployee=true
              su=new Employee(userProfile)
              su.setPassword(userProfile.password)
              log.info "Secondary user not found. Created with id {$userProfile.nif}"
            }
          }
        }else{
          log.info "User with id ${userProfile.nif} already exists"
          if(!userProfile.password.equals(userProfile.confirmPassword)){
            log.error "Password and password confirmation are not equal"
            result=AppConstants.SU_CREATE_RESULT_ERROR_USER_DATA
          }else{
            if(!su.checkPassword(userProfile.password)){
              log.error "Incorrect password"
              result=AppConstants.SU_CREATE_RESULT_ERROR_USER_DATA
            }
          }
        }
      }
      if(result.code==AppConstants.SU_CREATE_RESULT_OK.code){
        //Actualizamos datos del SU y de la SME
        if(!sme.validate() || !sme.save()){
          sme.errors.allErrors.each{
            log.warn messageSource.getMessage(it, Locale.getDefault())
          }
          result=AppConstants.SU_CREATE_RESULT_ERROR_SME_DATA
        }else{
          if(!su.validate() || !su.save()){
            su.errors.allErrors.each{
              log.warn messageSource.getMessage(it, Locale.getDefault())
            }
            result=AppConstants.SU_CREATE_RESULT_ERROR_USER_DATA
          }else{
            if(Membership.canAssociate(su, sme, userProfile.contactData)){
              log.info "Employee and sme can associate"
              Membership.link(sme, su, userProfile.contactData)
              //Una vez completado el registro, se elimina la invitación.
              invitationService.delete(invitation.code)
              //Se notifica por mail/sms el registro al primary user de la empresa.
              def pu=Employee.getById(sme.primaryUser)
              if(pu?.mailAdvice){
                def urlBorrado=applicationTagLib.createLink(controller: 'employee', action: 'delete',
                                                            absolute: true, params: [user:su.nif, company:sme.cif ])
                String body="""
                    The user ${su.name} has been registered as a Secondary User in ${sme.name}.
                    If you want to remove it, click on ${urlBorrado}.

                    The NEO team.
                    """
                pu.assignContactData(sme)
                def puMail=pu.contactData.email
                if(emailAndSmsService.sendEmailSmtp(puMail, ConfigurationHolder.config.neo.email.from,
                                                    "NEO info: Secondary User registered", body)){
                  log.info("Primary User informed vía email to ${puMail}.")
                }else{
                  //No ha sido posible enviar el mail.
                  result=AppConstants.SU_CREATE_RESULT_NOADVICE
                }
              }
              if(pu?.smsAdvice){
                if(emailAndSmsService.sendSms(pu.contactData.cellularNumber, "The user ${su.name} has been registered as Secondary User in ${sme.name}")){
                  log.info("Primary User informed vía email.")
                }else{
                  result=AppConstants.SU_CREATE_RESULT_NOADVICE
                }
              }
            }else{
              //No es posible crear la asociación. Se deshacen los cambios
              log.error "Imposible to associate user $su.nif and sme $sme.cif"
              result=AppConstants.SU_CREATE_RESULT_ERROR_NOASSOCIATE
              if(newEmployee) su.delete()
            }
          }
        }
      }
    }catch(Exception e){
      log.warn(e)
      result=AppConstants.SU_CREATE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result) || result.code==AppConstants.SU_CREATE_RESULT_NOADVICE.code){
      su.assignContactData(sme)
      result.employee=su
      result.args=[sme.name]
      result.sme=sme
    }
    result
  }

  def delete(String user, String company){
    def result=AppConstants.EMP_DELETE_RESULT_OK
    def employee=null
    def sme=null
    try{
      //Se comprueba la existencia del usuario y la sme.
      employee=Employee.getById(user)
      if(!employee){
        log.error "Employee with id ${user} not found."
        result=AppConstants.EMP_DELETE_RESULT_NOUSER
      }else{
        sme=Sme.getById(company)
        if(!sme){
          log.error "Company with id ${company} not found."
          result=AppConstants.EMP_DELETE_RESULT_NOSME
        }else{
          def smesOfEmployee=employee.smes()
          def employeesOfSme=sme.employees()
          //Se comprueba si hay relación entre la empresa y el usuario
          if(smesOfEmployee.contains(sme)){
            def employeeMail=employee.assignContactData(sme).email

            //Tratamiento de la asociacion
            Membership.unlink(employee, sme)
            log.info "Association deleted"

            //Tratamiento de la empresa
            if(employeesOfSme.size()==1){
              log.info "Company with no more users."
              sme.delete()
              log.info "Company deleted."
            }else{
              log.info "Company with more users."
              if(sme.primaryUser.equals(employee.nif)){
                log.info "User was the primary User."
                def suToPromote=employeesOfSme.get(0) //todo:!!!Criterio de promoción de secundario.
                sme.setPU(suToPromote.nif)
                log.info "User ${suToPromote} promoted to Primary User"
              }else{
                log.info "Deleted a secondary user of a company with several users"
              }
//              if (!sme.save()) {
//                sme.errors.each{
//                  log.warn(it)
//                }
//                log.error "Error saving company ${sme.cif}"
//                result=AppConstants.EMP_DELETE_RESULT_ERROR
//              }
            }

            //Tratamiento del empleado
            if(smesOfEmployee.size()==1){
              log.info "User only registered in company ${company}"
              employee.delete()
              log.info "User deleted."
            }else{
              log.info "User registered in serveral companies"
              if(!employee.save()){
                  employee.errors.each{
                    log.warn(it)
                  }
                log.error "Error saving user ${employee.nif}"
                result=AppConstants.EMP_DELETE_RESULT_ERROR
              }
            }
            //Se informa al usuario del borrado vía mail.
            String body="""
                Hello ${employee.name}
                Your registration in NEO to Company ${sme.name} has been removed.
                Thanks for your contribution to the community.

                The NEO team.
                """
            if(!emailAndSmsService.sendEmailSmtp(employeeMail, ConfigurationHolder.config.neo.email.from,
                                                "User removed from NEO", body)){
              //No ha sido posible enviar el mail.
              result=AppConstants.EMP_DELETE_RESULT_NOADVICE
            }

          }else{
            log.error "There is no conexion between Company: ${company} and Employee ${user}"
            result=AppConstants.EMP_DELETE_RESULT_ERROR
          }
        }
      }
    }catch(Exception e){
      log.debug(e.getMessage())
      result=AppConstants.EMP_DELETE_RESULT_ERROR
    }
//    if(AppConstants.isResponseOk(result) || result.code==AppConstants.EMP_DELETE_RESULT_NOADVICE.code){
//      if(employee)
//      result.employee=employee
//      if(sme)
//        result.sme=sme
//    }
    result
  }

  //Modificación del profile de un usuario.
  def changeUserProfile(String company, String user, Map userProfile){
    def result=AppConstants.EMP_CHANGEPROFILE_RESULT_OK
    def employee=null
    def sme=null
    try{
       //Se comprueba la existencia del empleado
      employee=Employee.getById(user)
      sme=Sme.getById(company)
      if(!employee || !sme){
         log.warn "The company or the employee doesn't exist."
         result=AppConstants.EMP_CHANGEPROFILE_RESULT_ERROR
      }else{
        def membership=Membership.canChangeContactData(employee, sme, userProfile.contactData)
        if(membership){
          membership.setContactData(userProfile.contactData)
          employee.setProfile(userProfile)
          if(!employee.save()){
             employee.errors.each{
               log.warn(it)
             }
             result=AppConstants.EMP_CHANGEPROFILE_RESULT_ERROR
          }
          if(!membership.save()){
             membership.errors.each{
               log.warn(it)
             }
             result=AppConstants.EMP_CHANGEPROFILE_RESULT_ERROR
          }
        }else{
          log.error "Invalid contact data to set"
          result=AppConstants.EMP_CHANGEPROFILE_RESULT_INVALID
        }
      }
    }catch(Exception e){
       log.error(e.message)
       result=AppConstants.EMP_CHANGEPROFILE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      employee.assignContactData(sme)
      result.employee=employee
      result.sme=sme      
    }
    result
  }


}
