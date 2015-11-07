package com.allenfancy.json;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ObjectToJSON {

	
	public String objectTOJson(User user){
		return JSON.toJSON(user).toString();
	}
	
	public String ArrayToJson(User[] user){
		return JSON.toJSONString(user);
	}
	
	public String ListToJson(List<User> user){
		return JSON.toJSONString(user);
	}
	
	public String MapToJson(Map<String, Object> map){
		return JSON.toJSONString(map);
	}
	
	public Object MapToJson1(Map<String,Object> map){
		return JSON.toJSON(map);
	}
}
