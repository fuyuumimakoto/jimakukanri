package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.MainPageLoad;
import logic.TeamPageLoader;
import logic.UserOP;
import logic.pojo_getter;
import pojo.Block;
import pojo.Team;
import pojo.User_info;
import pojo.member;
import pojo.task;

@Controller
public class TeamController {
	@RequestMapping("teamCreate")
	 public ModelAndView jump2teamCreate(String uid) {

		
		TeamPageLoader teamPageLoader=new TeamPageLoader();
		pojo_getter pojo_getter =new pojo_getter();
		
		int int_uid=pojo_getter.md5id2id(uid);
			
			

		        ModelAndView mav = new ModelAndView("teamcreate");
		if(teamPageLoader.ISLeader(int_uid)!=0)
		{
			System.out.print("\nisleader true");
			 mav.addObject("isleader","1");
		}
		else {
			System.out.print("\nisleader false");
			mav.addObject("isleader","0");
		}
		       
		        mav.addObject("uid",uid);
		    
		return mav;
		
	}
	@RequestMapping("TeamSearch")
	 public ModelAndView Teamsearch(String uid,String name) {
	
		 
		 TeamPageLoader teamPageLoader=new TeamPageLoader();
		 pojo_getter pojo_getter=new pojo_getter();
		 
		 int int_uid=pojo_getter.md5id2id(uid);

		 
			ModelAndView mav = new ModelAndView("team_add");
			List<Team> tList=null;
			int[] isapp=null;
			if(!name.equals(""))
			{
			 tList=teamPageLoader.searchTeamByName(name);
				 isapp=teamPageLoader.isappGetter(tList, int_uid);
			}

			
			mav.addObject("tlist",tList);
			mav.addObject("uid",uid);
			mav.addObject("isapp",isapp);
		
			
			
			return mav;
		
	}
	
	
	
	
	
	@RequestMapping("TeamApplication")
	 public ModelAndView TeamApplication(String uid,int tid) {
		 
		 
		 TeamPageLoader teamPageLoader=new TeamPageLoader();
		 pojo_getter pojo_getter =new pojo_getter();
		 
		 int int_uid=pojo_getter.md5id2id(uid);
		 
		 teamPageLoader.ApplicationAdd(tid, int_uid);

		
		 
			ModelAndView mav = new ModelAndView("team_add");
			ArrayList<Team> tList=new ArrayList<>();
			tList.add(pojo_getter.TeamGetter(tid));
			int[] isapp=new int[1];
			isapp[0]=1;
		
			mav.addObject("isapp",isapp);
			mav.addObject("tlist",tList);
			mav.addObject("uid",uid);
		
			
			
			return mav;
		
	}
	
	
	@RequestMapping("TeamCreate_post")
	 public ModelAndView Teamcreate(String uid,String name,int  tid,int pagenum) {
		 String stringuid=uid+""; //取得用户id
		 
		 TeamPageLoader teamPageLoader=new TeamPageLoader();
		 pojo_getter pojo_getter=new pojo_getter();
		 
		 int int_uid=pojo_getter.md5id2id(uid);
		 
		 teamPageLoader.TeamCreate(int_uid, name);
		 
		 
			ModelAndView mav = new ModelAndView("team");
			
			
			List<Team> tList=teamPageLoader.TeamListGetter(int_uid);
			
			mav.addObject("pagenum",pagenum);
			mav.addObject("uid",uid);
			mav.addObject("tList",tList);
			mav.addObject("tid" , tid);
			mav.addObject("tasklist",null);		

			
			return mav;
		
	}
	
	@RequestMapping("Team_info")
	 public ModelAndView jump2Team_info(String uid,int tid) {
		 
		 
		 TeamPageLoader teamPageLoader=new TeamPageLoader();
		 pojo_getter pojo_getter=new pojo_getter();
		 
		 int int_uid=pojo_getter.md5id2id(uid);
	
		 
		 List<User_info> user_info_member=teamPageLoader.TeamUser_infoGetter_member(tid);
		 List<User_info> user_info_app=teamPageLoader.TeamUser_infoGetter_app(tid);
		 String isleader=0+"";
		 if(teamPageLoader.isLeader(int_uid, tid))
		 {
			 isleader=1+"";
		 }
		 

		 Team team=pojo_getter.TeamGetter(tid);
	

		
		 
			ModelAndView mav = new ModelAndView("team_info");
	
		
			mav.addObject("uid",uid);
			mav.addObject("isleader",isleader);
			mav.addObject("team",team);
			mav.addObject("user_info_member",user_info_member);
			mav.addObject("user_info_app",user_info_app);
			
			return mav;
		
	}
	
	@RequestMapping("TeamAddUser")
	 public ModelAndView TeamAddUser(String uid,int tid) {
		
		 
		 TeamPageLoader teamPageLoader=new TeamPageLoader();
		 pojo_getter pojo_getter=new pojo_getter();
		 
		 int int_uid=pojo_getter.md5id2id(uid);
		 
		 teamPageLoader.agreeJoin(tid, int_uid);

	
	
		 
		 List<User_info> user_info_member=teamPageLoader.TeamUser_infoGetter_member(tid);
		 List<User_info> user_info_app=teamPageLoader.TeamUser_infoGetter_app(tid);
		 String isleader=0+"";
		 if(teamPageLoader.isLeader(int_uid, tid))
		 {
			 isleader=1+"";
		 }
		 


		
		 
			ModelAndView mav = new ModelAndView("team_info");
	
		
			mav.addObject("uid",uid);
			mav.addObject("isleader",isleader);
			mav.addObject("tid",tid);
			mav.addObject("user_info_member",user_info_member);
			mav.addObject("user_info_app",user_info_app);
			
			
			
			return mav;
		
	}
	
	
	@RequestMapping(value="team_info_updata",method=RequestMethod.POST)
	/* @ResponseBody  */
	 public ModelAndView team_info_updata(@RequestParam MultipartFile file,HttpServletRequest request,int tid,String name,String uid) throws IllegalStateException, IOException {
		TeamPageLoader teamPageLoader= new TeamPageLoader();
		pojo_getter pojo_getter=new pojo_getter();
		Team team=pojo_getter.TeamGetter(tid);
		
		int leader_tid;
		int int_uid=pojo_getter.md5id2id(uid);
		 
		if(!file.getOriginalFilename().equals(""))
		{team.setIcon(file.getOriginalFilename());
		teamPageLoader.UpdataTeam_info_Upicon(file, tid);
		}
		team.setName(name);
		
		teamPageLoader.UpdataTeam_DB(team);
		
		
		 List<User_info> user_info_member=teamPageLoader.TeamUser_infoGetter_member(tid);
		 List<User_info> user_info_app=teamPageLoader.TeamUser_infoGetter_app(tid);
		 String isleader=0+"";
		 if(teamPageLoader.isLeader(int_uid, tid))
		 {
			 isleader=1+"";
		 }
		 
		 leader_tid=teamPageLoader.ISLeader(int_uid);
			List<task> tasklist=teamPageLoader.taskListLoad(tid);
			List<Team> tList=teamPageLoader.TeamListGetter(int_uid);
			if(tid==0)
			{
				if(tList!=null)
				{tid=tList.get(0).getId();}
			
			}
	
		ModelAndView mav = new ModelAndView("team");
		mav.addObject("pagenum",1);
		mav.addObject("uid",uid);
		mav.addObject("tid" , tid);
		mav.addObject("tList",tList);
		mav.addObject("tasklist",tasklist);		
		mav.addObject("leader_tid",leader_tid);
	        return mav;
	    }
	
	
}
