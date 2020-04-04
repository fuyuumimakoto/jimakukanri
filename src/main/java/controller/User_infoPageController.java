package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Decoder.Text;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.UserOP;
import logic.chatPage;
import logic.pojo_getter;
import pojo.Chat_log;
import pojo.User_info;
import pojo.chat_log_file;

@Controller
public class User_infoPageController {

	@RequestMapping("jump2chat")
	 public ModelAndView jump2chat(int uid,int act_uid) {
		ArrayList<Chat_log> chat_logs ;
		ArrayList<User_info> user_infos;
	    chatPage chatPage=new chatPage();
	    pojo_getter pojo_getter=new pojo_getter();
	    chat_log_file chat_log_file=chatPage.chat_log_fileGetter(act_uid);
	    chat_logs=chatPage.chatlogGetter(chat_log_file, uid);
	    
	    user_infos=chatPage.user_infoGetter(chat_log_file.getChatuid(),uid);//get the userinfo and then   when this funtion complete the uidlist will be change
	    user_infos.add(pojo_getter.User_infoGetter(act_uid));//add the act userinfo to last
	    ModelAndView mav = new ModelAndView("chat");
	        mav.addObject("uid",uid);
	        mav.addObject("act_uid", act_uid);
	        mav.addObject("chat_logs",chat_logs);
	        mav.addObject("user_infos",user_infos);
	    
	      
	        return mav;
	    }
	
	@RequestMapping("sendchatmsg")
	 public ModelAndView sendchatmsg(int uid,int act_uid,String text) {
	    chatPage chatPage=new chatPage();
	    pojo_getter pojo_getter=new pojo_getter();
	    ArrayList<User_info> user_infos;
	    
	    chat_log_file actuser_CLF=chatPage.chat_log_fileGetter(act_uid);
	    chat_log_file user_CLF=chatPage.chat_log_fileGetter(uid);
	    
		int mark;
		mark=chatPage.sendmsg(uid, act_uid, actuser_CLF, user_CLF, text);
		
		user_infos=chatPage.user_infoGetter(actuser_CLF.getChatuid(),uid);//get the userinfo and then   when this funtion complete the uidlist will be change
	    user_infos.add(pojo_getter.User_infoGetter(act_uid));//add the act userinfo to last
		
	    ModelAndView mav = new ModelAndView("chat");
        mav.addObject("uid",uid);
        mav.addObject("act_uid", act_uid);
        mav.addObject("chat_logs",actuser_CLF.getChatloglist().get(mark));
        mav.addObject("user_infos",user_infos);
    
      
        return mav;
	
		
		
		
	}
	
	
	
	@RequestMapping("User_infoUpdata")
	 public ModelAndView jump2user_info_updata(String uid) {
	pojo_getter pojo_getter=new pojo_getter();
	
	int int_uid=pojo_getter.md5id2id(uid);
	
	User_info user_info=pojo_getter.User_infoGetter(int_uid);
	
	        ModelAndView mav = new ModelAndView("user_info_updata");
	        mav.addObject(user_info);
	      
	        return mav;
	    }
	
	@RequestMapping("registered")
	 public ModelAndView jump2registeredPage() {
		ModelAndView mav = new ModelAndView("registered");
		 return mav;
	}
	
	
	
	@RequestMapping(value="user_info_updata_requset",method=RequestMethod.POST)
	 @ResponseBody  
	 public ModelAndView user_info_updata(@RequestParam MultipartFile file,HttpServletRequest request,String uid,String name,int qq_num) throws IllegalStateException, IOException {
		UserOP userop=new UserOP();
		pojo_getter pojo_getter=new pojo_getter();
		
		int int_uid=pojo_getter.md5id2id(uid);
		
		User_info user_info=pojo_getter.User_infoGetter(int_uid);
		
		String[] idStrings=request.getParameterValues("checkbox");	
		int[] cheaks=new int[idStrings.length];
		for(int i=0;i<idStrings.length;i++)
		{
			cheaks[i]=Integer.parseInt(idStrings[i]);
		}
		
		user_info.setName(name);
		user_info.setQq_num(qq_num);
		
		int role=userop.role_cheak(cheaks);
		
		user_info.setRole(role);
		
		if(!file.getOriginalFilename().equals(""))
		{user_info.setIcon(int_uid+"/"+file.getOriginalFilename());
		userop.UpdataUser_info_Upicon(file, user_info.getId());
		}
		
		userop.UpdataUser_info_DB(user_info);
	
		   ModelAndView mav = new ModelAndView("user_info");
	        mav.addObject(user_info);
	        mav.addObject("uid",uid);
	        mav.addObject("act_uid",uid);
	        return mav;
	    }
}
