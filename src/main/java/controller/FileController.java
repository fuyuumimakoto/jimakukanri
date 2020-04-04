package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import staticvar.FileVar;
/**
 * 下载  测试
 * @author 王一土
 *
 */
@Controller
@RequestMapping("file")
public class FileController {

    @RequestMapping(value="/up_usericon",method=RequestMethod.POST)
    @ResponseBody  
    public String upload(@RequestParam MultipartFile file,HttpServletRequest request,String uid) throws IOException{  
    	System.out.print("file:\n"+ file.getOriginalFilename());
    	if (!file.getOriginalFilename().equals("")) {
    		 String path = FileVar.Absolute_headurl+"//user//"+uid;
    	        String fileName = file.getOriginalFilename();    
    	        File dir = new File(path,fileName);          
    	        if (!dir.getParentFile().exists()) {
    	            boolean result = dir.getParentFile().mkdirs();
    		  }
    	 
    	        file.transferTo(dir);  
    	        return fileName;
		}
       
      
        return "kong";  
    }  
    
       @RequestMapping("/down")  
        public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
           
            String fileName =  FileVar.Absolute_headurl+"/icon.jpg";  
            
            InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
              
            String filename = "下载文件.jpg";  
            
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
}