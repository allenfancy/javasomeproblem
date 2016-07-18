package com.allenfancy.network.ch04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;


public class PoolWebLog {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String file = "/Users/allen/temp/";
		ExecutorService service = Executors.newFixedThreadPool(4);
		Queue<LogEntry> results = new LinkedBlockingDeque<LogEntry>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"))){
			for(String entry = reader.readLine();entry != null;entry = reader.readLine()){
				LookUpTask task = new LookUpTask(entry);
				Future<String> future = service.submit(task);
				LogEntry result = new LogEntry(entry, future);
				results.add(result);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		for(LogEntry le : results){
			System.out.println(le.getFuture().get());
		}
		service.shutdown();
	}
}
