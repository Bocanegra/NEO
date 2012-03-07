public class RssReader {

  static Map parseRss(Map feeds) {
    def response=AppConstants.SME_SHOW_RSS_OK
    response.data=[:]
    def type="RSS"
    feeds.each { rss ->
      def feedList=[]
      def title
      try {
        def feed=new XmlParser().parse(rss.value)
        (0..<feed.channel.item.size()).each {
          feedList+=(groovy.util.Node)(feed.channel.item.get(it));
        }
        title=feed.channel.title.text()
        // Si no hemos leído ningún channel, tal vez sea Atom, lo intentamos
        if (!feedList) {
          type="Atom"
          (0..<feed.entry.size()).each {
            feedList+=(groovy.util.Node)(feed.entry.get(it));
          }
          title=feed.title.text()
        }
        response.data.put(title, feedList)
      } catch (Exception e) {
        System.out.println("Exception parsing RSS ${rss.value}"+e)
      }
    }
    response
  }

}