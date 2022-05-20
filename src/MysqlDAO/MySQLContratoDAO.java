package MysqlDAO;
import java.util.List;

import DAO.ContratoDAO;
import modelo.Contrato;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
public class MySQLContratoDAO implements ContratoDAO{
    
    private SessionFactory sessionFactory;
    public MySQLContratoDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void insertar(Contrato a) {
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
    public void modificar(Contrato a) {
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
    public void eliminar(Contrato a) {
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
    public List<Contrato> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Contrato> listaContrato = null;  
        try 
        {   
            listaContrato = session.createQuery("from Contrato").list(); 
        } finally 
        { 
            session.close(); 
        }  
        return listaContrato; 
    }

    @Override
    public Contrato obtener(Long id) {
        Contrato retorno = null;
            try {
                Session session = sessionFactory.openSession();
                System.out.println("Exito");
                retorno = session.get(Contrato.class, id);
            } catch (HibernateException hibernateException) {
                System.out.println(hibernateException);
                System.out.println("Fallo");
                
            }
            return retorno;
    }

	

}
