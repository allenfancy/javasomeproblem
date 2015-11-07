package javasomeproblem.javasomeproblem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.allenfancy.entity.ContactDto;
/***
 * 
 * @author allen
 *Set<String> :可以去掉其中重复的数据。
 *但是如果想要根据一个对象中某一个属性相同去掉重复的数据，就需要在这个对象中实现hashcode和equal方法
 *在equal和hashcode中用对应的字段去实现，
 *例如：
 *在contactDto中，我根据邮件地址相同去重
 *
 *
 */
public class ContactDtoTest {

	@Test
	public void testDto(){
		Set<ContactDto> list = new HashSet<ContactDto>();
		ContactDto d1 = new ContactDto();
		d1.setEmailAddress("allen1@qq.com");
		d1.setOther("allenFancy");
		d1.setUsername("allen1");
		ContactDto d2 = new ContactDto();
		d2.setEmailAddress("allen@qq.com");
		d2.setOther("allenFancy1");
		d2.setUsername("allen1");
		ContactDto d3 = new ContactDto();
		d3.setEmailAddress("allen@qq.com");
		d3.setOther("allenFancy2");
		d3.setUsername("allen1");
		ContactDto d4 = new ContactDto();
		d4.setEmailAddress("allen@qq.com");
		d4.setOther("allenFancy");
		d4.setUsername("allen1");
		ContactDto d5 = new ContactDto();
		d5.setEmailAddress("allen@qq.com");
		d5.setOther("allenFancy");
		d5.setUsername("allen1");
		
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
		
		System.out.println(list.size());
	}
}
