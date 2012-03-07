class RecommendationBox extends Content {

  static hasMany = [recommendations: Recommendation]

  static mapping = {
    table 'RecommendationBoxes'
  }

  static constraints = {
  }

  String toString() {
    "RecommendationBox with ${recommendations?.size()} recommendations"
  }

  // Se añade una recomendación para mostrarla en la web de la propia empresa
  def addRecommendation(String recommendedSme, String description=null) {
    def recommendation=null
    recommendations.each {
      if (it.recommendedSme.equals(recommendedSme)) {
        recommendation=it
      }
    }
    if (!recommendation) {
      this.addToRecommendations(new Recommendation(recommendedSme:recommendedSme, description:description))
    } else {
      // Si ya existe la recomendación, se sustituye la descripción
      log.info "Recommendation to ${recommendedSme} already found, description replaced"
      recommendation.description=description
    }
  }

  // Se elimina una recomendación existente
  def deleteRecommendation(String recommendedSme){
    def recommendation=null
    recommendations.each{
      if(it.recommendedSme.equals(recommendedSme)){
        recommendation=it
      }
    }
    if(!recommendation){
      log.info "Recommendation to ${recommendedSme} not found."
    }else{
      this.removeFromRecommendations(recommendation)
      recommendation.delete()
      log.info "Deleted recommendation to ${recommendedSme}"
    }
  }

  def returnLastRecommendations(int nmax, int noffset) {
    Recommendation.findAllByRecommendationBox(this, [max:nmax, offset:noffset, sort:'dateCreated', order:'desc'])
  }

  def returnRecommendationsFromLastLogout(Date logout) {
    Recommendation.findAllByRecommendationBoxAndDateCreatedGreaterThan(this, logout)
  }

}
