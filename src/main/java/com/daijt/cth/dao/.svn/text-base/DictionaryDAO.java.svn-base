package com.daijt.cth.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Service;

import com.daijt.cth.persistence.WordEntry;
import com.daijt.cth.service.DictionaryService;

@Service
public class DictionaryDAO extends JpaDaoSupport implements DictionaryService {
	
	// private static Logger log = Logger.getLogger(DictionaryDAO.class);
	
	private static final String WORD_LOOK_UP = "from WordEntry we where we.tags like ?";

	@Autowired
	@Required
	public void setJpaEntityManagerFactory(
	        @Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}
	
	public void addWordEntry(WordEntry we) {
		getJpaTemplate().persist(we);
	}

	@SuppressWarnings("unchecked")
	public List<WordEntry> wordLookup(String word) {
		
		List<WordEntry> words = getJpaTemplate().find(WORD_LOOK_UP, new Object[]{word});
		
		return words;
	}

}
