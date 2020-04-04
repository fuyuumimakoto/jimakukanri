package logic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import pojo.Block;
import pojo.Forusm;

public class BlockPageLoader {
	private static SessionFactory factory; 
	public List<Forusm> ForusmListLoader(int bid)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 //获取所有Forusm对象
			 Criteria cr = session.createCriteria(Forusm.class); 
			
			 cr.add(Restrictions.eq("bid", bid));   
	    	   List results = cr.list();
	    
	    	   
	    	   
	    	 if(!results.isEmpty()){
	            return results;
	         } 
	         
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
}
