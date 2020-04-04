package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.BlockPageLoader;
import logic.TeamPageLoader;
import logic.UserOP;
import logic.md5_encryption;
import logic.pojo_getter;
import pojo.Block;
import pojo.Forusm;
import pojo.Team;
import pojo.User;
import pojo.User_info;
import pojo.task;

@Controller
public class MainPageController {

	
	@RequestMapping("block_info")
	 public ModelAndView jump2blockPage(int bid,int uid) {
		
		ModelAndView mav = new ModelAndView("block");
	BlockPageLoader blockpageloader=new BlockPageLoader();
		List<Forusm> flist=new ArrayList();
		flist=blockpageloader.ForusmListLoader(bid);
		
		

		
		//int to string
		String string_uid=""+uid;
		
        mav.addObject("flist",blockpageloader.ForusmListLoader(bid));
      mav.addObject("uid",string_uid);
        return mav;
	}
	
	@RequestMapping("team")
	public ModelAndView jump2teamPage(String uid,int  tid,int pagenum) {
		
		
		ModelAndView mav = new ModelAndView("team");
		int leader_tid;
		
		
		TeamPageLoader teamPageLoader=new TeamPageLoader();
		pojo_getter pojo_getter=new pojo_getter();
		
		int  int_uid=pojo_getter.md5id2id(uid);
		
		List<Team> tList=teamPageLoader.TeamListGetter(int_uid);
		if(tid==0)
		{
			if(tList!=null)
			{tid=tList.get(0).getId();}
		
		}
		
		leader_tid=teamPageLoader.ISLeader(int_uid);
	
		List<task> tasklist=teamPageLoader.taskListLoad(tid);
		mav.addObject("pagenum",pagenum);
		mav.addObject("uid",uid);
		mav.addObject("tid" , tid);
		mav.addObject("tList",tList);
		mav.addObject("tasklist",tasklist);		
		mav.addObject("leader_tid",leader_tid);
		
		return mav;
	}
	
	
}
