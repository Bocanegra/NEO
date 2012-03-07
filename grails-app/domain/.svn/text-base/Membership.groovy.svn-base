class Membership {

  Employee employee
  Sme sme

  String email
  String telephoneNumber=""
  String cellularNumber=""

  static constraints={
    email(blank:false, email:true, size:1..100)
    telephoneNumber(nullable:true, size:0..11)
    cellularNumber(nullable:true, size:0..11)
  }

  static mapping={
    table 'Memberships'
  }

  String toString(){
    "Employee $employee in sme $sme: $email"
  }

  //Comprueba que se puede registrar una nueva asociaci�n
  static boolean canAssociate(Employee employee, Sme sme, Map contactData){
    def result=true
    //Se comprueba que no existe relaci�n previa entre el empleado y la empresa.
    def membership=Membership.findByEmployeeAndSme(employee, sme)
    //No existe otro empleado distinto con el mismo mail
    def memberEmail=Membership.findAllByEmail(contactData.email)
    def otherEmps=[]
    memberEmail.each{
      if(it.employee.nif!=employee.nif){otherEmps.add(it.employee.nif)}
    }
    if(membership || otherEmps.size()!=0){
      result=false
    }
    result
  }

  //Comprueba que se puede modificar el contactData de una asociaci�pn
  static Membership canChangeContactData(Employee employee, Sme sme, Map contactData){
    //Existe la relaci�n entre empleado y empresa
    def membership=Membership.findByEmployeeAndSme(employee, sme)
    //No existe otro empleado distinto con el mismo mail
    def memberEmail=Membership.findAllByEmail(contactData.email)
    def otherEmps=[]
    memberEmail.each{
      if(it.employee.nif!=employee.nif){otherEmps.add(it.employee.nif)}
    }
    if(!membership || otherEmps.size()!=0){
      membership=null
    }
    membership
  }

  //Asocia un empleado con una empresa
  static link(Sme sme, Employee employee, Map contactData){
    def membership=new Membership(contactData)
    employee?.addToMemberships(membership)
    sme?.addToMemberships(membership)
    membership.save()
    return membership
  }

  //Elimina la asociaci�n entre empleado y empresa
  static unlink(Employee employee, Sme sme){
    def membership=Membership.findByEmployeeAndSme(employee, sme)
    if(membership){
      employee?.removeFromMemberships(membership)
      sme?.removeFromMemberships(membership)
      membership.delete()
    }
  }
  
  def getContactData(){
    def contactData=["email":"${this.email}", "telephoneNumber":"${this.telephoneNumber}" , "cellularNumber":"${this.cellularNumber}"]
    return contactData
  }

  def setContactData(Map contactData){
    this.properties=contactData
  }
  
}
