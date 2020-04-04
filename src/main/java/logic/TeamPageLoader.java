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
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.multipart.MultipartFile;

import com.sun.mail.imap.protocol.UID;

import pojo.Block;
import pojo.Forusm_info;
import pojo.Group;
import pojo.Team;
import pojo.User_info;
import pojo.member;
import pojo.task;
import staticvar.FileVar;

public class TeamPageLoader {
	private static SessionFactory factory; 
public List<Team> TeamListGetter(int uid)//get Team list 
{
	List<Team> list = new ArrayList();
	pojo_getter pojo_getter=new pojo_getter();
	int[] TeamId=UserTeamIdGetter(uid);
	if(TeamId.length==1)
	return null;
	
	for(int i=1;i<TeamId.length;i++)
	{
		list.add(pojo_getter.TeamGetter(TeamId[i]));
	}
	 return list;
	
}

public boolean isLeader(int uid,int tid)
{
	String file=FileVar.Absolute_headurl+"/team/"+ tid+"/memberlist.data";
	 ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
	
			@SuppressWarnings("unchecked")
			List<member>  a = (List<member> ) ois.readObject();
			System.out.print("uid \n"+a.get(0).getUid());
			if (a.get(0).getUid()==uid) {
				
				return true;					}
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

	return false;
	
}

public int ISLeader(int uid)
{
	int[] TeamId=UserTeamIdGetter(uid);
System.out.print("\n teamid size:"+TeamId.length);
if (TeamId.length==1) {
	return 0;
}

		for(int i=1;i<TeamId.length;i++)
		{
			String file=FileVar.Absolute_headurl+"/team/"+ TeamId[i]+"/memberlist.data";
			
			System.out.print(file+"\n");
			 ObjectInputStream ois=null;
				try {
					ois = new ObjectInputStream(new FileInputStream(file));
			
					@SuppressWarnings("unchecked")
					List<member>  a = (List<member> ) ois.readObject();
					System.out.print("uid \n"+a.get(0).getUid());
					if (a.get(0).getUid()==uid) {
						
						return TeamId[i];					}
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
		 
		}
		return 0;
}

public void TeamCreate(int uid ,String name)
{
	int tid=Teamcreate(name);
	Teamcreate(tid, uid);
	ApplicationInit(tid);
UserJionTeam(tid, uid);
	
}

public void UserJionTeam(int tid,int uid)
{
updata_user_teamlist(tid, uid);
updata_team_merberlist(tid, uid, TeamMemberGetter(tid));
	
}

public void agreeJoin(int tid,int uid)
{
	removeAlist(tid, uid);
	UserJionTeam(tid, uid);
	
}

private void removeAlist(int tid,int uid) {
	int[] aList=ApplicationReader(tid);
	int mark=1;
	for(int i=0;i<aList.length;i++)
	{
		if(uid==aList[i])
		{
			mark=i;
			break;
		}
	}
	int[] newalist=new int[aList.length-1];
	for(int i=0;i<mark;i++)
	{
	   newalist[i]=aList[i];
	}
	for(int i=mark;i<newalist.length;i++)
	{
		newalist[i]=aList[i+1];
	}
	ApplicationResave(newalist, tid);
}

private void ApplicationInit(int tid)
{
	int[] Application=new int[1];
	Application[0]=0;
	ObjectOutputStream oos=null;
	
	  try {
		  File f = new File(FileVar.Absolute_headurl+"/team/"+tid+"/Applicationlist.data");
		  if (!f.getParentFile().exists()) {
	            boolean result = f.getParentFile().mkdirs();
		  }
		   oos = new ObjectOutputStream(new FileOutputStream(f));
	     
	   
		  oos.writeObject(Application);

	} catch (IOException e) {
		// TODO Auto-generated catch block0
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

public int[] isappGetter(List<Team> tList,int uid)
{
	List<member> members;
	int[] isapp=new int[tList.size()];
	for(int i=0;i<tList.size();i++)
	{
		members=TeamMemberGetter(tList.get(i).getId());
		boolean is_in_memberlist=false;
		for(int j=0;j<members.size();j++)
		{
			if(members.get(j).getUid()==uid)
			{
				is_in_memberlist=true;
				break;
				
			}
		}
		
		if(isapp(uid, tList.get(i).getId())||is_in_memberlist)
		{
			isapp[i]=1;
		}
		else {
			isapp[i]=0;
		}
	}
	return isapp;
}

private boolean isapp(int uid,int tid)
{
	int[] alist=ApplicationReader(tid);
	for(int i=0;i<alist.length;i++)
	{
		
		
		if (alist[i]==uid) {
			return true;
		}
	}
	return false;
}

private void ApplicationResave(int[] alist,int tid)
{
	ObjectOutputStream oos=null;
	
	  try {
		  File f = new File(FileVar.Absolute_headurl+"/team/"+tid+"/Applicationlist.data");
		  if (!f.getParentFile().exists()) {
	            boolean result = f.getParentFile().mkdirs();
		  }
		   oos = new ObjectOutputStream(new FileOutputStream(f));
	     
	   
		  oos.writeObject(alist);

	} catch (IOException e) {
		// TODO Auto-generated catch block0
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

public int[] ApplicationReader(int tid)
{
	  ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(FileVar.Absolute_headurl+"/team/"+tid+"/Applicationlist.data"));
			// 璇诲彇瀵硅薄
			// Student stu = (Student)ois.readObject();
			// System.out.println("璇诲彇鍒扮殑鏁版嵁涓�:"+stu);
			@SuppressWarnings("unchecked")
			int[] alist = (int[]) ois.readObject();
			return alist;
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

public void ApplicationAdd(int tid,int uid)
{
	int[] alist= ApplicationReader(tid);
	int[] newlist=new int[alist.length+1];
	for(int i=0;i<alist.length;i++)
	{
		newlist[i]=alist[i];
	}
	newlist[newlist.length-1]=uid;
	ApplicationResave(newlist, tid);
}

private void updata_team_merberlist(int tid,int uid,List<member> members)//
{
	member member=new member();
	member.setUid(uid);
	member.setRole(0);
	members.add(member);
	 ObjectOutputStream oos=null;
		
	  try {
		  File f = new File(FileVar.Absolute_headurl+"/team/"+tid+"/memberlist.data");
		  if (!f.getParentFile().exists()) {
	            boolean result = f.getParentFile().mkdirs();
		  }
		   oos = new ObjectOutputStream(new FileOutputStream(f));
	     
	   
		  oos.writeObject(members);

	} catch (IOException e) {
		// TODO Auto-generated catch block0
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

private void updata_user_teamlist(int tid,int uid)//add the new tid to the teamlist
{
	int[] teamlist= UserTeamIdGetter(uid);
	int[] newteamlist=new int[teamlist.length+1];
	for(int i=0;i<teamlist.length;i++)
	{
		newteamlist[i]=teamlist[i];
	}
	newteamlist[teamlist.length]=tid;
	teamlistSave(uid, newteamlist);
}


public void teamlistSave(int uid,int[] a)
{ObjectOutputStream oos=null;
String url=FileVar.Absolute_headurl+"/user/"+ uid+"/teamlist.data";
try {
	
	  File f = new File(url);
	  
	  if (!f.getParentFile().exists()) {
          boolean result = f.getParentFile().mkdirs();
	  }
	   oos = new ObjectOutputStream(new FileOutputStream(f));
   
 
	  oos.writeObject(a);

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

private void Teamcreate(int tid,int uid)//file :save merber list 
{
	member member=new member();
	member.setUid(uid);
	member.setRole(0);
	List<member> members=new ArrayList<member>();
	members.add(member);
	 ObjectOutputStream oos=null;
		
	  try {
		  File f = new File(FileVar.Absolute_headurl+"/team/"+tid+"/memberlist.data");
		  if (!f.getParentFile().exists()) {
	            boolean result = f.getParentFile().mkdirs();
		  }
		   oos = new ObjectOutputStream(new FileOutputStream(f));
	     
	   
		  oos.writeObject(members);

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

private int Teamcreate(String name)//database : only name time and id by ai,   icon by null 
{
	int tid=0;

	Date date=new Date();
	 Team team=new Team();
	 team.setName(name);
	 team.setCreatedate(date);
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
	       tid= (Integer) session.save(team);
	        tx.commit(); 
	    
	   
	     }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	     }finally {
	    	 
	        session.close(); 
	   
	     }
		return tid;
	  
	}

public List<member> TeamMemberGetter(int tid)
{
	String file=FileVar.Absolute_headurl+"/team/"+ tid+"/memberlist.data";
	  ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			List<member> a = (List<member>) ois.readObject();
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

public  int[] UserTeamIdGetter(int uid)//get TeamId list for file
{
	  
	String file=FileVar.Absolute_headurl+"/user/"+ uid+"/teamlist.data";
	  ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			// 璇诲彇瀵硅薄
			// Student stu = (Student)ois.readObject();
			// System.out.println("璇诲彇鍒扮殑鏁版嵁涓�:"+stu);
			@SuppressWarnings("unchecked")
			int[] a = (int[]) ois.readObject();
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

public List<task> taskListLoad(int tid)
{
	 try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
	
	 
	 
	 Session session = factory.openSession();
	 try{
		 //鑾峰彇鎵�鏈塨lock瀵硅薄
		 Criteria cr = session.createCriteria(task.class);   
		 cr.add(Restrictions.eq("tid",tid));   
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

public List<Team> searchTeamByName(String name) {
	  try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		


		 Session session = factory.openSession();
		 try{

			 String hql = "FROM Team WHERE name like '%";
			 for(int i=0;i<name.length();i++)
			 {
				 if(i==0)
				 {
					 hql=hql+name.charAt(0)+"%'";
					 continue;
				 }
				 hql=hql+"and name like '%"+name.charAt(i)+"%'";
			 }
			   System.out.print(hql+"\n");
			    //创建查询对象
			    Query createQuery = session.createQuery(hql);
			    List<Team> list = createQuery.list();
			    //打印信息
			   return list;
	      
	     }catch (HibernateException e) {
	       
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return null;
}

public List<User_info> TeamUser_infoGetter_member(int tid)
{
     List<member> members=TeamMemberGetter(tid);
	int[] uids=new int[members.size()-1];
	for(int i=1;i<members.size();i++)
	{
		uids[i-1]=members.get(i).getUid();
	}
	pojo_getter pojo_getter=new pojo_getter();
	return pojo_getter.user_infosGetter(uids);
}
public List<User_info> TeamUser_infoGetter_app(int tid)
{
     int[] a_uids=ApplicationReader(tid);
	int[] uids=new int[a_uids.length-1];
	for(int i=1;i<a_uids.length;i++)
	{
		uids[i-1]=a_uids[i];
	}
	pojo_getter pojo_getter=new pojo_getter();
	return pojo_getter.user_infosGetter(uids);
}


public void UpdataTeam_info_Upicon( MultipartFile file,int tid) throws IllegalStateException, IOException
{
	if (!file.getOriginalFilename().equals("")) {
		 String path = FileVar.Absolute_headurl+"/team/"+tid;
	        String fileName = file.getOriginalFilename();    
	        File dir = new File(path,fileName);          
	        if (!dir.getParentFile().exists()) {
	            boolean result = dir.getParentFile().mkdirs();
		  }
	 
	        file.transferTo(dir);  
}
}


public void UpdataTeam_DB(Team team)
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

         session.update(team); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
}


}
