package com.daijt.cth.service;

import java.util.List;

import com.daijt.cth.persistence.WordEntry;

public interface DictionaryService {
	
	public void addWordEntry(WordEntry we);
	
	public List<WordEntry> wordLookup(String word);
}
