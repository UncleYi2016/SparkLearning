package com.SparkLearning.Comparator;

import java.util.Comparator;

import com.SparkLearning.Model.Words;

public class AnalyzerDescComparators implements Comparator<Words> {

	@Override
	public int compare(Words o1, Words o2) {
		return o2.getCount() - o1.getCount();
	}

}
