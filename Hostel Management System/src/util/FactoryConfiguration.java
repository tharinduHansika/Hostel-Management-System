package util;

import dto.StudentDTO;
import entity.Reservartion;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        // configure() -> load and config Hibernate.cfg.xml file to SessionFactory
        // addAnnotatedClass() -> define which Entity that gonna use to Persist
        //xml Configuration
        Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservartion.class);

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Reservartion.class);


        //property file configuration
        //Configuration configuration = new Configuration();
        /*Properties properties =new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Hibernate.properties"));*/
        //configuration.setProperties(properties);

        /*Configuration configuration = new Configuration().setProperties(properties).addAnnotatedClass(Student.class).
                addAnnotatedClass(Reservartion.class).addAnnotatedClass(Room.class);*/



        // build a SessionFactory and assign it to sessionFactory reference
        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    // return a new Session through sessionFactory
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
