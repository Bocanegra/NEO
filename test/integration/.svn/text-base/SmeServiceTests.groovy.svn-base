import grails.test.*

class SmeServiceTests extends GrailsUnitTestCase {

  def smeService

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }


  void testaddSmeAndPU() {
    def smec = new EmployeeController()
    smec.smeService = smeService

    // Crear SME con lo mínimo indispensable
    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "TID"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvancedProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    def sme_1 = Sme.findByName('TID')
    def empl_1 = Employee.findByEmail('lagm@tid.es')
    assert sme_1
    assert empl_1
    println "El PU y la SME existen..."

    assertEquals sme_1.primaryUser, empl_1
    assertEquals sme_1.contents.size(), 3
    println "El PU está relacionado con la empresa y tiene ${sme_1.contents.size()} tipos de contenido"

    // Crear una SME con un usuario que ya es PrimaryUser de otra SME
    smec.params.smeBasicProfile.name = "TID 2"
    smec.params.smeBasicProfile.address = "C/Otra calle"
    smec.params.smeBasicProfile.town = "Madrid 2"
    smec.params.smeBasicProfile.country = "Spain 2"
    smec.params.smeBasicProfile.contactEmail = "lagm2@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco 2"
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()
    def sme_2 = Sme.findByName('TID 2')
    assertEquals sme_2.primaryUser, empl_1
    // Por tanto, este empleado ahora tiene 2 SMEs en su lista...
    assertEquals empl_1.smes.size(), 2
    println "El Empleado ahora tiene 2 SMEs en su lista"
  }

  /**
   * Se prueba una creación de SME donde falten datos
   */

  void testaddSmeWithoutData() {
    def smec = new EmployeeController()
    smec.smeService = smeService
    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "TID"
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.create()

    def sme_1 = Sme.findByName('TID')
    def empl_1 = Employee.findByName('Luis Angel')
    assert !sme_1
    assert !empl_1
  }

  /**
   * Pruebas de solicitud de contactos
   */

  //TESTS DE PARÁMETROS DE ENTRADA

  void testRequestContactParamsTodos() {
    //requestContact con todos los datos
    def smec = new EmployeeController()
    smec.smeService = smeService

    //Se crean las dos empresas a conectar
    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "Telefónica de España"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "Telefónica Investigación y Desarrollo"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    //Se lanza la solicitud de contacto
    smec.params.smeNameOrig = "Telefónica de España"
    smec.params.smeNameDest = "Telefónica Investigación y Desarrollo"
    smec.requestContact()

    //Se comprueba la request
    def smeDest = Sme.findByName("Telefónica Investigación y Desarrollo")
    def requestSet = smeDest.returnContactList().requests
    def requestList = requestSet as List


    assert 1 == Request.findAll().size()
    assert 2 == Sme.findAll().size()
    assert 1 == requestList.size()
    assert requestList[0].sender == "Telefónica de España"
  }

  void testRequestContactParamssmeNameOrig(){
    //requestContact sin smeNameOrig
    def smec=new EmployeeController()
    smec.params.smeNameDest="TID"
    smec.requestContact()

    assert 0==Request.findAll().size()
  }

  void testRequestContactParamssmeNameDest(){
    //requestContact sin smeNameDest
    def smec=new EmployeeController()
    smec.params.smeNameOrig="TID"
    smec.requestContact()
    
    assert 0==Request.findAll().size()
  }

   //TESTS DE VALORES DE RETORNO DEL SERVICIO
   //En cada caso se obtiene la respuesta esperada del servicio.

   void testRequestContactSRVOk(){
     //OK. Solicitud registrada en BD.
     def smec=new EmployeeController()
     smec.smeService=smeService

     //Se crean las dos empresas a conectar
     smec.params.smeBasicProfile = [:]
     smec.params.smeBasicProfile.name = "SME1"
     smec.params.smeBasicProfile.address = "C/Blabla 3"
     smec.params.smeBasicProfile.town = "Madrid"
     smec.params.smeBasicProfile.country = "Spain"
     smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
     smec.params.smeBasicProfile.sector = "Teleco"
     //smeAdvancedProfile opcional
     //smec.params.smeAdvancedProfile
     smec.params.smeContentProfile = [:]
     smec.params.smeContentProfile.contactList = Content.PUBLIC
     smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
     smec.params.smeContentProfile.feed = Content.PRIVATE
     smec.params.userProfile = [:]
     smec.params.userProfile.name = "Luis Angel"
     smec.params.userProfile.surname = "García"
     smec.params.userProfile.email = "lagm@tid.es"
     smec.create()

     smec.params.smeBasicProfile = [:]
     smec.params.smeBasicProfile.name = "SME2"
     smec.params.smeBasicProfile.address = "C/Blabla 3"
     smec.params.smeBasicProfile.town = "Madrid"
     smec.params.smeBasicProfile.country = "Spain"
     smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
     smec.params.smeBasicProfile.sector = "Teleco"
     //smeAdvancedProfile opcional
     //smec.params.smeAdvancedProfile
     smec.params.smeContentProfile = [:]
     smec.params.smeContentProfile.contactList = Content.PUBLIC
     smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
     smec.params.smeContentProfile.feed = Content.PRIVATE
     smec.params.userProfile = [:]
     smec.params.userProfile.name = "Luis Angel"
     smec.params.userProfile.surname = "García"
     smec.params.userProfile.email = "lagm@tid.es"
     smec.create()

     def result= smeService.requestContact("SME1", "SME2")

     assert result,AppConstants.SME_REQUESTCONTACT_RESULT_OK
     assert 1==Request.findAll().size()
  }

  void testRequestContactSRVNoSmeOrig(){
    //No existe sme origen. NO se registra solicitud en BD.
    def smec=new EmployeeController()
    smec.smeService=smeService

    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "SME2"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    def result= smeService.requestContact("SME1", "SME2")

    assert result,AppConstants.SME_REQUESTCONTACT_RESULT_NOSMEORIG
    assert 0==Request.findAll().size()
 }

  void testRequestContactSRVNoSmeDest(){
    //No existe sme destino. NO se registra solicitud en BD.
    def smec=new EmployeeController()
    smec.smeService=smeService

    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "SME1"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvanceProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    def result= smeService.requestContact("SME1", "SME2")

    assert result,AppConstants.SME_REQUESTCONTACT_RESULT_NOSMEDEST
    assert 0==Request.findAll().size()
 }

  
 void testChangeProfile(){

     def smec = new EmployeeController()
    smec.smeService = smeService

    // Crear SME con lo mínimo indispensable
    smec.params.smeBasicProfile = [:]
    smec.params.smeBasicProfile.name = "TID"
    smec.params.smeBasicProfile.address = "C/Blabla 3"
    smec.params.smeBasicProfile.town = "Madrid"
    smec.params.smeBasicProfile.country = "Spain"
    smec.params.smeBasicProfile.contactEmail = "lagm@tid.es"
    smec.params.smeBasicProfile.sector = "Teleco"
    //smeAdvancedProfile opcional
    //smec.params.smeAdvancedProfile
    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.PUBLIC
    smec.params.smeContentProfile.smeAdvancedProfile = Content.PRIVATE
    smec.params.smeContentProfile.feed = Content.PRIVATE
    smec.params.userProfile = [:]
    smec.params.userProfile.name = "Luis Angel"
    smec.params.userProfile.surname = "García"
    smec.params.userProfile.email = "lagm@tid.es"
    smec.create()

    def sme_1 = Sme.findByName('TID')
    def empl_1 = Employee.findByEmail('lagm@tid.es')
    assert sme_1
    assert empl_1

   //Ahroa cambio el sector en el basicprofile y la visibilidad del feed
    smec.params.smeBasicProfile=[:]
    smec.params.smeBasicProfile.name="TID"
    smec.params.smeBasicProfile.address="C/Blabla 3"
    smec.params.smeBasicProfile.town="Madrid"
    smec.params.smeBasicProfile.country="Spain"
    smec.params.smeBasicProfile.contactEmail="lagm@tid.es"
    smec.params.smeBasicProfile.sector="Informatica"

    smec.params.smeContentProfile = [:]
    smec.params.smeContentProfile.contactList = Content.NONE
    smec.params.smeContentProfile.smeAdvancedProfile = Content.CONTACTS
    smec.params.smeContentProfile.feed = Content.NONE

    smec.changeProfile()
    assert sme_1.sector, "Informatica"
    assert sme_1.returnFeed().visibility, "NONE"
   //assert sme_1.returnContactList().visibility, "NONE"
    println "Perfil cambiado"


  }  
  

}
