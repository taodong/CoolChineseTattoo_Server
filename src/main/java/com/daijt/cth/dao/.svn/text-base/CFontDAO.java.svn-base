package com.daijt.cth.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Service;

import com.daijt.cth.persistence.CFont;
import com.daijt.cth.service.CFontService;

@Service
public class CFontDAO extends JpaDaoSupport implements CFontService {

	private static final String ALL_VAILABLE_FONTS = "from CFont cf where cf.status = 'A' order by cf.weight";
	
	@Autowired
	@Required
	public void setJpaEntityManagerFactory(
	        @Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}
	
	public List<CFont> listAllFonts() {
		
		@SuppressWarnings("unchecked")
		List<CFont> fonts = (List<CFont>)getJpaTemplate().find(ALL_VAILABLE_FONTS);
		
		return fonts;
	}

}
