package com.SparkLearning.TextSegmentation;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.ASegment;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.IWord;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;

import com.SparkLearning.Comparator.AnalyzerDescComparators;
import com.SparkLearning.Model.Words;
import com.SparkLearning.Model.WordsDAO;

public class CustomizeAnalyzer {
	
	public static List<Words> wordCountCustomizeAnalyzer(List<String> datas){
		HashMap<String , Integer> wordList = new HashMap<String, Integer>();
		List<Words> result = new ArrayList<Words>();
        for(String s : datas){
        	List<String> words = workCutsomAnalyzer(s);
        	for(String w : words){
        		if(wordList.containsKey(w)){
        			Integer temp = wordList.get(w) + 1;
        			wordList.put(w,temp);
        		}else{
        			wordList.put(w, 1);
        		}	
        	}
        }
        result = sortCustomizeAnalyzer(wordList);
		return result;
	}
	
	public static List<Words> sortCustomizeAnalyzer(HashMap<String, Integer> wordList){
		List<Words> sortList = new ArrayList<Words>() ;
		for(String key : wordList.keySet()){
			Words word = new Words(key,wordList.get(key));
			sortList.add(word);
		}
		sortList.sort(new AnalyzerDescComparators());
		return sortList;
	}
	
	public static List<String> addCustomizeAnalyzer(List<String> datas){
		List<String> wordList = new ArrayList<String>();
        for(String s : datas){
        	List<String> words = workCutsomAnalyzer(s);
        	wordList.addAll(words);
        }
		return wordList;
	}
	
	public static List<String> workCutsomAnalyzer(String s ){
		List<String> words = new ArrayList<String>();
		
    	Analyzer analyzer = new JcsegAnalyzer(JcsegTaskConfig.COMPLEX_MODE);
        //非必须(用于修改默认配置): 获取分词任务配置实例  
        JcsegAnalyzer jcseg = (JcsegAnalyzer) analyzer;  	
        JcsegTaskConfig config = jcseg.getTaskConfig();  
        //追加同义词到分词结果中, 需要在jcseg.properties中配置jcseg.loadsyn=1  
        config.setAppendCJKSyn(true);  
        //追加拼音到分词结果中, 需要在jcseg.properties中配置jcseg.loadpinyin=1  
        config.setAppendCJKPinyin(true);  
        //更多配置, 请查看com.webssky.jcseg.core.JcsegTaskConfig类    
        TokenStream stream = null;  
        try {  
            stream = analyzer.tokenStream("myfield", s);  
            stream.reset();   
            CharTermAttribute  offsetAtt = stream.addAttribute(CharTermAttribute.class);  
            while (stream.incrementToken()) {  
            	words.add(offsetAtt.toString());
                //System.out.println(offsetAtt.toString());  
            }  
            stream.end();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            try {  
                if(stream != null)  
                    stream.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } 
        return words;
	}
}
