package logic;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import pojo.Block;
import pojo.Forusm;
import pojo.Group;
import pojo.Team;
import pojo.User;
import pojo.User_info;

public class pojo_getter {
	private static SessionFactory factory; 
	public Block BlockGetter(int bid)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 //获取指定block
			 Block block=(Block) session.get(Block.class, bid);
	         return block;
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
	
	public int md5id2id(String md5id)
	{
int id = 0;
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		


		
		 Session session = factory.openSession();

	     
	     try{
	    	 Criteria cr = session.createCriteria(User.class);   
	    	   cr.add(Restrictions.eq("md5id", md5id));   
	    	   List results = cr.list();
	    	 if(results.isEmpty()){
	            
	         } else {
	        	 //将得到的账号与输入的密码匹配
	        	
	            id=(int) ((User) results.get(0)).getId();
	        	
	         }
	         
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
	     
	 
		//返回用户id，若不匹配返回0
		return id;
	}
	
	public Team TeamGetter(int Tid)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 //获取指定block
			Team team=(Team) session.get(Team.class, Tid);
	         return team;
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
	public User_info User_infoGetter(int uid)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 //获取指定block
			User_info user_info=(User_info) session.get(User_info.class, uid);
	         return user_info;
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
	
	public List<User_info> user_infosGetter(int[] uids)
	{
		ArrayList< User_info> user_infos=new ArrayList<>();
		for(int i=0;i<uids.length;i++)
		{
			user_infos.add(User_infoGetter(uids[i]));
		}
		return user_infos;
	}
}
