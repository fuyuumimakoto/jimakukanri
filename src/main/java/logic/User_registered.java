package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import pojo.Chat_log;
import pojo.User;
import pojo.User_info;
import pojo.chat_log_file;
import staticvar.FileVar;
public class User_registered {
	private static SessionFactory factory; 
	public boolean registered(User user)
	{
		int uid= registeredUser(user);
		md5_encryption md5_encryption=new md5_encryption();
		String MD5pass;
		String MD5uid;
		MD5pass= md5_encryption.getHash(user.getPassword());
		MD5uid=md5_encryption.getHash(uid+"");
		user.setPassword(MD5pass);
		user.setMd5id(MD5uid);
		UpdataUser_DB(user);



		user.setId(uid);
		if(registeredUser_info(user))
		{
			return true;
		}

		

		
		return false;
	}
	 public static String getHash(String source, String hashType) {
			
	        // 用来将字节转换成 16 进制表示的字符  
	        char hexDigits[] = {'w', 'q', 'r', 'w', '2', 'b', 's', 'j', 'y', '7', 'x', 'a', 'p', 'q', 'h', 'i'};  
	  
	        try {  
	            MessageDigest md = MessageDigest.getInstance(hashType);  
	            md.update(source.getBytes()); // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要  
	            byte[] encryptStr = md.digest(); // 获得密文完成哈希计算,产生128 位的长整数  
	            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符  
	            int k = 0; // 表示转换结果中对应的字符位置  
	            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换  
	                byte byte0 = encryptStr[i]; // 取第 i 个字节  
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移  
	                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换  
	            }  
	            return new String(str); // 换后的结果转换为字符串  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	
	public boolean IsRegistered(User user)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		 
		 Session session = factory.openSession();
		 
		 try{
	    	 Criteria cr = session.createCriteria(User.class);  
	    	 System.out.print("\n resgitster mail:"+user.getMail()+"\n");
	    	   cr.add(Restrictions.eq("mail", user.getMail()));   
	    	   List results = cr.list();
	    	   System.out.print("\n list size:"+results.size());
	    	 if(results.isEmpty()){
	            return false;
	         } 
	    	 else {
	        		return true;
	         }
		
	    		
		
	}
		 
	 catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return true;
	     
	}
	
	
	private boolean registeredUser_info(User user)
	{
		User_info user_info=new User_info();
		user_info.setId(user.getId());
		user_info.setMail(user.getMail());
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
	       session.save(user_info);
	        tx.commit(); 
	    createTeamList(user_info.getId());//create teamlist file
	    createChatlog(user_info.getId());//create chatlog file
	     	return true;
	     }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	     }finally {
	    	 
	        session.close(); 
	   
	     }
		
		return false;
		
	}
	
	private void set_admin_welcome_meg(int uid){
		
	}
	
	private void createChatlog(int uid)
	{
		set_admin_welcome_meg(uid);
		ObjectOutputStream oos=null;

		String url =FileVar.Absolute_headurl+"/user/"+uid+"/chatlog.data";
		int[] init= {1};//admin
		Chat_log chat_log=new Chat_log(1,"welcome");
		
			ArrayList<Chat_log> first = new ArrayList<>();//create chatlog list obj
			first.add(chat_log);
			ArrayList<ArrayList<Chat_log>> list = new ArrayList<>();//create chatlog list list
			list.add(first);
			chat_log_file chat_log_file=new chat_log_file();//create chatlog file obj to save to file
			chat_log_file.setChatuid(init);
			chat_log_file.setChatloglist(list);
		
		  try {
			
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(chat_log_file);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	private void createTeamList(int uid)
	{
		ObjectOutputStream oos=null;

		String url =FileVar.Absolute_headurl+"/user/"+uid+"/teamlist.data";
		int[] init= {0};
		  try {
			
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(init);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	
	
	private int registeredUser(User user)
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
	    	//寮�鍚簨鍔�  
	   tx = session.beginTransaction(); 
	        int uid= (Integer) session.save(user);
	        tx.commit(); 
	    
	     	return uid;
	     }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	     }finally {
	    	 
	        session.close(); 
	   
	     }
		
		return 0;
		
	}
	
	public void UpdataUser_DB(User user)
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

	         session.update(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
}
