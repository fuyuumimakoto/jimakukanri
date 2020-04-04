package logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.Employee;
import pojo.Forusm;
import pojo.Forusm_info;

public class ForusmPoster {
	private static SessionFactory factory; 
	public int CreateForusm(Forusm forusm)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 Transaction  tx = null ;
		 int  forusmid=0;
		 
		 Session session = factory.openSession();
		 try{
			   tx = session.beginTransaction();
	         
	       forusmid = (Integer) session.save(forusm); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return forusmid;
	}
	
	
	public void reForusm(Forusm_info forusm_info)
	{
		
		
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 Transaction  tx = null ;
	
		 
		 Session session = factory.openSession();
		 try{
			   tx = session.beginTransaction();
	         
	       session.save(forusm_info); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	     
	}
	
}
