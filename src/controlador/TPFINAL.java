package controlador;
import controlador.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vista.*;

/**
 *
 * @author Aaron
 */
public class TPFINAL {
 private static Session session; 
    public static void main(String[] args) throws Exception {
       SessionFactory sessionFactory = conectar();
       session = sessionFactory.openSession();
       Controlador controlador=new Controlador(sessionFactory);
       
       VentanaPrincipal v= new VentanaPrincipal(controlador);
       v.setVisible(true);
    }
    
      public static void desconectar() {
            try {
                if (session != null) {
                    if (session.isConnected()) {
                        session.disconnect();
                    }

                    if (session.isOpen()) {
                        session.close();
                        
                    }
                }
            } catch (HibernateException e) {
                System.out.println(e);
                System.out.println("Fallo");
            }
        }
    public static SessionFactory conectar(){
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
				.build();
		try {
	            sessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory(registry);
                    System.out.println("configuracion exitosa");
	            //sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
	            System.out.println("Error al crear factory: " + e.getMessage());

	            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
	            // so destroy it manually.
	            StandardServiceRegistryBuilder.destroy( registry );
		}
       return sessionFactory;
    }
}
