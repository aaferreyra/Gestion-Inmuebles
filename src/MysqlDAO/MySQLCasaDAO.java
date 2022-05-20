package MysqlDAO;
import java.util.List;

import DAO.CasaDAO;
import modelo.Casa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLCasaDAO implements CasaDAO{
private final SessionFactory sessionFactory;

    public MySQLCasaDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    @Override
    public void insertar(Casa a) {
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
    public void modificar(Casa a) {
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
    public void eliminar(Casa a) {
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
    public List<Casa> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Casa> lista = null;  
        try 
        {   
            lista = session.createQuery("from Casa").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return lista; 
    }

    @Override
    public Casa obtener(Long id) {
       Casa retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Casa.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

}
