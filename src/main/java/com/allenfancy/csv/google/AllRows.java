package com.allenfancy.csv.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;


public class AllRows {

	public static void main(String[] args) throws IOException{
		long start = System.currentTimeMillis();
		URL url = new URL("http://static12.inboundmarketing.cn/public/contacts/contact2.csv");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream in = connection.getInputStream();
		Reader reader = new InputStreamReader(in,"GBK");
		CSVStrategy strategy = new CSVStrategy(',', '"', '#', true, true);
		CSVReader<String[]> csvReader = new CSVReaderBuilder<String[]>(reader).entryParser(new DefaultCSVEntryParser()).strategy(strategy).build();
		List<String[]> list = csvReader.readAll();
		for(int i = 0 ; i < list.size();i ++){
			String[] s = list.get(i);
			System.out.println(s[0]);
			for(String ss : s){
				
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("读取5W条数据所需要的数据 ： " + (end - start) + "ms");
	}
}
