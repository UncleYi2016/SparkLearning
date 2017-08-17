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
import com.SparkLearning.Model.Words;
import com.SparkLearning.TextSegmentation.CustomizeAnalyzer;


import org.apache.spark.mllib.linalg.Vector;
import scala.Tuple2;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "./default_analyze.txt";
		int loadNum = -1;
		try{
			loadNum = Integer.parseInt(args[0]);
			path = args[1];
		}catch(Exception e){
			
		}
//		List<WordsDAO> result = WordCount.countByUri("C://Users/vm/Desktop/wc.txt"); //读取的文件
		File file = new File(path);	//输出排序后的结果
		FileWriter out = new FileWriter(file);
//		for(WordsDAO w : result){
//			out.write(w.getContent() + "---" + w.getCount() + "\r\n");
//		}
//		out.close();
		
//		LoadHBaseData.LoadPostInfo();
//		String words = "期待此吧有校友有哪些是剑阁的？衡水哪里有租赁或者卖卡通形象的衣服的，就是那种充满了气人站在里面的那种，哪里有大头娃娃啊，带在头上 卡通衣服兔子最好，没有 的话别的也可以，全东莞最有影响力教育机构全能找到，家教信息免费发布。东莞培训通www.dgpxt.com 哈，标题要亮、亮、亮… 明天就情人节了，祝天下有情人终成眷属…有句话是这样说的 不爱的爱情永远不会变坏 我们调情 我们暧昧 但是我们不爱第一:什么时候开学?!要准确了!!!! 第二:怎么查成绩!这都好久!";
		List<String> testList = new ArrayList<String>();
		testList = LoadHBaseData.LoadPostInfo(loadNum);
//		testList.add(words);
		List<String> tempList = new ArrayList<String>();
		List<Words> testMap = CustomizeAnalyzer.wordCountCustomizeAnalyzer(testList);
		int count = 1;
		for(Words key : testMap){
			out.write(key.getWord() + "..." + key.getCount() + "\n");
//			System.out.println("Writing no. " + count++);
//			System.out.println(key.getWord() + "..." + key.getCount() + "\n");
		}
		out.close();
		System.out.println("FINISHED!");
	}
}
