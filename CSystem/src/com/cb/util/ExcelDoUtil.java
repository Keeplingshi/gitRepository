package com.cb.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.cb.domain.ClassDomain;
import com.cb.domain.StudentDomain;
import com.cb.service.IClassService;
import com.cb.service.IStudentService;

/**
 * excel文件操作类
 * @author chen
 *
 */
public class ExcelDoUtil {

	private static IStudentService studentService=(IStudentService)SpringContextUtil.getBean("studentService");
	private static IClassService classService=(IClassService)SpringContextUtil.getBean("classService");
	
	/**
	 * 从excel中录入学生信息
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static boolean studentInfoexcelToDB(File file,String classId) throws Exception
	{
		if(!file.exists()){
			return false;
		}
		
		ClassDomain classDomain=classService.doGetById(classId);
		if(classDomain==null){
			return false;
		}
		
		Workbook wb=null;
		
		try {
			wb = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(0);
		
		for(Row row:sheet){
			if(row.getRowNum()==0){
				continue;
			}
			StudentDomain studentDomain=new StudentDomain();
			String content=null;
			Cell cell=null;
			boolean flag=true;
			int i=0;
			switch (i) {
				case 0:	//学号
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setStuId(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 1:	//姓名
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setName(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 2:	//性别
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.SEX_TYPE);
					if(content!=null&&!"".equals(content)){
						studentDomain.setSex(Integer.valueOf(content));
					}else{
						flag=false;
						break;
					}
					i++;				
				case 3:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					Date date=cell.getDateCellValue();
					studentDomain.setBirthday(date);
					i++;					
				case 4:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.POLITICALSTATUE_TYPE);
					if(content!=null&&!"".equals(content)){
						studentDomain.setPoliticalStatus(Integer.valueOf(content));
					}else{
						flag=false;
						break;
					}
					i++;					
				case 5:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setIDnumber(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 6:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setNativePlace(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 7:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setDormitory(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 8:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setEmail(content);
					}else{
						flag=false;
						break;
					}
					i++;					
				case 9:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setTelephone(content);
					}else{
						flag=false;
						break;
					}
					i++;
				case 10:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setCellphone(content);
					}else{
						flag=false;
						break;
					}
					i++;
				default:
					break;
			}

			if(!flag){
				continue;
			}
			
			String stuId=studentDomain.getStuId();
			StudentDomain studentExistsDomain=studentService.doGetByStudentId(stuId);
			if(studentExistsDomain!=null){
				studentDomain.setId(studentExistsDomain.getId());
				studentExistsDomain=null;
			}
			studentDomain.setClassDomain(classDomain);
			studentService.doSave(studentDomain);
			
		}
		
		return true;
	}
	
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
