package com.SparkLearning.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.SparkLearning.Comparator.DescComparators;
import com.SparkLearning.Model.WordsDAO;

import scala.Tuple2;

public class WordCount {
	public static List<WordsDAO> countByUri(String uri) throws IOException{
		List<WordsDAO> result = new ArrayList<WordsDAO>();
		SparkConf conf = new SparkConf()
				.setAppName("WordCount")
				.setMaster("local[2]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> textFile = sc.textFile(uri);
//		String doc = textFile.first();
		String doc = "";
		InputStream is = new FileInputStream(uri);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
        	doc += line; // 将读到的内容添加到 buffer 中
            line = reader.readLine(); // 读取下一行
        }
		/*		中文分词部分		*/
		StopRecognition filter = new StopRecognition();
		filter.insertStopWords(Arrays.asList("r","n"));
		filter.insertStopNatures("w",null,"ns","r","u","e");
		String doc2 = ToAnalysis.parse(doc).recognition(filter).toStringWithOutNature(" ");
		System.out.println(doc2);
		
		try{
			Writer w = new FileWriter("C://Users/vm/Desktop/temp.txt", true);
			w.write(doc2);
			w.close();
		}catch (IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		textFile = sc.textFile("C://Users/vm/Desktop/temp.txt");
		/*		中文分词部分		*/
		JavaPairRDD<String, Integer> counts = textFile
				.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1))
				.reduceByKey((a,b) -> a+b);
		List<Tuple2<String, Integer>> output = counts.collect();
	    for (Tuple2<?,?> tuple : output) {
	      System.out.println(tuple._1() + ": " + tuple._2());
	      result.add(new WordsDAO((String)tuple._1, (int)tuple._2));
	      
	    }
//	    counts.saveAsTextFile("c://Users/vm/Desktop/result");
	    
	    File file = new File("c://Users/vm/Desktop/temp.txt");
	    file.delete();
	    result.sort(new DescComparators());
	    return result;
	    /*		关键词提取		*/
//	    KeyWordComputer kwc = new KeyWordComputer(5);
//	    Collection<Keyword> result = kwc.computeArticleTfidf("疾病", doc);
//	    System.out.println(result);
	    /*		关键词提取		*/
	}
}
