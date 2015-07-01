package com.daijt.cth.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "download_history")
public class DownloadRecord implements Serializable{

	private static final long serialVersionUID = -5272728129801852147L;
	
	private long id;
	private String word;
	private long fontId;
	private long sourceId;
	private long deviceId;
	private Timestamp dlDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	@Column(name = "font_id")
	public long getFontId() {
		return fontId;
	}
	public void setFontId(long fontId) {
		this.fontId = fontId;
	}
	
	@Column(name = "source_id")
	public long getSourceId() {
		return sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
	
	@Column(name = "device_id")
	public long getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name = "dl_date")
	public Timestamp getDlDate() {
		return dlDate;
	}
	public void setDlDate(Timestamp dlDate) {
		this.dlDate = dlDate;
	}
	
}
