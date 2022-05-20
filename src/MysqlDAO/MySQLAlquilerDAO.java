package MysqlDAO;
import java.util.List;

import DAO.AlquilerDAO;
import modelo.Alquiler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLAlquilerDAO implements AlquilerDAO{
private final SessionFactory sessionFactory;

    public MySQLAlquilerDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
	@Override
	public void insertar(Alquiler a) {
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
	public void modificar(Alquiler a) {
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
	public void eliminar(Alquiler a) {
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
	public List<Alquiler> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alquiler obtener(Long id) {
		Alquiler retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Alquiler.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
	}

}
