package com.daijt.cth.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class WordEntry implements Serializable {

	private static final long serialVersionUID = 4391933907657373478L;
	
	private String id;
	private String word;
	private String tags;
	private String category;
	private String simplCh;
	private String tradCh;
	private int safeStatus;
	private int audit;
	private String warning;
	
	@Id
	@Column
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	@Column
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Column
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name = "simpl_ch")
	public String getSimplCh() {
		return simplCh;
	}
	public void setSimplCh(String simplCh) {
		this.simplCh = simplCh;
	}
	
	@Column(name = "trad_ch")
	public String getTradCh() {
		return tradCh;
	}
	public void setTradCh(String tradCh) {
		this.tradCh = tradCh;
	}
	
	@Column(name = "safe_status")
	public int getSafeStatus() {
		return safeStatus;
	}
	public void setSafeStatus(int safeStatus) {
		this.safeStatus = safeStatus;
	}
	
	@Column
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}
	
	@Column
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	

}
