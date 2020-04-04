package test;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.Md5Crypt;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import logic.TeamPageLoader;
import logic.pojo_getter;
import pojo.Block;
import pojo.Chat_log;
import pojo.Forusm;
import pojo.Group;
import pojo.Team;
import pojo.User_info;
import pojo.task;

public class test {
	

public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
  
	pojo_getter pojo_getter =new pojo_getter();
	int i= pojo_getter.md5id2id("abwawxwqsxa7wphwrsyrr7qbqp7aqhqq");
	int j=0;
	//b59c67bf196a4758191e42f76670ceba
	//b59c67bz196a4758191e42z76670ceba
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
	  
	    /**@param source 需要加密的字符串 
	     * @param hashType  加密类型 （MD5 和 SHA） 
	     * @return 
	     */  
	    public static String getHash2(String source, String hashType) {  
	        StringBuilder sb = new StringBuilder();  
	        MessageDigest md5;  
	        try {  
	            md5 = MessageDigest.getInstance(hashType);  
	            md5.update(source.getBytes());  
	            for (byte b : md5.digest()) {  
	                sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出  
	            }  
	            return sb.toString();  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	  
	    /**@param source 需要加密的字符串 
	     * @param hashType  加密类型 （MD5 和 SHA） 
	     * @return 
	     */  
	    public static String getHash3(String source, String hashType) { 
		
	        // 用来将字节转换成 16 进制表示的字符  
	        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
	          
	        StringBuilder sb = new StringBuilder();  
	        MessageDigest md5;  
	        try {  
	            md5 = MessageDigest.getInstance(hashType);  
	            md5.update(source.getBytes());  
	            byte[] encryptStr = md5.digest();  
	            for (int i = 0; i < encryptStr.length; i++) {  
	                int iRet = encryptStr[i];  
	                if (iRet < 0) {  
	                    iRet += 256;  
	                }  
	                int iD1 = iRet / 16;  
	                int iD2 = iRet % 16;  
	                sb.append(hexDigits[iD1] + "" + hexDigits[iD2]);  
	            }  
	            return sb.toString();  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }



		
	
	




	private static SessionFactory factory; 
	public static Block BlockGetter(int bid)
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 //鑾峰彇鎸囧畾block
			 Block block=(Block) session.get(Block.class, bid);
	         return block;
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
	public static Group aa()
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 
			 Group group=(Group) session.get(Group.class, 1);
	         return group;
	         
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
	
	public static Team bb()
	{
		 try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		 
		 
		 Session session = factory.openSession();
		 try{
			 
			Team group=(Team) session.get(Team.class, 1);
	         return group;
	         
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
	     
		 
	}
}
