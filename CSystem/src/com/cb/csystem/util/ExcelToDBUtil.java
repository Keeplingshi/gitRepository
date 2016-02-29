package com.cb.csystem.util;

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

import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.service.IStudentService;
import com.cb.system.util.SpringContextUtil;
import com.cb.system.util.ValidateUtil;

/**
 * 从excel导入数据库
 * @author chen
 *
 */
public class ExcelToDBUtil {

	private static IStudentService studentService=(IStudentService)SpringContextUtil.getBean("studentService");
	private static IJobInfoService jobInfoService=(IJobInfoService)SpringContextUtil.getBean("jobInfoService");
	
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
			//存在该学生，更新
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
				studentService.doSaveStuAndJob(studentExistsDomain);
			}else{
				studentDomain.setClassDomain(classDomain);
				studentService.doSaveStuAndJob(studentDomain);
			}
			
		}
		
		return true;
	}

	/**
	 * @param file
	 * @param classDomain
	 */
	public static void jobInfoexcelToDB(MultipartFile file,ClassDomain classDomain) throws Exception{
		// TODO Auto-generated method stub
		if(classDomain==null){
			return ;
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
					if(ValidateUtil.notEmpty(content)){
						studentDomain.setStuId(content);
					}
					i++;					
				case 1:	//姓名
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
					content=cell.getStringCellValue().trim();
					if(ValidateUtil.notEmpty(content)){
						studentDomain.setName(content);
					}
					i++;					
				case 2:	//性别
					cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					content=cell.getStringCellValue().trim();
					content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.SEX_TYPE);
					if(ValidateUtil.notEmpty(content)){
						studentDomain.setSex(Integer.valueOf(content));
					}
					i++;
				default:
			}

			String stuId=studentDomain.getStuId();
			StudentDomain studentExistsDomain=studentService.doGetByStudentId(stuId);
			//存在该学生，更新
			if(studentExistsDomain!=null){
				//如果学生已经存在，则就业信息存在，取出赋值，保存
				JobInfoDomain jobInfoDomain=studentExistsDomain.getJobInfo();
				switch (i) {			
					case 3://签约状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.CONTRACTSTATUS_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setContractStatus(Integer.valueOf(content));
						}
						i++;					
					case 4://签约单位
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setCompany(content);
						}
						i++;					
					case 5://协议书状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.PROTOCALSTATE_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setProtocalState(Integer.valueOf(content));
						}
						i++;					
					case 6://当前状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.NOWSTATE_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setNowState(Integer.valueOf(content));
						}
						i++;					
					case 7://城市
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setCity(content);
						}
						i++;					
					case 8://薪金
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setSalary(Integer.valueOf(content));
						}
						i++;					
					case 9://备注
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setNote(content);
						}
						i++;
					default:
				}
				jobInfoService.doSave(jobInfoDomain);
				
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
				studentService.doSaveStuAndJob(studentExistsDomain);	//update
			}else{
				//如果学生不存在，则创建就业信息
				JobInfoDomain jobInfoDomain=new JobInfoDomain();
				switch (i) {			
					case 3://签约状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.CONTRACTSTATUS_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setContractStatus(Integer.valueOf(content));
						}
						i++;					
					case 4://签约单位
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setCompany(content);
						}
						i++;					
					case 5://协议书状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.PROTOCALSTATE_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setProtocalState(Integer.valueOf(content));
						}
						i++;					
					case 6://当前状态
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						content=CodeBookHelper.getValueByNameAndType(content, CodeBookConstsType.NOWSTATE_TYPE);
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setNowState(Integer.valueOf(content));
						}
						i++;					
					case 7://城市
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setCity(content);
						}
						i++;					
					case 8://薪金
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setSalary(Integer.valueOf(content));
						}
						i++;					
					case 9://备注
						cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK); 
						cell.setCellType(Cell.CELL_TYPE_STRING);  
						content=cell.getStringCellValue().trim();
						if(ValidateUtil.notEmpty(content)){
							jobInfoDomain.setNote(content);
						}
						i++;
					default:
				}
				
				jobInfoDomain.setStudent(studentDomain);
				jobInfoService.doSave(jobInfoDomain);
				studentDomain.setJobInfo(jobInfoDomain);
				studentDomain.setClassDomain(classDomain);
				studentService.doSave(studentDomain);	//save
				
			}

		}
		
	}
	
}
