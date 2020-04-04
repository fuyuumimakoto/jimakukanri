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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.ForusmPageLoader;
import logic.ForusmPoster;
import logic.pojo_getter;
import pojo.Block;
import pojo.Forusm;
import pojo.Forusm_info;
import pojo.User;
import staticvar.FileVar;

@Controller
public class BlockPageController {
	
	
	
	 @RequestMapping("forusm")
	 public ModelAndView jump2forusm(int fid,int uid,int bid,int pagenum) {
		
		 String stringuid=uid+""; //鍙栧緱鐢ㄦ埛id
		 String stringbid=bid+""; //鍙栧緱鍒嗗尯id
		 String url=FileVar.Absolute_headurl+"//forusm//"+fid+".data"; //鐢ㄥ垎鍖篿d鍜屽笘瀛恑d寰楀埌瀛樺彇url
		 List<Forusm_info> FInfoList=new ArrayList(); //鍒濆鍖栧笘瀛愬唴瀹瑰璞″垪琛�
		 ForusmPageLoader forusmPageLoader=new  ForusmPageLoader();
		 FInfoList= forusmPageLoader.Forusm_infoListLoader(url);//閫氳繃鏂囦欢璇诲彇甯栧瓙鍐呭
		 
		
			
		 
		 ModelAndView mav = new ModelAndView("forusm");
		 
		
		
			
			pojo_getter pojo_getter=new pojo_getter();
			Block block=pojo_getter.BlockGetter(bid);//鑾峰彇block瀵硅薄			
			mav.addObject("pagenum", pagenum);
		mav.addObject(block);
	        mav.addObject("FInfoList",FInfoList);
	        mav.addObject("uid",stringuid);
	        return mav;
		 
	 }
	 
	 @RequestMapping("Postforusm")
	 public ModelAndView postforusm(int bid,int uid,String text,String title) {
		 ModelAndView mav = new ModelAndView("forusm");
		 String stringuid =uid+"";
		
	
	
	

	postforusmInit(bid, title, uid, text,mav);
	
	 
	
	 mav.addObject("uid",stringuid);

		 
		
		    return mav;
	 }
	 
	 
	 public void postforusmInit(int bid,String title,int uid,String text,ModelAndView mav)
	 {
		 int fid;
		 String timePattern = "yyyy-MM-dd HH:mm:ss";
		 SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
			String formatedDate = sdf.format(new Date());
		 ForusmPoster forusmPoster=new ForusmPoster();
	Forusm postforusm=new Forusm();
	postforusm.setBid(bid);
	postforusm.setTitle(title);
	postforusm.setUid(uid);
	postforusm.setCreate_time(formatedDate);
	postforusm.setRe_time(formatedDate);
		 //build forusm object
	
	fid=forusmPoster.CreateForusm(postforusm);
	
	
		Forusm_info forusm_info =new Forusm_info ();
	forusm_info.setFid(fid);
	forusm_info.setRe_time(formatedDate);
	forusm_info.setText(text);
	forusm_info.setUid(uid);
	
	
	//build forusm_info object
	
	List<Forusm_info> FInfoList=new ArrayList(); 
	FInfoList.add(forusm_info);
	String url=FileVar.Absolute_headurl+"//forusm//"+fid+".data";
	filesave(FInfoList, url);
	
	
	pojo_getter pojo_getter=new pojo_getter();
	
	 mav.addObject("FInfoList",FInfoList);
	 mav.addObject(pojo_getter.BlockGetter(bid));
	
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
	 
}
