import grails.converters.*

class EmployeeController {

  // Se definen los servicios que vamos a utilizar en este controlador
  def employeeService
  def invitationService
  def smeService

  def index={

    if (session.membership) {
      redirect(action:'renderContent')
    } else {
      redirect(action:'login')
    }
  }

  def list={
  }

  def login={
    session.setMaxInactiveInterval(2*60*60)
    if (request.fromIPhone) {
      render(view:'/iphone3/login')
    } else {
      withFormat {
        // Si se llega aquí desde Flex, es porque se ha caducado la sesión
        xml { render(contentType: "text/xml", view: '/result.xml', model:AppConstants.GLOBAL_ERROR_COOKIE) }
        html { render(view:'login') }
      }
    }
  }

  def validate={
    session.setMaxInactiveInterval(2*60*60)
    Map response
    if (!params.user || !params.password) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      response=employeeService.validate(params.user, params.password)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    if (AppConstants.isResponseOk(response)) {
      // Si el login es correcto se registra usuario en la sesión
      session.employeeId=response.employee.nif
      // Se almacena el membership
      def membership=Membership.findByEmployeeAndSme(response.employee, response.sme)
      if (membership) {
        session.membership=membership
      } else {
        // El render es correcto, pero no hay relación entre ellos, un error extraño, algo raro hay
        log.fatal "Error searching Membership with ${response.employee} and ${response.sme}"
      }
      def sme=response.sme
      withFormat {
        html { redirect(action:'renderContent', params:[sme:sme.cif, employee:response.employee.nif, smeToRender:sme.cif]) }
        json { render response as JSON }
//        xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:sme, employee:response.employee]) }
        xml { render(contentType: "text/xml", view: 'list.xml', model: response) }
        iphone { redirect(action:'renderContent.iphone', params:[sme:sme.cif, employee:response.employee.nif, smeToRender:sme.cif]) }
      }
    } else {
      //Si se ha producido un error en la validación se vuelve a la página de login
      withFormat {
        html { redirect(action:'login') }
        json { render response as JSON }
        xml { render(contentType: "text/xml", view: '/result.xml', model: response) }
        iphone {
          render (text:"""
            <root>
              <destination mode="replace" zone="form-res" />
              <data><![CDATA[ <div class="err">${response.info}</div> ]]></data>
            </root>
          """, contentType:"text/xml")
        }
      }
    }
  }

  def renderContent = {
    Map response
    if (session.membership) {
      // Si existe la cookie de sesión, puede venir un parámetro para mostrar una sme distinta a la nuestra
      if (params.smeToRender) {
        response=smeService.renderContent(session.membership.sme.cif, session.membership.employee.nif, params.smeToRender)
      } else {
        response=smeService.renderContent(session.membership.sme.cif, session.membership.employee.nif, session.membership.sme.cif)
      }
    } else {
      // Si no existe la cookie, tenemos que venir de la selección de SME a mostrar (employee/list.gsp), y vienen parámetros
      if (!params.sme || !params.employee || !params.smeToRender) {
        response=AppConstants.GLOBAL_ERROR_PARAMS
      } else {
        // Comprobación adicional, que el parámetro 'employee' recibido corresponda con la cookie employeeId
        if (params.employee!=session.employeeId) {
          response=AppConstants.GLOBAL_ERROR_COOKIE
        } else {
          response=smeService.renderContent(params.sme, params.employee, params.smeToRender)
        }
      }
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    if (AppConstants.isResponseOk(response)) {
      // Si el render es correcto, se almacena en la sesión la relación SME-Employee (la clase Membership)
      def membership=Membership.findByEmployeeAndSme(session.membership.employee, session.membership.sme)
      if (membership) {
        session.membership=membership
      } else {
        // El render es correcto, pero no hay relación entre ellos, un error extraño, algo raro hay
        log.fatal "Error searching Membership with ${response.employee} and ${response.sme}"
      }
    }
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response) }
      iphone { render(contentType:"text/xml", view:'/iphone2/show', model:response) }
    }
  }

  def register = {
    Map response
    if (!params.smeProfile || !params.userProfile || !params.invitation) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      // Se recogen los ficheros subidos (logos y demás)
      def logoSme=request.getFile('logo_sme')
      def avatarEmpl=request.getFile('avatar_emp')
      if (logoSme.size<=0) {
        logoSme=null
      }
      if (avatarEmpl.size<=0) {
        avatarEmpl=null
      }
      response=employeeService.register(params.smeProfile+[logoSme:logoSme],
                                        params.userProfile+[avatarEmpl:avatarEmpl], params.invitation)
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    if(AppConstants.isResponseOk(response)){
      //El registro ha sido correcto.
      flash.message=response.info
      withFormat{
        html { redirect(action:'index') }
        json { render response as JSON }
        xml { render response as XML }
      }
    }else{
      //Si hubo algún fallo en el registro se muestra el error y se permite su corrección.
      withFormat{
        html { render(view:'register', model:response)}
        json { render response as JSON }
        xml { render response as XML }
      }
    }
  }

  def logout = {
    Map response
    if (!session.membership && !session.employeeId) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
//      response=employeeService.logout(session.membership?.employee?.nif?:session.employeeId)
      session.invalidate()
      response=AppConstants.EMP_LOGOUT_RESULT_OK
    }
    response.info=message(code:response.info, args:response.data)
    flash.message=response.info
    withFormat{
      html { render(view:'login', model:response)}
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response) }
    }
  }

  def inviteSME = {
    Map response
    if (!params.email || !params.smeName) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=invitationService.invite(session.membership.sme.cif, params.email, params.smeName, params.userName,
                                          request.getHeader("User-Agent"))
      }
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee])}
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def inviteSU = {
    Map response
    if (!params.email) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=invitationService.invite(session.membership.sme.primaryUser, params.email, session.membership.sme.cif, 
                                          params.userName, request.getHeader("User-Agent"))
      }
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee])}
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  // Borrado de un usuario
  def delete = {
    Map response
    if (!params.user) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=employeeService.delete(params.user, session.membership.sme.cif)
      }
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  // Registro de Usuario Secundario
  def create = {
    Map response
    if (!params.userProfile || !params.invitation) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=employeeService.create(params.userProfile, session.membership.sme.cif, params.invitation)
      }
    }
    response.info=message(code:response.info, args:response.args)
    response.remove('args')
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def requestContact = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.requestContact(session.membership.sme.cif, params.smeDest)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone { render (contentType:'text/xml', view:'/iphone2/info', model:response) }
    }
  }

  def acceptContact = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.acceptContact(session.membership.sme.cif, params.smeDest)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json {render response as JSON}
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone {
        if (AppConstants.isResponseOk(response)) {
          response+=[sme:session.membership.sme.cif, employee:session.membership.employee.nif]
          render (view:'iphone/info', model:response)
        } else {
          render (view:'iphone/error')
        }
      }
    }
  }

  def rejectContact = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.rejectContact(session.membership.sme.cif, params.smeDest)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone {
        if (AppConstants.isResponseOk(response)) {
          response+=[sme:session.membership.sme.cif, employee:session.membership.employee.nif]
          render (view:'iphone/info', model:response)
        } else {
          render (view:'iphone/error')
        }
      }
    }
  }

  def deleteContact = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.deleteContact(session.membership.sme.cif, params.smeDest)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def addRecommendation = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.addRecommendation(session.membership.sme.cif, params.smeDest, params.description)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def deleteRecommendation = {
    Map response
    if (!params.smeDest) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        params.smeDest.tokenize(',').each {
          response=smeService.deleteRecommendation(session.membership.sme.cif, it.trim())
//          if (!AppConstants.isResponseOk(response)) break;
        }
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat{
      html { render(view:'show', model:[sme:session.membership?.sme, employee:session.membership?.employee]) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def changeUserProfile = {
    Map response
    if (!params.userProfile) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=employeeService.changeUserProfile(session.membership.sme.cif, session.membership.employee.nif, params.userProfile)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { forward(action:'renderContent') } // Aquí tenemos que redirigir porque han cambiado los datos y hay que volver a leerlos
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone {
        if (AppConstants.isResponseOk(response)) {
          response+=[sme:session.membership.sme.cif, employee:session.membership.employee.nif]
          render (view:'iphone/info', model:response)
        } else {
          render (view:'iphone/error')
        }
      }
    }
  }

  def changeSmeProfile = {
    Map response
    if (!params.smeBasicProfile && !params.smeAdvancedProfile && ! params.smeContentProfile) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      log.debug "Antes de llamar a smeService.changeSmeProfile"
      response=smeService.changeSmeProfile(params.smeBasicProfile,params.smeAdvancedProfile,params.smeContentProfile)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone {
        if (AppConstants.isResponseOk(response)) {
          if (params.sme) response+=[sme:params.sme]
          if (params.employee) response+=[employee:params.employee]
          render (view:'iphone/info', model:response)
        } else {
          render (view:'iphone/error')
        }
      }
    }
  }

  def setPrimaryUser={
    Map response
    if(!params.sme || !params.user){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response= smeService.setPrimaryUser(params.sme,params.user)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def search={
    Map response
    if(!params.query){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      def user=params.user
      if(!user){
        user=session.membership.employee.nif
      }
      def town=params.town?:session.membership.sme.town
      response=smeService.search(params.query, town, user)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response+=[sme:session.membership?.sme])}
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'searchList.xml', model: response) }
      iphone { render(contentType:"text/xml", view:'/iphone2/searchList', model:response) }
    }
  }

  def advancedsearch = {
    Map response
    log.debug params
//    if (!params.filter.town) {
//      response=AppConstants.GLOBAL_ERROR_PARAMS
//    } else {
      def user=params.user
      if (!user) {
        user=session.membership.employee.nif
      }
      response=smeService.advancedsearch(params.query, params.filter, user)
//    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response+=[sme:session.membership?.sme])}
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:session.membership?.sme, employee:session.membership?.employee]) }
      iphone { render(contentType:"text/xml", view:'/iphone2/searchList', model:response) }
    }
  }

   def searchBlog={
    Map response
     log.debug params
   if (!params.query) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      def user=params.user
      if (!user) {
        user=session.membership.employee.nif
      }
      response=smeService.searchBlog(params.query, params.user, params.search_title, params.search_text)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:session.membership?.sme, employee:session.membership?.employee]) }
      html { render(view:'show', model:response+=[sme:session.membership?.sme])}
    }
  }

   def searchMessage={
    Map response
     log.debug params
   if (!params.query) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      def user=params.user
      def company = session.membership.sme.cif
      if (!user) {
        user=session.membership.employee.nif
      }
      response=smeService.searchMessage(params.query, user, company, params.search_subject,params.search_text)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'messages.xml', model: response) }
      html { render(view:'show', model:response+=[sme:session.membership?.sme])}
    }
  }

  def sendMessage = {
    Map response
    if (!params.addressees || !params.text) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.sendMessage(params.addressees, session.membership.employee.nif, session.membership.sme.cif, params.title, params.text)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def answerMessage = {
    Map response
    if (!params.idMsg || !params.text) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.answerMessage(params.idMsg, session.membership.employee.nif, session.membership.sme.cif, params.title, params.text)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def deleteMessage = {
    Map response
    if (!params.idMsg) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.deleteMessage(params.idMsg, session.membership.employee.nif, session.membership.sme.cif)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def deleteNotification={
    Map response
    if(!params.idNotif || !params.company){
      response=AppConstants.GLOBAL_ERROR_PARAMS
    }else{
      response= smeService.deleteNotification(params.idNotif, params.company)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def addNotice = {
    Map response
    if (!params.title || !params.text) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.addNotice(session.membership.sme.cif, session.membership.employee.nif, params.title, params.text)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
      iphone {
        if (AppConstants.isResponseOk(response)) {
          if (params.sme) response+=[sme:params.sme]
          if (params.employee) response+=[employee:params.employee]
          render (view:'iphone/info', model:response)
        } else {
          render (view:'iphone/error')
        }
      }
    }
  }

  def deleteNotice = {
    Map response
    if (!params.idNotice) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.deleteNotice(session.membership.sme.cif, params.idNotice)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def addPost = {
    Map response
    if (!params.title || !params.text) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.addPost(session.membership.sme.cif, session.membership.employee.nif, params.title, params.text)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def modifyPost = {
    Map response
    if (!params.idPost || !params.title || !params.text) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.modifyPost(session.membership.sme.cif, session.membership.employee.nif, params.idPost, params.title, params.text)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def deletePost = {
    Map response
    if (!params.idPost) {
      response=AppConstants.GLOBAL_ERROR_PARAMS
    } else {
      if (!session.membership) {
        response=AppConstants.GLOBAL_ERROR_COOKIE
      } else {
        response=smeService.deletePost(session.membership.sme.cif, session.membership.employee.nif, params.idPost)
      }
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      html { render(view:'show', model:response) }
      json { render response as JSON }
      xml { render(contentType: "text/xml", view: 'show.xml', model: response+[sme:Sme.getById(session.membership.sme.cif),
                                                                               employee:Employee.getById(session.membership.employee.nif)]) }
    }
  }

  def avatar_image = {
    def employee=Employee.getById(params.id)
    if (!employee || !employee.avatar || !employee.avatarType) {
      return;
    }
    response.setContentType(employee.avatarType)
    response.setContentLength(employee.avatar.size())
    OutputStream out=response.getOutputStream();
    out.write(employee.avatar);
    out.close();
  }

  def logo_image = {
    def sme=Sme.getById(params.id)
    if (!sme || !sme.logo || !sme.logoType) {
      return;
    }
    response.setContentType(sme.logoType)
    response.setContentLength(sme.logo.size())
    OutputStream out=response.getOutputStream();
    out.write(sme.logo);
    out.close();
  }

  def showSmeInfo = {
    def sme=Sme.getById(params.id)
    withFormat {
      json { render sme as JSON }
      xml { render sme as XML }
      iphone { render(contentType:"text/xml", view:'/iphone2/sme', model:[sme:sme, employee:session.membership?.employee.nif, smeOrig:session.membership?.sme.cif]) }
    }
  }

  def showSectorInfo = {
    Map response
    if (!session.membership) {
      response=AppConstants.GLOBAL_ERROR_COOKIE
    } else {
      Map feedsRss=[:]
      session.membership.sme.sector.tokenize(',').each { s ->
        def sector=Sector.findByName(s)
        if (sector) {
          sector.feeds?.readLines()?.each { feed ->
            def title=feed.tokenize(';')[0]
            def rss=feed.tokenize(';')[1]
            feedsRss.put(title, rss)
          }
        }
      }
      response=RssReader.parseRss(feedsRss)
    }
    response.info=message(code:response.info)
    flash.message=response.info
    withFormat {
      json { render response as JSON }
      xml { render(contentType:"text/xml", view:'rss.xml', model:response) }
    }
  }

  def rss = {
    if (!params.rss) {
      return
    }
    def feed=new XmlParser().parse(params.rss)
    def feedList=[]
    def title
    def type="RSS"
    (0..<feed.channel.item.size()).each {
      feedList+=(groovy.util.Node)(feed.channel.item.get(it));
    }
    title=feed.channel.title.text()
    // Si no hemos leído ningún channel, tal vez sea Atom, lo intentamos
    if (!feedList) {
      type="Atom"
      (0..<feed.entry.size()).each {
        feedList+=(groovy.util.Node)(feed.entry.get(it));
      }
      title=feed.title.text()
    }
    withFormat {
      iphone { render(contentType:"text/xml", view:'/iphone2/rss', model:[title:title, feeds:feedList, type:type]) }
    }
  }

  def returnLastPosts = {
    withFormat {
      xml { render(contentType: "text/xml", view: 'show.xml', model: [sme:Sme.getById(session.membership.sme.cif),
                                                                      employee:Employee.getById(session.membership.employee.nif),
                                                                      nmaxPosts:params.nmax, noffsetPosts:params.noffset]) }
    }
  }

  def returnLastNotifications = {
    withFormat {
      xml { render(contentType: "text/xml", view: 'show.xml', model: [sme:Sme.getById(session.membership.sme.cif),
                                                                      employee:Employee.getById(session.membership.employee.nif),
                                                                      nmaxNotif:params.nmax, noffsetNotif:params.noffset]) }
    }
  }

  def cleaningJob = {
    CleaningJob.triggerNow()
    render (text: "Ok")
  }

}
