class AppConstants {

  // Constantes utilizadas para retornar las respuestas al FE.

  static final Map GLOBAL_ERROR_PARAMS = [code: "000001", info: "global.error.params"]
  static final Map GLOBAL_ERROR_COOKIE = [code: "000002", info: "global.error.cookie"]

  static final Map INVITATION_INVITE_RESULT_OK = [code: "001000", info: "invitation.invite.ok"]
  static final Map INVITATION_INVITE_RESULT_NOMAIL = [code: "001001", info: "invitation.invite.nomail"]
  static final Map INVITATION_INVITE_RESULT_ERROR = [code: "001002", info: "invitation.invite.error"]

  static final Map INVITATION_VALIDATE_RESULT_OK = [code: "002000", info: "invitation.validate.ok"]
  static final Map INVITATION_VALIDATE_RESULT_NODATA = [code: "002001", info: "invitation.validate.nodata"]
  static final Map INVITATION_VALIDATE_RESULT_SMEEXISTS = [code: "002002", info: "invitation.validate.smeexists"]
  static final Map INVITATION_VALIDATE_RESULT_FRAUD = [code: "002003", info: "invitation.validate.fraud"]
  static final Map INVITATION_VALIDATE_RESULT_ERROR = [code: "002004", info: "invitation.validate.error"]

  static final Map INVITATION_DELETE_RESULT_OK = [code: "022000", info: "invitation.delete.ok"]
  static final Map INVITATION_DELETE_RESULT_ERROR = [code: "022001", info: "invitation.delete.error"]

  static final Map SME_CREATE_RESULT_OK = [code: "003000", info: "sme.create.ok"]
  static final Map SME_CREATE_RESULT_ERROR_PU_DATA = [code: "003001", info: "sme.create.error.pu.data"]
  static final Map SME_CREATE_RESULT_ERROR_SME_DATA = [code: "003002", info: "sme.create.error.sme.data"]
  static final Map SME_CREATE_RESULT_ERROR_NOASSOCIATE = [code: "003003", info: "sme.create.error.noassociate"]
  static final Map SME_CREATE_RESULT_ERROR = [code: "003004", info: "sme.create.error"]

  static final Map SME_CHANGEPROFILE_RESULT_OK = [code: "004000", info: "sme.changeprofile.ok"]
  static final Map SME_CHANGEPROFILE_RESULT_ERROR = [code: "004001", info: "sme.changeprofile.error"]

  static final Map SME_SETPU_RESULT_OK = [code: "005000", info: "sme.setpu.ok"]
  static final Map SME_SETPU_RESULT_NOSME = [code: "005001", info: "sme.setpu.nosme"]
  static final Map SME_SETPU_RESULT_NOUSER = [code: "005002", info: "sme.setpu.nouser"]
  static final Map SME_SETPU_RESULT_NOCONEXION = [code: "005003", info: "sme.setpu.noconexion"]
  static final Map SME_SETPU_RESULT_ERROR = [code: "005004", info: "sme.setpu.error"]

  static final Map SME_GETCONTACTLIST_RESULT_OK = [code: "006000", info: "sme.getcontactlist.ok"]
  static final Map SME_GETCONTACTLIST_RESULT_ERROR = [code: "006001", info: "sme.getcontactlist.error"]

  static final Map SME_REQUESTCONTACT_RESULT_OK = [code: "007000", info: "sme.requestcontact.ok"]
  static final Map SME_REQUESTCONTACT_RESULT_NOSMEORIG = [code: "007001", info: "sme.requestcontact.nosmeorig"]
  static final Map SME_REQUESTCONTACT_RESULT_NOSMEDEST = [code: "007002", info: "sme.requestcontact.nosmedest"]
  static final Map SME_REQUESTCONTACT_RESULT_ERROR = [code: "007003", info: "sme.requestcontact.error"]

  static final Map SME_LISTPENDINGREQUEST_RESULT_OK = [code: "008000", info: "sme.listpendingrequest.ok"]
  static final Map SME_LISTPENDINGREQUEST_RESULT_NODATA = [code: "008001", info: "sme.listpendingrequest.nodata"]
  static final Map SME_LISTPENDINGREQUEST_RESULT_ERROR = [code: "008002", info: "sme.listpendingrequest.error"]

  static final Map SME_ACCEPTCONTACT_RESULT_OK = [code: "009000", info: "sme.acceptcontact.ok"]
  static final Map SME_ACCEPTCONTACT_RESULT_NOSMEORIG = [code: "009001", info: "sme.acceptcontact.nosmeorig"]
  static final Map SME_ACCEPTCONTACT_RESULT_NOSMEDEST = [code: "009002", info: "sme.acceptcontact.nosmedest"]
  static final Map SME_ACCEPTCONTACT_RESULT_NOREQUEST = [code: "009003", info: "sme.acceptcontact.norequest"]
  static final Map SME_ACCEPTCONTACT_RESULT_ERROR = [code: "009004", info: "sme.acceptcontact.error"]

  static final Map SME_GETFEED_RESULT_OK = [code: "010000", info: "sme.getfeed.ok"]
  static final Map SME_GETFEED_RESULT_ERROR = [code: "010001", info: "sme.getfeed.error"]

  static final Map EMP_CHANGEPROFILE_RESULT_OK = [code: "011000", info: "emp.changeprofile.ok"]
  static final Map EMP_CHANGEPROFILE_RESULT_INVALID = [code: "011001", info: "emp.changeprofile.invalid"]
  static final Map EMP_CHANGEPROFILE_RESULT_ERROR = [code: "011002", info: "emp.changeprofile.error"]

  static final Map EMP_LOGIN_RESULT_OK = [code: "012000", info: "emp.login.ok"]
  static final Map EMP_LOGIN_RESULT_INVALID = [code: "012001", info: "emp.login.invalid"]
  static final Map EMP_LOGIN_RESULT_NOEXISTS = [code: "012002", info: "emp.login.noexists"]
  static final Map EMP_LOGIN_RESULT_PASSWORD_FAIL = [code: "012003", info: "emp.login.password.fail"]
  static final Map EMP_LOGIN_RESULT_ERROR = [code: "012004", info: "emp.login.error"]

  static final Map EMP_DELETE_RESULT_OK = [code: "013000", info: "emp.delete.ok"]
  static final Map EMP_DELETE_RESULT_NOUSER = [code: "013001", info: "emp.delete.nouser"]
  static final Map EMP_DELETE_RESULT_NOSME = [code: "013002", info: "emp.delete.nosme"]
  static final Map EMP_DELETE_RESULT_NOADVICE = [code: "013003", info: "emp.delete.noadvice"]
  static final Map EMP_DELETE_RESULT_ERROR = [code: "013004", info: "emp.delete.error"]

  static final Map EMP_LOGOUT_RESULT_OK = [code: "014000", info: "emp.logout.ok"]
  static final Map EMP_LOGOUT_RESULT_NOEXISTS = [code: "014001", info: "emp.logout.noexists"]
  static final Map EMP_LOGOUT_RESULT_ERROR = [code: "014002", info: "emp.logout.error"]

  static final Map SU_CREATE_RESULT_OK = [code: "015000", info: "su.create.ok"]
  static final Map SU_CREATE_RESULT_NODATA = [code: "015001", info: "su.create.nodata"]
  static final Map SU_CREATE_RESULT_NOADVICE = [code: "015002", info: "su.create.noadvice"]
  static final Map SU_CREATE_RESULT_NOSME = [code: "015003", info: "su.create.nosme"]
  static final Map SU_CREATE_RESULT_ERROR_USER_DATA = [code: "015004", info: "su.create.error.user.data"]
  static final Map SU_CREATE_RESULT_ERROR_SME_DATA = [code: "015005", info: "su.create.error.sme.data"]
  static final Map SU_CREATE_RESULT_ERROR_NOASSOCIATE = [code: "015005", info: "su.create.error.noassociate"]
  static final Map SU_CREATE_RESULT_ERROR = [code: "015007", info: "su.create.error"]

  static final Map ADM_LOGIN_RESULT_OK = [code: "016000", info: "adm.login.ok"]
  static final Map ADM_LOGIN_RESULT_NOEXISTS = [code: "016001", info: "adm.login.noexists"]
  static final Map ADM_LOGIN_RESULT_PASSWORD_FAIL = [code: "016002", info: "adm.login.password.fail"]
  static final Map ADM_LOGIN_RESULT_ERROR = [code: "016003", info: "adm.login.error"]

  static final Map SME_REJECTCONTACT_RESULT_OK = [code: "017000", info: "sme.rejectcontact.ok"]
  static final Map SME_REJECTCONTACT_RESULT_NOSMEORIG = [code: "017001", info: "sme.rejectcontact.nosmeorig"]
  static final Map SME_REJECTCONTACT_RESULT_NOSMEDEST = [code: "017002", info: "sme.rejectcontact.nosmedest"]
  static final Map SME_REJECTCONTACT_RESULT_NOREQUEST = [code: "017003", info: "sme.rejectcontact.norequest"]
  static final Map SME_REJECTCONTACT_RESULT_ERROR = [code: "017004", info: "sme.rejectcontact.error"]

  static final Map SME_DELETECONTACT_RESULT_OK = [code: "018000", info: "sme.deletecontact.ok"]
  static final Map SME_DELETECONTACT_RESULT_NOSMEORIG = [code: "018001", info: "sme.deletecontact.nosmeorig"]
  static final Map SME_DELETECONTACT_RESULT_NOSMEDEST = [code: "018002", info: "sme.deletecontact.nosmedest"]
  static final Map SME_DELETECONTACT_RESULT_NOCONTACT = [code: "018003", info: "sme.deletecontact.nocontact"]
  static final Map SME_DELETECONTACT_RESULT_ERROR = [code: "018004", info: "sme.deletecontact.error"]

  static final Map SME_ADDRECOMMENDATION_RESULT_OK = [code: "019000", info: "sme.addrecommendation.ok"]
  static final Map SME_ADDRECOMMENDATION_RESULT_NOSMEORIG = [code: "019001", info: "sme.addrecommendation.nosmeorig"]
  static final Map SME_ADDRECOMMENDATION_RESULT_NOSMEDEST = [code: "019002", info: "sme.addrecommendation.nosmedest"]
  static final Map SME_ADDRECOMMENDATION_RESULT_NOCONTACT = [code: "019003", info: "sme.addrecommendation.nocontact"]
  static final Map SME_ADDRECOMMENDATION_RESULT_ERROR = [code: "019004", info: "sme.addrecommendation.error"]

  static final Map SME_DELETERECOMMENDATION_RESULT_OK = [code: "020000", info: "sme.deleterecommendation.ok"]
  static final Map SME_DELETERECOMMENDATION_RESULT_NOSMEORIG = [code: "020001", info: "sme.deleterecommendation.nosmeorig"]
  static final Map SME_DELETERECOMMENDATION_RESULT_NOSMEDEST = [code: "020002", info: "sme.deleterecommendation.nosmedest"]
  static final Map SME_DELETERECOMMENDATION_RESULT_ERROR = [code: "020003", info: "sme.deleterecommendation.error"]

  static final Map SME_RENDERCONTENT_RESULT_OK = [code: "021000", info: "sme.rendercontent.ok"]
  static final Map SME_RENDERCONTENT_RESULT_NOSME = [code: "021001", info: "sme.rendercontent.nosme"]
  static final Map SME_RENDERCONTENT_RESULT_NOUSER = [code: "021002", info: "sme.rendercontent.nouser"]
  static final Map SME_RENDERCONTENT_RESULT_ERROR = [code: "021003", info: "sme.rendercontent.error"]

  static final Map ADM_LOGOUT_RESULT_OK = [code: "022000", info: "adm.logout.ok"]
  static final Map ADM_LOGOUT_RESULT_NOUSER = [code: "022001", info: "adm.logout.nouser"]
  static final Map ADM_LOGOUT_RESULT_ERROR = [code: "022002", info: "adm.logout.error"]

  static final Map SME_SEARCH_RESULT_OK = [code: "023000", info: "sme.search.ok"]
  static final Map SME_SEARCH_RESULT_NORESULTS = [code: "023001", info: "sme.search.noresults"]
  static final Map SME_SEARCH_RESULT_ERROR = [code: "023002", info: "sme.search.error"]

  static final Map EMP_SENDMESSAGE_RESULT_OK = [code: "024000", info: "emp.sendmessage.ok"]
  static final Map EMP_SENDMESSAGE_RESULT_NOSENDER = [code: "024001", info: "emp.sendmessage.nosender"]
  static final Map EMP_SENDMESSAGE_RESULT_ERROR = [code: "024002", info: "emp.sendmessage.error"]

  static final Map EMP_ANSWERMESSAGE_RESULT_OK = [code: "025000", info: "emp.answermessage.ok"]
  static final Map EMP_ANSWERMESSAGE_RESULT_NOSENDER = [code: "025001", info: "emp.answermessage.nosender"]
  static final Map EMP_ANSWERMESSAGE_RESULT_NOADDRESSEE = [code: "025002", info: "emp.answermessage.noaddressee"]
  static final Map EMP_ANSWERMESSAGE_RESULT_NOCONEXION = [code: "025003", info: "emp.answermessage.noconexion"]
  static final Map EMP_ANSWERMESSAGE_RESULT_NOMSG = [code: "025004", info: "emp.answermessage.nomsg"]
  static final Map EMP_ANSWERMESSAGE_RESULT_ERROR = [code: "025005", info: "emp.answermessage.error"]

  static final Map EMP_DELETEMESSAGE_RESULT_OK = [code: "026000", info: "emp.deletemessage.ok"]
  static final Map EMP_DELETEMESSAGE_RESULT_NOMSG = [code: "026001", info: "emp.deletemessage.nomsg"]
  static final Map EMP_DELETEMESSAGE_RESULT_NOSENDER = [code: "026002", info: "emp.deletemessage.nosender"]
  static final Map EMP_DELETEMESSAGE_RESULT_ERROR = [code: "026003", info: "emp.deletemessage.error"]

  static final Map SME_DELETENOTIFICATION_RESULT_OK = [code: "027000", info: "sme.delenotification.ok"]
  static final Map SME_DELETENOTIFICATION_RESULT_NOSME = [code: "027001", info: "sme.delenotification.nosme"]
  static final Map SME_DELETENOTIFICATION_RESULT_NONOTIF = [code: "027002", info: "sme.delenotification.nonotif"]
  static final Map SME_DELETENOTIFICATION_RESULT_ERROR = [code: "027003", info: "sme.deletenotification.error"]

  static final Map SME_ADDNOTICE_RESULT_OK = [code: "028000", info: "sme.addnotice.ok"]
  static final Map SME_ADDNOTICE_RESULT_NOSENDER = [code: "028001", info: "sme.addnotice.nosender"]
  static final Map SME_ADDNOTICE_RESULT_ERROR = [code: "028002", info: "sme.addnotice.error"]

  static final Map SME_DELETENOTICE_RESULT_OK = [code: "029000", info: "sme.deletenotice.ok"]
  static final Map SME_DELETENOTICE_RESULT_NOSENDER = [code: "029001", info: "sme.deletenotice.nosender"]
  static final Map SME_DELETENOTICE_RESULT_NONOTICE = [code: "029002", info: "sme.deletenotice.nonotice"]
  static final Map SME_DELETENOTICE_RESULT_ERROR = [code: "029003", info: "sme.deletenotice.error"]

  static final Map SME_PUBLICIZEOPERATORINFO_RESULT_OK = [code: "030000", info: "sme.publicizeoperatorinfo.ok"]
  static final Map SME_PUBLICIZEOPERATORINFO_RESULT_NOSENDER = [code: "030001", info: "sme.publicizeoperatorinfo.nosender"]
  static final Map SME_PUBLICIZEOPERATORINFO_RESULT_ERROR = [code: "030002", info: "sme.publicizeoperatorinfo.error"]

  static final Map SME_ADDPOST_RESULT_OK = [code: "031000", info: "sme.addpost.ok"]
  static final Map SME_ADDPOST_RESULT_NOSENDER = [code: "031001", info: "sme.addpost.nosender"]
  static final Map SME_ADDPOST_RESULT_ERROR = [code: "031002", info: "sme.addpost.error"]

  static final Map SME_MODIFYPOST_RESULT_OK = [code: "032000", info: "sme.modifypost.ok"]
  static final Map SME_MODIFYPOST_RESULT_NOSENDER = [code: "032001", info: "sme.modifypost.nosender"]
  static final Map SME_MODIFYPOST_RESULT_NOPOST = [code: "032002", info: "sme.modifypost.nopost"]
  static final Map SME_MODIFYPOST_RESULT_ERROR = [code: "032003", info: "sme.modifypost.error"]

  static final Map SME_DELETEPOST_RESULT_OK = [code: "033000", info: "sme.deletepost.ok"]
  static final Map SME_DELETEPOST_RESULT_NOSENDER = [code: "033001", info: "sme.deletepost.nosender"]
  static final Map SME_DELETEPOST_RESULT_NOPOST = [code: "033002", info: "sme.deletepost.nopost"]
  static final Map SME_DELETEPOST_RESULT_ERROR = [code: "033003", info: "sme.deletepost.error"]

  static final Map SME_SHOW_RSS_OK = [code: "034000", info: "sme.show.rss.ok"]
  static final Map SME_SHOWS_RSS_ERROR = [code: "034001", info: "sme.show.rss.error"]

  static final Map SME_SEARCHBLOG_RESULT_OK = [code: "035000", info: "sme.searchblog.ok"]
  static final Map SME_SEARCHBLOG_RESULT_NORESULTS = [code: "035001", info: "sme.searchblog.noresults"]
  static final Map SME_SEARCHBLOG_RESULT_ERROR = [code: "035002", info: "sme.searchblog.error"]

  static final Map SME_SEARCHMESSAGE_RESULT_OK = [code: "036000", info: "sme.searchmessage.ok"]
  static final Map SME_SEARCHMESSAGE_RESULT_NORESULTS = [code: "036001", info: "sme.searchmessage.noresults"]
  static final Map SME_SEARCHMESSAGE_RESULT_ERROR = [code: "036002", info: "sme.searchmessage.error"]

  static boolean isResponseOk(Map response) {
    response.code.endsWith('0')
  }

}