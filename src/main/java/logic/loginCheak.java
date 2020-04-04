package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;

import pojo.User;

public class loginCheak {  
	private static SessionFactory factory; 
public int CheakPass(User user)
{
	int id=0;
	 try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
	
	
	User cheak_user = new User();

	
	 Session session = factory.openSession();

     
     try{
    	 Criteria cr = session.createCriteria(User.class);   
    	   cr.add(Restrictions.eq("mail", user.getMail()));   
    	   List results = cr.list();
    	 if(results.isEmpty()){
            
         } else {
        	 //将得到的账号与输入的密码匹配
        	 cheak_user=(User) results.get(0);
        	
        	 if(cheak_user.getPassword().equals(getHash(user.getPassword(), "MD5")))
            id=cheak_user.getId();
        	
         }
         
      
     }catch (HibernateException e) {
       
        e.printStackTrace(); 
     }finally {
        session.close(); 
     }
     
 
	//返回用户id，若不匹配返回0
	return id;
	
	
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


}
