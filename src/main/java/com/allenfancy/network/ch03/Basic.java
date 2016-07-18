package com.allenfancy.network.ch03;

import java.util.concurrent.ExecutionException;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 * 
 *
 */
public class Basic {

	public static void main(String[] args){
		/*String filename = "/Users/allen/temp/xxx.csv";
		ReturnDigest dr = new ReturnDigest(filename);
		dr.start();
		
		StringBuilder result = new StringBuilder(filename);
		result.append(" : ");
		byte[] digest = dr.getDigest();
		System.out.println(digest.length);
		//result.append(DatatypeConverter.printHexBinary(digest));
		System.out.println(result);*/
		
		int []data = {1,23,34,321,2,4324,43254,546,767,6571,32,5787,989,90,1324,479,996,86,66,43665,565,656,546,65464,6546,2};
		try {
			int max = MultiThreadMaxFinder.max(data);
			System.out.println(max);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
