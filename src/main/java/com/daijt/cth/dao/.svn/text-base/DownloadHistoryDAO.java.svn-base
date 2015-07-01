package com.daijt.cth.dao;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Service;

import com.daijt.cth.persistence.DownloadRecord;
import com.daijt.cth.service.DownloadHistoryService;

@Service
public class DownloadHistoryDAO extends JpaDaoSupport implements
		DownloadHistoryService {

	@Autowired
	@Required
	public void setJpaEntityManagerFactory(
	        @Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}
	
	public void addDownloadRecord(DownloadRecord dr) {
		getJpaTemplate().persist(dr);
	}

}
