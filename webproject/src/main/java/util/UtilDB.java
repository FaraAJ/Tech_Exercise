/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Employee> listEmployees() {
      List<Employee> resultList = new ArrayList<Employee>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Employee").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Employee employee = (Employee) iterator.next();
            resultList.add(employee);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Employee> listEmployees(String keyword) {
      List<Employee> resultList = new ArrayList<Employee>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM Employee").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            Employee employee = (Employee) iterator.next();
            if (employee.getName().startsWith(keyword)) {
               resultList.add(employee);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createEmployees(String name, String email, String phone, String address) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Employee(name, email, phone, address));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   public static void deleteEmployeeById(int id) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         Employee employee = (Employee) session.get(Employee.class, id);
	         if (employee != null) {
	            session.delete(employee);
	            tx.commit();
	         } else {
	            System.out.println("Employee with ID " + id + " not found.");
	         }
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
   }


   public static void clearEmployees() {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   try {
		   tx = session.beginTransaction();
		   session.createQuery("DELETE FROM Employee").executeUpdate();
		   tx.commit();
	   		} catch (HibernateException e) {
	   			if (tx != null)
	   				tx.rollback();
	   			e.printStackTrace();
	   		} finally {
	   			session.close();
	   	}
   }
   
   public static void resetEmployeeIdCounter() {
       Session session = getSessionFactory().openSession();
       Transaction tx = null;
       try {
           tx = session.beginTransaction();
           String sql = "ALTER TABLE employee AUTO_INCREMENT = 1";
           session.createSQLQuery(sql).executeUpdate();
           tx.commit();
       } catch (HibernateException e) {
           if (tx != null)
               tx.rollback();
           e.printStackTrace();
       } finally {
           session.close();
       }
   }

   
   
}
