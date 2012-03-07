class Recommendation {

  String recommendedSme
  String description
  Date dateCreated

  static belongsTo = [recommendationBox: RecommendationBox]

  static constraints = {
    description(nullable:true, size:1..1000)
  }

  static mapping = {
    table 'Recommendations'
  }

  String toString() {
    "Recommendation to SME ${recommendedSme}"
  }

}
