package com.cb.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cb.domain.StudentDomain;

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
			
			OutputStream out = new FileOutputStream(path);
			workbook.write(out);
			out.close();
			b=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
}
