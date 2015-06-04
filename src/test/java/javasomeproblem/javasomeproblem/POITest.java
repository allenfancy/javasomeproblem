package javasomeproblem.javasomeproblem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.allenfancy.poi.example.ExportExcelTool;
import com.allenfancy.poi.model.Student;
/***
 * 一般在游览器中实现下载功能：
 * 需要设置response和request的的头信息如下：
 * req.setCharacterEncoding("UTF-8");
   res.setCharacterEncoding("UTF-8");
   res.setContentType("application/octet-stream;charset=utf-8");
   res.setHeader("Content-Disposition","attachment;filename="+ xlsName);
 * @author HP
 *
 */
public class POITest {

	
	@Test
	public void testPOI1(){
		String []titles = {"姓名","年龄","生日","年纪","班级"};
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("吴涛",23,new Date(),"2011","2"));
		students.add(new Student("吴涛1",23,new Date(),"2011","2"));
		students.add(new Student("吴涛2",23,new Date(),"2011","2"));
		students.add(new Student("吴涛3",23,new Date(),"2011","2"));
		students.add(new Student("吴涛4",23,new Date(),"2011","2"));
		students.add(new Student("吴涛5",23,new Date(),"2011","2"));
		ExportExcelTool excelTool = new ExportExcelTool<Student>();
		excelTool.exportExcel2(students, "我是学生", "E://student.xls", titles, "yyy-mm-dd");
		
	}
	

	
}
