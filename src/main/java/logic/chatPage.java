package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pojo.Chat_log;
import pojo.User_info;
import pojo.chat_log_file;
import staticvar.FileVar;

public class chatPage {
	
	public int sendmsg(int uid,int act_uid,chat_log_file actuser_CLF,chat_log_file user_CLF,String text)
	{
		int actuser_mark = 0;
		Chat_log chat_log=new Chat_log(act_uid,text);
		
			
		if(!iseixst_num(actuser_CLF.getChatuid(), uid)&&!iseixst_num(user_CLF.getChatuid(), act_uid))//if unexist add the num to each obj
		{
			uidlistadd(actuser_CLF.getChatuid(), uid,actuser_CLF);
			uidlistadd(user_CLF.getChatuid(), act_uid,user_CLF);//add the num to the uidlist
			
			ArrayList<Chat_log> actuser_chat_loglist=new ArrayList<>();
			ArrayList<Chat_log> user_chat_loglist=new ArrayList<>();
			actuser_chat_loglist.add(new Chat_log(uid,"frist with uid:"+uid));
			user_chat_loglist.add(new Chat_log(act_uid, "frist with uid:"+act_uid));//init chat_log_list
			
			actuser_chat_loglist.add(chat_log);
			user_chat_loglist.add(chat_log);//add the send msg
			
			ArrayList<ArrayList<Chat_log>> actuser_chatloglist_list=actuser_CLF.getChatloglist();
			ArrayList<ArrayList<Chat_log>> user_chatloglist_list=user_CLF.getChatloglist();//get the chat_log list list
			
			actuser_chatloglist_list.add(actuser_chat_loglist);
			user_chatloglist_list.add(user_chat_loglist);//add the list  to the list list
			
			actuser_CLF.setChatloglist(actuser_chatloglist_list);
			user_CLF.setChatloglist(user_chatloglist_list);//set list list
				
		}
		else {
			 actuser_mark=markGetter(uid, actuser_CLF.getChatloglist());
			int user_mark=markGetter(act_uid, user_CLF.getChatloglist());
			
			ArrayList<ArrayList<Chat_log>> actuser_chatloglist_list=actuser_CLF.getChatloglist();
			ArrayList<ArrayList<Chat_log>> user_chatloglist_list=user_CLF.getChatloglist();//get the chat_log list list
			
			actuser_chatloglist_list.get(actuser_mark).add(chat_log);
			user_chatloglist_list.get(user_mark).add(chat_log);//add the send msg
			
			actuser_CLF.setChatloglist(actuser_chatloglist_list);
			user_CLF.setChatloglist(user_chatloglist_list);//set list list
		}
		chatFileSave(act_uid, actuser_CLF);
		chatFileSave(uid, user_CLF);
		return actuser_mark;
	}
	
	private void chatFileSave(int uid,chat_log_file  chat_log_file)
	{
		ObjectOutputStream oos=null;

		String url =FileVar.Absolute_headurl+"/user/"+uid+"/chatlog.data";
		
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
	
	private int markGetter(int uid,ArrayList<ArrayList<Chat_log>> chat_log_list_list)
	{
		for(int i=0;i<chat_log_list_list.size();i++)
		{
			if(uid==chat_log_list_list.get(i).get(0).getUid())
			{return i;}
		}
		return 0;
	}
	
	private void uidlistadd(int[] uidlist,int uid,chat_log_file chat_log_file)
	{
		int[] new_uidlist=new int[uidlist.length+1];
		System.arraycopy(uidlist, 0, new_uidlist, 0, uidlist.length);
		new_uidlist[new_uidlist.length-1]=uid;
		chat_log_file.setChatuid(new_uidlist);
	}
	
	private void uidlistadd(int[] uidlist,int uid)
	{
		int[] new_uidlist=new int[uidlist.length+1];
		System.arraycopy(uidlist, 0, new_uidlist, 0, uidlist.length);
		new_uidlist[new_uidlist.length-1]=uid;
		uidlist=new_uidlist;
	}
	public ArrayList<Chat_log>  chatlogGetter(chat_log_file chat_log_file,int uid)
	{
	
		{if(!iseixst_num(chat_log_file.getChatuid(), uid))
		{return null;}//when the taget num unexist there will be return null
		else {
			ArrayList<ArrayList<Chat_log>> chatloglist_list=chat_log_file.getChatloglist();
			for(int i=0;i<chatloglist_list.size();i++)
			{
				if(chatloglist_list.get(i).get(0).getUid()==uid)
				{
					System.out.print("\nmark:"+i);
					return chatloglist_list.get(i);
					
				}
			}
		}}
		return null;
		
	}
	
	public ArrayList<User_info> user_infoGetter(int[] uidlist,int uid)//retrun user info list ,if uid unexist there will add a fake userinfo to the list
	{
		ArrayList<User_info> user_infos =new ArrayList<>();
	
		pojo_getter pojo_getter=new pojo_getter();
		if(!iseixst_num(uidlist, uid))
		{
		uidlistadd(uidlist, uid);
		}
		for(int i=0;i<uidlist.length;i++)
		{
			System.out.print("the uidlist \n:"+uidlist[i]);
		}
		for(int i=0;i<uidlist.length;i++)
		{
			user_infos.add(pojo_getter.User_infoGetter(uidlist[i]));
		}
		return user_infos;
	}

private void chat_init(int act_uid,int uid)
{
	chat_log_file actuser_CLF= chat_log_fileGetter(act_uid);
	int[] uidlist=actuser_CLF.getChatuid();
	if(!iseixst_num(uidlist, uid))
	{
		int[] new_uidlist=new int[uidlist.length+1];
		System.arraycopy(uidlist, 0, new_uidlist, 0, uidlist.length);
		new_uidlist[new_uidlist.length]=uid;
		
	}
}

private boolean iseixst_num(int[] uidlist,int uid)
{
	for(int i=0;i<uidlist.length;i++)
	{
		if(uidlist[i]==uid)
			return true;
	}
	return false;
}

public chat_log_file chat_log_fileGetter(int uid)
{
	String url =FileVar.Absolute_headurl+"/user/"+uid+"/chatlog.data";
	  ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));

			@SuppressWarnings("unchecked")
			chat_log_file chat_log_file= (chat_log_file) ois.readObject();
			return chat_log_file;
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
