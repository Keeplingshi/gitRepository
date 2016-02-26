package com.cb.csystem.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.util.bean.JobInfoCountBean;
import com.cb.system.util.DateUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.SelectItem;

/**
 * 从数据库导出信息到excel文件
 * @author chen
 *
 */
public class DBToExcelUtil {

	/**
	 * 将学生信息写入excel文件
	 * @param studentDomains
	 * @return
	 */
	@SuppressWarnings("resource")
	public static boolean studnetinfoDBToExcel(List<StudentDomain> studentDomains,String path)
	{
		boolean b=false;
		String[] headers = { "学号", "姓名", "性别", "出生日期","政治面貌","身份证号","籍贯","宿舍号","班级","电子邮件","联系电话","手机号" };
		int columnNum=headers.length;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("学生基本信息");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < columnNum; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		int index=0;
		HSSFCell[] cells=new HSSFCell[columnNum];
		for(StudentDomain studentDomain:studentDomains)
		{
			index++;
			row = sheet.createRow(index);
			for(int i=0;i<columnNum;i++){
				cells[i]=row.createCell(i);
			}
			cells[0].setCellValue(studentDomain.getStuId());
			cells[1].setCellValue(studentDomain.getName());
			if(studentDomain.getSex()!=null){
				cells[2].setCellValue(CodeBookHelper.getNameByValueAndType(studentDomain.getSex().toString(), CodeBookConstsType.SEX_TYPE));
			}
			cells[3].setCellValue(DateUtil.getDayFormat(studentDomain.getBirthday()));
			if(studentDomain.getPoliticalStatus()!=null){
				cells[4].setCellValue(CodeBookHelper.getNameByValueAndType(studentDomain.getPoliticalStatus().toString(), CodeBookConstsType.POLITICALSTATUE_TYPE));
			}
			cells[5].setCellValue(studentDomain.getIDnumber());
			cells[6].setCellValue(studentDomain.getNativePlace());
			cells[7].setCellValue(studentDomain.getDormitory());
			cells[8].setCellValue(studentDomain.getClassDomain().getName());
			cells[9].setCellValue(studentDomain.getEmail());
			cells[10].setCellValue(studentDomain.getTelephone());
			cells[11].setCellValue(studentDomain.getCellphone());
			
		}
		
		try {
			//首先创建文件
			if(FileUtil.createFile(path)){
				OutputStream out = new FileOutputStream(path);
				workbook.write(out);
				out.close();
				b=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 导出就业信息数据
	 * @param jobInfoDomains
	 * @param path
	 * @return
	 */
	@SuppressWarnings("resource")
	public static boolean jobInfoDBToExcel(List<JobInfoDomain> jobInfoDomains,List<SelectItem> selectList,String path)
	{
		boolean b=false;
		String[] headers = { "学号", "姓名", "性别", "签约状态","签约单位","协议书状态","当前状态","城市","薪金/月","备注"};
		int columnNum=headers.length;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("就业信息");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < columnNum; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		int index=0;
		HSSFCell[] cells=new HSSFCell[columnNum];
		for(JobInfoDomain jobInfoDomain:jobInfoDomains)
		{
			index++;
			row = sheet.createRow(index);
			for(int i=0;i<columnNum;i++){
				cells[i]=row.createCell(i);
			}
			StudentDomain studentDomain=jobInfoDomain.getStudent();
			if(studentDomain!=null){
				//学号
				cells[0].setCellValue(studentDomain.getStuId());
				//姓名
				cells[1].setCellValue(studentDomain.getName());
				//性别
				if(studentDomain.getSex()!=null){
					cells[2].setCellValue(CodeBookHelper.getNameByValueAndType(studentDomain.getSex().toString(), CodeBookConstsType.SEX_TYPE));
				}
				//签约状态
				if(jobInfoDomain.getContractStatus()!=null){
					cells[3].setCellValue(CodeBookHelper.getNameByValueAndType(jobInfoDomain.getContractStatus().toString(), CodeBookConstsType.CONTRACTSTATUS_TYPE));
				}
				//签约单位
				cells[4].setCellValue(jobInfoDomain.getCompany());
				//协议书状态
				if(jobInfoDomain.getProtocalState()!=null){
					cells[5].setCellValue(CodeBookHelper.getNameByValueAndType(jobInfoDomain.getProtocalState().toString(), CodeBookConstsType.PROTOCALSTATE_TYPE));
				}
				//当前状态
				if(jobInfoDomain.getNowState()!=null){
					cells[6].setCellValue(CodeBookHelper.getNameByValueAndType(jobInfoDomain.getNowState().toString(), CodeBookConstsType.NOWSTATE_TYPE));
				}
				//城市
				cells[7].setCellValue(jobInfoDomain.getCity());
				//薪金
				if(jobInfoDomain.getSalary()!=null){
					cells[8].setCellValue(jobInfoDomain.getSalary());
				}
				//备注
				cells[9].setCellValue(jobInfoDomain.getNote());
			}
		}
		
		//统计信息写入excel
		index+=3;
		row = sheet.createRow(index);
		for(int i=0;i<selectList.size();i++)
		{
			cells[i]=row.createCell(i);
			cells[i].setCellValue(selectList.get(i).getSelectText());
		}
		index++;
		row = sheet.createRow(index);
		for(int i=0;i<selectList.size();i++)
		{
			cells[i]=row.createCell(i);
			cells[i].setCellValue(selectList.get(i).getSelectValue());
		}
		
		try {
			//首先创建文件
			if(FileUtil.createFile(path)){
				OutputStream out = new FileOutputStream(path);
				workbook.write(out);
				out.close();
				b=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 就业统计信息导出
	 * @param selectList
	 * @param path
	 * @return
	 */
	@SuppressWarnings("resource")
	public static boolean jobInfoCountDBToExcel(List<JobInfoCountBean> jobInfoCountBeans,String path)
	{
		boolean b=false;
		String[] headers = { "班级", "班级人数", "保研", "考研","已签约","未签约","考公务员","已签约平均工资","出国","统计日期"};
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("就业统计信息");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		
		// 产生表格标题行
		int columnNum=headers.length;
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < columnNum; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		int index=0;
		HSSFCell[] cells=new HSSFCell[columnNum];
		for(JobInfoCountBean jobInfoCountBean:jobInfoCountBeans)
		{
			index++;
			row = sheet.createRow(index);
			for(int i=0;i<columnNum;i++){
				cells[i]=row.createCell(i);
			}
			
			//班级名称
			cells[0].setCellValue(jobInfoCountBean.getClassName());
			//班级人数
			cells[1].setCellValue(jobInfoCountBean.getClassMemberNum());
			//保研
			cells[2].setCellValue(jobInfoCountBean.getContractState_C());
			//考研
			cells[3].setCellValue(jobInfoCountBean.getContractState_D());
			//已签约
			cells[4].setCellValue(jobInfoCountBean.getContractState_A());
			//未签约
			cells[5].setCellValue(jobInfoCountBean.getContractState_B());
			//考公务员
			cells[6].setCellValue(jobInfoCountBean.getContractState_E());
			//已签约平均工资
			cells[7].setCellValue(jobInfoCountBean.getAverageSalary());
			//出国
			cells[8].setCellValue(jobInfoCountBean.getContractState_F());
			//统计日期
			cells[9].setCellValue(jobInfoCountBean.getCountDate());

		}
		
		try {
			//首先创建文件
			if(FileUtil.createFile(path)){
				OutputStream out = new FileOutputStream(path);
				workbook.write(out);
				out.close();
				b=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
}
