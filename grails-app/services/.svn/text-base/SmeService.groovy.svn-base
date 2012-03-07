import org.springframework.context.*

class SmeService implements ApplicationContextAware {

  boolean transactional = true

  ApplicationContext applicationContext

  //Servicios utilizados
  def searchableService

  /**
   * Se crea una nueva Sme.
   * @params smeProfile Map con los datos de la SME.
   * @params pu Employee con los datos del usuario primario.
   */
  def create(Map smeProfile, Employee pu) {
    //Se asigna el basic profile.
    Sme sme=new Sme(smeProfile.basicProfile)
    //Se asigna el usuario como primario a la empresa.
    sme.setPU(pu.nif)
    // Se guarda el logo
    if (smeProfile.logoSme) {
      sme.logoType=smeProfile.logoSme.getContentType()-"image/"
      sme.logo=smeProfile.logoSme.getBytes()
      def basePath=applicationContext.getResource("avatars").getFile().toString()
      def urlLogo=new File(basePath, sme.cif+"."+sme.logoType)
      smeProfile.logoSme.transferTo(urlLogo)
      sme.urlLogo=urlLogo.getAbsolutePath()
    }

    //Se trata el content profile.
    def smeContentProfile=smeProfile.contentProfile
    //Contact List. Obligatorio.
    sme.addToContents(new ContactList(visibility:smeContentProfile.contactList))
    //OperatorInfo. Obligatorio.
    sme.addToContents(new OperatorInfo(visibility:smeContentProfile.operatorInfo))
    //ProfileData
    if(smeContentProfile.profileData!=Content.NONE){
      ProfileData profileData=new ProfileData(smeProfile.advancedProfile)
      profileData.visibility=smeContentProfile.profileData
      sme.addToContents(profileData)
    }
    //Blog
    if(smeContentProfile.blog!=Content.NONE){
      sme.addToContents(new Blog(visibility:smeContentProfile.blog))
    }
    //Foro
    if(smeContentProfile.noticeBoard!=Content.NONE){
      sme.addToContents(new NoticeBoard(visibility:smeContentProfile.noticeBoard))
    }
    //Mensajería
    if(smeContentProfile.messageBox!=Content.NONE){
      sme.addToContents(new MessageBox(visibility:smeContentProfile.messageBox))
    }
    //Buscador
    if(smeContentProfile.searchEngine!=Content.NONE){
      sme.addToContents(new SearchEngine(visibility:smeContentProfile.searchEngine))
    }
    //Recomendaciones
    if(smeContentProfile.recomendationBox!=Content.NONE){
      sme.addToContents(new RecommendationBox(visibility:smeContentProfile.recomendationBox))
    }
    //Feed de actividad
    if(smeContentProfile.feed!=Content.NONE){
      sme.addToContents(new Feed(visibility:smeContentProfile.feed))
    }
    sme
  }

  //Se muestra el contenido de la Sme según la visibilidad permitida para el usuario.
  def renderContent(String company, String user, String companyToRender){
    def result=AppConstants.SME_RENDERCONTENT_RESULT_OK
    def sme=null                           
    def smeToRender=null
    def employee=null
    try{
      //Se comprueba que ambas entidades están registradas.
      sme=Sme.getById(company)
      employee=Employee.getById(user)
      smeToRender=Sme.getById(companyToRender)
      if(!sme || !employee || !employee.smes().contains(sme)){
        log.warn "Could not find user ${user} or company ${company} or there is no conexion between them."
        result=AppConstants.SME_RENDERCONTENT_RESULT_NOUSER
      }else{
        //Se comprueba la existencia de la empresa a presentar
        if(!smeToRender){
          log.warn "Could not find sme to render ${companyToRender}."
          result=AppConstants.SME_RENDERCONTENT_RESULT_NOSME
        }else{
          //Identificar la visibilidad del usuario en la empresa.
          def visibility=[]
          def primaryUser=false
          if(employee.smes().contains(smeToRender)){
            visibility.add(Content.PUBLIC)
            visibility.add(Content.CONTACTS)
            visibility.add(Content.PRIVATE)
            if(smeToRender.primaryUser.equals(user)){
              primaryUser=true
            }
          }else{
            def isContact=false
            sme.returnContactList()?.contacts.each{
              if(it.sme.equals(smeToRender.cif)){
                isContact=true
              }
            }
            if(isContact){
              visibility.add(Content.PUBLIC)
              visibility.add(Content.CONTACTS)
            }else{
              visibility.add(Content.PUBLIC)
            }
          }
          log.info "Allowed visibility of user ${user} in company ${sme.name} is ${visibility}."
          def contents=[]
          smeToRender.contents.each{
            if ((it.visibility) in visibility) {
             contents.add(it)
            }
          }
          //log.info "Contents with visibility: ${visibility} in sme: ${sme.name} are: ${contents}"
          contents.each{
            if(it.getClass().toString().equals("class MessageBox")){
              //Se retornan los mensajes personales del usuario.
              def mBox=[]
              it.messages.each{message->
                if(message.addressee.equals(user)){
                   mBox.add(message)
                }
              }
              it.messages = mBox
            }
          }
          //Se retornan los contenidos que corresponda.
          smeToRender.contents=contents
        }
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.SME_RENDERCONTENT_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=smeToRender
      employee.assignContactData(smeToRender)
      result.employee=employee
    }
    result
  }


  //La empresa orig solicita a la empresa dest la inclusión en la lista de contactos.
  def requestContact(String smeOrig, String smeDest){
    def result=AppConstants.SME_REQUESTCONTACT_RESULT_OK
    def sme1=null
    try{
      //Se comprueba que ambas empresas están registradas.
      sme1=Sme.getById(smeOrig)
      if(!sme1){
        log.warn "Could not find SME $smeOrig."
        result=AppConstants.SME_REQUESTCONTACT_RESULT_NOSMEORIG
      }else{
        def sme2=Sme.getById(smeDest)
        if(!sme2){
          log.warn "Could not find SME $smeDest."
          result=AppConstants.SME_REQUESTCONTACT_RESULT_NOSMEDEST
        }else{
          // La empresa origen no puede ser la misma que la de destino
          if (sme1==sme2) {
            log.warn "Sme orig and dest can't be the same"
            result=AppConstants.SME_REQUESTCONTACT_RESULT_ERROR
          } else {
            // Se crea Request para empresa destino, si no existe ya
            if (!sme2.returnContactList().hasRequest(smeOrig)) {
              sme2.returnContactList()?.addRequest(smeOrig, sme1.name)
              if(!sme2.save()){
                sme2.errors.each{
                  log.warn(it)
                }
                result=AppConstants.SME_REQUESTCONTACT_RESULT_ERROR
              }
            } else {
              log.warn "The request for ${smeOrig} already exists"
              result=AppConstants.SME_REQUESTCONTACT_RESULT_ERROR
            }
          }
        }
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.SME_REQUESTCONTACT_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result))
      result.sme=sme1
    result
  }

  // La empresa origen acepta la solicitud de contacto de la empresa destino.
  def acceptContact(String smeOrig, String smeDest){
    def result=AppConstants.SME_ACCEPTCONTACT_RESULT_OK
    def sme1=null
    try{
      //Se comprueba que ambas empresas están registradas y que existe la solicitud de contacto.
      sme1=Sme.getById(smeOrig)
      if(!sme1){
        log.warn "Could not find SME $smeOrig."
        result=AppConstants.SME_ACCEPTCONTACT_RESULT_NOSMEORIG
      }else{
        def sme2=Sme.getById(smeDest)
        if(!sme2){
          log.warn "Could not find SME $smeDest."
          result=AppConstants.SME_ACCEPTCONTACT_RESULT_NOSMEDEST
        }else{
          def cl1=sme1.returnContactList()
          if(cl1?.hasRequest(smeDest)){
            //La smeOrig modifica el estado de la smeDest en su contactlist a conexión débil.
            cl1?.addContact(smeDest, sme2.name)
            //Se añade el contacto smeOrig en la lista de contactos de smeDest como conexión débil.
            def cl2=sme2.returnContactList()
            cl2?.addContact(smeOrig, sme1.name)
            if(!sme1.save()){
              sme1.errors.each{
                log.warn(it)
              }
              result=AppConstants.SME_ACCEPTCONTACT_RESULT_ERROR
              log.error "Error saving SMEs"
            }
            if(!sme2.save()){
              sme2.errors.each{
                log.warn(it)
              }
              result=AppConstants.SME_ACCEPTCONTACT_RESULT_ERROR
              log.error "Error saving SMEs"
            }
            //Se crean las notificaciones de conexión.
            def message=[type:"", addressees:[], tile:"", info:""]
            message.type=QueueService.NOTIFICATION
            //El feed de la propia empresa recibe la notificación.
            message.addressees.add(sme1.cif)
            if(!cl1.visibility.equals(Content.PRIVATE)){
              //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
              cl1.contacts.each{
                if(it.state!=Contact.INACTIVE){
                  message.addressees.add(it.sme)
                }
              }
              log.info "Notification to the contacts of $smeOrig"
            }
            message.title=Notification.NEWCONEXION
            message.info= "Firms "+ sme1.name+ " and "+ sme2.name + " are now friends."
            sendJMSMessage("neoQueue", message)
            message.addressees=[]
            if(cl1.visibility.equals(Content.PRIVATE)){
              //El feed de la propia empresa recibe la notificación, si no se ha enviado ya.
             message.addressees.add(sme2.cif)
            }
            if(!cl2.visibility.equals(Content.PRIVATE)){
              //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
              cl2.contacts.each{
                if(!it.sme.equals(sme1.cif) && it.state!=Contact.INACTIVE){
                  message.addressees.add(it.sme)
                }
              }
              log.info "Notification to the contacts of $smeDest"
            }
            sendJMSMessage("neoQueue", message)
          }else{
            log.warn "There is not request from SME $smeDest in SME $smeOrig."
            result=AppConstants.SME_ACCEPTCONTACT_RESULT_NOREQUEST
          }
        }
      }
    }catch(Exception e){
      result=AppConstants.SME_ACCEPTCONTACT_RESULT_ERROR
      log.error(e.message,e)
    }
    if(AppConstants.isResponseOk(result))
      result.sme=sme1
    result
  }

   //La empresa origen rechaza el contacto con la destino
   def rejectContact(String smeOrig, String smeDest){
     def result=AppConstants.SME_REJECTCONTACT_RESULT_OK
     def sme1=null
     try{
       //Se comprueba que ambas empresas están registradas y existe solicitud de contacto.
       sme1=Sme.getById(smeOrig)
       if(!sme1){
         log.warn "Could not find SME $smeOrig."
         result=AppConstants.SME_REJECTCONTACT_RESULT_NOSMEORIG
       }else{
         def sme2=Sme.getById(smeDest)
         if(!sme2){
           log.warn "Could not find SME $smeDest."
           result=AppConstants.SME_REJECTCONTACT_RESULT_NOSMEDEST
         }else{
           def cl1=sme1.returnContactList()
           if(cl1?.hasRequest(smeDest)){
             //Se elimina la solicitud de contacto en la contactlist
             cl1?.deleteRequest(smeDest)
             if(!sme1.save()){
               sme1.errors.each{
                 log.warn(it)
               }
               result=AppConstants.SME_REJECTCONTACT_RESULT_ERROR
               log.error "Error saving SMEs"
             }
           }else{
             log.warn "There is not request from SME $smeDest in SME $smeOrig."
             result=AppConstants.SME_REJECTCONTACT_RESULT_NOREQUEST
           }
         }
       }
     }catch(Exception e){
         result=AppConstants.SME_REJECTCONTACT_RESULT_ERROR
         log.error(e.message,e)
     }
     if(AppConstants.isResponseOk(result))
       result.sme=sme1
     result
   }

   //La empresa origen elimina a la destino de su lista de contactos.
   def deleteContact(String smeOrig, String smeDest){
     def result=AppConstants.SME_DELETECONTACT_RESULT_OK
     def sme1=null
     try{
       //Se comprueba que ambas empresas están registradas.
       sme1=Sme.getById(smeOrig)
       if(!sme1){
         log.warn "Could not find SME $smeOrig."
         result=AppConstants.SME_DELETECONTACT_RESULT_NOSMEORIG
       }else{
         def sme2=Sme.getById(smeDest)
         if(!sme2){
           log.warn "Could not find SME $smeDest."
           result=AppConstants.SME_DELETECONTACT_RESULT_NOSMEDEST
         }else{
           def cl1=sme1.returnContactList()
           if(cl1?.isContact(smeDest)){
             //Se elimina la conexión de forma bidireccional.
             cl1?.deleteContact(smeDest)
             def cl2=sme2.returnContactList()
             cl2?.deleteContact(smeOrig)
             if(!sme1.save()){
                sme1.errors.each{
                  log.warn(it)
                }
               result=AppConstants.SME_DELETECONTACT_RESULT_ERROR
               log.error "Error saving SMEs"
             }
             if(!sme2.save()){
               sme2.errors.each{
                 log.warn(it)
               }
               result=AppConstants.SME_DELETECONTACT_RESULT_ERROR
               log.error "Error saving SMEs"
             }
             //Se crean las notificaciones de desconexión para los contactos.
             def message=[type:"", addressees:[], title:"", info:""]
             message.type=QueueService.NOTIFICATION
             //El feed de la propia empresa recibe la notificación.
             message.addressees.add(sme1.cif)
             if(!cl1.visibility.equals(Content.PRIVATE)){
               //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
               cl1.contacts.each{
                 if(it.state!=Contact.INACTIVE){
                   message.addressees.add(it.sme)
                 }
               }
               log.info "Notification to the contacts of $smeOrig"
             }
             message.title=Notification.DROPCONEXION
             message.info= "Conexion dropped between firms "+ smeOrig+ " and "+ smeDest+ "."
             sendJMSMessage("neoQueue", message)
             message.addressees=[]
             if(cl1.visibility.equals(Content.PRIVATE)){
               //El feed de la propia empresa recibe la notificación, si no se ha enviado ya.
                message.addressees.add(sme2.cif)
             }
             if(!cl2.visibility.equals(Content.PRIVATE)){
               //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
               cl2.contacts.each{
                 if(!it.sme.equals(sme1.cif) && it.state!=Contact.INACTIVE){
                   message.addressees.add(it.sme)
                 }
               }
               log.info "Notification to the contacts of $smeDest"
             }
             sendJMSMessage("neoQueue", message)
           }else{
             log.warn "There is not contact $smeDest in contact list of sme $smeOrig."
             result=AppConstants.SME_DELETECONTACT_RESULT_NOCONTACT
           }
         }
       }
     }catch(Exception e){
         result=AppConstants.SME_DELETECONTACT_RESULT_ERROR
         log.error(e.message,e)
     }
     if(AppConstants.isResponseOk(result))
       result.sme=sme1
     result
   }

   //La empresa origen recomienda a la destino
   def addRecommendation(String smeOrig, String smeDest, String description){
     def result=AppConstants.SME_ADDRECOMMENDATION_RESULT_OK
     def sme1=null
     try{
       //Se comprueba que ambas empresas están registradas y están conectadas
       sme1=Sme.getById(smeOrig)
       if(!sme1){
         log.warn "Could not find SME $smeOrig."
         result=AppConstants.SME_ADDRECOMMENDATION_RESULT_NOSMEORIG
       }else{
         def sme2=Sme.getById(smeDest)
         if(!sme2){
           log.warn "Could not find SME $smeDest."
           result=AppConstants.SME_ADDRECOMMENDATION_RESULT_NOSMEDEST
         }else{
           def cl1=sme1.returnContactList()
           def cl2=sme2.returnContactList()
           def rb1=sme1.returnRecommendationBox()
           def rb2=sme2.returnRecommendationBox()
           if(cl1?.isContact(smeDest)){
             //Se incrementa la valoración de la empresa recomendada y se guarda referencia de la que recomienda.
             sme2.addVote()
             cl2?.addRecommender(smeOrig)
             //Se almacena la recomendación en la origen y se actualiza el estado de la conexión.
             rb1?.addRecommendation(smeDest, description)
             cl1.setStrong(smeDest)
             if(!sme1.save() || !sme2.save()){
               sme1.errors.each{
                 log.warn(it)
               }
               sme2.errors.each{
                 log.warn(it)
               }
               result=AppConstants.SME_ADDRECOMMENDATION_RESULT_ERROR
               log.error "Error saving SME"
             }
             //Se crean las notificaciones de recomendación para los contactos.
             def message=[type:"", addressees:[], title:"", info:""]
             message.type=QueueService.NOTIFICATION
             //El feed de la propia empresa recibe la notificación.
             message.addressees.add(sme1.cif)
             if(!rb1.visibility?.equals(Content.PRIVATE)){
               //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
               cl1.contacts.each{
                 if(it.state!=Contact.INACTIVE){
                   message.addressees.add(it.sme)
                 }
               }
               log.info "Notification to the contacts of $smeOrig"
             }
             message.title=Notification.NEWRECOMMENDATION
             message.info= "The company "+sme1.name+" recommends to  "+sme2.name+"."
             sendJMSMessage("neoQueue", message)
             message.addressees=[]
             if(rb1?.visibility.equals(Content.PRIVATE)){
               //El feed de la propia empresa recibe la notificación, si no se ha enviado ya.
              message.addressees.add(sme2.cif)
             }
             if(!rb2?.visibility.equals(Content.PRIVATE)){
               //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
               cl2.contacts.each{
                 if(!it.sme.equals(sme1.cif) && it.state!=Contact.INACTIVE){
                   message.addressees.add(it.sme)
                 }
               }
               log.info "Notification to the contacts of $smeDest"
             }
             sendJMSMessage("neoQueue", message)
           }else{
             log.warn "There is not contact $smeDest in contact list of sme $smeOrig."
             result=AppConstants.SME_ADDRECOMMENDATION_RESULT_NOCONTACT
           }
         }
       }
     }catch(Exception e){
         result=AppConstants.SME_ADDRECOMMENDATION_RESULT_ERROR
         log.error(e.message,e)
     }
     if(AppConstants.isResponseOk(result))
       result.sme=sme1
     result
   }

   //La empresa origen elimina la recomendación a la destino de su RecommendationBox
   def deleteRecommendation(String smeOrig, String smeDest){
     def result=AppConstants.SME_DELETERECOMMENDATION_RESULT_OK
     def sme1=null
     try{
       //Se comprueba que ambas empresas están registradas.
       sme1=Sme.getById(smeOrig)
       if(!sme1){
         log.warn "Could not find SME $smeOrig."
         result=AppConstants.SME_DELETERECOMMENDATION_RESULT_NOSMEORIG
       }else{
         Sme sme2=Sme.getById(smeDest)
         if(!sme2){
           log.warn "Could not find SME $smeDest."
           result=AppConstants.SME_DELETERECOMMENDATION_RESULT_NOSMEDEST
         }else{
           def cl1=sme1.returnContactList()
           def cl2=sme2.returnContactList()
           //Se resta valoración a la recomendada y se elimina referencia de la q recomendaba
           sme2.delVote()
           cl2.delRecommender(smeOrig)
           //Se elimina la recomendación para que no se muestre en web de la orig y se restaura el enlace a weak
           sme1.returnRecommendationBox()?.deleteRecommendation(smeDest)
           cl1.setWeak(smeDest)
           if (!sme1.save() || !sme2.save()) {
             sme1.errors.each {
               log.warn(it)
             }
             sme2.errors.each {
               log.warn(it)
             }
             result=AppConstants.SME_DELETERECOMMENDATION_RESULT_ERROR
             log.error "Error saving SME"
           }
         }
       }
     } catch(Exception e) {
       result=AppConstants.SME_DELETERECOMMENDATION_RESULT_ERROR
       log.error(e.message,e)
     }
     if(AppConstants.isResponseOk(result))
       result.sme=sme1
     result
   }

  //El PU modifica el profile de su empresa.
  def changeSmeProfile(Map smeBasicProfile, Map smeAdvancedProfile, Map smeContentProfile){
    log.debug "Llamando a changeSmeProfile"
    def result=AppConstants.SME_CHANGEPROFILE_RESULT_OK
    def sme1=null
    try {
      sme1=Sme.getById(smeBasicProfile.cif)
      if (!sme1) {
        log.warn "Could not find SME $smeBasicProfile.name."
        result=AppConstants.SME_CHANGEPROFILE_RESULT_ERROR
      } else {
        sme1.setProfile(smeBasicProfile, smeAdvancedProfile, smeContentProfile)
        if (!sme1.save()) {
          sme1.errors.each{
            log.warn(it)
          }
          result=AppConstants.SME_CHANGEPROFILE_RESULT_ERROR
          log.error "Error saving SME"
        } else {
          // Se envía notificación para feed
          def message=[type:"", addressees:[], title:"", info:""]
          message.type=QueueService.NOTIFICATION
          // El feed de la propia empresa recibe la notificación
          message.addressees.add(sme1.cif)
          if (!sme1.returnProfileData()?.visibility.equals(Content.PRIVATE)) {
            // Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
            sme1.returnContactList()?.contacts.each {
              if (it.state!=Contact.INACTIVE) {
                message.addressees.add(it.sme)
              }
            }
            log.info "Notification to the contacts of $sme1.cif"
          }
          message.title=Notification.SMEPROFILECHANGED
          message.info="The profile data of "+sme1.name+" has changed"
          sendJMSMessage("neoQueue", message)
        }
      }
    } catch (Throwable e) {
      result=AppConstants.SME_CHANGEPROFILE_RESULT_ERROR
      log.error(e.message,e)
    }
    if (AppConstants.isResponseOk(result)) {
      result.sme=sme1
    }
    result
  }

   //Permite establecer como Primary User de una SME pasada como argumento.
    def setPrimaryUser(String sme, String user){
      def result=AppConstants.SME_SETPU_RESULT_OK
      def sme1=null
      try{
        //Se comprueba que la empresa y el usuario están registrados
        sme1=Sme.getById(sme)
        if(!sme1){
          log.error "Could not find SME $sme."
          result=AppConstants.SME_SETPU_RESULT_NOSME
        }else{
          def pu=Employee.getById(user)
          if(!pu){
            log.error "Could not find employee $user."
            result=AppConstants.SME_SETPU_RESULT_NOUSER
          }else{
            if(pu.smes().contains(sme1)){
              sme1.setPU(user)
              log.info "Setting as PU ${user} in SME ${sme}"
            }else{
              log.error "There is no relation between employee $user & company $sme."
              result=AppConstants.SME_SETPU_RESULT_NOCONEXION
            }
          }
        }
      }catch(Exception e){
        result=AppConstants.SME_SETPU_RESULT_ERROR
        log.error(e.message,e)
      }
      if(AppConstants.isResponseOk(result)){
        result.sme=sme1
      }
      result
    }

  //Permite buscar empresas en la red.
  def search(String query, String town, String user){
    //query = query + "*"
    log.info "query: ${query}"
    def result=AppConstants.SME_SEARCH_RESULT_OK
    def data=[]
    def lista=[]
    try{
      //Se realiza la búsqueda y se trata el resultado.
      def options=[sort: "numRecommendations", order: "desc", suggestQuery: true]

      def ListaSmes=Sme.searchEvery(query, options)
      def ListaPds=ProfileData.searchEvery(query, options)
      def resultadosSmeMiCiudad=[]
      def resultadosSmeNoMiCiudad=[]
      def suggestion = null

      if((ListaSmes.size == 0)&&(ListaPds.size == 0)){
              def searchResult=searchableService.search(query,[suggestQuery: true])
              suggestion=searchResult.suggestedQuery
              if(suggestion){
                ListaSmes=Sme.searchEvery(suggestion,options)
                ListaPds=ProfileData.searchEvery(suggestion,options)
              }
      }
      result.suggestion=suggestion
      if((ListaSmes.size != 0)||(ListaPds.size != 0)){
      def sme=null
      ListaPds.each{
          sme=Sme.getById(it.sme.cif)
          if(sme){
            lista.add(sme.cif)
            if(sme.town == town)
              resultadosSmeMiCiudad.add(sme)
            else
              resultadosSmeNoMiCiudad.add(sme)
          }else
            result=AppConstants.SME_SEARCH_RESULT_ERROR
      }
      ListaSmes.each{
         if(!lista.contains(it.cif)){
         lista.add(it)
          if(it.town == town)
            resultadosSmeMiCiudad.add(it)
          else
            resultadosSmeNoMiCiudad.add(it)
        }
       }
      }
      resultadosSmeMiCiudad.each{
        data.add(it)
      }
      resultadosSmeNoMiCiudad.each{
        data.add(it)
      }

    if((ListaSmes.size == 0)&&(ListaPds.size == 0)) result=AppConstants.SME_SEARCH_RESULT_NORESULTS

    }catch(Exception e){
      result=AppConstants.SME_SEARCH_RESULT_ERROR
      log.error(e.message,e)
    }
    if(AppConstants.isResponseOk(result)){
      result.data=data
       //provisional para la vista...
      def employee=Employee.getById(user)
      result.employee=employee
    }
    result
  }

    //Permite buscar empresas en la red.
  def advancedsearch(String query, Map filter, String user) {
    log.info "Entro en advancedsearch $query, $filter, $user"
    def result = AppConstants.SME_SEARCH_RESULT_OK
    def advanceddata = []
    try {
      def options = [sort: "numRecommendations", order: "desc",suggestQuery: true]

       //Si no hay nombre de empresa quito este campo a la hora de buscar
      if ((query == null)&&((filter.name == "")||(filter.name == "All"))&&(filter.town != " ")) query = filter.town
      if ((query == null)&&((filter.name != "")||(filter.name != "All"))) query = " *" + filter.name + "* " + filter.town
      if ((query != null)&&((filter.name == "")||(filter.name == "All"))) query = query + " " +filter.town
      if ((query != null)&&((filter.name != "")||(filter.name != "All"))) query = query + " *" + filter.name + "* " + " " +filter.town
      if ((query == null)&&((filter.name == "")||(filter.name == "All"))&&(filter.town == " ")) query = "cadena vacia"
      

      //Se realiza la búsqueda y se trata el resultado.
      log.info "Busqueda: $query"
      def ListaSmes = Sme.searchEvery(query, options)
      def ListaPds = ProfileData.searchEvery(query, options)
      def resultados = []
      Sme sme = null
      if (ListaSmes.size == 0) {
        ListaPds.each {
          sme = Sme.getById(it.sme.cif)
          if (sme) {
              resultados.add(sme)
          } else
            result = AppConstants.SME_SEARCH_RESULT_ERROR
        }
      } else {
        ListaSmes.each {
            resultados.add(it)
        }
      }
      resultados.each {
        advanceddata.add(it)
      }
    if((ListaSmes.size == 0)&&(ListaPds.size == 0)) result=AppConstants.SME_SEARCH_RESULT_NORESULTS

    } catch (Exception e) {
      result = AppConstants.SME_SEARCH_RESULT_ERROR
      log.error(e.message, e)
    }
    if (AppConstants.isResponseOk(result)) {
      result.advanceddata = advanceddata
      //provisional para la vista...
      def employee = Employee.getById(user)
      result.employee = employee
    }
    result
  }

     //Permite buscar empresas en la red.
  def searchBlog(String query, String user,String search_title, String search_text) {
    log.info "Entro en searchblog $query, text: $search_text , title: $search_title"
    def options = []
    if ((search_title == null) && (search_text == null))  options = [properties: ["title"], suggestQuery: true]    
    if ((search_title == "yes") && (search_text == null))  options = [properties: ["title"], suggestQuery: true]
    if ((search_title == null) && (search_text == "yes"))  options = [properties: ["text"], suggestQuery: true]
    if ((search_title == "yes") && (search_text == "yes"))  options = [suggestQuery: true]

    def result = AppConstants.SME_SEARCHBLOG_RESULT_OK
    def blogdata = []
    def suggestion = null
    try {

      def postid = null
      //Se realiza la búsqueda y se trata el resultado.
      def ListaPosts = Post.searchEvery(query, options)
            if (ListaPosts.size == 0){
              def searchResult = searchableService.search(query,[suggestQuery: true])
              suggestion = searchResult.suggestedQuery
              if (suggestion){
              ListaPosts = Post.searchEvery(suggestion, options)
              }
             }


            if (ListaPosts.size != 0){
              ListaPosts.each {
                postid =it.id
                log.info "idpost ${postid}"
                blogdata.add(it)
              }
            }
    if (ListaPosts.size == 0) result=AppConstants.SME_SEARCHBLOG_RESULT_NORESULTS
    } catch (Exception e) {
      result = AppConstants.SME_SEARCHBLOG_RESULT_ERROR
      log.error(e.message, e)
    }
    if (AppConstants.isResponseOk(result)) {
      result.blogdata = blogdata
      result.suggestion = suggestion
       //provisional para la vista...
       def employee = Employee.getById(user)
      result.employee = employee
    }
    result
  }

  def searchMessage(String query, String user, String company,String search_subject, String search_text) {
    log.info "Entro en searchmessage $query , user: $user, sme:$company"
    // Message.findAllByMessageBoxAndTextLike("texto_a_buscar")
    def options = [:]
    def search_properties = []
    if ((search_text == null) && (search_text == null)) search_properties += 'title'
    if (search_subject == "true") search_properties += 'title'
    if (search_text == "true")  search_properties += 'text'
    options=[properties:search_properties,suggestQuery: true]
    def result = AppConstants.SME_SEARCHMESSAGE_RESULT_OK
    def messagedata = []
    def suggestion = null
    try {

       //Se realiza la búsqueda y se trata el resultado.
      def listaMessages = Message.searchEvery(query,options)
            if (listaMessages.size == 0){
              def searchResult = searchableService.search(query,[suggestQuery: true])
              suggestion = searchResult.suggestedQuery
              if (suggestion){
                listaMessages= Message.searchEvery(suggestion, options)
                result.suggestion = suggestion
              }
            }
      if (listaMessages.size!=0){
              listaMessages.each {
                messagedata.add(it)
            }
      }
    if (listaMessages.size == 0) result=AppConstants.SME_SEARCHMESSAGE_RESULT_NORESULTS
    } catch (Exception e) {
      result = AppConstants.SME_SEARCHMESSAGE_RESULT_ERROR
      log.error(e.message, e)
    }
    if (AppConstants.isResponseOk(result)) {
      result.messagedata = messagedata
      result.suggestion = suggestion
       //provisional para la vista...
       def employee = Employee.getById(user)
      result.employee = employee
    }
    result
  }



  //Envío de un mensaje personal.
  //addressees [sme1.cif:employee.nif, sme2.cif:employee.nif, ...]
  def sendMessage(String addressees, String user, String company, String title, String text){
    def result=AppConstants.EMP_SENDMESSAGE_RESULT_OK
    def employee=null
    def sme=null
    try {
      title=title?:"New message through NEO"
      // Se parsean los destinatarios
      Map addrs=[:]
      addressees.tokenize(';').each {
        def cif=it.tokenize(':')[0]
        def nifs=it.tokenize(':')[1]
        addrs.put(cif, nifs)
      }
      log.debug "Message sends to ${addrs}"
      // Se comprueba la existencia del usuario y la empresa emisora, y la relación entre ambos
      employee=Employee.getById(user)
      sme=Sme.getById(company)
      if (employee && sme && employee.smes().contains(sme)) {
        // Se comprueba la existencia de los destinatarios y se identifica su empresa
        def addresseesSme=addrs.keySet()
        def addresseesEmp=null
        def addSme=null
        def addEmp=null
        addresseesSme.each {
          addSme=Sme.getById(it)
          if (!addSme) {
            log.warn "Addressee SME ${it} doesn't exist"
          } else {
            addresseesEmp=(addrs[it]).split(",")
            addresseesEmp.each{ addressee ->
              addEmp=Employee.getById(addressee)
              if (!addEmp || !addEmp.smes().contains(addSme)) {
//              if (!addEmp) {
                log.warn "Addressee user ${addressee} doesn't exist or there is no conexion with SME: ${it}"
              } else {
                addSme.returnMessageBox()?.addMessage(addressee, employee.nif, sme.cif, title, text)
              }
            }
            if (!addSme.save()) {
              addSme.errors.each{error->
                log.warn(error)
              }
              result=AppConstants.EMP_SENDMESSAGE_RESULT_ERROR
              log.error "Error saving SME"
            }
          }
        }
      } else {
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.EMP_SENDMESSAGE_RESULT_NOSENDER
      }
    } catch(Exception e) {
       log.error(e.message)
       result=AppConstants.EMP_SENDMESSAGE_RESULT_ERROR
    }
    if (AppConstants.isResponseOk(result)) {
      result.sme=sme
      result.employee=employee
    }
    result
  }

  //El usuario responde a un mensaje recibido
  def answerMessage(String id, String user, String company, String title, String text){
    def result=AppConstants.EMP_ANSWERMESSAGE_RESULT_OK
    def employee=null
    try{
      // Se recupera la información del mensaje original
      def message=Message.get(id)
      if (message) {
        if (!title) {
          title="Response: ${message.title}"
        }
        //Se comprueba la existencia del usuario y la empresa emisoras, y la propiedad del mensaje.
        employee=Employee.getById(user)
        def sme=Sme.getById(company)
        if (employee && sme && employee.smes().contains(sme)) {
          if (employee.nif.equals(message.addressee)) {
            //Se comprueba la existencia del destinatario y su empresa. Serán los remitentes del mensaje original.
            def addresseeSme=Sme.getById(message.senderSme)
            def addresseeEmp=Employee.getById(message.senderUser)
//            if (addresseeSme && addresseeEmp && addresseeEmp.smes().contains(addresseeSme)) {
            if (addresseeSme && addresseeEmp) {
              addresseeSme.returnMessageBox()?.addMessage(addresseeEmp.nif, employee.name, sme.name, title, text)
              //Se almacenan los mensajes enviados en BD.
              if(!addresseeSme.save()){
                addresseeSme.errors.each{
                  log.warn(it)
                }
                result=AppConstants.EMP_ANSWERMESSAGE_RESULT_ERROR
                log.error "Error saving SME"
              }
            }else{
              log.error "The addressee SME or user doesn't exist or there is no conexion between them."
              result=AppConstants.EMP_ANSWERMESSAGE_RESULT_NOADDRESSEE
            }
          }else{
            log.error "Only the addresse of a message can answer it."
            result=AppConstants.EMP_ANSWERMESSAGE_RESULT_NOCONEXION          
          }
        }else{
          log.error "The sender sme or user doesn't exist or there is no conexion between them."
          result=AppConstants.EMP_ANSWERMESSAGE_RESULT_NOSENDER
        }
      }else{
        log.error "The message doesn't exist."
        result=AppConstants.EMP_ANSWERMESSAGE_RESULT_NOMSG
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.EMP_ANSWERMESSAGE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.employee=employee
    }
    result
  }

  //El usuario elimina un mensaje de su buzón personal
  def deleteMessage(String idMsg, String user, String company){
    def result=AppConstants.EMP_DELETEMESSAGE_RESULT_OK
    def employee=null
    try{
      //Se comprueba la existencia del usuario, la empresa y la propiedad del mensaje
      employee=Employee.getById(user)
      def sme=Sme.getById(company)
      if(employee && sme && employee.smes().contains(sme)){
        if(!sme.returnMessageBox()?.deleteMessage(idMsg, user)){
          log.warn "Only the owner of a message can delete it. "
          result=AppConstants.EMP_DELETEMESSAGE_RESULT_NOMSG          
        }
        if(!sme.save()){
          sme.errors.each{
            log.warn(it)
          }
          result=AppConstants.EMP_DELETEMESSAGE_RESULT_ERROR
          log.error "Error saving SME"
        }
      }else{
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.EMP_DELETEMESSAGE_RESULT_NOSENDER
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.EMP_DELETEMESSAGE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.employee=employee
    }
    result
  }

  //El usuario elimina una notificación del feed de su empresa
  def deleteNotification(String idNotif, String company){
    def result=AppConstants.SME_DELETENOTIFICATION_RESULT_OK
    def sme=null
    try{
      //Se comprueba la existencia de la empresa y la propiedad de la notificación
      sme=Sme.getById(company)
      if(sme){
        if(!sme.returnFeed()?.deleteNotification(idNotif)){
          log.warn "Only the owner of a feed can delete it's notifications. "
          result=AppConstants.SME_DELETENOTIFICATION_RESULT_NONOTIF
        }else{
          if(!sme.save()){
            sme.errors.each{
              log.warn(it)
            }
            result=AppConstants.SME_DELETENOTIFICATION_RESULT_ERROR
            log.error "Error saving SME"
          }
        }
      }else{
        log.error "Sme doesn't exist."
        result=AppConstants.SME_DELETENOTIFICATION_RESULT_NOSME
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.SME_DELETENOTIFICATION_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=sme
    }
    result
  }

  //El usuario pone un anuncio en el tablón de sus contactos
  def addNotice(String company, String user, String title, String text){
    def result=AppConstants.SME_ADDNOTICE_RESULT_OK
    def employee=null
    def sme=null
    try{
      //Se comprueba la existencia del usuario y la empresa
      employee=Employee.getById(user)
      sme=Sme.getById(company)
      if(employee && sme && employee.smes().contains(sme)){
        //Se crea el mensaje para la distribución de los anuncios.
        NoticeBoard nb=sme.returnNoticeBoard()
        nb.addNotice(sme.cif, title, text)
        if (!sme.save()) {
          log.error "Error saving NoticeBoard to SME ${sme.name}"
          sme.errors.each { log.error(it) }
          result=AppConstants.SME_ADDNOTICE_RESULT_ERROR
        } else {
          def message=[type:"", addressees:[], title:"", info:"", sender:""]
//        El tablón de anuncios de la propia empresa recibe el anuncio.
//        message.addressees.add(sme.cif)
          if (nb && !nb.visibility.equals(Content.PRIVATE)) {
            //Se envía anuncio también al tablón de los contactos, para visibility PUBLIC o CONTACT
            sme.returnContactList()?.contacts.each{
              if(it.state!=Contact.INACTIVE){
                message.addressees.add(it.sme)
              }
            }
          }
          message.type=QueueService.NOTICE
          message.title=title
          message.info=text
          message.sender=sme.cif
          log.info "Notice to the contacts of: $company"
          sendJMSMessage("neoQueue", message)
        }
      }else{
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.SME_ADDNOTICE_RESULT_NOSENDER
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.SME_ADDNOTICE_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=sme
      result.employee=employee
    }
    result
  }

  //El usuario borra un anuncio del tablón de su empresa
   def deleteNotice(String company, String idNotice){
     def result=AppConstants.SME_DELETENOTICE_RESULT_OK
     def sme=null
     try{
       //Se comprueba la existencia del usuario, la empresa y la propiedad del anuncio
       sme=Sme.getById(company)
       if(sme){
         if(!sme.returnNoticeBoard()?.deleteNotice(idNotice)){
           log.warn "Only the owner of a notice can delete it. "
           result=AppConstants.SME_DELETENOTICE_RESULT_NONOTICE
         }
         if(!sme.save()){
           sme.errors.each{
             log.warn(it)
           }
           result=AppConstants.SME_DELETENOTICE_RESULT_ERROR
           log.error "Error saving SME"
         }
       }else{
         log.error "Sme doesn't exist."
         result=AppConstants.SME_DELETENOTICE_RESULT_NOSENDER
       }
     }catch(Exception e){
       log.error(e.message)
       result=AppConstants.SME_DELETENOTICE_RESULT_ERROR
     }
     if(AppConstants.isResponseOk(result)){
       result.sme=sme
     }
     result
  }

  //El administrador pone un anuncio
  def publicizeOperatorInfo(String user, String title, String text, String companies){
    def result=AppConstants.SME_PUBLICIZEOPERATORINFO_RESULT_OK
    def admin=null
    try{
      //Se comprueba la existencia del usuario administrador
      admin=Administrator.getById(user)
      if(admin){
        //Se envía mensaje para la publicación del anuncio.
        def message=[type:"", addressees:[], title:"", info:"", sender:""]
        message.type=QueueService.OPERATORINFO
        //Si no se indican empresas destinatarias, se envía a todas.
        if(companies){
          def addressees=companies.split(",")
          addressees.each{
            message.addressees.add(it)
          }          
        }
        message.title=title
        message.info=text
        sendJMSMessage("neoQueue", message)
      }else{
        log.error "Administrator $user doesn't exist."
        result=AppConstants.SME_PUBLICIZEOPERATORINFO_RESULT_NOSENDER
      }
    }catch(Exception e){
      log.error(e.message)
      result=AppConstants.SME_PUBLICIZEOPERATORINFO_RESULT_ERROR
    }
    if(AppConstants.isResponseOk(result)){
      result.admin=admin
    }
    result
  }

  //Cualquier usuario puede publicar un post en el blog de su empresa.
  def addPost(String senderCompany, String senderUser, String title, String text){
    def result=AppConstants.SME_ADDPOST_RESULT_OK
    def sme=null
    def employee=null
    try{
      //Se comprueba la existencia del usuario y la empresa
      employee=Employee.getById(senderUser)
      sme=Sme.getById(senderCompany)
      if (employee && sme && employee.smes().contains(sme)) {
        def blog=sme.returnBlog()
        blog?.addPost(employee.name, title, text)
        if(!sme.save()){
          sme.errors.each{
            log.warn(it)
          }
          result=AppConstants.SME_ADDPOST_RESULT_ERROR
          log.error "Error saving SME"
        }
        //Se crean las notificaciones.
        def message=[type:"", addressees:[], title:"", info:""]
        message.type=QueueService.NOTIFICATION
        //El feed de la propia empresa recibe la notificación.
        message.addressees.add(sme.cif)
        if(!blog.visibility.equals(Content.PRIVATE)){
          //Se envía notificación también al feed de los contactos para visibility PUBLIC o CONTACT
          sme.returnContactList()?.contacts.each{
            if(it.state!=Contact.INACTIVE){
              message.addressees.add(it.sme)
            }
          }
          log.info "Notification to the contacts of $senderCompany"
        }
        message.title=Notification.NEWPOST
        message.info= "New blog post from user "+senderUser+" of company  "+senderCompany+": "+title+"."
        sendJMSMessage("neoQueue", message)
      }else{
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.SME_ADDPOST_RESULT_NOSENDER
      }
    }catch(Exception e){
        result=AppConstants.SME_ADDPOST_RESULT_ERROR
        log.error(e.message,e)
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=sme
      result.employee=employee
    }
    result
  }

  //El autor de un post y el PU de la empresa podrán modificarlo.
  def modifyPost(String senderCompany, String senderUser, String idPost, String title, String text){
    def result=AppConstants.SME_MODIFYPOST_RESULT_OK
    def sme=null
    def employee=null
    try{
      //Se comprueba la existencia del usuario, la empresa y la relación entre ambos.
      employee=Employee.getById(senderUser)
      sme=Sme.getById(senderCompany)
      if(employee && sme && employee.smes().contains(sme)){
        log.info "Sme desde la que se pide el cambio ${sme.cif}"
        if(!sme.returnBlog()?.modifyPost(idPost, senderUser, title, text)){
           log.warn "Only the owner of a post or the PU of the company can modify it. "
           result=AppConstants.SME_MODIFYPOST_RESULT_NOPOST
        }else{
           if(!sme.save()){
             sme.errors.each{
               log.warn(it)
             }
             result=AppConstants.SME_MODIFYPOST_RESULT_ERROR
             log.error "Error saving SME"
           }
        }
      }else{
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.SME_MODIFYPOST_RESULT_NOSENDER
      }
    }catch(Exception e){
        result=AppConstants.SME_MODIFYPOST_RESULT_ERROR
        log.error(e.message,e)
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=sme
      result.employee=employee
    }
    result
  }

  //El autor de un post y el PU de la empresa podrán modificarlo.
  def deletePost(String senderCompany, String senderUser, String idPost){
    def result=AppConstants.SME_DELETEPOST_RESULT_OK
    def sme=null
    def employee=null
    try{
      //Se comprueba la existencia del usuario, la empresa y la relación entre ambos.
      employee=Employee.getById(senderUser)
      sme=Sme.getById(senderCompany)
      if(employee && sme && employee.smes().contains(sme)){
        if(!sme.returnBlog()?.deletePost(idPost, senderUser)){
           log.warn "Only the owner of a post or the PU of the company can delete it. "
           result=AppConstants.SME_DELETEPOST_RESULT_NOPOST
        }else{
           if(!sme.save()){
             sme.errors.each{
               log.warn(it)
             }
             result=AppConstants.SME_DELETEPOST_RESULT_ERROR
             log.error "Error saving SME"
           }
        }
      }else{
        log.error "User or Sme doesn't exist or there is no conexion between them."
        result=AppConstants.SME_DELETEPOST_RESULT_NOSENDER
      }
    }catch(Exception e){
        result=AppConstants.SME_DELETEPOST_RESULT_ERROR
        log.error(e.message,e)
    }
    if(AppConstants.isResponseOk(result)){
      result.sme=sme
      result.employee=employee
    }
    result
  }

}
