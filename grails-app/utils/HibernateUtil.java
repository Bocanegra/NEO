/**
 * Created by IntelliJ IDEA.
 * User: valle
 * Date: 30-jun-2009
 * Time: 14:45:57
 */

import org.hibernate.*;
public class HibernateUtil{

  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory(){
    return sessionFactory;
  }

  public static void setSessionFactory(SessionFactory sf){
    sessionFactory=sf;
  }

  public static void shutdown(){
    sessionFactory.close();
  }

}
