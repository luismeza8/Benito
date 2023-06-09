package mx.itson.benito.utilerias;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * La utilidad de Hibernate
 *
 * @author lm
 */
public class HibernateUtil {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            factory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println("No se pudo crear la sesión de Hibernate debido al error: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void setSessionFactory(SessionFactory factory) {
        HibernateUtil.factory = factory;
    }

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        HibernateUtil.serviceRegistry = serviceRegistry;
    }
}
