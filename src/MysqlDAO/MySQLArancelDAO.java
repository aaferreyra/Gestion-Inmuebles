package MysqlDAO;
import java.util.List;

import DAO.ArancelDAO;
import modelo.Arancel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLArancelDAO implements ArancelDAO {
private final SessionFactory sessionFactory;

    public MySQLArancelDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    @Override
    public void insertar(Arancel a) {
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
    public void modificar(Arancel a) {
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
    public void eliminar(Arancel a) {
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
    public List<Arancel> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Arancel> listaArancel = null;  
        try 
        {   
            listaArancel = session.createQuery("from Arancel").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return listaArancel; 
    }

    @Override
    public Arancel obtener(Long id) {
       Arancel retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Arancel.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

	

}
