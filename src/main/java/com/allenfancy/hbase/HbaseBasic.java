package com.allenfancy.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseBasic {

	private static Configuration conf = null;
	static{
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "localhost");
	}
	
	public static void createTable(String tableName,String[] family) throws MasterNotRunningException, ZooKeeperConnectionException, IOException{
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor desc = new HTableDescriptor(tableName);
		for(int i = 0 ; i < family.length;i++){
			desc.addFamily(new HColumnDescriptor(family[i]));
		}
		if(admin.tableExists(tableName)){
			System.out.println("Table Exists!");
			System.exit(0);
		}else{
			admin.createTable(desc);
			System.out.println("Create table Success!");
		}
	}
	
	public static void addData(String rowKey,String tableName,String[] column1,String[]value1,String[]value2,String []column2) throws IOException{
		Put put = new Put(Bytes.toBytes(rowKey));//设置KEY
		HTable table = new HTable(conf,Bytes.toBytes(tableName));
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();
		
		for(int i = 0 ; i < columnFamilies.length ; i ++){
			String familyName = columnFamilies[i].getNameAsString();
			if (familyName.equals("article")) { // article列族put数据  
                for (int j = 0; j < column1.length; j++) {  
                    put.add(Bytes.toBytes(familyName),  
                            Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));  
                }  
            }  
            if (familyName.equals("author")) { // author列族put数据  
                for (int j = 0; j < column2.length; j++) {  
                    put.add(Bytes.toBytes(familyName),  
                            Bytes.toBytes(column2[j]), Bytes.toBytes(value2[j]));  
                }  
            }  
        }  
        table.put(put);  
        System.out.println("add data Success!");  
	}
}
