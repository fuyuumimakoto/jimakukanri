package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.MainPageLoad;
import logic.TaskPage;
import logic.TeamPageLoader;
import logic.UserOP;
import logic.md5_encryption;
import logic.pojo_getter;
import pojo.Block;
import pojo.Team;
import pojo.User_info;
import pojo.member;
import pojo.task;
import pojo.task_info;
import staticvar.FileVar;



@Controller
public class TaskController {
	md5_encryption md5_encryption=new md5_encryption();
	
	@RequestMapping("taskCreate")
	 public ModelAndView jump2taskCreate(String uid,int tid) {
		

			MainPageLoad mainpageloader=new MainPageLoad();
	

		        ModelAndView mav = new ModelAndView("taskcreate");
		
		        mav.addObject("tid",tid);
		        mav.addObject("uid",uid);
		       
		return mav;
		
	}
	
	
	@RequestMapping(value="task_file_up",method=RequestMethod.POST)
	 @ResponseBody  
	 public ModelAndView user_info_updata(@RequestParam MultipartFile file,HttpServletRequest request,int tid,int taskid,String uid,int mark) throws IllegalStateException, IOException {
		
		TaskPage taskPage=new TaskPage();
		task_info task_info=taskPage.task_infoGetter(tid, taskid);
		
		String[] file_name=task_info.getFiles_name();
		
		if(!file.getOriginalFilename().equals(""))
		{
			file_name[mark]=file.getOriginalFilename();
		taskPage.FileUp(file, taskid, tid);
		}
		
		task_info.setFiles_name(file_name);
		
		taskPage.resave(task_info);
	
		String[] name=taskPage.nameGetByids(task_info.getStaff_id());

		
		String[] md5ids=new String[task_info.getPart()*3];
		
		for(int i=0;i<md5ids.length;i++)
		{
			md5ids[i]=md5_encryption.getHash(task_info.getStaff_id()[i]+"");
		}

	   
	        ModelAndView mav = new ModelAndView("task_info");
	        mav.addObject("md5ids",md5ids);
	        mav.addObject("name",name);
	        mav.addObject("task_info",task_info);
	        mav.addObject("uid",uid);
	       
	        return mav;
	    }
	
	@RequestMapping("task_file_down")  
    public void down(HttpServletRequest request,HttpServletResponse response,String file,int tid,int taskid,String uid) throws Exception{  
       
        String fileName =  FileVar.Absolute_headurl+"//team//"+tid+"//task//"+taskid+"//"+file;
        
        
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
          
        String filename = file;  
        
        filename = URLEncoder.encode(filename,"UTF-8");  
        
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
            
        response.setContentType("multipart/form-data");   
        
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }  
	
	
	@RequestMapping("taskinfo")
	 public ModelAndView jump2taskinfo(String uid,int tid,int taskid) {
		

			MainPageLoad mainpageloader=new MainPageLoad();
			
		
			TaskPage taskPage=new TaskPage();
		
			
			task_info task_info=taskPage.task_infoGetter(tid, taskid);
		

	
			String[] name=taskPage.nameGetByids(task_info.getStaff_id());
			
			String[] md5ids=new String[task_info.getPart()*3];
			
			for(int i=0;i<md5ids.length;i++)
			{
				md5ids[i]=md5_encryption.getHash(task_info.getStaff_id()[i]+"");
			}
 
		        ModelAndView mav = new ModelAndView("task_info");
		 
		      mav.addObject("md5ids",md5ids);
		        mav.addObject("name",name);
		        mav.addObject("task_info",task_info);
		        mav.addObject("uid",uid);
		       
		return mav;
		
	}
	
	@RequestMapping("jump2task_info_modify")
	 public ModelAndView jump2task_info_modify(String uid,int tid,int taskid) {
		

			MainPageLoad mainpageloader=new MainPageLoad();
			
		
			TaskPage taskPage=new TaskPage();
	
			task_info task_info=taskPage.task_infoGetter(tid, taskid);
			
			List<User_info> user_infos=taskPage.worker_listGetter(tid);
			int[][] worker_id_list=taskPage.worker_idGetter(user_infos);
			String[][] worker_name_list=taskPage.worker_nameGetter(worker_id_list);
			
	
		

	
			String[] name_old=taskPage.nameGetByids(task_info.getStaff_id());

		        ModelAndView mav = new ModelAndView("task_info_modify");
		 
		      
		        mav.addObject("name_old",name_old);
		        mav.addObject("name",worker_name_list);
		        mav.addObject("id",worker_id_list);
		        mav.addObject("task_info",task_info);
		        mav.addObject("uid",uid);
		       
		        
		       
		return mav;
		
	}
	
	@RequestMapping("task_info_updata")
	 @ResponseBody  
	 public ModelAndView task_info_updata(HttpServletRequest request,String uid,int tid,int taskid) {
		

			MainPageLoad mainpageloader=new MainPageLoad();
			
		
			TaskPage taskPage=new TaskPage();
			task_info task_info=taskPage.task_infoGetter(tid, taskid);
			
			String[] idStrings=request.getParameterValues("sel_id");	
			
			taskPage.task_info_ids_updata(task_info, idStrings);
			taskPage.resave(task_info);
		

	
			String[] name=taskPage.nameGetByids(task_info.getStaff_id());
String[] md5ids=new String[task_info.getPart()*3];
			
			for(int i=0;i<md5ids.length;i++)
			{
				md5ids[i]=md5_encryption.getHash(task_info.getStaff_id()[i]+"");
			}

		        ModelAndView mav = new ModelAndView("task_info");
		 
		        mav.addObject("md5ids",md5ids);
		        mav.addObject("name",name);
		        mav.addObject("task_info",task_info);
		        mav.addObject("uid",uid);
		       
		return mav;
		
	}
	
	@RequestMapping("taskjoin")
	 public ModelAndView taskjoin(int uid,int role,int tid,int taskid) {
		

			MainPageLoad mainpageloader=new MainPageLoad();
			
			String[] names=null;
		
			TaskPage taskPage=new TaskPage();
			task_info task_info=taskPage.task_infoGetter(tid, taskid);
			taskPage.taskjoin(task_info,role, uid);
		System.out.print(task_info.getName());
	

		        ModelAndView mav = new ModelAndView("task_info");
		        
		        mav.addObject("names",names);
		        mav.addObject("task_info",task_info);
		        mav.addObject("uid",uid);
		       
		return mav;
		
	}
	
	@RequestMapping("taskCreate_post")
	 public ModelAndView taskCreate(String uid,int tid,String name,int part,int time) throws UnsupportedEncodingException {
		

			MainPageLoad mainpageloader=new MainPageLoad();
			pojo_getter pojo_getter=new pojo_getter();
			
			int int_uid=pojo_getter.md5id2id(uid);
		        ModelAndView mav = new ModelAndView("team");
		        TaskPage taskPage=new TaskPage();
		        task task=new task();
		        task.setCreatedate(new Date());
		        task.setCreateuid(int_uid);
		        
		        task.setName( name);
		        task.setPart(part);
		        task.setTid(tid);
		        task.setIsdone(0);
		        task.setTime(time);
		  
		
		        taskPage.taskCreate(task);
		        TeamPageLoader teamPageLoader=new TeamPageLoader();
				List<Team> tList=teamPageLoader.TeamListGetter(int_uid);
				List<task> tasklist=teamPageLoader.taskListLoad(tid);
		       
				int leader_tid;
				leader_tid=teamPageLoader.ISLeader(int_uid);
				
				mav.addObject("pagenum",1);
				mav.addObject("uid",uid);
				mav.addObject("tid" , tid);
				mav.addObject("tList",tList);
				mav.addObject("tasklist",tasklist);		
				mav.addObject("leader_tid",leader_tid);
		       
		return mav;
		
	}
	
	@RequestMapping("/updateFormList")
	@ResponseBody
	public Map<String, Object>  updateFormList(@RequestBody List<member> members)
	{
		System.out.print(members.size());
		return null;
		
	}
	
}
