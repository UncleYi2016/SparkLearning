package com.SparkLearning;

import java.util.ArrayList;
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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.SparkLearning.Controller.LoadHBaseData;
import com.SparkLearning.Controller.WordCount;
import com.SparkLearning.TextSegmentation.CustomizeAnalyzer;


import org.apache.spark.mllib.linalg.Vector;
import scala.Tuple2;

public class test {

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		List<String> datas = LoadHBaseData.LoadPostInfo();
		HashMap<String,Integer> testMap = CustomizeAnalyzer.addCustomizeAnalyzer(datas);
		for(String key : testMap.keySet()){
			System.out.println(key + "..." +  testMap.get(key));
		}
	}
}
