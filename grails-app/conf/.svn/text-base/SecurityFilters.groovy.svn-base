class SecurityFilters {

  def filters = {
    // Se comprueba el User-Agent para ver si viene de iPhone/iPod, y se pone la propiedad a true
    all(controller: '*', action: '*') {
      before = {
        if (request.getHeader("User-Agent") =~ "Mobile.*Safari" ||
            request.getHeader("User-Agent") =~ "SymbianOS") {
          request['fromIPhone']=true
          if (request.servletPath.equals("/")) {
            redirect(controller: 'employee', action: 'login')
          }
        } else {
          if (request.servletPath.equals("/")) {
            redirect(uri:'/NEOWEB/NEOWEB.html')
          }
        }
      }
    }

    loginCheck(controller: 'employee', action: '*') {
      before = {
        if (!session.employeeId && actionName!="validate" && actionName!="login" && actionName!='register') {
          redirect(controller: 'employee', action: 'login')
          return false
        }
      }
    }

    loginCheck(controller: 'administrator', action: '*') {
      before = {
        if (!session.administrator && actionName!="validate" && actionName!="login") {
          redirect(controller: 'administrator', action: 'login')
          return false
        }
      }
    }

  }

}