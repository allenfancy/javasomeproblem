package com.allenfancy.distributed.ch01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProtocolUtil {

	public static Request readRequest(InputStream input) throws IOException{
		byte[] encodeByte = new byte[1];
		input.read(encodeByte);
		byte encode = encodeByte[0];
		
		byte[] commandLengthBytes = new byte[4];
		input.read(commandLengthBytes);
		
		int commandLength = ByteUtils.bytes2Int(commandLengthBytes);
		
		//读取命令
		byte[] commandBytes = new byte[commandLength];
		input.read(commandBytes);
		String command = "";
		if("GBK".getBytes()[0] == encode){
			command = new String(commandBytes,"GBK");
		}else{
			command = new String(commandBytes,"UTF-8");
		}
		Request request = new Request();
		request.setCommand(command);
		request.setEncode(encode);
		request.setCommandLength(commandLength);
		return request;
	}
	
	public static void writeResponse(OutputStream output,Response response) throws IOException{
		output.write(response.getEncode());
		output.write(ByteUtils.int2ByteArray(response.getResponseLength()));
		
		if("GBK".getBytes()[0] == response.getEncode()){
			output.write(response.getRepsone().getBytes("GBK"));
		}else{
			output.write(response.getRepsone().getBytes("UTF-8"));
		}
		output.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
