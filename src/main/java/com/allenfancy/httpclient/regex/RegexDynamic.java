package com.allenfancy.httpclient.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/***
 * 正则表达式，动态去替换一些东西
 * @author HP
 *
 */
public class RegexDynamic {

	private static final String REGEX_PATTERN_STRING = "\\{\\{(.*?)\\}}";
	
	public static void main(String[] args) {
		Pattern p = Pattern.compile(REGEX_PATTERN_STRING);
		String subject = "{{name}}您好!我现在在研究一些东西!谢谢{{name}}!";
		Matcher m = p.matcher(subject);
		boolean b = m.find();
		StringBuffer sb = new StringBuffer();
		while (b) {
			//String s = m.group(1);
			m.appendReplacement(sb,"allen");
			b = m.find();
		}
		m.appendTail(sb);
		
		System.out.println(sb.toString());
	}


}
