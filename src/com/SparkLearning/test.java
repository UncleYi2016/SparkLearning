package com.SparkLearning;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.feature.IDF;
import org.apache.spark.rdd.RDD;

import com.SparkLearning.Controller.LoadHBaseData;
import com.SparkLearning.Controller.WordCount;
import com.SparkLearning.Model.WordsDAO;


import org.apache.spark.mllib.linalg.Vector;
import scala.Tuple2;

public class test {

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		counts.put("的", 1);
		
		Integer temp = counts.get("的");
		temp++;
		counts.put("的", temp);
		counts.put("的2", temp);
		counts.put("的3", temp);
		counts.put("的4", temp);
		counts.put("的5", temp);
		counts.put("的6", temp);
		for(String key : counts.keySet()){
			System.out.println(key + " --- " + counts.get(key));
		}
		
		// TODO Auto-generated method stub
//		List<WordsDAO> result = WordCount.countByUri("C://Users/vm/Desktop/wc.txt"); //读取的文件
//		File file = new File("C://Users/vm/Desktop/wc_result.txt");	//输出排序后的结果
//		FileWriter out = new FileWriter(file);
//		for(WordsDAO w : result){
//			out.write(w.getContent() + "---" + w.getCount() + "\r\n");
//		}
//		out.close();
		
		
//		LoadHBaseData.LoadPostInfo();
	}
	

}
