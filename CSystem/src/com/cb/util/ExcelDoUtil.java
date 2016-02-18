package com.cb.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * excel文件操作类
 * @author chen
 *
 */
public class ExcelDoUtil {

	/**
	 * 保存excel文件
	 * @param request
	 * @param savePath 保存路径
	 * @return
	 */
	public static String saveFile(HttpServletRequest request,String savePath)
	{
		String filePath=request.getServletContext().getRealPath("/")+"/WEB-INF/fileSave/"+savePath+"/";
		File fileDir=new File(filePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
        	List<?> items = upload.parseRequest(request);
        	Iterator<?> it = items.iterator();
        	while (it.hasNext()) {
        		FileItem item = (FileItem) it.next();
        		if (!item.isFormField()) { 
        			filePath+=item.getName();
        			if (item.getName() != null && !item.getName().equals("")) {   
        				File file = new File(filePath);
        				item.write(file);
        			}
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
        
        return filePath;
	}
	
}
