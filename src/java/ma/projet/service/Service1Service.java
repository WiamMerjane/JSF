package ma.projet.service;

import java.util.List;
import ma.projet.beans.Service1;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Lenovo
 */
public class Service1Service implements IDao<Service1>{
    @Override
    public boolean create(Service1 o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Service1 o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Service1 o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service1 getById(int id) {
        Service1 employe  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe  = (Service1) session.get(Service1.class, id);
        session.getTransaction().commit();
        return employe;
    }
    
    public Service1 getById(Long id) {
        Service1 employe  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe  = (Service1) session.get(Service1.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Service1> getAll() {
        List <Service1> employes = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("from Service1").list();
        session.getTransaction().commit();
        return employes;
    }
    
    public List<Service1> getAllServices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Service1> services = session.createQuery("from Service1").list();
        session.getTransaction().commit();
        return services;
    }
    
    
}