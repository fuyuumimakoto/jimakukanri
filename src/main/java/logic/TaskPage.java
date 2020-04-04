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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.multipart.MultipartFile;

import pojo.Block;
import pojo.Forusm_info;
import pojo.User;
import pojo.User_info;
import pojo.member;
import pojo.task;
import pojo.task_info;
import staticvar.FileVar;

public class TaskPage {
	private static SessionFactory factory; 
	
	
	public String[] merbernames(List<member> members  )
	{
		pojo_getter pojo_getter=new pojo_getter();
		if(members!=null)
		{String[] names=new String[members.size()];
		for(int i=0;i<members.size();i++)
		{
			names[i]=pojo_getter.User_infoGetter(members.get(i).getUid()).getName();
		}
		
		return names;
		}
		return null;
	}
	
	
	public boolean taskCreate(task task)
	{
		
		int taskid= tasksave(task);
		task.setId(taskid);
		filesave(task);
		return false;
		
	}
	
	public String[] nameGetByids(int[]  ids)
	{
		String[] names=new String[ids.length];
		pojo_getter pojo_getter=new pojo_getter();
		for(int i=0;i<ids.length;i++)
		{if(ids[i]!=0)
			{names[i]=pojo_getter.User_infoGetter(ids[i]).getName();}
		else {
			names[i]="";
		}
		}
		return names;
	}
	
	public void FileUp( MultipartFile file,int taskid,int tid) throws IllegalStateException, IOException
	{

   		 String path = FileVar.Absolute_headurl+"//team//"+tid+"//task//"+taskid;
   	        String fileName = file.getOriginalFilename();    
   	        File dir = new File(path,fileName);          
   	        if (!dir.getParentFile().exists()) {
   	            boolean result = dir.getParentFile().mkdirs();
   		  }
   	 
   	        file.transferTo(dir);  

	

	
	
}
	
	public task_info task_infoGetter(int tid,int taskid)
	{
		task_info task_info=null;


		  ObjectInputStream ois=null;
String url=FileVar.Absolute_headurl+"//team//"+tid+"//task//"+taskid+".data";

try {
	
	ois = new ObjectInputStream(new FileInputStream(url));
   
	   @SuppressWarnings("unchecked")
		ArrayList<task_info> arrayList = (ArrayList<task_info>) ois.readObject();
		task_info=arrayList.get(0);

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


        
		
		return task_info;
		
	}
	
	private boolean cheakrole(task_info task_info,int newrole,int uid)
	{
		List<member> members=task_info.getMembers();
		if(members!=null)
		for(int i=0;i<members.size();i++)
		{
			if(uid==members.get(i).getUid())
			{
				return true;
			}
	}
		return false;
	}
	
	private int rolechange(int role,int newrole)
	{
		//role change rule 1fan 2jian 3zhou 4jiao
		//5(12) 6(13) 7(14) 8(23) 9(24) 10(34)
		//11(123) 12(124) 13(134) 14(234) 15(1234)
		if(newrole==1)
		{
			if(role==2)
			{
				role= 5;
			}
			if(role==3)
			{
				role= 6;
			}
			if(role==4)
			{
				role=7;
			}
			if(role==8)
			{
				role=11;
			}
			if(role==9)
			{
				role=12;
			}
			if(role==10)
			{
				role=13;
			}
			if(role==14)
			{
				role=15;
			}
		}
		
		if (newrole==2)
		{
			if(role==1)
			{
				role= 5;
			}
			if(role==3)
			{
				role= 8;
			}
			if(role==4)
			{
				role=9;
			}
			if(role==6)
			{
				role=11;
			}
			if(role==7)
			{
				role=12;
			}
			if(role==10)
			{
				role=14;
			}
			if(role==13)
			{
				role=15;
			}
		}
		
		if (newrole==3)
		{
			if(role==1)
			{
				role=6;
			}
			if(role==2)
			{
				role= 8;
			}
			if(role==4)
			{
				role= 10;
			}
			if(role==5)
			{
				role=11;
			}
			if(role==7)
			{
				role=13;
			}
			if(role==9)
			{
				role=14;
			}
			if(role==12)
			{
				role=15;
			}
		}
	
		if (newrole==4)
		{
			if(role==1)
			{
				role= 7;
			}
			if(role==2)
			{
				role= 9;
			}
			if(role==3)
			{
				role= 10;
			}
			if(role==5)
			{
				role=12;
			}
			if(role==6)
			{
				role=13;
			}
			if(role==8)
			{
				role=14;
			}
			if(role==11)
			{
				role=15;
			}
		}
	
		
		
		
		return role;
		
	}
	
	private void changeMembers(List<member> members,int uid,int newrole)
	{
		for(int i=0;i<members.size();i++)
		{
			if(uid==members.get(i).getUid())
			{
				int role=members.get(i).getRole();
		
				members.get(i).setRole(rolechange(role, newrole));
			}
			
			
		}
	}
	
	public void taskjoin(task_info task_info,int newrole,int uid)
	{
		 member merber=new member();		 
		
		 if(cheakrole(task_info, newrole, uid))
		 {
			 changeMembers(task_info.getMembers(), uid, newrole);
			 resave(task_info);
		 }
		 else {
			
			 merber.setRole(newrole);
			 merber.setUid(uid);
			 if(task_info.getMembers()!=null)
			 {task_info.getMembers().add(merber);}
			 else
			 {
				 ArrayList<member> arrayList = new ArrayList<member>();
				 arrayList.add(merber);
				 task_info.setMembers(arrayList);
			 }
			 resave(task_info);
		}
	}
	
	
	public void resave(task_info task_info)
	{
		 ObjectOutputStream oos=null;
		 String url=FileVar.Absolute_headurl+"//team//"+task_info.getTid()+"//task//"+task_info.getId()+".data";
		 ArrayList<task_info> arrayList = new ArrayList<task_info>();
		 arrayList.add(task_info);
		  try {
				
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(arrayList);
	
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
	  
	  public static void filesave(task task)
	  {
		  ObjectOutputStream oos=null;
String url=FileVar.Absolute_headurl+"//team//"+task.getTid()+"//task//"+task.getId()+".data";

int[] staff_id=new int[task.getPart()*3] ;
String[] files_name = new String[task.getPart()*3];
for(int i=0;i<task.getPart()*3;i++)
{
	staff_id[i]=0;
	files_name[i]="";
}

task_info task_info=new task_info();
task_info.setCreatedate(task.getCreatedate());
task_info.setCreateuid(task.getCreateuid());
task_info.setId(task.getId());
task_info.setName(task.getName());
task_info.setTid(task.getTid());
task_info.setIsdone(0);
task_info.setUrl(task.getUrl());
task_info.setMembers(null);
task_info.setPart(task.getPart());
task_info.setTime(task.getTime());
task_info.setFiles_name(files_name);
task_info.setStaff_id(staff_id);

ArrayList<task_info> arrayList = new ArrayList<task_info>();
arrayList.add(task_info);

		  try {
			
			  File f = new File(url);
			  
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(arrayList);
	
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
	
	public int  tasksave(task task)
	{
		int taskid=0;
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
	     
	       taskid = (Integer) session.save(task); 
	        tx.commit(); 
	  
	   
	     }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	     }finally {
	    	 
	        session.close(); 
	   
	     }
		return taskid;
	  
	}
	
	public List<User_info> worker_listGetter(int tid)
	{
		TeamPageLoader teamPageLoader=new TeamPageLoader();
		List<member> members=teamPageLoader.TeamMemberGetter(tid);
		int[] id_list=new int[members.size()-1];
		for(int i=0;i<id_list.length;i++)
		{
			id_list[i]=members.get(i+1).getUid();
		}
		
		pojo_getter pojo_getter=new pojo_getter();
		List<User_info> user_infos=pojo_getter.user_infosGetter(id_list);

		return user_infos;
	}
	
	
	public String[][] worker_nameGetter(int[][] worker_id_list)
	{
	
		String[][] worker_name_list=new String[3][];
		worker_name_list[0]=nameGetByids(worker_id_list[0]);
		worker_name_list[1]=nameGetByids(worker_id_list[1]);
		worker_name_list[2]=nameGetByids(worker_id_list[2]);
	

		return worker_name_list;
		
	}
	public int[][] worker_idGetter(List<User_info> user_infos)
	{
		ArrayList<Integer> zhou=new ArrayList<>();
		ArrayList<Integer> fan=new ArrayList<>();
		ArrayList<Integer> jiao=new ArrayList<>();
		for(int i=0;i<user_infos.size();i++)
		{
			int role=user_infos.get(i).getRole();
			int id=user_infos.get(i).getId();
			if(role==1||role==4||role==5||role==7)
			{
				zhou.add(id);
			}
			if(role==2||role==4||role==6||role==7)
			{
				fan.add(id);
			}
			if(role==3||role==5||role==6||role==7)
			{
				jiao.add(id);
			}
		}
		int[][] worker_id_list=new int[3][];
		worker_id_list[0]=new int[zhou.size()];
		worker_id_list[1]=new int[fan.size()];
		worker_id_list[2]=new int[jiao.size()];
		
		for(int i=0;i<worker_id_list[0].length;i++)
		{
			worker_id_list[0][i]=zhou.get(i);
		}
		for(int i=0;i<worker_id_list[1].length;i++)
		{
			worker_id_list[1][i]=fan.get(i);
		}
		for(int i=0;i<worker_id_list[2].length;i++)
		{
			worker_id_list[2][i]=jiao.get(i);
		}
		
		
		return worker_id_list;
		
	}
	
	public void task_info_ids_updata(task_info task_info,String[] ids)
	{
		int[] ids_int=new int[ids.length];
		for(int i=0;i<ids.length;i++)
		{
			ids_int[i]=Integer.parseInt(ids[i]);
		}
		
		task_info.setStaff_id(ids_int);
	}
}
