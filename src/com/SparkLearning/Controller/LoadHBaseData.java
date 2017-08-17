package com.SparkLearning.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class LoadHBaseData {
	public static int runtimes = 600000;//链接数据库的等待时间
	public static Configuration config;
	public HTable table;
	public static HBaseAdmin admin;
	
	public static List<String> LoadPostInfo(){
		List<String> result = new ArrayList<String>();
		config = HBaseConfiguration.create();
		config.set("hbase.master", "hadoop5.site:16020");//代码使用16020，但是仍然需要配置16000
		config.set("hbase.zookeeper.quorum", "hadoop3.site");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		
		try {
			//table = new HTable(config, Bytes.toBytes("scores"));
	        admin = new HBaseAdmin(config);
		} catch (Exception e) {
			System.out.println("Hbase连接出错："+e);
		}
		
		HTablePool pool = new HTablePool(config, runtimes);
		
		try {
			ResultScanner rs = pool.getTable("post_info").getScanner(new Scan());
			int count = 0;
			for (Result r : rs) {
				if(r != null){
					String data = new String(r.getValue(Bytes.toBytes("post_main"), Bytes.toBytes("mp_detail")));
					result.add(data);
				}
				if(count == 300){
					break;
				}
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
