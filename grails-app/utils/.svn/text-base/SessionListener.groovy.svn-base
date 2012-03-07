import javax.servlet.http.*
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.springframework.context.ApplicationContext

class SessionListener implements HttpSessionListener {

  void sessionCreated(HttpSessionEvent event) {
    // Nothing to do
  }

  void sessionDestroyed(HttpSessionEvent event) {
    println "SessionDestroyed catching, logout user"
    def session=event.session
    ApplicationContext ctx=(ApplicationContext)session.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT);
    EmployeeService employeeService=(EmployeeService)ctx.getBean("employeeService")
    employeeService.logout(session.membership?.employee?.nif?:session.employeeId)
  }
}