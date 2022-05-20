package MysqlDAO;
import java.util.List;

import DAO.RelacionDeDependenciaDAO;
import modelo.Relaciondedependencia;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLRelacionDeDependenciaDAO implements RelacionDeDependenciaDAO{

    private final SessionFactory sessionFactory;

    public MySQLRelacionDeDependenciaDAO(SessionFactory sessionFactory) {
         this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertar(Relaciondedependencia a) {
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
    public void modificar(Relaciondedependencia a) {
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
    public void eliminar(Relaciondedependencia a) {
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
    public List<Relaciondedependencia> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Relaciondedependencia> lista = null;  
        try 
        {   
            lista = session.createQuery("from Relaciondedependencia").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return lista; 
    }

    @Override
    public Relaciondedependencia obtener(Long id) {
        Relaciondedependencia retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Relaciondedependencia.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

	

}
