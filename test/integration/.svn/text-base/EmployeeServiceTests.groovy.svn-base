import grails.test.*

class EmployeeServiceTests extends GrailsUnitTestCase {

  // Constantes
  static String LOGIN_PRUEBA="lagm@tid.es"
  static String MAIL_PRUEBA="lagm@tid.es"

  def employeeService

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testLoginAndLogout() {
    // Tenemos que tener un Employee en el sistema, lo metemos
    def employee=new Employee(name:"Luis", surname:"Garcia", email:MAIL_PRUEBA).save(flush:true)
    assert 1, Employee.findAll().size()

    // Se comprueba un login correcto
    def ec=new EmployeeController()
    ec.employeeService=employeeService
    ec.params.idEmployee=LOGIN_PRUEBA
    ec.login()
    assert employee.logged
    println "Login comprobado"

    // Se comprueba con un login incorrecto
    ec.params.idEmployee="kkk"
    assert !ec.login()
    println "Login con usuario inexistente comprobado"

    // Se comprueba el logout del primer usuario logueado
    ec.params.idEmployee=LOGIN_PRUEBA
    ec.logout()
    assert !employee.logged
    println "Logout comprobado"


    // Vamos a comprobar el cambio de Profile
    ec.params.userProfile=[:]
    ec.params.userProfile.name="Victor"
    ec.params.userProfile.surname="Martin"
    ec.params.userProfile.email=LOGIN_PRUEBA

    ec.changeProfile()

    assert employee.name,"Victor"
    println "Se ha cambiado el perfil"

  }

}
