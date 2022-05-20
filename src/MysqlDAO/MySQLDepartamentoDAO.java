package MysqlDAO;
import java.util.List;

import DAO.DepartamentoDAO;
import modelo.Departamento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLDepartamentoDAO implements DepartamentoDAO {
private final SessionFactory sessionFactory;

    public MySQLDepartamentoDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    @Override
    public void insertar(Departamento a) {
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
    public void modificar(Departamento a) {
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
    public void eliminar(Departamento a) {
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
    public List<Departamento> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Departamento> lista = null;  
        try 
        {   
            lista = session.createQuery("from Departamento").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return lista; 
    }

    @Override
    public Departamento obtener(Long id) {
        Departamento retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Departamento.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

	

}
