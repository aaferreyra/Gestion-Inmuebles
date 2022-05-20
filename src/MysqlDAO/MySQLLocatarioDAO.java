package MysqlDAO;
import java.util.List;

import DAO.LocatarioDAO;
import modelo.Locatario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLLocatarioDAO implements LocatarioDAO{
    private final SessionFactory sessionFactory;

    public MySQLLocatarioDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    @Override
    public void insertar(Locatario a) {
       try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(a);
            session.getTransaction().commit();
            session.close();
            System.out.println("Exito");
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println("Fallo");
        }
    }

    @Override
    public void modificar(Locatario a) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(a);
            session.getTransaction().commit();
            session.close();
            System.out.println("Exito");
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println("Fallo");
        }	
    }

    @Override
    public void eliminar(Locatario a) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(a);
            session.getTransaction().commit();
            session.close();
            System.out.println("Exito");
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println("Fallo");
        }
    }

    @Override
    public List<Locatario> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Locatario> lista = null;  
        try 
        {   
            lista = session.createQuery("from Locatario").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return lista; 
    }

    @Override
    public Locatario obtener(Long id) {
       Locatario retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Locatario.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

	

}
