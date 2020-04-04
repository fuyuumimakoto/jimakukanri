package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import pojo.Forusm;
import pojo.Forusm_info;

public class ForusmPageLoader {
	private static SessionFactory factory; 
	public List<Forusm_info> Forusm_infoListLoader(String url)
	{
	

		  ObjectInputStream ois=null;
			try {
				ois = new ObjectInputStream(new FileInputStream(url));
				// 读取对象
				// Student stu = (Student)ois.readObject();
				// System.out.println("读取到的数据为:"+stu);
				@SuppressWarnings("unchecked")
				ArrayList<Forusm_info> arrayList = (ArrayList<Forusm_info>) ois.readObject();
				return arrayList;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		

		  
		return null;
	}
	
}
