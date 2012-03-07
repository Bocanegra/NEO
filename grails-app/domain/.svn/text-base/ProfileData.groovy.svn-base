class ProfileData extends Content {

  String taxCode
  String corporateName=""
  String metaTags
  Date dateCreated
  Date lastUpdated

  static constraints = {
    taxCode(nullable:true)
    corporateName(nullable:true)
    metaTags(nullable:true)
  }

  static mapping = {
    table 'ProfileDatas'
  }

  static searchable = {
    sme(component: true)
    metaTags: spellCheck 'include'
  }

  String toString() {
    "ProfileData: $corporateName; tags: [$metaTags]"
  }

}