package com.SparkLearning.Comparator;

import java.util.Comparator;

import com.SparkLearning.Model.WordsDAO;

public class DescComparators implements Comparator<WordsDAO>{

	@Override
	public int compare(WordsDAO o1, WordsDAO o2) {
		return o2.getCount() - o1.getCount();
	}
}

