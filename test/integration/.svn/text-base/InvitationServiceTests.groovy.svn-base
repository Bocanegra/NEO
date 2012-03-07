import grails.test.*

class InvitationServiceTests extends GrailsUnitTestCase {

  //Constantes
  static String MAIL_PRUEBA="valle@tid.es"

  def invitationService
  def smeService
  
  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }
  //************* TEST DE INVITE *************

  //TESTS DE PARÁMETROS DE ENTRADA
  //Se crea y envía la invitación siempre que se reciben correctamente los parámetros de entrada.

  void testInviteParamsTodos() {
    //Invitación con todos los datos
    def ic=new InvitationController()
    ic.invitationService=invitationService
    ic.params.sender="O2"
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.params.addresseeEmployeeName="Alicia Valle Martín"
    ic.params.urlBase="http://www.neo.com/invitations"
    
    ic.invite()
    assert 1==Invitation.findAll().size()
  }


  void testInviteParamsObligatorios() {
    //Invitación con sólo parámetros obligatorios
    def ic=new InvitationController()
    ic.invitationService=invitationService
    ic.params.sender="O2"
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.urlBase="http://www.neo.com/invitations"
    ic.invite()
    assert 1==Invitation.findAll().size()
  }

  void testInviteParamsSender() {
    //Invitación sin dato obligatorio sender
    def ic=new InvitationController()
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.params.addresseeEmployeeName="Alicia Valle Martín"
    ic.params.urlBase="http://www.neo.com/invitations"
    ic.invite()
    assert 0==Invitation.findAll().size()
  }
  
  void testInviteParamsaddresseeMail() {
    //Invitación sin dato obligatorio addresseeMail
    def ic=new InvitationController()
    ic.params.sender="O2"
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.params.addresseeEmployeeName="Alicia Valle Martín"
    ic.params.urlBase="http://www.neo.com/invitations"
    ic.invite()
    assert 0==Invitation.findAll().size()
  }

  void testInviteParamsurlBase() {
    //Invitación sin dato obligatorio urlBase
    def ic=new InvitationController()
    ic.params.sender="O2"
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.params.addresseeEmployeeName="Alicia Valle Martín"
    ic.invite()
    assert 0==Invitation.findAll().size()
  }


  //TESTS DE VALORES DE RETORNO DEL SERVICIO
  //En cada caso se obtiene la respuesta esperada del servicio.

   void testInviteSRVOk(){
     //OK. Invitación registrada en BD.
     def ic=new InvitationController()
     ic.invitationService=invitationService
     def result= invitationService.invite("O2",MAIL_PRUEBA, "", "", "http://www.neo.com/invitations")

     assert result,AppConstants.INVITATION_VALIDATE_RESULT_OK
     assert 1==Invitation.findAll().size()
  }

  void testInviteSRVNoMail(){
    //NOMAIL. Invitación no registrada en BD.
    //Para probarlo hacer que el mail lance excepción
    /*
    def ic=new InvitationController()
    ic.invitationService=invitationService
    def result= invitationService.invite("O2","neo@tid.es", "", "", "http://www.neo.com/invitations")

    assertEquals result,AppConstants.INVITATION_INVITE_RESULT_NOMAIL
    assert 0==Invitation.findAll().size()
    */
 }


  //************* TEST DE VALIDATE *************

  //TESTS DE PARÁMETROS DE ENTRADA
  //Se valida la invitación siempre que se reciben correctamente los parámetros de entrada.

  void testValidateParamsObligatorios(){
    def ic=new InvitationController()
    ic.invitationService = invitationService
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Validaciónn con todos los datos
    ic.params.hashCode=invitation.code
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.validate()
    assert 1==Invitation.findAll().size()
  }

  void testValidateParamsCode() {
    def ic=new InvitationController()
    //No se asocia el servicio porque antes de llamarlo se detecta que falta un param obligatorio y sale
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Validación sin hash
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.validate()
    assert 1==Invitation.findAll().size()
  }

  void testValidateParamsaddresseeMail() {
    def ic=new InvitationController()
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Validaciónn sin addresseeMail
    ic.params.hashCode=invitation.code
    ic.params.addresseeSMEName="Telefónica Investigación y Desarrollo"
    ic.validate()
    assert 1==Invitation.findAll().size()
  }

  void testValidateParamsaddresseeSMEName() {
    def ic=new InvitationController()
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Validaciónn sin addresseeSMEName
    ic.params.hashCode=invitation.code
    ic.params.addresseeMail=MAIL_PRUEBA
    ic.validate()
    assert 1==Invitation.findAll().size()
  }

  
  //TESTS DE VALORES DE RETORNO DEL SERVICIO
  //En cada caso se obtiene la respuesta esperada del servicio.

  void testValidateSRVOk(){
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Resultado ok. Invitación válida empresa no existe. Datos obtenidos de Experian.
    def result= invitationService.validate(invitation.code, invitation.addresseeMail, invitation.addresseeSMEName)

    assert 1==Invitation.findAll().size()
    assertEquals result,AppConstants.INVITATION_VALIDATE_RESULT_OK
  }

  void testValidateSRVNoData(){

    //Consulta a Experian
    //invitationService.invite("O2", MAIL_PRUEBA, "Empresa que no esté en Experian","Ali", "http://www.neo.com/invitations")

    //Resultado ok. Invitación válida empresa no existe. Sin datos de Experian.
    //def result= invitationService.validate(invitation.code, invitation.addresseeMail, invitation.addresseeSMEName)

    //assert 1==Invitation.findAll().size()
    //assertEquals result,AppConstants.INVITATION_VALIDATE_RESULT_NODATA

  }

  void testValidateSRVSmeExists(){
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Invitación válida empresa existe. Alta de usuario secundario.
    def smeBasicProfile=[:]
    smeBasicProfile.name="TID"
    smeBasicProfile.address="C/Blabla 3"
    smeBasicProfile.town="Madrid"
    smeBasicProfile.country="Spain"
    smeBasicProfile.contactEmail="lagm@tid.es"
    smeBasicProfile.sector="Teleco"
    def smeAdvancedProfile= [:]
    def smeContentProfile=[:]
    smeContentProfile.contactList=Content.PUBLIC
    smeContentProfile.smeAdvanceProfile=Content.PRIVATE
    smeContentProfile.feed=Content.PRIVATE
    def userProfile=[:]
    userProfile.name="Luis Angel"
    userProfile.surname="García"
    userProfile.email=MAIL_PRUEBA

    smeService.create( smeBasicProfile, smeAdvancedProfile, smeContentProfile, userProfile)
    def invitationResult= invitationService.validate(invitation.code, invitation.addresseeMail, invitation.addresseeSMEName)

    assert 1==Invitation.findAll().size()
    assert 1==Sme.findAll().size()
    assertEquals invitationResult,AppConstants.INVITATION_VALIDATE_RESULT_SMEEXISTS
  }

  void testValidateSRVFraud(){
    invitationService.invite("O2", MAIL_PRUEBA, "TID","Ali", "http://www.neo.com/invitations")
    def invitation=Invitation.findByAddresseeSMEName("TID")

    //Fraude. Invitación no válida.
    def result= invitationService.validate(invitation.code, "lagm@tid.es", invitation.addresseeSMEName)
    assertEquals result,AppConstants.INVITATION_VALIDATE_RESULT_FRAUD
  }
  
}
