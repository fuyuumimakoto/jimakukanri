package logic;

import java.io.File;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.multipart.MultipartFile;

import pojo.Employee;
import pojo.User;
import pojo.User_info;
import staticvar.FileVar;

public class UserOP {
	private static SessionFactory factory; 
	
	
	
	
	
	
	public void UpdataUser_info_DB(User_info user_info)
	{
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();

	         session.update(user_info); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public void UpdataUser_info_Upicon( MultipartFile file,int uid) throws IllegalStateException, IOException
	{
		if (!file.getOriginalFilename().equals("")) {
   		 String path = FileVar.Absolute_headurl+"//user//"+uid;
   	        String fileName = file.getOriginalFilename();    
   	        File dir = new File(path,fileName);          
   	        if (!dir.getParentFile().exists()) {
   	            boolean result = dir.getParentFile().mkdirs();
   		  }
   	 
   	        file.transferTo(dir);  
	}
	

	
	
}
	
	public int role_cheak(int[] cheaks )
	{
		//1 2 3
		//1+2=4 1+3=5 2+3=6
		if(cheaks.length==1)
		{
			return cheaks[0];
		}
		if(cheaks.length==2)
		{
			int total=cheaks[0]+cheaks[1];
			return total+1;
		}
		return 7;
	}
}