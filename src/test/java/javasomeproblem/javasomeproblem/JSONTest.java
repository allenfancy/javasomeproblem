package javasomeproblem.javasomeproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenfancy.json.ObjectToJSON;
import com.allenfancy.json.User;

public class JSONTest {

	@Test
	public void testObjectToJson(){
		ObjectToJSON otj = new ObjectToJSON();
		User user = new User();
		user.setName("allen");
		user.setAddress("sh");
		user.setAge(25);
		user.setOther("other");
		System.out.println(otj.objectTOJson(user));
	}
	
	@Test
	public void testObjectToArray(){
		ObjectToJSON otj = new ObjectToJSON();
		User user = new User();
		user.setName("allen");
		user.setAddress("sh");
		user.setAge(25);
		user.setOther("other");
		User user1 = new User();
		user1.setName("allen1");
		user1.setAddress("sh1");
		user1.setAge(25);
		user1.setOther("other1");
		User []users = {user,user1};
		System.out.println(otj.ArrayToJson(users));
	}
	
	
	@Test
	public void testObjectToList(){
		ObjectToJSON otj = new ObjectToJSON();
		User user = new User();
		user.setName("allen");
		user.setAddress("sh");
		user.setAge(25);
		user.setOther("other");
		User user1 = new User();
		user1.setName("allen1");
		user1.setAddress("sh1");
		user1.setAge(25);
		user1.setOther("other1");
		List<User> lists = new ArrayList<User>();
		lists.add(user1);
		lists.add(user);
		System.out.println(otj.ListToJson(lists));
	}
	
	
	@Test
	public void testObjectToMap(){
		ObjectToJSON otj = new ObjectToJSON();
		User user = new User();
		user.setName("allen");
		user.setAddress("sh");
		user.setAge(25);
		user.setOther("other");
		User user1 = new User();
		user1.setName("allen1");
		user1.setAddress("sh1");
		user1.setAge(25);
		user1.setOther("other1");
		List<User> lists = new ArrayList<User>();
		lists.add(user1);
		lists.add(user);
		
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("map1", "map1");
		map.put("user1", user1);
		map.put("a", 1);
		map.put("b", "2");
		map.put("c", false);
		map.put("d", 1.2);
		map.put("e", 1.2f);
		map.put("arrays", new int[]{1,2,4,5});
		map.put("lists", lists);
		map.put("maps", map1);
		System.out.println(otj.MapToJson1(map));
		
	}
	@Test
	public void testJSONObject(){
		JSONObject json = JSON.parseObject("{\"a\":1,\"arrays\":[1,2,4,5],\"b\":\"2\",\"c\":false,\"d\":1.2,\"e\":1.2,\"lists\":[{\"address\":\"sh1\",\"age\":25,\"id\":0,\"name\":\"allen1\",\"other\":\"other1\"},{\"address\":\"sh\",\"age\":25,\"id\":0,\"name\":\"allen\",\"other\":\"other\"}],\"maps\":{\"map1\":\"map1\"},\"user1\":{\"address\":\"sh1\",\"age\":25,\"id\":0,\"name\":\"allen1\",\"other\":\"other1\"}}");
		String a = String.valueOf(json.get("a"));
		System.out.println(json.get("arrays"));
		System.out.println(json.get("lists"));
	
	}
}

