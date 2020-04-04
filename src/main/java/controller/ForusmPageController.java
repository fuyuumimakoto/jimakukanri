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
import pojo.Forusm_info;



@Controller
public class ForusmPageController {
	public String headurl=System.getProperty("catalina.home")+"\\data";
	
	 @RequestMapping("reforusm")
	 public ModelAndView postforusm(int fid,int uid,String text,int bid,int pagenum) {
		 Forusm_info reforusm_info =new Forusm_info ();
		 String stringuid =uid+"";
		 ModelAndView mav = new ModelAndView("forusm");
		 reforusm(fid, uid, text, mav, bid);
		 mav.addObject("uid",stringuid);
			 mav.addObject("pagenum",pagenum);
			
			    return mav;
	 }
	 
	 
	 public void reforusm(int fid,int uid,String text,ModelAndView mav,int bid)
	 {
		 Forusm_info reforusm_info =new Forusm_info ();
		 String timePattern = "yyyy-MM-dd HH:mm:ss";
		 SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
			String formatedDate = sdf.format(new Date());
		 reforusm_info.setFid(fid);
		 reforusm_info.setUid(uid);
		 reforusm_info.setRe_time(formatedDate);
		 reforusm_info.setText(text);
		 //reforusm object craete
		 
		 String url=headurl+"//forusm//"+fid+".data";
		 
		 List<Forusm_info> FInfoList=forusmFileRead(url);
		 //forusm_info load
		 
		 FInfoList.add(reforusm_info);
		 //add reforusm_info
		
		 mav.addObject("FInfoList",FInfoList);
			forusmFilesave(FInfoList, url);
		 pojo_getter pojo_getter=new pojo_getter();
			
		
		 mav.addObject(pojo_getter.BlockGetter(bid));
		 
	 }
	 
	 public static void forusmFilesave(List<Forusm_info> forusm_infos,String url)
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
	 public static List<Forusm_info> forusmFileRead(String url) {
		  

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
