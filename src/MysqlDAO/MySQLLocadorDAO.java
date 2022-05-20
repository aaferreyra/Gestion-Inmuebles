package MysqlDAO;
import java.util.List;

import DAO.LocadorDAO;
import modelo.Locador;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLLocadorDAO implements LocadorDAO {

	private final SessionFactory sessionFactory;

    public MySQLLocadorDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void insertar(Locador a) {
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
	public void modificar(Locador a) {
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
	public void eliminar(Locador a) {
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
	public List<Locador> obtenerTodos() {
		Session session = sessionFactory.openSession();
                List<Locador> lista = null;  
                try 
                {   
                    lista = session.createQuery("from Locador").list(); 
                } finally 
                { 
                    session.close(); 
                }  
                return lista; 
	}

    @Override
    public Locador obtener(Long id) {
        Locador retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Locador.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }
}
