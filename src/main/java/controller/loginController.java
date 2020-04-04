package controller;

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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import logic.MainPageLoad;
import logic.User_registered;
import logic.loginCheak;
import logic.md5_encryption;
import logic.pojo_getter;
import pojo.Block;
import pojo.Employee;
import pojo.Forusm_info;
import pojo.User;
import pojo.User_info;
@Controller
public class loginController{

	
	@RequestMapping("test")
	 public ModelAndView test(String url) {
		 ModelAndView mav = new ModelAndView("html/tt");
		 mav.addObject("url", url);
	     return mav;
		 
	}
	
	
	 @RequestMapping("user_login")
	 public ModelAndView cheak2login(User user) {
		 
	
	      loginCheak logincheak=new loginCheak();
	      int id=0;
	      id=(int) logincheak.CheakPass(user);
if(id!=0)
{

	    user.setId(id);
	    
		

	    md5_encryption md5_encryption=new md5_encryption();
		List<Block> block_info=new ArrayList();//鑾峰彇block鍒楄〃
		MainPageLoad mainpageloader=new MainPageLoad();
		block_info=mainpageloader.BlockListLoad();
		String md5id=md5_encryption.getHash(user.getId()+"");

	        ModelAndView mav = new ModelAndView("main");
    
	        mav.addObject("uid",md5id);
	        mav.addObject("Blist",block_info);
	        return mav;
}
else
{
	 ModelAndView mav = new ModelAndView("index");
     mav.addObject( "message","鐢ㄦ埛id鎴栧瘑鐮侀敊璇�");
     return mav;
}
	    }
	 
	 @RequestMapping("user_registered")
	 public ModelAndView user_registered(User user) {
	System.out.print("GET IT AND THE Mail"+user.getMail()+" THE PASS IS"+user.getPassword());
		 User_registered  user_registered =new User_registered ();
		if(user_registered.IsRegistered(user))
		{
			 ModelAndView mav = new ModelAndView("registered");
		       mav.addObject( user);

		       return mav;
		}
if(user_registered.registered(user) )
{
	       
	        pojo_getter pojo_getter=new pojo_getter();
			User_info user_info=pojo_getter.User_infoGetter(user.getId());
			
	


			
		        ModelAndView mav = new ModelAndView("user_info");
		        mav.addObject(user_info);
		        mav.addObject("uid",user.getMd5id());
		        mav.addObject("act_uid",user.getMd5id());
		        return mav;
}
else 
{
	   ModelAndView mav = new ModelAndView("registered");
       mav.addObject( user);

       return mav;
}

	    }
	 
	  public static Forusm_info forsumcreate(int fid,int id,String text,int uid)
	  {
			 String timePattern = "yyyy-MM-dd HH:mm:ss";
			 SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
				String formatedDate = sdf.format(new Date());
		  Forusm_info f=new Forusm_info();
		  f.setFid(fid);
		  f.setId(id);
		  f.setRe_time(formatedDate);
		  f.setText(text);
		  f.setUid(uid);
		  return f;
	  }
	 
	 public static void filesave(List<Forusm_info> forusm_infos)
	  {
		  ObjectOutputStream oos=null;
		
		  try {
			  File f = new File(System.getProperty("catalina.home")+"\\data\\forsum\\1.txt");
			  if (!f.getParentFile().exists()) {
		            boolean result = f.getParentFile().mkdirs();
			  }
			   oos = new ObjectOutputStream(new FileOutputStream(f));
		     
		   
			  oos.writeObject(forusm_infos);
			  System.out.println(f.getAbsolutePath());
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
	 

	 

}