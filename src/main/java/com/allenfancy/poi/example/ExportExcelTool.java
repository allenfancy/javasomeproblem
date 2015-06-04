package com.allenfancy.poi.example;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExportExcelTool<T> {

	/***
	 * 
	 * @param dataset    导出的数据集合
	 * @param xlsName    excel的名字
	 * @param res		 请求流【输出流】
	 * @param firstRow	第一行的标题栏数据
	 * @param pattern   时间格式
	 */
	public void exportExcel(Collection<T> dataset,String xlsName,HttpServletResponse res,String[] firstRow, String pattern){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(xlsName);
		//设置表格的宽度
		sheet.setDefaultColumnWidth((short) 25);	
		// 生成title的样式
		HSSFCellStyle style = setTitleStyle(workbook);
		// 生成content的样式
		HSSFCellStyle style2 = setContentStyle(workbook);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < firstRow.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);//设置标题的样式
			HSSFRichTextString text = new HSSFRichTextString(firstRow[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();

			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				//设置样式
				cell.setCellStyle(style2);
				//获取字段
				Field field = fields[i];
				//获取字段名
				String fieldName = field.getName();
				//拼接getXxxx()方法
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					//得到方法
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					//得到value的值
					Object value = getMethod.invoke(t, new Object[] {});
					//解析数据然后填充excel中
					resolveValueObject(cell,value,pattern);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			
			OutputStream  out = res.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param dataset
	 * @param xlsName
	 * @param path
	 * @param firstRow
	 * @param pattern
	 */
	public void exportExcel2(Collection<T> dataset,String xlsName,String path,String[] firstRow, String pattern){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(xlsName);
		//设置表格的宽度
		sheet.setDefaultColumnWidth((short) 25);	
		// 生成title的样式
		HSSFCellStyle style = setTitleStyle(workbook);
		// 生成content的样式
		HSSFCellStyle style2 = setContentStyle(workbook);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < firstRow.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);//设置标题的样式
			HSSFRichTextString text = new HSSFRichTextString(firstRow[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();

			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				//设置样式
				cell.setCellStyle(style2);
				//获取字段
				Field field = fields[i];
				//获取字段名
				String fieldName = field.getName();
				//拼接getXxxx()方法
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					//得到方法
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					//得到value的值
					Object value = getMethod.invoke(t, new Object[] {});
					//解析数据然后填充excel中
					resolveValueObject(cell,value,pattern);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			
			OutputStream  out = new FileOutputStream(path);
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/****
	 * 解析參數
	 * @param cell
	 * @param value
	 * @param pattern
	 * @return
	 */
	private HSSFCell resolveValueObject(HSSFCell cell,Object value,String pattern){
		//如果有其他類型的擴展 在下面添加就行
		String textValue = null;
		if(value instanceof Integer){
			cell.setCellValue(Integer.parseInt(value.toString()));
		}else if (value instanceof Double) {
			cell.setCellValue(Double.parseDouble(value.toString()));
		}else if(value instanceof Long){
			cell.setCellValue(Long.parseLong(value.toString()));
		}else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			textValue = sdf.format(date);
			cell.setCellValue(textValue);
		}else {
			if(value != null){
				textValue = value.toString();
				//textValue = convertString(textValue);
				cell.setCellValue(textValue);
			}else{
				//textValue = "";
				cell.setCellValue(textValue);
			}	
		}
		return cell;
	}
	
	/***
	 * 设置标题的样式
	 * @param workbook
	 * @return
	 */
	private HSSFCellStyle setTitleStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);//设置背景颜色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index); //设置字体的颜色
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	
	/***
	 * 设置内容中的样式
	 * @param workbook
	 * @return
	 */
	private HSSFCellStyle setContentStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);//设置背景的颜色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	
	/**
	 * 状态转换
	 * @param status
	 * @return
	 */
	/*private String convertString(String status){
		return so
	}*/
}
