package MysqlDAO;
import java.util.List;

import DAO.VentaDAO;
import modelo.Venta;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLVentaDAO implements VentaDAO{
private final SessionFactory sessionFactory;

    public MySQLVentaDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    @Override
    public void insertar(Venta a) {
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
    public void modificar(Venta a) {
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
    public void eliminar(Venta a) {
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
    public List<Venta> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Venta> lista = null;  
        try 
        {   
            lista = session.createQuery("from Venta").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return lista;     }

    @Override
    public Venta obtener(Long id) {
        Venta retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Venta.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }


}
