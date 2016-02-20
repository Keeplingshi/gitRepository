package com.cb.util;

import java.io.IOException;
import java.util.Date;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cb.domain.ClassDomain;
import com.cb.domain.StudentDomain;
import com.cb.service.IStudentService;

/**
 * 从excel导入数据库
 * @author chen
 *
 */
public class ExcelToDBUtil {

	private static IStudentService studentService=(IStudentService)SpringContextUtil.getBean("studentService");
	
	/**
	 * 从excel中录入学生信息
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public static boolean studentInfoexcelToDB(MultipartFile file,ClassDomain classDomain) throws Exception
	{
		if(classDomain==null){
			return false;
		}
		
		Workbook wb=null;
		
		try {
			wb = WorkbookFactory.create(file.getInputStream());
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
			int i=0;
			switch (i) {
				case 0:	//学号
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setStuId(content);
					}
					i++;					
				case 1:	//姓名
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setName(content);
					}
					i++;					
				case 2:	//性别
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.SEX_TYPE);
					if(content!=null&&!"".equals(content)){
						studentDomain.setSex(Integer.valueOf(content));
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
					}
					i++;					
				case 5:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setIDnumber(content);
					}
					i++;					
				case 6:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setNativePlace(content);
					}
					i++;					
				case 7:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setDormitory(content);
					}
					i++;					
				case 8:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(ValidateUtil.checkEmail(content)){
						studentDomain.setEmail(content);
					}
					i++;					
				case 9:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(content!=null&&!"".equals(content)){
						studentDomain.setTelephone(content);
					}
					i++;
				case 10:
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					if(ValidateUtil.checkMobileNumber(content)){
						studentDomain.setCellphone(content);
					}
					i++;
				default:
			}

			String stuId=studentDomain.getStuId();
			StudentDomain studentExistsDomain=studentService.doGetByStudentId(stuId);
			if(studentExistsDomain!=null){
				studentExistsDomain.setName(studentDomain.getName());
				studentExistsDomain.setSex(studentDomain.getSex());
				studentExistsDomain.setBirthday(studentDomain.getBirthday());
				studentExistsDomain.setPoliticalStatus(studentDomain.getPoliticalStatus());
				studentExistsDomain.setIDnumber(studentDomain.getIDnumber());
				studentExistsDomain.setNativePlace(studentDomain.getNativePlace());
				studentExistsDomain.setDormitory(studentDomain.getDormitory());
				studentExistsDomain.setClassDomain(classDomain);
				studentExistsDomain.setEmail(studentDomain.getEmail());
				studentExistsDomain.setTelephone(studentDomain.getTelephone());
				studentExistsDomain.setCellphone(studentDomain.getCellphone());
				studentService.doSave(studentExistsDomain);
			}else{
				studentDomain.setClassDomain(classDomain);
				studentService.doSave(studentDomain);
			}
			
		}
		
		return true;
	}
	
}
