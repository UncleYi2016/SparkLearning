package com.SparkLearning;

import java.util.Collection;
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
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.SparkLearning.Controller.LoadHBaseData;
import com.SparkLearning.Controller.WordCount;
import com.SparkLearning.Model.WordsDAO;

import scala.Tuple2;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		List<WordsDAO> result = WordCount.countByUri("C://Users/vm/Desktop/wc.txt"); //读取的文件
//		File file = new File("C://Users/vm/Desktop/wc_result.txt");	//输出排序后的结果
//		FileWriter out = new FileWriter(file);
//		for(WordsDAO w : result){
//			out.write(w.getContent() + "---" + w.getCount() + "\r\n");
//		}
//		out.close();
		
		LoadHBaseData.LoadPostInfo();
	}
	

}
