import org.codehaus.groovy.grails.commons.ConfigurationHolder

class CleaningJob {

  static triggers = {
    cronExpression: '0 0 2 * * ?'
  }

  def execute() {

    // 1. Limpieza de feeds que lleven más tiempo del indicado
    log.info "Starting CleningJob of Notifications..."
    def iniTime=new Date()
    def nDays=Integer.parseInt(ConfigurationHolder.config.neo.cleaning.feeds.days)
    def toDelete=Notification.findAllByDateCreatedLessThan(new Date()-nDays)
    toDelete.each {
      it.delete()
    }
    log.info "${toDelete.size()} Notifications deleted in ${new Date()-iniTime} miliseconds"
    
  }
}
