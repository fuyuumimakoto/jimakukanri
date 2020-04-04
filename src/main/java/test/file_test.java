package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import logic.TeamPageLoader;
import logic.pojo_getter;
import pojo.Chat_log;
import pojo.Forusm_info;
import pojo.Team;
import pojo.User;
import pojo.chat_log_file;
import pojo.member;
import pojo.task_info;
import staticvar.FileVar;

public class file_test {
	  public static void main(String[] args) {
String url="D://Dev_Soft//tomcat//data"+"//user//1//chatlog.data";
ObjectOutputStream oos=null;

int[] init= {1};//admin
Chat_log chat_log=new Chat_log(1,"welcome");

	ArrayList<Chat_log> first = new ArrayList<>();//create chatlog list obj
	first.add(chat_log);
	ArrayList<ArrayList<Chat_log>> list = new ArrayList<>();//create chatlog list list
	list.add(first);
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
	  
	 private void add(int a)
	 {
		 a++;
	 }
	  
	  public static void filesave(List<task_info> task_info,String url,int a)
	  {
		  ObjectOutputStream oos=null;

		  try {
			
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(task_info);
	
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
	  
	  
	  
		 public static List<Forusm_info> fileread(String url) {
			  

			  ObjectInputStream ois=null;
				try {
					ois = new ObjectInputStream(new FileInputStream(url));
					// 璇诲彇瀵硅薄
					// Student stu = (Student)ois.readObject();
					// System.out.println("璇诲彇鍒扮殑鏁版嵁涓�:"+stu);
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
	  public static void filesave(List<Forusm_info> forusm_infos,String url)
	  {
		  ObjectOutputStream oos=null;

		  try {
			
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(forusm_infos);
	
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
	  
	  public static void filesave(List<member> member)
	  {
		  ObjectOutputStream oos=null;
		
		  try {
			  File f = new File("D://Dev_Soft//tomcat//data"+"//team//1//memberlist.data");
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(member);
	
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
	  public static List<member>  fileread() {
		  
		  String file="D://Dev_Soft//tomcat//data"+"//team//1//memberlist.data";
		  ObjectInputStream ois=null;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				// 璇诲彇瀵硅薄
				// Student stu = (Student)ois.readObject();
				// System.out.println("璇诲彇鍒扮殑鏁版嵁涓�:"+stu);
				@SuppressWarnings("unchecked")
				List<member>  a = (List<member> ) ois.readObject();
				return a;
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
