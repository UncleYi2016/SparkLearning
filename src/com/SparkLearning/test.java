package com.SparkLearning;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkConf conf = new SparkConf()
				.setAppName("WordCount")
				.setMaster("local[2]");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> textFile = sc.textFile("C://Users/vm/Desktop/medipay-illnames.txt");
//		String doc = textFile.first();
//		StopRecognition filter = new StopRecognition();
//		filter.insertStopWords(Arrays.asList("r","n"));
//		filter.insertStopNatures("w",null,"ns","r","u","e");
//		String doc2 = ToAnalysis.parse(doc).recognition(filter).toStringWithOutNature(" ");
//		System.out.println(doc2);
//		
//		try{
//			Writer w = new FileWriter("C://Users/vm/Desktop/temp.txt", true);
//			w.write(doc2);
//			w.close();
//		}catch (IOException ioe){
//			System.out.println(ioe.getMessage());
//		}
//		
//		textFile = sc.textFile("C://Users/vm/Desktop/temp.txt");
		JavaPairRDD<String, Integer> counts = textFile
				.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1))
				.reduceByKey((a,b) -> a+b);
		List<Tuple2<String, Integer>> output = counts.collect();
	    for (Tuple2<?,?> tuple : output) {
	      System.out.println(tuple._1() + ": " + tuple._2());
	    }
	    counts.saveAsTextFile("c://Users/vm/Desktop/result");
	    
	    File file = new File("c://Users/vm/Desktop/temp.txt");
	    file.delete();
	}
	

}
