package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.MainPageLoad;
import logic.UserOP;
import logic.pojo_getter;
import pojo.Block;
import pojo.User_info;

import staticvar.FileVar;

@Controller
public class normalController {
	@RequestMapping("Main")
	 public ModelAndView jump2Main(String uid) {
		
		 
			List<Block> block_info=new ArrayList();//获取block列表
			MainPageLoad mainpageloader=new MainPageLoad();
			block_info=mainpageloader.BlockListLoad();

		        ModelAndView mav = new ModelAndView("main");
		
		        mav.addObject("uid",uid);
		        mav.addObject("Blist",block_info);
		return mav;
		
	}
	
	

	
	@RequestMapping("user_info")
	 public ModelAndView jump2userPage(String uid,String act_uid) {
		pojo_getter pojo_getter=new pojo_getter();
		int int_uid=pojo_getter.md5id2id(uid);
		User_info user_info=pojo_getter.User_infoGetter(int_uid);


		
	        ModelAndView mav = new ModelAndView("user_info");
	        mav.addObject(user_info);
	        mav.addObject("uid",uid);
	        mav.addObject("act_uid",act_uid);
	        return mav;
	    }
	
	@RequestMapping("jump2Index")
	 public ModelAndView jump2Index() {
	

			MainPageLoad mainpageloader=new MainPageLoad();


		        ModelAndView mav = new ModelAndView("index");
		
		return mav;
		
	}
}
