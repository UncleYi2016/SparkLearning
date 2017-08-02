package com.SparkLearning.Comparator;

import java.util.Comparator;

import com.SparkLearning.Model.WordsDAO;

public class AscComparators implements Comparator<WordsDAO>{

	@Override
	public int compare(WordsDAO o1, WordsDAO o2) {
		return o1.getCount() - o2.getCount();
	}
}

